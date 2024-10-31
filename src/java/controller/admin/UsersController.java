package controller.admin;

import dal.UserDAO;
import dto.UsersDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Users;

@WebServlet(name = "UsersController", urlPatterns = {"/admin/users"})
public class UsersController extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //===================Hàm phân quyền=====================================================
        HttpSession session = request.getSession();

        //Lấy về userID từ account trong sesion khi đăng nhập
        Users account = (Users) session.getAttribute("account");

        if (account != null) {
            String role = account.getRole();
            if (!role.equals("manager") && !role.equals("staff") && !role.equals("shipper")) {
                session.setAttribute("notifyAuth", "notAuthorized");

                //Chuyển hướng trang qua chủ customer
                String targetURL = request.getContextPath() + "/customer/Homepage";      //đổi dường dẫn ở đây
                String encodedURL = response.encodeRedirectURL(targetURL);
                response.sendRedirect(encodedURL);
                return;
            } else if (role.equals("shipper")) {        //nếu là shipper thì ko cho coi trang này
                session.setAttribute("notifyAuth", "notAuthorized");

                String targetURL = request.getContextPath() + "/admin/Delivery.jsp";      //đổi dường dẫn ở đây
                String encodedURL = response.encodeRedirectURL(targetURL);
                response.sendRedirect(encodedURL);
                return;
            }
        } else {
            // If no account is found in the session, redirect to login or another appropriate page
            String loginURL = request.getContextPath() + "/SignIn.jsp";
            String encodedURL = response.encodeRedirectURL(loginURL);
            response.sendRedirect(encodedURL);
            return;
        }
        //===================End Hàm phân quyền=================================================

        // Handle pagination for customers list
        String pageStr = request.getParameter("page");
        int page = 1;
        int pageSize = 10;

        if (pageStr != null) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        List<UsersDTO> users = userDAO.getAllUsers(page, pageSize);
        int totalCustomers = userDAO.getTotalUsers();
        int totalPages = (int) Math.ceil(totalCustomers / (double) pageSize);

        // Set customers and pagination details as request attributes
        request.setAttribute("users", users);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Users.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //===================Hàm phân quyền=====================================================
        HttpSession session = request.getSession();

        //Lấy về userID từ account trong sesion khi đăng nhập
        Users account = (Users) session.getAttribute("account");

        if (account != null) {
            String role = account.getRole();
            if (!role.equals("manager") && !role.equals("staff") && !role.equals("shipper")) {
                session.setAttribute("notifyAuth", "notAuthorized");

                //Chuyển hướng trang qua chủ customer
                String targetURL = request.getContextPath() + "/customer/Homepage";      //đổi dường dẫn ở đây
                String encodedURL = response.encodeRedirectURL(targetURL);
                response.sendRedirect(encodedURL);
                return;
            } else if (role.equals("shipper")) {        //nếu là shipper thì ko cho coi trang này
                session.setAttribute("notifyAuth", "notAuthorized");

                String targetURL = request.getContextPath() + "/admin/Delivery.jsp";      //đổi dường dẫn ở đây
                String encodedURL = response.encodeRedirectURL(targetURL);
                response.sendRedirect(encodedURL);
                return;
            }
        } else {
            // If no account is found in the session, redirect to login or another appropriate page
            String loginURL = request.getContextPath() + "/SignIn.jsp";
            String encodedURL = response.encodeRedirectURL(loginURL);
            response.sendRedirect(encodedURL);
            return;
        }
        //===================End Hàm phân quyền=================================================

        //- Lấy giá trị action về
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        //- switch case cac action
        switch (action) {
            case "edit":
                handleEditUser(request, response);
                break;
            case "search":
                handleSearchUser(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void handleEditUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String fullName = request.getParameter("userFullName");
        String email = request.getParameter("userEmail");
        String address = request.getParameter("userAddress");
        String phone = request.getParameter("userPhone");
        String roleName = request.getParameter("userRoleName");
        String status = request.getParameter("userStatus");

        try {
            UsersDTO userUp = new UsersDTO();
            userUp.setUserId(userId);
            userUp.setFullName(fullName);
            userUp.setEmail(email);
            userUp.setAddress(address);
            userUp.setPhone(phone);
            userUp.setRoleName(roleName);
            userUp.setStatus(status);

            boolean updated = userDAO.updateUserWithRole(userUp);

            if (updated) {
                response.sendRedirect(request.getContextPath() + "/admin/users");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/users?error=Update failed");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin/users?error=Invalid input format");
        }
    }

    private void handleSearchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle pagination for customers list
        String pageStr = request.getParameter("page");
        int page = 1;

        //lấy dữ liệu tìm kiếm
        String query = request.getParameter("searchQuery");

        List<UsersDTO> users = userDAO.searchUsers(query);
        int totalPages = 1;

        // Set customers and pagination details as request attributes
        request.setAttribute("users", users);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Users.jsp");
        dispatcher.forward(request, response);
    }
}
