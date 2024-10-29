package controller.admin;

import dal.OrdersDAO;
import dal.ProductDAO;
import dal.ReceiptsDAO;
import dto.OrderDTO;
import dto.ReceiptDTO;
import dto.ReceiptDetailDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.OrderDetail;
import model.Products;
import model.ReceiptDetails;
import model.Users;

@WebServlet(name = "ReceiptsController", urlPatterns = {"/admin/receipts"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class ReceiptsController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReceiptsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReceiptsController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //- Lấy giá trị action về
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        //- switch case cac action
        switch (action) {
            case "viewReceiptDetail":
                handleViewReceiptDetail(request, response);
                break;
            default:
                // Khởi tạo ReceiptsDAO để lấy danh sách đơn hàng từ cơ sở dữ liệu
                ReceiptsDAO redao = new ReceiptsDAO();
                List<ReceiptDTO> listReceipt = redao.getAllReceipts();

                // Đặt dữ liệu vào request attribute
                request.setAttribute("listReceipt", listReceipt);

                // Chuyển tiếp đến trang Orders.jsp
                request.getRequestDispatcher("Receipts.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //- Lấy giá trị action về
            String action = request.getParameter("action") == null ? "" : request.getParameter("action");
            //- switch case cac action
            switch (action) {
                case "viewReceiptDetail":
                    handleViewReceiptDetail(request, response);
                    break;
                case "addDetailOld":
                    handleAddDetailOld(request, response);
                    break;
                case "addDetailNew":
                    handleAddDetailNew(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (ParseException ex) {
            Logger.getLogger(ReceiptsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void handleViewReceiptDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Khởi tạo ReceiptsDAO để lấy danh sách đơn hàng từ cơ sở dữ liệu
        ReceiptsDAO redao = new ReceiptsDAO();
        ProductDAO pdao = new ProductDAO();

        HttpSession session = request.getSession();
        Users account = (Users) session.getAttribute("account");

        //lấy thông tin chi tiết của receipts
        int receiptId = Integer.parseInt(request.getParameter("currentId"));

        List<ReceiptDetailDTO> listReDetail = redao.getReceiptDetailById(receiptId);
        ReceiptDTO receiptInfo = redao.getReceiptById(receiptId);
        List<Products> listProducts = redao.getAllProducts();
        
        if(!listReDetail.isEmpty()){
            //lấy thông tin các lô hàng liên quan đến đơn nhập
            List<Integer> receiptDetailIds = new ArrayList<>();
            for (ReceiptDetailDTO rd : listReDetail) {
                receiptDetailIds.add(rd.getReceiptDetailId());
            }
            request.setAttribute("listBatches", redao.getBatchesByReceiptDetailIds(receiptDetailIds));
        }

        List<Category> categories = pdao.getAllCategories();

        // Đặt dữ liệu vào request attribute
        request.setAttribute("listReDetail", listReDetail);
        request.setAttribute("receiptInfo", receiptInfo);
        request.setAttribute("listProducts", listProducts);
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/admin/ReceiptDetail.jsp").forward(request, response);
    }

    private void handleAddDetailOld(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        // Khởi tạo ReceiptsDAO để lấy danh sách đơn hàng từ cơ sở dữ liệu
        ReceiptsDAO redao = new ReceiptsDAO();

        HttpSession session = request.getSession();
        Users account = (Users) session.getAttribute("account");

        int receiptId = Integer.parseInt(request.getParameter("receiptId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double inputPrice = Double.parseDouble(request.getParameter("inputPrice"));
        //Định dạng ngày SQL là "yyyy-MM-dd"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date expiryDate = dateFormat.parse(request.getParameter("expiryDate"));
        ReceiptDetails rd = new ReceiptDetails(receiptId, productId, quantity, inputPrice, expiryDate);

        //thêm ReceiptDetails vào db
        int receiptDetailId = redao.insertReDetail(rd);
       
        if (receiptDetailId > 0) {
            //thêm thông tin vào BatchesProduct
            boolean kqb = redao.insertBatchesProduct(receiptDetailId, productId, quantity, expiryDate);
            
            // Chuyển hướng về trang JSP với trạng thái mới
            response.sendRedirect("receipts?action=viewReceiptDetail&currentId=" + receiptId);
        } else {
            //lỗi
            System.out.println("??? Error when insertReDetail in controller!");
        }
    }

    private void handleAddDetailNew(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
        // Khởi tạo ReceiptsDAO để lấy danh sách đơn hàng từ cơ sở dữ liệu
        ReceiptsDAO redao = new ReceiptsDAO();

        HttpSession session = request.getSession();
        Users account = (Users) session.getAttribute("account");

        //Thêm sảm phẩm trước, Get form parameters
        String name = request.getParameter("name");
        String unitMeasure = request.getParameter("unitMeasure");
        int supplierId = Integer.parseInt(request.getParameter("supplierId"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String description = request.getParameter("description");
        BigDecimal unitPrice = BigDecimal.valueOf(Double.parseDouble((request.getParameter("unitPrice"))));
        String status = request.getParameter("status");

        List<String> proImages = handleMultipleFiles(request, response); // Xử lý file upload

        // Call addProduct method from ProductDao   //lấy ảnh đầu tiên làm thumbail
        int productId = redao.addProductInReceipt(name, unitMeasure, supplierId, categoryId, description, unitPrice, status, proImages.get(0));
        
        if (productId != -1) {
            // Kiểm tra nếu có ảnh mới được tải lên
            if (!proImages.isEmpty()) {
                //Thêm ảnh vào Gallery
                for (String proImage : proImages) {
                    boolean kqi = redao.insertGallery(productId, proImage);
                }
            } else {
                System.out.println("???No new images uploaded, gallery add skipped.");
            }
            
            //Thêm thông tin đơn nhập
            int receiptId = Integer.parseInt(request.getParameter("receiptId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double inputPrice = Double.parseDouble(request.getParameter("inputPrice"));
            //Định dạng ngày SQL là "yyyy-MM-dd"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date expiryDate = dateFormat.parse(request.getParameter("expiryDate"));
            ReceiptDetails rd = new ReceiptDetails(receiptId, productId, quantity, inputPrice, expiryDate);
            //thêm ReceiptDetails vào db
            int receiptDetailId = redao.insertReDetail(rd);
            
            //thêm thông tin vào BatchesProduct
            boolean kqb = redao.insertBatchesProduct(receiptDetailId, productId, quantity, expiryDate);
            
            // Chuyển hướng về trang JSP với trạng thái mới
            response.sendRedirect("receipts?action=viewReceiptDetail&currentId=" + receiptId);
        } else {
            //lỗi
            System.out.println("??? Error when addProductInReceipt in controller!");
        }
    }

    private List<String> handleMultipleFiles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Sử dụng getServletContext().getRealPath("") để lấy đường dẫn gốc của dự án
        String uploadPath = getServletContext().getRealPath("") + File.separator + "images" + File.separator + "products";

        // Tạo thư mục lưu file nếu chưa có
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            if (!created) {
                throw new ServletException("Failed to create upload directory.");
            }
        }

        // Danh sách tên các file đã lưu
        List<String> fileNames = new ArrayList<>();

        // Lặp qua các file từ form với tên là "file"
        for (Part filePart : request.getParts()) {
            if (filePart.getName().equals("file") && filePart.getSize() > 0) {
                // Lấy tên file
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                if (fileName == null || fileName.isEmpty()) {
                    continue;
                }

                // Đường dẫn đầy đủ của file sẽ được lưu
                String filePath = uploadPath + File.separator + fileName;

                // Ghi file vào thư mục đích
                try (InputStream fileContent = filePart.getInputStream()) {
                    File fileToSave = new File(filePath);
                    Files.copy(fileContent, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                
                // Trả về tên file đã lưu (là fileName và thêm ../images/products/ ở phía trc)
                fileName = "../images/products/" + fileName;
                
                // Thêm tên file vào danh sách
                fileNames.add(fileName);

                System.out.println("Uploaded File Path: " + filePath);
            }
        }

        // Trả về danh sách tên các file đã lưu
        return fileNames;
    }

}
