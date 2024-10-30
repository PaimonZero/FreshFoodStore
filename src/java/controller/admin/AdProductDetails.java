package controller.admin;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
@WebServlet(urlPatterns = {"/admin/AdProductDetails"})
public class AdProductDetails extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdProductDetails</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdProductDetails at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //- Lấy giá trị action về
            String action = request.getParameter("action") == null ? "" : request.getParameter("action");
            //- switch case cac action
            switch (action) {
                case "updatePromo":
                    handlePromo(request, response);
                    break;
                case "updateBatch":
                    handleBatch(request, response);
                    break;
                case "deleteBatch":
                    handleDeleteBatch(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (ParseException ex) {
            Logger.getLogger(AdProductDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void handlePromo(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int productId = Integer.parseInt(request.getParameter("productId"));
        int promoId = Integer.parseInt(request.getParameter("promoId"));
        Date startDate = dateFormat.parse(request.getParameter("startDate"));
        Date endDate = dateFormat.parse(request.getParameter("endDate"));
        double discount = Double.parseDouble(request.getParameter("discount"));

        ProductDAO dao = new ProductDAO();
        dao.updatePromos(promoId, startDate, endDate, discount);

        request.getRequestDispatcher("ProductInfo.jsp?productId=" + productId).forward(request, response);
    }

    private void handleBatch(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int batchId = Integer.parseInt(request.getParameter("batchId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        ProductDAO dao = new ProductDAO();
        dao.updateBatch(batchId, quantity);

        request.getRequestDispatcher("ProductInfo.jsp?productId=" + productId).forward(request, response);
    }

    private void handleDeleteBatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int batchId = Integer.parseInt(request.getParameter("batchId"));

        ProductDAO dao = new ProductDAO();
        boolean isDeleted = dao.deleteBatchById(batchId);

        if (isDeleted) {
            // Redirect đến trang sản phẩm với thông báo thành công nếu cần
            request.setAttribute("message", "Batch deleted successfully.");
        } else {
            // Redirect với thông báo lỗi nếu xóa không thành công
            request.setAttribute("message", "Failed to delete batch.");
        }

        request.getRequestDispatcher("ProductInfo.jsp?productId=" + productId).forward(request, response);

    }
}
