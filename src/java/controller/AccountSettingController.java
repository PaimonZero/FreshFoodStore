/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DashboardDAO;
import dal.UserDAO;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import model.Users;

/**
 *
 * @author DELL
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
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
                handleChangePassword(request, response);
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

        String avatar = handleFile(request, response); // Xử lý file upload

        // Retrieve other form data using request.getParameter()
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phoneNumber");
        String address = request.getParameter("address");

        // Update the user information in the database
        Users newAccInfo = dao.updateUserInfo(account.getUserId(), fullName, email, phone, address, avatar);

        // Check if the update was successful
        if (newAccInfo == null) {
            System.out.println("Error: HandleEdit update failed!");
        }

        // Cập nhật lại giá trị trong session
        session.setAttribute("account", newAccInfo);

        // Redirect to the account settings page after update
        //lấy thông tin user
        Users listInfo = dao.findAllInfo(account.getUserId());
        request.setAttribute("listInfo", listInfo);
        //hiện thông tin order của user        
        request.getRequestDispatcher("/customer/AccountSetting.jsp").forward(request, response);
    }

    private String handleFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Sử dụng getServletContext().getRealPath("") để lấy đường dẫn gốc của dự án
        String uploadPath = getServletContext().getRealPath("") + File.separator + "images";

        // Tạo thư mục lưu file nếu chưa có
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            if (!created) {
                throw new ServletException("Failed to create upload directory.");
            }
        }

        // Lấy phần file từ form với tên là "avatar"
        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if (fileName == null || fileName.isEmpty()) {
            return null;
        }

        // Đường dẫn đầy đủ của file sẽ được lưu
        //String filePath = uploadPath + File.separator + fileName;
        String filePath = uploadPath + File.separator + fileName;

        // Ghi file vào thư mục đích
        try (InputStream fileContent = filePart.getInputStream()) {
            File fileToSave = new File(filePath);
            Files.copy(fileContent, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        System.out.println("Upload Path: " + uploadPath);
        System.out.println("File Path: " + filePath);

        // Trả về tên file đã lưu (là fileName và thêm ../images/ ở phía trc)
        fileName = "../images/" + fileName;
        return fileName;
    }

    private void handleChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO dao = new UserDAO();
        DashboardDAO dao1 = new DashboardDAO();
        HttpSession session = request.getSession();
        //Lấy về userID từ account trong sesion khi đăng nhập
        Users account = (Users) session.getAttribute("account");
        String newPassword = request.getParameter("new-password");
        String oldPassword = request.getParameter("oldPassword");
        Users listInfo = dao1.findAllInfo(account.getUserId());
        if (oldPassword.equals(listInfo.getPassword())) {
            // Nếu mật khẩu cũ đúng, cập nhật mật khẩu mới
            dao.updatePasswordUserDB(account.getUserId(), newPassword);   
            request.setAttribute("listInfo", listInfo);
            request.setAttribute("successMessage", "Mật khẩu đã được cập nhật thành công!");
            request.getRequestDispatcher("/customer/AccountSetting.jsp").forward(request, response);
        } else {
            // Nếu mật khẩu cũ sai, trả về thông báo lỗi
            request.setAttribute("listInfo", listInfo);
            request.setAttribute("errorMessage", "Mật khẩu cũ không chính xác!");
            request.getRequestDispatcher("/customer/AccountSetting.jsp").forward(request, response);
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
