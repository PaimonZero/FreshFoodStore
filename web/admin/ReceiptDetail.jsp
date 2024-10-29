<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.BatchProduct"%>
<%@page import="model.Promos"%>
<%@page import="dto.ProductDTO"%>

<%@page import="model.Products"%>
<%@page import="model.Supplier"%>
<%@page import="model.Category"%>
<%@page import="dal.ProductDAO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../css/bootstrap.css">
        <link rel="stylesheet" href="../css/adminCss/ProductInfo.css">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="../admin/HeadSidebar/header-sidebar.css">
        <title>Product Information</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.min.js"></script>
        <style>
            .suggestions {
                max-height: 150px;
                overflow-y: auto;
                width: 92%;
                position: absolute;
                z-index: 99;
                background-color: #fff;
                display: none; /* Mặc định ẩn */
            }
            .suggestions.show {
                display: block; /* Hiện khi có gợi ý */
                border: 1px solid gray;
                border-radius: 10px;
            }
            .suggestion-item {
                padding: 8px;
                cursor: pointer;
            }
            .suggestion-item:hover {
                background-color: #f0f0f0;
            }
        </style>
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
                        <div id="header-container"></div>

                        <div class="row mt-4">
                            <div class="col-md-12">
                                <div class="card text-dark bg-light d-flex mb-3">

                                    <div class="card-header bg-light d-flex align-items-center justify-content-between">
                                        <h4 class="mb-0" style="font-weight: bold;">Đơn nhập hàng số ${receiptInfo.receiptId}</h4>
                                        <!--                                        <div>
                                                                                    <button class="btn btn-sm btn-outline-success" data-bs-toggle="modal" 
                                                                                            data-bs-target="#editProductModal" style="width: 105px;">Edit Product</button>
                                        
                                                                                    <button class="btn btn-sm btn-outline-danger" data-bs-toggle="modal"
                                                                                            data-bs-target="#deleteConfirmationModal"  style="width: 105px;">Delete</button>
                                                                                </div>-->
                                    </div>

                                    <!-- Start of the Content Section with 2 Columns -->
                                    <div class="card-body">
                                        <div class="row">
                                            <!-- Column for Product Details and Information -->
                                            <div class="col-md-12">
                                                <h4 class="mb-4" style="text-align: left; text-decoration: underline; text-decoration-color: #27ae60;">Overview</h4>
                                                <h5 class="mb-4" style="text-align: left;">Tông tin đơn nhập số ${receiptInfo.receiptId}</h5>

                                                <div class="row mb-4">
                                                    <div class="col-md-12">
                                                        <table class="table table-borderless" style="width: 100%; border-collapse: separate; border-spacing: 0 10px;">
                                                            <tbody>
                                                                <tr>
                                                                    <td style="padding: 10px 10px;"><strong>Tên nhà cung cấp:</strong></td>
                                                                    <td style="padding: 10px 10px;">${receiptInfo.supplierName}</td>
                                                                </tr>
                                                                <tr>
                                                                    <td style="padding: 10px 10px;"><strong>Ngày nhập</strong></td>
                                                                    <td style="padding: 10px 10px;"><fmt:formatDate value="${receiptInfo.inputDate}" pattern="dd/MM/yyyy" /></td>
                                                                </tr>
                                                                <tr>
                                                                    <td style="padding: 10px 10px;"><strong>Tổng giá trị đơn nhập: </strong></td>
                                                                    <td style="padding: 10px 10px;"><fmt:formatNumber value="${receiptInfo.totalPrice}" pattern="#,###" /></td>
                                                                </tr>
                                                                <tr>
                                                                    <td style="padding: 10px 10px;"><strong>Tổng số loại sản phẩm nhập: </strong></td>
                                                                    <td style="padding: 10px 10px;">${receiptInfo.productTypes}</td>
                                                                </tr>
                                                                <tr>
                                                                    <td style="padding: 10px 10px;"><strong>Tổng số lượng sản phẩm nhập: </strong></td>
                                                                    <td style="padding: 10px 10px;">${receiptInfo.totalQuantity}</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>

                                                <%--  <div class="supplier-info mb-4">
                                                          <h2 class="h5">Thông tin nhà cung cấp</h2>
                                                          <div class="col-md-12">
                                                              <table class="table table-borderless" style="width: 100%; border-collapse: separate; border-spacing: 0 10px;">
                                                                  <tbody>
                                                                      <tr>
                                                                          <td style="padding: 10px 10px;"><strong>Tên Nhà Cung Cấp</strong></td>
                                                                          <td style="padding: 10px 10px;"><%= productInfo.getSupplierName() %></td>
                                                                      </tr>
                                                                      <tr>
                                                                          <td style="padding: 10px 10px;"><strong>Số điện thoại</strong></td>
                                                                          <td style="padding: 10px 10px;"><%= productInfo.getSupplierPhone() %></td>
                                                                      </tr>
                                                                  </tbody>
                                                              </table>
                                                          </div>
                                                      </div> --%>

                                                <!-- Các phần thông tin khác có thể được thêm sau -->
                                                <div class="batch-info mb-4">
                                                    <div class="row">
                                                        <div class="col-7">
                                                            <h2 class="h5">Thông tin đơn nhập: </h2>
                                                        </div>
                                                        <div class="col-5">
                                                            <button class="btn btn-sm btn-outline-success" data-bs-toggle="modal" 
                                                                    data-bs-target="#addPrduct01" style="margin-bottom: 0.5rem">Thêm sản phẩm đã có</button>

                                                            <button class="btn btn-sm btn-outline-warning" data-bs-toggle="modal" 
                                                                    data-bs-target="#addPrduct02" style="margin-bottom: 0.5rem"><strong>Thêm sản phẩm mới</strong></button>
                                                        </div>
                                                    </div>
                                                    <table class="table table-bordered text-center">
                                                        <thead>
                                                            <tr>
                                                                <th>ID_ReceiptDetail</th>
                                                                <th>Mã sản phẩm</th>
                                                                <th>Tên sản phẩm</th>
                                                                <th>Số lượng nhập</th>
                                                                <th>Giá nhập</th>
                                                                <th>Ngày hết hạn</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="receiptDe" items="${listReDetail}">
                                                                <tr>
                                                                    <td>${receiptDe.receiptDetailId}</td>
                                                                    <td>${receiptDe.productId}</td>
                                                                    <td>${receiptDe.productName}</td>
                                                                    <td>${receiptDe.quantity}</td>
                                                                    <td><fmt:formatNumber value="${receiptDe.inputPrice}" pattern="#,###" /></td>
                                                                    <td><fmt:formatDate value="${receiptDe.expiryDate}" pattern="dd/MM/yyyy" /></td>
                                                                </tr>
                                                            </c:forEach>
                                                            <c:if test="${empty listReDetail}">
                                                                <tr>
                                                                    <td colspan="6" class="text-center">Không có chi tiết đơn nhập nào!</td>
                                                                </tr>
                                                            </c:if>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <%-- Thông tin listBatches --%>
                                                <div class="batch-info mb-4">
                                                    <div class="row">
                                                        <div class="col-7">
                                                            <h2 class="h5">Thông tin các lô hàng liên quan: </h2>
                                                        </div>
                                                        <!--                                                        <div class="col-5">
                                                                                                                    <button class="btn btn-sm btn-outline-success" data-bs-toggle="modal" 
                                                                                                                            data-bs-target="#addPrduct01" style="margin-bottom: 0.5rem">Thêm sản phẩm đã có</button>
                                                        
                                                                                                                    <button class="btn btn-sm btn-outline-warning" data-bs-toggle="modal" 
                                                                                                                            data-bs-target="#addPrduct02" style="margin-bottom: 0.5rem"><strong>Thêm sản phẩm mới</strong></button>
                                                                                                                </div>-->
                                                    </div>
                                                    <table class="table table-bordered text-center">
                                                        <thead>
                                                            <tr>
                                                                <th>Mã lô hàng</th>
                                                                <th>Tên sản phẩm</th>
                                                                <th>Số lượng</th>
                                                                <th>Giá bán</th>
                                                                <th>Ngày hết hạn</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="bat" items="${listBatches}">
                                                                <tr>
                                                                    <td>${bat.batchId}</td>
                                                                    <td>${bat.productName}</td>
                                                                    <td>${bat.quantity}</td>
                                                                    <td><fmt:formatNumber value="${bat.unitPrice}" pattern="#,###" /></td>
                                                                    <td><fmt:formatDate value="${bat.expiryDate}" pattern="dd/MM/yyyy" /></td>
                                                                </tr>
                                                            </c:forEach>
                                                            <c:if test="${empty listBatches}">
                                                                <tr>
                                                                    <td colspan="6" class="text-center">Không có lô hàng nào</td>
                                                                </tr>
                                                            </c:if>
                                                        </tbody>
                                                    </table>

                                                </div>
                                            </div>
                                            <!-- Column for Product Image -->
                                            <!--                                            <div class="col-md-4">
                                                                                            <div class="d-flex flex-column align-items-center">
                                                                                                <div class="product-image mb-2">
                                                                                                    <img src="image.png" alt="Sản phẩm" class="img-fluid">
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>-->
                                        </div>
                                    </div>
                                    <!-- End of the Content Section -->
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <%--        <div class="modal fade" id="deleteConfirmationModal" tabindex="-1" aria-labelledby="deleteConfirmationModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="deleteConfirmationModalLabel">Xác nhận xóa sản phẩm</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Bạn có chắc chắn muốn xóa sản phẩm này không?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                <form action="DeleteProductServlet" method="post">
                                    <input type="hidden" name="productId" value="${receiptInfo.receiptId}">
                                    <button type="submit" class="btn btn-danger">Xóa</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>--%>

        <div class="modal fade" id="addPrduct01" tabindex="-1" aria-labelledby="promoModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="promoModalLabel">Thêm thông tin cho sản phẩm đã từng nhập</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="promoForm" action="receipts?action=addDetailOld" method="POST">
                            <input type="hidden" name="receiptId" value="${receiptInfo.receiptId}">

                            <div class="mb-3">
                                <label for="sanPham" class="form-label">Chọn sản phẩm</label>
                                <select id="sanPham" name="productId" class="js-select2 form-select" required>
                                    <option selected disabled value="">Chọn sản phẩm</option>
                                    <c:forEach var="pd" items="${listProducts}">
                                        <option value="${pd.productId}">${pd.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label for="quantity" class="form-label">Số lượng nhập</label>
                                <input type="number" class="form-control" id="quantity" name="quantity" min="1" required>
                            </div>

                            <div class="mb-3">
                                <label for="inputPrice" class="form-label">Giá nhập</label>
                                <input type="number" class="form-control" id="inputPrice" name="inputPrice" min="1000" required>
                            </div>
                            <div class="mb-3">
                                <label for="expiryDate" class="form-label">Ngày hết hạn</label>
                                <input type="date" class="form-control" id="expiryDate" name="expiryDate" required>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <button type="submit" class="btn btn-primary" form="promoForm">Lưu thay đổi</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="addPrduct02" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title text-center w-100" id="exampleModalLabel">Thêm thông tin cho sản phẩm mới nhập</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="promoForm2" action="receipts?action=addDetailNew" method="POST" enctype="multipart/form-data">
                            <input type="hidden" name="supplierId" value="${receiptInfo.supplierId}"/>
                            <input type="hidden" name="receiptId" value="${receiptInfo.receiptId}"/>

                            <div class="container-fluid">
                                <div class="row">
                                    <!-- Left Column -->
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="productName" class="form-label">Tên sản phẩm mới</label>
                                            <input type="text" class="form-control" id="productName" name="name" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="unitMeasure" class="form-label">Đơn vị tính</label>
                                            <input type="text" class="form-control" id="unitMeasure" name="unitMeasure" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="categorySelect" class="form-label">Danh mục sản phẩm</label>
                                            <select class="form-select" id="categorySelect" name="categoryId" required>
                                                <option selected disabled value="">Choose a Category</option>
                                                <!-- Dùng JSTL để lặp qua danh sách categories -->
                                                <c:forEach var="category" items="${categories}">
                                                    <option value="${category.categoryId}">${category.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <!-- Right Column -->
                                    <div class="col-md-6">
                                        <div class="mb-3 text-center">
                                            <label for="imageUpload" class="form-label">Upload Image</label>
                                            <div class="image-upload">
                                                <label for="imageFile" class="image-text">Click to upload image</label>
                                                <input type="file" class="form-control" id="imageFile" name="file" accept="image/*" multiple>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="mb-3">
                                            <label for="description" class="form-label">Thông tin mô tả</label>
                                            <textarea class="form-control" id="description" name="description" required></textarea>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="unitPrice" class="form-label">Giá bán ra</label>
                                            <input type="number" class="form-control" id="unitPrice" name="unitPrice" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="status" class="form-label">Status</label>
                                            <select class="form-select" id="status" name="status" required>
                                                <option value="In Stock">In Stock</option>
                                                <option value="Out of stock">Out of stock</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="quantity2" class="form-label">Số lượng nhập</label>
                                            <input type="number" class="form-control" id="quantity2" name="quantity" min="1" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="inputPrice2" class="form-label">Giá nhập</label>
                                            <input type="number" class="form-control" id="inputPrice2" name="inputPrice" min="1000" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="expiryDate2" class="form-label">Ngày hết hạn</label>
                                            <input type="date" class="form-control" id="expiryDate2" name="expiryDate" required>
                                        </div>
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                    <button type="submit" class="btn btn-primary" form="promoForm2">Lưu thay đổi</button>
                                </div>
                            </div>
                        </form>
                    </div> 
                </div>
            </div>
        </div>

        <script>
            //            function populateModal(button) {
            //                document.getElementById('productId').value = button.getAttribute('data-productId');
            //                document.getElementById('promoId').value = button.getAttribute('data-promotionId');
            //                document.getElementById('startDate').value = button.getAttribute('data-startDate');
            //                document.getElementById('endDate').value = button.getAttribute('data-endDate');
            //                document.getElementById('discount').value = button.getAttribute('data-discount');
            //            }

            $(document).ready(function () {
                // Khởi tạo Select2 khi modal hiển thị
                $('#addPrduct01').on('shown.bs.modal', function () {
                    $('.js-select2').select2({
                        dropdownParent: $('#addPrduct01'), // Đảm bảo dropdown hiển thị trong modal
                        placeholder: "Chọn sản phẩm",
                        allowClear: true
                    });
                });
            });

            // Khởi tạo Select2 sau khi tải trang
            $(document).ready(function () {
                $('.idSelect01').select2();
            });
            // Khởi tạo Select2 sau khi tải trang
            $(document).ready(function () {
                $('.js-example-basic-single').select2();
            });

        </script>                                   

        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/bootstrap.js"></script>
        <script src="../admin/HeadSidebar/MenuButton.js"></script>
        <script src="../admin/HeadSidebar/SideBar.js"></script>
    </body>

</html>
