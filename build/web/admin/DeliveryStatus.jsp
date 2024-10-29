<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/adminCss/UpdateStatus.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../admin/HeadSidebar/header-sidebar.css">
    <title>Delivery Status</title>

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
                                        <h4 class="mb-0" style="font-weight: bold;">OrderDetails  &#8901</h4>
                                        <h5 class="mb-0" >${orderStatus[0].orderCreatedAtString} &#8901;${orderStatus[0].quantity} sản phẩm</h5>
                                    </div>
                                    <div>
                                        <form action="UpdateDeliveryStatus" method="post">
                                            <input type="hidden" name="orderId" value="${orderStatus[0].orderId}" />
                                            <button class="btn btn-sm btn-outline-success"style="width: 130px;"
                                                    type="submit" name="action" value="update" >Update Status</button>
                                            <button class="btn btn-sm btn-outline-danger" style="width: 130px;"
                                                    type="submit" name="action" value="undo" >Undo</button>
                                        </form>
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
                                                    <h6>${orderStatus[0].fullName}</h6>
                                                    <p>${orderStatus[0].address}</p>
                                                    <hr>
                                                    <p><strong>Email</strong></p>
                                                    <p>${orderStatus[0].email}</p>
                                                    <p><strong>Phone</strong></p>
                                                    <p>${orderStatus[0].phone}</p>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Shipping Address -->
                                        <div class="col-md-4">
                                            <div class="card">
                                                <div class="card-header" style="text-transform: uppercase;">Shipping Address</div>
                                                <div class="card-body">
                                                    <h6>${orderStatus[0].receiverName}</h6>
                                                    <p>${orderStatus[0].deliveryLocation}</p>
                                                    <hr>
                                                    <p><strong>Email</strong></p>
                                                    <p>${orderStatus[0].email}</p>
                                                    <p><strong>Phone</strong></p>
                                                    <p>${orderStatus[0].receiverPhone}</p>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Order Summary -->
                                        <div class="col-md-4">
                                            <div class="card">
                                                <div class="card-header text-muted">
                                                    <div class="row p-2">
                                                        <div class="col-md-5" style="border-right: 2px solid #ccc;">
                                                            <h6>Mã đơn hàng: </h6>
                                                            <strong class="text-dark">${orderStatus[0].orderId}</strong>
                                                        </div>
                                                        <div class="col-md-7">
                                                            <h6>Phương thức thanh toán: </h6>
                                                            <strong class="text-dark">${orderStatus[0].paymentType}</strong>
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
                                                            <td class="shippingFee">${orderStatus[0].shippingFeeString}đ</td>
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


                                    <div class="step-wizard">
                                        <c:set var="status" value="${orderStatus[0].deliveryStatus}" />
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

                                    <div class="card-body -list" style="height: auto; ">
                                        <table class="table ">
                                            <thead class="table-active">
                                                <tr>
                                                    <th scope="col">Product</th>
                                                    <th scope="col">Price</th>
                                                    <th scope="col">Quantity</th>
                                                    <th scope="col">Discount</th>
                                                    <th scope="col">SubTotal </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="order" items="${orderStatus}">
                                                    <tr>
                                                        <td class="d-flex align-items-center">
                                                            <!-- Chỉ nhận tên ảnh chứ không lấy đường dẫn -->
                                                            <img src="${order.productImage}" alt="${order.productName}" class="product-img me-2" 
                                                                 style="width: 100px; height: auto;  border-radius: 5px; object-fit: cover" >
                                                            <span>${order.productName}</span>
                                                        </td>
                                                        <td class="item-price">${order.unitPriceOutString}đ</td>
                                                        <td class="input-qty">${order.quantity}</td>
                                                        <td class="discount">${order.discountString}%</td>
                                                        <td class="item-total-price"></td> <!-- Nếu bạn có trường tổng giá -->
                                                    </tr>
                                                </c:forEach>
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
        <script src="../admin/HeadSidebar/SideBar.js"></script>
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




</body>

</html>