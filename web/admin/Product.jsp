<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dal.ProductDAO"%>
<%@page import="model.Products"%>
<%@page import="model.Supplier"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/adminCss/Product.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../admin/HeadSidebar/header-sidebar.css">
    <title>Product</title>
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

                    <%
                        ProductDAO productDao = new ProductDAO();
                        int totalProducts = productDao.calculateTotalProducts();
                        int totalPromos = productDao.calculateTotalPromos();
                        int totalLow = productDao.calculateLowStock();
                         int totalOut = productDao.calculateOutOfStock();

                    %>
                    <div class="row mt-4">
                        <div class="col-md-12">
                            <div class="card text-dark bg-light d-flex mb-3">
                                <h4 class="card-header bg-light" style="font-weight: bold;">Overall Inventory</h4>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-3" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#00BA1E;">Total product</h5>
                                            <div class="d-flex justify-content-between">
                                                <p class="card-text"><%= totalProducts %></p>
                                                <p class="card-text">Products</p>
                                            </div>
                                        </div>
                                        <div class="col-md-3" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#845EBC;">Current Promotions</h5>

                                            <div class="d-flex justify-content-between">
                                                <p class="card-text"><%= totalPromos %></p>
                                                <p class="card-text">Products</p>
                                            </div>
                                        </div>
                                        <div class="col-md-3" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#E19133">Low Stock</h5>
                                            <div class="d-flex justify-content-between">
                                                <p class="card-text"><%= totalLow %></p>
                                                <p class="card-text">Products</p>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <h5 class="card-title" style="color:#F36960">Out of stock</h5>
                                            <div class="d-flex justify-content-between">
                                                <p class="card-text"><%= totalOut %></p>
                                                <p class="card-text">Products</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="card text-dark bg-light d-flex mb-3">
                                <div class="card-header bg-light d-flex align-items-center justify-content-between">
                                    <h4 class="mb-0" style="font-weight: bold;">Product</h4>
                                    <!--                                    <div>
                                                                                                                    <button class="btn btn-sm btn-outline-success" data-bs-toggle="modal"
                                                                                                                            data-bs-target="#addProductModal"style="width: 96px;">Add Product</button>
                                    
                                                                            <button class="btn btn-sm btn-outline-secondary"style="width: 70px;">Filter</button>
                                                                            <button class="btn btn-sm btn-outline-secondary"style="width: 96px;">Dowload All</button>
                                                                        </div>-->
                                </div>
                                <div class="card-body" style="height: auto; ">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th scope="col">IdProduct</th>
                                                <th scope="col">Products</th>
                                                <th scope="col">Quantity</th>
                                                <th scope="col">Unit Measue</th>
                                                <th scope="col">Promotion</th>
                                                <th scope="col">Selling Price</th>
                                                <th scope="col">Availability</th>
                                                <th scope="col"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                
                                                List<Products> productList = productDao.getAllProducts();
                                                for (Products product : productList) {
                                            %>
                                            <%
                                                NumberFormat formatter = NumberFormat.getInstance(Locale.US); // or any locale you prefer
                                            %>

                                            <tr style="cursor: pointer;" onclick="window.location.href = 'ProductInfo.jsp?productId=<%= product.getProductId() %>'">
                                        <input type="hidden" value="<%= product.getProductId()%>">
                                        <td><%= product.getProductId() %></td>
                                        <td><%= product.getName() %></td>
                                        <td>
                                            <span class="badge 
                                                  <c:choose>
                                                      <c:when test="<%= product.getQuantity() == 0 %>">bg-danger text-white</c:when>
                                                      <c:when test="<%= product.getQuantity() <21 %>">bg-warning text-white</c:when>
                                                      <c:otherwise>bg-success</c:otherwise>
                                                  </c:choose>">
                                                <%= product.getQuantity() %>
                                            </span>
                                        </td>

                                        <td><%= product.getUnitMeasure() %></td>
                                        <td>
                                            <%= product.getPromotionStatus() %>
                                        </td>
                                        <td><%= formatter.format(product.getUnitPrice()) %></td>
                                        <td><%= product.getStatus() %></td>
                                        <td>
                                            <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal"
                                                    data-bs-target="#editProductModal">
                                                <ion-icon name="pencil-outline"></ion-icon>
                                            </button>
                                        </td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                        </tbody>
                                    </table>

                                </div>
                                <div class="card-footer d-flex justify-content-between"
                                     style="bottom: 0; background-color: white;">
                                    <button class="btn btn-outline-secondary"style="width: 100px;">Previous</button>
                                    <span class="mx-3">Page 1 of 1</span>
                                    <button class="btn btn-outline-secondary"style="width: 100px;">Next</button>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
                <!-- Modal -->
                <div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title text-center w-100" id="exampleModalLabel">Add Product</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form action="AddProductServlet" method="post">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <!-- Left Column -->
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
                                            <button type="submit" class="btn btn-primary">Add Product</button>
                                        </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>


    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js">
    </script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap.js"></script>
    <!--<script src="../Style/js/Product.js"></script>-->
    <script src="../admin/HeadSidebar/MenuButton.js"></script>
    <script src="../admin/HeadSidebar/SideBar.js"></script>

</body>

</html>