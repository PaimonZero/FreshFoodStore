/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BatchProductDAO;
import dal.ProductDAO;
import dto.ProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Products;
import util.Validate;

/**
 *
 * @author nguye
 */
@WebServlet(name = "ProductDetailController", urlPatterns = "/customer/productDetail")
public class ProductDetailController extends HttpServlet {

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
        ProductDAO p = new ProductDAO();
        BatchProductDAO bpdao = new BatchProductDAO();
        int productId = Integer.parseInt(request.getParameter("id"));
         bpdao.updateBatchforProductDetail();

        ProductDTO pdto = p.findProductById(productId);
        List<Products> lienquan = p.findProductByCategory(productId);
        for(Products x:lienquan){
            x.setUnitPriceString(Validate.BigDecimalToMoney(x.getUnitPrice()));
        }
        ArrayList<ProductDTO> listImage = p.findProductGalleryById(productId);
        pdto.setUnitPriceString(Validate.BigDecimalToMoney(pdto.getUnitPrice()));
        request.setAttribute("listImage", listImage);
        request.setAttribute("lienquan", lienquan);
        request.setAttribute("product", pdto);
        request.getRequestDispatcher("productDetail.jsp").forward(request, response);
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

}
