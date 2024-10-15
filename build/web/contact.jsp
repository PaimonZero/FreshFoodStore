<%-- 
    Document   : contact
    Created on : Sep 30, 2024, 11:39:50 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Account</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <script src="https://kit.fontawesome.com/54f0cb7e4a.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/customerCss/contact.css" />
    </head>
    <body>
        <%@include file="HeaderLogin.jsp" %>
        <div class="container" style="padding-top: 130px;">
            <div class="row ">
                <div class="col-md-3">
                    <div class="content-left p-4">
                        <div class="col-md-4 text-center location w-100">
                            <i class="fas fa-map-marker-alt mb-3"></i>
                            <h6>2715 Ash Dr. San Jose, South Dakota 83475</h6>
                        </div>
                        <hr>
                        <div class="col-md-4 text-center w-100 mail">
                            <i class="far fa-envelope mb-3"></i>
                            <h6>Proxy@gmail.com</h6>
                            <h6>Help.proxy@gmail.com</h6>
                        </div>
                        <hr>
                        <div class="col-md-4 text-center w-100 call">
                            <i class="fas fa-phone-volume mb-3"></i>
                            <h6>(219) 555-0114</h6>
                            <h6>(164) 333-0487</h6>
                        </div>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="content-right p-4">
                        <h3>Just Say Hello</h3>
                        <h6>Bạn có muốn chào tôi không hay bạn muốn bắt đầu dự án <br> của mình và cần sự giúp đỡ của tôi?
                            Hãy liên
                            hệ
                            với tôi.</h6>
                        <form action="">
                            <div class="row g-3 mt-3">
                                <div class="col-md-6">
                                    <input type="text" class="w-100 form-control" placeholder="Họ và tên">
                                </div>
                                <div class="col-md-6">
                                    <input type="text" class="w-100 form-control" placeholder="Email">
                                </div>
                            </div>
                            <input type="text" class="w-100 form-control mt-3 mb-3" placeholder="Số điện thoại"
                                   pattern="[0-9]{10}" title="Vui lòng nhập đúng số điện thoại">
                            <textarea name="" id="" placeholder="Tiêu đề"></textarea>
                            <button type="submit" class="btn btn-success send-btn mt-3">Gửi tin nhắn</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid mt-4 mb-3">
            <iframe
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3835.856168121187!2d108.25831637544461!3d15.968885884696228!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3142116949840599%3A0x365b35580f52e8d5!2zxJDhuqFpIGjhu41jIEZQVCDEkMOgIE7hurVuZw!5e0!3m2!1svi!2s!4v1727714220522!5m2!1svi!2s"
                style="width: 100%;" height="450" style="border:0;" allowfullscreen="" loading="lazy"
                referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
        <%@include file="Footer.jsp" %>
        <script src="./js/bootstrap.bundle.min.js"></script>
    </body>
</html>
