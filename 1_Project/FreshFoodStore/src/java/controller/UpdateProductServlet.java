package controller;

import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/updateProduct")
public class UpdateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin từ request
        String productIdStr = request.getParameter("productId");
        String name = request.getParameter("name");
        String unitMeasure = request.getParameter("unitMeasure");
        String supplierIdStr = request.getParameter("supplierId");
        String categoryIdStr = request.getParameter("categoryId");
        String description = request.getParameter("description");
        String unitPriceStr = request.getParameter("unitPrice");
        String status = request.getParameter("status");
        String quantityStr = request.getParameter("quantity");
        String expirationDateStr = request.getParameter("expirationDate");

        // Kiểm tra các giá trị null và hợp lệ
        if (productIdStr == null || productIdStr.isEmpty() || !productIdStr.matches("\\d+") ||
            supplierIdStr == null || supplierIdStr.isEmpty() || !supplierIdStr.matches("\\d+") ||
            categoryIdStr == null || categoryIdStr.isEmpty() || !categoryIdStr.matches("\\d+") ||
            unitPriceStr == null || unitPriceStr.isEmpty() || !unitPriceStr.matches("\\d+") ||
            expirationDateStr == null) {
            request.setAttribute("errorMessage", "One or more fields are missing or invalid.");
            request.getRequestDispatcher("Product.jsp").forward(request, response);
            return;
        }

        try {
            int productId = Integer.parseInt(productIdStr);
            int supplierId = Integer.parseInt(supplierIdStr);
            int categoryId = Integer.parseInt(categoryIdStr);
            BigDecimal unitPrice = BigDecimal.valueOf(Double.parseDouble(unitPriceStr));
            int quantity = Integer.parseInt(quantityStr);

            // Chuyển đổi expirationDateStr thành đối tượng Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(expirationDateStr);

            // Tạo đối tượng Product và gọi phương thức cập nhật
            ProductDAO productDao = new ProductDAO();
            productDao.updateProduct(productId, name, unitMeasure, supplierId, categoryId, description, unitPrice, status, quantity, utilDate);
            // Chuyển hướng về trang sản phẩm
            response.sendRedirect("ProductInfo.jsp?productId=" + productId);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid number format.");
            request.getRequestDispatcher("ProductInfo.jsp").forward(request, response);
        } catch (ParseException e) {
            request.setAttribute("errorMessage", "Invalid date format.");
            request.getRequestDispatcher("ProductInfo.jsp").forward(request, response);
        }
    }
}



