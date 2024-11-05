<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" import="model.*,java.util.*, dal.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="shotcut icon" href="./images/logoFreshFoodStore.png"/>
        <link rel="stylesheet" href="./css/customerCss/Header-login.css"/>
    </head>
    <body>
        <header class="position-fixed w-100 bg-light hight--header d-flex flex-column">


            <!--mới sửa class navbar thêm vào bg-light-->
            <!-- Nav-bar -->
            <nav class="navbar align-items-center border-bottom bg-light flex-grow-1">
                <div class="container-fluid">
                    <div class="d-flex align-items-center ">
                        <a href="customer/Homepage" class="navbar-brand m-0 textColor fw-bold brand pacifico-regular">FreshFoodStore</a><!--để tạm là homePage.jsp-->
                        <div id="verticalbar" class="mx-xxl-2 mx-1 d-none d-lg-inline-block"></div>
                        <a href="customer/Homepage" class="nav-link textColor fw-lighter lh-sm brandSubHeading d-none d-lg-inline-block pacifico-regular">Thực phẩm
                            <br>
                            <span class="fw-medium brandSubHeading d-none d-lg-inline-block pacifico-regular">sạch, tươi mỗi ngày</span>
                        </a>
                        <div class="position-relative ms-4 d-none d-xl-inline-block">
                            <input class="searchInput bg-body-tertiary iconCursor ps-md-5 p-1 p-md-2 " type="search"
                                   placeholder="Tìm kiếm sản phẩm..." aria-label="Search">
                            <img class="position-absolute searchImg iconCursor" src="./images/png/search.svg"
                                 alt="search">
                        </div>
                    </div>
                    <div class="d-flex align-items-center flex-grow-1 flex-lg-grow-0">
                        <div class="d-none d-lg-block">
                            <ul class="d-flex m-0 list-unstyled ">
                                <li class="nav-item iconChange  me-4 pt-2">
                                    <a href="./customer/contact.jsp" class="nav-link text-center  p-0">
                                        <div class=" d-flex align-items-center overflow-hidden changeWidth mx-auto">
                                            <img class="one iconHeight mx-2" src="./images/svg/telephone.png" alt="book">
                                            <img class="two iconHeight mx-2" src="./images/svg/telephone-tran.png"
                                                 alt="book-half">
                                        </div>
                                        <div class="smallFont textColor">Gọi điện</div>
                                    </a>
                                </li>
                                <li class="nav-item iconChange me-4 pt-2">
                                    <a href="./customer/contact.jsp" class="nav-link text-center p-0">
                                        <div class=" d-flex align-items-center overflow-hidden changeWidth mx-auto">
                                            <img class="iconHeight mx-2" src="./images/svg/placeholder.png" alt="dpad">
                                            <img class=" iconHeight mx-2" src="./images/svg/placehoder-tran.png" alt="dpad">
                                        </div>
                                        <div class="smallFont textColor">Địa chỉ</div>
                                    </a>
                                </li>
                                <li class="nav-item iconChange  me-4 pt-1">
                                    <a href="#" class="nav-link text-center p-0">
                                        <div class=" d-flex align-items-center overflow-hidden changeWidth mx-auto">
                                            <img class="iconHeight--delivery mx-2" src="./images/svg/delivery-car.png"
                                                 alt="bar-chart">
                                            <img class=" iconHeight--delivery mx-2" src="./images/svg/delivery-car-tran.png"
                                                 alt="bar-chart">
                                        </div>
                                        <div class="smallFont textColor">Đơn hàng</div>
                                    </a>
                                </li>
                                <li class="nav-item iconChange  me-4 pt-2">
                                    <a href="auth?action=login" class="nav-link text-center p-0"> <!--để tạm ShoppingCart-->
                                        <div class=" d-flex align-items-center overflow-hidden changeWidth mx-auto">
                                            <img class="iconHeight mx-2" src="./images/svg/hand-bag.png"
                                                 alt="lightbulb">
                                            <img class=" iconHeight mx-2" src="./images/svg/hand-bag-tran.png"
                                                 alt="lightbulb">
                                        </div>
                                        <div class="smallFont textColor">Giỏ hàng</div>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div id="iconContainer" class="d-flex flex-grow-1 justify-content-end justify-content-lg-center align-items-center rounded-5 bg-light d-xl-none">
                            <div class="search-box">
                                <input type="text" class="search-input" placeholder="Sản phẩm,..." />
                                <button class="search-btn">
                                    <i class="fas fa-search"></i>
                                </button>
                            </div>
                        </div>
                        <c:choose>
                            <c:when test="${not empty account}">
                                <form id="infoForm" action="Dashboard?action=listInfo" method="POST">
                                    <div class="user-icon" onclick="document.getElementById('infoForm').submit();" style="cursor: pointer;">
                                        <img src="${account.avatar}" width="40" height="40" style="object-fit: cover; border-radius: 50%;" alt="alt"/>
                                    </div>
                                </form>
                                <li class="dropdown" id="icon-drop" style="list-style: none;">
                                    <a class="nav-link dropdown-toggle arrow-none nav-user px-2 pt-0 pb-0 m-0" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
                                        <span class="d-lg-flex flex-column gap-1 d-none"></span>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-end dropdown-menu-animated profile-dropdown">
                                        <form action="authC?action=logout" method="POST">
                                            <button style="background-color: transparent; border: none; color: inherit; cursor: pointer; padding: 0;">
                                                <img class="d-none d-lg-inline-block" src="../images/png/box-arrow-in-right.svg" alt="box-arrow">
                                                Logout
                                            </button>
                                        </form>
                                    </div>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <form action="auth?action=login" method="POST">
                                    <a>
                                        <button id="loginBtn" class="btn btn-sm m-sm-2 m-1 bg-primary text-white rounded-5 px-sm-3 px-2">
                                            Login
                                            <img class="d-none d-lg-inline-block" src="./images/png/box-arrow-in-right.svg" alt="box-arrow">
                                        </button>
                                    </a>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </nav>
            <nav id="secondNav" class="bottom-0 position-fixed end-0 start-0">
                <div class="d-lg-none">
                    <div class="container-fluid d-flex justify-content-between shadow pt-2">
                        <a href="#" class="text-center text-decoration-none textColor">
                            <img class="iconHeight mb-1" src="./images/svg/logo.png" alt="book">
                            <div class="extraSmallFont active pb-1 pacifico-regular">HexTech</div>
                        </a>
                        <a href="#" class="text-decoration-none textColor  text-center">
                            <img class="iconHeight mb-1" src="./images/svg/telephone.png" alt="book">
                            <div class="extraSmallFont pb-1">Gọi điện</div>
                        </a>
                        <a href="#" class="text-decoration-none textColor  text-center">
                            <img class="iconHeight mb-1" src="./images/svg/placeholder.png" alt="dpad">
                            <div class="extraSmallFont pb-1">Địa chỉ</div>
                        </a>
                        <a href="#" class="text-decoration-none textColor  text-center">
                            <img class="iconHeight mb-1" src="./images/svg/delivery-car.png" alt="bar-chart">
                            <div class="extraSmallFont pb-1">Đơn hàng</div>
                        </a>
                        <a href="#" class="text-decoration-none textColor  text-center">
                            <img class="iconHeight mb-1" src="./images/svg/hand-bag.png" alt="lightbulb">
                            <div class="extraSmallFont pb-1">Giỏ hàng</div>
                        </a>
                        <a href="#" class="text-decoration-none textColor  text-center">
                            <img class="iconHeight mb-1" src="./images/svg/menu.png" alt="briefcase">
                            <div class="extraSmallFont pb-1">Danh mục</div>
                        </a>
                    </div>
                </div>
            </nav>
        </header>
        <script src="./js/authJs/myCode.js"></script>
    </body>
</html>
