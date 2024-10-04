<%-- 
    Document   : orderHistory
    Created on : Sep 30, 2024, 10:54:38 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Body Customer Dashboard</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <script src="https://kit.fontawesome.com/54f0cb7e4a.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="../css/bootstrap.min.css"/>
        <link rel="stylesheet" href="../css/customerCss/orderHistory.css">
    </head>
    <body>
        <%@include file="HeaderLogin1.jsp" %>
        <div class="mobile-header">
            <div class="d-flex justify-content-between align-items-center">
                <button class="menu-toggle btn btn-outline-secondary">
                    <i class="fas fa-bars"></i>Menu
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

                <!-- Main Content -->
                <div class="col-lg-9 mt-2 mb-3">
                    <!-- Order History -->
                    <div class="card shadow-sm content">
                        <div class="card-body">
                            <h5 class="card-title fw-bold mb-3">Order History</h5>
                            <div class="table-responsive">
                                <table class="table table-borderless table-hover">
                                    <thead class="table-active">
                                        <tr>
                                            <th>Order ID</th>
                                            <th>Date</th>
                                            <th>Total</th>
                                            <th>Status</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>#8333</td>
                                            <td>4 Apr, 2021</td>
                                            <td>$135.00 (5 Products)</td>
                                            <td><span class="badge bg-warning text-dark">Processing</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                        <tr>
                                            <td>#5045</td>
                                            <td>27 Mar, 2021</td>
                                            <td>$25.00 (1 Product)</td>
                                            <td><span class="badge bg-info">On the way</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                        <tr>
                                            <td>#5260</td>
                                            <td>20 Mar, 2021</td>
                                            <td>$250.00 (4 Products)</td>
                                            <td><span class="badge bg-success">Completed</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                        <tr>
                                            <td>#4600</td>
                                            <td>19 Mar, 2021</td>
                                            <td>$35.00 (1 Product)</td>
                                            <td><span class="badge bg-success">Completed</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                        <tr>
                                            <td>#4152</td>
                                            <td>18 Mar, 2021</td>
                                            <td>$578.00 (13 Products)</td>
                                            <td><span class="badge bg-success">Completed</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                        <tr>
                                            <td>#8811</td>
                                            <td>10 Mar, 2021</td>
                                            <td>$345.00 (6 Products)</td>
                                            <td><span class="badge bg-success">Completed</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                        <tr>
                                            <td>#3536</td>
                                            <td>5 Mar, 2021</td>
                                            <td>$560.00 (2 Products)</td>
                                            <td><span class="badge bg-success">Completed</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                        <tr>
                                            <td>#1734</td>
                                            <td>27 Feb, 2021</td>
                                            <td>$560.00 (2 Products)</td>
                                            <td><span class="badge bg-success">Completed</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                        <tr>
                                            <td>#7791</td>
                                            <td>25 Feb, 2021</td>
                                            <td>$560.00 (2 Products)</td>
                                            <td><span class="badge bg-success">Completed</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                        <tr>
                                            <td>#4846</td>
                                            <td>24 Feb, 2021</td>
                                            <td>$230.00 (1 Product)</td>
                                            <td><span class="badge bg-success">Completed</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                        <tr>
                                            <td>#5948</td>
                                            <td>20 Feb, 2021</td>
                                            <td>$230.00 (1 Product)</td>
                                            <td><span class="badge bg-success">Completed</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                        <tr>
                                            <td>#1577</td>
                                            <td>12 Oct, 2020</td>
                                            <td>$230.00 (1 Product)</td>
                                            <td><span class="badge bg-success">Completed</span></td>
                                            <td><a href="#" class="text-success">View Details</a></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                            <!-- Pagination -->
                            <nav aria-label="Page navigation">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#" tabindex="-1" aria-disabled="true">&laquo;</a>
                                    </li>
                                    <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                                    <li class="page-item">
                                        <a class="page-link" href="#">&raquo;</a>
                                    </li>
                                </ul>
                            </nav>
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
