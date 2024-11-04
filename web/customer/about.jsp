<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About us</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <script src="https://kit.fontawesome.com/54f0cb7e4a.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
    <style>
        @media screen and (max-width: 768px) {
            .desktop-view {
                display: none;
            }

            .mobile-view {
                display: block;
            }
        }

        @media screen and (min-width: 768px) {
            .mobile-view {
                display: none;
            }
        }
    </style>
</head>

<body>
    <%@include file="./HeaderLogin1.jsp" %>
    <div class="container" style="padding-top: 130px;">
        <div class="row" data-aos="fade-down">
            <div class="col-md-6" style="margin: auto 0;">
                <h1 class="fw-bold">Cửa hàng thực phẩm tươi sống 100%</h1>
                <p class="text-muted">Trang web của cửa hàng thực phẩm tươi sống là một nền tảng trực tuyến hiện
                    đại,
                    giúp khách hàng dễ dàng truy cập và mua sắm các loại thực phẩm tươi ngon như rau củ, thịt, cá,
                    hoa
                    quả, và các sản phẩm hữu cơ khác. Mục tiêu chính của trang web là cung cấp trải nghiệm mua sắm
                    thuận
                    tiện, nhanh chóng và an toàn cho người tiêu dùng, đồng thời đảm bảo chất lượng và sự tươi mới
                    của
                    sản phẩm.</p>
            </div>
            <div class="col-md-6">
                <img src="../images/about1.png" class="w-100" alt="">
            </div>
        </div>
    </div>
    <div class="container-fluid mt-3">
        <div class="row">
            <div class="col-md-6 desktop-view" data-aos="flip-up">
                <img src="../images/about2.png" style="width: 100%;" alt="">
            </div>
            <div class="col-md-6" style="margin: auto 0;" data-aos="flip-left">
                <p class="fw-bold h1">100% Cửa hàng thực <br> phẩm đáng tin cậy</p>
                <p>Trang web của cửa hàng thực phẩm tươi sống không chỉ là một nền tảng mua sắm trực tuyến mà còn là nơi
                    khách hàng có thể hoàn toàn tin tưởng về chất lượng sản phẩm và dịch vụ.</p>
                <div class="row">
                    <div class="col-md-6" data-aos="zoom-in">
                        <div class="d-flex">
                            <p class="fas fa-leaf text-success p-3 rounded-circle"
                               style="background-color: rgb(174, 205, 174);"></p>
                            <div class="mx-3">
                                <strong class="h5">100% Thực phẩm tươi</strong> <br>
                                <small style="font-size: 13px;">100% sản phẩm tươi và sạch</small>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6" data-aos="zoom-in">
                        <div class="d-flex">
                            <p class="fas fa-headset text-success p-3 rounded-circle"
                               style="background-color: rgb(174, 205, 174);"></p>
                            <div class="mx-3">
                                <strong class="h5">Hỗ trợ 24/7</strong> <br>
                                <small style="font-size: 13px;">Hỗ trợ liên lạc</small>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6" data-aos="zoom-in">
                        <div class="d-flex">
                            <p class="far fa-star text-success p-3 rounded-circle"
                               style="background-color: rgb(174, 205, 174);"></p>
                            <div class="mx-3">
                                <strong class="h5">Nhận xét từ khách hàng</strong> <br>
                                <small style="font-size: 13px;">Nhận xét tốt từ khách hàng</small>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6" data-aos="zoom-in">
                        <div class="d-flex">
                            <p class="fas fa-cash-register text-success p-3 rounded-circle"
                               style="background-color: rgb(174, 205, 174);"></p>
                            <div class="mx-3">
                                <strong class="h5">Thanh toán an toàn</strong> <br>
                                <small style="font-size: 13px;">Đảm bảo giao dịch an toàn</small>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6" data-aos="zoom-in">
                        <div class="d-flex">
                            <p class="fas fa-shipping-fast text-success p-3 rounded-circle"
                               style="background-color: rgb(174, 205, 174);"></p>
                            <div class="mx-3">
                                <strong class="h5">Miễn phí ship</strong> <br>
                                <small style="font-size: 13px;">Miễn phí ship với vourcher</small>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6" data-aos="zoom-in">
                        <div class="d-flex">
                            <p class="fas fa-boxes text-success p-3 rounded-circle"
                               style="background-color: rgb(174, 205, 174);"></p>
                            <div class="mx-3">
                                <strong class="h5">Đóng gói kĩ càng</strong> <br>
                                <small style="font-size: 13px;">Đảm bảo chất lượng sản phẩm đến tận tay</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6 mobile-view" data-aos="fade-down-left">
                <img src="../images/about2.png" style="width: 100%;" alt="">
            </div>
        </div>
    </div>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6 m-auto" data-aos="fade-right">
                <h1>Giao hàng nhanh</h1>
                <p class="text-muted"> Quy trình xử lý đơn hàng tự động và hệ thống vận chuyển hiện đại, chúng tôi cam
                    kết đưa sản phẩm đến tay bạn chỉ trong vài giờ hoặc trong ngày.Dịch vụ giao hàng nhanh giúp bạn tiết
                    kiệm thời gian, đảm bảo sự hài lòng và tiện lợi, đặc biệt phù hợp với các mặt hàng cần bảo quản tươi
                    sống hoặc khi bạn cần nhận hàng gấp.</p>
                <div class="mb-2 d-flex">
                    <div
                        style="background-color: rgb(235, 240, 235); width: 30px; height: 30px; display: flex; justify-content: center; align-items: center;border-radius: 50%;">
                        <div style="color: green;">&#10003;</div>
                    </div>
                    <p style="margin: auto 2%;">Giao hàng nhanh</p>
                </div>
                <div class="d-flex mb-2">
                    <div
                        style="background-color: rgb(235, 240, 235); width: 30px; height: 30px; display: flex; justify-content: center; align-items: center;border-radius: 50%;">
                        <div style="color: green;">&#10003;</div>
                    </div>
                    <p style="margin: auto 2%;">Bảo đảm an toàn</p>
                </div>
                <div class="d-flex mb-2">
                    <div
                        style="background-color: rgb(235, 240, 235); width: 30px; height: 30px; display: flex; justify-content: center; align-items: center;border-radius: 50%;">
                        <div style="color: green;">&#10003;</div>
                    </div>
                    <p style="margin: auto 2%;">Giao dịch nhanh chóng</p>
                </div>
                <a href="Homepage">
                    <button class="position-relative btn text-light p-2 px-5 mt-3"
                            style="background-color: rgb(76, 199, 76); border-radius: 50px;">Mua ngay
                        <i class="fas fa-arrow-right position-absolute" style="top: 13px; right: 10%;"></i>
                    </button>
                </a>


            </div>
            <div class="col-md-6 mt-2 desktop-view">
                <img src="../images/about3.png" class="w-100" alt="">
            </div>
        </div>
    </div>
    <%@include file="Footer.jsp" %>
    <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
    <script>
        AOS.init();
    </script>
</body>

</html>