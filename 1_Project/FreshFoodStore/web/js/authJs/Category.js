/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
//input 2 range
const minRange = document.getElementById('min-range');
const maxRange = document.getElementById('max-range');
const minValue = document.getElementById('min-value');
const maxValue = document.getElementById('max-value');
const track = document.querySelector('.track');

function updateSlider() {
    const min = parseInt(minRange.value);
    const max = parseInt(maxRange.value);

    if (min > max) {
        minRange.value = max;
        maxRange.value = min;
    }

    const percent1 = ((minRange.value - minRange.min) / (minRange.max - minRange.min)) * 100;
    const percent2 = ((maxRange.value - minRange.min) / (minRange.max - minRange.min)) * 100;

    track.style.left = percent1 + "%";
    track.style.width = (percent2 - percent1) + "%";

    minValue.textContent = Number(minRange.value).toLocaleString() + 'đ';
    maxValue.textContent = Number(maxRange.value).toLocaleString() + 'đ';
}

minRange.addEventListener('input', updateSlider);
maxRange.addEventListener('input', updateSlider);

updateSlider();
//test link đến product detail
function redirectToHref(event) {
    event.preventDefault();  // Ngăn chặn form submit thông thường
    var href = "https://getbootstrap.com/docs/5.0/components/buttons/"; // Lấy href từ div
    window.location.href = href;  // Chuyển hướng đến URL trong href
}

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

