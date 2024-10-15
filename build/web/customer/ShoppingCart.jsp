<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Shopping Cart</title>
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <script src="https://kit.fontawesome.com/54f0cb7e4a.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="../css/customerCss/ShoppingCart.css">
    </head>
    <body>
        <%@include file="HeaderLogin1.jsp" %>
        <div class="container mb-3" style="padding-top: 130px;">
            <h3 class="text-center mt-3">Giỏ hàng của tôi</h3>
            <div class="row mt-5">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="row g-3">
                                <div class="col-lg-8">
                                    <div class="table-responsive table-container">
                                        <table class="table table-borderless table-nowrap table-centered mb-0">
                                            <thead class="table-light">
                                                <tr class="table-header">
                                                    <th class="first-column">Sản phẩm</th>
                                                    <th class="text-center">Giá</th>
                                                    <th class="second-column">Số lượng</th>
                                                    <th>Tổng tiền</th>
                                                    <th style="width: 5%;"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr class="product">
                                                    <td>
                                                        <img src="../images/fil2.png" alt="contact-img" title="contact-img"
                                                             class="rounded me-3" height="64" />
                                                        <p class="m-0 d-inline-block align-middle font-16 product-name">
                                                            <a href="apps-ecommerce-products-details.html"
                                                               class="text-body">Ớt chuông</a>
                                                            <!-- <br>
                                                            <small class="me-2"><b>Size:</b> Large </small>
                                                            <small><b>Color:</b> Light Green
                                                            </small> -->
                                                        </p>
                                                    </td>
                                                    <td>
                                                        <div class="item-price">$14</div>
                                                    </td>
                                                    <td class="text-center">
                                                        <div class="item-quantity d-flex justify-content-center">
                                                            <div class="qty-container">
                                                                <button class="qty-btn-minus btn-light" type="button"><i
                                                                        class="fa fa-minus"></i></button>
                                                                <input type="text" name="qty" value="2" class="input-qty" />
                                                                <button class="qty-btn-plus btn-light" type="button"><i
                                                                        class="fa fa-plus"></i></button>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="item-total-price">$74</div>
                                                    </td>
                                                    <td>
                                                        <a href="javascript:void(0);" class="action-icon"> <i
                                                                class="far fa-trash-alt"></i></a>
                                                    </td>
                                                </tr>
                                                <tr class="product">
                                                    <td>
                                                        <img src="../images/fil3.png" alt="contact-img" title="contact-img"
                                                             class="rounded me-3" height="64" />
                                                        <p class="m-0 d-inline-block align-middle font-16 product-name">
                                                            <a href="apps-ecommerce-products-details.html"
                                                               class="text-body">Xoài</a>
                                                            <!-- <br>
                                                            <small class="me-2"><b>Size:</b> Small </small>
                                                            <small><b>Color:</b> Brown </small> -->
                                                        </p>
                                                    </td>
                                                    <td>
                                                        <div class="item-price">$99.00</div>
                                                    </td>
                                                    <td>
                                                        <!-- <input type="number" min="1" value="2" class="form-control"
                                                            placeholder="Qty" style="width: 90px;"> -->
                                                        <div class="item-quantity d-flex justify-content-center">
                                                            <div class="qty-container">
                                                                <button class="qty-btn-minus btn-light" type="button"><i
                                                                        class="fa fa-minus"></i></button>
                                                                <input type="text" name="qty" value="2" class="input-qty" />
                                                                <button class="qty-btn-plus btn-light" type="button"><i
                                                                        class="fa fa-plus"></i></button>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="item-total-price">$198.00</div>
                                                    </td>
                                                    <td>
                                                        <a href="javascript:void(0);" class="action-icon"> <i
                                                                class="far fa-trash-alt"></i></a>
                                                    </td>
                                                </tr>
                                                <tr class="product">
                                                    <td>
                                                        <img src="../images/fil4.png" alt="contact-img" title="contact-img"
                                                             class="rounded me-3" height="64" />
                                                        <p class="m-0 d-inline-block align-middle font-16 product-name">
                                                            <a href="apps-ecommerce-products-details.html"
                                                               class="text-body">Ớt chuông xanh</a>
                                                            <!-- <br>
                                                            <small class="me-2"><b>Size:</b> Medium </small>
                                                            <small><b>Color:</b> Green </small> -->
                                                        </p>
                                                    </td>
                                                    <td>
                                                        <div class="item-price">$49.99</div>
                                                    </td>
                                                    <td>
                                                        <!-- <input type="number" min="1" value="10" class="form-control"
                                                            placeholder="Qty" style="width: 90px;"> -->
                                                        <div class="item-quantity d-flex justify-content-center">
                                                            <div class="qty-container">
                                                                <button class="qty-btn-minus btn-light" type="button"><i
                                                                        class="fa fa-minus"></i></button>
                                                                <input type="text" name="qty" value="2" class="input-qty" />
                                                                <button class="qty-btn-plus btn-light" type="button"><i
                                                                        class="fa fa-plus"></i></button>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="item-total-price">$499.90</div>
                                                    </td>
                                                    <td>
                                                        <a href="javascript:void(0);" class="action-icon"> <i
                                                                class="far fa-trash-alt"></i></a>
                                                    </td>
                                                </tr>
                                                <tr class="product">
                                                    <td>
                                                        <img src="../images/fil4.png" alt="contact-img" title="contact-img"
                                                             class="rounded me-3" height="64" />
                                                        <p class="m-0 d-inline-block align-middle font-16 product-name">
                                                            <a href="apps-ecommerce-products-details.html"
                                                               class="text-body">Ớt chuông xanh</a>
                                                            <!-- <br>
                                                            <small class="me-2"><b>Size:</b> Large </small>
                                                            <small><b>Color:</b> Orange </small> -->
                                                        </p>
                                                    </td>
                                                    <td>
                                                        <div class="item-price">$129.99</div>
                                                    </td>
                                                    <td>
                                                        <!-- <input type="number" min="1" value="1" class="form-control"
                                                            placeholder="Qty" style="width: 90px;"> -->
                                                        <div class="item-quantity d-flex justify-content-center">
                                                            <div class="qty-container">
                                                                <button class="qty-btn-minus btn-light" type="button"><i
                                                                        class="fa fa-minus"></i></button>
                                                                <input type="text" name="qty" value="2" class="input-qty" />
                                                                <button class="qty-btn-plus btn-light" type="button"><i
                                                                        class="fa fa-plus"></i></button>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="item-total-price">$129.99</div>
                                                    </td>
                                                    <td>
                                                        <a href="javascript:void(0);" class="action-icon"> <i
                                                                class="far fa-trash-alt"></i></a>
                                                    </td>
                                                </tr>
                                                <tr class="product">
                                                    <td>
                                                        <img src="../images/fil4.png" alt="contact-img" title="contact-img"
                                                             class="rounded me-3" height="64" />
                                                        <p class="m-0 d-inline-block align-middle font-16 product-name">
                                                            <a href="apps-ecommerce-products-details.html"
                                                               class="text-body">Ớt chuông xanh</a>
                                                            <!-- <br>
                                                            <small class="me-2"><b>Size:</b> Large </small>
                                                            <small><b>Color:</b> Orange </small> -->
                                                        </p>
                                                    </td>
                                                    <td>
                                                        <div class="item-price">$129.99</div>
                                                    </td>
                                                    <td>
                                                        <!-- <input type="number" min="1" value="1" class="form-control"
                                                            placeholder="Qty" style="width: 90px;"> -->
                                                        <div class="item-quantity d-flex justify-content-center">
                                                            <div class="qty-container">
                                                                <button class="qty-btn-minus btn-light" type="button"><i
                                                                        class="fa fa-minus"></i></button>
                                                                <input type="text" name="qty" value="2" class="input-qty" />
                                                                <button class="qty-btn-plus btn-light" type="button"><i
                                                                        class="fa fa-plus"></i></button>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="item-total-price">$129.99</div>
                                                    </td>
                                                    <td>
                                                        <a href="javascript:void(0);" class="action-icon"> <i
                                                                class="far fa-trash-alt"></i></a>
                                                    </td>
                                                </tr>
                                                <tr class="product">
                                                    <td>
                                                        <img src="../images/fil4.png" alt="contact-img" title="contact-img"
                                                             class="rounded me-3" height="64" />
                                                        <p class="m-0 d-inline-block align-middle font-16 product-name">
                                                            <a href="apps-ecommerce-products-details.html"
                                                               class="text-body">Ớt chuông xanh</a>
                                                            <!-- <br>
                                                            <small class="me-2"><b>Size:</b> Large </small>
                                                            <small><b>Color:</b> Orange </small> -->
                                                        </p>
                                                    </td>
                                                    <td>
                                                        <div class="item-price">$129.99</div>
                                                    </td>
                                                    <td>
                                                        <!-- <input type="number" min="1" value="1" class="form-control"
                                                            placeholder="Qty" style="width: 90px;"> -->
                                                        <div class="item-quantity d-flex justify-content-center">
                                                            <div class="qty-container">
                                                                <button class="qty-btn-minus btn-light" type="button"><i
                                                                        class="fa fa-minus"></i></button>
                                                                <input type="text" name="qty" value="2" class="input-qty" />
                                                                <button class="qty-btn-plus btn-light" type="button"><i
                                                                        class="fa fa-plus"></i></button>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="item-total-price">$129.99</div>
                                                    </td>
                                                    <td>
                                                        <a href="javascript:void(0);" class="action-icon"> <i
                                                                class="far fa-trash-alt"></i></a>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>

                                    </div> <!-- end table-responsive-->
                                    <div class="mt-3 d-flex">
                                        <a href="#">
                                            <button class="btn"
                                                    style="background-color: #ddd; font-weight: bold; border-radius: 50px; padding: 10px 20px;font-size: 15px;">Tiếp
                                                tục mua hàng</button>
                                        </a>
                                        <a href="#" style="margin-left: auto;">
                                            <button class="btn"
                                                    style="background-color: #ddd; font-weight: bold; border-radius: 50px; padding: 10px 20px;font-size: 15px;">Cập
                                                nhật giỏ hàng</button>
                                        </a>
                                    </div>
                                    <div class="coupon mt-4">
                                        <div class="row">
                                            <div class="col-md-3 pt-2">
                                                <h4>Coupon Code</h4>
                                            </div>
                                            <div class="col-md-9 position-relative">
                                                <form action="">
                                                    <input type="text" id="coupon-code"
                                                           style="border-radius: 50px; padding: 10px 20px;"
                                                           class="form-control" placeholder="Enter your coupon code" />
                                                    <button type="submit" class="btn btn-dark apply-btn">Apply
                                                        coupon</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- end col -->

                                <div class="col-lg-4">
                                    <div class="border p-3 mt-4 mt-lg-0 rounded">
                                        <h4 class="header-title mb-3">Tổng giá trị giỏ hàng</h4>
                                        <form action="">
                                            <div class="table-responsive">
                                                <table class="table mb-0">
                                                    <tbody>
                                                        <tr>
                                                            <td>Tổng cộng :</td>
                                                            <td><div class="total-amount">$1571.19</div></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Giảm giá : </td>
                                                            <td><div class="discount-amount">-$157.11</div></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Phí vận chuyển :</td>
                                                            <td><div class="shipping-amount">$25</div></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Thuế khấu trừ (10%): </td>
                                                            <td><div class="tax-amount">$19.22</div></td>
                                                        </tr>
                                                        <tr>
                                                            <th>Tổng tiền phải thanh toán :</th>
                                                            <th><div class="final-total">$1458.3</div></th>
                                                        </tr>
                                                    </tbody>
                                                </table>
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
    <script src="../js/authJs/ShoppingCart.js"></script>
</body>
</html>
