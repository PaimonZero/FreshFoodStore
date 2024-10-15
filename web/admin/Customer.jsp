<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/adminCss/Customer.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../admin/HeadSidebar/header-sidebar.css">
    <title>Customer</title>
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
                                    <h4 class="mb-0" style="font-weight: bold;">Customer</h4>
                                    <div>
                                        <button class="btn btn-sm btn-outline-success" data-bs-toggle="modal"
                                                data-bs-target="#addProductModal" style="width: 105px;">Edit
                                            Customer</button>

                                        <button class="btn btn-sm btn-outline-secondary"
                                                style="width: 105px;">Filter</button>
                                        <button class="btn btn-sm btn-outline-secondary" style="width: 105px;">Dowload
                                            All</button>
                                    </div>
                                </div>
                                <div class="card-body" style="height: 549.3px; overflow-y: auto;">
                                    <table class="table ">
                                        <thead>
                                            <tr>
                                                <th scope="col">Customer ID</th>
                                                <th scope="col">Name</th>
                                                <th scope="col">Number</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">Address</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="customer" items="${customers}">
                                                <tr>
                                                    <td>${customer.customerId}</td>
                                                    <td>${customer.fullName}</td>
                                                    <td>${customer.phone}</td>
                                                    <td>${customer.email}</td>
                                                    <td>${customer.address}</td>
                                                    <td>${customer.status}</td>
                                                    <td>
                                                        <!-- Edit Customer Button with data attributes -->
                                                        <button class="btn btn-primary btn-sm" 
                                                                data-customer-id="${customer.customerId}"
                                                                data-customer-name="${customer.fullName}"
                                                                data-customer-number="${customer.phone}"
                                                                data-customer-email="${customer.email}"
                                                                data-customer-address="${customer.address}"
                                                                data-customer-status="${customer.status}" 
                                                                data-bs-toggle="modal"
                                                                data-bs-target="#addProductModal"
                                                                onclick="populateModal(this)">Edit</button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
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
                                <h5 class="modal-title" id="addProductModalLabel">Edit Customer</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form id="editCustomerForm" action="${pageContext.request.contextPath}/admin/customers" method="POST">
                                    <input type="hidden" name="action" value="edit">
                                    <input type="hidden" name="customerId" id="customerId">

                                    <div class="form-group">
                                        <label for="customerName">Tên khách hàng</label>
                                        <input type="text" class="form-control" id="customerName" name="customerName" placeholder="Enter customer name">
                                    </div>
                                    <div class="form-group">
                                        <label for="customerEmail">Email</label>
                                        <input type="email" class="form-control" id="customerEmail" name="customerEmail" placeholder="Enter email">
                                    </div>
                                    <div class="form-group">
                                        <label for="customerAddress">Địa chỉ</label>
                                        <input type="text" class="form-control" id="customerAddress" name="customerAddress" placeholder="Enter Address">
                                    </div>
                                    <div class="form-group">
                                        <label for="customerPhone">Số điện thoại</label>
                                        <input type="text" class="form-control" id="customerPhone" name="customerPhone" placeholder="Enter Phone Number">
                                    </div>
                                    <div class="form-group">
                                        <label for="customerStatus">Trạng thái</label>
                                        <select class="form-control" id="customerStatus" name="customerStatus">
                                            <option value="Block" ${customerStatus == 'Block' ? 'selected' : ''}>Block</option>
                                            <option value="Normal" ${customerStatus == 'Normal' ? 'selected' : ''}>Normal</option>
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
                        document.getElementById('customerId').value = button.getAttribute('data-customer-id');
                        document.getElementById('customerName').value = button.getAttribute('data-customer-name');
                        document.getElementById('customerEmail').value = button.getAttribute('data-customer-email');
                        document.getElementById('customerAddress').value = button.getAttribute('data-customer-address');
                        document.getElementById('customerPhone').value = button.getAttribute('data-customer-number');
                        document.getElementById('customerStatus').value = button.getAttribute('data-customer-status');
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
                <script>
                    $(document).ready(function () {
                        $('#table_id').DataTable();
                    });
                </script>
                </body>
                </html>
