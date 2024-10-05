<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/adminCss/Report.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../admin/HeadSidebar/header-sidebar.css">
    <title>Report</title>
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
                    <!-- Overview -->
                    <div class="row">
                        <!-- Overview -->
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-header" style="text-transform: uppercase;">Overview</div>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-4 card-item">
                                            <div class="card-content">
                                                <p class="card-value"><strong>₹21,190</strong></p>
                                                <p class="card-title">Total Profit</p>
                                            </div>
                                        </div>
                                        <div class="col-md-4 card-item">
                                            <div class="card-content">
                                                <p class="card-value"><strong>₹18,300</strong></p>
                                                <p class="card-title">Revenue</p>
                                            </div>
                                        </div>
                                        <div class="col-md-4 card-item">
                                            <div class="card-content">
                                                <p class="card-value"><strong>₹17,432</strong></p>
                                                <p class="card-title">Sales</p>
                                            </div>
                                        </div>
                                    </div>
                                    <hr>

                                    <div class="row">
                                        <div class="col-md-3 card-item">
                                            <div class="card-content">
                                                <p class="card-value"><strong>₹21,190</strong></p>
                                                <p class="card-title">Net Purchase Value</p>
                                            </div>
                                        </div>
                                        <div class="col-md-3 card-item">
                                            <div class="card-content">
                                                <p class="card-value"><strong>₹18,300</strong></p>
                                                <p class="card-title">Net Sales Value</p>
                                            </div>
                                        </div>
                                        <div class="col-md-3 card-item">
                                            <div class="card-content">
                                                <p class="card-value"><strong>₹17,432</strong></p>
                                                <p class="card-title">MoM Profit</p>
                                            </div>
                                        </div>
                                        <div class="col-md-3 card-item">
                                            <div class="card-content">
                                                <p class="card-value"><strong>₹17,432</strong></p>
                                                <p class="card-title">YoY Profit</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Best Selling Category -->
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-header bg-light d-flex align-items-center justify-content-between">
                                    <div style="text-transform: uppercase;">Best Selling Category</div>
                                    <button class="btn btn-sm btn-outline-primary">See All</button>
                                </div>
                                <div class="card-body">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Catergory</th>
                                                <th>Turn Over</th>
                                                <th>Increase By</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>Vegetable</td>
                                                <td>₹26,000</td>
                                                <td>3.2%</td>
                                            </tr>
                                            <tr>
                                                <td>Instant Food</td>
                                                <td>₹26,000</td>
                                                <td>2%</td>
                                            </tr>
                                            <tr>
                                                <td>Households</td>
                                                <td>₹26,000</td>
                                                <td>1.5%</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Profit and Revenue Charts -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card chart-card">
                                <div class="card-body">
                                    <div class="d-flex justify-content-between">
                                        <h3 class="card-title mb-4">Profit and Revenue</h3>
                                        <button class="btn btn-outline-secondary mb-4">Weekly</button>
                                    </div>
                                    <canvas id="profitChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Best Selling Product Section -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card text-dark bg-light d-flex mb-3">
                                <div class="card-header bg-light d-flex align-items-center justify-content-between">
                                    <h4 class="mb-0" style="font-weight: bold;">Product</h4>
                                    <div>
                                        <button class="btn btn-sm btn-outline-primary">See All</button>
                                    </div>
                                </div>
                                <div class="card-body" style="height: 260px; overflow-y: auto;">
                                    <table class="table ">
                                        <thead>
                                            <tr>
                                                <th scope="col">IdProduct</th>
                                                <th scope="col">Products</th>
                                                <th scope="col">Category</th>
                                                <th scope="col">Remaining Quantity</th>
                                                <th scope="col">Turn over</th>
                                                <th scope="col">Increase by</th>
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
                                            </tr>
                                            <tr>
                                                <td>2</td>
                                                <td>Product B</td>
                                                <td>50</td>
                                                <td>5</td>
                                                <td>In progress</td>
                                                <td>$30</td>
                                            </tr>
                                            <tr>
                                                <td>3</td>
                                                <td>Product C</td>
                                                <td>0</td>
                                                <td>2</td>
                                                <td>None</td>
                                                <td>$20</td>
                                            </tr>
                                            <tr>
                                                <td>4</td>
                                                <td>Product D</td>
                                                <td>10</td>
                                                <td>1</td>
                                                <td>None</td>
                                                <td>$10</td>
                                            </tr>
                                            <tr>
                                                <td>5</td>
                                                <td>Product E</td>
                                                <td>20</td>
                                                <td>3</td>
                                                <td>None</td>
                                                <td>$15</td>
                                            </tr>
                                        </tbody>
                                    </table>

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
    <script src="../js/adminJs/Report.js"></script>
</body>

</html>