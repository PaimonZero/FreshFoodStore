/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DashboardDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import model.Users;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;

/**
 *
 * @author DELL
 */
@MultipartConfig
public class AccountSettingController extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "images";

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
            out.println("<h1>Servlet AccountSettingController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
    }

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
                handleEdit(request, response);
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
        request.getRequestDispatcher("/customer/AccountSetting.jsp").forward(request, response);
    }

    // Method to handle editing user info
    private void handleEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DashboardDAO dao = new DashboardDAO();
        HttpSession session = request.getSession();

        // Get the user information from the session
        Users account = (Users) session.getAttribute("account");

        // Retrieve other form data using request.getParameter()
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phoneNumber");
        String address = request.getParameter("address");

        // Update the user information in the database
        int userId = dao.updateUserInfo(account.getUserId(), fullName, email, phone, address);

        // Check if the update was successful
        if (userId == -1) {
            System.out.println("Error: HandleEdit update failed!");
        }

        // Redirect to the account settings page after update
        request.getRequestDispatcher("/customer/AccountSetting.jsp").forward(request, response);
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
