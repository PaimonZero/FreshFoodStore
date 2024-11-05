<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign In - Ecobazar</title>
        <!-- Bootstrap CSS -->
        <!--<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">-->
        <link rel="stylesheet" href="./css/bootstrap.min.css"/>
        <link href="./css/customerCss/forgot.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="HeaderLogin.jsp" %>
        <nav class="navbar navbar-expand-lg navbar-light bg-light" style="padding-top: 130px;">
            <div class="container">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item active">
                            <a class="nav-link" href="#">Home</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Shop
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="#">Fruits</a>
                                <a class="dropdown-item" href="#">Vegetables</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPages" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Pages
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownPages">
                                <a class="dropdown-item" href="#">Account</a>
                                <a class="dropdown-item" href="#">About Us</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Blog</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">About Us</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Contact Us</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Breadcrumb Section -->
        <section class="breadcrumb-section">
            <div class="container">
                <div class="breadcrumb-content">
                    <a href="#"><i class="fa fa-home"></i> Home</a> &nbsp; > &nbsp;
                    <a href="#">Account</a> &nbsp; > &nbsp;
                    <span class="active">Login</span>
                </div>
            </div>
        </section>

        <%-- Thông báo lỗi --%>
        <c:if test="${not empty notifyForgot}">
            <input type="hidden" id="notifyForgot" value="${notifyForgot}" />
            <c:remove var="notifyForgot" scope="request" />
        </c:if>

        <!-- Forgot Password Form -->
        <section class="forgot-password-section py-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-5">
                        <div class="card p-4">
                            <h3 class="text-center mb-3">Forgot password</h3>
                            <p class="text-center mb-4">Vui lòng nhập email để xác nhận!</p>
                            <form action="forgot" method="GET">    <%-- Do action ko nhận nên để trong thẻ input ở dưới --%>
                                <input type="hidden" name="action" value="changePass"/>
                                <div class="form-group">
                                    <label for="email" class="sr-only">Email</label>
                                    <input required type="email" name="email" class="form-control input-form" id="email" placeholder="Enter email">
                                </div>
                                <button class="btn btn-success btn-block w-100 mt-3 p-2" style="border-radius: 50px;">Gủi mã xác nhận</button>
                                <div class="text-center mt-3">
                                    <p class="text-center mt-3 link">Muốn quay lại? <a href="SignIn.jsp">Đăng nhập</a></p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="Footer.jsp" %>
        <script src="./js/bootstrap.bundle.min.js"></script>
        <script>
            window.onload = function () {
                var notifyForgotField = document.getElementById('notifyForgot');
                if (notifyForgotField) {
                    var notifyAuth = notifyForgotField.value;
                    if (notifyAuth === "NotFound") {
                        alert("Không tìm thấy tài khoản trên!");
                    }
                    // Remove the hidden input field after alert
                    notifyForgotField.remove();
                }
            };
        </script>
    </body>
</html>
