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
        <link rel="shotcut icon" href="../images/logoFFSNoBG.png"/>
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
                                <form id="infoForm2" action="OrderHistory" method="POST"> <%--đổi đường dẫn--%>
                                    <a class="nav-link active" onclick="document.getElementById('infoForm2').submit();" style="cursor: pointer;">
                                        <i class="fas fa-sync-alt me-2"></i>
                                        Lịch sử đơn hàng
                                    </a>
                                </form>
                            </li>
<!--                            <li>
                                <form id="infoForm3" action="" method="POST"> <%--đổi đường dẫn--%>
                                    <a class="nav-link" onclick="document.getElementById('infoForm3').submit();" style="cursor: pointer;">
                                        <i class="far fa-heart me-2"></i>
                                        Danh sách yêu thích
                                    </a>
                                </form>
                            </li>-->
                            <li>
                                <form id="infoForm4" action="" method="POST"> <%--đổi đường dẫn--%>
                                    <a class="nav-link" onclick="document.getElementById('infoForm4').submit();" style="cursor: pointer;">
                                        <i class="bi bi-bag me-2"></i>
                                        Giỏ hàng
                                    </a>
                                </form>
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
                                <form id="infoForm6" action="authC?action=logout" method="POST"> <%--đổi đường dẫn--%>
                                    <a class="nav-link" onclick="document.getElementById('infoForm6').submit();" style="cursor: pointer;">
                                        <i class="fas fa-sign-out-alt me-2"></i>
                                        Đăng xuất
                                    </a>
                                </form>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-9 mt-2 mb-3">
                    <!-- Order Details -->
                    <div class="card shadow-sm">
                        <div class="card-header">
                            <div class="d-flex justify-content-between align-items-center mb-2 mt-2 content">
                                <h5 class="card-title text-center fw-bold">Chi tiết đơn hàng &#8901; <small
                                        class="text-muted fw-light">${orderCreatedAtString} &#8901; ${totalQuantity} sản phẩm</small></h5>
                                <form id="back" action="OrderHistory" method="POST"> <%--đổi đường dẫn--%>
                                    <a class="text-success fw-bold" onclick="document.getElementById('back').submit();" style="cursor: pointer;">
                                        Trở về lịch sử đơn hàng
                                    </a>
                                </form>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="row">

                                <div class="col-md-7">
                                    <div class="row mb-4 g-0">
                                        <div class="col-md-6">
                                            <div class="card" style="border-radius: 0; height: 345px;">
                                                <div class="card-header text-uppercase text-muted">
                                                    <h6 class="mt-2">Địa chỉ thanh toán</h6>
                                                </div>
                                                <div class="card-body">
                                                    <p class="fw-bold">${fullName}</p>
                                                    <p>${address}</p>
                                                    <p>Email <br><strong>${email}</strong></p>
                                                    <p>Số điện thoại <br><strong>${phone}</strong></p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="card" style="border-radius: 0; height: 345px;">
                                                <div class="card-header text-uppercase text-muted">
                                                    <h6 class="mt-2">Địa chỉ giao hàng</h6>
                                                </div>
                                                <div class="card-body">
                                                    <p class="fw-bold">${receiverName}</p>
                                                    <p>${deliveryLocation}</p>
                                                    <p>Email <br><strong>${email}</strong></p>
                                                    <p>Số điện thoại <br><strong>${receiverPhone}</strong></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-5">
                                    <div class="card">
                                        <div class="card-header text-muted">
                                            <div class="row p-2">
                                                <div class="col-md-5" style="border-right: 2px solid #ccc;">
                                                    <h6>Mã đơn hàng: </h6>
                                                    <strong class="text-dark">${orderId}</strong>
                                                </div>
                                                <div class="col-md-7">
                                                    <h6>Phương thức thanh toán: </h6>
                                                    <strong class="text-dark">${paymentType}</strong>
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
                                                    <td>Phí vận chuyển:</td>
                                                    <td class="shippingFee">${shippingFeeString}đ</td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 19px;">Tổng thanh toán: </td>
                                                    <td><strong style="font-size: 23px; color: rgb(52, 171, 52);" class="total">$84.00</strong></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <!--                         Order Progress 
                                                <div class="mb-4">
                                                    <div class="step-wizard">
                        <c:set var="status" value="${deliveryStatus}" />
                        <ul class="step-wizard-list">
                            <h3>${deliveryStatus}</h3>
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
                </div>-->
                        <%-- Thanh tiến trình cho trạng thái giao hàng deliveryStatus --%>
                        <div class="step-wizard">
                            <c:set var="status" value="${deliveryStatus}" />
                            <ul class="step-wizard-list">
                                <!-- Step 1: Receive or Cancel -->
                                <li class="step-wizard-item ">
                                    <span class="progress-count">1</span>
                                    <span class="progress-label">
                                        <c:choose>
                                            <c:when test="${status == 'Cancel'}">
                                                Cancel
                                            </c:when>
                                            <c:otherwise>
                                                Receive
                                            </c:otherwise>
                                        </c:choose>
                                    </span>
                                </li>

                                <!-- Step 2: Waiting -->
                                <li class="step-wizard-item ${status == 'Cancel' ? 'current-item' : ''}">
                                    <span class="progress-count">2</span>
                                    <span class="progress-label">Waiting</span>
                                </li>

                                <!-- Step 3: Shipping -->
                                <li class="step-wizard-item ${status == 'Waiting' ? 'current-item' : ''}">
                                    <span class="progress-count">3</span>
                                    <span class="progress-label">Shipping</span>
                                </li>

                                <!-- Step 4: Delivered -->
                                <li class="step-wizard-item ${status == 'Shipping' ? 'current-item' : ''}">
                                    <span class="progress-count">4</span>
                                    <span class="progress-label">Delivered</span>
                                </li>
                            </ul>
                        </div>


                        <!-- Order Items -->
                        <table class="table table-hover">
                            <thead class="table-active">
                                <tr>
                                    <th>Sản phẩm</th>
                                    <th>Giá</th>
                                    <th>Số lượng</th>
                                    <th>Giảm giá</th>
                                    <th>Tổng tiền</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="orderCurrentItem" items="${orderCurrent}">
                                    <tr>
                                        <td class="d-flex align-items-center">
                                            <!--lưu ý ở image chỉ được phép nhận tên ảnh chứ không lấy đường dẫn-->
                                            <img src="${orderCurrentItem.productImage}" alt="Red Capsicum" class="product-img me-2">
                                            <span>${orderCurrentItem.productName}</span>
                                        </td>
                                        <td class="item-price">${orderCurrentItem.unitPriceOutString}đ</td>
                                        <td class="input-qty">${orderCurrentItem.quantity}</td>
                                        <td class="discount">${orderCurrentItem.discountString}%</td>
                                        <td class="item-total-price"></td>
                                    </tr>
                                </c:forEach>
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
                                        function calculateTotal() {
                                            // Get all the rows in the table
                                            const rows = document.querySelectorAll('tr');

                                            rows.forEach(row => {
                                                // Get the price, quantity, discount, and total price elements
                                                const priceElement = row.querySelector('.item-price');
                                                const qtyElement = row.querySelector('.input-qty');
                                                const discountElement = row.querySelector('.discount');
                                                const totalElement = row.querySelector('.item-total-price');

                                                // If all required elements exist
                                                if (priceElement && qtyElement && discountElement && totalElement) {
                                                    // Get price (remove 'đ', commas, and periods, and convert to number)
                                                    let price = priceElement.textContent.trim().replace('đ', '').replace(/\./g, '').replace(/,/g, '');
                                                    let quantity = qtyElement.textContent.trim();
                                                    let discount = discountElement.textContent.trim().replace('%', '');

                                                    // Convert price, quantity, and discount to numbers
                                                    price = parseFloat(price);
                                                    quantity = parseInt(quantity);
                                                    discount = parseFloat(discount) / 100; // Convert discount to decimal

                                                    // Calculate total price with discount applied
                                                    const totalPrice = price * quantity * (1 - discount);

                                                    // Format total price with commas and 'đ' and update the total element
                                                    totalElement.textContent = new Intl.NumberFormat('vi-VN').format(totalPrice) + 'đ';
                                                }
                                            });
                                        }

// Run the function when the page loads
                                        document.addEventListener('DOMContentLoaded', calculateTotal);

                                        // Function to calculate subtotal, shipping fee, and total
                                        function calculateCartSummary() {
                                            // Calculate subtotal by summing all item total prices
                                            let subtotal = 0;
                                            document.querySelectorAll('.item-total-price').forEach(item => {
                                                // Remove 'đ' and dots, then convert to number
                                                let itemTotal = item.textContent.trim().replace('đ', '').replace(/\./g, '');
                                                itemTotal = parseFloat(itemTotal);
                                                subtotal += itemTotal;
                                            });

                                            // Update subtotal in the table (format with dots and 'đ')
                                            document.querySelector('.subtotal').textContent = new Intl.NumberFormat('vi-VN').format(subtotal) + 'đ';

                                            // Get shipping fee (remove 'đ' and dots)
                                            let shippingFee = document.querySelector('.shippingFee').textContent.trim().replace('đ', '').replace(/\./g, '');
                                            shippingFee = parseFloat(shippingFee);

                                            // Calculate total (subtotal plus shipping)
                                            const total = subtotal + shippingFee;

                                            // Update total in the table (format with dots and 'đ')
                                            document.querySelector('.total').textContent = new Intl.NumberFormat('vi-VN').format(total) + 'đ';
                                        }

                                        document.addEventListener('DOMContentLoaded', calculateCartSummary);

        </script>
        <script>
            //hiện thanh sidebar mobile
            document.querySelector('.menu-toggle').addEventListener('click', function () {
                document.getElementById('sidebar').classList.toggle('show');
            });
        </script>
    </body>

</html>