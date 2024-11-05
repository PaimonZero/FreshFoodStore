package controller.admin;

import dal.ProductDAO;
import dal.ReceiptsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("/updateProduct")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, //1MB
        maxFileSize = 1024 * 1024 * 10, //10MB
        maxRequestSize = 1024 * 1024 * 50 //50MB
)

public class UpdateProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin từ request
        String productIdStr = request.getParameter("productId");
        String name = request.getParameter("name");
        String unitMeasure = request.getParameter("unitMeasure");;
        String categoryIdStr = request.getParameter("categoryId");
        String description = request.getParameter("description");
        String unitPriceStr = request.getParameter("unitPrice");
        String status = request.getParameter("status");


        // Kiểm tra các giá trị null và hợp lệ
        if (productIdStr == null || productIdStr.isEmpty() || !productIdStr.matches("\\d+")
//                || supplierIdStr == null || supplierIdStr.isEmpty() || !supplierIdStr.matches("\\d+")
                || categoryIdStr == null || categoryIdStr.isEmpty() || !categoryIdStr.matches("\\d+")
                || unitPriceStr == null || unitPriceStr.isEmpty() || !unitPriceStr.matches("\\d+")
                ) {
            request.setAttribute("errorMessage", "One or more fields are missing or invalid.");
            request.getRequestDispatcher("Product.jsp").forward(request, response);
            return;
        }

        try {
            int productId = Integer.parseInt(productIdStr);
//            int supplierId = Integer.parseInt(supplierIdStr);
            int categoryId = Integer.parseInt(categoryIdStr);
            BigDecimal unitPrice = BigDecimal.valueOf(Double.parseDouble(unitPriceStr));

            // Chuyển đổi expirationDateStr thành đối tượng Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Tạo đối tượng Product và gọi phương thức cập nhật
            ProductDAO productDao = new ProductDAO();
            ReceiptsDAO redao = new ReceiptsDAO();

            List<String> proImages = handleMultipleFiles(request, response); // Xử lý file upload

            // Kiểm tra nếu có ảnh mới được tải lên
            if (!proImages.isEmpty()) {
                // Xóa ảnh cũ trong gallery
                boolean kqDelete = redao.deleteGalleryByPId(productId);

                // Thêm ảnh mới vào Gallery
                for (String proImage : proImages) {
                    boolean kqi = redao.insertGallery(productId, proImage);
                }
            } else {
                proImages.add(request.getParameter("pImgOld"));     //Thêm lại ảnh thumnail cũ để update table product ko bị lỗi
                System.out.println("No new images uploaded, gallery update skipped.");
            }

            //Xử lý update product
            productDao.updateProduct(productId, name, unitMeasure, categoryId, description, unitPrice, status, proImages.get(0));
            // Chuyển hướng về trang sản phẩm
            response.sendRedirect("ProductInfo.jsp?productId=" + productId);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid number format.");
            request.getRequestDispatcher("ProductInfo.jsp").forward(request, response);
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
