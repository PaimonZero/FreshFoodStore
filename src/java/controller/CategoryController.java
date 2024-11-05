/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BatchProductDAO;
import dal.CategoryDAO;
import dal.ProductDAO;
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

        ProductDAO prdao = new ProductDAO(); // hàng lẻ 
        BatchProductDAO bpdao = new BatchProductDAO();
        bpdao.updateBatchforProductDetail();

        CategoryDAO c = new CategoryDAO();
        int traicay = c.countProduct("Fruits");
        int raucu = c.countProduct("Vegetables");
        int haisan = c.countProduct("Seafood");
        int thit = c.countProduct("Meat");
        int douong = c.countProduct("Beverages");
        int snack = c.countProduct("Snacks");
        int giavi = c.countProduct("Spices");
        int dauthucvat = c.countProduct("VegetableOil");
        int trung = c.countProduct("Egg");
        int DairyProducts = c.countProduct("DairyProducts");
        int Tuber = c.countProduct("Tuber");
        int CerealsNuts = c.countProduct("CerealsNuts");

        // paging
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int offset = (page - 1) * PAGE_SIZE;
        int row = PAGE_SIZE;

        String food = request.getParameter("food");
        String sortOrder = request.getParameter("sortProduct");
//          sort range
        String minStr = request.getParameter("minRange");
        String maxStr = request.getParameter("maxRange");

        double min = 1000;
        double max = 1000000;
        if (minStr != null && !minStr.isBlank() && maxStr != null && !maxStr.isBlank()) {
            min = Double.parseDouble(minStr);
            max = Double.parseDouble(maxStr);
        }
        //  them
        List<ProductDTO> products = new ArrayList<>();

        int pageCount;
        int pageCount2;

        // search 
        String textSearch = request.getParameter("textSearch");
        if (textSearch == null || textSearch.trim().isEmpty() || !textSearch.matches("^[\\p{L}\\p{M}0-9\\s]*$")) {
            products = getProductByType(food, min, max, offset, row, sortOrder);
            int productCount = getProductCount(food);
            if (productCount % PAGE_SIZE == 0) {
                pageCount = productCount / PAGE_SIZE;
            } else {
                pageCount = productCount / PAGE_SIZE + 1;
            }
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);
            request.setAttribute("productCount", productCount);
            request.setAttribute("textSearch", textSearch);

        } else {
            products = prdao.findByName(textSearch, offset, row);
            int productCount2 = prdao.countProductSearch(textSearch);
            if (productCount2 % PAGE_SIZE == 0) {
                pageCount2 = productCount2 / PAGE_SIZE;
            } else {
                pageCount2 = productCount2 / PAGE_SIZE + 1;
            }
            request.setAttribute("pageCount2", pageCount2);
            request.setAttribute("currentPage2", page);
            request.setAttribute("productCount2", productCount2);
        }

        List<ProductDTO> sale = c.listProductSale();

        request.setAttribute("sale", sale);
        request.setAttribute("textSearch", textSearch);

        request.setAttribute("products", products);

        request.setAttribute("raucu", raucu);
        request.setAttribute("traicay", traicay);
        request.setAttribute("haisan", haisan);
        request.setAttribute("thit", thit);
        request.setAttribute("douong", douong);
        request.setAttribute("snack", snack);
        request.setAttribute("giavi", giavi);
        request.setAttribute("dauthucvat", dauthucvat);
        request.setAttribute("trung", trung);
        request.setAttribute("DairyProducts", DairyProducts);
        request.setAttribute("BabyFood", Tuber);
        request.setAttribute("CerealsNuts", CerealsNuts);

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

    private List<ProductDTO> getProductByType(String food, double min, double max, int offset, int row, String sortOrder) {

        CategoryDAO cd = new CategoryDAO();
        List<ProductDTO> p = new ArrayList<>();
        if ("Fruits".equals(food)) {
            p = cd.findByName("Fruits", min, max, offset, row);
        } else if ("Vegetables".equals(food)) {
            p = cd.findByName("Vegetables", min, max, offset, row);
        } else if ("Seafood".equals(food)) {
            p = cd.findByName("Seafood", min, max, offset, row);
        } else if ("Meat".equals(food)) {
            p = cd.findByName("Meat", min, max, offset, row);
        } else if ("Beverages".equals(food)) {
            p = cd.findByName("Beverages", min, max, offset, row);
        } else if ("Snacks".equals(food)) {
            p = cd.findByName("Snacks", min, max, offset, row);
        } else if ("Spices".equals(food)) {
            p = cd.findByName("Spices", min, max, offset, row);
        } else if ("VegetableOil".equals(food)) {
            p = cd.findByName("VegetableOil", min, max, offset, row);
        } else if ("Egg".equals(food)) {
            p = cd.findByName("Egg", min, max, offset, row);
        } else if ("DairyProducts".equals(food)) {
            p = cd.findByName("DairyProducts", min, max, offset, row);
        } else if ("Tuber".equals(food)) {
            p = cd.findByName("Tuber", min, max, offset, row);
        } else if ("CerealsNuts".equals(food)) {
            p = cd.findByName("CerealsNuts", min, max, offset, row);
        }

        if (sortOrder != null) {
            switch (sortOrder) {
                case "tang":
                    p = cd.sortProductByUporDown(food, min, max, "p.unitPrice", "asc", offset, row);
                    break;
                case "giam":
                    p = cd.sortProductByUporDown(food, min, max, "p.unitPrice", "desc", offset, row);
                    break;
                case "moi":
                    p = cd.sortProductByUporDown(food, min, max, "p.productId", "desc", offset, row);
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
        } else if ("VegetableOil".equals(food)) {
            p = cd.countProduct("Vegetable Oil");
        } else if ("Egg".equals(food)) {
            p = cd.countProduct("Egg");
        } else if ("DairyProducts".equals(food)) {
            p = cd.countProduct("DairyProducts");
        } else if ("Tuber".equals(food)) {
            p = cd.countProduct("Tuber");
        } else if ("CerealsNuts".equals(food)) {
            p = cd.countProduct("CerealsNuts");
        }

        return p;

    }

}
//done
