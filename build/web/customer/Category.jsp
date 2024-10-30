<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
        <link rel="shotcut icon" href="../images/logoFFSNoBG.png"/>
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
                                        <li><input type="radio" name="food" value="Fruits" checked />Trái cây tươi<span
                                                class="lighter-text">(${traicay})</span></li>
                                        <li><input type="radio" name="food" value="Vegetables" />Rau củ <span class="lighter-text">(${raucu})</span>
                                        </li>
                                        <li><input type="radio" name="food" value="Seafood" />Cá, hải sản <span
                                                class="lighter-text">(${haisan})</span></li>
                                        <li><input type="radio" name="food" value="Meat" />Thịt <span class="lighter-text">(${thit})</span>
                                        </li>
                                        <li><input type="radio" name="food" value="Beverages" />Đồ uống <span class="lighter-text">(${douong})</span>
                                        </li>
                                        <li><input type="radio" name="food" value="Snacks" />Snack <span
                                                class="lighter-text">(${snack})</span></li>
                                        <li><input type="radio" name="food" value="Spices" />Gia vị <span class="lighter-text">(${giavi})</span>
                                        </li>
                                        <li><input type="radio" name="food" value="VegetableOil" />Dầu thực vật <span class="lighter-text">(${dauthucvat})</span>
                                        </li>
                                        <li><input type="radio" name="food" value="Egg" />Trứng <span class="lighter-text">(${trung})</span>
                                        </li>
                                        <li><input type="radio" name="food" value="CerealsNuts" />Ngũ Cốc và Hạt<span class="lighter-text">(${CerealsNuts})</span>
                                        </li>
                                        <li><input type="radio" name="food" value="Tuber" />Đồ ăn trẻ em <span class="lighter-text">(${Tuber})</span>
                                        </li>
                                        <li><input type="radio" name="food" value="DairyProducts" />Sữa<span class="lighter-text">(${DairyProducts})</span>
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
                                                <!--<form action="category" method="get">--> 
                                                <div class="slider">
                                                    <div class="track"></div>
                                                    <input type="range" name="minRange" min="1000" max="1000000" value="1000" id="min-range">
                                                    <input type="range" name="maxRange" min="1000" max="1000000" value="1000000" id="max-range">
                                                </div>
                                                <!--</form>-->
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
                                <c:forEach var="p" items="${sale}">
                                    <a href="productDetail?id=${p.productId}" style="text-decoration: none; color: unset;">
                                        <div class="card mb-3">
                                            <div class="row g-0">
                                                <div class="col-md-4">
                                                    <img src="${p.image}" class="img-fluid rounded-start" alt="...">
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="card-body sale-card">
                                                        <h6 class="card-title">${p.name}</h6>
    <!--                                                    <p class="card-text fw-bold" style="margin: 0;">${p.unitPrice*(100-p.discount)/100}đ &emsp;&emsp;
                                                          
                                                            <span class="fw-lighter"
                                                                  style="text-decoration: line-through; color: gray;font-size: 14px;">${p.unitPrice}₫
                                                            </span>
                                                          
                                                        </p>-->
                                                        <p class="card-text fw-bold" style="margin: 0;">
                                                            <!-- Discounted Price -->
                                                            <fmt:formatNumber value="${(p.unitPrice * (100 - p.discount)) / 100}" type="currency" currencySymbol="₫" groupingUsed="true" />
                                                            &emsp;&emsp;

                                                            <!-- Original Price (Strikethrough) -->
                                                            <span class="fw-lighter" style="text-decoration: line-through; color: gray; font-size: 14px;">
                                                                <fmt:formatNumber value="${p.unitPrice}" type="currency" currencySymbol="₫" groupingUsed="true" />
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
                                </c:forEach>
                            </div>

                        </div>
                    </form>
                </div>
                <div class="col-md-9">
                    <form action="category" method="get">
                        <div class="content-right-top d-flex">

                            <input type="hidden" name="food" value="${param.food}" />

                            <h6>Sắp xếp: </h6>
                            <select name="sortProduct">
                                <option value="tang">Giá tăng dần</option>
                                <option value="giam">Giá giảm dần</option>

                            </select>
                            <button type="submit" class="btn btn-success p-1 px-3">Lọc</button>
                        </div>
                    </form>
                    <!--                    <div style="margin-left: auto;">
                                            <span><strong>${productCount}</strong> Kết quả tìm thấy với từ khóa <strong>'${textSearch}'</strong></span>
                                        </div>-->
                    <div style="margin-left: auto;">
                        <span>
                            <strong>${productCount}</strong> Kết quả tìm thấy 
                            <c:choose>
                                <c:when test="${not empty textSearch}">
                                    với từ khóa <strong>'${textSearch}'</strong>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </div>
                    <div class="row g-2 mt-2">
                        <c:forEach var="p" items="${products}"> 
                            <div class="col-lg-4 col-md-4 col-sm-6 col-4">
                                <div class="card outstand">
                                    <a href="productDetail?id=${p.productId}">
                                        <img src="${p.image}" class="card-img-top" alt="...">
                                    </a>
                                    <div class="btn ${p.status == 'In Stock'? 'btn-success' : 'btn-dark'} out-stock-btn">
                                        ${p.status == 'In Stock' ? 'Còn Hàng' : 'Hết hàng'}
                                    </div>
                                    <div class="icons">
                                        <i class="fas fa-heart"></i>
                                        <i class="fas fa-eye"></i>
                                    </div>
                                    <div class="card-body">

                                        <h5 class="card-title fw-bold">${p.name}</h5>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <h6 class="fw-bold" style="margin-bottom: 0;">
                                                    <!-- Kiểm tra nếu discount > 0 thì hiển thị giá đã giảm -->
                                                    <c:choose>
                                                        <c:when test="${p.discount > 0}">
                                                            <!-- Giá đã giảm -->
                                                            <fmt:formatNumber value="${(p.unitPrice * (100 - p.discount)) / 100}" type="currency" currencySymbol="₫" groupingUsed="true" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <!-- Nếu discount = 0, chỉ hiển thị giá gốc -->
                                                            <fmt:formatNumber value="${p.unitPrice}" type="currency" currencySymbol="₫" groupingUsed="true" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </h6>

                                                <!-- Hiển thị giá gốc nếu discount > 0 -->
                                                <c:if test="${p.discount > 0}">
                                                    <span style="text-decoration: line-through; color: gray; font-size: 14px;">
                                                        <fmt:formatNumber value="${p.unitPrice}" type="currency" currencySymbol="₫" groupingUsed="true" />
                                                    </span>
                                                </c:if>



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
                        <c:if test="${empty products}">
                            <img style="width: 80%; margin: 0 auto;" src="../images/snapedit_1729332572486.png"/>
                        </c:if>

                        <!-- </form> -->
                    </div>
                    <c:set var="categoryProduct" value="${param.food}" />


                    <!-- đánh số trang chú ý: tạm thời fix cứng-- THAY BANG DOAN NAY-->
                    <c:choose>
                        <c:when test="${textSearch == null || textSearch == ''}">
                            <div class="pagination">
                                <ul class="pagination pagination-sm">
                                    <c:forEach begin="1" end="${pageCount}" var="i">
                                        <li class="page-item ${i == currentPage ? 'active' : ''}">
                                            <a class="page-link" href="category?food=${categoryProduct}&sortProduct=${param.sortProduct}&page=${i}">${i}</a>
                                        </li>
                                    </ul>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:when test="${textSearch != null}">

                            <div class="pagination">
                                <ul class="pagination pagination-sm">
                                    <!--<h1>aaaaaaa</h1>-->
                                    <c:forEach begin="1" end="${pageCount2}" var="i">
                                        <li class="page-item ${i == currentPage2 ? 'active' : ''}">
                                            <a class="page-link" href="category?textSearch=${textSearch}&page=${i}">${i}</a>
                                        </li>
                                    </c:forEach>
                                </ul>

                            </div>
                        </c:when>
                    </c:choose> 


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

    <script>
        function submitRange() {
            const minRange = document.getElementById('min-range').value;
            const maxRange = document.getElementById('max-range').value;

            const xhr = new XMLHttpRequest();
            xhr.open('POST', contextPath + '/customer/category', true);  // Thay đổi phương thức và URL theo yêu cầu
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                    document.getElementById('responseMessage').innerText = xhr.responseText;
                }
            };

            xhr.send(`minRange=${minRange}&maxRange=${maxRange}`);  // Gửi dữ liệu
        }
        const contextPath = "${pageContext.request.contextPath}";
    </script>
</body>
</html>
