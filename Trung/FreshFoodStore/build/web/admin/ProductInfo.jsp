<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

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
                                <div class="card-header bg-light d-flex align-items-center justify-content-between">
                                    <h4 class="mb-0" style="font-weight: bold;">Product</h4> <!--chỗ này thế bằng tên sản phẩm-->
                                    <div>
                                        <button class="btn btn-sm btn-outline-success"style="width: 105px;">Edit Product</button>
                                        <button class="btn btn-sm btn-outline-secondary"style="width: 105px;">Download All</button>
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
                                                                <td style="padding: 10px 10px;">Thịt heo ba chỉ</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="padding: 10px 10px;"><strong>Mã sản phẩm:</strong></td>
                                                                <td style="padding: 10px 10px;">456567</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="padding: 10px 10px;"><strong>Danh mục sản phẩm:</strong></td>
                                                                <td style="padding: 10px 10px;">Thịt</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="padding: 10px 10px;"><strong>Đơn vị tính:</strong></td>
                                                                <td style="padding: 10px 10px;">Kg</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="padding: 10px 10px;"><strong>Giá bán ra:</strong></td>
                                                                <td style="padding: 10px 10px;">200.000 vnd</td>
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
                                                                <td style="padding: 10px 10px;">Ronald Martin</td>
                                                            </tr>
                                                            <tr>
                                                                <td style="padding: 10px 10px;"><strong>Số điện thoại</strong></td>
                                                                <td style="padding: 10px 10px;">0123456789</td>
                                                            </tr>

                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>

                                            <div class="batch-info mb-4">
                                                <h2 class="h5">Các lô hàng hiện có</h2>
                                                <table class="table table-bordered text-center">
                                                    <thead>
                                                        <tr>
                                                            <th>Mã lô hàng</th>
                                                            <th>Giá nhập</th>
                                                            <th>Ngày hết hạn</th>
                                                            <th>Số lượng hiện có</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>5</td>
                                                            <td>₹430</td>
                                                            <td>11/12/24</td>
                                                            <td>15</td>
                                                        </tr>
                                                        <tr>
                                                            <td>25</td>
                                                            <td>₹257</td>
                                                            <td>21/12/24</td>
                                                            <td>19</td>
                                                        </tr>
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
                                                        <tr>
                                                            <td>3</td>
                                                            <td>11/12/24</td>
                                                            <td>12/12/24</td>
                                                            <td class="no-border-right">35 </td>
                                                            <td class="no-border-left">
                                                                <button class="btn btn-sm btn-outline-secondary" >
                                                                    <ion-icon name="pencil-outline" ></ion-icon>
                                                                </button>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <div class = "col-md-1">

                                        </div>
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