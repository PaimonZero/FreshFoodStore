<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="dto.OrderDTO" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.HashMap" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/adminCss/Orders.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../admin/HeadSidebar/header-sidebar.css">
    <title>Orders</title>
</head>

<body>
    <%@include file="HeadSidebar/sidebar.jsp" %> 
    <%@include file="HeadSidebar/header.jsp" %>
    <div class="scale-container">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2 bg-Secondary text-white">
                    <div id="sidebar-container"></div>
                </div>
                <div class="col-md-10 bg-Secondary text-white">
                    <div id="header-container">
                        <div class="row mt-4">
                            <div class="col-md-12">
                                <div class="card text-dark bg-light d-flex mb-3">
                                    <h4 class="card-header bg-light" style="font-weight: bold;">Tổng quan đơn hàng</h4>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-md-3" style="border-right: 3px solid #F0F1F3;">
                                                <h5 class="card-title" style="color:#1570EF;">Tổng số đơn hàng</h5>
                                                <p class="card-text">
                                                    <c:choose>
                                                        <c:when test="${not empty orderOverview}">
                                                            <c:out value="${orderOverview['totalOrders']}" />
                                                        </c:when>
                                                        <c:otherwise>0</c:otherwise>
                                                    </c:choose>
                                                </p>
                                                <p class="card-text">Last 7 days</p>
                                            </div>
                                            <div class="col-md-3" style="border-right: 3px solid #F0F1F3;">
                                                <h5 class="card-title" style="color:#E19133;">Tổng tiền nhận được</h5>
                                                <div class="d-flex justify-content-between">
                                                    <p class="card-text">
                                                        <c:choose>
                                                            <c:when test="${not empty orderOverview}">
                                                                <c:out value="${orderOverview['totalRevenue']}" />
                                                            </c:when>
                                                            <c:otherwise>0</c:otherwise>
                                                        </c:choose>
                                                    </p>
                                                </div>
                                                <div class="d-flex justify-content-between">
                                                    <p class="card-text">Last 7 days</p>
                                                    <p class="card-text">Revenue</p>
                                                </div>
                                            </div>
                                            <div class="col-md-3" style="border-right: 3px solid #F0F1F3;">
                                                <h5 class="card-title" style="color:#845EBC">Đang vận chuyển</h5>
                                                <div class="d-flex justify-content-between">
                                                    <p class="card-text">
                                                        <c:choose>
                                                            <c:when test="${not empty orderOverview}">
                                                                <c:out value="${orderOverview['totalShipping']}" />
                                                            </c:when>
                                                            <c:otherwise>0</c:otherwise>
                                                        </c:choose>
                                                    </p>
                                                </div>
                                                <div class="d-flex justify-content-between">
                                                    <p class="card-text">Last 7 days</p>
                                                    <p class="card-text">Cost</p>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <h5 class="card-title" style="color:#F36960">Số đơn hủy/trả</h5>
                                                <div class="d-flex justify-content-between">
                                                    <p class="card-text">
                                                        <c:choose>
                                                            <c:when test="${not empty orderOverview}">
                                                                <c:out value="${orderOverview['totalCanceled']}" />
                                                            </c:when>
                                                            <c:otherwise>0</c:otherwise>
                                                        </c:choose>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="card text-dark bg-light d-flex mb-3">
                                    <div class="card-header bg-light d-flex align-items-center justify-content-between">
                                        <h4 class="mb-0" style="font-weight: bold;">Đơn hàng</h4>
                                        <div>
                                            <button class="btn btn-sm btn-outline-secondary"style="width: 105px;">Bộ lọc</button>
                                            <button class="btn btn-sm btn-outline-secondary"style="width: 105px;">Lịch sử đơn hàng</button>
                                        </div>
                                    </div>

                                    <!-- Pagination logic -->
                                    <%
                                        int itemsPerPage = 9;
                                        List<OrderDTO> orderDisplayList = (List<OrderDTO>) request.getAttribute("orderDisplayList");
                                        int totalOrders = (orderDisplayList != null) ? orderDisplayList.size() : 0;
                                        int totalPages = (int) Math.ceil((double) totalOrders / itemsPerPage);
                                        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
                                        int startIndex = (currentPage - 1) * itemsPerPage;
                                        int endIndex = Math.min(startIndex + itemsPerPage, totalOrders);
                                    %>

                                    <div class="card-body">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col">ID</th>
                                                    <th scope="col">Tên khách hàng</th>
                                                    <th scope="col">Thanh toán</th>
                                                    <th scope="col">Tổng giá trị</th>
                                                    <th scope="col">Ngày đặt</th>
                                                    <th scope="col">Người giao đơn</th>
                                                    <th scope="col">Trạng thái đơn hàng</th>
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!-- Lặp qua danh sách đơn hàng và hiển thị -->
                                                <%
                                                    if (orderDisplayList == null || orderDisplayList.isEmpty()) {
                                                %>
                                                <tr>
                                                    <td colspan="7" class="text-center">Không có đơn hàng nào</td>
                                                </tr>
                                                <%
                                                    } else {
                                                        for (int i = startIndex; i < endIndex; i++) {
                                                            OrderDTO order = orderDisplayList.get(i);
                                                %>
                                                <tr>
                                                    <td><%= order.getOrderId() %></td>
                                                    <td><%= order.getReceiverName() %></td>
                                                    <td><%= order.getPaymentStatus() %></td>
                                                    <td><%= order.getTotalPrice() %></td>
                                                    <td><%= order.getOrderCreatedAt() %></td>
                                                    <td><%= order.getShipperName() %></td>
                                                    <td><%= order.getDeliveryStatus() %></td>
                                                </tr>
                                                <%
                                                        }
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                    </div>

                                    <!-- Pagination buttons -->
                                    <div class="card-footer d-flex justify-content-between"
                                         style="bottom: 0; background-color: white;">
                                        <button class="btn btn-outline-secondary" id="prevPage" style="width: 100px;" <% if (currentPage == 1) { %>disabled<% } %>>Previous</button>
                                        <span class="mx-3">Page <span id="currentPage"><%= currentPage %></span> of <span id="totalPages"><%= totalPages %></span></span>
                                        <button class="btn btn-outline-secondary" id="nextPage" style="width: 100px;" <% if (currentPage == totalPages) { %>disabled<% } %>>Next</button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap.js"></script>
    <script src="../admin/HeadSidebar/MenuButton.js"></script>

    <!-- JavaScript to handle pagination -->
    <script>
        document.getElementById('prevPage').addEventListener('click', function () {
            var currentPage = parseInt(document.getElementById('currentPage').innerText);
            if (currentPage > 1) {
                window.location.href = "?page=" + (currentPage - 1);
            }
        });

        document.getElementById('nextPage').addEventListener('click', function () {
            var currentPage = parseInt(document.getElementById('currentPage').innerText);
            var totalPages = parseInt(document.getElementById('totalPages').innerText);
            if (currentPage < totalPages) {
                window.location.href = "?page=" + (currentPage + 1);
            }
        });
    </script>
</body>
</html>