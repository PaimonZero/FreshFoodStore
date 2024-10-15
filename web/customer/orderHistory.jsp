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
                                <form id="infoForm1" action="Dashboard?action=listInfo" method="POST">
                                    <a class="nav-link" aria-current="page" onclick="document.getElementById('infoForm1').submit();" style="cursor: pointer;">
                                        <i class="fas fa-th-large me-2"></i>
                                        Bảng điều khiển
                                    </a>
                                </form>
                            </li>
                            <li>
                                <form id="infoForm2" action="OrderHistory" method="POST"> <%--đổi đường dẫn--%>
                                    <a class="nav-link active" onclick="document.getElementById('infoForm2').submit();" style="cursor: pointer;">
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
                                        <c:if test="${empty AllOrderList}">
                                            <tr>
                                                <td colspan="5" class="text-center text-danger fw-bold">Lịch sử giỏ hàng đang trống.</td>
                                            </tr>
                                        </c:if>

                                        <c:if test="${!empty AllOrderList}">
                                            <c:forEach var="order" items="${AllOrderList}">
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

                            <!-- Pagination -->
                            <!--                                                        <nav aria-label="Page navigation">
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
                                                                                    </nav>-->
                            <div class="pagination">
                                <ul class="pagination pagination-sm m-auto mt-3">
                                    <c:if test="${pageCount > 1}">
                                        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                            <a class="page-link p-2 px-3" href="OrderHistory?page=${currentPage - 1}" tabindex="-1" aria-disabled="${currentPage == 1}">&laquo;</a>
                                        </li>
                                    </c:if>
                                    <c:forEach begin="1" end="${pageCount}" var="i">
                                        <li class="page-item ${i == currentPage ? 'active' : ''}">
                                            <a class="page-link p-2 px-3" href="OrderHistory?page=${i}">${i}</a>
                                        </li>

                                    </c:forEach>
                                    <c:if test="${pageCount > 1}">
                                        <li class="page-item ${currentPage == pageCount ? 'disabled' : ''}">
                                            <a class="page-link p-2 px-3" href="OrderHistory?page=${currentPage + 1}" aria-disabled="${currentPage == pageCount}">&raquo;</a>
                                        </li>
                                    </c:if>
                                </ul>
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
