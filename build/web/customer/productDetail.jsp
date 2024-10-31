<%-- Document : productDetail Created on : Oct 4, 2024, 7:29:26 PM Author : DELL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/><!--đổi thành location VN-->
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Ecobazar Product Page</title>
        <!--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">-->
        <link rel="stylesheet" href="../css/bootstrap.min.css" />
        <link rel="stylesheet" href="../css/customerCss/productDetail.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <script src="https://kit.fontawesome.com/54f0cb7e4a.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="shotcut icon" href="../images/logoFFSNoBG.png"/>
    </head>

    <body>
        <%@include file="HeaderLogin1.jsp" %>
        <section class="container" style="padding-top: 130px;">
            <div class="row">   
                <!-- Product Image -->
                <div class="col-md-6">
<!--                    <img src="${requestScope.product.image}" alt="Cải Thìa" class="img-fluid">
                    <div class="mt-3 d-flex">
                        <img src="${requestScope.product.image}" style="width: 30%; height: 50%;"  class="img-thumbnail me-2" alt="Cải Thìa 1">
                        <img src="${requestScope.product.image}" style="width: 30%; height: 50%;" class="img-thumbnail me-2" alt="Cải Thìa 2">
                    </div>-->
                    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">                    
                        <div class="carousel-inner">
                            <c:forEach var="itemImage1" items="${listImage}" varStatus="status">
                                <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                    <img src="${itemImage1.galleryImage}" class="d-block w-100" alt="Slide ${status.index + 1}">
                                </div>
                            </c:forEach>
                        </div>

                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>

                    </div>
                    <div style="display: flex; justify-content: center; margin-top: 20px;">
                        <c:forEach var="itemImage" items="${listImage}" varStatus="status">
                            <!-- Thay thế nút bằng hình ảnh -->
                            <img src="${itemImage.galleryImage}"
                                 data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${status.index}" 
                                 class="${status.index == 0 ? 'active' : ''} thumbnail mx-1" 
                                 aria-current="${status.index == 0 ? 'true' : ''}" 
                                 style="width: 20%; height: 100px; border: 2px solid #ccc; border-radius: 10px; cursor: pointer;" />
                        </c:forEach>
                    </div>
                </div>
                <!-- Product Information -->
                <div class="col-md-6">
                    <h3 class="product-title">${requestScope.product.name}</h3>
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <span style="font-size: 18px;" class="badge<c:choose>
                                  <c:when test="${requestScope.product.status == 'In Stock'}">bg-success</c:when>
                                  <c:when test="${requestScope.product.status == 'Out of Stock'}">bg-danger</c:when>
                              </c:choose>">
                            ${requestScope.product.status}
                        </span>
                        <div class="product-rating">
                            <i class="bi bi-star-fill text-warning"></i>
                            <i class="bi bi-star-fill text-warning"></i>
                            <i class="bi bi-star-fill text-warning"></i>
                            <i class="bi bi-star-fill text-warning"></i>
                            <i class="bi bi-star text-warning"></i>

                        </div>
                    </div>

<!--                    <p class="product-price text-danger fs-4">${(requestScope.product.unitPrice* (100-requestScope.product.discount))/100}đ <span
                            class="text-decoration-line-through text-muted">${requestScope.product.unitPriceString}đ</span></p>-->
                    <p class="product-price text-danger fs-4">
                        <c:if test="${requestScope.product.discount > 0}">
                            <!-- Hiển thị giá sau khi đã chiết khấu nếu discount > 0 -->
                            <fmt:formatNumber value="${(requestScope.product.unitPrice * (100 - requestScope.product.discount)) / 100}" 
                                              type="currency" currencySymbol="₫" groupingUsed="true" />
                            <span class="text-decoration-line-through text-muted">${requestScope.product.unitPriceString}đ</span>
                        </c:if>
                        <c:if test="${requestScope.product.discount == 0}">
                            <!-- Hiển thị giá bình thường nếu không có chiết khấu -->
                            <fmt:formatNumber value="${requestScope.product.unitPrice}" type="currency" currencySymbol="₫" groupingUsed="true" />
                        </c:if>
                    </p>
                    <p><strong>Loại:</strong> <span>${requestScope.product.nameCategory}</span> | <strong>Thẻ:</strong> <span>Cải Xanh, Sức
                            khỏe</span></p>
                    <form action="${pageContext.request.contextPath}/customer/shoppingcart" method="get"> 
                        <div class="product-quantity d-flex align-items-center mb-4">
                            <label class="me-3">Số lượng:</label>
                            <input type="number" name="soluong" value="1" min="1" max="${requestScope.product.batchQuantity}" class="form-control w-25">
                        </div>

                        <input type="hidden" name="id" id="id" value="${requestScope.product.productId}" />
                        <c:choose>
                            <c:when test="${requestScope.product.status == 'In Stock'}">
                                
                                <button type="submit" class="btn btn-success btn-lg w-100 mb-3 position-relative add-btn">
                                    Thêm vào giỏ hàng
                                    <i class="fas fa-cart-plus cart-icon"></i>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" class="btn btn-secondary btn-lg w-100 mb-3 position-relative add-btn" disabled>
                                    Hết hàng
                                    <i class="fas fa-cart-plus cart-icon"></i>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </form>


                    <div class="d-flex align-items-center justify-content-between">
                        <div class="d-flex align-items-center">
                            <a href="#" class="text-decoration-none">Chia sẻ:</a>
                            <div class="connect-social share-icon">
                                <ul class="d-flex px-2">
                                    <li><a href="#" class="social-button facebook"><i class="fab fa-facebook"></i></a>
                                    </li>
                                    <li><a href="#" class="social-button twitter"><i class="fab fa-twitter"></i></a>
                                    </li>
                                    <li><a href="#" class="social-button"><i class="fab fa-pinterest-p"></i></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <a href="#" class="btn btn-outline-primary">Yêu Thích</a>
                    </div>
                    <!--                    <div class="promotion mt-3">
                                            <img src="../images/Ship1.png" class="w-100" alt="alt" />
                                            <div class="row promotion-info mt-2">
                                                <div class="col-md-6 d-flex align-items-center">
                                                    <i class="fas fa-shipping-fast"></i> Giao hàng nhanh
                                                </div>
                                                <div class="col-md-6 d-flex align-items-center">
                                                    <i class="fas fa-leaf"></i> 100% tươi sạch
                                                </div>
                                            </div>
                                        </div>-->
                    <div class="box mt-4">
                        <div class="box-header">
                            <h4>Nhận ngay khuyến mại đặc biệt</h4>
                        </div>
                        <div class="box-content">
                            <div>
                                <li>
                                    <span><i class="icon fas fa-check-circle" style="color: #48bb78;"></i></span>
                                    <h5>Tham gia rút thăm trúng thưởng với giải thưởng lên đến 10.000.000đ</h5>
                                </li>
                                <li>
                                    <span><i class="icon fas fa-check-circle" style="color: #48bb78;"></i></span>
                                    <h5>Cơ hội nhận được phần quà dụng cụ nhà bếp lên tới 5.000.000đ</h5>
                                </li>
                                <li>
                                    <span><i class="icon fas fa-check-circle" style="color: #48bb78;"></i></span>
                                    <h5>Cơ hội trúng phiếu mua hàng trị giá 500.000₫ mỗi tháng</h5>
                                </li>
                                <li>
                                    <span><i class="icon fas fa-check-circle" style="color: #48bb78;"></i></span>
                                    <h5>Tặng túi vải khi thu đổi túi nhựa</h5>
                                </li>
                                <li>
                                    <span><i class="icon fas fa-check-circle" style="color: #48bb78;"></i></span>
                                    <h5>Nhận phiếu mua hàng 40.000₫ khi mua thực phẩm theo mùa</h5>
                                </li>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mt-5">
                <div class="col-md-12">
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active" id="description-tab" data-bs-toggle="tab"
                                    data-bs-target="#description" type="button" role="tab">Mô Tả</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="details-tab" data-bs-toggle="tab" data-bs-target="#details"
                                    type="button" role="tab">Thông Tin Chi Tiết</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="reviews-tab" data-bs-toggle="tab" data-bs-target="#reviews"
                                    type="button" role="tab">Nhận Xét Khác Hàng</button>
                        </li>
                    </ul>

                    <div class="tab-content mt-3" id="myTabContent">
                        <div class="tab-pane fade show active" id="description" role="tabpanel">
                            <p>${requestScope.product.name}</p>
                        </div>
                        <div class="tab-pane fade" id="details" role="tabpanel">
                            <p>${requestScope.product.description}</p>
                        </div>
                        <div class="tab-pane fade" id="reviews" role="tabpanel">
                            <p>Good</p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Related Products -->
            <div class="related-products mt-5 mb-3">
                <h4 class="mb-4">Sản Phẩm Liên Quan</h4>
                <div class="row">
                    <c:forEach var="p" items="${lienquan}"> 
                        <div class="col-md-3">
                            <div class="card">
                                <a href="productDetail?id=${p.productId}">
                                    <img src="${p.image}" class="card-img-top" alt="Táo Xanh">
                                </a>
                                <div class="card-body">
                                    <h5 class="card-title">${p.name}</h5>
                                    <p class="card-text text-danger">${p.unitPriceString}đ</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>


                </div>
            </div>
        </section>
        <%@include file="Footer.jsp" %>
        <script src="../js/bootstrap.bundle.min.js"></script>
    </body>

</html>