<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                            <p class="card-text">Num</p>
                                            <p class="card-text">Last 7 days</p>
                                        </div>
                                        <!-- Column 2 -->
                                        <div class="col-md-3 d-flex flex-column align-items-center justify-content-center" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#817AF3; font-size: 2.5rem;">
                                                <ion-icon name="stats-chart-outline"></ion-icon>
                                            </h5>
                                            <p class="card-text">Num</p>
                                            <p class="card-text">Last 7 days</p>
                                        </div>
                                        <!-- Column 3 -->
                                        <div class="col-md-3 d-flex flex-column align-items-center justify-content-center" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#DBA362; font-size: 2.5rem;">
                                                <ion-icon name="trending-up-outline"></ion-icon>
                                            </h5>
                                            <p class="card-text">Num</p>
                                            <p class="card-text">Last 7 days</p>
                                        </div>
                                        <!-- Column 4 -->
                                        <div class="col-md-3 d-flex flex-column align-items-center justify-content-center">
                                            <h5 class="card-title" style="color:#58D365; font-size: 2.5rem;">
                                                <ion-icon name="cash-outline"></ion-icon>
                                            </h5>
                                            <p class="card-text">Num</p>
                                            <p class="card-text">Last 7 days</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    
                        <!-- Purchase Overview -->
                        <div class="col-md-4">
                            <div class="card">
                                <div class="card-header bg-light" style="text-transform: uppercase; ">Inventory Summary</div>
                                <div class="card-body">
                                    <div class="row">
                                        <!-- Column 1 -->
                                        <div class="col-md-6 d-flex flex-column align-items-center justify-content-center" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#DBA362; font-size: 2.5rem;">
                                                <ion-icon name="file-tray-outline"></ion-icon>
                                            </h5>
                                            <p class="card-text">50</p>
                                            <p class="card-text">Last 7 days</p>
                                        </div>
                                        <!-- Column 2 -->
                                        <div class="col-md-6 d-flex flex-column align-items-center justify-content-center">
                                            <h5 class="card-title" style="color:#817AF3; font-size: 2.5rem;">
                                                <ion-icon name="location-outline"></ion-icon>
                                            </h5>
                                            <p class="card-text">50</p>
                                            <p class="card-text">Last 7 days</p>
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
                                                <ion-icon name="bag-outline"></ion-icon>                                            </h5>
                                            <p class="card-text">50</p>
                                            <p class="card-text">Last 7 days</p>
                                        </div>
                                        <!-- Column 2 -->
                                        <div class="col-md-3 d-flex flex-column align-items-center justify-content-center" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#58D365; font-size: 2.5rem;">
                                                <ion-icon name="cash-outline"></ion-icon>
                                            </h5>
                                            <p class="card-text">50</p>
                                            <p class="card-text">Last 7 days</p>
                                        </div>
                                        <!-- Column 3 -->
                                        <div class="col-md-3 d-flex flex-column align-items-center justify-content-center" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#817AF3; font-size: 2.5rem;">
                                                <ion-icon name="close-outline"></ion-icon>                                            </h5>
                                            <p class="card-text">50</p>
                                            <p class="card-text">Last 7 days</p>
                                        </div>
                                        <!-- Column 4 -->
                                        <div class="col-md-3 d-flex flex-column align-items-center justify-content-center">
                                            <h5 class="card-title" style="color:#DBA362; font-size: 2.5rem;">
                                                <ion-icon name="arrow-undo-outline"></ion-icon>
                                            </h5>
                                            <p class="card-text">50</p>
                                            <p class="card-text">Last 7 days</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    
                        <!-- Product Summary -->
                        <div class="col-md-4">
                            <div class="card">
                                <div class="card-header bg-light" style="text-transform: uppercase; ">Product Summary</div>
                                <div class="card-body">
                                    <div class="row">
                                        <!-- Column 1 -->
                                        <div class="col-md-6 d-flex flex-column align-items-center justify-content-center" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#24B8F1; font-size: 2.5rem;">
                                                <ion-icon name="person-circle-outline"></ion-icon>                                            </h5>
                                            <p class="card-text">50</p>
                                            <p class="card-text">Last 7 days</p>
                                        </div>
                                        <!-- Column 2 -->
                                        <div class="col-md-6 d-flex flex-column align-items-center justify-content-center">
                                            <h5 class="card-title" style="color:#817AF3; font-size: 2.5rem;">
                                                <ion-icon name="reader-outline"></ion-icon>                                            </h5>
                                            <p class="card-text">50</p>
                                            <p class="card-text">Last 7 days</p>
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
                                        <button class="btn btn-sm btn-outline-primary">See All</button>
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
                                            <tr>
                                                <td>1</td>
                                                <td>Product A</td>
                                                <td>100</td>
                                                <td>10</td>
                                                
                                            </tr>
                                            <tr>
                                                <td>2</td>
                                                <td>Product B</td>
                                                <td>50</td>
                                                <td>5</td>
                                                
                                            </tr>
                                            <tr>
                                                <td>3</td>
                                                <td>Product C</td>
                                                <td>0</td>
                                                <td>2</td>
                                                
                                            </tr>
                                            <tr>
                                                <td>4</td>
                                                <td>Product D</td>
                                                <td>10</td>
                                                <td>1</td>
                                            </tr>
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
                                        <button class="btn btn-sm btn-outline-primary">See All</button>
                                    </div>
                                </div>
                                <div class="card-body" style="height: 260px; overflow-y: auto;">
                                    <div class="container ">
                                        <!-- Product Item 1 -->
                                        <div class="row align-items-center py-3 border-bottom">
                                            <!-- Product Image -->
                                            <div class="col-2">
                                                <img src="https://via.placeholder.com/70" alt="Product Image" class="img-fluid rounded">
                                            </div>
                                            <!-- Product Info -->
                                            <div class="col-8">
                                                <h5 class="mb-1 font-weight-bold">Tata Salt</h5>
                                                <p class="text-muted mb-0">Remaining Quantity:</p>
                                                <p class="text-muted mb-0">10 packed</p>
                                            </div>
                                            <!-- Product Status -->
                                            <div class="col-2 text-end">
                                                <span class="badge rounded-pill bg-danger text-white p-2">Low</span>
                                            </div>
                                        </div>
                                    
                                        <!-- Product Item 2 -->
                                        <div class="row align-items-center py-3 border-bottom">
                                            <!-- Product Image -->
                                            <div class="col-2">
                                                <img src="https://via.placeholder.com/70" alt="Product Image" class="img-fluid rounded">
                                            </div>
                                            <!-- Product Info -->
                                            <div class="col-8">
                                                <h5 class="mb-1 font-weight-bold">Lays</h5>
                                                <p class="text-muted mb-0">Remaining Quantity:</p>
                                                <p class="text-muted mb-0">10 packed</p>
                                            </div>
                                            <!-- Product Status -->
                                            <div class="col-2 text-end">
                                                <span class="badge rounded-pill bg-danger text-white p-2">Low</span>
                                            </div>
                                        </div>
                                    
                                        <!-- Product Item 3 -->
                                        <div class="row align-items-center py-3 border-bottom">
                                            <!-- Product Image -->
                                            <div class="col-2">
                                                <img src="https://via.placeholder.com/70" alt="Product Image" class="img-fluid rounded">
                                            </div>
                                            <!-- Product Info -->
                                            <div class="col-8">
                                                <h5 class="mb-1 font-weight-bold">Lays</h5>
                                                <p class="text-muted mb-0">Remaining Quantity:</p>
                                                <p class="text-muted mb-0">10 packed</p>
                                            </div>
                                            <!-- Product Status -->
                                            <div class="col-2 text-end">
                                                <span class="badge rounded-pill bg-danger text-white p-2">Low</span>
                                            </div>
                                        </div>
                                    </div>
                                       
                                </div>
                                
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

    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <!-- Bootstrap JS -->
    <!--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>-->
    <script src="../js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <!-- Custom JS -->
    <script src="../admin/HeadSidebar/MenuButton.js"></script>
    <script src="../js/adminJs/Dashboard.js"></script>
</body>

</html>