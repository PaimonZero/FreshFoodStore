package feature.loginGoogle;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Users;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author HoangNam
 */
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/loginGoogle"})
public class LoginGoogleController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("error") == null) {
            //Sau khi user đăng nhập bằng gg, gg gửi lại cho ta refresh token với tên là code
            String code = request.getParameter("code");
            //Gửi lại refresh token để lấy accessToken
            String accessToken = getToken(code);
            //Gửi lại accessToken để lấy dữ liệu
            GoogleAcc user = getUserInfo(accessToken);
            //In ra terminal
            System.out.println(user);

            //Xử lý đăng nhập
            handleLoginGoogle(user, request, response);
        } else {
            String encodedURL = response.encodeRedirectURL("auth.jsp");
            response.sendRedirect(encodedURL);
        }
    }

    public void handleLoginGoogle(GoogleAcc acc, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        // Set session timeout to 30 minutes
        session.setMaxInactiveInterval(30 * 60);

        //Tìm kiếm xem thử email đó đã tồn tại tài khoản chưa
        UserDAO dao = new UserDAO();
        Users checkUser = dao.findByEmail(acc.getEmail());

        if (checkUser == null) {     //Nếu chưa có tk thì tạo mới tk với mk là id mà Google đưa cho mình //lưu mk trong password và passGoogle
            //insert new user
            checkUser = new Users();        //tạo lại checkUser, tránh lỗi null khi set giá trị vào vật thể null
            checkUser.setEmail(acc.getEmail());
            checkUser.setPassword(acc.getId());         //pass tùy biến từ người dùng, trong th này thì user khởi tạo acc bằng gg nên pass = id gg
            checkUser.setPassGoogle(acc.getId());     //passGoogle lấy từ id của GG
            checkUser.setFullName(acc.getName());
            checkUser.setRole("customer");                     //là customer
            checkUser.setPhone("1234567890");           //set mặt định sđt là 1234567890
            checkUser.setAddress(acc.getName() + " location"); //set mặt định sđt là name + location

            int id = dao.insertUserDB(checkUser);
            checkUser.setUserId(id);            //set thêm userID để gửi cho session
            //Xong cho đăng nhập thôi
        }
        //Đăng nhập cho cả khi đã có tk hay mới tạo tk ở trên
        session.setAttribute("account", checkUser);

        String targetURL;
        String encodedURL;
        switch (checkUser.getRole()) {
            case "staff":
                case "manager":
                case "shipper":
                //Chuyển hướng trang qua admin (chưa làm)
                targetURL = "index2.html";      //đổi dường dẫn ở đây //request.getContextPath() + "/Admin/index.jsp";
                encodedURL = response.encodeRedirectURL(targetURL);
                break;
            case "customer":
                //Chuyển hướng trang qua user
                targetURL = "index.html";      //đổi dường dẫn ở đây
                encodedURL = response.encodeRedirectURL(targetURL);
                break;
            default:
                throw new AssertionError();
        }
        response.sendRedirect(encodedURL);
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static GoogleAcc getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        GoogleAcc googlePojo = new Gson().fromJson(response, GoogleAcc.class);

        return googlePojo;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
