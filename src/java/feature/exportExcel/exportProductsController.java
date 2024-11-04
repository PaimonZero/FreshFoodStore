/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package feature.exportExcel;

import dal.OrdersDAO;
import dal.ProductDAO;
import dal.ReceiptsDAO;
import dal.UserDAO;
import dto.OrderDTO;
import dto.ReceiptDTO;
import dto.ReceiptDetailDTO;
import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Products;

/**
 *
 * @author HoangNam
 */
@WebServlet(name = "exportProductsController", urlPatterns = {"/admin/export"})
public class exportProductsController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet exportProductsController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet exportProductsController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //- Lấy giá trị action về
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        //- switch case cac action
        switch (action) {
            case "products":
                handleExportProduct(request, response);
                break;
            case "orders":
                handleExportOrder(request, response);
                break;
            case "users":
                handleExportUser(request, response);
                break;
            case "receipts":
                handleExportReceipt(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void handleExportProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        List<Products> products = dao.getAllProducts();

        // Default filename with date and time
        String currentDate = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "products_" + currentDate + ".xlsx";

        // Set response headers
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        // Write Excel file to the response output stream
        try (OutputStream outputStream = response.getOutputStream()) {
            ExportProductsToExcel.exportProductsToExcel(products, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            System.err.println("Error exporting Excel file: " + e.getMessage());
        }
        //Trả về trang product.jsp
        request.getRequestDispatcher("Product.jsp").forward(request, response);
    }

    private void handleExportOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrdersDAO orderDAO = new OrdersDAO();
        List<OrderDTO> orders = orderDAO.getAllOrders();

        // Default filename with date and time
        String currentDate = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "orders_" + currentDate + ".xlsx";

        // Set response headers
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        // Write Excel file to the response output stream
        try (OutputStream outputStream = response.getOutputStream()) {
            ExportOrdersToExcel.exportOrdersToExcel(orders, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            System.err.println("Error exporting Excel file: " + e.getMessage());
        }
        //Trả về trang order bên admin
        request.getRequestDispatcher("/OrdersController").forward(request, response);
    }

    private void handleExportUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        int page = 1; // Change this to get data for specific pages if needed
        int pageSize = 100; // Modify the page size as required
        List<UsersDTO> users = userDAO.getAllUsers(page, pageSize);

        // Default filename with date and time
        String currentDate = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "users_" + currentDate + ".xlsx";

        // Set response headers
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        // Write Excel file to the response output stream
        try (OutputStream outputStream = response.getOutputStream()) {
            ExportUsersToExcel.exportUsersToExcel(users, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            System.err.println("Error exporting Excel file: " + e.getMessage());
        }
        //Trả về trang user bên admin
        request.getRequestDispatcher("/users").forward(request, response);
    }

    private void handleExportReceipt(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int receiptId = Integer.parseInt(request.getParameter("receiptId"));
        ReceiptsDAO receiptDAO = new ReceiptsDAO();

        // Fetch receipt summary and details
        ReceiptDTO receipt = receiptDAO.getReceiptById(receiptId);
        List<ReceiptDetailDTO> receiptDetails = receiptDAO.getReceiptDetailById(receiptId);

        // Default filename with date and time
        String currentDate = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "receipt_" + receiptId + "_" + currentDate + ".xlsx";

        // Set response headers
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        // Write Excel file to the response output stream
        try (OutputStream outputStream = response.getOutputStream()) {
            ExportReceiptToExcel.exportReceiptToExcel(receipt, receiptDetails, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            System.err.println("Error exporting Excel file: " + e.getMessage());
        }
        // Chuyển hướng về trang JSP với trạng thái mới
        response.sendRedirect("receipts?action=viewReceiptDetail&currentId=" + receiptId);
    }

}
