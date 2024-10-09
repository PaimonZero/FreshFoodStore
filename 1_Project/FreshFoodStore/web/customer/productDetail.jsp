<%-- Document : productDetail Created on : Oct 4, 2024, 7:29:26 PM Author : DELL --%>

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
    </head>

    <body>
        <%@include file="HeaderLogin1.jsp" %>
        <section class="container" style="padding-top: 130px;">
           <c:if test="${requestScope.product != null}">
    <p>Product Name: ${requestScope.product.name}</p>
</c:if>
<c:if test="${requestScope.product == null}">
    <p>Product data is null!</p>
</c:if>
            
            <div class="row">   
                <!-- Product Image -->
                <div class="col-md-6">
                    <img src="../images/${requestScope.product.image}" alt="Cải Thìa" class="img-fluid">
                    <div class="mt-3 d-flex">
                        <img src="../images/${requestScope.product.image}" class="img-thumbnail me-2" alt="Cải Thìa 1">
                        <img src="../images/${requestScope.product.image}" class="img-thumbnail me-2" alt="Cải Thìa 2">
                    </div>
                </div>
                <!-- Product Information -->
                <div class="col-md-6">
                    <h3 class="product-title">${requestScope.product.name}</h3>
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <span class="badge bg-success">${requestScope.product.status}</span>
                        <div class="product-rating">
                            <i class="bi bi-star-fill text-warning"></i>
                            <i class="bi bi-star-fill text-warning"></i>
                            <i class="bi bi-star-fill text-warning"></i>
                            <i class="bi bi-star-fill text-warning"></i>
                            <i class="bi bi-star text-warning"></i>
                        
                        </div>
                    </div>

                    <p class="product-price text-danger fs-4">${(requestScope.product.unitPrice* (100-requestScope.product.discount))/100}đ <span
                            class="text-decoration-line-through text-muted">${requestScope.product.unitPrice}đ</span></p>
                    <p><strong>Loại:</strong> <span>${requestScope.product.nameCategory}</span> | <strong>Thẻ:</strong> <span>Cải Xanh, Sức
                            khỏe</span></p>

                    <div class="product-quantity d-flex align-items-center mb-4">
                        <label class="me-3">Số lượng:</label>
                        <input type="number" value="1" class="form-control w-25">
                    </div>
                    <button class="btn btn-success btn-lg w-100 mb-3 position-relative add-btn">Thêm vào giỏ
                        hàng
                        <i class="fas fa-cart-plus cart-icon"></i>
                    </button>

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
                    <div class="promotion mt-3">
                        <img src="../images/.png" class="w-100" alt="alt" />
                        <div class="row promotion-info mt-2">
                            <div class="col-md-6 d-flex align-items-center">
                                <i class="fas fa-shipping-fast"></i> Giao hàng nhanh
                            </div>
                            <div class="col-md-6 d-flex align-items-center">
                                <i class="fas fa-leaf"></i> 100% tươi sạch
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
                            <img src="../images/${p.image}" class="card-img-top" alt="Táo Xanh">
                            </a>
                            <div class="card-body">
                                <h5 class="card-title">${p.name}</h5>
                                <p class="card-text text-danger">${p.unitPrice}đ</p>
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