//// Upload file
//var btnUpload = $("#upload-file"),
//        btnUpload2 = $("#upload-file2"),
//        btnOuter = $(".button_outer"),
//        uploadedImage = $("#uploaded-image"),
//        uploadedImage2 = $("#uploaded-image2");
//
//btnUpload.on("change", function (e) {
//    var ext = btnUpload.val().split('.').pop().toLowerCase();
//    if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
//        $(".error_msg").text("Chỉ hỗ trợ nhận file gif, png, jpg, jpeg.");
//    } else {
//        $(".error_msg").text("");
//        btnOuter.addClass("file_uploading");
//
//        setTimeout(function () {
//            btnOuter.addClass("file_uploaded");
//        }, 3000);
//
//        // Create URL for the uploaded file
//        var uploadedFile = URL.createObjectURL(e.target.files[0]);
//
//        // Update the src attribute of the existing img tag and show it
//        setTimeout(function () {
//            uploadedImage.attr("src", uploadedFile).show(); // Set the src and display the image
//
//            // Reset the upload button state after the tick is shown for 2 seconds, but keep the image
//            setTimeout(function () {
//                btnOuter.removeClass("file_uploaded file_uploading"); // Reset to initial state
//                btnUpload.val(""); // Clear the file input value
//            }, 1000); // Adjust the time as needed
//        }, 3500);
//    }
//});
//btnUpload2.on("change", function (e) {
//    var ext = btnUpload2.val().split('.').pop().toLowerCase();
//    if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
//        $(".error_msg").text("Chỉ hỗ trợ nhận file gif, png, jpg, jpeg.");
//    } else {
//        $(".error_msg").text("");
//        btnOuter.addClass("file_uploading");
//
//        setTimeout(function () {
//            btnOuter.addClass("file_uploaded");
//        }, 3000);
//
//        // Create URL for the uploaded file
//        var uploadedFile = URL.createObjectURL(e.target.files[0]);
//
//        // Update the src attribute of the existing img tag and show it
//        setTimeout(function () {
//            uploadedImage2.attr("src", uploadedFile).show(); // Set the src and display the image
//
//            // Reset the upload button state after the tick is shown for 2 seconds, but keep the image
//            setTimeout(function () {
//                btnOuter.removeClass("file_uploaded file_uploading"); // Reset to initial state
//                btnUpload2.val(""); // Clear the file input value
//            }, 1000); // Adjust the time as needed
//        }, 3500);
//    }
//});
var btnUpload = $("#upload-avatar"),
    btnOuter = $(".button_outer"),
    uploadedImage = $("#uploaded-image");

// Handle file input change event
btnUpload.on("change", function (e) {
    // Check file extension
    var ext = btnUpload.val().split('.').pop().toLowerCase();
    if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
        $(".error_msg").text("Chỉ hỗ trợ nhận file gif, png, jpg, jpeg.");
    } else {
        $(".error_msg").text(""); // Clear error message if valid file type
        btnOuter.addClass("file_uploading");

        // Show processing bar and file uploading effect
        setTimeout(function () {
            btnOuter.addClass("file_uploaded");
        }, 3000);

        // Display the uploaded image
        setTimeout(function () {
            uploadedImage.attr("src", uploadedFile).show(); // Set the src attribute of the img tag

            // Reset upload button state after showing success box
            setTimeout(function () {
                btnOuter.removeClass("file_uploaded file_uploading"); // Reset to initial state
                btnUpload.val(""); // Clear the file input value
            }, 1000); // Adjust time as necessary
        }, 3500);
    }
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

//thông báo lỗi confirm password
document.getElementById('save-btn').addEventListener('click', function (event) {
    var newPassword = document.getElementById('new-pass').value;
    var confirmPassword = document.getElementById('confirm-pass').value;
    var errorElement = document.getElementById('password-error');
    var form = event.target.closest('form'); // Get the form element

    // Trigger HTML5 form validation (pattern, required, etc.)
    if (!form.checkValidity()) {
        // Prevent form submission if any input is invalid (like pattern or required)
        event.preventDefault();
        form.reportValidity(); // This will show validation errors in the form fields
        return;
    }
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

//hiện thanh sidebar mobile
document.querySelector('.menu-toggle').addEventListener('click', function () {
    document.getElementById('sidebar').classList.toggle('show');
});

//btn go to top
let mybutton = document.getElementById("myBtn");

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function () {
    scrollFunction();
};

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        mybutton.style.display = "block";
    } else {
        mybutton.style.display = "none";
    }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}