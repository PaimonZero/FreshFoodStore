<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--<link rel="stylesheet" href="../BootstrapCSS/bootstrap.css">-->
    <link rel="stylesheet" href="../css/adminCss/Inventory.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../admin/HeadSidebar/header-sidebar.css">
    <title>Inventory</title>
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
                                <h4 class="card-header bg-light" style="font-weight: bold;">Overall Inventory</h4>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-3" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#00BA1E;">Total batches</h5>
                                            <p class="card-text">50</p>
                                            <p class="card-text">Last 7 days</p>
                                        </div>
                                        <div class="col-md-3" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#10A760;">Still Valid</h5>

                                            <div class="d-flex justify-content-between">
                                                <p class="card-text">33</p>
                                                <p class="card-text">25000</p>
                                            </div>

                                            <div class="d-flex justify-content-between">
                                                <p class="card-text">Last 7 days</p>
                                                <p class="card-text">Revenue</p>
                                            </div>
                                        </div>
                                        <div class="col-md-3" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#E19133">Expiring Soon</h5>
                                            <div class="d-flex justify-content-between">
                                                <p class="card-text">5</p>
                                                <p class="card-text">2500</p>
                                            </div>

                                            <div class="d-flex justify-content-between">
                                                <p class="card-text">Last 7 days</p>
                                                <p class="card-text">Cost</p>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <h5 class="card-title" style="color:#F36960">Expied</h5>
                                            <div class="d-flex justify-content-between">
                                                <p class="card-text">12</p>
                                                <p class="card-text">2</p>
                                            </div>

                                            <div class="d-flex justify-content-between">
                                                <p class="card-text">Products</p>
                                                <p class="card-text">Expiring soon</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="card text-dark bg-light d-flex mb-3">
                                <div class="card-header bg-light d-flex align-items-center justify-content-between">
                                    <h4 class="mb-0" style="font-weight: bold;">Product Batches</h4>
                                    <div>
                                        
                                        <button class="btn btn-sm btn-outline-secondary"style="width: 105px;">Filter</button>
                                        <button class="btn btn-sm btn-outline-secondary"style="width: 105px;">Dowload All</button>
                                    </div>
                                </div>
                                <div class="card-body" style="height: 340px; overflow-y: auto;">
                                    <table class="table ">
                                        <thead>
                                            <tr>
                                                <th scope="col">IdBatch</th>
                                                <th scope="col">Sản phẩm</th>
                                                <th scope="col">Ngày nhập</th>
                                                <th scope="col">Giá mua vào</th>
                                                <th scope="col">Số lượng hiện tại</th>
                                                <th scope="col">Ngày hết hạn</th>
                                                <th scope="col">Trạng thái</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td>Product A</td>
                                                <td>100</td>
                                                <td>10</td>
                                                <td>In progress</td>
                                                <td>$50</td>
                                                <td>In Stock</td>
                                            </tr>
                                            <tr>
                                                <td>2</td>
                                                <td>Product B</td>
                                                <td>50</td>
                                                <td>5</td>
                                                <td>In progress</td>
                                                <td>$30</td>
                                                <td>In Stock</td>
                                            </tr>
                                            <tr>
                                                <td>3</td>
                                                <td>Product C</td>
                                                <td>0</td>
                                                <td>2</td>
                                                <td>None</td>
                                                <td>$20</td>
                                                <td>Out of Stock</td>
                                            </tr>
                                            <tr>
                                                <td>4</td>
                                                <td>Product D</td>
                                                <td>10</td>
                                                <td>1</td>
                                                <td>None</td>
                                                <td>$10</td>
                                                <td>In Stock</td>
                                            </tr>
                                            <tr>
                                                <td>5</td>
                                                <td>Product E</td>
                                                <td>20</td>
                                                <td>3</td>
                                                <td>None</td>
                                                <td>$15</td>
                                                <td>In Stock</td>
                                            </tr>
                                            <tr>
                                                <td>6</td>
                                                <td>Product F</td>
                                                <td>0</td>
                                                <td>2</td>
                                                <td>None</td>
                                                <td>$20</td>
                                                <td>Out of Stock</td>
                                            </tr>
                                            <tr>
                                                <td>7</td>
                                                <td>Product G</td>
                                                <td>10</td>
                                                <td>1</td>
                                                <td>None</td>
                                                <td>$10</td>
                                                <td>In Stock</td>
                                            </tr>
                                            <tr>
                                                <td>8</td>
                                                <td>Product H</td>
                                                <td>20</td>
                                                <td>3</td>
                                                <td>None</td>
                                                <td>$15</td>
                                                <td>In Stock</td>
                                            </tr>
                                            <tr>
                                                <td>9</td>
                                                <td>Product I</td>
                                                <td>0</td>
                                                <td>2</td>
                                                <td>None</td>
                                                <td>$20</td>
                                                <td>Out of Stock</td>
                                            </tr>
                                            <tr>
                                                <td>10</td>
                                                <td>Product J</td>
                                                <td>10</td>
                                                <td>1</td>
                                                <td>None</td>
                                                <td>$10</td>
                                                <td>In Stock</td>
                                            </tr>
                                        </tbody>
                                    </table>

                                </div>
                                <div class="card-footer d-flex justify-content-between"
                                    style="bottom: 0; background-color: white;">
                                    <button class="btn btn-outline-secondary"style="width: 100px;">Previous</button>
                                    <span class="mx-3">Page 1 of 10</span>
                                    <button class="btn btn-outline-secondary"style="width: 100px;">Next</button>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>


            </div>

        </div>
    </div>

<!--    <script>
        // Load sidebar
        fetch('./HeadSidebar/sidebar.html')
            .then(response => response.text())
            .then(data => {
                document.getElementById('sidebar-container').innerHTML = data;
            });

        // Load header
        fetch('./HeadSidebar/header.html')
            .then(response => response.text())
            .then(data => {
                document.getElementById('header-container').innerHTML = data;
            });
    </script>-->
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js">
    </script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap.js"></script>
    <!--<script src="../js/adminJs/Product.js"></script> -->  <!--????-->
    <script src="../admin/HeadSidebar/MenuButton.js"></script>
</body>

</html>