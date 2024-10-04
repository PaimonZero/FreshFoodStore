<%-- Document : orderDetail Created on : Sep 30, 2024, 11:22:44 AM Author : DELL --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Body Customer Dashboard</title>
        <link rel="stylesheet" href="../css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <script src="https://kit.fontawesome.com/54f0cb7e4a.js" crossorigin="anonymous"></script>
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="../css/customerCss/orderDetail.css">
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
                    <!-- Order Details -->
                    <div class="card shadow-sm">
                        <div class="card-header">
                            <div class="d-flex justify-content-between align-items-center mb-2 mt-2 content">
                                <h5 class="card-title text-center fw-bold">Order Details &#8901; <small
                                        class="text-muted fw-light">April 24, 2023 &#8901;3 product</small></h5>
                                <a href="#" class="text-success fw-bold">Back to List</a>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="row mb-4 g-0">
                                        <div class="col-md-6">
                                            <div class="card" style="border-radius: 0;">
                                                <div class="card-header text-uppercase text-muted">
                                                    <h6 class="mt-2">Billing Address</h6>
                                                </div>
                                                <div class="card-body">
                                                    <p>Dianne Russell</p>
                                                    <p>4140 Parker Rd, Allentown, New Mexico 31134</p>
                                                    <p>Email: dianne.russell@gmail.com</p>
                                                    <p>Phone: (671) 555-0110</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="card" style="border-radius: 0;">
                                                <div class="card-header text-uppercase text-muted">
                                                    <h6 class="mt-2">Billing Address</h6>
                                                </div>
                                                <div class="card-body">
                                                    <p>Dianne Russell</p>
                                                    <p>4140 Parker Rd, Allentown, New Mexico 31134</p>
                                                    <p>Email: dianne.russell@gmail.com</p>
                                                    <p>Phone: (671) 555-0110</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="card">
                                        <div class="card-header text-muted">
                                            <div class="row p-2">
                                                <div class="col-md-5" style="border-right: 2px solid #ccc;">
                                                    <h6>Order ID: </h6>
                                                    <strong class="text-dark">#4852</strong>
                                                </div>
                                                <div class="col-md-7">
                                                    <h6>Payment Method: </h6>
                                                    <strong class="text-dark">PayPal</strong>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-body p-2">
                                            <table class="table table-borderless pay-table">
                                                <tr>
                                                    <td>Subtotal:</td>
                                                    <td>$365.00</td>
                                                </tr>
                                                <tr>
                                                    <td>Discount:</td>
                                                    <td>20%</td>
                                                </tr>
                                                <tr>
                                                    <td>Shipping:</td>
                                                    <td>Free</td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 20px;">Total:</td>
                                                    <td><strong style="font-size: 23px; color: rgb(52, 171, 52);">$84.00</strong></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Order Progress -->
                        <div class="order-progress mb-4">
                            <ul class="progress-container d-flex justify-content-between align-items-center">
                                <!-- Step 1: Order Received -->
                                <li class="progress-step completed">
                                    <div class="step-circle">
                                        <i class="bi bi-check-circle-fill"></i>
                                    </div>
                                    <span>Order received</span>
                                </li>

                                <!-- Step 2: Processing -->
                                <li class="progress-step active">
                                    <div class="step-circle">
                                        <span>02</span>
                                    </div>
                                    <span>Processing</span>
                                </li>

                                <!-- Step 3: On the Way -->
                                <li class="progress-step">
                                    <div class="step-circle">
                                        <span>03</span>
                                    </div>
                                    <span>On the way</span>
                                </li>

                                <!-- Step 4: Delivered -->
                                <li class="progress-step">
                                    <div class="step-circle">
                                        <span>04</span>
                                    </div>
                                    <span>Delivered</span>
                                </li>
                            </ul>
                        </div>


                        <!-- Order Items -->
                        <table class="table table-hover">
                            <thead class="table-active">
                                <tr>
                                    <th>Product</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Subtotal</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="d-flex align-items-center">
                                        <img src="./images/fil2.png" alt="Red Capsicum" class="product-img me-2">
                                        <span>Red Capsicum</span>
                                    </td>
                                    <td>$14.00</td>
                                    <td>x5</td>
                                    <td>$70.00</td>
                                </tr>
                                <tr>
                                    <td class="d-flex align-items-center">
                                        <img src="./images/fil4.png" alt="Green Capsicum"
                                             class="product-img me-2">
                                        <span>Green Capsicum</span>
                                    </td>
                                    <td>$14.00</td>
                                    <td>x2</td>
                                    <td>$28.00</td>
                                </tr>
                                <tr>
                                    <td class="d-flex align-items-center">
                                        <img src="./images/list4.png" alt="Green Chili"
                                             class="product-img me-2">
                                        <span>Green Chili</span>
                                    </td>
                                    <td>$26.70</td>
                                    <td>x10</td>
                                    <td>$267.00</td>
                                </tr>
                            </tbody>
                        </table>

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