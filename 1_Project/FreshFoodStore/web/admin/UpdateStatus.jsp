<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/adminCss/UpdateStatus.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../admin/HeadSidebar/header-sidebar.css">
    <title>Update Status</title>

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

                <div class="row-mt-4">
                    <div class="col-md-12">
                        <div class="card text-dark bg-light d-flex mb-3 mt-3">
                            
                            <div class="card-header bg-light d-flex align-items-center justify-content-between">
                                <div class="d-flex align-items-center">
                                <h4 class="mb-0" style="font-weight: bold;">OrderDetails</h4>
                                <h5 class="mb-0" > . April 24, 2021  3 Products</h5>
                                </div>
                                <div>
                                    <button class="btn btn-sm btn-outline-success"style="width: 130px;">Update Status</button>
                                    <button class="btn btn-sm btn-outline-danger" style="width: 130px;">Cancel</button>
                                </div>
                                <div>
                                    <button class="btn btn-sm btn-outline-success"style="width: 130px">Order List</button>
                                </div>
                            </div>
                            
                            <div class="card-body">
                                
                                    <div class="row">
                                        <!-- Billing Address -->
                                        <div class="col-md-4">
                                            <div class="card">
                                                <div class="card-header" style="text-transform: uppercase;">Billing Address</div>
                                                <div class="card-body">
                                                    <h6>Dainne Russell</h6>
                                                    <p>4140 Parker Rd. Allentown, New Mexico 31134</p>
                                                    <hr>
                                                    <p><strong>Email</strong></p>
                                                    <p>dainne.ressell@gmail.com</p>
                                                    <p><strong>Phone</strong></p>
                                                    <p>(671) 555-0110</p>
                                                </div>
                                            </div>
                                        </div>
                            
                                        <!-- Shipping Address -->
                                        <div class="col-md-4">
                                            <div class="card">
                                                <div class="card-header" style="text-transform: uppercase;">Shipping Address</div>
                                                <div class="card-body">
                                                    <h6>Dainne Russell</h6>
                                                    <p>4140 Parker Rd. Allentown, New Mexico 31134</p>
                                                    <hr>
                                                    <p><strong>Email</strong></p>
                                                    <p>dainne.ressell@gmail.com</p>
                                                    <p><strong>Phone</strong></p>
                                                    <p>(671) 555-0110</p>
                                                </div>
                                            </div>
                                        </div>
                            
                                        <!-- Order Summary -->
                                        <div class="col-md-4">
                                            <div class="card">
                                                <div class="card-header d-flex justify-content-between">
                                                    <span style="border-right: 3px solid #939393; text-transform: uppercase;">Order ID: <strong>#4152</strong></span>
                                                    <span style="margin-left: 10px; text-transform: uppercase;">Payment Method: <strong>Paypal</strong></span>
                                                </div>
                                                    <div class="table-responsive">
                                                        <table class="table mb-0">
                                                            <thead class="table-light">
                                                            
                                                            </thead>
                                                            <tbody>
                                                            <tr>
                                                                <td><h6>Subtotal :</h6></td>
                                                                <td><h6>$365.00</h6></td>
                                                            </tr>
                                                            <tr>
                                                                <td><h6>Discount :</h6></td>
                                                                <td><h6>20%</h6></td>
                                                            </tr>
                                                            <tr>
                                                                <td><h6>Shipping : </h6></td>
                                                                <td><h6>Free</h6></td>
                                                            </tr>
                                                            <tr>
                                                                <th><h4>Total :</h4></th>
                                                                <th style="color:#00BA1E;"><h4>$84.00</h4></th>
                                                            </tr>
                                                            <tr>
                                                                <th style="color: white; background-color: #00BA1E; text-align: center; border-radius: 5px;">Already Paid</th>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                        </div>
                                    </div>
                                    
                                </div>
                            

                            <div class="step-wizard">
                                <ul class="step-wizard-list">
                                    <li class="step-wizard-item ">
                                        <span class="progress-count">1</span>
                                        <span class="progress-label">Order received</span>
                                    </li>
                                    <li class="step-wizard-item current-item">
                                        <span class="progress-count">2</span>
                                        <span class="progress-label">Processing</span>
                                    </li>
                                    <li class="step-wizard-item ">
                                        <span class="progress-count">3</span>
                                        <span class="progress-label">On the way</span>
                                    </li>
                                    <li class="step-wizard-item ">
                                        <span class="progress-count">4</span>
                                        <span class="progress-label">Delivered</span>
                                    </li>
                                </ul>
                            </div>
                            
                            <div class="card-body-list" style="height: 200px; ">
                                <table class="table ">
                                    <thead>
                                        <tr>
                                            <th scope="col">Product</th>
                                            <th scope="col">Price</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">SubTotal </th>
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
                                            <td>3</td>
                                            <td>Product C</td>
                                            <td>0</td>
                                            <td>2</td>
                                            
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
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js">
    </script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap.js"></script>
    <!--<script src="../Style/js/Product.js"></script>-->
    <script src="../admin/HeadSidebar/MenuButton.js"></script>

</body>

</html>