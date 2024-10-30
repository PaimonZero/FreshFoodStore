/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DashboardDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.OrderDetail;
import model.Users;
import util.Validate;

/**
 *
 * @author DELL
 */
public class orderDetailController extends HttpServlet {

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
            out.println("<title>Servlet orderDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet orderDetailController at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DashboardDAO dao = new DashboardDAO();
        HttpSession session = request.getSession();
        Users account = (Users) session.getAttribute("account");
        //lấy thông tin user
        Users listInfo = dao.findAllInfo(account.getUserId());
        request.setAttribute("listInfo", listInfo);
//        //hiện thông tin order của user
//        ArrayList<Orders> listOrder = dao.findAllOrder(account.getUserId());
//        //chuyển thành dạng tiền việt
//        for (Orders sp : listOrder) {
//            sp.setTotalString(Validate.doubleToMoney(sp.getTotal()));
//            sp.setOrderCreatedAtString(Validate.convertDateFormat(sp.getOrderCreatedAt()));
//        }
//        request.setAttribute("listOrder", listOrder);

        //lấy thông tin chi tiết của order
        int orderId = Integer.parseInt(request.getParameter("orderID"));
        ArrayList<OrderDetail> orderCurrent = dao.findOrderDetailById(orderId);
        int totalQuantity = 0;
        // Giả sử danh sách orderCurrent có ít nhất một phần tử
        if (!orderCurrent.isEmpty()) {
            // Lấy thông tin từ phần tử đầu tiên
            String deliveryStatus = orderCurrent.get(0).getDeliveryStatus();
            orderId = orderCurrent.get(0).getOrderId();
            String paymentType = orderCurrent.get(0).getPaymentType();
            String shippingFeeString = Validate.doubleToMoney(orderCurrent.get(0).getShippingFee());
            String address = orderCurrent.get(0).getAddress(); // Lấy địa chỉ
            String fullName = orderCurrent.get(0).getFullName(); // Lấy tên đầy đủ
            String email = orderCurrent.get(0).getEmail(); // Lấy email
            String phone = orderCurrent.get(0).getPhone(); // Lấy số điện thoại
            String receiverName = orderCurrent.get(0).getReceiverName(); // Lấy tên người nhận
            String deliveryLocation = orderCurrent.get(0).getDeliveryLocation(); // Lấy vị trí giao hàng
            String receiverPhone = orderCurrent.get(0).getReceiverPhone(); // Lấy số điện thoại người nhận
            String orderCreatedAtString = Validate.convertDateFormat(orderCurrent.get(0).getOrderCreatedAt());

            // Đặt các giá trị vào request attribute
            request.setAttribute("deliveryStatus", deliveryStatus);
            request.setAttribute("orderId", orderId);
            request.setAttribute("paymentType", paymentType);
            request.setAttribute("shippingFeeString", shippingFeeString);
            request.setAttribute("address", address);
            request.setAttribute("fullName", fullName);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("receiverName", receiverName);
            request.setAttribute("deliveryLocation", deliveryLocation);
            request.setAttribute("receiverPhone", receiverPhone);
            request.setAttribute("orderCreatedAtString", orderCreatedAtString);

        }
        for (OrderDetail orderCurrentItem : orderCurrent) {
            orderCurrentItem.setUnitPriceOutString(Validate.doubleToMoney(orderCurrentItem.getUnitPriceOut()));//chuyển thành tiền việt
            orderCurrentItem.setDiscountString(Validate.doubleToMoney(orderCurrentItem.getDiscount()));
            orderCurrentItem.setShippingFeeString(Validate.doubleToMoney(orderCurrentItem.getShippingFee()));
            orderCurrentItem.setOrderCreatedAtString(Validate.convertDateFormat(orderCurrentItem.getOrderCreatedAt()));//định dạng ngày dd/mm/yyyy
            totalQuantity += orderCurrentItem.getQuantity();
        }
        request.setAttribute("totalQuantity", totalQuantity);
        request.setAttribute("orderCurrent", orderCurrent);
        request.getRequestDispatcher("/customer/orderDetail.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
//done