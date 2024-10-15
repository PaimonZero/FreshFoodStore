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
import model.OrderDetail;
import model.Users;
import util.Validate;

/**
 *
 * @author DELL
 */
public class orderDetailController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<h1>Servlet orderDetailController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
        OrderDetail orderCurrent = dao.findOrderDetailById(orderId);
        orderCurrent.setUnitPriceOutString(Validate.doubleToMoney(orderCurrent.getUnitPriceOut()));//chuyển thành tiền việt
        orderCurrent.setDiscountString(Validate.doubleToMoney(orderCurrent.getDiscount()));
        orderCurrent.setShippingFeeString(Validate.doubleToMoney(orderCurrent.getShippingFee()));
        orderCurrent.setOrderCreatedAtString(Validate.convertDateFormat(orderCurrent.getOrderCreatedAt()));//định dạng ngày dd/mm/yyyy
        request.setAttribute("orderCurrent", orderCurrent);
        request.getRequestDispatcher("/customer/orderDetail.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
