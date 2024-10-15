<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Account</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <script src="https://kit.fontawesome.com/54f0cb7e4a.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="./css/authCss/createAccount.css"/>
        <link rel="stylesheet" href="./css/bootstrap.min.css">
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
        
        <%-- Thông báo đăng kí --%>
        <c:if test="${not empty notifySigup}">
            <input type="hidden" id="notifySigup" value="${notifySigup}" />
            <c:remove var="notifySigup" scope="request" />
        </c:if>
    
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
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"   
                               data-bs-toggle="dropdown" aria-expanded="false">  
                                Shop  
                            </a>  
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">  
                                <li><a class="dropdown-item" href="#">Fruits</a></li>  
                                <li><a class="dropdown-item" href="#">Vegetables</a></li>  
                            </ul>  
                        </li>  

                        <li class="nav-item dropdown">  
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPages" role="button"   
                               data-bs-toggle="dropdown" aria-expanded="false">  
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
        
        <!-- SignIn -->
        <section class="create-account-section py-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-5">
                        <div class="card shadow">
                            <div class="card-body">
                                <h3 class="text-center mb-4">Create Account</h3>
                                <form action="auth?action=signup" method="POST">
                                    <div class="mb-3">
                                        <input required type="text" name="fullname" class="form-control input-form" placeholder="Full name">
                                    </div>
                                    <div class="mb-3">
                                        <input required type="email" name="email" class="form-control input-form" placeholder="Email">
                                    </div>
                                    <div class="mb-3">
                                        <input required type="tel" name="phoneNumber" pattern="[0-9]{10}" title="Vui lòng nhập đúng số điện thoại" class="form-control input-form" placeholder="Phone Number">
                                    </div>
                                    <div class="mb-3 position-relative">
                                        <input required type="password" name="password" class="form-control input-form" id="new-pass" placeholder="Password">
                                        <span toggle="#new-pass"
                                              class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                    </div>
                                    <div class="mb-3 position-relative">
                                        <input required type="password" class="form-control input-form" id="confirm-pass" placeholder="Confirm Password">
                                        <span toggle="#confirm-pass"
                                              class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                    </div>
                                    <div class="text-danger mt-2" id="password-error" style="display: none;">Mật khẩu xác nhận không trùng khớp!</div>
                                    <div class="mb-3 form-check">
                                        <input type="checkbox" class="form-check-input" id="termsCheck">
                                        <label class="form-check-label" for="termsCheck">Accept all terms & Conditions</label>
                                    </div>
                                    <div class="d-grid">
                                        <button type="submit" class="btn btn-success p-2" style="border-radius: 50px; background-color: #1DBD3A; border: none;" id="save-btn">Create Account</button>
                                    </div>
                                </form>
                                <p class="text-center mt-3 link">Already have an account? <a href="SignIn.jsp">Login</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="Footer.jsp" %>
        <script>           
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
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> 
        <script src="./js/bootstrap.bundle.min.js"></script>
        <script src="./js/authJs/createAccount.js"></script>
    </body>
</html>
