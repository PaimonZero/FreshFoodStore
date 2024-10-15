/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.checkOutDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.OrderDetail;
import model.Users;
import util.Validate;

/**
 *
 * @author DELL
 */
public class checkOutController extends HttpServlet {

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
            out.println("<title>Servlet checkOutController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet checkOutController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
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
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        switch (action) {
            case "listCart":
                handleListCart(request, response);
                break;
            case "updateCartInfo":
                handleUpdateInfo(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void handleListCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkOutDAO dao = new checkOutDAO();
        HttpSession session = request.getSession();

        //Lấy về userID từ account trong sesion khi đăng nhập
        Users account = (Users) session.getAttribute("account");
        int orderId = (int) session.getAttribute("giohangOrderId");
        ArrayList<OrderDetail> listCart = dao.listAllCart(account.getUserId(), orderId);//tạm thời để orderId là 11 để test
        if (!listCart.isEmpty()) {
            // Lấy shippingFee từ một sản phẩm trong listCart (giá trị shippingFee giống nhau cho toàn đơn hàng)
            double shippingFee = listCart.get(0).getShippingFee();
            // Set giá trị shippingFee dạng tiền tệ (chuỗi định dạng tiền)
            String shippingFeeString = Validate.doubleToMoney(shippingFee);
            request.setAttribute("shippingFee", shippingFeeString);
        }
        for (OrderDetail sp : listCart) {
//            sp.setTotalString(Validate.doubleToMoney(sp.getTotal()));
//            sp.setSubTotalString(Validate.doubleToMoney(sp.getSubTotal()));
            sp.setUnitPriceOutString(Validate.doubleToMoney(sp.getUnitPriceOut()));
            sp.setDiscountString(Validate.doubleToMoney(sp.getDiscount()));
//            sp.setShippingFeeString(Validate.doubleToMoney(sp.getShippingFee()));
        }
        request.setAttribute("listCart", listCart);
        request.getRequestDispatcher("/customer/CheckOut.jsp").forward(request, response);
    }

    private void handleUpdateInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkOutDAO dao = new checkOutDAO();
        HttpSession session = request.getSession();

        //Lấy về userID từ account trong sesion khi đăng nhập
        Users account = (Users) session.getAttribute("account");
//        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String receiverName = request.getParameter("fullName");
        String deliveryLocation = request.getParameter("address");
        String receiverPhone = request.getParameter("receiverPhone");
        String paymentType = request.getParameter("payment");
        String paymentStatus = "Pending";//tạm thời để cứng
        int isConfirmed = Integer.parseInt(request.getParameter("isConfirmed"));
        int orderId = (int) session.getAttribute("giohangOrderId");

        if (paymentType.equals("Cash")) {
            isConfirmed = 1;//set về thành order
            dao.updateCartInfo(paymentStatus, paymentType, deliveryLocation, receiverName, receiverPhone, isConfirmed, account.getUserId(), orderId); //để tạm để test
            request.getRequestDispatcher("/customer/OrderHistory").forward(request, response);
        } else {
            dao.updateCartInfo(paymentStatus, paymentType, deliveryLocation, receiverName, receiverPhone, isConfirmed, account.getUserId(), orderId); //để tạm để test
            request.getRequestDispatcher("/customer/CheckOut.jsp").forward(request, response); //thay đường dẫn ở đây
        }
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
