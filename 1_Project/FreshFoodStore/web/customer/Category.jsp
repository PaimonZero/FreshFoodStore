<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Category</title>
        
        <!--bootstrap 5.3.3-->
        <link rel="stylesheet" href="../css/bootstrap.min.css"> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <script src="https://kit.fontawesome.com/54f0cb7e4a.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="../css/customerCss/Category.css">
    </head>
    <body>
        <%@include file="HeaderLogin1.jsp" %>
        <div class="container" style="padding-top: 160px;">
            <div class="row">
                <div class="col-md-3">
                    <form action="">
                        <div class="d-flex fil-btn">
                            <button type="submit" class="position-relative">Lọc
                                <i class="bi bi-sliders"></i>
                            </button>
                        </div>
                        <div class="accordion mt-1" id="accordionExample">
                            <!-- Accordion 1 -->
                            <!-- <div class="accordion-item"> -->
                            <h2 class="accordion-header" id="headingOne">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    Tất cả sản phẩm
                                </button>
                            </h2>
                            <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne">
                                <div class="accordion-body">
                                    <ul class="list-unstyled list-unstyled-cate ">
                                        <li><input type="radio" name="food" checked />Trái cây tươi (25)<span
                                                class="lighter-text">(134)</span></li>
                                        <li><input type="radio" name="food" />Rau củ <span class="lighter-text">(150)</span>
                                        </li>
                                        <li><input type="radio" name="food" />Cá, hải sản <span
                                                class="lighter-text">(54)</span></li>
                                        <li><input type="radio" name="food" />Thịt <span class="lighter-text">(47)</span>
                                        </li>
                                        <li><input type="radio" name="food" />Nấm <span class="lighter-text">(43)</span>
                                        </li>
                                        <li><input type="radio" name="food" />Thực phẩm đông lạnh <span
                                                class="lighter-text">(38)</span></li>
                                        <li><input type="radio" name="food" />Gia vị <span class="lighter-text">(15)</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <!-- </div> -->
                            <hr>

                            <!-- Accordion 2 -->
                            <!-- <div class="accordion-item"> -->
                            <h2 class="accordion-header" id="headingTwo">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                                    Mức giá
                                </button>
                            </h2>
                            <div id="collapseTwo" class="accordion-collapse collapse show" aria-labelledby="headingTwo">
                                <div class="accordion-body">
                                    <ul class="list-unstyled">
                                        <li>
                                            <div class="range-slider">
                                                <div class="slider">
                                                    <div class="track"></div>
                                                    <input type="range" min="1000" max="1000000" value="50000"
                                                           id="min-range">
                                                    <input type="range" min="1000" max="1000000" value="800000"
                                                           id="max-range">
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="values d-flex align-items-center">
                                                Giá từ: &nbsp;
                                                <span id="min-value"></span>
                                                &nbsp; - &nbsp;
                                                <span id="max-value"></span>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <hr>
                            <div class="discount-banner text-center">
                                <h3><strong>79%</strong> Discount</h3>
                                <h6>On your first order</h6>
                                <div class="mt-3">
                                    <a href="#">Mua ngay
                                        <i class="fas fa-arrow-right text-success"></i>
                                    </a>
                                </div>

                            </div>
                            <div class="mt-4">
                                <h5>Sản phẩm sales</h5>
                                <a href="#" style="text-decoration: none; color: unset;">
                                    <div class="card mb-3">
                                        <div class="row g-0">
                                            <div class="col-md-4">
                                                <img src="../images/fil2.png" class="img-fluid rounded-start" alt="...">
                                            </div>
                                            <div class="col-md-8">
                                                <div class="card-body sale-card">
                                                    <h6 class="card-title">Ớt chuông</h6>
                                                    <p class="card-text fw-bold" style="margin: 0;">80.000đ &emsp;&emsp;
                                                        <span class="fw-lighter"
                                                              style="text-decoration: line-through; color: gray;font-size: 14px;">100.000₫
                                                        </span>
                                                    </p>
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
                                <a href="#" style="text-decoration: none; color: unset;">
                                    <div class="card mb-3">
                                        <div class="row g-0">
                                            <div class="col-md-4">
                                                <img src="../images/fil3.png" class="img-fluid rounded-start" alt="...">
                                            </div>
                                            <div class="col-md-8">
                                                <div class="card-body sale-card">
                                                    <h6 class="card-title">Xoài</h6>
                                                    <p class="card-text fw-bold" style="margin: 0;">80.000đ &emsp;&emsp;
                                                        <span class="fw-lighter"
                                                              style="text-decoration: line-through; color: gray;font-size: 14px;">100.000₫
                                                        </span>
                                                    </p>
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
                                <a href="#" style="text-decoration: none; color: unset;">
                                    <div class="card mb-3">
                                        <div class="row g-0">
                                            <div class="col-md-4">
                                                <img src="../images/fil4.png" class="img-fluid rounded-start" alt="...">
                                            </div>
                                            <div class="col-md-8">
                                                <div class="card-body sale-card">
                                                    <h6 class="card-title">Ớt chuông xanh</h6>
                                                    <p class="card-text fw-bold" style="margin: 0;">80.000đ &emsp;&emsp;
                                                        <span class="fw-lighter"
                                                              style="text-decoration: line-through; color: gray;font-size: 14px;">100.000₫
                                                        </span>
                                                    </p>
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
                    </form>
                </div>
                <div class="col-md-9">
                    <div class="content-right-top d-flex">
                        <h6>Sắp xếp: </h6>
                        <select>
                            <option value="">Latest</option>
                            <option value="">Giá tăng dần</option>
                            <option value="">Giá giảm dần</option>
                            <option value="">Sản phẩm mới nhất</option>
                            <option value="">Sản phẩm bán chạy nhất</option>
                        </select>
                        <div style="margin-left: auto;">
                            <span><strong>52</strong> Kết quả tìm thấy</span>
                        </div>
                    </div>
                    <div class="row g-2 mt-2">
                        <div class="col-lg-4 col-md-4 col-sm-6 col-4">
                            <div class="card outstand">
                                <img src="../images/list2.png" class="card-img-top" alt="...">
                                <div class="btn btn-dark out-stock-btn">Out of stock</div>
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
                        <div class="col-lg-4 col-md-4 col-sm-6 col-4">
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
                        <div class="col-lg-4 col-md-4 col-sm-6 col-4">
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
                        <div class="col-lg-4 col-md-4 col-sm-6 col-4">
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
                        <div class="col-lg-4 col-md-4 col-sm-6 col-4">
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
                        <div class="col-lg-4 col-md-4 col-sm-6 col-4">
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
                        <!-- cấm xóa cáy này dùng để trỏ tới product detail -->
                        <!-- <form action=""> -->
                        <div class="col-lg-4 col-md-4 col-sm-6 col-4">
                            <div class="card outstand">
                                <img src="../images/list2.png" class="card-img-top" style="cursor: pointer;" alt="..."
                                     onclick="redirectToHref(event)">
                                <div class="icons">
                                    <i class="fas fa-heart"></i>
                                    <i class="fas fa-eye"></i>
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title fw-bold" style="cursor: pointer;" onclick="redirectToHref(event)">
                                        Cải thảo</h5>
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
                        <!-- </form> -->
                        <!-- <form action=""> -->
                        <div class="col-lg-4 col-md-4 col-sm-6 col-4">
                            <div class="card outstand">
                                <img src="../images/list2.png" class="card-img-top" style="cursor: pointer;" alt="..."
                                     onclick="redirectToHref(event)">
                                <div class="icons">
                                    <i class="fas fa-heart"></i>
                                    <i class="fas fa-eye"></i>
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title fw-bold" style="cursor: pointer;" onclick="redirectToHref(event)">
                                        Cải thảo</h5>
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
                        <!-- </form> -->
                        <!-- <form action=""> -->
                        <div class="col-lg-4 col-md-4 col-sm-6 col-4">
                            <div class="card outstand">
                                <img src="../images/list2.png" class="card-img-top" style="cursor: pointer;" alt="..."
                                     onclick="redirectToHref(event)">
                                <div class="icons">
                                    <i class="fas fa-heart"></i>
                                    <i class="fas fa-eye"></i>
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title fw-bold" style="cursor: pointer;" onclick="redirectToHref(event)">
                                        Cải thảo</h5>
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
                        <!-- </form> -->
                    </div>
                    <!-- đánh số trang chú ý: tạm thời fix cứng-->
                    <div class="pagination">
                        <a class="fas fa-angle-left arrow" href="#"></a>
                        <a class="current" href="#">1</a>
                        <a href="#">2</a>
                        <a href="#">3</a>
                        <a href="#">4</a>
                        <a href="#">5</a>
                        <span class="dots">...</span>
                        <a href="#">21</a>
                        <a class="fas fa-angle-right arrow" href="#"></a>
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
            </div>
        </div>
        <%@ include file="Footer.jsp" %>
        <script src="../js/bootstrap.bundle.min.js"></script>
        <script src="../js/authJs/Category.js"></script>
    </body>
</html>
