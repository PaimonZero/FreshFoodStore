/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import dto.ProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Products;

/**
 *
 * @author nguye
 */
@WebServlet(name = "CategoryDAO", urlPatterns = {"/customer/category"})
public class CategoryController extends HttpServlet {

    private static final int PAGE_SIZE = 12;

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
        CategoryDAO c = new CategoryDAO();
        int traicay = c.countProduct("Fruits");
        int raucu = c.countProduct("Vegetables");
        int haisan = c.countProduct("Seafood");
        int thit = c.countProduct("Meat");
        int douong = c.countProduct("Beverages");
        int snack = c.countProduct("Snacks");
        int giavi = c.countProduct("Spices");
        int dauthucvat = c.countProduct("Vegetable Oil");
        int trung = c.countProduct("Egg");
        int nam = c.countProduct("Mushroom");
        int protein = c.countProduct("Protein");

        // paging
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int offset = (page - 1) * PAGE_SIZE;
        int row = PAGE_SIZE;

        String food = request.getParameter("food");
        String sortOrder = request.getParameter("sortProduct");
        int productCount = getProductCount(food);
        List<ProductDTO> products = getProductByType(food, offset, row, sortOrder);

        int pageCount;
        if(productCount % PAGE_SIZE==0){
            pageCount =productCount /PAGE_SIZE;
        } else {
            pageCount =productCount /PAGE_SIZE +1;
        }

        List<ProductDTO> sale = c.listProductSale();

        request.setAttribute("sale", sale);

        request.setAttribute("productCount", productCount);
        request.setAttribute("products", products);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("currentPage", page);

        request.setAttribute("raucu", raucu);
        request.setAttribute("traicay", traicay);
        request.setAttribute("haisan", haisan);
        request.setAttribute("thit", thit);
        request.setAttribute("douong", douong);
        request.setAttribute("snack", snack);
        request.setAttribute("giavi", giavi);
        request.setAttribute("dauthucvat", dauthucvat);
        request.setAttribute("trung", trung);
        request.setAttribute("nam", nam);
        request.setAttribute("protein", protein);

        request.getRequestDispatcher("Category.jsp").forward(request, response);
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

        processRequest(request, response);
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
        processRequest(request, response);
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

    private List<ProductDTO> getProductByType(String food, int offset, int row, String sortOrder) {

        CategoryDAO cd = new CategoryDAO();
        List<ProductDTO> p = new ArrayList<>();
        if ("Fruits".equals(food)) {
            p = cd.findByName("Fruits", offset, row);
        } else if ("Vegetables".equals(food)) {
            p = cd.findByName("Vegetables", offset, row);
        } else if ("Seafood".equals(food)) {
            p = cd.findByName("Seafood", offset, row);
        } else if ("Meat".equals(food)) {
            p = cd.findByName("Meat", offset, row);
        } else if ("Beverages".equals(food)) {
            p = cd.findByName("Beverages", offset, row);
        } else if ("Snacks".equals(food)) {
            p = cd.findByName("Snacks", offset, row);
        } else if ("Spices".equals(food)) {
            p = cd.findByName("Spices", offset, row);
        } else if ("Vegetable Oil".equals(food)) {
            p = cd.findByName("Vegetable Oil", offset, row);
        } else if ("Egg".equals(food)) {
            p = cd.findByName("Egg", offset, row);
        } else if ("Mushroom".equals(food)) {
            p = cd.findByName("Mushroom", offset, row);
        } else if ("Protein".equals(food)) {
            p = cd.findByName("Protein", offset, row);
        }

        if (sortOrder != null) {
            switch (sortOrder) {
                case "tang":
                    p = cd.sortProductByUporDown(food, "p.unitPrice", "asc", offset, row);
                    break;
                case "giam":
                    p = cd.sortProductByUporDown(food, "p.unitPrice", "desc", offset, row);
                    break;
                case "moi":
                    p = cd.sortProductByUporDown(food, "p.productId", "desc", offset, row);
                    break;

                default:
                    break;
            }
        }

        return p;

    }

    private int getProductCount(String food) {

        CategoryDAO cd = new CategoryDAO();

        int p = 0;
        if ("Fruits".equals(food)) {
            p = cd.countProduct("Fruits");
        } else if ("Vegetables".equals(food)) {
            p = cd.countProduct("Vegetables");
        } else if ("Seafood".equals(food)) {
            p = cd.countProduct("Seafood");
        } else if ("Meat".equals(food)) {
            p = cd.countProduct("Meat");
        } else if ("Beverages".equals(food)) {
            p = cd.countProduct("Beverages");
        } else if ("Snacks".equals(food)) {
            p = cd.countProduct("Snacks");
        } else if ("Spices".equals(food)) {
            p = cd.countProduct("Spices");
        } else if ("Vegetable Oil".equals(food)) {
            p = cd.countProduct("Vegetable Oil");
        } else if ("Egg".equals(food)) {
            p = cd.countProduct("Egg");
        } else if ("Mushroom".equals(food)) {
            p = cd.countProduct("Mushroom");
        } else if ("Protein".equals(food)) {
            p = cd.countProduct("Protein");
        }

        return p;

    }

}
