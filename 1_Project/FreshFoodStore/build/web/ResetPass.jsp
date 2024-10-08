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
        <!--<link href="App.css" rel="stylesheet">-->
        <link rel="stylesheet" href="./css/bootstrap.min.css"/>
        <link rel="stylesheet" href="./css/authCss/resetPass.css"/>
        <style>
            @media screen and (max-width: 768px){
                .mobile-view{
                    display: none;
                }
                .breadcrumb-section{
                    padding-top: 130px;
                }
            }
        </style>
    </head>
    <body>
        <%@include file="HeaderLogin.jsp" %>
        <!-- Navbar (Menu) -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light mobile-view" style="padding-top: 130px;">
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

        <!-- Reset Password Form -->
        <section class="reset-password-section py-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-5">
                        <div class="card p-4">
                            <h3 class="text-center mb-3">Reset password</h3>
                            <p class="text-center mb-4">Please enter the OTP code sent to your email</p>
                            <form action="forgot" method="POST">
                                <input type="hidden" name="otpRight" value="${otp}"/>
                                <input type="hidden" name="userID" value="${userID}"/>
                                <!-- OTP Input -->
                                <div class="form-group">
                                    <label for="otp" class="sr-only">Enter OTP</label>
                                    <input type="text" name="OTP" required class="form-control input-form" id="otp" placeholder="Enter OTP">
                                </div>
                                <!-- Resend OTP Link -->
                                <div class="text-right mb-3 mt-3">
                                    <a href="forgot?action=resendOtp&email=${emailUser}" class="text-success">Resend OTP code</a>
                                </div>
                                <!-- New Password Input -->
                                <div class="form-group position-relative">
                                    <label for="newPassword" class="sr-only">New password</label>
                                    <input type="password" name="newPass" required class="form-control input-form" id="new-pass" placeholder="New password">
                                    <span toggle="#new-pass"
                                          class="fa fa-fw fa-eye field-icon password-toggle"></span>
                                </div>
                                <!-- Confirm Password Input -->
                                <div class="form-group position-relative mt-3">
                                    <label for="confirmPassword" class="sr-only">Confirm password</label>
                                    <input type="password" class="form-control input-form" id="confirm-pass" placeholder="Confirm password">
                                    <!--<span class="password-toggle"><i class="fa fa-eye"></i></span>-->
                                    <span toggle="#confirm-pass"
                                                  class="fa fa-fw fa-eye field-icon password-toggle"></span>
                                </div>
                                <!-- Phần hiển thị lỗi -->
                                <div class="text-danger mt-2" id="password-error" style="display: none;">Mật khẩu xác nhận
                                    không
                                    trùng khớp!</div>
                                <div>
                                <!-- Submit Button -->
                                <button type="submit" class="btn btn-success btn-block mt-3 w-100" id="save-btn" style="border-radius: 50px;">Submit</button>
                                <!-- Sign In Link -->
                                <div class="text-center mt-3">
                                    <p>Want to go back? <a href="SignIn.jsp">Sign in</a></p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@ include file="./Footer.jsp" %>
        
        <script>
            window.onload = function () {
                var notifyForgotField = document.getElementById('notifyForgot');
                if (notifyForgotField) {
                    var notifyAuth = notifyForgotField.value;
                    if (notifyAuth === "saiOtp") {
                        alert("Sai mã otp! nhập lại mã!");
                    } else if (notifyAuth === "resendOtp") {
                        alert("Đã gửi lại mã OTP! Hãy kiểm tra email của bạn!");
                    }
                    // Remove the hidden input field after alert
                    notifyForgotField.remove();
                }
            };
        </script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="./js/bootstrap.bundle.min.js"></script>
        <script src="./js/authJs/resetPass.js"></script>
    </body>
</html>
