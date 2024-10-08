<%-- Document : orderDetail Created on : Sep 30, 2024, 11:22:44 AM Author : DELL --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Body Customer Dashboard</title>
        <link rel="stylesheet" href="../css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <script src="https://kit.fontawesome.com/54f0cb7e4a.js" crossorigin="anonymous"></script>
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="../css/customerCss/orderDetail.css">
    </head>

    <body>
        <%@include file="HeaderLogin1.jsp" %>
        <div class="mobile-header">
            <div class="d-flex justify-content-between align-items-center">
                <button class="menu-toggle btn btn-outline-secondary">
                    <i class="fas fa-bars"></i> Menu
                </button>
            </div>
        </div>

        <div class="container mobile-content">
            <div class="row mt-4">
                <div class="col-lg-3 mt-2">
                    <div class="sidebar d-flex flex-column flex-shrink-0 bg-body-tertiary" id="sidebar">
                        <span class="fs-4 p-3" style="font-weight: 500;">Menu</span>
                        <ul class="nav nav-pills flex-column mb-auto">
                            <li class="nav-item">
                                <form id="infoForm1" action="Dashboard?action=listInfo" method="POST">
                                    <a class="nav-link" aria-current="page" onclick="document.getElementById('infoForm1').submit();" style="cursor: pointer;">
                                        <i class="fas fa-th-large me-2"></i>
                                        Bảng điều khiển
                                    </a>
                                </form>
                            </li>
                            <li>
                                <a href="#" class="nav-link active">
                                    <i class="fas fa-sync-alt me-2"></i>
                                    Lịch sử đơn hàng
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-link">
                                    <i class="far fa-heart me-2"></i>
                                    Danh sách yêu thích
                                </a>
                            </li>
                            <li>
                                <a href="ShoppingCart.jsp" class="nav-link"><!--để tạm-->
                                    <i class="bi bi-bag me-2"></i>
                                    Giỏ hàng
                                </a>
                            </li>
                            <li>
                                <form id="infoForm5" action="AccountSetting?action=showData" method="POST"> <%--đổi đường dẫn--%>
                                    <a class="nav-link" onclick="document.getElementById('infoForm5').submit();" style="cursor: pointer;">
                                        <i class="bi bi-gear me-2"></i>
                                        Cài đặt
                                    </a>
                                </form>
                            </li>
                            <li>
                                <a href="#" class="nav-link">
                                    <i class="fas fa-sign-out-alt me-2"></i>
                                    Đăng xuất
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-9 mt-2 mb-3">
                    <!-- Order Details -->
                    <div class="card shadow-sm">
                        <div class="card-header">
                            <div class="d-flex justify-content-between align-items-center mb-2 mt-2 content">
                                <h5 class="card-title text-center fw-bold">Order Details &#8901; <small
                                        class="text-muted fw-light">${orderCurrent.orderCreatedAtString} &#8901;${orderCurrent.quantity} product</small></h5>
                                <a href="#" class="text-success fw-bold">Back to List</a> <%--trỏ qua orderHistory--%>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="row mb-4 g-0">
                                        <div class="col-md-6">
                                            <div class="card" style="border-radius: 0;">
                                                <div class="card-header text-uppercase text-muted">
                                                    <h6 class="mt-2">Địa chỉ thanh toán</h6>
                                                </div>
                                                <div class="card-body">
                                                    <p class="fw-bold">${orderCurrent.fullName}</p>
                                                    <p>${orderCurrent.address}</p>
                                                    <p>Email <br><strong>${orderCurrent.email}</strong></p>
                                                    <p>Số điện thoại <br><strong>${orderCurrent.phone}</strong></p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="card" style="border-radius: 0;">
                                                <div class="card-header text-uppercase text-muted">
                                                    <h6 class="mt-2">Địa chỉ giao hàng</h6>
                                                </div>
                                                <div class="card-body">
                                                    <p class="fw-bold">${orderCurrent.receiverName}</p>
                                                    <p>${orderCurrent.deliveryLocation}</p>
                                                    <p>Email <br><strong>${orderCurrent.email}</strong></p>
                                                    <p>Số điện thoại <br><strong>${orderCurrent.receiverPhone}</strong></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="card">
                                        <div class="card-header text-muted">
                                            <div class="row p-2">
                                                <div class="col-md-5" style="border-right: 2px solid #ccc;">
                                                    <h6>Order ID: </h6>
                                                    <strong class="text-dark">${orderCurrent.orderId}</strong>
                                                </div>
                                                <div class="col-md-7">
                                                    <h6>Payment Method: </h6>
                                                    <strong class="text-dark">${orderCurrent.paymentType}</strong>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-body p-2">
                                            <table class="table table-borderless pay-table">
                                                <tr>
                                                    <td>Tổng tiền đơn hàng:</td>
                                                    <td class="subtotal"></td>
                                                </tr>
                                                <tr>
                                                    <td>Giảm giá:</td>
                                                    <td class="discount">${orderCurrent.discountString}%</td>
                                                </tr>
                                                <tr>
                                                    <td>Phí vận chuyển:</td>
                                                    <td class="shippingFee">${orderCurrent.shippingFeeString}đ</td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 20px;">Tổng tiền:</td>
                                                    <td><strong style="font-size: 23px; color: rgb(52, 171, 52);" class="total">$84.00</strong></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Order Progress -->
                        <div class="mb-4">
                            <div class="step-wizard">
                                <c:set var="status" value="${orderCurrent.deliveryStatus}" />
                                <ul class="step-wizard-list">
                                    <li class="step-wizard-item ${status == 'Cancelled' ? 'current-item' : ''}">
                                        <span class="progress-count">1</span>
                                        <span class="progress-label">Cancelled</span>
                                    </li>
                                    <li class="step-wizard-item ${status == 'Order received' ? 'current-item' : ''}">
                                        <span class="progress-count">2</span>
                                        <span class="progress-label">Order received</span>
                                    </li>
                                    <li class="step-wizard-item ${status == 'On the way' ? 'current-item' : ''}">
                                        <span class="progress-count">3</span>
                                        <span class="progress-label">On the way</span>
                                    </li>
                                    <li class="step-wizard-item ${status == 'Delivered' ? 'current-item' : ''}">
                                        <span class="progress-count">4</span>
                                        <span class="progress-label">Delivered</span>
                                    </li>
                                </ul>
                            </div>
                        </div>


                        <!-- Order Items -->
                        <table class="table table-hover">
                            <thead class="table-active">
                                <tr>
                                    <th>Product</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Subtotal</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="d-flex align-items-center">
                                        <!--lưu ý ở image chỉ được phép nhận tên ảnh chứ không lấy đường dẫn-->
                                        <img src="../images/${orderCurrent.productImage}" alt="Red Capsicum" class="product-img me-2">
                                        <span>Red Capsicum</span>
                                    </td>
                                    <td class="item-price">${orderCurrent.unitPriceOutString}đ</td>
                                    <td class="input-qty">${orderCurrent.quantity}</td>
                                    <td class="item-total-price"></td>
                                </tr>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
        <script src="../js/bootstrap.bundle.min.js"></script>
        <script src="../js/authJs/orderDetail.js"></script>

        <script>
                                        //hiện thanh sidebar mobile
                                        document.querySelector('.menu-toggle').addEventListener('click', function () {
                                            document.getElementById('sidebar').classList.toggle('show');
                                        });
        </script>
    </body>

</html>