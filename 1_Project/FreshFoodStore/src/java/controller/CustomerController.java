/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Customer;

@WebServlet(name = "CustomerController", urlPatterns = {"/admin/customers"})
public class CustomerController extends HttpServlet {

    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle pagination for customers list
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

        List<Customer> customers = customerDAO.getAllCustomers(page, pageSize);
        int totalCustomers = customerDAO.getTotalCustomers();
        int totalPages = (int) Math.ceil(totalCustomers / (double) pageSize);

        // Set customers and pagination details as request attributes
        request.setAttribute("customers", customers);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Customer.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            String customerIdStr = request.getParameter("customerId");
            String name = request.getParameter("customerName");
            String number = request.getParameter("customerPhone");
            String email = request.getParameter("customerEmail");
            String address = request.getParameter("customerAddress");
            String status = request.getParameter("customerStatus");

            try {
                int customerId = Integer.parseInt(customerIdStr);

                Customer customer = new Customer();
                customer.setCustomerId(customerId);
                customer.setFullName(name);
                customer.setPhone(number);
                customer.setEmail(email);
                customer.setAddress(address);
                customer.setStatus(status);

                boolean updated = customerDAO.editCustomer(customer);

                if (updated) {
                    response.sendRedirect(request.getContextPath() + "/admin/customers");
                } else {
                    response.sendRedirect(request.getContextPath() + "/admin/customers?error=Update failed");
                }

            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/admin/customers?error=Invalid input format");
            }
        }
    }
}
