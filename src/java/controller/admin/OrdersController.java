/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DashboardDAO;
import dal.DeliveryDAO;
import dal.OrdersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import dto.OrderDTO;
import dto.ShipperDTO;
import jakarta.servlet.http.HttpSession;
import model.OrderDetail;
import model.Users;
import util.Validate;

/**
 *
 * @author plmin
 */
public class OrdersController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Orders Controller</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrdersController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //===================Hàm phân quyền=====================================================
        HttpSession session = request.getSession();

        //Lấy về userID từ account trong sesion khi đăng nhập
        Users account = (Users) session.getAttribute("account");

        if (account != null) {
            String role = account.getRole();
            if (!role.equals("manager") && !role.equals("staff") && !role.equals("shipper")) {
                session.setAttribute("notifyAuth", "notAuthorized");

                //Chuyển hướng trang qua chủ customer
                String targetURL = request.getContextPath() + "/customer/Homepage";      //đổi dường dẫn ở đây
                String encodedURL = response.encodeRedirectURL(targetURL);
                response.sendRedirect(encodedURL);
                return;
            } else if (role.equals("shipper")) {        //nếu là shipper thì ko cho coi trang này
                session.setAttribute("notifyAuth", "notAuthorized");

                String targetURL = request.getContextPath() + "/admin/DeliveryList";      //đổi dường dẫn ở đây
                String encodedURL = response.encodeRedirectURL(targetURL);
                response.sendRedirect(encodedURL);
                return;
            }
        } else {
            // If no account is found in the session, redirect to login or another appropriate page
            String loginURL = request.getContextPath() + "/SignIn.jsp";
            String encodedURL = response.encodeRedirectURL(loginURL);
            response.sendRedirect(encodedURL);
            return;
        }
        //===================End Hàm phân quyền=================================================

        //- Lấy giá trị action về
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        //- switch case cac action
        switch (action) {
            case "viewOrderDetail":
                handleViewOrderDetail(request, response);
                break;
            default:
                // Khởi tạo OrdersDAO để lấy danh sách đơn hàng từ cơ sở dữ liệu
                OrdersDAO ordersDAO = new OrdersDAO();
                List<OrderDTO> orderDisplayList = ordersDAO.getAllOrders();

                // Lấy thông tin tổng quan đơn hàng
                Map<String, Object> orderOverview = ordersDAO.getOrderOverview();

                // Đặt dữ liệu vào request attribute
                request.setAttribute("orderDisplayList", orderDisplayList);
                request.setAttribute("orderOverview", orderOverview);

                // Chuyển tiếp đến trang Orders.jsp
                request.getRequestDispatcher("Orders.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //===================Hàm phân quyền=====================================================
        HttpSession session = request.getSession();

        //Lấy về userID từ account trong sesion khi đăng nhập
        Users account = (Users) session.getAttribute("account");

        if (account != null) {
            String role = account.getRole();
            if (!role.equals("manager") && !role.equals("staff") && !role.equals("shipper")) {
                session.setAttribute("notifyAuth", "notAuthorized");

                //Chuyển hướng trang qua chủ customer
                String targetURL = request.getContextPath() + "/customer/Homepage";      //đổi dường dẫn ở đây
                String encodedURL = response.encodeRedirectURL(targetURL);
                response.sendRedirect(encodedURL);
                return;
            } else if (role.equals("shipper")) {        //nếu là shipper thì ko cho coi trang này
                session.setAttribute("notifyAuth", "notAuthorized");

                String targetURL = request.getContextPath() + "/admin/DeliveryList";      //đổi dường dẫn ở đây
                String encodedURL = response.encodeRedirectURL(targetURL);
                response.sendRedirect(encodedURL);
                return;
            }
        } else {
            // If no account is found in the session, redirect to login or another appropriate page
            String loginURL = request.getContextPath() + "/SignIn.jsp";
            String encodedURL = response.encodeRedirectURL(loginURL);
            response.sendRedirect(encodedURL);
            return;
        }
        //===================End Hàm phân quyền=================================================

        //- Lấy giá trị action về
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        //- switch case cac action
        switch (action) {
            case "viewOrderDetail":
                handleViewOrderDetail(request, response);
                break;
            case "updateShippingStatus":
                handleUpdateShippingStatus(request, response);
                break;
            case "updatePaymentStatus":
                handleUpdatePaymentStatus(request, response);
                break;
            case "updateShipper":
                handleUpdateShipper(request, response);
                break;
            case "search":
                handleSearch(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void handleViewOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrdersDAO odao = new OrdersDAO();

        HttpSession session = request.getSession();
        Users account = (Users) session.getAttribute("account");

        //lấy thông tin chi tiết của order
        int orderId = Integer.parseInt(request.getParameter("currentId"));
        List<OrderDetail> orderStatus = odao.getOrderDetailById(orderId);
        //lấy danh sách shipper cho modal update shipper
        List<ShipperDTO> listShipper = odao.getAllShipperInfo();

        for (OrderDetail orderStatu : orderStatus) {
            orderStatu.setUnitPriceOutString(Validate.doubleToMoney(orderStatu.getUnitPriceOut()));//chuyển thành tiền việt
            orderStatu.setDiscountString(Validate.doubleToMoney(orderStatu.getDiscount()));
            orderStatu.setShippingFeeString(Validate.doubleToMoney(orderStatu.getShippingFee()));
            orderStatu.setOrderCreatedAtString(Validate.convertDateFormat(orderStatu.getOrderCreatedAt()));//định dạng ngày dd/mm/yyyy
        }

        request.setAttribute("listShipper", listShipper);
        request.setAttribute("orderStatus", orderStatus);
        request.getRequestDispatcher("/admin/OrderDetails.jsp").forward(request, response);
    }

    private void handleUpdateShippingStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String action = request.getParameter("actionShip");

        DeliveryDAO deliveryDAO = new DeliveryDAO();
        List<OrderDetail> orderStatus = deliveryDAO.getOrderDetailById(orderId);

        String currentStatus = deliveryDAO.getCurrentStatus(orderId); // Lấy trạng thái hiện tại

        String newStatus = currentStatus;
        String paymentStatus = orderStatus.get(0).getPaymentStatus();

        // Xác định trạng thái mới
        switch (action) {
            case "update":
                switch (currentStatus) {
                    case "Cancel":
                        newStatus = "Waiting";
                        break;
                    case "Waiting":
                        newStatus = "Shipping";
                        break;
                    case "Shipping":
                        newStatus = "Delivered";
//                        paymentStatus = "Paid";      //đã bỏ vì thấy ko hợp lý
                        break;
                    default:
                        // Handle unexpected currentStatus if necessary
                        break;
                }
                break;

            case "undo":
                switch (currentStatus) {
                    case "Cancel":
                        newStatus = "Cancel";
                        break;
                    case "Delivered":
                        newStatus = "Shipping";
                        break;
                    case "Shipping":
                        newStatus = "Waiting";
                        break;
                    case "Waiting":
                        newStatus = "Cancel";
                        break;
                    default:
                        // Handle unexpected currentStatus if necessary
                        break;
                }
                break;
            default:
                // Handle unexpected action if necessary
                break;
        }

        // Gọi phương thức cập nhật trạng thái trong DeliveryDAO
        boolean isUpdated = deliveryDAO.updateDeliveryStatus(orderId, newStatus, paymentStatus);

        // Chuyển hướng về trang JSP với trạng thái mới
        response.sendRedirect("OrdersController?action=viewOrderDetail&currentId=" + orderId);
    }

    private void handleUpdatePaymentStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String action = request.getParameter("actionPay");

        OrdersDAO dao = new OrdersDAO();
        DeliveryDAO daod = new DeliveryDAO();
        List<OrderDetail> orderStatus = dao.getOrderDetailById(orderId);

        String deliveryStatus = orderStatus.get(0).getDeliveryStatus(); // Lấy trạng thái hiện tại

        String paymentStatus = orderStatus.get(0).getPaymentStatus();   // Lấy trạng thái hiện tại
        String newPayStatus = paymentStatus;

        // Xác định trạng thái mới
        switch (action) {
            case "update":
                if ("Waiting".equals(paymentStatus)) {
                    newPayStatus = "Paid";
                }
                break;
            case "undo":
                if ("Paid".equals(paymentStatus)) {
                    newPayStatus = "Waiting";
                }
                break;
            default:
                throw new AssertionError();
        }
        // Gọi phương thức cập nhật trạng thái trong DeliveryDAO
        boolean isUpdated = daod.updateDeliveryStatus(orderId, deliveryStatus, newPayStatus);

        // Chuyển hướng về trang JSP với trạng thái mới
        response.sendRedirect("OrdersController?action=viewOrderDetail&currentId=" + orderId);
    }

    private void handleUpdateShipper(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int shipperId = Integer.parseInt(request.getParameter("shipperId"));
//        String shipperName = request.getParameter("shipperName");
//        String shipperPhone = request.getParameter("shipperPhone");

        OrdersDAO dao = new OrdersDAO();
        dao.updateDelivery(orderId, shipperId);

        // Chuyển hướng về trang JSP với trạng thái mới
        response.sendRedirect("OrdersController?action=viewOrderDetail&currentId=" + orderId);
    }

    private void handleSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");
        
        // Khởi tạo OrdersDAO để lấy danh sách đơn hàng từ cơ sở dữ liệu
        OrdersDAO ordersDAO = new OrdersDAO();
        List<OrderDTO> orderDisplayList = ordersDAO.searchOrders(searchQuery);

        // Lấy thông tin tổng quan đơn hàng
        Map<String, Object> orderOverview = ordersDAO.getOrderOverview();

        // Đặt dữ liệu vào request attribute
        request.setAttribute("orderDisplayList", orderDisplayList);
        request.setAttribute("orderOverview", orderOverview);

        // Chuyển tiếp đến trang Orders.jsp
        request.getRequestDispatcher("Orders.jsp").forward(request, response);
    }

}
