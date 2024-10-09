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

                                    <%
                                        // Lấy productId từ query string
                                        int productId = Integer.parseInt(request.getParameter("productId"));
                                    
                                        // Tạo ProductInfoDao và lấy thông tin sản phẩm
                                        ProductDAO productDAO = new ProductDAO();
                                        ProductDTO productInfo = productDAO.getProductInfoById(productId); 
                                    %>

                                    <div class="card-header bg-light d-flex align-items-center justify-content-between">
                                        <h4 class="mb-0" style="font-weight: bold;"><%= productInfo.getProductName() %></h4>
                                        <div>
                                            <button class="btn btn-sm btn-outline-success" data-bs-toggle="modal" 
                                                    data-bs-target="#editProductModal" style="width: 105px;">Edit Product</button>

                                            <button class="btn btn-sm btn-outline-danger" data-bs-toggle="modal"
                                                    data-bs-target="#deleteConfirmationModal"  style="width: 105px;">Delete</button>

                                        </div>
                                    </div>

                                    <!-- Start of the Content Section with 2 Columns -->
                                    <div class="card-body">
                                        <div class="row">
                                            <!-- Column for Product Details and Information -->
                                            <div class="col-md-7">
                                                <h4 class="mb-4" style="text-align: left; text-decoration: underline; text-decoration-color: #27ae60;">Overview</h4>
                                                <h5 class="mb-4" style="text-align: left;">Chi tiết sản phẩm</h5>

                                                <div class="row mb-4">
                                                    <div class="col-md-12">
                                                        <table class="table table-borderless" style="width: 100%; border-collapse: separate; border-spacing: 0 10px;">
                                                            <tbody>
                                                                <tr>
                                                                    <td style="padding: 10px 10px;"><strong>Tên sản phẩm:</strong></td>
                                                                    <td style="padding: 10px 10px;"><%= productInfo.getProductName() %></td>
                                                                </tr>
                                                                <tr>
                                                                    <td style="padding: 10px 10px;"><strong>Mã sản phẩm:</strong></td>
                                                                    <td style="padding: 10px 10px;"><%= productInfo.getProductId() %></td>
                                                                </tr>
                                                                <tr>
                                                                    <td style="padding: 10px 10px;"><strong>Danh mục sản phẩm:</strong></td>
                                                                    <td style="padding: 10px 10px;"><%= productInfo.getCategoryName() %></td>
                                                                </tr>
                                                                <tr>
                                                                    <td style="padding: 10px 10px;"><strong>Đơn vị tính:</strong></td>
                                                                    <td style="padding: 10px 10px;"><%= productInfo.getUnitMeasure() %></td>
                                                                </tr>
                                                                <tr>
                                                                    <td style="padding: 10px 10px;"><strong>Giá bán ra:</strong></td>
                                                                    <td style="padding: 10px 10px;"><%= productInfo.getUnitPrice() %> VND</td>
                                                                </tr>
                                                                <tr>
                                                                    <td style="padding: 10px 10px;"><strong>Mô tả</strong></td>
                                                                    <td style="padding: 10px 10px;"><%= productInfo.getDescription() %></td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>

                                                <div class="supplier-info mb-4">
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
                                                </div>

                                                <!-- Các phần thông tin khác có thể được thêm sau -->
                                                <div class="batch-info mb-4">
                                                    <h2 class="h5">Các lô hàng hiện có</h2>
                                                    <table class="table table-bordered text-center">
                                                        <thead>
                                                            <tr>
                                                                <th>Mã lô hàng</th>
                                                                <th>Ngày nhập</th>
                                                                <th>Ngày hết hạn</th>
                                                                <th>Số lượng hiện có</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <%
                                                                // Lấy danh sách các lô hàng từ ProductInfo
                                                                List<BatchProduct> batchProducts = productInfo.getBatchProducts();
                                                                if (batchProducts != null && !batchProducts.isEmpty()) {
                                                                    for (BatchProduct batchProduct : batchProducts) {
                                                            %>
                                                            <tr>
                                                                <td><%= batchProduct.getBatchId() %></td>
                                                                <td><%= batchProduct.getCreatedAt() %></td>
                                                                <td><%= batchProduct.getExpiryDate() %></td>
                                                                <td><%= batchProduct.getQuantity() %></td>
                                                            </tr>
                                                            <%
                                                                    }
                                                                } else {
                                                            %>
                                                            <tr>
                                                                <td colspan="4">Không có lô hàng nào.</td>
                                                            </tr>
                                                            <%
                                                                }
                                                            %>
                                                        </tbody>
                                                    </table>
                                                </div>

                                                <div class="promotion-info">
                                                    <h2 class="h5">Chương trình khuyến mãi</h2>
                                                    <table class="table table-bordered text-center">
                                                        <thead>
                                                            <tr>
                                                                <th>Id khuyến mãi</th>
                                                                <th>Ngày bắt đầu</th>
                                                                <th>Ngày kết thúc</th>
                                                                <th class="no-border-right">Chiết khấu (%)</th>
                                                                <th class="no-border-left"></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <%
                                                                // Lấy danh sách các chương trình khuyến mãi từ ProductInfo
                                                                List<Promos> promosList = productInfo.getPromosList();
                                                                if (promosList != null && !promosList.isEmpty()) {
                                                                    for (Promos promo : promosList) {
                                                            %>
                                                            <tr>
                                                                <td><%= promo.getPromotionId() %></td>
                                                                <td><%= promo.getStartDate() %></td>
                                                                <td><%= promo.getEndDate() %></td>
                                                                <td><%= promo.getDiscount() %></td>
                                                                <td>
                                                                    <button class="btn btn-sm btn-outline-secondary">
                                                                        <ion-icon name="pencil-outline"></ion-icon>
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                            <%
                                                                    }
                                                                } else {
                                                            %>
                                                            <tr>
                                                                <td colspan="5">Không có chương trình khuyến mãi nào.</td>
                                                            </tr>
                                                            <%
                                                                }
                                                            %>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            <div class="col-md-1"></div>
                                            <!-- Column for Product Image -->
                                            <div class="col-md-4">
                                                <div class="d-flex flex-column align-items-center">
                                                    <div class="product-image mb-2">
                                                        <img src="image.png" alt="Sản phẩm" class="img-fluid">
                                                    </div>
                                                    <div class="product-quantity text-center">
                                                        <p><strong>Hiện có:</strong> 34</p>
                                                        <p><strong>Số lượng hàng trong kho:</strong> 10</p>
                                                    </div>
                                                </div>
                                            </div>
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
        <div class="modal fade" id="deleteConfirmationModal" tabindex="-1" aria-labelledby="deleteConfirmationModalLabel" aria-hidden="true">
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
                            <input type="hidden" name="productId" value="<%= productInfo.getProductId() %>">
                            <button type="submit" class="btn btn-danger">Xóa</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <%
                           ProductDAO productDao = new ProductDAO();
        %>
        <div class="modal fade" id="editProductModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title text-center w-100" id="exampleModalLabel">Update Product</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="UpdateProductServlet" method="post">
                            <div class="container-fluid">
                                <div class="row">
                                    <!-- Left Column -->
                                    <input type="hidden" name="productId" value="<%= productInfo.getProductId() %>">
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="productName" class="form-label">Product Name</label>
                                            <input type="text" class="form-control" id="productName" name="name" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="unitMeasure" class="form-label">Unit Measure</label>
                                            <input type="text" class="form-control" id="unitMeasure" name="unitMeasure" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="supplierSelect" class="form-label">Supplier</label>
                                            <select class="form-select" id="supplierSelect" name="supplierId" required>
                                                <option selected disabled value="">Choose a Supplier</option>
                                                <%-- Loop through suppliers --%>
                                                <% List<Supplier> suppliers = productDao.getAllSuppliers();
                                           for (Supplier supplier : suppliers) { %>
                                                <option value="<%= supplier.getSupplierId() %>"><%= supplier.getName() %></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="categorySelect" class="form-label">Category</label>
                                            <select class="form-select" id="categorySelect" name="categoryId" required>
                                                <option selected disabled value="">Choose a Category</option>
                                                <%-- Loop through categories --%>
                                                <% List<Category> categories = productDao.getAllCategories();
                                           for (Category category : categories) { %>
                                                <option value="<%= category.getCategoryId() %>"><%= category.getName() %></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="expirationDate" class="form-label">Expiration Date</label>
                                            <input type="date" class="form-control" id="expirationDate" name="expirationDate" required>
                                        </div>
                                         <div class="mb-3">
                                            <label for="unitPrice" class="form-label">Quantity</label>
                                            <input type="number" class="form-control" id="quantity" name="quantity" required>
                                        </div>
                                    </div>

                                    <!-- Right Column -->
                                    <div class="col-md-6">
                                        <div class="mb-3 text-center">
                                            <label for="imageUpload" class="form-label">Upload Image</label>
                                            <div class="image-upload">
                                                <label for="imageFile" class="image-text">Click to upload image</label>
                                                <input type="file" class="form-control" id="imageFile" name="image">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="mb-3">
                                            <label for="description" class="form-label">Description</label>
                                            <textarea class="form-control" id="description" name="description" required></textarea>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="unitPrice" class="form-label">Unit Price</label>
                                            <input type="number" class="form-control" id="unitPrice" name="unitPrice" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label for="status" class="form-label">Status</label>
                                            <select class="form-select" id="status" name="status" required>
                                                <option value="Available">Available</option>
                                                <option value="Unavailable">Unavailable</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary">Update Product</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/bootstrap.js"></script>
        <script src="../admin/HeadSidebar/MenuButton.js"></script>
        <script src="../admin/HeadSidebar/SideBar.js"></script>

    </body>

</html>
