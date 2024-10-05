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
import model.Users;

/**
 *
 * @author DELL
 */
//url pattern: Dashboard
public class DashboardController extends HttpServlet {

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
            out.println("<title>Servlet CustomerDashboard</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerDashboard at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        request.getRequestDispatcher("/customer/CustomerDashboard.jsp").forward(request, response);
        
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
        //- Lấy giá trị action về
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        //- switch case cac action
        switch (action) {
            case "login":
                handleLogin(request, response);
                break;
            case "logout":
                handleLogout(request, response);
                break;
            case "listInfo":
                handleShow(request, response);
                break;
            default:
                throw new AssertionError();
        }

    }

    private void handleShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DashboardDAO dao = new DashboardDAO();
        HttpSession session = request.getSession();

        //Lấy về userID từ account trong sesion khi đăng nhập
        Users account = (Users) session.getAttribute("account");

        ArrayList<Users> listInfo = dao.findAllInfo(account.getUserId());
        request.setAttribute("listInfo", listInfo);
        request.getRequestDispatcher("/customer/CustomerDashboard.jsp").forward(request, response);
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

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetURL = request.getContextPath() + "/auth?action=login";
        String encodedURL = response.encodeRedirectURL(targetURL);
        response.sendRedirect(encodedURL);
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String targetURL = request.getContextPath() + "/auth?action=logout";
        String encodedURL = response.encodeRedirectURL(targetURL);
        response.sendRedirect(encodedURL);
    }

}
