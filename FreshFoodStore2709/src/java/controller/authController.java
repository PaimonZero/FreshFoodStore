
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Users;

@WebServlet(name = "authController", urlPatterns = {"/authController"})
public class authController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet authController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet authController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                request.getRequestDispatcher("auth.jsp").forward(request, response);
    }

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
            case "signup":
                handleSignup(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Set session timeout to 30 minutes
        session.setMaxInactiveInterval(30 * 60);

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        //Tìm trong db xem thử có user nào có email và password giống ko
        Users account = dao.findByEmailPassword(email, password);
        //nếu account = null -> tai khoan mat khau ko dung -> set về lỗi -> trở lại login.jsp

        if (account == null) {
            //dang nhap that bai -> productControler để lấy dữ liệu hiển thị
            //khi này account = null; ko cho sài tính năng (limited access)
            session.setAttribute("account", account);
            request.setAttribute("notifyAuth", "failed");

            request.getRequestDispatcher("auth.jsp").forward(request, response);
            //Chuyển hướng trang
//            String targetURL = "auth";
//            String encodedURL = response.encodeRedirectURL(targetURL);
//            response.sendRedirect(encodedURL);
        } else {
            //dang nhap thanh cong -> productControler để lấy dữ liệu hiển thị
            //khi này account != null; cho sài tính năng đă (access all)
//            session.setAttribute("account", account);
//            request.setAttribute("notifyAuth", "success");

            // Add username to cookie
            Cookie userName = new Cookie("emailCookie", email);
            userName.setMaxAge(30 * 60);
            response.addCookie(userName);

            String targetURL;
            String encodedURL;
            switch (account.getRoleID()) {
                case 1:
                    //Chuyển hướng trang qua admin 
                    session.setAttribute("account", account);
                    request.setAttribute("notifyAuth", "success");
                    
                    //targetURL = request.getContextPath() + "/Admin/index";
                    targetURL = "homePage"; //đổi dường dẫn ở đây
                    encodedURL = response.encodeRedirectURL(targetURL);
                    response.sendRedirect(encodedURL);
                    break;
                case 2:
                    session.setAttribute("account", account);
                    request.setAttribute("notifyAuth", "success");
                    
                    //Chuyển hướng trang qua user
                    targetURL = "homePage";      //đổi dường dẫn ở đây
                    encodedURL = response.encodeRedirectURL(targetURL);
                    response.sendRedirect(encodedURL);
                    break;
                case 3:                                 //Tài khoản bị block
                    //Thông báo cho khách hàng và chuyển lại trang đăng nhập
                    request.setAttribute("notifyAuth", "blocked");
                    request.getRequestDispatcher("auth.jsp").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie loginCookie = null;
        //Lấy cookies cho account muốn logout
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("emailCookie")) {
                    loginCookie = cookie;
                    break;
                }
            }
        }

        //Nếu tồn tại cookie đó thì destroy nó
        if (loginCookie != null) {
            loginCookie.setMaxAge(0);
            response.addCookie(loginCookie);
        }

        //Lấy session hiện tại, (false) có ý nghĩa là nếu lấy session ra null thì ko tạo lại sesion
        HttpSession session = request.getSession(false);
        //vô hiệu hóa session hiện tại
        if (session != null) {
            session.invalidate();
        }

        String encodedURL = response.encodeRedirectURL("auth.jsp");
        response.sendRedirect(encodedURL);
    }

    private void handleSignup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Set session timeout to 30 minutes
        session.setMaxInactiveInterval(30 * 60);

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String phoneNumber = request.getParameter("phoneNumber");

        UserDAO dao = new UserDAO();
        //Tìm trong db xem thử email đã tồn tại chưa
        Users account = dao.findByEmail(email);
        if (account == null) {
            //insert new user
            Users user = new Users(userID, fullname_found, email_found, phone_found);
            user.setEmail(email);
            user.setPassword(password);
            user.setFullName(fullname);
            user.setPhone(phoneNumber);
            user.setRoleID(2);          //là customer
            user.setAddress(fullname + "location");

            dao.insertUserDB(user);
            //Thông báo đăng kí thành công
            request.setAttribute("notifySigup", "success");
            //trở về để dăng nhập
            request.getRequestDispatcher("auth.jsp").forward(request, response);
        } else {
            //Thông báo người đã tồn tại email
            request.setAttribute("notifySigup", "failed");
            request.getRequestDispatcher("auth.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
