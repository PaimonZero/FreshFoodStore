<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
            <h3 class="text-center mt-3 fw-bold">Giỏ hàng của tôi</h3>
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
                                                    <!--<th></th>-->
                                                    <th colspan="2">Tổng tiền</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="p" items="${listOrderDetail}"> 
                                                    <tr class="product">
                                                        <td>
                                                            <img src="${p.image}" alt="contact-img" title="contact-img"
                                                                 class="rounded me-3" height="64" />
                                                            <p class="m-0 d-inline-block align-middle font-16 product-name">
                                                                <a href="#">
                                                                    ${p.name}</a>
                                                                <!-- <br>
                                                                <small class="me-2"><b>Size:</b> Large </small>
                                                                <small><b>Color:</b> Orange </small> -->
                                                            </p>
                                                        </td>
                                                        <td>
                                                            <div class="item-price">${p.unitPriceString} đ</div>
                                                            <div class="d-none">${p.discountString}%</div>
                                                        </td>
                                                        <!-- <input type="number" min="1" value="1" class="form-control"
                                                            placeholder="Qty" style="width: 90px;"> -->
                                                        <td class="text-center d-flex">
                                                            <div class="item-quantity d-flex justify-content-center" style="padding-left: 20px;">
                                                                <div class="qty-container w-100">
                                                                    <!--                                                                    <button class="qty-btn-minus btn-light" type="button"><i
                                                                                                                                                class="fa fa-minus"></i></button>-->

                                                                    <button type="button" class="qty-btn-minus btn-light" onclick="changeQuantity(${p.orderDetailId}, -1, this)">-</button>
                                                                    <input type="number" name="quantity" class="input-qty" style="width: 55px;" value="${p.quantity}" min="1" max="${p.batchQuantity}" readonly />
                                                                    <button type="button" class="qty-btn-plus btn-light" onclick="changeQuantity(${p.orderDetailId}, +1, this)">+</button>
                                                                    <!--                                                                                                                                <!--                                                                    <button class="qty-btn-plus btn-light" type="button"><i
                                                                                                                                                                                                        class="fa fa-plus"></i></button>-->
                                                                </div>
                                                            </div>
                                                            <input type="submit" class="col-5 ok" style="display: none; background: none; border: none; padding: 0; cursor: pointer; outline: none; color: blue;" value="OK"> <%--update giỏ hàng khi số lượng tăng hoặc giảm--%>
                                                        </td>
                                                        <td>
                                                            <!--<div class="item-total-price">${p.quantity*(100-p.discount)*p.unitPrice}</div>-->
                                                            <div class="item-total-price"></div>
                                                        </td>
                                                        <td>
                                                            <!--                                                            <a href="javascript:void(0);" class="action-icon"> <i
                                                                                                                                class="far fa-trash-alt"></i></a>-->
                                                            <!--<h1>${p.orderDetailId}</h1>-->
                                                            <form id="delete" action="shoppingcart?action=delete" method="POST"> <%--đổi đường dẫn--%>
                                                                <input type="hidden" name="deleteId" id="deleteId" value="${p.orderDetailId}" />
                                                                <a class="action-icon" onclick="document.getElementById('delete').submit();" style="cursor: pointer;">
                                                                    <i class="far fa-trash-alt me-2"></i>
                                                                </a>
                                                            </form>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>

                                    </div> <!-- end table-responsive-->
                                    <div class="mt-3 d-flex">
                                        <a href="${pageContext.request.contextPath}/customer/Homepage">
                                            <button class="btn"
                                                    style="background-color: #ddd; font-weight: bold; border-radius: 50px; padding: 10px 20px;font-size: 15px;">Tiếp
                                                tục mua hàng</button>
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
                                        <form action="checkOut?action=listCart" method="POST">
                                            <div class="table-responsive">
                                                <table class="table mb-0">
                                                    <tbody>
                                                        <tr>
                                                            <td>Tổng cộng :</td>
                                                            <td><div class="total-amount"></div></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Phí vận chuyển :</td>
                                                            <td><div class="shipping-amount"></div></td>
                                                        </tr>
                                                        <tr>
                                                            <th>Tổng tiền phải thanh toán :</th>
                                                            <th><div class="final-total"></div></th>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <button type="submit" class="process-pay-btn btn w-100 mt-4">Đi đến thanh
                                                    toán</button>
                                            </div>
                                        </form>
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
          <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
    const contextPath = "${pageContext.request.contextPath}";
</script>


    </body>
</html>