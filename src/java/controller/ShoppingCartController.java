/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OrderDetailDAO;
import dal.ProductDAO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import dto.ProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.Orders;
import model.Users;
import util.Validate;

/**
 *
 * @author nguye
 */
@WebServlet(name = "ShoppingCartController", urlPatterns = {"/customer/shoppingcart"})
public class ShoppingCartController extends HttpServlet {

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

        HttpSession session = request.getSession();
        int productId = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("soluong"));
        //khi chưa login thì quay về login
        if (session.getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/auth");
            return;
        }

        Users user = (Users) session.getAttribute("account");
        OrderDetailDAO od = new OrderDetailDAO();
        OrderDTO o = od.findOrderById(user.getUserId());

        ProductDTO p = od.findProductById(productId);

        if (o == null) { //không có order
            o = new OrderDTO(); //tạo order mới
            o.setUserId(user.getUserId());
            od.createOrder(o);
        }
        
        OrderDetailDTO odDTO = od.findOrderDetail(o.getOrderId(), productId);
        if (odDTO == null) {
            odDTO = new OrderDetailDTO();
            odDTO.setOrderId(o.getOrderId());
            odDTO.setQuantity(quantity);
            odDTO.setBatchId(p.getBatchId());
            odDTO.setUnitPriceOut(BigDecimal.valueOf(quantity).multiply(p.getUnitPrice()).multiply(BigDecimal.valueOf(p.getDiscount())));
            od.createOrderDetail(odDTO);
        } else {
            odDTO.setQuantity(odDTO.getQuantity() + quantity);
            od.updateQuantity(odDTO);
        }
        List<OrderDetailDTO> listOrderDetail = od.listOrderDetail(o.getOrderId());
        for(OrderDetailDTO x:listOrderDetail){
            x.setUnitPriceString(Validate.BigDecimalToMoney(x.getUnitPrice()));
            x.setDiscountString(Validate.doubleToMoney(x.getDiscount()));
        }
        session.setAttribute("giohangOrderId", o.getOrderId());
        request.setAttribute("listProduct", listOrderDetail);

//        List<ProductDTO> listProduct = (List<ProductDTO>) session.getAttribute("listProduct");
//        if (session.getAttribute("listProduct") == null) {
//            listProduct = new ArrayList<>();
//        }
//        ProductDAO prd = new ProductDAO();
//        ProductDTO p = prd.findProductById(productId);
//        listProduct.add(p);
//        session.setAttribute("listProduct", listProduct);
        request.getRequestDispatcher("/customer/ShoppingCart.jsp").forward(request, response);

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
//        processRequest(request, response);
 
        
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        switch (action) {
            case "delete":
                handleDeleteOrderId(request,response);
                break;
            case "updateCartInfo":
//                handleUpdateInfo(request, response);
                break;
            default:
                throw new AssertionError();
        }
        
    }
   private void handleDeleteOrderId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       
       int deleteId = Integer.parseInt(request.getParameter("deleteId"));
       OrderDetailDAO od = new OrderDetailDAO();
       od.deleteOrderDetailById(deleteId);
       response.sendRedirect(request.getContextPath()+"/customer/giohang");
       
       
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
