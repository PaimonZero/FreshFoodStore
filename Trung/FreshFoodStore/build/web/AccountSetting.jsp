<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Account Setting</title>
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <script src="https://kit.fontawesome.com/54f0cb7e4a.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="./css/customerCss/accountSetting.css">
        
    </head>
    <body>
        <%@include file="HeaderLogin.jsp" %>
        <div class="mobile-header">
            <div class="d-flex justify-content-between align-items-center">
                <button class="menu-toggle btn btn-outline-secondary">
                    <i class="fas fa-bars"></i> Menu
                </button>
            </div>
        </div>

        <div class="container mobile-content">
            <div class="row mt-4">
                <div class="col-lg-3 mt-2">
                    <div class="sidebar d-flex flex-column flex-shrink-0 bg-body-tertiary" id="sidebar">
                        <span class="fs-4 p-3" style="font-weight: 500;">Menu</span>
                        <ul class="nav nav-pills flex-column mb-auto">
                            <li class="nav-item">
                                <a href="#" class="nav-link active" aria-current="page">
                                    <i class="fas fa-th-large me-2"></i>
                                    Bảng điều khiển
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-link">
                                    <i class="fas fa-sync-alt me-2"></i>
                                    Lịch sử đơn hàng
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-link">
                                    <i class="far fa-heart me-2"></i>
                                    Danh sách yêu thích
                                </a>
                            </li>
                            <li>
                                <a href="ShoppingCart.jsp" class="nav-link"><!--để tạm-->
                                    <i class="bi bi-bag me-2"></i>
                                    Giỏ hàng
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-link">
                                    <i class="bi bi-gear me-2"></i>
                                    Cài đặt
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-link">
                                    <i class="fas fa-sign-out-alt me-2"></i>
                                    Đăng xuất
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="card mt-2">
                        <div class="card-header">
                            <h5 class="card-title">Cài đặt tài khoản</h5>
                        </div>
                        <div class="card-body">
                            <form action="">
                                <div class="row">
                                    <div class="col-md-6 desktop-view">
                                        <div class="mb-3">
                                            <label for="exampleFormControlInput1" class="form-label">Họ</label>
                                            <input type="text" class="form-control input" id="exampleFormControlInput1"
                                                   placeholder="Họ" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="exampleFormControlInput2" class="form-label">Tên</label>
                                            <input type="text" class="form-control input" id="exampleFormControlInput2"
                                                   placeholder="Tên" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="exampleFormControlInput3" class="form-label">Email</label>
                                            <input type="email" class="form-control input" id="exampleFormControlInput3"
                                                   placeholder="Email">
                                        </div>
                                        <div class="mb-3">
                                            <label for="exampleFormControlInput4" class="form-label">Số điện thoại</label>
                                            <input type="text" class="form-control input" id="exampleFormControlInput4"
                                                   placeholder="Phone Number" pattern="[0-9]{10}"
                                                   title="Vui lòng nhập đúng số điện thoại" required>
                                        </div>
                                        <div>
                                            <button type="submit" class="save-btn">Lưu thay đổi</button>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <img src="./images/png-clipart-user-profile-2018-in-sight-user-conference-expo-business-default-business-angle-service.png"
                                             alt="Uploaded Image" class="user-img" id="uploaded-image">
                                        <div class="panel">
                                            <div class="button_outer">
                                                <div class="btn_upload">
                                                    <input type="file" id="upload-file" name="userImg">
                                                    Upload Image
                                                </div>
                                                <div class="processing_bar"></div>
                                                <div class="success_box"></div>
                                            </div>
                                            <div class="error_msg"></div>
                                        </div>

                                    </div>
                                    <div class="col-md-6 mobile-view">
                                        <div class="mb-3">
                                            <label for="exampleFormControlInput1" class="form-label">Họ</label>
                                            <input type="text" class="form-control input" id="exampleFormControlInput1"
                                                   placeholder="Họ" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="exampleFormControlInput2" class="form-label">Tên</label>
                                            <input type="text" class="form-control input" id="exampleFormControlInput2"
                                                   placeholder="Tên" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="exampleFormControlInput3" class="form-label">Email</label>
                                            <input type="email" class="form-control input" id="exampleFormControlInput2"
                                                   placeholder="Email">
                                        </div>
                                        <div class="mb-3">
                                            <label for="exampleFormControlInput3" class="form-label">Số điện thoại</label>
                                            <input type="text" class="form-control input" id="exampleFormControlInput2"
                                                   placeholder="Phone Number" pattern="[0-9]{10}"
                                                   title="Vui lòng nhập đúng số điện thoại" required>
                                        </div>
                                        <div>
                                            <button type="submit" class="save-btn">Lưu thay đổi</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="card mt-3">
                        <div class="card-header">
                            <h5 class="card-title">Địa chỉ thanh toán</h5>
                        </div>
                        <div class="card-body">
                            <form action="">
                                <div class="row g-3">
                                    <div class="col-md-4">
                                        <label for="first-name" class="form-label">Họ người nhận</label>
                                        <input type="text" class="form-control input" id="first-name" placeholder="Họ"
                                               required>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="last-name" class="form-label">Tên người nhận</label>
                                        <input type="text" class="form-control input" id="last-name" placeholder="Tên"
                                               required>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="company-name" class="form-label">Tên công ty (tùy chọn)</label>
                                        <input type="text" class="form-control input" id="company-name"
                                               placeholder="Tên công ty">
                                    </div>
                                </div>
                                <div>
                                    <label for="address" class="form-label">Địa chỉ nhận hàng</label>
                                    <input type="text" class="form-control input" id="address"
                                           placeholder="Đại học FPT Đà Nẵng" required>
                                </div>
                                <div class="row mt-3">
                                    <div class="form-group col-md-4">
                                        <label for="city" class="form-label">Chọn thành phố</label>
                                        <select id="city" class="form-control input" required>
                                            <option value="">Chọn thành phố</option>
                                            <option value="da-nang">Đà Nẵng</option>
                                            <option value="ho-chi-minh">Hồ Chí Minh</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="district" class="form-label">Chọn tỉnh</label>
                                        <select id="district" class="form-control input" required>
                                            <option value="">Chọn tỉnh</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row g-3 mt-2">
                                    <div class="col-md-6">
                                        <label for="exampleFormControlInput3" class="form-label">Email</label>
                                        <input type="email" class="form-control input" id="exampleFormControlInput2"
                                               placeholder="Email">
                                    </div>
                                    <div class="col-md-6">
                                        <label for="exampleFormControlInput3" class="form-label">Số điện thoại</label>
                                        <input type="text" class="form-control input" id="exampleFormControlInput3"
                                               placeholder="Phone Number" pattern="[0-9]{10}"
                                               title="Vui lòng nhập đúng số điện thoại">
                                    </div>
                                </div>
                                <div>
                                    <button type="submit" class="save-btn mt-3">Lưu thay đổi</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="card mt-3 mb-3">
                        <div class="card-header">
                            <h5 class="card-title">Thay đổi mật khẩu</h5>
                        </div>
                        <form action="">
                            <div class="card-body">
                                <label for="pass" class="form-label">Mật khẩu hiện tại</label>
                                <div class="position-relative">
                                    <input type="password" class="form-control input" name="password" id="pass"
                                           value="999999">
                                    <span toggle="#pass" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                </div>
                                <div class="row g-3 mt-1">
                                    <div class="col-md-6">
                                        <label for="new-pass" class="form-label">Mật khẩu mới</label>
                                        <div class="position-relative">
                                            <input type="password" class="form-control input" name="new-password"
                                                   id="new-pass" value="999999">
                                            <span toggle="#new-pass"
                                                  class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="confirm-pass" class="form-label">Xác nhận mật khẩu mới</label>
                                        <div class="position-relative">
                                            <input type="password" class="form-control input" name="confirm-password"
                                                   id="confirm-pass" value="999999">
                                            <span toggle="#confirm-pass"
                                                  class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                        </div>
                                    </div>
                                </div>
                                <!-- Phần hiển thị lỗi -->
                                <div class="text-danger mt-2" id="password-error" style="display: none;">Mật khẩu xác nhận
                                    không
                                    trùng khớp!</div>
                                <div>
                                    <button type="submit" class="save-btn mt-3" id="save-btn">Lưu thay đổi</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <button onclick="topFunction()" id="myBtn" title="Go to top">
            <i class="fas fa-arrow-up"></i>
        </button>
        <%@ include file="Footer.jsp" %>
        <script src="./js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> 
        <!--có thể bị lỗi do dùng jquery 3.3.1-->
        <script src="./js/authJs/accountSetting.js"></script>
    </body>
</html>
