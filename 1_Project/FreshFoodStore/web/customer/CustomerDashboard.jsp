<%-- 
    Document   : customerDashboard
    Created on : Sep 30, 2024, 9:02:20 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Body Customer Dashboard</title>
        <link rel="stylesheet" href="../css/bootstrap.min.css"/>
        <link rel="stylesheet" href="../css/customerCss/customerDashboard.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <script src="https://kit.fontawesome.com/54f0cb7e4a.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    </head>
    <body>
        <%@include file="HeaderLogin1.jsp" %>
        <div class="mobile-header">
            <div class="d-flex justify-content-between align-items-center">
                <button class="menu-toggle btn btn-outline-secondary">
                    <i class="fas fa-bars"></i> Menu
                </button>
            </div>
        </div>

        <div class="container mobile-content">
            <div class="row mt-4">
                <div class="col-lg-3 mt-2">
                    <div class="sidebar d-flex flex-column flex-shrink-0 bg-body-tertiary" id="sidebar">
                        <span class="fs-4 p-3" style="font-weight: 500;">Menu</span>
                        <ul class="nav nav-pills flex-column mb-auto">
                            <li class="nav-item">
                                <a href="#" class="nav-link active" aria-current="page">
                                    <i class="fas fa-th-large me-2"></i>
                                    Bảng điều khiển
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-link">
                                    <i class="fas fa-sync-alt me-2"></i>
                                    Lịch sử đơn hàng
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-link">
                                    <i class="far fa-heart me-2"></i>
                                    Danh sách yêu thích
                                </a>
                            </li>
                            <li>
                                <a href="ShoppingCart.jsp" class="nav-link"><!--để tạm-->
                                    <i class="bi bi-bag me-2"></i>
                                    Giỏ hàng
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-link">
                                    <i class="bi bi-gear me-2"></i>
                                    Cài đặt
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-link">
                                    <i class="fas fa-sign-out-alt me-2"></i>
                                    Đăng xuất
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-9 mt-2 mb-3">
                    <div class="row">
                        <!-- Profile Info -->
                        <div class="col-md-6 mb-3">
                            <div class="card shadow-sm edit-btn">
                                <div class="card-body text-center">
                                    <img src="../images/z5881231576618_adb311bd5db037f90e283bec9afa8e0f.jpg" alt="Profile Image" class="profile-picture">
                                    <h5 class="card-title">Dianne Russell</h5>
                                    <p class="text-muted">Customer</p>
                                    <a href="#" class="btn btn-outline-success btn-sm">Edit Profile</a>
                                </div>
                            </div>
                        </div>

                        <!-- Billing Info -->
                        <div class="col-md-6 mb-3">
                            <div class="card shadow-sm edit-btn">
                                <div class="card-body">
                                    <h5 class="card-title text-muted">Billing Address</h5>
                                    <p class="h4 fw-bold">Dianne Russell</p>
                                    <p>4140 Parker Rd. Allentown, New Mexico 31134</p>
                                    <p>diannerussell@gmail.com</p>
                                    <p>(671) 555-0110</p>
                                    <a href="#" class="btn btn-outline-success btn-sm">Edit Address</a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Order History -->
                    <div class="card shadow-sm">
                        <div class="card-body content">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h5 class="card-title">Recent Order History</h5>
                                <a href="#" class="text-success">View All</a>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-borderless table-hover">
                                    <thead class="table-active">
                                        <tr>
                                            <th>Order ID</th>
                                            <th>Date</th>
                                            <th>Total</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>#738</td>
                                            <td>8 Sep, 2020</td>
                                            <td>$135.00 (5 Products)</td>
                                            <td><span class="badge bg-warning text-dark">Processing</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                        <tr>
                                            <td>#703</td>
                                            <td>24 May, 2020</td>
                                            <td>$25.00 (1 Product)</td>
                                            <td><span class="badge bg-info">On the way</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                        <tr>
                                            <td>#130</td>
                                            <td>22 Oct, 2020</td>
                                            <td>$250.00 (4 Products)</td>
                                            <td><span class="badge bg-success">Completed</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                        <tr>
                                            <td>#561</td>
                                            <td>1 Feb, 2020</td>
                                            <td>$35.00 (1 Product)</td>
                                            <td><span class="badge bg-success">Completed</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                        <tr>
                                            <td>#538</td>
                                            <td>21 Sep, 2020</td>
                                            <td>$578.00 (13 Products)</td>
                                            <td><span class="badge bg-success">Completed</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                        <tr>
                                            <td>#492</td>
                                            <td>22 Oct, 2020</td>
                                            <td>$345.00 (7 Products)</td>
                                            <td><span class="badge bg-success">Completed</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
        <script src="../js/bootstrap.bundle.min.js"></script>
        <script>
            //hiện thanh sidebar mobile
            document.querySelector('.menu-toggle').addEventListener('click', function () {
                document.getElementById('sidebar').classList.toggle('show');
            });
        </script>
    </body>
</html>
