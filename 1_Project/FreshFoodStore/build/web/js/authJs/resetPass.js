//thông báo lỗi confirm password
document.getElementById('save-btn').addEventListener('click', function (event) {  
    var newPassword = document.getElementById('new-pass').value;  
    var confirmPassword = document.getElementById('confirm-pass').value;  
    var errorElement = document.getElementById('password-error');  

    // Kiểm tra nếu mật khẩu và xác nhận mật khẩu không trùng khớp  
    if (newPassword !== confirmPassword) {  
        errorElement.style.display = 'block'; // Hiển thị thông báo lỗi  
        event.preventDefault(); // Ngăn không cho submit form nếu mật khẩu không khớp  
    } else {  
        errorElement.style.display = 'none'; // Ẩn thông báo lỗi  
        // Nếu mật khẩu trùng khớp, cho phép form submit hoặc xử lý tiếp theo  
//        alert("Mật khẩu đã được lưu thành công!");  
        // Nếu bạn không muốn form submit ở đây, cũng có thể sử dụng event.preventDefault();  
    }  
});
//con mắt password
$(".password-toggle").click(function () {
    $(this).toggleClass("fa-eye fa-eye-slash");
    var input = $($(this).attr("toggle"));
    if (input.attr("type") == "password") {
        input.attr("type", "text");
    } else {
        input.attr("type", "password");
    }
});