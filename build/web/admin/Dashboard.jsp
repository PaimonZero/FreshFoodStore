<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--<link rel="stylesheet" href="../BootstrapCSS/bootstrap.css">-->
    <link rel="stylesheet" href="../css/adminCss/Dashboard.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../admin/HeadSidebar/header-sidebar.css">
    <title>Dashboard</title>
</head>

<body>
    <%@include file="HeadSidebar/sidebar.jsp" %> 
    <%@include file="HeadSidebar/header.jsp" %>
    <div class="scale-container">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2 bg-light">
                    <!-- Sidebar -->
                    <div id="sidebar-container"></div>
                </div>
                <div class="col-md-10 bg-light">
                    <!-- Header -->
                    <div id="header-container"></div>
                    <!-- Main Content -->
                    <div class="row" >
                        <!-- Sales Overview -->
                        <div class="col-md-8">
                            <div class="card">
                                <div class="card-header bg-light" style="text-transform: uppercase; ">Sales Overview</div>
                                <div class="card-body">
                                    <div class="row">
                                        <!-- Column 1 -->
                                        <div class="col-md-3 d-flex flex-column align-items-center justify-content-center" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#5095fc; font-size: 2.5rem;">
                                                <ion-icon name="bar-chart-outline"></ion-icon>
                                            </h5>
                                            <p class="card-text">Sales</p>
                                            <p class="card-text">${totalProductsSold}</p>
                                        </div>
                                        <!-- Column 2 -->
                                        <div class="col-md-3 d-flex flex-column align-items-center justify-content-center" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#817AF3; font-size: 2.5rem;">
                                                <ion-icon name="stats-chart-outline"></ion-icon>
                                            </h5>
                                            <p class="card-text">Revenue</p>
                                            <p class="card-text">${totalRevenue}</p>
                                        </div>
                                        <!-- Column 3 -->
                                        <div class="col-md-3 d-flex flex-column align-items-center justify-content-center" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#DBA362; font-size: 2.5rem;">
                                                <ion-icon name="trending-up-outline"></ion-icon>
                                            </h5>
                                            <p class="card-text">Profit</p>
                                            <p class="card-text">${profit}</p>
                                        </div>
                                        <!-- Column 4 -->
                                        <div class="col-md-3 d-flex flex-column align-items-center justify-content-center">
                                            <h5 class="card-title" style="color:#58D365; font-size: 2.5rem;">
                                                <ion-icon name="cash-outline"></ion-icon>
                                            </h5>
                                            <p class="card-text">Cost</p>
                                            <p class="card-text">${totalCost}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Purchase Overview -->
                        <div class="col-md-4">
                            <div class="card">
                                <div class="card-header bg-light" style="text-transform: uppercase; ">Delivery Summary</div>
                                <div class="card-body">
                                    <div class="row">
                                        <!-- Column 1 -->
                                        <div class="col-md-6 d-flex flex-column align-items-center justify-content-center" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#DBA362; font-size: 2.5rem;">
                                                <ion-icon name="location-outline"></ion-icon>
                                            </h5>
                                            <p class="card-text">Delivered</p>
                                            <p class="card-text">${totalDeliveredOrders}</p>
                                        </div>
                                        <!-- Column 2 -->
                                        <div class="col-md-6 d-flex flex-column align-items-center justify-content-center">
                                            <h5 class="card-title" style="color:#817AF3; font-size: 2.5rem;">
                                                <ion-icon name="close-outline"></ion-icon>
                                            </h5>
                                            <p class="card-text">Canceled</p>
                                            <p class="card-text">${totalCanceledOrders}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Purchase Overview -->
                        <div class="col-md-8">
                            <div class="card">
                                <div class="card-header bg-light" style="text-transform: uppercase; ">Purchase Overview</div>
                                <div class="card-body">
                                    <div class="row">
                                        <!-- Column 1 -->
                                        <div class="col-md-3 d-flex flex-column align-items-center justify-content-center" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#5095fc; font-size: 2.5rem;">
                                                <ion-icon name="bag-outline"></ion-icon></h5>
                                            <p class="card-text">Products</p>
                                            <p class="card-text">${totalProducts}</p>
                                        </div>
                                        <!-- Column 2 -->
                                        <div class="col-md-3 d-flex flex-column align-items-center justify-content-center" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#58D365; font-size: 2.5rem;">
                                                <ion-icon name="cube-outline"></ion-icon>
                                            </h5>
                                            <p class="card-text">Quantity</p>
                                            <p class="card-text">${totalQuantity}</p>
                                        </div>
                                        <!-- Column 3 -->
                                        <div class="col-md-3 d-flex flex-column align-items-center justify-content-center" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#817AF3; font-size: 2.5rem;">
                                                <ion-icon name="cash-outline"></ion-icon></h5>
                                            <p class="card-text">Input Price</p>
                                            <p class="card-text">${totalInputPrice}</p>
                                        </div>
                                        <!-- Column 4 -->
                                        <div class="col-md-3 d-flex flex-column align-items-center justify-content-center">
                                            <h5 class="card-title" style="color:#DBA362; font-size: 2.5rem;">
                                                <ion-icon name="receipt-outline"></ion-icon>
                                            </h5>
                                            <p class="card-text">Receipt</p>
                                            <p class="card-text">${totalReceipts}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Product Summary -->
                        <div class="col-md-4">
                            <div class="card">
                                <div class="card-header bg-light" style="text-transform: uppercase; ">Supplier Summary</div>
                                <div class="card-body">
                                    <div class="row">
                                        <!-- Column 1 -->
                                        <div class="col-md-6 d-flex flex-column align-items-center justify-content-center" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#24B8F1; font-size: 2.5rem;">
                                                <ion-icon name="person-circle-outline"></ion-icon>                                            </h5>
                                            <p class="card-text">Suppliers</p>
                                            <p class="card-text">${totalSuppliers}</p>
                                        </div>
                                        <!-- Column 2 -->
                                        <div class="col-md-6 d-flex flex-column align-items-center justify-content-center">
                                            <h5 class="card-title" style="color:#817AF3; font-size: 2.5rem;">
                                                <ion-icon name="reader-outline"></ion-icon>                                            </h5>
                                            <p class="card-text">Categories</p>
                                            <p class="card-text">${totalCategories}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>

                    <!-- Profit and Revenue Charts -->
                    <div class="row">
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="card-title mb-4">Sales Overview Chart</h3>
                                    <canvas id="salesChart"></canvas>
                                </div>
                            </div>
                        </div>  

                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="card-title mb-4">Order Summary</h3>
                                    <canvas id="orderChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Best Selling Product Section -->
                    <div class="row">
                        <div class="col-md-8">
                            <div class="card text-dark bg-light d-flex mb-3">
                                <div class="card-header bg-light d-flex align-items-center justify-content-between">
                                    <h4 class="mb-0" style="font-weight: bold;">Top Selling Product</h4>
                                    <div>
                                        <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#allProductsModal">See All</button>
                                    </div>
                                </div>
                                <div class="card-body" style="height: auto; overflow-y: auto;">
                                    <table class="table ">
                                        <thead>
                                            <tr>
                                                <th scope="col">Name</th>
                                                <th scope="col">Sold Quantity</th>
                                                <th scope="col">Remaining Quantity</th>
                                                <th scope="col">Price</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${topSellingProducts}" var="product" varStatus="status">
                                                <c:if test="${status.index < 4}"> <!-- Only show the first 4 products -->
                                                    <tr>
                                                        <td>${product.productName}</td>
                                                        <td>${product.totalQuantitySold}</td>
                                                        <td>${product.remainingQuantity}</td>
                                                        <td>
                                                            <fmt:formatNumber value="${product.price}" pattern="#,###" />
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card text-dark bg-light d-flex mb-3">
                                <div class="card-header bg-light d-flex align-items-center justify-content-between">
                                    <h4 class="mb-0" style="font-weight: bold;">Low Quantity Stock</h4>
                                    <div>
                                        <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#seeAllModal">See All</button>
                                    </div>
                                </div>
                                <div class="card-body" style="height: 250px; overflow-y: auto;">
                                    <div class="container">
                                        <!-- Dynamic Product Items -->
                                        <c:forEach var="product" items="${leastStockedProducts}" varStatus="status">
                                            <c:if test="${status.index < 4}">
                                                <div class="row align-items-center py-3 border-bottom">
                                                    <!-- Product Image -->
                                                    <div class="col-2">
                                                        <img src="${product.productImage}" alt="Product Image" class="img-fluid rounded">
                                                    </div>
                                                    <!-- Product Info -->
                                                    <div class="col-8">
                                                        <h5 class="mb-1 font-weight-bold">${product.productName}</h5>
                                                        <p class="text-muted mb-0">Remaining Quantity:</p>
                                                        <p class="text-muted mb-0">${product.quantityRemaining} packed</p>
                                                    </div>
                                                    <!-- Product Status -->
                                                    <div class="col-2 text-end">
                                                        <span class="badge rounded-pill bg-danger text-white p-2">Low</span>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal for displaying all products -->
    <div class="modal fade" id="allProductsModal" tabindex="-1" aria-labelledby="allProductsModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="allProductsModalLabel">All Top Selling Products</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Sold Quantity</th>
                                <th scope="col">Remaining Quantity</th>
                                <th scope="col">Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${topSellingProducts}" var="product">
                                <tr>
                                    <td>${product.productName}</td>
                                    <td>${product.totalQuantitySold}</td>
                                    <td>${product.remainingQuantity}</td>
                                    <td>
                                        <fmt:formatNumber value="${product.price}" pattern="#,###" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal for "See All" -->
    <div class="modal fade" id="seeAllModal" tabindex="-1" aria-labelledby="seeAllModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="seeAllModalLabel">All Low Quantity Products</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="container">
                        <c:forEach var="product" items="${leastStockedProducts}" varStatus="status">
                            <c:if test="${status.index < 10}">
                            <div class="row align-items-center py-3 border-bottom">
                                <div class="col-2">
                                    <img src="${product.productImage}" alt="Product Image" class="img-fluid rounded">
                                </div>
                                <div class="col-8">
                                    <h5 class="mb-1 font-weight-bold">${product.productName}</h5>
                                    <p class="text-muted mb-0">Remaining Quantity:</p>
                                    <p class="text-muted mb-0">${product.quantityRemaining} packed</p>
                                </div>
                                <div class="col-2 text-end">
                                    <span class="badge rounded-pill bg-danger text-white p-2">Low</span>
                                </div>
                            </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <!-- Bootstrap JS -->
    <!--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>-->
    <script src="../js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <!-- Custom JS -->
    <script src="../admin/HeadSidebar/MenuButton.js"></script>
    <script src="../js/adminJs/Dashboard.js"></script>
    <script src="../admin/HeadSidebar/SideBar.js"></script>

    <script>

    </script>

</body>

</html>