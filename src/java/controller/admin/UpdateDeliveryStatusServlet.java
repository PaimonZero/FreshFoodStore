/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DashboardDAO;
import dal.DeliveryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.OrderDetail;
import model.Users;
import util.Validate;

/**
 *
 * @author PC
 */
@WebServlet("/admin/UpdateDeliveryStatus")
public class UpdateDeliveryStatusServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String action = request.getParameter("action");

        DeliveryDAO deliveryDAO = new DeliveryDAO();
        List<OrderDetail> orderStatus = deliveryDAO.getOrderDetailById(orderId);

        String currentStatus = deliveryDAO.getCurrentStatus(orderId); // Lấy trạng thái hiện tại

        String newStatus = currentStatus;
        String paymentStatus = orderStatus.get(0).getPaymentStatus();

        // Xác định trạng thái mới
        if ("update".equals(action)) {
            if ("Cancel".equals(currentStatus)) {
                newStatus = "Waiting";
            } else if ("Waiting".equals(currentStatus)) {
                newStatus = "Shipping";
            } else if ("Shipping".equals(currentStatus)) {
                newStatus = "Delivered";
                paymentStatus = "Paid";
            } else if ("Delivered".equals(currentStatus)) {
                newStatus = "Delivered"; // Cập nhật nếu đến lần thứ 5
                paymentStatus = "Paid";
            }
        } else if ("undo".equals(action)) {
            if ("Cancel".equals(currentStatus)) {
                newStatus = "Cancel";
            } else if ("Delivered".equals(currentStatus)) {
                newStatus = "Shipping";
            } else if ("Shipping".equals(currentStatus)) {
                newStatus = "Waiting";
            } else if ("Waiting".equals(currentStatus)) {
                newStatus = "Cancel";
            }
        }

        // Gọi phương thức cập nhật trạng thái trong DeliveryDAO
        boolean isUpdated = deliveryDAO.updateDeliveryStatus(orderId, newStatus, paymentStatus);

        // Chuyển hướng về trang JSP với trạng thái mới
        response.sendRedirect("DeliveryStatusController?currentId=" + orderId);
//request.getRequestDispatcher("DeliveryStatusController").forward(request, response);
    }
}
