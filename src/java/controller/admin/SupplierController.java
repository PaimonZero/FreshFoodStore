/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.SupplierDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Supplier;
import model.Users;

@WebServlet(name = "SupplierController", urlPatterns = {"/admin/suppliers"})
public class SupplierController extends HttpServlet {

    private SupplierDAO supplierDAO;

    @Override
    public void init() throws ServletException {
        supplierDAO = new SupplierDAO();
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
        
        // Handle pagination for supplier list
        String pageStr = request.getParameter("page");
        int page = 1;
        int pageSize = 10;

        if (pageStr != null) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        List<Supplier> supplier = supplierDAO.getAllSupplier(page, pageSize);
        int totalSupplier = supplierDAO.getTotalSupplier();
        int totalPages = (int) Math.ceil(totalSupplier / (double) pageSize);

        // Set supplier and pagination details as request attributes
        request.setAttribute("supplier", supplier);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Suppliers.jsp");
        dispatcher.forward(request, response);
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

        //- Lấy giá trị action về
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        //- switch case cac action
        switch (action) {
            case "edit":
                handleEditSupplier(request, response);
                break;
            case "search":
                handleSearchSupplier(request, response);
                break;
            case "add":
                handleAddSupplier(request, response);
                break;
            default:
                throw new AssertionError();
        }
        
    }

    private void handleEditSupplier(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String supplierIdStr = request.getParameter("supplierId");
        String name = request.getParameter("supplierName");
        String number = request.getParameter("supplierPhone");
        String email = request.getParameter("supplierEmail");
        String address = request.getParameter("supplierAddress");
        String moreInfo = request.getParameter("supplierInfo");
//            String status = request.getParameter("supplierStatus");

        try {
            int supplierId = Integer.parseInt(supplierIdStr);

            Supplier supplier = new Supplier();
            supplier.setSupplierId(supplierId);
            supplier.setName(name);
            supplier.setPhone(number);
            supplier.setEmail(email);
            supplier.setAddress(address);
            supplier.setMoreInfo(moreInfo);
//                supplier.setStatus(status);

            boolean updated = supplierDAO.editSupplier(supplier);

            if (updated) {
                response.sendRedirect(request.getContextPath() + "/admin/suppliers");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/suppliers?error=Update failed");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin/suppliers?error=Invalid input format");
        }
    }

    private void handleAddSupplier(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("supplierName");
        String number = request.getParameter("supplierPhone");
        String email = request.getParameter("supplierEmail");
        String address = request.getParameter("supplierAddress");
        String moreInfo = request.getParameter("supplierInfo");

        try {
            Supplier supplier = new Supplier();
            supplier.setName(name);
            supplier.setPhone(number);
            supplier.setEmail(email);
            supplier.setAddress(address);
            supplier.setMoreInfo(moreInfo);

            boolean updated = supplierDAO.addSupplier(supplier);

            if (updated) {
                response.sendRedirect(request.getContextPath() + "/admin/suppliers");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/suppliers?error=Add failed");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin/suppliers?error=Invalid input format");
        }
    }

    private void handleSearchSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle pagination for customers list
        String pageStr = request.getParameter("page");
        int page = 1;
       
        //lấy dữ liệu tìm kiếm
        String query = request.getParameter("searchQuery");
        
        
        List<Supplier> supplier = supplierDAO.searchSuppliers(query);
        int totalPages = 1;

        // Set supplier and pagination details as request attributes
        request.setAttribute("supplier", supplier);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Suppliers.jsp");
        dispatcher.forward(request, response);
    }
}
