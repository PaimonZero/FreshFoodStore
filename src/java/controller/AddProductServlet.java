package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String name = request.getParameter("name");
        String unitMeasure = request.getParameter("unitMeasure");
        int supplierId = Integer.parseInt(request.getParameter("supplierId"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String description = request.getParameter("description");
        BigDecimal unitPrice = BigDecimal.valueOf(Double.parseDouble((request.getParameter("unitPrice"))));
        String status = request.getParameter("status");

        // Call addProduct method from ProductDao
        ProductDAO productDao = new ProductDAO();
        productDao.addProduct(name, unitMeasure, supplierId, categoryId, description, unitPrice, status);

        // Redirect to a confirmation or product list page
        response.sendRedirect("Product.jsp");
    }
}

