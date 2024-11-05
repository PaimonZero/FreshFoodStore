

var countdownDate = new Date("Nov 18, 2024 23:59:59").getTime();

// Cập nhật đồng hồ mỗi 1 giây  
var countdownFunction = setInterval(function () {

    // Lấy thời gian hiện tại  
    var now = new Date().getTime();

    // Tính toán khoảng thời gian còn lại  
    var distance = countdownDate - now;

    // Tính số ngày, giờ, phút và giây  
    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);

    // Đảm bảo hiển thị giờ, phút, giây luôn có hai chữ số  
    days = days < 10 ? "0" + days : days;
    hours = hours < 10 ? "0" + hours : hours;
    minutes = minutes < 10 ? "0" + minutes : minutes;
    seconds = seconds < 10 ? "0" + seconds : seconds;

    // Hiển thị kết quả  
    document.getElementById("days").textContent = days;
    document.getElementById("hours").textContent = hours;
    document.getElementById("minutes").textContent = minutes;
    document.getElementById("seconds").textContent = seconds;

    // Nếu hết thời gian  
    if (distance < 0) {
        clearInterval(countdownFunction);
        document.getElementById("countdown").innerHTML = "Hết giờ khuyến mãi!";
    }
}, 1000);




//video
const video = document.getElementById('myVideo');
const playPauseButton = document.getElementById('playPauseButton');

// Hàm để chuyển đổi giữa Play và Pause  
function togglePlayPause() {
    if (video.paused) {
        video.play();
        playPauseButton.classList.remove('play-icon');
        playPauseButton.classList.add('pause-icon');
    } else {
        video.pause();
        playPauseButton.classList.remove('pause-icon');
        playPauseButton.classList.add('play-icon');
    }
}

// Gắn sự kiện click cho nút  
playPauseButton.addEventListener('click', togglePlayPause);

// Khi video đang phát, nút sẽ chuyển thành Pause  
video.addEventListener('play', function () {
    playPauseButton.classList.remove('play-icon');
    playPauseButton.classList.add('pause-icon');
});

// Khi video tạm dừng, nút sẽ chuyển thành Play  
video.addEventListener('pause', function () {
    playPauseButton.classList.remove('pause-icon');
    playPauseButton.classList.add('play-icon');
});


// popup giỏ hàng
document.querySelectorAll('.qty-btn-plus').forEach(function (button) {
    button.addEventListener('click', function () {
        const qtyInput = button.previousElementSibling;
        let currentValue = parseInt(qtyInput.value);
        qtyInput.value = currentValue + 1;
        updateTotal(); // Update total after changing quantity
    });
});

document.querySelectorAll('.qty-btn-minus').forEach(function (button) {
    button.addEventListener('click', function () {
        const qtyInput = button.nextElementSibling;
        let currentValue = parseInt(qtyInput.value);
        if (currentValue > 0) {
            qtyInput.value = currentValue - 1;
            updateTotal(); // Update total after changing quantity
        }
    });
});

document.querySelectorAll('.openCartBtn').forEach(function (button) {
    button.addEventListener('click', function () {
        document.getElementById('cartSidebar').classList.add('show');
    });
});

document.getElementById('closeCartBtn').addEventListener('click', function () {
    document.getElementById('cartSidebar').classList.remove('show');
});

function updateTotal() {
    let total = 0;
    document.querySelectorAll('.cart-item').forEach(function (item) {
        const priceText = item.querySelector('.item-price').textContent;
        const price = parseFloat(priceText.replace('Giá: $', '').trim());
        const quantity = parseInt(item.querySelector('.qty-container input').value);
        const itemTotalPrice = price * quantity;
        item.querySelector('.item-total-price').textContent = 'Tổng: $' + itemTotalPrice.toFixed(2);
        total += itemTotalPrice;
    });
    document.querySelector('.total-price').textContent = 'Tổng cộng: $' + total.toFixed(2);
}

document.querySelectorAll('.qty-container input').forEach(function (input) {
    input.addEventListener('change', updateTotal);
});

updateTotal();

