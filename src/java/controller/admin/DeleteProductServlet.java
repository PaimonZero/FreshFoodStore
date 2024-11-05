package controller.admin;

import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdStr = request.getParameter("productId");
        
        // Kiểm tra xem productId có phải null hoặc rỗng hay không
        if (productIdStr == null || productIdStr.isEmpty() || productIdStr.equals("undefined")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID.");
            return; // Thoát khỏi phương thức
        }

        try {
            int productId = Integer.parseInt(productIdStr);
            ProductDAO productDao = new ProductDAO();
            // Gọi phương thức xóa sản phẩm
            productDao.deleteProduct(productId);
            
            // Chuyển hướng về trang Product.jsp sau khi xóa thành công
            response.sendRedirect("Product.jsp");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID format.");
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while deleting the product.");
        }
    }

    // Nếu cần, bạn có thể định nghĩa phương thức doPost nếu bạn muốn xử lý các yêu cầu POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Chuyển hướng yêu cầu POST sang yêu cầu GET
    }
}
