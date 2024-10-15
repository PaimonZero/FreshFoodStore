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
        <link rel="stylesheet" href="../css/customerCss/homePage.css"/>
        <style>
            #cartSidebar {
    position: fixed;
    top: 0;
    width: 36%;
    right: -40%;
    height: 100%;
    background-color: white;
    box-shadow: -2px 0 5px rgba(0, 0, 0, 0.2);
    transition: right 0.3s ease;
    z-index: 1050;
    display: flex;
    flex-direction: column;
}

#cartSidebar.show {
    right: 0;
}

.cart-header {
    color: black;
    padding: 15px;
    font-size: 18px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.cart-header .btn-close {
    background: none;
    border: none;
    color: black;
    font-size: 20px;
}

.cart-items {
    flex: 1; /* Take available space */
    overflow-y: auto; /* Enable vertical scrolling */
}

.cart-item {
    border-bottom: 1px solid #eaeaea;
    padding: 10px;
}

.cart-item img {
    width: 50px;
    height: 50px;
    object-fit: cover;
}

.cart-item .item-details {
    flex: 1;
    margin-left: 15px;
}

.cart-item .item-total-price {
    font-weight: bold;
}

.cart-footer {
    padding: 15px;
    background-color: #f8f9fa;
}

.checkout-btn {
    border-radius: 50px;
    background-color: rgb(54, 157, 54);
    color: white;
    border: none;
    padding: 10px;
}

.checkout-btn:hover {
    background-color: rgb(65, 189, 65);
}

.goto-cart {
    border-radius: 50px;
    background-color: #ddd;
    color: rgb(54, 157, 54);
    border: none;
    padding: 10px;
}

.qty-container {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 60%;
}

.qty-container .input-qty {
    text-align: center;
    padding: 6px 10px;
    border: 1px solid #d4d4d4;
    width: 45%;
    height: 25px;
}

.qty-container .qty-btn-minus,
.qty-container .qty-btn-plus {
    border: 1px solid #d4d4d4;
    font-size: 10px;
    height: 25px;
    width: 18%;
    transition: 0.3s;
}

.qty-container .qty-btn-plus {
    border-top-right-radius: 20px;
    border-bottom-right-radius: 20px;
    background-color: rgb(104, 200, 104);
}

.qty-container .qty-btn-minus {
    border-top-left-radius: 20px;
    border-bottom-left-radius: 20px;
    background-color: rgb(255, 102, 102);
}

/* chỉnh nút mua ngay dạng mobile */
@media (max-width: 768px) {
    .buy-btn button {
        display: none;
    }

    .outstand h5 {
        font-size: 17px;
    }

    .pagination a {
        width: 9%;
        height: 30px;
    }
}

@media (min-width: 768px) {
    .buy-mobile {
        display: none;
    }
}

        </style>
    </head>
    <body>
        <%@include file="HeaderLogin1.jsp" %>
        <div class="container-fluid mb-5" style="padding-top: 130px;">
            <div class="row">
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
                        <a href="" class="shop-now-btn">
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
                        <h5>Chỉ từ <strong>$79.99</strong></h5>
                        <a href="" class="shop-now-btn">
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
            </div>
            <div class="cate-menu">
                <h6
                    style="text-transform: uppercase; text-align: center; color: green; font-weight: bold; margin-top: 20px;">
                    Category</h6>
                <h3 style="text-transform: uppercase; text-align: center; font-weight: bold;">DANH MỤC SẢN PHẢM</h3>
                <div class="row cate">
                    <div class="col-md-2 mb-3">
                        <a href="">
                            <div class="card cate-img">
                                <img src="../images/cate001.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Trái cây</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="">
                            <div class="card cate-img">
                                <img src="../images/cate2.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Rau củ</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="">
                            <div class="card cate-img">
                                <img src="../images/cate3.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Thịt & Cá</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="">
                            <div class="card cate-img">
                                <img src="../images/cate4.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Đồ Đông Lạnh</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="">
                            <div class="card cate-img">
                                <img src="../images/pngtree-eggs-in-the-basket-png-image_11517687.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Trứng</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="">
                            <div class="card cate-img">
                                <img src="../images/cate6.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Đồ Uống</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="">
                            <div class="card cate-img">
                                <img src="../images/cate7.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Hải sản</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="">
                            <div class="card cate-img">
                                <img src="../images/Nam-rom-Cac-loai-nam-an-duoc.jpg" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Nấm</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="">
                            <div class="card cate-img">
                                <img src="../images/cate9.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Gia vị</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="">
                            <div class="card cate-img">
                                <img src="../images/cate10.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Thực phẩm giàu protein</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="">
                            <div class="card cate-img">
                                <img src="../images/cate11.png" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title text-center fw-bold">Snacks</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-2 mb-3">
                        <a href="">
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
            <h6 style="text-transform: uppercase; text-align: center; color: green; font-weight: bold; margin-top: 60px;">
                Products</h6>
            <h3 style="text-transform: uppercase; text-align: center; font-weight: bold;">Sản phẩm nổi bật</h3>
            <div class="container-fluid">
                <div class="row g-2">
                    <div class="col-md-3 list-img mt-3">
                        <h6 style="text-transform: uppercase; font-weight: bold;">Mùa đông sale sập sàn</h6>
                        <h1 style="text-transform: uppercase; color: #3f923f;">sale 75%</h1>
                        <a href="" class="shop-now-btn">
                            <button
                                class="mt-3 btn btn-light text-success position-relative fw-bold border border-success px-5">Mua
                                ngay
                                <i class="fas fa-arrow-right position-absolute"></i>
                            </button>
                        </a>
                    </div>
                    <div class="col-md-9 mt-3">
                        <div class="row g-2">
                            <div class="col-lg-3 col-md-4 col-sm-6 col-4">
                                <div class="card outstand">
                                    <img src="../images/list2.png" class="card-img-top" alt="...">
                                    <div class="icons">
                                        <i class="fas fa-heart"></i>
                                        <i class="fas fa-eye"></i>
                                    </div>
                                    <div class="card-body">
                                        <h5 class="card-title fw-bold">Cải thảo</h5>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <h6 class="fw-bold" style="margin-bottom: 0;">150.000₫</h6>
                                                <span class=""
                                                      style="text-decoration: line-through; color: gray;font-size: 14px;">200.000₫
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
                            <div class="col-lg-3 col-md-4 col-sm-6 col-4">
                                <div class="card outstand">
                                    <img src="../images/list3.png" class="card-img-top" alt="...">
                                    <div class="icons">
                                        <i class="fas fa-heart"></i>
                                        <i class="fas fa-eye"></i>
                                    </div>
                                    <div class="card-body">
                                        <h5 class="card-title fw-bold">Xà lách</h5>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <h6 class="fw-bold" style="margin-bottom: 0;">150.000₫</h6>
                                                <span class=""
                                                      style="text-decoration: line-through; color: gray;font-size: 14px;">200.000đ
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
                            <div class="col-lg-3 col-md-4 col-sm-6 col-4">
                                <div class="card outstand">
                                    <img src="../images/list4.png" class="card-img-top" alt="...">
                                    <div class="icons">
                                        <i class="fas fa-heart"></i>
                                        <i class="fas fa-eye"></i>
                                    </div>
                                    <div class="card-body">
                                        <h5 class="card-title fw-bold">Ớt xanh</h5>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <h6 class="fw-bold" style="margin-bottom: 0;">150.000₫</h6>
                                                <span class=""
                                                      style="text-decoration: line-through; color: gray;font-size: 14px;">200.000₫
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
                            <div class="col-lg-3 col-md-4 col-sm-6 col-4">
                                <div class="card outstand">
                                    <img src="../images/list5.png" class="card-img-top" alt="...">
                                    <div class="icons">
                                        <i class="fas fa-heart"></i>
                                        <i class="fas fa-eye"></i>
                                    </div>
                                    <div class="card-body">
                                        <h5 class="card-title fw-bold">Bắp</h5>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <h6 class="fw-bold" style="margin-bottom: 0;">150.000₫</h6>
                                                <span class=""
                                                      style="text-decoration: line-through; color: gray;font-size: 14px;">200.000₫
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
                        </div>
                    </div>
                </div>
                <div class="row mt-5">
                    <div class="col-md-3">
                        <h5 class="fw-bold">Khuyến mãi hấp dẫn</h5>
                        <div class="col-md-4 w-100">
                            <a href="" style="text-decoration: none; color: unset;">
                                <div class="card mb-3">
                                    <div class="row g-0">
                                        <div class="col-4">
                                            <img src="../images/sale1.png" class="img-fluid rounded-start" alt="...">
                                        </div>
                                        <div class="col-8">
                                            <div class="card-body">
                                                <h6 class="card-title text-muted">Táo xanh</h6>
                                                <p class="card-text fw-bold" style="margin: 0;">35.000đ</p>
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
                            </a>
                        </div>
                        <div class="col-md-4 w-100">
                            <a href="" style="text-decoration: none; color: unset;">
                                <div class="card mb-3">
                                    <div class="row g-0">
                                        <div class="col-4">
                                            <img src="../images/sale2.png" class="img-fluid rounded-start" alt="...">
                                        </div>
                                        <div class="col-8">
                                            <div class="card-body">
                                                <h6 class="card-title text-muted">Cam</h6>
                                                <p class="card-text fw-bold" style="margin: 0;">35.000đ</p>
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
                            </a>
                        </div>
                        <div class="col-md-4 w-100">
                            <a href="" style="text-decoration: none; color: unset;">
                                <div class="card mb-3">
                                    <div class="row g-0">
                                        <div class="col-4">
                                            <img src="../images/sale3.png" class="img-fluid rounded-start" alt="...">
                                        </div>
                                        <div class="col-8">
                                            <div class="card-body">
                                                <h6 class="card-title text-muted">Xà lách</h6>
                                                <p class="card-text fw-bold" style="margin: 0;">200.000đ</p>
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
                            </a>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <h5 class="fw-bold">Bán chạy nhất</h5>
                        <div class="col-md-4 w-100">
                            <a href="" style="text-decoration: none; color: unset;">
                                <div class="card mb-3">
                                    <div class="row g-0">
                                        <div class="col-4">
                                            <img src="../images/sale4.png" class="img-fluid rounded-start" alt="...">
                                        </div>
                                        <div class="col-8">
                                            <div class="card-body">
                                                <h6 class="card-title text-muted">Cà tím</h6>
                                                <p class="card-text fw-bold" style="margin: 0;">120.000đ</p>
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
                            </a>
                        </div>
                        <div class="col-md-4 w-100">
                            <a href="" style="text-decoration: none; color: unset;">
                                <div class="card mb-3">
                                    <div class="row g-0">
                                        <div class="col-4">
                                            <img src="../images/sale5.png" class="img-fluid rounded-start" alt="...">
                                        </div>
                                        <div class="col-8">
                                            <div class="card-body">
                                                <h6 class="card-title text-muted">Đậu bắp</h6>
                                                <!-- &emsp; là tạo 4 khoảng cách trắng -->
                                                <p class="card-text fw-bold" style="margin: 0;">100.000đ &emsp;&emsp;<span
                                                        class=""
                                                        style="text-decoration: line-through; color: gray;font-size: 14px;">200.000₫
                                                    </span> </p>
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
                            </a>
                        </div>
                        <div class="col-md-4 w-100">
                            <a href="" style="text-decoration: none; color: unset;">
                                <div class="card mb-3">
                                    <div class="row g-0">
                                        <div class="col-4">
                                            <img src="../images/sale6.png" class="img-fluid rounded-start" alt="...">
                                        </div>
                                        <div class="col-8">
                                            <div class="card-body">
                                                <h6 class="card-title text-muted">Cà chua</h6>
                                                <p class="card-text fw-bold" style="margin: 0;">60.000đ</p>
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
                            </a>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <h5 class="fw-bold">Đánh giá tốt nhất</h5>
                        <div class="col-md-4 w-100">
                            <a href="" style="text-decoration: none; color: unset;">
                                <div class="card mb-3">
                                    <div class="row g-0">
                                        <div class="col-4">
                                            <img src="../images/sale7.png" class="img-fluid rounded-start" alt="...">
                                        </div>
                                        <div class="col-8">
                                            <div class="card-body">
                                                <h6 class="card-title text-muted">Khoai tây</h6>
                                                <p class="card-text fw-bold" style="margin: 0;">50.000đ</p>
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
                            </a>
                        </div>
                        <div class="col-md-4 w-100">
                            <a href="" style="text-decoration: none; color: unset;">
                                <div class="card mb-3">
                                    <div class="row g-0">
                                        <div class="col-4">
                                            <img src="../images/sale8.png" class="img-fluid rounded-start" alt="...">
                                        </div>
                                        <div class="col-8">
                                            <div class="card-body">
                                                <h6 class="card-title text-muted">Bắp</h6>
                                                <p class="card-text fw-bold" style="margin: 0;">80.000đ &emsp;&emsp; <span
                                                        class=""
                                                        style="text-decoration: line-through; color: gray;font-size: 14px;">100.000₫
                                                    </span></p>
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
                            </a>
                        </div>
                        <div class="col-md-4 w-100">
                            <a href="" style="text-decoration: none; color: unset;">
                                <div class="card mb-3">
                                    <div class="row g-0">
                                        <div class="col-4">
                                            <img src="../images/sale9.png" class="img-fluid rounded-start" alt="...">
                                        </div>
                                        <div class="col-8">
                                            <div class="card-body">
                                                <h6 class="card-title text-muted">Súp lơ</h6>
                                                <p class="card-text fw-bold" style="margin: 0;">220.000đ</p>
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
                            </a>
                        </div>
                    </div>
                    <div class="col-md-3 sale text-center">
                        <h6>HOT SALE</h6>
                        <h1><strong>Giảm 37%</strong> trên mỗi hóa đơn</h1>
                        <a href="" class="shop-now-btn">
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
                    <video id="myVideo" loop>
                        <source src="../images/992601-hd_1920_1080_25fps.mp4" type="video/mp4">
                    </video>
                    <button class="play-pause-button play-icon" id="playPauseButton"></button>
                </div>
                <!-- đánh giá của client -->
                <div class="feedback">
                    <h6 style="text-transform: uppercase; text-align: center; color: green; font-weight: bold; ">
                        Client Testiomial</h6>
                    <h2 style="text-align: center; font-weight: bold; margin-bottom: 30px;">What our Client Says</h2>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="card">
                                    <i class="fas fa-quote-right px-3 pt-3"
                                       style="color: rgb(149, 228, 149); font-size: 30px;"></i>
                                    <div class="card-body">
                                        <p class="">Lorem ipsum dolor sit amet consectetur, adipisicing elit. Natus tempora
                                            voluptate vel quia autem laboriosam ut dolores illo voluptatum harum odio
                                            delectus
                                            reiciendis earum cum dolorem fugiat doloribus, numquam laborum!</p>
                                        <div class="row">
                                            <div class="col-2">
                                                <img src="../images/user1.png" width="50" alt="#">
                                            </div>
                                            <div class="col-6 px-4">
                                                <h6 class="text-dark fw-bold">John Doe</h6>
                                                <p class="text-muted">Customer Service</p>
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
                                        <p class="">Lorem ipsum dolor sit amet consectetur, adipisicing elit. Natus tempora
                                            voluptate vel quia autem laboriosam ut dolores illo voluptatum harum odio
                                            delectus
                                            reiciendis earum cum dolorem fugiat doloribus, numquam laborum!</p>
                                        <div class="row">
                                            <div class="col-2">
                                                <img src="../images/user1.png" width="50" alt="#">
                                            </div>
                                            <div class="col-6">
                                                <h6 class="text-dark fw-bold">John Doe</h6>
                                                <p class="text-muted">Customer Service</p>
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
                                        <p class="">Lorem ipsum dolor sit amet consectetur, adipisicing elit. Natus tempora
                                            voluptate vel quia autem laboriosam ut dolores illo voluptatum harum odio
                                            delectus
                                            reiciendis earum cum dolorem fugiat doloribus, numquam laborum!</p>
                                        <div class="row">
                                            <div class="col-2">
                                                <img src="../images/user1.png" width="50" alt="#">
                                            </div>
                                            <div class="col-6">
                                                <h6 class="text-dark fw-bold">John Doe</h6>
                                                <p class="text-muted">Customer Service</p>
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
        <div id="cartSidebar">
            <div class="cart-header">
                <span>Giỏ Hàng (2)</span>
                <button class="btn-close fas fa-times" id="closeCartBtn"></button>
            </div>
            <div class="cart-items">
                <div class="cart-item d-flex align-items-center">
                    <img src="https://via.placeholder.com/50" alt="Product 1">
                    <div class="item-details">
                        <h6>Sản phẩm 1</h6>
                        <div class="item-price">Giá: $10</div>
                        <div class="item-quantity d-flex align-items-center">
                            <span class="me-2">Số lượng:</span>
                            <div class="qty-container">
                                <button class="qty-btn-minus btn-light" type="button"><i
                                        class="fa fa-minus"></i></button>
                                <input type="text" name="qty" value="2" class="input-qty" />
                                <button class="qty-btn-plus btn-light" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </div>
                        </div>
                    </div>
                    <div class="item-total-price">Tổng: $20</div>
                    <button class="btn ms-3 fas fa-trash-alt text-danger"></button>
                </div>

                <div class="cart-item d-flex align-items-center">
                    <img src="https://via.placeholder.com/50" alt="Product 2">
                    <div class="item-details">
                        <h6>Sản phẩm 2</h6>
                        <div class="item-price">Giá: $25</div>
                        <div class="item-quantity d-flex align-items-center">
                            <span class="me-2">Số lượng:</span>
                            <div class="qty-container">
                                <button class="qty-btn-minus btn-light" type="button"><i
                                        class="fa fa-minus"></i></button>
                                <input type="text" name="qty" value="2" class="input-qty" />
                                <button class="qty-btn-plus btn-light" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </div>
                        </div>
                    </div>
                    <div class="item-total-price">Tổng: $25</div>
                    <button class="btn ms-3 fas fa-trash-alt text-danger"></button>
                </div>
                <div class="cart-item d-flex align-items-center">
                    <img src="https://via.placeholder.com/50" alt="Product 2">
                    <div class="item-details">
                        <h6>Sản phẩm 2</h6>
                        <div class="item-price">Giá: $25</div>
                        <div class="item-quantity d-flex align-items-center">
                            <span class="me-2">Số lượng:</span>
                            <div class="qty-container">
                                <button class="qty-btn-minus btn-light" type="button"><i
                                        class="fa fa-minus"></i></button>
                                <input type="text" name="qty" value="2" class="input-qty" />
                                <button class="qty-btn-plus btn-light" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </div>
                        </div>
                    </div>
                    <div class="item-total-price">Tổng: $25</div>
                    <button class="btn ms-3 fas fa-trash-alt text-danger"></button>
                </div>
                <div class="cart-item d-flex align-items-center">
                    <img src="https://via.placeholder.com/50" alt="Product 2">
                    <div class="item-details">
                        <h6>Sản phẩm 2</h6>
                        <div class="item-price">Giá: $25</div>
                        <div class="item-quantity d-flex align-items-center">
                            <span class="me-2">Số lượng:</span>
                            <div class="qty-container">
                                <button class="qty-btn-minus btn-light" type="button"><i
                                        class="fa fa-minus"></i></button>
                                <input type="text" name="qty" value="2" class="input-qty" />
                                <button class="qty-btn-plus btn-light" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </div>
                        </div>
                    </div>
                    <div class="item-total-price">Tổng: $25</div>
                    <button class="btn ms-3 fas fa-trash-alt text-danger"></button>
                </div>
                <div class="cart-item d-flex align-items-center">
                    <img src="https://via.placeholder.com/50" alt="Product 2">
                    <div class="item-details">
                        <h6>Sản phẩm 2</h6>
                        <div class="item-price">Giá: $25</div>
                        <div class="item-quantity d-flex align-items-center">
                            <span class="me-2">Số lượng:</span>
                            <div class="qty-container">
                                <button class="qty-btn-minus btn-light" type="button"><i
                                        class="fa fa-minus"></i></button>
                                <input type="text" name="qty" value="2" class="input-qty" />
                                <button class="qty-btn-plus btn-light" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </div>
                        </div>
                    </div>
                    <div class="item-total-price">Tổng: $25</div>
                    <button class="btn ms-3 fas fa-trash-alt text-danger"></button>
                </div>
                <div class="cart-item d-flex align-items-center">
                    <img src="https://via.placeholder.com/50" alt="Product 2">
                    <div class="item-details">
                        <h6>Sản phẩm 2</h6>
                        <div class="item-price">Giá: $25</div>
                        <div class="item-quantity d-flex align-items-center">
                            <span class="me-2">Số lượng:</span>
                            <div class="qty-container">
                                <button class="qty-btn-minus btn-light" type="button"><i
                                        class="fa fa-minus"></i></button>
                                <input type="text" name="qty" value="2" class="input-qty" />
                                <button class="qty-btn-plus btn-light" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </div>
                        </div>
                    </div>
                    <div class="item-total-price">Tổng: $25</div>
                    <button class="btn ms-3 fas fa-trash-alt text-danger"></button>
                </div>
                <div class="cart-item d-flex align-items-center">
                    <img src="https://via.placeholder.com/50" alt="Product 2">
                    <div class="item-details">
                        <h6>Sản phẩm 2</h6>
                        <div class="item-price">Giá: $25</div>
                        <div class="item-quantity d-flex align-items-center">
                            <span class="me-2">Số lượng:</span>
                            <div class="qty-container">
                                <button class="qty-btn-minus btn-light" type="button"><i
                                        class="fa fa-minus"></i></button>
                                <input type="text" name="qty" value="2" class="input-qty" />
                                <button class="qty-btn-plus btn-light" type="button"><i
                                        class="fa fa-plus"></i></button>
                            </div>
                        </div>
                    </div>
                    <div class="item-total-price">Tổng: $25</div>
                    <button class="btn ms-3 fas fa-trash-alt text-danger"></button>
                </div>
            </div>
            <div class="cart-footer">
                <span class="total-price fw-bold">Tổng cộng: $45</span>
                <br>
                <button type="button" class="w-100 mt-2 checkout-btn">Thanh Toán</button>
                <button type="button" class="w-100 mt-2 goto-cart">Đến trang giỏ hàng</button>
            </div>
        </div>
        <button onclick="topFunction()" id="myBtn" title="Go to top">
            <i class="fas fa-arrow-up"></i>
        </button>
        <%@ include file="Footer.jsp" %>
        <script src="../js/bootstrap.bundle.min.js"></script>
        
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
        <script src="../js/authJs/homePage.js"></script>
    </body>

</html>
