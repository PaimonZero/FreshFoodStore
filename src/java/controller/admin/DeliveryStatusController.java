/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DashboardDAO;
import dal.DeliveryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import model.OrderDetail;
import model.Users;
import util.Validate;

/**
 *
 * @author PC
 */
public class DeliveryStatusController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet orderDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet orderDetailController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        DashboardDAO dao = new DashboardDAO();
        DeliveryDAO daod = new DeliveryDAO();

        HttpSession session = request.getSession();
        Users account = (Users) session.getAttribute("account");
        //lấy thông tin user
        Users listInfo = dao.findAllInfo(account.getUserId());
        request.setAttribute("listInfo", listInfo);

        //lấy thông tin chi tiết của order
        int orderId = Integer.parseInt(request.getParameter("currentId"));

        List<OrderDetail> orderStatus = daod.getOrderDetailById(orderId);
        for (OrderDetail orderStatu : orderStatus) {
            orderStatu.setUnitPriceOutString(Validate.doubleToMoney(orderStatu.getUnitPriceOut()));//chuyển thành tiền việt
            orderStatu.setDiscountString(Validate.doubleToMoney(orderStatu.getDiscount()));
            orderStatu.setShippingFeeString(Validate.doubleToMoney(orderStatu.getShippingFee()));
            orderStatu.setOrderCreatedAtString(Validate.convertDateFormat(orderStatu.getOrderCreatedAt()));//định dạng ngày dd/mm/yyyy
        }
        request.setAttribute("orderStatus", orderStatus);
        request.getRequestDispatcher("/admin/DeliveryStatus.jsp").forward(request, response);

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
        DashboardDAO dao = new DashboardDAO();
        DeliveryDAO daod = new DeliveryDAO();

        HttpSession session = request.getSession();
        Users account = (Users) session.getAttribute("account");
        //lấy thông tin user
        Users listInfo = dao.findAllInfo(account.getUserId());
        request.setAttribute("listInfo", listInfo);

        //lấy thông tin chi tiết của order
        int orderId = Integer.parseInt(request.getParameter("currentId"));

        List<OrderDetail> orderStatus = daod.getOrderDetailById(orderId);
        for (OrderDetail orderStatu : orderStatus) {
            orderStatu.setUnitPriceOutString(Validate.doubleToMoney(orderStatu.getUnitPriceOut()));//chuyển thành tiền việt
            orderStatu.setDiscountString(Validate.doubleToMoney(orderStatu.getDiscount()));
            orderStatu.setShippingFeeString(Validate.doubleToMoney(orderStatu.getShippingFee()));
            orderStatu.setOrderCreatedAtString(Validate.convertDateFormat(orderStatu.getOrderCreatedAt()));//định dạng ngày dd/mm/yyyy
        }
//        orderStatus.setUnitPriceOutString(Validate.doubleToMoney(orderStatus.getUnitPriceOut()));//chuyển thành tiền việt
//        orderStatus.setDiscountString(Validate.doubleToMoney(orderStatus.getDiscount()));
//        orderStatus.setShippingFeeString(Validate.doubleToMoney(orderStatus.getShippingFee()));
//        orderStatus.setOrderCreatedAtString(Validate.convertDateFormat(orderStatus.getOrderCreatedAt()));//định dạng ngày dd/mm/yyyy

        request.setAttribute("orderStatus", orderStatus);
        request.getRequestDispatcher("/admin/DeliveryStatus.jsp").forward(request, response);
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
