<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Account Setting</title>
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <script src="https://kit.fontawesome.com/54f0cb7e4a.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="../css/customerCss/accountSetting.css">
    </head>
    <body>
        <%@include file="HeaderLogin1.jsp" %>
        <!--        <div class="mobile-header">
                    <div class="d-flex justify-content-between align-items-center">
                        <button class="menu-toggle btn btn-outline-secondary">
                            <i class="fas fa-bars"></i> Menu
                        </button>
                    </div>
                </div>-->

        <div class="container mobile-content" style="padding-top: 100px;">
            <div class="row mt-3">
                <div class="col-lg-3 mt-2">
                    <div class="sidebar d-flex flex-column flex-shrink-0 bg-body-tertiary" id="sidebar">
                        <span class="fs-4 p-3" style="font-weight: 500;">Menu</span>
                        <ul class="nav nav-pills flex-column mb-auto">
                            <li class="nav-item">
                                <form id="infoForm1" action="Dashboard?action=listInfo" method="POST">
                                    <a class="nav-link" aria-current="page" onclick="document.getElementById('infoForm1').submit();" style="cursor: pointer;">
                                        <i class="fas fa-th-large me-2"></i>
                                        Bảng điều khiển
                                    </a>
                                </form>
                            </li>
                            <li>
                                <form id="infoForm2" action="OrderHistory" method="POST"> <%--đổi đường dẫn--%>
                                    <a class="nav-link" onclick="document.getElementById('infoForm2').submit();" style="cursor: pointer;">
                                        <i class="fas fa-sync-alt me-2"></i>
                                        Lịch sử đơn hàng
                                    </a>
                                </form>
                            </li>
                            <li>
                                <form id="infoForm3" action="" method="POST"> <%--đổi đường dẫn--%>
                                    <a class="nav-link" onclick="document.getElementById('infoForm3').submit();" style="cursor: pointer;">
                                        <i class="far fa-heart me-2"></i>
                                        Danh sách yêu thích
                                    </a>
                                </form>
                            </li>
                            <li>
                                <form id="infoForm4" action="giohang" method="GET"> <%--đổi đường dẫn--%>
                                    <a class="nav-link" onclick="document.getElementById('infoForm4').submit();" style="cursor: pointer;">
                                        <i class="bi bi-bag me-2"></i>
                                        Giỏ hàng
                                    </a>
                                </form>
                            </li>
                            <li>
                                <form id="infoForm5" action="AccountSetting?action=showData" method="POST"> <%--đổi đường dẫn--%>
                                    <a class="nav-link active" onclick="document.getElementById('infoForm5').submit();" style="cursor: pointer;">
                                        <i class="bi bi-gear me-2"></i>
                                        Cài đặt
                                    </a>
                                </form>
                            </li>
                            <li>
                                <form id="infoForm6" action="authC?action=logout" method="POST"> <%--đổi đường dẫn--%>
                                    <a class="nav-link" onclick="document.getElementById('infoForm6').submit();" style="cursor: pointer;">
                                        <i class="fas fa-sign-out-alt me-2"></i>
                                        Đăng xuất
                                    </a>
                                </form>
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
                            <div class="row">
                                <form action="AccountSetting?action=editData" method="POST" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col-md-6 px-3 desktop-view"><%--lưu ý cái này cho máy tính--%>
                                            <div class="mb-3">
                                                <label for="exampleFormControlInput1" class="form-label">Họ và tên</label>
                                                <input type="text" class="form-control input" id="exampleFormControlInput1"
                                                       placeholder="Họ và tên" name="fullName" value="${listInfo.fullName}" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="exampleFormControlInput3" class="form-label">Email</label>
                                                <input type="email" class="form-control input" id="exampleFormControlInput3"
                                                       placeholder="Email" name="email" value="${listInfo.email}">
                                            </div>
                                            <div class="mb-3">
                                                <label for="exampleFormControlInput4" class="form-label">Số điện thoại</label>
                                                <input type="text" class="form-control input" id="exampleFormControlInput4"
                                                       placeholder="Số điện thoại" pattern="[0-9]{10}"
                                                       title="Vui lòng nhập đúng số điện thoại" name="phoneNumber" value="${listInfo.phone}" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="address" class="form-label">Địa chỉ nhận hàng</label>
                                                <input type="text" class="form-control input" id="address"
                                                       placeholder="Đại học FPT Đà Nẵng" name="address" value="${listInfo.address}" required>
                                            </div>
                                            <div>
                                                <button type="submit" class="save-btn" disabled>Lưu thay đổi</button>
                                            </div>
                                        </div>
                                        <div class="col-md-6 desktop-view ">
                                            <img src="../images/${listInfo.avatar}"
                                                 alt="Uploaded Image" class="user-img" id="uploaded-image">
                                            <div class="panel">
                                                <div class="button_outer">
                                                    <div class="btn_upload">
                                                        <%--id="upload-file"--%>
                                                        <input type="file" id="upload-avatar" class="input" name="file" accept="image/*">       
                                                        Upload Image
                                                    </div>
                                                    <div class="processing_bar"></div>
                                                    <div class="success_box"></div>
                                                </div>
                                                <div class="error_msg"></div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <form action="">
                                    <div class="col-md-6 mobile-view">
                                        <img src="../images/${listInfo.avatar}"
                                             alt="Uploaded Image" class="user-img" id="uploaded-image2">
                                        <div class="panel">
                                            <div class="button_outer">
                                                <div class="btn_upload">
                                                    <input type="file" id="upload-file2" name="userImg" accept="image/*">
                                                    Upload Image
                                                </div>
                                                <div class="processing_bar"></div>
                                                <div class="success_box"></div>
                                            </div>
                                            <div class="error_msg"></div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 mobile-view"> <%--cái này cho điện thoại--%>
                                        <div class="mb-3">
                                            <label for="exampleFormControlInput1" class="form-label">Họ và tên</label>
                                            <input type="text" class="form-control input" id="exampleFormControlInput1"
                                                   placeholder="Họ và tên" value="${listInfo.fullName}" name="fullName" required>
                                        </div>
                                        <!--                                        <div class="mb-3">
                                                                                    <label for="exampleFormControlInput2" class="form-label">Tên</label>
                                                                                    <input type="text" class="form-control input" id="exampleFormControlInput2"
                                                                                           placeholder="Tên" required>
                                                                                </div>-->
                                        <div class="mb-3">
                                            <label for="exampleFormControlInput3" class="form-label">Email</label>
                                            <input type="email" class="form-control input" id="exampleFormControlInput2"
                                                   placeholder="Email" value="${listInfo.email}" name="email">
                                        </div>
                                        <div class="mb-3">
                                            <label for="exampleFormControlInput3" class="form-label">Số điện thoại</label>
                                            <input type="text" class="form-control input" id="exampleFormControlInput2"
                                                   placeholder="Số điện thoại" pattern="[0-9]{10}"
                                                   title="Vui lòng nhập đúng số điện thoại" value="${listInfo.phone}" name="phoneNumber" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="address" class="form-label">Địa chỉ nhận hàng</label>
                                            <input type="text" class="form-control input" id="address"
                                                   placeholder="Đại học FPT Đà Nẵng" value="${listInfo.address}" name="address" required>
                                        </div>
                                        <div>
                                            <button type="submit" class="save-btn" disabled>Lưu thay đổi</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="card mt-3 mb-3">
                        <div class="card-header">
                            <h5 class="card-title">Thay đổi mật khẩu</h5>
                        </div>
                        <form action="AccountSetting?action=changePassword" method="POST">
                            <div class="card-body">
                                <label for="pass" class="form-label">Mật khẩu hiện tại</label>
                                <div class="position-relative">
                                    <input type="password" class="form-control input" name="password" id="pass"
                                           value="${listInfo.password}" disabled>
                                    <span toggle="#pass" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                </div>
                                <div class="row g-3 mt-1">
                                    <div class="col-md-6">
                                        <label for="new-pass" class="form-label">Mật khẩu mới</label>
                                        <div class="position-relative">
                                            <input type="password" class="form-control input" name="new-password"
                                                   id="new-pass" placeholder="Mật khẩu mới" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" 
                                                   title="Mật khẩu phải có ít nhất 8 kí tự và phải chứa cả chữ và số" required>
                                            <span toggle="#new-pass"
                                                  class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="confirm-pass" class="form-label">Xác nhận mật khẩu mới</label>
                                        <div class="position-relative">
                                            <input type="password" class="form-control input" name="confirm-password"
                                                   id="confirm-pass" placeholder="Xác nhận mật khẩu mới">
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
        <script src="../js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> 
        <!--có thể bị lỗi do dùng jquery 3.3.1-->
        <script src="../js/authJs/accountSetting.js"></script>
        <script>
            document.getElementById('upload-avatar').addEventListener('change', function (event) {
                const file = event.target.files[0]; // Lấy file từ input
                if (file) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        // Đặt src của ảnh thành nội dung của file được đọc
                        document.getElementById('uploaded-image').src = e.target.result;
                    };
                    reader.readAsDataURL(file); // Đọc file và trả kết quả dưới dạng URL base64
                }
            });

            document.addEventListener('DOMContentLoaded', function () {
                // Lấy tất cả các input fields và nút lưu
                const inputs = document.querySelectorAll('.input');
                const saveButton = document.querySelector('.save-btn');

                // Lưu giá trị ban đầu của tất cả các input fields
                const initialValues = Array.from(inputs).map(input => input.value);

                // Hàm kiểm tra xem có sự thay đổi trong các input fields không
                function checkForChanges() {
                    let isChanged = false;

                    inputs.forEach((input, index) => {
                        if (input.value !== initialValues[index]) {
                            isChanged = true;
                        }
                    });

                    // Kích hoạt hoặc vô hiệu hóa nút "Lưu thay đổi" dựa trên thay đổi
                    if (isChanged) {
                        saveButton.disabled = false;
                    } else {
                        saveButton.disabled = true;
                    }
                }

                // Đặt sự kiện 'input' cho tất cả các trường nhập liệu để kiểm tra khi có thay đổi
                inputs.forEach(input => {
                    input.addEventListener('input', checkForChanges);
                });

                // Vô hiệu hóa nút "Lưu thay đổi" ban đầu nếu không có thay đổi
                saveButton.disabled = true;
            });
        </script>
    </body>
</html>
