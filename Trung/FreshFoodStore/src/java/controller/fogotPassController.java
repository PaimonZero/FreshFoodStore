package controller;

import dal.UserDAO;
import feature.sendEmail.Email;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import model.Users;

@WebServlet(name = "fogotPassController", urlPatterns = {"/forgot"})
public class fogotPassController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet fogotPassController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet fogotPassController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //- Lấy giá trị action về
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        //- switch case cac action
        switch (action) {
            case "forgot":
                handleForgot(request, response);
                break;
            case "resendOtp":
                request.setAttribute("notifyForgot", "resendOtp");
            case "changePass":
                handleEmail(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int otp = Integer.parseInt(request.getParameter("otpRight"));
        int otpInput = Integer.parseInt(request.getParameter("OTP"));
        int userID = Integer.parseInt(request.getParameter("userID"));

        String newPass = request.getParameter("newPass");

        UserDAO dao = new UserDAO();

        if (otp == otpInput) {   //nếu user nhập đúng otp
            dao.updatePasswordUserDB(userID, newPass);
            //Chuyển hướng qua trang đăng nhập
            response.sendRedirect("SignIn.jsp");
        } else {                //nếu sai otp
            //Thông báo cho khách hàng và chuyển lại trang quên mk
            request.setAttribute("otp", otp);
            request.setAttribute("userID", userID);

            request.setAttribute("notifyForgot", "saiOtp");
            request.getRequestDispatcher("ResetPass.jsp").forward(request, response);
        }
    }

    private void handleForgot(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String targetURL = "Forgot.jsp";      //đổi dường dẫn ở đây
        String encodedURL = response.encodeRedirectURL(targetURL);
        response.sendRedirect(encodedURL);
    }

    private void handleEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        
        UserDAO dao = new UserDAO();

        Users acc = dao.findByEmail(email);
        if (acc == null) {          //Tài khoản không tồn tại
            //Thông báo cho khách hàng và chuyển lại trang quên mk
            request.setAttribute("notifyForgot", "NotFound");
            request.getRequestDispatcher("Forgot.jsp").forward(request, response);
        } else {
            //tạo otp random có 8 chữ số
            Random random = new Random();
            // Số nguyên 8 chữ số sẽ nằm trong khoảng từ 10000000 đến 99999999
            int min = 10000000;
            int max = 99999999;
            // Tạo số ngẫu nhiên trong khoảng [min, max]
            int otp = random.nextInt((max - min) + 1) + min;

            String tieuDe = "Forgot password notification from your HexTech account";
            String noiDung = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    <title>Document</title>\n"
                    + "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n"
                    + "        integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n"
                    + "    <script src=\"https://kit.fontawesome.com/54f0cb7e4a.js\" crossorigin=\"anonymous\"></script>\n"
                    + "\n"
                    + "    <style>\n"
                    + "        .warning {\n"
                    + "            border: 2px solid red;\n"
                    + "            padding: 10px;\n"
                    + "            border-radius: 10px;\n"
                    + "            background-color: #FFDBDE;\n"
                    + "        }\n"
                    + "\n"
                    + "        .body {\n"
                    + "            padding-left: 30%;\n"
                    + "            padding-right: 30%;\n"
                    + "        }\n"
                    + "\n"
                    + "        @media only screen and (max-width: 768px) {\n"
                    + "            .body {\n"
                    + "                padding-left: 3%;\n"
                    + "                padding-right: 3%;\n"
                    + "            }\n"
                    + "        }"
                    + "    </style>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "    <div class=\"container-fluid body\">\n"
                    + "        <h4 style=\"font-weight: bold; margin-top: 20px; margin-bottom: 20px;\">Kính gửi quý khách," + acc.getFullName() + ".</h4>\n"
                    + "        <div class=\"warning\">\n"
                    + "            <p style=\"color: red; font-weight: bold;\"><i class=\"fas fa-exclamation-triangle\"></i> [Cảnh báo] Yêu cầu cấp\n"
                    + "                lại mật khẩu Tài khoản dành cho Cá nhân của Quý khách đang\n"
                    + "                được thực hiện.</p>\n"
                    + "            <p>Vui lòng nhập mã OTP sau để xác thực:</p>\n"
                    + "            <p><strong\n"
                    + "                    style=\"box-shadow: 0px 0px 5px black; padding: 10px; border-radius: 10px; font-size: 30px;\">" + otp + "</strong>\n"
                    + "                là mã\n"
                    + "                OTP của bạn</p>\n"
                    + "        </div>\n"
                    + "        <br>\n"
                    + "        <p><strong>Lưu ý: </strong>Quý khách vui lòng không chia sẻ mã này với bất kỳ ai vì lí do bảo mật</p>\n"
                    + "        <br>\n"
                    + "        <p>Nếu không phải Quý khách thực hiện, hãy liên hệ ngay tới Tổng đài <strong>1900 6036</strong> hoặc\n"
                    + "            <strong>1900 585 885</strong> để được hỗ trợ, tránh rủi ro bị kẻ xấu lợi dụng.\n"
                    + "            Nếu đúng Quý khách thực hiện yêu cầu trên, vui lòng bỏ qua email này.\n"
                    + "        </p>\n"
                    + "        <div class=\"row\">\n"
                    + "            <p class=\"col-9\">Trân trọng, <br>HEXTECH SHOP</p>\n"
                    + "            <img class=\"col-3\" style=\"width: 22%;\" src=\"./images/logo-color.png\" alt=\"\">\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "</body>\n"
                    + "\n"
                    + "</html>";
            Email.sendEmail(acc.getEmail().trim(), tieuDe, noiDung);

            acc.setOtp(otp);
            request.setAttribute("acc", acc);
            request.setAttribute("otp", otp);
            request.setAttribute("userID", acc.getUserId());
            request.setAttribute("emailUser", email);           //Gửi lại email để còn resend OTP

            //chuyển hướng trang
            request.getRequestDispatcher("ResetPass.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
