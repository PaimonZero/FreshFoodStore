<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/adminCss/Orders.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../admin/HeadSidebar/header-sidebar.css">

    <title>Orders</title>
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
                                <h4 class="card-header bg-light" style="font-weight: bold;">Overall Orders</h4>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-3" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#1570EF;">Tổng số đơn hàng</h5>
                                            <p class="card-text">50</p>
                                            <p class="card-text">Last 7 days</p>
                                        </div>
                                        <div class="col-md-3" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#E19133;">Tổng tiền nhận được</h5>

                                            <div class="d-flex justify-content-between">
                                                <p class="card-text">5</p>
                                                <p class="card-text">25000</p>
                                            </div>

                                            <div class="d-flex justify-content-between">
                                                <p class="card-text">Last 7 days</p>
                                                <p class="card-text">Revenue</p>
                                            </div>
                                        </div>
                                        <div class="col-md-3" style="border-right: 3px solid #F0F1F3;">
                                            <h5 class="card-title" style="color:#845EBC">Đang vận chuyển</h5>
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
                                            <h5 class="card-title" style="color:#F36960">Số đơn hủy/trả</h5>
                                            <div class="d-flex justify-content-between">
                                                <p class="card-text">12</p>
                                                <p class="card-text">2</p>
                                            </div>

                                            <div class="d-flex justify-content-between">
                                                <p class="card-text">Ordered</p>
                                                <p class="card-text">Not in stock</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="card text-dark bg-light d-flex mb-3">
                                <div class="card-header bg-light d-flex align-items-center justify-content-between">
                                    <h4 class="mb-0" style="font-weight: bold;">Orders</h4>
                                    <div>
                                        <button class="btn btn-sm btn-outline-secondary"style="width: 105px;">Filter</button>
                                        <button class="btn btn-sm btn-outline-secondary"style="width: 105px;">Order History</button>
                                    </div>
                                </div>
                                <div class="card-body" style="height: 340px; overflow-y: auto;">
                                    <table class="table ">
                                        <thead>
                                            <tr>
                                                <th scope="col">IdOrder</th>
                                                <th scope="col">Customer Name </th>
                                                <th scope="col">Pay</th>
                                                <th scope="col">Total Value</th>
                                                <th scope="col">Order Date</th>
                                                <th scope="col">Shipper</th>
                                                <th scope="col">Status</th>
                                                <th scope="col"></th>
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
                                                <td>Demo</td>
                                                <td>
                                                    <ion-icon name="pencil-outline"></ion-icon>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>2</td>
                                                <td>Product B</td>
                                                <td>50</td>
                                                <td>5</td>
                                                <td>In progress</td>
                                                <td>$30</td>
                                                <td>Demo</td>
                                                <td>
                                                    <ion-icon name="pencil-outline"></ion-icon>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>3</td>
                                                <td>Product C</td>
                                                <td>0</td>
                                                <td>2</td>
                                                <td>None</td>
                                                <td>$20</td>
                                                <td>Out of Stock</td>
                                                <td>
                                                    <ion-icon name="pencil-outline"></ion-icon>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>4</td>
                                                <td>Product D</td>
                                                <td>10</td>
                                                <td>1</td>
                                                <td>None</td>
                                                <td>$10</td>
                                                <td>Demo</td>
                                                <td>
                                                    <ion-icon name="pencil-outline"></ion-icon>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>5</td>
                                                <td>Product E</td>
                                                <td>20</td>
                                                <td>3</td>
                                                <td>None</td>
                                                <td>$15</td>
                                                <td>Demo</td>
                                                <td>
                                                    <ion-icon name="pencil-outline"></ion-icon>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>6</td>
                                                <td>Product F</td>
                                                <td>0</td>
                                                <td>2</td>
                                                <td>None</td>
                                                <td>$20</td>
                                                <td>Out of Stock</td>
                                                <td>
                                                    <ion-icon name="pencil-outline"></ion-icon>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>7</td>
                                                <td>Product G</td>
                                                <td>10</td>
                                                <td>1</td>
                                                <td>None</td>
                                                <td>$10</td>
                                                <td>Demo</td>
                                                <td>
                                                    <ion-icon name="pencil-outline"></ion-icon>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>8</td>
                                                <td>Product H</td>
                                                <td>20</td>
                                                <td>3</td>
                                                <td>None</td>
                                                <td>$15</td>
                                                <td>Demo</td>
                                                <td>
                                                    <ion-icon name="pencil-outline"></ion-icon>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>9</td>
                                                <td>Product I</td>
                                                <td>0</td>
                                                <td>2</td>
                                                <td>None</td>
                                                <td>$20</td>
                                                <td>Out of Stock</td>
                                                <td>
                                                    <ion-icon name="pencil-outline"></ion-icon>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>10</td>
                                                <td>Product J</td>
                                                <td>10</td>
                                                <td>1</td>
                                                <td>None</td>
                                                <td>$10</td>
                                                <td>Demo</td>
                                                <td>
                                                    <ion-icon name="pencil-outline"></ion-icon>
                                                </td>
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
    <!--<script src="../Style/js/Product.js"></script>-->
    <script src="../admin/HeadSidebar/MenuButton.js"></script>
    <script src="../admin/HeadSidebar/SideBar.js"></script>

</body>

</html>