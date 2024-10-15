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
        <style>
            .edit-btn button{
                padding: 10px 20px;
            }
        </style>
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
                                <form id="infoForm1" action="Dashboard?action=listInfo" method="POST">
                                    <a class="nav-link active" aria-current="page" onclick="document.getElementById('infoForm1').submit();" style="cursor: pointer;">
                                        <i class="fas fa-th-large me-2"></i>
                                        Bảng điều khiển
                                    </a>
                                </form>
                            </li>
                            <li>
                                <form id="infoForm2" action="OrderHistory" method="POST"> <%--đổi đường dẫn--%>
                                    <a class="nav-link" onclick="document.getElementById('infoForm2').submit();" style="cursor: pointer;">
                                        <i class="fas fa-sync-alt me-2"></i>
                                        Lịch sử đơn hàng
                                    </a>
                                </form>
                            </li>
                            <li>
                                <form id="infoForm3" action="" method="POST"> <%--đổi đường dẫn--%>
                                    <a class="nav-link" onclick="document.getElementById('infoForm3').submit();" style="cursor: pointer;">
                                        <i class="far fa-heart me-2"></i>
                                        Danh sách yêu thích
                                    </a>
                                </form>
                            </li>
                            <li>
                                <form id="infoForm4" action="" method="POST"> <%--đổi đường dẫn--%>
                                    <a class="nav-link" onclick="document.getElementById('infoForm4').submit();" style="cursor: pointer;">
                                        <i class="bi bi-bag me-2"></i>
                                        Giỏ hàng
                                    </a>
                                </form>
                            </li>
                            <li>
                                <form id="infoForm5" action="AccountSetting?action=showData" method="POST"> <%--đổi đường dẫn--%>
                                    <a class="nav-link" onclick="document.getElementById('infoForm5').submit();" style="cursor: pointer;">
                                        <i class="bi bi-gear me-2"></i>
                                        Cài đặt
                                    </a>
                                </form>
                            </li>
                            <li>
                                <form id="infoForm6" action="" method="POST"> <%--đổi đường dẫn--%>
                                    <a class="nav-link" onclick="document.getElementById('infoForm6').submit();" style="cursor: pointer;">
                                        <i class="fas fa-sign-out-alt me-2"></i>
                                        Đăng xuất
                                    </a>
                                </form>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-9 mt-2 mb-3">
                    <div class="row">
                        <!-- Profile Info -->
                        <div class="col-md-6 mb-3">
                            <div class="card shadow-sm edit-btn">
                                <form action="AccountSetting?action=showData" method="POST">
                                    <div class="card-body text-center">
                                        <img src="../images/${listInfo.avatar}" alt="Profile Image" class="profile-picture">
                                        <h5 class="card-title">${listInfo.fullName}</h5>
                                        <p class="text-muted">Customer</p> <%--tạm để cứng ở đây--%>
                                        <button type="submit" class="btn btn-outline-success btn-sm">Edit Profile</button>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <!-- Billing Info -->
                        <div class="col-md-6 mb-3">
                            <div class="card shadow-sm edit-btn">
                                <form action="AccountSetting?action=showData" method="POST">
                                    <div class="card-body">
                                        <h5 class="card-title text-muted">Billing Address</h5>
                                        <p class="h4 fw-bold">${listInfo.fullName}</p>
                                        <p>${listInfo.address}</p>
                                        <p>${listInfo.email}</p>
                                        <p>${listInfo.phone}</p>
                                        <button type="submit" class="btn btn-outline-success btn-sm">Edit Address</button>
                                    </div>
                                </form>
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
                                        <c:if test="${empty listOrder}">
                                            <tr>
                                                <td colspan="5" class="text-center text-danger fw-bold">Lịch sử giỏ hàng đang trống.</td>
                                            </tr>
                                        </c:if>

                                        <c:if test="${!empty listOrder}">
                                            <c:forEach var="order" items="${listOrder}" begin="0" end ="5">
                                                <tr>
                                                    <td>${order.orderId}</td>
                                                    <td>${order.orderCreatedAtString}</td>
                                                    <!--<td>$135.00 (5 Products)</td>-->
                                                    <td>${order.totalString}₫ (${order.quantity} Product)</td><%--đã validate về dạng tiền VN--%>
                                                    <td><span class="badge 
                                                              <c:choose>
                                                                  <c:when test="${order.deliveryStatus == 'Pending'}">bg-warning text-dark</c:when>
                                                                  <c:when test="${order.deliveryStatus == 'Shipped'}">bg-info text-dark</c:when>
                                                                  <c:when test="${order.deliveryStatus == 'Delivered'}">bg-success</c:when>
                                                              </c:choose>">
                                                            ${order.deliveryStatus}
                                                        </span>
                                                    </td>
                                                    <td>
                                                        <!--                                                        <a href="orderDetail" class="text-success">
                                                                                                                    <input type="hidden" name="orderID" value="${order.orderId}">
                                                                                                                    View Details
                                                                                                                </a>-->
                                                        <form action="orderDetail" method="POST">
                                                            <input type="hidden" name="orderID" value="${order.orderId}">
                                                            <button type="submit" class="btn p-0 px-2 btn-danger">Xem chi tiết</button>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--        <form action="Dashboard">
                    <button type="submit">submit</button>
                </form>-->
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
