<%-- 
    Document   : homePage
    Created on : Sep 27, 2024, 3:22:47 PM
    Author     : DELL
--%>
<!--có đụng-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home Page</title>
        <!--        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
                      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">-->
        <link rel="stylesheet" href="../css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <script src="https://kit.fontawesome.com/54f0cb7e4a.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <link rel="shotcut icon" href="../images/logoFFSNoBG.png"/>
        <link rel="stylesheet" href="../css/customerCss/homePageTest.css"/>
    </head>
    <body>
        <%@include file="HeaderLogin1.jsp" %>
        <div class="container-fluid" style="padding-top: 130px;">
            <div class="row g-2">
                <div class="col-md-3 sidebar">
                    <div class="desktop-view">
                        <nav class="nav flex-column">
                            <div class="sidebar-container">
                                <a class="nav-link active px-4" href="category?food=Fruits">
                                    <i class="fas fa-apple-alt"></i> Trái cây
                                </a>
                                <a class="nav-link px-4" href="category?food=Vegetables">
                                    <i class="fas fa-seedling"></i> Rau 
                                </a>
                                <a class="nav-link px-4" href="category?food=Seafood">
                                    <i class="fas fa-fish"></i> Cá, hải sản
                                </a>
                                <a class="nav-link px-4" href="category?food=Meat">
                                    <i class="fas fa-drumstick-bite"></i> Thịt
                                </a>
                                <a class="nav-link px-4" href="category?food=Egg">
                                    <i class="fas fa-egg"></i> Trứng
                                </a>
                                <a class="nav-link px-4" href="category?food=Beverages">
                                    <i class="fas fa-snowflake"></i> Đồ uống
                                </a>
                                <a class="nav-link px-4" href="category?food=Spices">
                                    <i class="fas fa-pepper-hot"></i> Gia vị
                                </a>
                                <a class="nav-link px-4" href="category?food=DairyProducts">
                                    <i class="fas fa-egg"></i> Sản phẩm từ sữa
                                </a>
                                <a class="nav-link px-4" href="category?food=Tuber">
                                    <i class="fas fa-carrot"></i> Củ, quả
                                </a>
                            </div>
                        </nav>
                    </div>

                    <div class="mobile-view">
                        <!-- Nút bấm hiển thị sidebar dạng popup đang để ở headerLogin1.jsp dòng 148-->

                        <!-- Sidebar Offcanvas -->
                        <div class="offcanvas offcanvas-start sidebar-offcanvas" tabindex="-1" id="offcanvasSidebar"
                             aria-labelledby="offcanvasSidebarLabel">
                            <div class="offcanvas-header">
                                <h5 class="offcanvas-title" id="offcanvasSidebarLabel">Danh mục</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                                        aria-label="Close"></button>
                            </div>
                            <div class="offcanvas-body">
                                <div class="sidebar-container">
                                    <a class="nav-link active" href="category?food=Fruits">
                                        <i class="fas fa-apple-alt"></i> Trái cây
                                    </a>
                                    <a class="nav-link" href="category?food=Vegetables">
                                        <i class="fas fa-seedling"></i> Rau
                                    </a>
                                    <a class="nav-link" href="category?food=Seafood">
                                        <i class="fas fa-fish"></i> Cá, hải sản
                                    </a>
                                    <a class="nav-link" href="category?food=Meat">
                                        <i class="fas fa-drumstick-bite"></i> Thịt
                                    </a>
                                    <a class="nav-link" href="category?food=Egg">
                                        <i class="fas fa-egg"></i> Trứng
                                    </a>
                                    <a class="nav-link" href="category?food=Beverages">
                                        <i class="fas fa-snowflake"></i> Đồ uống
                                    </a>
                                    <a class="nav-link" href="category?food=Spices">
                                        <i class="fas fa-pepper-hot"></i> Gia vị
                                    </a>
                                    <a class="nav-link" href="category?food=DairyProducts">
                                        <i class="fas fa-cheese"></i> Sản phẩm từ sữa
                                    </a>
                                    <a class="nav-link" href="category?food=Tuber">
                                        <i class="fas fa-carrot"></i> Củ, quả
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="row">
                        <div class="col-12">
                            <div id="mainSlider" class="carousel slide" data-bs-ride="carousel" data-bs-interval="5000">
                                <!-- Indicators -->
                                <div class="carousel-indicators">
                                    <button type="button" data-bs-target="#mainSlider" data-bs-slide-to="0" class="active"
                                            aria-current="true" aria-label="Slide 1"></button>
                                    <button type="button" data-bs-target="#mainSlider" data-bs-slide-to="1"
                                            aria-label="Slide 2"></button>
                                    <button type="button" data-bs-target="#mainSlider" data-bs-slide-to="2"
                                            aria-label="Slide 3"></button>
                                </div>
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <img src="../images/Thiết kế chưa có tên (3).png" class="d-block w-100 carousel-image"
                                             alt="...">
                                    </div>
                                    <div class="carousel-item">
                                        <img src="../images/Thiết kế chưa có tên (4).png" class="d-block w-100 carousel-image"
                                             alt="...">
                                    </div>
                                    <div class="carousel-item">
                                        <img src="../images/Thiết kế chưa có tên (5).png" class="d-block w-100 carousel-image"
                                             alt="...">
                                    </div>
                                </div>
                                <button class="carousel-control-prev" type="button" data-bs-target="#mainSlider"
                                        data-bs-slide="prev">
                                    <div class="caro-btn">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Previous</span>
                                    </div>
                                </button>
                                <button class="carousel-control-next" type="button" data-bs-target="#mainSlider"
                                        data-bs-slide="next">
                                    <div class="caro-btn">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Next</span>
                                    </div>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="container-fluid mt-3">
            <div class="row g-2">
                <div class="col-lg-3 col-md-6 col-sm-6 col-3">
                    <div class="card p-2 info-item">
                        <i class="bi bi-truck info-image"></i> <!-- Bootstrap truck icon -->
                        <div class="info-text">
                            <h4>Miễn phí giao hàng</h4>
                            <p>Miễn phí giao hàng cho đơn hàng trên 500k.</p>
                        </div>
                    </div>
                </div>

                <div class="col-lg-3 col-md-6 col-sm-6 col-3">
                    <div class="card p-2 info-item">
                        <i class="bi bi-headset info-image"></i> <!-- Bootstrap headset icon -->
                        <div class="info-text">
                            <h4>Bộ phận hỗ trợ 24/7</h4>
                            <p>Hỗ trợ khách hàng 24/7.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-6 col-3">
                    <div class="card p-2 info-item">
                        <i class="bi bi-lock-fill info-image"></i> <!-- Bootstrap lock icon -->
                        <div class="info-text">
                            <h4>An toàn, bảo mật</h4>
                            <p>Chúng tôi bảo đảm thông tin được bảo mật.</p>
                        </div>
                    </div>
                </div>

                <div class="col-lg-3 col-md-6 col-sm-6 col-3">
                    <div class="card p-2 info-item">
                        <i class="bi bi-box-fill info-image"></i> <!-- Bootstrap box icon -->
                        <div class="info-text">
                            <h4>Hoàn tiền ngay</h4>
                            <p>Chính sách hoàn tiền dễ dàng.</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row g-2">
                <h6 style="text-transform: uppercase; text-align: center; color: green; font-weight: bold; margin-top: 60px;">
                    Products</h6>
                <h3 style="text-transform: uppercase; text-align: center; font-weight: bold;">Sản phẩm nổi bật</h3>
                <div class="col-md-3 list-img mt-3">
                    <h6 style="text-transform: uppercase; font-weight: bold;">Mùa đông sale sập sàn</h6>
                    <h1 style="text-transform: uppercase; color: #3f923f;">sale 75%</h1>
                    <a href="category?food=Vegetables" class="shop-now-btn">
                        <button
                            class="mt-3 btn btn-light text-success position-relative fw-bold border border-success px-5">Mua
                            ngay
                            <i class="fas fa-arrow-right position-absolute"></i>
                        </button>
                    </a>
                </div>
                <div class="col-md-9 mt-3">
                    <div class="row g-2">
                        <c:forEach var="p" items="${noibat}"> 
                            <div class="col-lg-3 col-md-4 col-sm-6 col-4">
                                <div class="card outstand">
                                    <a href="productDetail?id=${p.productId}"> 
                                        <img src="${p.image}" class="card-img-top" alt="...">
                                    </a>
                                    <div class="icons">
                                        <i class="fas fa-heart"></i>
                                        <i class="fas fa-eye"></i>
                                    </div>
                                    <div class="card-body">
                                        <h5 class="card-title fw-bold">${p.name}</h5>
                                        <div class="row">
                                            <div class="col-md-6">
<!--                                                    <h6 class="fw-bold" style="margin-bottom: 0;">${p.unitPrice*(100-p.discount)/100}₫</h6>-->
                                                <h6 class="fw-bold" style="margin-bottom: 0;">
                                                    <fmt:formatNumber value="${(p.unitPrice * (100 - p.discount)) / 100}" type="currency" currencySymbol="₫" groupingUsed="true" />
                                                </h6>
                                                <c:choose>
                                                    <c:when test="${p.discount > 0}">
                                                        <span style="text-decoration: line-through; color: gray; font-size: 14px;">
                                                            ${p.unitPriceString}₫
                                                        </span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <p></p>
                                                    </c:otherwise>
                                                </c:choose>
                                                <div>
                                                    <span class="fa fa-star checked"></span>
                                                    <span class="fa fa-star checked"></span>
                                                    <span class="fa fa-star checked"></span>
                                                    <span class="fa fa-star checked"></span>
                                                    <span class="fa fa-star"></span>
                                                </div>
                                            </div>
                                            <div class="col-md-6 d-flex align-items-center buy-btn">
                                                <button class="fas fa-shopping-bag openCartBtn"></button>
                                            </div>
                                        </div>
                                        <div class="buy-mobile">
                                            <button class="btn btn-primary w-100">Mua</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid mt-4 mb-5">
            <div class="row desktop-view">
                <div class="col-md-4 mb-3">
                    <div class="pro-img1">
                        <h6>BEST DEALS</h6>
                        <h1>Sale tháng</h1>
                        <div id="countdown" style="color: white; font-weight: bold;">
                            <div class="time-box">
                                <span id="days"></span>
                                <div class="label">Ngày</div>
                            </div>
                            <div class="separator">:</div>
                            <div class="time-box">
                                <span id="hours"></span>
                                <div class="label">Giờ</div>
                            </div>
                            <div class="separator">:</div>
                            <div class="time-box">
                                <span id="minutes"></span>
                                <div class="label">Phút</div>
                            </div>
                            <div class="separator">:</div>
                            <div class="time-box">
                                <span id="seconds"></span>
                                <div class="label">Giây</div>
                            </div>
                        </div>
                        <a href="category?food=Vegetables" class="shop-now-btn">
                            <button class="mt-4 btn btn-light text-success position-relative fw-bold">Shop now
                                <i class="fas fa-arrow-right position-absolute"></i>
                            </button>
                        </a>
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <div class="pro-img2">
                        <h6>80% FAT FREE</h6>
                        <h1>Thịt ít chất béo</h1>
                        <h5>Chỉ từ <strong>200.000đ</strong></h5>
                        <a href="category?food=Meat" class="shop-now-btn">
                            <button class="mt-5 btn btn-light text-success position-relative fw-bold">Shop now
                                <i class="fas fa-arrow-right position-absolute"></i>
                            </button>
                        </a>
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <div class="pro-img3 text-dark">
                        <h6>SUMMER SALE</h6>
                        <h1>100% Trái cây tươi</h1>
                        <span>Lên tới
                            <span class="highlight">
                                <span class="badge">
                                    64% OFF
                                </span>
                            </span>
                        </span> <br>
                        <a href="category?food=Fruits" class="shop-now-btn">
                            <button class="mt-5 btn btn-light text-success position-relative fw-bold">Shop now
                                <i class="fas fa-arrow-right position-absolute"></i>
                            </button>
                        </a>
                    </div>
                </div>
            </div>
            <div class="row mobile-view">
                <swiper-container>
                    <swiper-slide>
                        <div class="col-md-4 mb-3">
                            <div class="pro-img1">
                                <h6>BEST DEALS</h6>
                                <h1>Sale tháng</h1>
                                <div id="countdown" style="color: white;">
                                    <div class="time-box">
                                        <span id="days"></span>
                                        <div class="label">Ngày</div>
                                    </div>
                                    <div class="separator">:</div>
                                    <div class="time-box">
                                        <span id="hours"></span>
                                        <div class="label">Giờ</div>
                                    </div>
                                    <div class="separator">:</div>
                                    <div class="time-box">
                                        <span id="minutes"></span>
                                        <div class="label">Phút</div>
                                    </div>
                                    <div class="separator">:</div>
                                    <div class="time-box">
                                        <span id="seconds"></span>
                                        <div class="label">Giây</div>
                                    </div>
                                </div>
                                <a href="" class="shop-now-btn">
                                    <button class="mt-4 btn btn-light text-success position-relative fw-bold">Shop now
                                        <i class="fas fa-arrow-right position-absolute"></i>
                                    </button>
                                </a>
                            </div>
                        </div>
                    </swiper-slide>
                    <swiper-slide>
                        <div class="col-md-4 mb-3">
                            <div class="pro-img2">
                                <h6>80% FAT FREE</h6>
                                <h1>Thịt ít chất béo</h1>
                                <h5>Chỉ từ <strong>$79.99</strong></h5>
                                <a href="" class="shop-now-btn">
                                    <button class="mt-5 btn btn-light text-success position-relative fw-bold">Shop now
                                        <i class="fas fa-arrow-right position-absolute"></i>
                                    </button>
                                </a>
                            </div>
                        </div>
                    </swiper-slide>
                    <swiper-slide>
                        <div class="col-md-4 mb-3">
                            <div class="pro-img3 text-dark">
                                <h6>SUMMER SALE</h6>
                                <h1>100% Trái cây tươi</h1>
                                <h5>Lên tới
                                    <div class="highlight">
                                        <div class="badge">
                                            64% OFF
                                        </div>
                                    </div>
                                </h5>
                                <a href="" class="shop-now-btn">
                                    <button class="mt-5 btn btn-light text-success position-relative fw-bold">Shop now
                                        <i class="fas fa-arrow-right position-absolute"></i>
                                    </button>
                                </a>
                            </div>
                        </div>
                    </swiper-slide>
                </swiper-container>
            </div>
            <div class="cate-menu">
                <h6
                    style="text-transform: uppercase; text-align: center; color: green; font-weight: bold; margin-top: 20px;">
                    Category</h6>
                <h3 style="text-transform: uppercase; text-align: center; font-weight: bold;">DANH MỤC SẢN PHẢM</h3>
                <div class="row cate">
                    <div class="col-md-2 mb-3">
                        <a href="category?food=Fruits">
                            <div class="card cate-img">
                                <img src="../images/cate001.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Trái cây</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="category?food=Vegetables">
                            <div class="card cate-img">
                                <img src="../images/pngtree-leafy-greens-assortment-png-image_13405275.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Rau</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="category?food=Seafood">
                            <div class="card cate-img">
                                <img src="../images/cate3.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Cá,Hải sản</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="category?food=Meat">
                            <div class="card cate-img">
                                <img src="../images/cate4.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Thịt</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="category?food=Egg">
                            <div class="card cate-img">
                                <img src="../images/pngtree-eggs-in-the-basket-png-image_11517687.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Trứng</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="category?food=Beverages">
                            <div class="card cate-img">
                                <img src="../images/cate6.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Đồ Uống</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="category?food=DairyProducts">
                            <div class="card cate-img">
                                <img src="../images/dinh-duong-cho-be-4-tuoi-sua.webp" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Thực Phẩm Từ Sữa</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="category?food=Tuber">
                            <div class="card cate-img">
                                <img src="../images/cate2.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Củ quả</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="category?food=Spices">
                            <div class="card cate-img">
                                <img src="../images/cate9.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Gia vị</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="category?food=CerealsNuts">
                            <div class="card cate-img">
                                <img src="../images/cate10.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Ngũ Cốc và Hạt</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="category?food=Snacks">
                            <div class="card cate-img">
                                <img src="../images/cate11.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Snacks</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="category?food=VegetableOil">
                            <div class="card cate-img">
                                <img src="../images/cate12.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Dầu thực vật</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <!--            <h6 style="text-transform: uppercase; text-align: center; color: green; font-weight: bold; margin-top: 60px;">
                            Products</h6>
                        <h3 style="text-transform: uppercase; text-align: center; font-weight: bold;">Sản phẩm nổi bật</h3>-->
            <div class="container-fluid">
                <!--                <div class="row g-2">
                                    <div class="col-md-3 list-img mt-3">
                                        <h6 style="text-transform: uppercase; font-weight: bold;">Mùa đông sale sập sàn</h6>
                                        <h1 style="text-transform: uppercase; color: #3f923f;">sale 75%</h1>
                                        <a href="category?food=Vegetables" class="shop-now-btn">
                                            <button
                                                class="mt-3 btn btn-light text-success position-relative fw-bold border border-success px-5">Mua
                                                ngay
                                                <i class="fas fa-arrow-right position-absolute"></i>
                                            </button>
                                        </a>
                                    </div>
                                    <div class="col-md-9 mt-3">
                                        <div class="row g-2">
                <c:forEach var="p" items="${noibat}"> 
                    <div class="col-lg-3 col-md-4 col-sm-6 col-4">
                        <div class="card outstand">
                            <a href="productDetail?id=${p.productId}"> 
                                <img src="${p.image}" class="card-img-top" alt="...">
                            </a>
                            <div class="icons">
                                <i class="fas fa-heart"></i>
                                <i class="fas fa-eye"></i>
                            </div>
                            <div class="card-body">
                                <h5 class="card-title fw-bold">${p.name}</h5>
                                <div class="row">
                                    <div class="col-md-6">
                                        <h6 class="fw-bold" style="margin-bottom: 0;">${p.unitPrice*(100-p.discount)/100}₫</h6>
                                        <h6 class="fw-bold" style="margin-bottom: 0;">
                    <fmt:formatNumber value="${(p.unitPrice * (100 - p.discount)) / 100}" type="currency" currencySymbol="₫" groupingUsed="true" />
                </h6>
                <span class=""
                      style="text-decoration: line-through; color: gray;font-size: 14px;">${p.unitPriceString}₫
                </span>
                <div>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star"></span>
                </div>
            </div>
            <div class="col-md-6 d-flex align-items-center buy-btn">
                <button class="fas fa-shopping-bag openCartBtn"></button>
            </div>
        </div>
        <div class="buy-mobile">
            <button class="btn btn-primary w-100">Mua</button>
        </div>
    </div>
</div>
</div>
                </c:forEach>
            </div>
        </div>
    </div>-->
                <div class="row mt-5">
                    <div class="col-md-3">
                        <h5 class="fw-bold">Khuyến mãi hấp dẫn</h5>
                        <c:forEach var="p" items="${khuyenMai}">
                            <div class="col-md-4 w-100">
                                <!--<a href="" style="text-decoration: none; color: unset;">-->
                                <div class="card mb-3">
                                    <div class="row g-0">
                                        <div class="col-4 d-flex align-items-center">
                                            <a href="productDetail?id=${p.productId}">
                                                <img src="${p.image}" class="img-fluid rounded-start" alt="...">
                                            </a>
                                        </div>
                                        <div class="col-8">
                                            <div class="card-body">
                                                <h6 class="card-title text-muted">${p.name}</h6>
                                                <p class="card-text fw-bold" style="margin: 0;">${p.unitPriceString}đ</p>
                                                <div>
                                                    <span class="fa fa-star checked"></span>
                                                    <span class="fa fa-star checked"></span>
                                                    <span class="fa fa-star checked"></span>
                                                    <span class="fa fa-star checked"></span>
                                                    <span class="fa fa-star"></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--</a>-->
                            </div>
                        </c:forEach>
                    </div>
                    <div class="col-md-3">
                        <h5 class="fw-bold">Bán chạy nhất</h5>
                        <c:forEach var="p" items="${banChay}">
                            <div class="col-md-4 w-100">
                                <!--<a href="" style="text-decoration: none; color: unset;">-->
                                <div class="card mb-3">
                                    <div class="row g-0">
                                        <div class="col-4 d-flex align-items-center">
                                            <a href="productDetail?id=${p.productId}">
                                                <img src="${p.image}" class="img-fluid rounded-start" alt="...">
                                            </a>
                                        </div>
                                        <div class="col-8">
                                            <div class="card-body">
                                                <h6 class="card-title text-muted">${p.name}</h6>
                                                <p class="card-text fw-bold" style="margin: 0;">${p.unitPriceString}đ</p>
                                                <div>
                                                    <span class="fa fa-star checked"></span>
                                                    <span class="fa fa-star checked"></span>
                                                    <span class="fa fa-star checked"></span>
                                                    <span class="fa fa-star checked"></span>
                                                    <span class="fa fa-star"></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--</a>-->
                            </div>
                        </c:forEach>
                    </div>
                    <div class="col-md-3">
                        <h5 class="fw-bold">Đánh giá tốt nhất</h5>
                        <c:forEach var="p" items="${danhGiaTot}">
                            <div class="col-md-4 w-100">
                                <!--<a href="" style="text-decoration: none; color: unset;">-->
                                <div class="card mb-3">
                                    <div class="row g-0">
                                        <div class="col-4 d-flex align-items-center">
                                            <a href="productDetail?id=${p.productId}">
                                                <img src="${p.image}" class="img-fluid rounded-start" alt="...">
                                            </a>
                                        </div>
                                        <div class="col-8">
                                            <div class="card-body">
                                                <h6 class="card-title text-muted text--wrap">${p.name}</h6>
                                                <p class="card-text fw-bold" style="margin: 0;">${p.unitPriceString}đ</p>
                                                <div>
                                                    <span class="fa fa-star checked"></span>
                                                    <span class="fa fa-star checked"></span>
                                                    <span class="fa fa-star checked"></span>
                                                    <span class="fa fa-star checked"></span>
                                                    <span class="fa fa-star"></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--</a>-->
                            </div>
                        </c:forEach>
                    </div>
                    <div class="col-md-3 sale text-center">
                        <h6>HOT SALE</h6>
                        <h1><strong>Giảm 37%</strong> trên mỗi hóa đơn</h1>
                        <a href="category?food=Fruits" class="shop-now-btn">
                            <button
                                class="btn btn-light text-success position-relative fw-bold border border-success px-5">Mua
                                ngay
                                <i class="fas fa-arrow-right position-absolute"></i>
                            </button>
                        </a>
                    </div>
                </div>
                <h3 style="font-weight: bold; text-align: center;margin-top: 30px;">Cửa Hàng Thực Phẩm Tươi Sống Tốt Nhất
                    Việt Nam</h3>
                <div class="video-container w-100 mb-5">
                    <video id="myVideo" src="../images/992601-hd_1920_1080_25fps.mp4" loop autoplay muted controls>
                        Your browser does not support the video tag.
                    </video>
                </div>
                <button class="play-pause-button play-icon" id="playPauseButton"></button>
            </div>
            <!-- đánh giá của client -->
            <div class="feedback">
                <h6 style="text-transform: uppercase; text-align: center; color: green; font-weight: bold; ">
                    Đánh giá của khách hàng</h6>
                <h2 style="text-align: center; font-weight: bold; margin-bottom: 30px;">Khách hàng của chúng tôi nói gì ?</h2>
                <div class="container">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="card">
                                <i class="fas fa-quote-right px-3 pt-3"
                                   style="color: rgb(149, 228, 149); font-size: 30px;"></i>
                                <div class="card-body">
                                    <p class="">Chất lượng sản phẩm: "Rau củ và thịt tươi ngon, luôn giữ được độ tươi mới. Tôi thấy yên tâm khi mua thực phẩm từ cửa hàng này."</p>
                                    <div class="row">
                                        <div class="col-2">
                                            <img class="rounded-circle" src="https://png.pngtree.com/thumb_back/fw800/background/20230410/pngtree-office-finance-and-accounting-concept-female-accountant-utilizing-calculator-and-computer-photo-image_51346942.jpg" width="50" height="50" alt="#">
                                        </div>
                                        <div class="col-6 px-4">
                                            <h6 class="text-dark fw-bold">Lê Thị Thanh Hà</h6>
                                            <p class="text-muted">Nhân viên kế toán</p>
                                        </div>
                                        <div class="col-4">
                                            <i class="fas fa-star checked"></i>
                                            <i class="fas fa-star checked"></i>
                                            <i class="fas fa-star checked"></i>
                                            <i class="fas fa-star checked"></i>
                                            <i class="fas fa-star checked"></i>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 feedback-mobile">
                            <div class="card">
                                <i class="fas fa-quote-right px-3 pt-3"
                                   style="color: rgb(149, 228, 149); font-size: 30px;"></i>
                                <div class="card-body">
                                    <p class="">Dịch vụ giao hàng: "Giao hàng rất nhanh và đúng giờ. Đặc biệt, đóng gói cẩn thận và luôn đảm bảo vệ sinh an toàn."</p>
                                    <div class="row">
                                        <div class="col-2">
                                            <img class="rounded-circle" src="https://scontent.fdad3-1.fna.fbcdn.net/v/t1.6435-9/110562117_918976985272435_3418741708224314419_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=6ee11a&_nc_eui2=AeEVwCT3KVoP2lEFZOW6j-vfR2PxS8uws7hHY_FLy7CzuLcaPCSXcSq5I3HSqDtwJFSWH4Dp8rzzb5qfH2IZKWfU&_nc_ohc=6unPrIlf4fkQ7kNvgEv66V8&_nc_zt=23&_nc_ht=scontent.fdad3-1.fna&_nc_gid=AiHRJaPc3ZqgXh1BZsrgfj5&oh=00_AYAfPqaxRbnuSaEEcyH998J7PjRaQ3cfjO2Wy9pYyoN1gA&oe=6745669D" width="50" height="50" alt="#">
                                        </div>
                                        <div class="col-6">
                                            <h6 class="text-dark fw-bold">Phạm Lê Hoàng Nam</h6>
                                            <p class="text-muted">Sinh viên đại học</p>
                                        </div>
                                        <div class="col-4">
                                            <i class="fas fa-star checked"></i>
                                            <i class="fas fa-star checked"></i>
                                            <i class="fas fa-star checked"></i>
                                            <i class="fas fa-star checked"></i>
                                            <i class="fas fa-star checked"></i>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 feedback-mobile">
                            <div class="card">
                                <i class="fas fa-quote-right px-3 pt-3"
                                   style="color: rgb(149, 228, 149); font-size: 30px;"></i>
                                <div class="card-body">
                                    <p class="">Đa dạng sản phẩm: "Có rất nhiều lựa chọn cho rau củ, thịt, và hải sản. Tôi có thể tìm thấy hầu hết những gì tôi cần cho bữa ăn hàng ngày."</p>
                                    <div class="row">
                                        <div class="col-2">
                                            <img class="rounded-circle" src="https://cdnimage.baucuquochoi.vn/t400x500/Media/Graphic/Profile/2021/05/06/ca-mau5661-jpg-le-manh-hung.jpg" width="50" height="50" alt="#">
                                        </div>
                                        <div class="col-6">
                                            <h6 class="text-dark fw-bold">Lê Mạnh Hùng</h6>
                                            <p class="text-muted">Doanh nhân, Giám đốc</p>
                                        </div>
                                        <div class="col-4">
                                            <i class="fas fa-star checked"></i>
                                            <i class="fas fa-star checked"></i>
                                            <i class="fas fa-star checked"></i>
                                            <i class="fas fa-star checked"></i>
                                            <i class="fas fa-star checked"></i>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Giỏ hàng popup -->
    
    <button onclick="topFunction()" id="myBtn" title="Go to top">
        <i class="fas fa-arrow-up"></i>
    </button>
    <%@ include file="./Footer.jsp" %>
    <script src="../js/bootstrap.bundle.min.js"></script>
    <script src="../js/authJs/homePageTest.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-element-bundle.min.js"></script>
    <script>
        //btn go to top
        let mybutton = document.getElementById("myBtn");
        // When the user scrolls down 20px from the top of the document, show the button
        window.onscroll = function () {
            scrollFunction();
        };

        function scrollFunction() {
            if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                mybutton.style.display = "block";
            } else {
                mybutton.style.display = "none";
            }
        }

        // When the user clicks on the button, scroll to the top of the document
        function topFunction() {
            document.body.scrollTop = 0;
            document.documentElement.scrollTop = 0;
        }
    </script>
</body>

</html>
