document.getElementById('save-btn').addEventListener('click', function (event) {  
    var newPassword = document.getElementById('new-pass').value;  
    var confirmPassword = document.getElementById('confirm-pass').value;  
    var errorElement = document.getElementById('password-error');  
    var termsCheck = document.getElementById('termsCheck');  
    
    // Kiểm tra nếu mật khẩu và xác nhận mật khẩu không trùng khớp  
    if (newPassword !== confirmPassword) {  
        errorElement.style.display = 'block'; // Hiển thị thông báo lỗi  
        event.preventDefault(); // Ngăn không cho submit form nếu mật khẩu không khớp  
    } else {  
        errorElement.style.display = 'none'; // Ẩn thông báo lỗi  
    }  
    
    // Kiểm tra nếu checkbox "Accept all terms & Conditions" chưa được chọn  
    if (!termsCheck.checked) {  
        alert("Bạn phải chấp nhận các điều khoản trước khi tạo tài khoản!");  
        event.preventDefault(); // Ngăn form submit nếu chưa chọn checkbox  
    }  

    // Nếu mọi điều kiện đều thỏa mãn, form sẽ được submit bình thường  
});

//con mắt password
$(".toggle-password").click(function () {

    $(this).toggleClass("fa-eye fa-eye-slash");
    var input = $($(this).attr("toggle"));
    if (input.attr("type") == "password") {
        input.attr("type", "text");
    } else {
        input.attr("type", "password");
    }
});