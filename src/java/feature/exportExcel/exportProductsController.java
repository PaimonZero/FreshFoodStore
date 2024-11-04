/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package feature.exportExcel;

import dal.ProductDAO;
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
@WebServlet(name = "exportProductsController", urlPatterns = {"/admin/exportProducts"})
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
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
