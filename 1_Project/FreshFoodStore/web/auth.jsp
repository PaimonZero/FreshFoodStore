<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vn">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login page</title>
        <link rel="stylesheet" href="./css/authCss/cssAuth.css">
        <link rel="shortcut icon" href="./img_svg/mainPage/logo-color.png">
        <script src="https://kit.fontawesome.com/54f0cb7e4a.js" crossorigin="anonymous"></script>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Pacifico&display=swap');
        </style>
        <script src="https://kit.fontawesome.com/3a767ca8aa.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <%-- Thông báo đăng nhập --%>
        <c:if test="${not empty notifyAuth}">
            <input type="hidden" id="notifyAuth" value="${notifyAuth}" />
            <c:remove var="notifyAuth" scope="request" />
        </c:if>
        
        <%-- Thông báo đăng kí --%>
        <c:if test="${not empty notifySigup}">
            <input type="hidden" id="notifySigup" value="${notifySigup}" />
            <c:remove var="notifySigup" scope="request" />
        </c:if>

        <h2 class="pacifico-regular">HexTech - Công nghệ thay đổi cuộc sống</h2>
        <a class="back-btn" href="userMainPage.jsp"><i class="fas fa-backward"></i> Trang chủ</a>
        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <form action="auth?action=signup" method="POST">
                    <h1>Đăng ký</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <%-- Sửa lại tên FreshFoodShop ở đây (Đăng kí)   (Đẫ thêm uri HexTech bên gg)   --%>     
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/FreshFoodStore/loginGoogle&response_type=code&client_id=618274633562-0q8f8eupsfksnkc8hvrjl73uh9nicmf7.apps.googleusercontent.com&approval_prompt=force" 
                           class="social"><i class="fab fa-google-plus-g"></i></a>
                        <%-- End sửa lại tên FreshFoodShop ở đây --%>
                        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>hoặc sử dụng email của bạn để đăng ký</span>
                    <input type="text" name="fullname" placeholder="Họ và tên" required />
                    <input type="email" name="email" placeholder="Email" required />
                    <input type="password" name="password" placeholder="Mật khẩu" required/>
                    <input type="tel" name="phoneNumber" placeholder="Số điện thoại" pattern="[0-9]{10}" title="Số điện thoại phải có 10 chữ số" required/>
                    <button style="cursor: pointer;">Đăng ký</button>
                </form>
            </div>
            <div class="form-container sign-in-container">
                <form action="auth?action=login" method="POST">
                    <h1>Đăng nhập</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <%-- Sửa lại tên FreshFoodShop ở đây (Đăng nhập) --%>
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/FreshFoodStore/loginGoogle&response_type=code&client_id=618274633562-0q8f8eupsfksnkc8hvrjl73uh9nicmf7.apps.googleusercontent.com&approval_prompt=force" 
                           class="social"><i class="fab fa-google-plus-g"></i></a>
                        <%-- End sửa lại tên FreshFoodShop ở đây --%>
                        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>Sử dụng tài khoản của bạn</span>
                    <input type="text" placeholder="Email" name="email" />
                    <input type="password" placeholder="Mật khẩu" name="password" />
                    <a href="forgot?action=forgot">Quên mật khẩu?</a>
                    <button type="submit" name="action" value="login" style="cursor: pointer;">Đăng nhập</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p> Để nhận được những ưu đãi đặc biệt, vui lòng đăng nhập tài khoản của bạn.</p>
                        <button class="ghost" id="signIn">Đăng nhập</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <p>Đăng ký tài khoản của bạn để bắt đầu hành trình cùng chúng tôi và tận hưởng những ưu đãi đặc biệt.</p>
                        <button class="ghost" id="signUp">Đăng ký</button>
                    </div>
                </div>
            </div>

        </div>

        <div class="login-mobile">
            <div class="btn">
                <form class="register-form" action="auth?action=signup" method="POST">
                    <h1>Tạo tài khoản</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <%-- Sửa lại tên HexTech77 ở đây (Đăng kí) --%>
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/HexTech77/loginGoogle&response_type=code&client_id=217200688737-k99p0tk3hkv77vui4cf1jkkqdheorca2.apps.googleusercontent.com&approval_prompt=force" 
                           class="social"><i class="fab fa-google-plus-g"></i></a>
                        <%-- End sửa lại tên HexTech77 ở đây --%>
                        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>sử dụng email để đăng nhập</span>
                    <input type="text" name="fullname" placeholder="Họ và tên" required />
                    <input type="email" name="email" placeholder="Email" required />
                    <input type="password" name="password" placeholder="Mật khẩu" required/>
                    <input type="tel" name="phoneNumber" placeholder="Số điện thoại" pattern="[0-9]{10}" title="Số điện thoại phải có 10 chữ số" required/>
                    <button>Đăng ký</button>
                    <p class="toggle-container"><a href="#" id="signInLink">Đã có tài khoản? Đăng nhập ngay</a></p>
                </form>
            </div>

            <div>
                <form class="login-form" action="auth?action=login" method="POST">
                    <h1>Đăng nhập</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <%-- Sửa lại tên HexTech77 ở đây (Đăng nhập) --%>
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/HexTech77/loginGoogle&response_type=code&client_id=217200688737-k99p0tk3hkv77vui4cf1jkkqdheorca2.apps.googleusercontent.com&approval_prompt=force" 
                           class="social"><i class="fab fa-google-plus-g"></i></a>
                        <%-- End sửa lại tên HexTech77 ở đây --%>
                        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>hoặc sử dụng tài khoản của bạn</span>
                    <input type="text" placeholder="Email" name="email" />
                    <input type="password" placeholder="Mật khẩu" name="password" />
                    <div class="quenMK">
                        <a href="forgot?action=forgot">Quên mật khẩu?</a>
                    </div>
                    <button>Đăng nhập</button>
                    <p class="toggle-container"><a href="#" id="signUpLink">Chưa có tài khoản? Đăng ký ngay</a></p>
                </form>
            </div>
        </div>


        <footer>
            <p>
                Created with <i class="fa fa-heart"></i> by
                Hoang Nam & Trung Kien & Khanh Nhan & Ngoc Trung & Huu Quang
            </p>
        </footer>

        <script>
            window.onload = function () {
                var notifyAuthField = document.getElementById('notifyAuth');
                if (notifyAuthField) {
                    var notifyAuth = notifyAuthField.value;
                    if (notifyAuth === "success") {
                        alert("Đăng nhập thành công!");
                    } else if (notifyAuth === "failed") {
                        alert("Đăng nhập thất bại!");
                    } else if (notifyAuth === "blocked") {
                        alert("Tài khoản của bạn đã bị khóa. Hãy liên hệ bộ phận chăm sóc khách hàng (0582647644) để biết thêm thông tin!");
                    }
                    // Remove the hidden input field after alert
                    notifyAuthField.remove();
                }
            };
            
            window.onload = function () {
                var notifyAuthField = document.getElementById('notifySigup');
                if (notifyAuthField) {
                    var notifyAuth = notifyAuthField.value;
                    if (notifyAuth === "success") {
                        alert("Đăng kí thành công!");
                    } else if (notifyAuth === "failed") {
                        alert("Đăng kí thất bại! (Email này đã được đăng kí!)");
                    }
                    // Remove the hidden input field after alert
                    notifyAuthField.remove();
                }
            };
        </script>

        <script src="./js/authJs/myCodeAuth.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </body>

</html>