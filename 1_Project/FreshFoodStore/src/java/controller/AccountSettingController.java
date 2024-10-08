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
import model.Users;

/**
 *
 * @author DELL
 */
public class AccountSettingController extends HttpServlet {
   
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
            out.println("<title>Servlet AccountSettingController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountSettingController at " + request.getContextPath () + "</h1>");
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
//        processRequest(request, response);
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        //- switch case cac action
        switch (action) {
            //hiển thị data
            case "showData":
                handleShowData(request, response);
                break;
            //edit data
            case "editData":
//                handleShow(request, response);
                break;
            case "changePassword":
                break;
            default:
                throw new AssertionError();
        }
    }
    private void handleShowData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DashboardDAO dao = new DashboardDAO();
        HttpSession session = request.getSession();

        //Lấy về userID từ account trong sesion khi đăng nhập
        Users account = (Users) session.getAttribute("account");
        //lấy thông tin user
        Users listInfo = dao.findAllInfo(account.getUserId());
        request.setAttribute("listInfo", listInfo);
        //hiện thông tin order của user
//        ArrayList<Orders> listOrder = dao.findAllReccentOrder(account.getUserId());
        //chuyển thành dạng tiền việt
//        for (Orders sp : listOrder) {
//            sp.setTotalString(Validate.doubleToMoney(sp.getTotal()));
//            sp.setOrderCreatedAtString(Validate.convertDateFormat(sp.getOrderCreatedAt()));
//        }
//        request.setAttribute("listOrder", listOrder);
        //hiện thông tin billing address đẻ hỏi lại
        
        request.getRequestDispatcher("/customer/AccountSetting.jsp").forward(request, response);
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
