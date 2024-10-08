
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--<link rel="stylesheet" href="../css/bootstrap.css">-->
    <link rel="stylesheet" href="../css/adminCss/Customer.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../admin/HeadSidebar/header-sidebar.css">
    <title>Customer</title>
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
                                    <h4 class="mb-0" style="font-weight: bold;">Customer</h4>
                                    <div>
                                        <button class="btn btn-sm btn-outline-success" data-bs-toggle="modal"
                                            data-bs-target="#addProductModal" style="width: 105px;">Edit
                                            Customer</button>

                                        <button class="btn btn-sm btn-outline-secondary"
                                            style="width: 105px;">Filter</button>
                                        <button class="btn btn-sm btn-outline-secondary" style="width: 105px;">Dowload
                                            All</button>
                                    </div>
                                </div>
                                <div class="card-body" style="height: 549.3px; overflow-y: auto;">
                                    <table class="table ">
                                        <thead>
                                            <tr>
                                                <th scope="col">IdCustomer</th>
                                                <th scope="col">Customer Name</th>
                                                <th scope="col">Customer Number</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">Address</th>
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
                                                <td>In Stock</td>
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
                                                <td>In Stock</td>
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

                                                <td>In Stock</td>
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

                                                <td>In Stock</td>
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

                                                <td>In Stock</td>
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

                                                <td>In Stock</td>
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

                                                <td>In Stock</td>
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

                                                <td>In Stock</td>
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

                                                <td>In Stock</td>
                                                <td>
                                                    <ion-icon name="pencil-outline"></ion-icon>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>

                                </div>
                                <div class="card-footer d-flex justify-content-between"
                                    style="bottom: 0; background-color: white;">
                                    <button class="btn btn-outline-secondary" style="width: 100px;">Previous</button>
                                    <span class="mx-3">Page 1 of 10</span>
                                    <button class="btn btn-outline-secondary" style="width: 100px;">Next</button>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
                <div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="addProductModalLabel"
                    aria-hidden="true">
                    <div class="modal-dialog" style="margin-top: 70px;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addProductModalLabel">Edit Customer</h5>
                            </div>
                            <div class="modal-body">

                                <form>
                                    <div class="form-group">
                                        <div class="form-row">
                                            <label for="productName">Tên khách hàng</label>
                                            <input type="text" class="form-control" id="productName"
                                                placeholder="Enter customer name">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="form-row">
                                            <label for="productCategory">Email</label>
                                            <input type="text" class="form-control" id="productCategory"
                                                placeholder="Enter email">

                                            </input>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="form-row">
                                            <label for="productPrice">Địa chỉ</label>
                                            <input type="number" class="form-control" id="productPrice"
                                                placeholder="Enter Address">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="form-row">
                                            <label for="productSupplier">Số điện thoại</label>
                                            <input type="text" class="form-control" id="productSupplier"
                                                placeholder="Enter Phone Number">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="form-row">
                                            <label for="productUnit">Trạng thái</label>
                                            <select class="form-control" id="productUnit">
                                                <option>Normal</option>
                                                <option>Block</option>
                                            </select>
                                        </div>
                                    </div>
                                </form>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                    style="width: 70px;">Cancel</button>
                                <button type="button" class="btn btn-success" data-dismiss="modal"
                                    style="width: 70px;">Save</button>
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
    <!--<script src="../BootstrapJS/bootstrap.min.js"></script>-->
    <script src="../js/bootstrap.bundle.min.js"></script>
    <script src="../js/bootstrap.js"></script>
    <script src="../admin/HeadSidebar/MenuButton.js"></script>
    

</body>

</html>