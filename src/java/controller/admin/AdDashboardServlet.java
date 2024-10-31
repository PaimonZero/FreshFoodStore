package controller.admin;

import dal.AdDashboardDAO;
import dal.DashboardDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import model.Users;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdDashboardServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DashboardDAO dao = new DashboardDAO();
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

                String targetURL = request.getContextPath() + "/admin/Delivery.jsp";      //đổi dường dẫn ở đây
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

        // Instantiate the DAO
        AdDashboardDAO dashboardDAO = new AdDashboardDAO();

        // Fetch data from DAO
        int totalProductsSold = dashboardDAO.calculateTotalProductsSold();
        double totalRevenue = dashboardDAO.calculateTotalRevenue();
        double totalCost = dashboardDAO.calculateTotalCost();
        double profit = dashboardDAO.calculateProfit();
        int totalProducts = dashboardDAO.calculateTotalProducts();
        int totalQuantity = dashboardDAO.calculateTotalQuantityInReceiptDetails();
        double totalInputPrice = dashboardDAO.calculateTotalInputPrice();
        int totalReceipts = dashboardDAO.calculateTotalReceipts();
        int totalDeliveredOrders = dashboardDAO.calculateTotalDeliveredOrders();
        int totalCanceledOrders = dashboardDAO.calculateTotalCanceledOrders();
        int totalSuppliers = dashboardDAO.calculateTotalSuppliers();
        int totalCategories = dashboardDAO.calculateTotalCategories();

        List<Map<String, Object>> topSellingProducts = dashboardDAO.getTopSellingProducts();
        List<Map<String, Object>> leastStockedProducts = dashboardDAO.getLeastStockedProducts();

        DecimalFormat df = new DecimalFormat("#,###.##");
        // Set attributes to pass data to JSP
        request.setAttribute("totalProductsSold", totalProductsSold);
        request.setAttribute("totalRevenue", df.format(totalRevenue));
        request.setAttribute("totalCost", df.format(totalCost));
        request.setAttribute("profit", df.format(profit));
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("totalQuantity", totalQuantity);
        request.setAttribute("totalInputPrice", df.format(totalInputPrice));
        request.setAttribute("totalReceipts", totalReceipts);
        request.setAttribute("totalDeliveredOrders", totalDeliveredOrders);
        request.setAttribute("totalCanceledOrders", totalCanceledOrders);
        request.setAttribute("totalSuppliers", totalSuppliers);
        request.setAttribute("totalCategories", totalCategories);

        request.setAttribute("topSellingProducts", topSellingProducts);
        request.setAttribute("leastStockedProducts", leastStockedProducts);

        // Forward data to JSP page for rendering
        request.getRequestDispatcher("/admin/Dashboard.jsp").forward(request, response);
    }

    // Thêm điều kiện trả về JSON nếu yêu cầu từ fetch
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for Admin Dashboard";
    }
}
