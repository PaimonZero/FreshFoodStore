<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Checkout</title>
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <script src="https://kit.fontawesome.com/54f0cb7e4a.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="../css/customerCss/checkout.css">
    </head>
    <body>
        <%@include file="HeaderLogin1.jsp" %>
        <div class="container" style="padding-top: 130px;">
            <h3 class="text-center mt-3 fw-bold">Thanh toán</h3>
            <div class="row mt-5">
                <div class="col-12">
                    <div class="card mb-3">
                        <div class="card-body">
                            <form action="checkOut?action=updateCartInfo" method="POST">
                                <div class="row">
                                    <div class="col-lg-8">
                                        <h4>Thông tin thanh toán</h4>
                                        <hr>
                                        <!--<div class="row g-3">-->
                                        <div class="form-group mt-2">
                                            <label for="first-name">Họ và tên người nhận</label>
                                            <input type="text" id="first-name" name="fullName" placeholder="Họ" required>
                                        </div>
                                        <!--</div>-->
                                        <div class="form-group">
                                            <label for="street-address">Địa chỉ nhận</label>
                                            <input type="text" id="street-address" name="address" placeholder="Địa chỉ" required>
                                        </div>
                                        <div class="row g-3">
                                            <div class="form-group col-md-6 email-form">
                                                <label for="email">Email</label>
                                                <input type="email" id="email" placeholder="Địa chỉ Email" value="${account.email}" disabled>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label for="phone">Số điện thoại</label>
                                                <input type="text" id="phone" name="receiverPhone" pattern="[0-9]{10}"
                                                       title="Vui lòng nhập đúng số điện thoại" placeholder="Số điện thoại" required>
                                            </div>

                                        </div>
                                        <hr>
                                        <div class="add-info">
                                            <h4 class="mb-4">Thông tin bổ sung</h4>
                                            <span>Ghi chú đơn hàng (Tùy chọn)</span>
                                            <br>
                                            <textarea placeholder="Ghi chú về đơn hàng của bạn, ví dụ: ghi chú đặc biệt về việc giao hàng, ...">
                                            </textarea>
                                        </div>
                                    </div>
                                    <!-- end col -->

                                    <div class="col-lg-4">
                                        <div class="border p-3 mt-4 mt-lg-0 rounded">
                                            <h4 class="header-title mb-3">Tóm tắt giỏ hàng</h4>
                                            <div class="product-container">
                                                <c:forEach var="cartItem" items="${listCart}">
                                                    <div class="d-flex align-items-center mb-4">
                                                        <img src="${cartItem.productImage}" width="65" height="55" alt="contact-img" title="contact-img"
                                                             class="rounded me-3" height="64" />
                                                        <p class="m-0 d-inline-block align-middle font-16 product-name">
                                                            <a href="" class="text-body" <%--có thể gán link dẫn đến product detail--%>
                                                               style="font-weight: 500;"><strong>${cartItem.productName}</strong> x${cartItem.quantity}</a>
                                                        </p>
                                                        <div style="display: none;" class="item-price">${cartItem.unitPriceOutString}đ</div>
                                                        <div style="display: none;" class="input-qty">${cartItem.quantity}</div>
                                                        <div style="display: none;" class="discount">${cartItem.discountString}%</div>
                                                        <div style="margin-left: auto; font-weight: bold;" class="item-total-price"></div>
                                                    </div>
                                                    <input type="hidden" name="orderId" value="${cartItem.orderId}"></input>
                                                </c:forEach>
                                            </div>
                                            <!--<form action="">-->
                                            <div class="table-responsive">
                                                <table class="table mb-0 mt-1">
                                                    <tbody>
                                                        <tr>
                                                            <td>Tổng cộng :</td>
                                                            <td>
                                                                <div class="subtotal"></div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Phí vận chuyển :</td>
                                                            <td>
                                                                <div class="shippingFee">${shippingFee}đ</div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>Tổng tiền phải thanh toán :</th>
                                                            <th>
                                                                <div class="total"></div>
                                                            </th>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <div class="mt-3 payment">
                                                    <h5>Hình thức thanh toán</h5>
                                                    <div class="d-flex align-items-center mb-2">
                                                        <input type="radio" id="cod" name="payment" value="Cash" checked>
                                                        <label for="cod">Cash on Delivery</label> <br>
                                                    </div>
                                                    <div class="d-flex align-items-center mb-2">
                                                        <input type="radio" id="paypal" name="payment" value="QRCode">
                                                        <label for="paypal">VNPay</label> <br>
                                                    </div>
<!--                                                    <div class="d-flex align-items-center mb-2">
                                                        <input type="radio" id="VNPay" name="payment" value="VNPay">
                                                        <label for="VNPay">VNPay</label> <br>
                                                    </div>-->
                                                </div>
                                                            <div><input type="hidden" name="isConfirmed" value="0"></div>
                                                <button type="submit" class="process-pay-btn btn w-100 mt-4">Đi đến thanh
                                                    toán</button>
                                            </div>
                                            <!--</form>-->
                                            <!-- end table-responsive -->
                                        </div>
                                    </div> <!-- end col -->
                                </div> <!-- end row -->
                            </form>
                        </div> <!-- end card-body-->
                    </div> <!-- end card-->
                </div> <!-- end col -->
            </div>
        </div>
        <%@ include file="Footer.jsp" %>
        <script src="../js/bootstrap.bundle.min.js"></script>
        <script src="../js/authJs/checkout.js"></script>
    </body>
</html>
