<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="dal.DeliveryDAO"%>
<%@page import="model.Delivery"%>
<%@page import="java.util.List"%>
<%@page import="model.Users"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../css/adminCss/ListDelivery.css">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="../admin/HeadSidebar/header-sidebar.css">
        <title>List Delivery</title>
    </head>

    <body>
        <%@include file="HeadSidebar/sidebar.jsp" %> 
        <%@include file="HeadSidebar/header.jsp" %>

        <%
//            DeliveryDAO dao = new DeliveryDAO();
//            
//            // Get the user account from the session
//            Users account = (Users) session.getAttribute("account");
//            
//            if (account != null) {
//                String role = account.getRole();
//
//                // Redirect if the role is not manager, staff, or shipper
//                if (!role.equals("manager") && !role.equals("staff") && !role.equals("shipper")) {
//                    session.setAttribute("notifyAuth", "notAuthorized");
//                    String targetURL = request.getContextPath() + "/customer/Homepage";
//                    String encodedURL = response.encodeRedirectURL(targetURL);
//                    response.sendRedirect(encodedURL);
//                    return;
//                } else if (role.equals("shipper")) {
//                    //Lấy shipperId để lấy listDelivery cho shipper đó
//                    int shipperId = dao.getShipperIdByUserId(account.getUserId());
//                    List<Delivery> deliveryList = dao.getAllDeliveryForShipper(shipperId);
//                    request.setAttribute("deliveryList", deliveryList);
//                }
//            } else {
//                // If no account is found in the session, redirect to login or another appropriate page
//                String loginURL = request.getContextPath() + "/SignIn.jsp";
//                String encodedURL = response.encodeRedirectURL(loginURL);
//                response.sendRedirect(encodedURL);
//                return;
//            }
        %>
        <div class="scale-container">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-2 bg-Secondary text-white">
                        <div id="sidebar-container"></div>
                    </div>
                    <div class="col-md-10 bg-Secondary text-white">
                        <div id="header-container"></div>

                        <div class="row mt-4">
                            <div class="col-md-12">
                                <div class="card text-dark bg-light d-flex mb-3">
                                    <div class="card-header bg-light d-flex align-items-center justify-content-between">
                                        <h4 class="mb-0" style="font-weight: bold;">Delivery List for ${sessionScope.account.fullName}</h4>
                                        <div>
                                            <form action="${pageContext.request.contextPath}/admin/DeliveryList?action=search" method="POST" style="display: flex; align-items: center;">
                                                <input type="text" name="searchQuery" placeholder="Find id,name,phone" class="form-control" style="width: 200px; margin-right: 10px; margin-bottom: 0">
                                                <button type="submit" class="btn btn-sm btn-outline-success" style="width: 105px;">Search</button>
                                            </form>
<!--                                            <button class="btn btn-sm btn-outline-success" style="width: 105px;">Order History</button>
                                            <button class="btn btn-sm btn-outline-secondary" style="width: 105px;">Filter</button>-->
                                        </div>
                                    </div>
                                    <div class="card-body" style="height: auto;">
                                        <table class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Order ID</th>
                                                    <th scope="col">Customer Name</th>
                                                    <th scope="col">Phone Number</th>
                                                    <th scope="col">Payment Status</th>
                                                    <th scope="col">Total Value</th>
                                                    <th scope="col">Order Date</th>
                                                    <th scope="col">Delivery Status</th>
                                                    <th scope="col"></th>
                                                    <th scope="col" type="hidden"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!-- JSTL loop through the deliveryList -->
                                                <% 
//                                                    //hàm lấy dữ liệu toàn bộ delivery, hiện tại ko sử dụng
//                                                    DeliveryDAO dao = new DeliveryDAO();        
//                                                    List<Delivery> deliveryList = dao.getAllDelivery();
//                                                    request.setAttribute("deliveryList", deliveryList);
                                                %>
                                                <c:choose>
                                                    <c:when test="${not empty deliveryList}">
                                                        <c:forEach var="delivery" items="${deliveryList}">
                                                            <!-- Make form ID unique by appending the orderId -->
                                                        <form id="infoForm2_${delivery.orderId}" action="DeliveryStatusController" method="POST">
                                                            <tr onclick="document.getElementById('infoForm2_${delivery.orderId}').submit();" style="cursor: pointer;">
                                                                <td>${delivery.orderId}</td>
                                                                <td>${delivery.userName}</td>
                                                                <td>${delivery.receiverPhone}</td>
                                                                <td>${delivery.paymentStatus}</td>
                                                                <td><fmt:formatNumber value="${delivery.totalValue}" pattern="#,###" /></td>
                                                                <td><fmt:formatDate value="${delivery.orderDate}" pattern="yyyy-MM-dd" /></td>
                                                                <td>${delivery.deliveryStatus}</td>
                                                                <td>
                                                                    <input type="hidden" name="currentId" value="${delivery.orderId}" />
                                                                </td>
                                                                <td>
                                                                    <button class="btn btn-sm btn-outline-primary">
                                                                        <ion-icon name="pencil-outline"></ion-icon>
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                        </form>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <tr>
                                                        <td colspan="9" style="text-align: center;">Hiện tại bạn chưa có đơn hàng nào cần giao!</td>
                                                    </tr>
                                                </c:otherwise>
                                            </c:choose>
                                            </tbody>

                                        </table>
                                    </div>
                                    <!--                                    <div class="card-footer d-flex justify-content-between" style="bottom: 0; background-color: white;">
                                                                            <button class="btn btn-outline-secondary" style="width: 100px;">Previous</button>
                                                                            <span class="mx-3">Page 1 of 10</span>
                                                                            <button class="btn btn-outline-secondary" style="width: 100px;">Next</button>
                                                                        </div>-->

                                    <!-- Pagination buttons -->
                                    <div class="card-footer d-flex justify-content-between"
                                         style="bottom: 0; background-color: white;">
                                        <button class="btn btn-outline-secondary" id="prevPage" style="width: 100px;">Previous</button>
                                        <span class="mx-3" id="pageInfo"></span>
                                        <button class="btn btn-outline-secondary" id="nextPage" style="width: 100px;">Next</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%-- Thông báo đăng nhập --%>
        <c:if test="${not empty notifyAuth}">
            <input type="hidden" id="notifyAuth" value="${notifyAuth}" />
            <c:remove var="notifyAuth" scope="session" />
        </c:if>

        <script>
            window.onload = function () {
                var notifyAuthField = document.getElementById('notifyAuth');
                if (notifyAuthField) {
                    var notifyAuth = notifyAuthField.value;
                    if (notifyAuth === "notAuthorized") {
                        alert("Bạn chưa được cấp quyền truy cập vào trang đấy!");
                    }
                    // Remove the hidden input field after alert
                    notifyAuthField.remove();
                }
            };
        </script>
        <script>
// Dữ liệu sẽ được hiển thị trong bảng
            let table = document.querySelector('table tbody');
            let rows = table.querySelectorAll('tr');
            let rowsPerPage = 8; // Số hàng hiển thị trên mỗi trang
            let currentPage = 1; // Trang hiện tại
            let totalPages = Math.ceil(rows.length / rowsPerPage); // Tổng số trang

// Hàm hiển thị hàng theo trang
            function displayTablePage(page) {
                // Tính toán chỉ mục bắt đầu và kết thúc
                let start = (page - 1) * rowsPerPage;
                let end = start + rowsPerPage;

                // Ẩn tất cả các hàng trước
                rows.forEach((row, index) => {
                    row.style.display = (index >= start && index < end) ? '' : 'none';
                });

                // Cập nhật trạng thái nút phân trang
                document.getElementById('prevPage').disabled = (page === 1);
                document.getElementById('nextPage').disabled = (page === totalPages);

                // Cập nhật số trang hiển thị
                document.getElementById('pageInfo').innerText = "Page " + page + " of " + totalPages;
            }

// Hàm chuyển sang trang trước
            function prevPage() {
                if (currentPage > 1) {
                    currentPage--;
                    displayTablePage(currentPage);
                }
            }

// Hàm chuyển sang trang tiếp theo
            function nextPage() {
                if (currentPage < totalPages) {
                    currentPage++;
                    displayTablePage(currentPage);
                }
            }

// Gắn sự kiện cho các nút "Previous" và "Next"
            document.getElementById('prevPage').addEventListener('click', prevPage);
            document.getElementById('nextPage').addEventListener('click', nextPage);

// Hiển thị trang đầu tiên khi trang tải
            displayTablePage(currentPage);
        </script>

        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/bootstrap.js"></script>
        <script src="../admin/HeadSidebar/MenuButton.js"></script>
        <script src="../admin/HeadSidebar/SideBar.js"></script>
    </body>
</html>
