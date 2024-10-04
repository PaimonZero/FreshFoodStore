<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <h3 class="text-center mt-3">Thanh toán</h3>
            <div class="row mt-5">
                <div class="col-12">
                    <div class="card mb-3">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-lg-8">
                                    <h4>Thông tin thanh toán</h4>
                                    <form action="">
                                        <div class="row g-3">
                                            <div class="form-group col-md-4">
                                                <label for="first-name">Họ người nhận</label>
                                                <input type="text" id="first-name" placeholder="Họ" required>
                                            </div>
                                            <div class="form-group col-md-4">
                                                <label for="last-name">Tên người nhận</label>
                                                <input type="text" id="last-name" placeholder="Tên" required>
                                            </div>
                                            <div class="form-group col-md-4">
                                                <label for="company-name">Tên công ty <span class="optional">(Tùy
                                                        chọn)</span></label>
                                                <input type="text" id="company-name" placeholder="Tên công ty">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="street-address">Địa chỉ nhận</label>
                                            <input type="text" id="street-address" placeholder="Địa chỉ">
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-4">
                                                <label for="city">Chọn thành phố</label>
                                                <select id="city" required>
                                                    <option value="">Chọn thành phố</option>
                                                    <option value="da-nang">Đà Nẵng</option>
                                                    <option value="ho-chi-minh">Hồ Chí Minh</option>
                                                </select>
                                            </div>

                                            <div class="form-group col-md-4">
                                                <label for="district">Chọn tỉnh</label>
                                                <select id="district" required>
                                                    <option value="">Chọn tỉnh</option>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-4">
                                                <label for="zip-code">Zip Code</label>
                                                <input type="text" id="zip-code" placeholder="Zip Code">
                                            </div>
                                        </div>
                                        <div class="row g-3">
                                            <div class="form-group col-md-6">
                                                <label for="email">Email</label>
                                                <input type="email" id="email" placeholder="Địa chỉ Email" required>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label for="phone">Số điện thoại</label>
                                                <input type="text" id="phone" placeholder="Số điện thoại" required>
                                            </div>

                                        </div>
                                        <div class="row">
                                            <div class="checkbox-group col-md-4 mb-3">
                                                <input type="checkbox" id="different-address" name="different-address">
                                                <label for="different-address">Giao hàng đến địa chỉ khác</label>
                                            </div>

                                            <!-- Input nhập địa chỉ mới, ban đầu bị ẩn -->
                                            <div class="col-md-8" id="new-address" style="display: none;">
                                                <label for="new-address-input">Đia chỉ giao hàng khác:</label>
                                                <input type="text" id="new-address-input" class="form-control"
                                                       placeholder="Địa chỉ giao hàng mới" required>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="add-info">
                                            <h4 class="mb-4">Thông tin bổ sung</h4>
                                            <span>Ghi chú đơn hàng (Tùy chọn)</span>
                                            <br>
                                            <textarea 
                                                placeholder="Ghi chú về đơn hàng của bạn, ví dụ: ghi chú đặc biệt về việc giao hàng, ...">
                                            </textarea>
                                        </div>
                                    </form>
                                </div>
                                <!-- end col -->

                                <div class="col-lg-4">
                                    <div class="border p-3 mt-4 mt-lg-0 rounded">
                                        <h4 class="header-title mb-3">Tóm tắt giỏ hàng</h4>
                                        <div class="product-container">
                                            <div class="d-flex align-items-center">
                                                <img src="../images/fil4.png" alt="contact-img" title="contact-img"
                                                     class="rounded me-3" height="64" />
                                                <p class="m-0 d-inline-block align-middle font-16 product-name">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body"
                                                       style="font-weight: 500;">Ớt chuông
                                                        xanh x5</a>
                                                </p>
                                                <div style="margin-left: auto; font-weight: bold;" class="item-total-price">
                                                    $198.00</div>
                                            </div>
                                            <div class="d-flex align-items-center">
                                                <img src="../images/fil3.png" alt="contact-img" title="contact-img"
                                                     class="rounded me-3" height="64" />
                                                <p class="m-0 d-inline-block align-middle font-16 product-name">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body"
                                                       style="font-weight: 500;">Xoài x2</a>
                                                </p>
                                                <div style="margin-left: auto; font-weight: bold;" class="item-total-price">
                                                    $198.00</div>
                                            </div>
                                            <div class="d-flex align-items-center">
                                                <img src="../images/fil3.png" alt="contact-img" title="contact-img"
                                                     class="rounded me-3" height="64" />
                                                <p class="m-0 d-inline-block align-middle font-16 product-name">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body"
                                                       style="font-weight: 500;">Xoài x2</a>
                                                </p>
                                                <div style="margin-left: auto; font-weight: bold;" class="item-total-price">
                                                    $198.00</div>
                                            </div>
                                            <div class="d-flex align-items-center">
                                                <img src="../images/fil3.png" alt="contact-img" title="contact-img"
                                                     class="rounded me-3" height="64" />
                                                <p class="m-0 d-inline-block align-middle font-16 product-name">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body"
                                                       style="font-weight: 500;">Xoài x2</a>
                                                </p>
                                                <div style="margin-left: auto; font-weight: bold;" class="item-total-price">
                                                    $198.00</div>
                                            </div>
                                            <div class="d-flex align-items-center">
                                                <img src="../images/fil3.png" alt="contact-img" title="contact-img"
                                                     class="rounded me-3" height="64" />
                                                <p class="m-0 d-inline-block align-middle font-16 product-name">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body"
                                                       style="font-weight: 500;">Xoài x2</a>
                                                </p>
                                                <div style="margin-left: auto; font-weight: bold;" class="item-total-price">
                                                    $198.00</div>
                                            </div>
                                            <div class="d-flex align-items-center">
                                                <img src="../images/fil3.png" alt="contact-img" title="contact-img"
                                                     class="rounded me-3" height="64" />
                                                <p class="m-0 d-inline-block align-middle font-16 product-name">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body"
                                                       style="font-weight: 500;">Xoài x2</a>
                                                </p>
                                                <div style="margin-left: auto; font-weight: bold;" class="item-total-price">
                                                    $198.00</div>
                                            </div>
                                            <div class="d-flex align-items-center">
                                                <img src="../images/fil3.png" alt="contact-img" title="contact-img"
                                                     class="rounded me-3" height="64" />
                                                <p class="m-0 d-inline-block align-middle font-16 product-name">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body"
                                                       style="font-weight: 500;">Xoài x2</a>
                                                </p>
                                                <div style="margin-left: auto; font-weight: bold;" class="item-total-price">
                                                    $198.00</div>
                                            </div>
                                        </div>
                                        <form action="">
                                            <div class="table-responsive">
                                                <table class="table mb-0 mt-1">
                                                    <tbody>
                                                        <tr>
                                                            <td>Tổng cộng :</td>
                                                            <td>
                                                                <div class="total-amount">$1571.19</div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Giảm giá : </td>
                                                            <td>
                                                                <div class="discount-amount">-$157.11</div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Phí vận chuyển :</td>
                                                            <td>
                                                                <div class="shipping-amount">$25</div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Thuế khấu trừ (10%): </td>
                                                            <td>
                                                                <div class="tax-amount">$19.22</div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>Tổng tiền phải thanh toán :</th>
                                                            <th>
                                                                <div class="final-total">$1458.3</div>
                                                            </th>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <div class="mt-3 payment">
                                                    <h5>Hình thức thanh toán</h5>
                                                    <div class="d-flex align-items-center mb-2">
                                                        <input type="radio" id="cod" name="payment" value="" checked>
                                                        <label for="cod">Cash on Delivery</label> <br>
                                                    </div>
                                                    <div class="d-flex align-items-center mb-2">
                                                        <input type="radio" id="paypal" name="payment" value="">
                                                        <label for="paypal">Paypal</label> <br>
                                                    </div>
                                                    <div class="d-flex align-items-center mb-2">
                                                        <input type="radio" id="VNPay" name="payment" value="">
                                                        <label for="VNPay">VNPay</label> <br>
                                                    </div>
                                                </div>
                                                <button type="submit" class="process-pay-btn btn w-100 mt-4">Đi đến thanh
                                                    toán</button>
                                        </form>
                                    </div>
                                    <!-- end table-responsive -->
                                </div>
                            </div> <!-- end col -->

                        </div> <!-- end row -->
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
