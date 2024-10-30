<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign In - Ecobazar</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="./css/bootstrap.min.css"/>
        <!--<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">-->
        <link href="./css/customerCss/SignIn.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        
        <style>
            .social-container {
                margin: 5px 0;
            }

            .social-container a {
                border: 1px solid #DDDDDD;
                border-radius: 50%;
                display: inline-flex;
                justify-content: center;
                align-items: center;
                /*margin: 0 5px;*/
                height: 40px;
                width: 40px;
                color: #333;
                font-size: 14px;
                text-decoration: none;
                margin: 0px 5px;
            }
        </style>
    </head>
    <body>
        <%@include file="HeaderLogin.jsp" %>
        <input type="hidden" id="notifyForgot" name="notifyForgot" value="${notifyForgot}">
        
        <!-- Navbar (Menu) -->
<!--        <nav class="navbar navbar-expand-lg navbar-light bg-light" style="padding-top: 110px;">
            <div class="container">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item active">
                            <a class="nav-link" href="customer/Homepage">Home</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Shop
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#">Fruits</a></li>
                                <li><a class="dropdown-item" href="#">Vegetables</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPages" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Pages
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownPages">
                                <li><a class="dropdown-item" href="#">Account</a></li>
                                <li><a class="dropdown-item" href="#">About Us</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Blog</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">About Us</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="./customer/contact.jsp">Contact Us</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>-->
        <!-- Breadcrumb Section -->
        <section class="breadcrumb-section" style="padding-top: 140px;">
            <div class="container">
                <div class="breadcrumb-content">
                    <a href="customer/Homepage"><i class="fa fa-home"></i> Home</a> &nbsp; > &nbsp;
                    <!--<a href="#">Account</a> &nbsp; > &nbsp;-->
                    <span class="active">Login</span>
                </div>
            </div>
        </section>

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

        <!-- Sign In Form -->
        <section class="login-section py-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-5">
                        <div class="card p-4">
                            <h3 class="text-center mb-4">Sign In</h3>
                            <div class="social-container">
                                <!--<a href="#" class="social"><i class="fab fa-facebook-f"></i></a>-->
                                <%-- Sửa lại tên FreshFoodShop ở đây (Đăng kí)   (Đẫ thêm uri HexTech bên gg)   --%>     
                                <!--                                <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/FreshFoodStore/loginGoogle&response_type=code&client_id=618274633562-0q8f8eupsfksnkc8hvrjl73uh9nicmf7.apps.googleusercontent.com&approval_prompt=force" 
                                                                   class="social"><i class="fab fa-google-plus-g"></i></a>-->
                                <%-- End sửa lại tên FreshFoodShop ở đây --%>
                                <!--<a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>-->
                            </div>
                            <form action="auth?action=login" method="POST">
                                <div class="form-group">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" name="email" class="form-control" id="email" placeholder="Nhập email" required>
                                </div>
                                <div class="form-group mt-2">
                                    <label for="password" class="form-label">Mật khẩu</label>
                                    <input type="password" name="password" class="form-control" id="password" placeholder="Nhập mật khẩu" required>
                                </div>
                                <div>
                                    <c:if test="${not empty error}">
                                        <div class="alert alert-danger mt-3">${error}</div>
                                    </c:if>
                                </div>
                                <!--                                <div class="form-check mb-3 mt-2">
                                                                    <input type="checkbox" class="form-check-input" id="rememberMe">
                                                                    <label class="form-check-label" for="rememberMe">Remember me</label>
                                                                </div>-->
                                <button class="btn btn-success btn-block w-100 p-2 mt-2" style="border-radius: 50px;">Đăng nhập</button>
                                <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/FreshFoodStore/loginGoogle&response_type=code&client_id=618274633562-0q8f8eupsfksnkc8hvrjl73uh9nicmf7.apps.googleusercontent.com&approval_prompt=force" class="btn btn-login google mt-3">Login with Google</a>
                                <div class="text-center mt-3">
                                    <a href="forgot?action=forgot">Quên mật khẩu?</a>
                                </div>
                                <div class="text-center mt-3">
                                    <p>Don't have an account? <a href="CreateAccount.jsp">Đăng ký</a></p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="Footer.jsp" %>
        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <!--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>-->
        <script src="./js/bootstrap.bundle.min.js"></script>
        <!--<script src="./js/authJs/myCode.js"></script>-->
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
        <script>
            window.onload = function () {
                var notifyAuthField = document.getElementById('notifyForgot');
                if (notifyAuthField) {
                    var notifyAuth = notifyAuthField.value;
                    if (notifyAuth === "success") {
                        alert("Đổi mật khẩu thành công! Hãy đăng nhập lại!");
                    }
                    // Remove the hidden input field after alert
                    notifyAuthField.remove();
                }
            };
        </script>
        <%
            session.removeAttribute("notifyForgot");
        %>
    </body>
</html>
