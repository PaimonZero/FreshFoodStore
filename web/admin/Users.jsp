<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/adminCss/Customer.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../admin/HeadSidebar/header-sidebar.css">
    <title>Users</title>
</head>

<body>
    <%@include file="HeadSidebar/sidebar.jsp" %> 
    <%@include file="HeadSidebar/header.jsp" %>

    <div class="scale-container">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2 text-white">
                    <div id="sidebar-container"></div>
                </div>
                <div class="col-md-10 text-white">
                    <div id="header-container"></div>
                    <div class="row mt-4">
                        <div class="col-md-12">
                            <div class="card text-dark bg-light d-flex mb-3">
                                <div class="card-header bg-light d-flex align-items-center justify-content-between">
                                    <h4 class="mb-0" style="font-weight: bold;">Users</h4>
                                    <div class="d-flex justify-content-center align-items-center">
                                        <form action="export?action=users" method="post">
                                            <button class="btn btn-sm btn-outline-secondary me-2">Export to Excel</button>
                                        </form>

                                        <form action="${pageContext.request.contextPath}/admin/users?action=search" method="POST" style="display: flex; align-items: center;">
                                            <input type="text" name="searchQuery" placeholder="Find name,email,phone,.." class="form-control" style="width: 200px; margin-right: 10px; margin-bottom: 0">
                                            <button type="submit" class="btn btn-sm btn-outline-success" style="width: 105px;">Search</button>
                                        </form>
                                        <!--                                        <button class="btn btn-sm btn-outline-secondary"
                                                                                        style="width: 105px;">Filter</button>
                                                                                <button class="btn btn-sm btn-outline-secondary" style="width: 105px;">Dowload All</button>-->
                                    </div>
                                </div>
                                <div class="card-body" style="height: 549.3px; overflow-y: auto;">
                                    <table class="table ">
                                        <thead>
                                            <tr>
                                                <th scope="col">User ID</th>
                                                <th scope="col">Tên đầy đủ</th>
                                                <th scope="col">Số điện thoại</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">Địa chỉ</th>
                                                <th scope="col">Vai trò</th>
                                                <th scope="col">Trạng thái</th>
                                                <th scope="col">Ngày tạo</th>
                                                <th scope="col">Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:choose>
                                                <c:when test="${empty users}">
                                                    <tr>
                                                        <td colspan="9" style="text-align: center;">No users found.</td>
                                                    </tr>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach var="user" items="${users}">
                                                        <tr>
                                                            <td>${user.userId}</td>
                                                            <td>${user.fullName}</td>
                                                            <td>${user.phone}</td>
                                                            <td>${user.email}</td>
                                                            <td>${user.address}</td>
                                                            <td>${user.roleName}</td>
                                                            <td style="color: ${user.status == 'block' ? 'red' : 'green'};">${user.status}</td>
                                                            <td>
                                                                <fmt:formatDate value="${user.createdAt}" pattern="dd/MM/yyyy" />
                                                            </td>
                                                            <td>
                                                                <!-- Only show Edit button if account.role is not "staff" or user.roleName is not "manager" -->
                                                                <c:if test="${account.role != 'staff' || user.roleName != 'Manager'}">
                                                                    <button class="btn btn-primary btn-sm" 
                                                                            data-user-id="${user.userId}"
                                                                            data-user-fullName="${user.fullName}"
                                                                            data-user-phone="${user.phone}"
                                                                            data-user-email="${user.email}"
                                                                            data-user-address="${user.address}"
                                                                            data-user-roleName="${user.roleName}"
                                                                            data-user-status="${user.status}" 
                                                                            data-bs-toggle="modal"
                                                                            data-bs-target="#addProductModal"
                                                                            onclick="populateModal(this)">Edit</button>
                                                                </c:if>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </tbody>
                                    </table>

                                </div>
                                <div class="card-footer d-flex justify-content-between" style="bottom: 0; background-color: white;">
                                    <!-- Previous Button -->
                                    <c:if test="${currentPage > 1}">
                                        <button class="btn btn-outline-secondary" style="width: 100px;"
                                                onclick="window.location.href = '?page=${currentPage - 1}'">Previous</button>
                                    </c:if>
                                    <c:if test="${currentPage == 1}">
                                        <button class="btn btn-outline-secondary" style="width: 100px;" disabled>Previous</button>
                                    </c:if>

                                    <!-- Page Counter -->
                                    <span class="mx-3">Page ${currentPage} of ${totalPages}</span>

                                    <!-- Next Button -->
                                    <c:if test="${currentPage < totalPages}">
                                        <button class="btn btn-outline-secondary" style="width: 100px;"
                                                onclick="window.location.href = '?page=${currentPage + 1}'">Next</button>
                                    </c:if>
                                    <c:if test="${currentPage == totalPages}">
                                        <button class="btn btn-outline-secondary" style="width: 100px;" disabled>Next</button>
                                    </c:if>
                                </div>

                            </div>

                        </div>
                    </div>

                </div>
                <div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
                    <div class="modal-dialog" style="margin-top: 70px;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addProductModalLabel">Edit User</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form id="editCustomerForm" action="${pageContext.request.contextPath}/admin/users" method="POST">
                                    <input type="hidden" name="action" value="edit">
                                    <input type="hidden" name="userId" id="userId">

                                    <div class="form-group">
                                        <label for="userFullName">Tên người dùng</label>
                                        <input type="text" class="form-control" id="userFullName" name="userFullName" placeholder="Enter Full Name">
                                    </div>
                                    <div class="form-group">
                                        <label for="userEmail">Email</label>
                                        <input type="email" class="form-control" id="userEmail" name="userEmail" placeholder="Enter Email">
                                    </div>
                                    <div class="form-group">
                                        <label for="userAddress">Địa chỉ</label>
                                        <input type="text" class="form-control" id="userAddress" name="userAddress" placeholder="Enter Address">
                                    </div>
                                    <div class="form-group">
                                        <label for="userPhone">Số điện thoại</label>
                                        <input type="text" class="form-control" id="userPhone" name="userPhone" pattern="\d{10}" title="Không đúng định dạng sđt" placeholder="Enter Phone Number">
                                    </div>
                                    <div class="form-group">
                                        <label for="userRoleName">Vai trò</label>
                                        <select class="form-control" id="userRoleName" name="userRoleName">
                                            <option value="Manager" ${userRole == 'Manager' ? 'selected' : ''}>Manager</option>
                                            <option value="Staff" ${userRole == 'Staff' ? 'selected' : ''}>Staff</option>
                                            <option value="Shipper" ${userRole == 'Shipper' ? 'selected' : ''}>Shipper</option>
                                            <option value="Customer" ${userRole == 'Customer' ? 'selected' : ''}>Customer</option>
                                            <option value="Unknown" ${userRole == 'Unknown' ? 'selected' : ''}>Unknown</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="userStatus">Trạng thái</label>
                                        <select class="form-control" id="userStatus" name="userStatus">
                                            <option value="blocked" ${customerStatus == 'Blocked' ? 'selected' : ''}>Block</option>
                                            <option value="normal" ${customerStatus == 'Normal' ? 'selected' : ''}>Normal</option>
                                        </select>
                                    </div>

                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="width: 70px;">Cancel</button>
                                <button type="submit" class="btn btn-success" form="editCustomerForm" style="width: 70px;">Save</button>
                            </div>
                        </div>
                    </div>
                </div>


                <script>
                    function populateModal(button) {
                        document.getElementById('userId').value = button.getAttribute('data-user-id');
                        document.getElementById('userFullName').value = button.getAttribute('data-user-fullName');
                        document.getElementById('userEmail').value = button.getAttribute('data-user-email');
                        document.getElementById('userAddress').value = button.getAttribute('data-user-address');
                        document.getElementById('userPhone').value = button.getAttribute('data-user-phone');
                        document.getElementById('userRoleName').value = button.getAttribute('data-user-roleName');
                        document.getElementById('userStatus').value = button.getAttribute('data-user-status');
                    }

                </script>

                <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
                <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
                <script src="../js/bootstrap.bundle.min.js"></script>
                <script src="../js/adminJs/Customer.js"></script>
                <script src="../js/bootstrap.min.js"></script>
                <script src="../js/jquery-3.6.0.min.js"></script>
                <script src="../js/jquery.dataTables.min.js"></script>
                <script src="../js/dataTables.bootstrap5.min.js"></script>
                <script src="../admin/HeadSidebar/MenuButton.js"></script>
                <script src="../admin/HeadSidebar/SideBar.js"></script>
                <script>
                    $(document).ready(function () {
                        $('#table_id').DataTable();
                    });
                </script>
                </body>
                </html>
