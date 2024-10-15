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
import java.util.ArrayList;
import model.Orders;
import model.Users;
import util.Validate;

/**
 *
 * @author DELL
 */
public class OrderHistoryController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderHistoryController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderHistoryController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();

        //Lấy về userID từ account trong sesion khi đăng nhập
        Users account = (Users) session.getAttribute("account");
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int count = dao.CountOrder(account.getUserId());
        int offset = (page - 1) * PAGE_SIZE;
        int row = PAGE_SIZE;
//        int pageCount = count / PAGE_SIZE;
        int pageCount;
        if (count % PAGE_SIZE == 0) {
            pageCount = count / PAGE_SIZE;
        } else {
            pageCount = count / PAGE_SIZE + 1;
        }
        ArrayList<Orders> AllOrderList = dao.findAllOrder(account.getUserId(), offset, row);
        for (Orders sp : AllOrderList) {
            sp.setTotalString(Validate.doubleToMoney(sp.getTotal()));
            sp.setOrderCreatedAtString(Validate.convertDateFormat(sp.getOrderCreatedAt()));
        }
        request.setAttribute("AllOrderList", AllOrderList);
        request.setAttribute("count", count);
        request.setAttribute("currentPage", page);
        request.setAttribute("pageCount", pageCount);
        request.getRequestDispatcher("/customer/orderHistory.jsp").forward(request, response);
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

        DashboardDAO dao = new DashboardDAO();
        HttpSession session = request.getSession();

        //Lấy về userID từ account trong sesion khi đăng nhập
        Users account = (Users) session.getAttribute("account");
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int count = dao.CountOrder(account.getUserId());
        int offset = (page - 1) * PAGE_SIZE;
        int row = PAGE_SIZE;
        int pageCount;
        if (count % PAGE_SIZE == 0) {
            pageCount = count / PAGE_SIZE;
        } else {
            pageCount = count / PAGE_SIZE + 1;
        }
        ArrayList<Orders> AllOrderList = dao.findAllOrder(account.getUserId(), offset, row);
        for (Orders sp : AllOrderList) {
            sp.setTotalString(Validate.doubleToMoney(sp.getTotal()));
            sp.setOrderCreatedAtString(Validate.convertDateFormat(sp.getOrderCreatedAt()));
        }
        request.setAttribute("AllOrderList", AllOrderList);
        request.setAttribute("count", count);
        request.setAttribute("currentPage", page);
        request.setAttribute("pageCount", pageCount);
        request.getRequestDispatcher("/customer/orderHistory.jsp").forward(request, response);
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
