<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/adminCss/Supplier.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../admin/HeadSidebar/header-sidebar.css">
    <title>Supplier</title>
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
                                    <h4 class="mb-0" style="font-weight: bold;">Supplier</h4>
                                    <div style="display: flex; align-items: center;">
                                        <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal"
                                                data-bs-target="#addProductModal" style="width: 105px; margin-right: 10px;">Add Supplier</button>

                                        <form action="${pageContext.request.contextPath}/admin/suppliers?action=search" method="POST" style="display: flex; align-items: center;">
                                            <input type="text" name="searchQuery" placeholder="Find name, email, phone,.." class="form-control" style="width: 200px; margin-right: 10px; margin-bottom: 0">
                                            <button type="submit" class="btn btn-sm btn-outline-success" style="width: 105px;">Search</button>
                                        </form>
                                    </div>

                                </div>
                                <div class="card-body" style="height: 549.3px; overflow-y: auto;">
                                    <table class="table ">
                                        <thead>
                                            <tr>
                                                <th scope="col">Supplier ID</th>
                                                <th scope="col">Name</th>
                                                <th scope="col">Phone</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">Address</th>
                                                <th scope="col">Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="supplier" items="${supplier}">
                                                <tr>
                                                    <td>${supplier.supplierId}</td>
                                                    <td>${supplier.name}</td>
                                                    <td>${supplier.phone}</td>
                                                    <td>${supplier.email}</td>
                                                    <td>${supplier.address}</td>
                                                    <td>
                                                        <!-- Edit Supplier Button with data attributes -->
                                                        <button class="btn btn-primary btn-sm" 
                                                                data-supplier-id="${supplier.supplierId}"
                                                                data-supplier-name="${supplier.name}"
                                                                data-supplier-number="${supplier.phone}"
                                                                data-supplier-email="${supplier.email}"
                                                                data-supplier-address="${supplier.address}"
                                                                data-supplier-moreInfo="${supplier.moreInfo}"
                                                                data-bs-toggle="modal"
                                                                data-bs-target="#editProductModal"
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
                <%--Modal edit --%>
                <div class="modal fade" id="editProductModal" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
                    <div class="modal-dialog" style="margin-top: 70px;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addProductModalLabel">Edit Supplier</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form id="editSupplierForm" action="${pageContext.request.contextPath}/admin/suppliers" method="POST">
                                    <input type="hidden" name="action" value="edit">
                                    <input type="hidden" name="supplierId" id="supplierId">

                                    <div class="form-group">
                                        <label for="supplierName">Tên khách hàng</label>
                                        <input type="text" class="form-control" id="supplierName" name="supplierName" placeholder="Enter supplier name" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="supplierEmail">Email</label>
                                        <input type="email" class="form-control" id="supplierEmail" name="supplierEmail" placeholder="Enter email" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="supplierAddress">Địa chỉ</label>
                                        <input type="text" class="form-control" id="supplierAddress" name="supplierAddress" placeholder="Enter Address" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="supplierPhone">Số điện thoại</label>
                                        <input type="text" class="form-control" id="supplierPhone" name="supplierPhone" pattern="\d{10}" title="Không đúng định dạng sđt" placeholder="Enter Phone Number" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="supplierInfo">Thông tin thêm</label>
                                        <textarea class="form-control" id="supplierInfo" name="supplierInfo" placeholder="Enter More Info" rows="3"></textarea>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="width: 70px;">Cancel</button>
                                <button type="submit" class="btn btn-success" form="editSupplierForm" style="width: 70px;">Save</button>
                            </div>
                        </div>
                    </div>
                </div>

                <%--Modal add (khác action ở form so với edit thôi) --%>                      
                <div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
                    <div class="modal-dialog" style="margin-top: 70px;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addProductModalLabel">Edit Supplier</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form id="addSupplierForm" action="${pageContext.request.contextPath}/admin/suppliers" method="POST">
                                    <input type="hidden" name="action" value="add">
                                    <div class="form-group">
                                        <label for="supplierName">Tên khách hàng</label>
                                        <input type="text" class="form-control"  name="supplierName" placeholder="Enter supplier name" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="supplierEmail">Email</label>
                                        <input type="email" class="form-control" name="supplierEmail" placeholder="Enter email" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="supplierAddress">Địa chỉ</label>
                                        <input type="text" class="form-control" name="supplierAddress" placeholder="Enter Address" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="supplierPhone">Số điện thoại</label>
                                        <input type="text" class="form-control"  name="supplierPhone" pattern="\d{10}" title="Không đúng định dạng sđt" placeholder="Enter Phone Number" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="supplierInfo">Thông tin thêm</label>
                                        <textarea class="form-control"  name="supplierInfo" placeholder="Enter More Info" rows="3"></textarea>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="width: 70px;">Cancel</button>
                                <button type="submit" class="btn btn-success" form="addSupplierForm" style="width: 70px;">Save</button>
                            </div>
                        </div>
                    </div>
                </div>

                <script>
                    function populateModal(button) {
                        document.getElementById('supplierId').value = button.getAttribute('data-supplier-id');
                        document.getElementById('supplierName').value = button.getAttribute('data-supplier-name');
                        document.getElementById('supplierEmail').value = button.getAttribute('data-supplier-email');
                        document.getElementById('supplierAddress').value = button.getAttribute('data-supplier-address');
                        document.getElementById('supplierPhone').value = button.getAttribute('data-supplier-number');
                        document.getElementById('supplierInfo').value = button.getAttribute('data-supplier-moreInfo');
                    }

                </script>

                <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
                <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
                <script src="../js/bootstrap.bundle.min.js"></script>
                <script src="../js/adminJs/Supplier.js"></script>
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
