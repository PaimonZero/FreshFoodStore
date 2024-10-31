<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header d-flex justify-content-between align-items-center p-3">
    <button id="sidebar-toggle" class="sidebar-toggle-btn">
        <span><ion-icon name="menu-outline"></ion-icon></span>
    </button>
    <div class="search-bar position-relative">
        <input type="text" class="form-control" placeholder="Search..." style="padding-left: 30px;">
        <span class="search-icon position-absolute">
            <ion-icon name="search-outline"></ion-icon>
        </span>
    </div>
    <div class="icons d-flex align-items-center">
        <div class="not-item ">
            <span class="icon">
                <ion-icon name="notifications-outline" class="icon mx-2"></ion-icon>
            </span>
        </div>
        <c:choose>
            <c:when test="${not empty account}">
                <form id="infoForm" action="/FreshFoodStore/customer/Dashboard?action=listInfo" method="POST">
                    <div class="user-icon" onclick="document.getElementById('infoForm').submit();" style="cursor: pointer;">
                        <img src="${account.avatar}" width="40" height="40" style="object-fit: cover; border-radius: 50%;" alt="alt"/>
                    </div>
                </form>
                <!---->               
<!--                <li class="dropdown" id="icon-drop" style="list-style: none;">
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
                </li>-->
            </c:when>
            <c:otherwise>
                <form action="auth?action=login" method="POST">
                    <a>
                        <button id="loginBtn" class="btn btn-sm m-sm-2 m-1 bg-primary text-white rounded-5 px-sm-3 px-2">
                            Login
                            <img class="d-none d-lg-inline-block" src="../images/png/box-arrow-in-right.svg" alt="box-arrow">
                        </button>
                    </a>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</div>

