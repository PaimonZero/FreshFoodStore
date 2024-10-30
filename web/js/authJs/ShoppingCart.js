function changeQuantity(orderDetailId, change, button) {
    var input = button.parentNode.querySelector('.input-qty');
    var currentQuantity = parseInt(input.value);
    var maxQuantity = parseInt(input.max);
    var minQuantity = parseInt(input.min);

    var newQuantity = currentQuantity + change;

    if (newQuantity >= minQuantity && newQuantity <= maxQuantity) {
        input.value = newQuantity;

        // Gửi yêu cầu AJAX
        var xhr = new XMLHttpRequest();
        xhr.open("POST", contextPath + "/customer/updateQuantity", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                console.log(xhr.responseText);
            }
        };

        xhr.send("orderDetailId=" + orderDetailId + "&newQuantity=" + newQuantity);

        // Cập nhật giá tổng
        calculatePrices();
    }
}

function formatCurrencyVND(value) {
    return value.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
}

function calculatePrices() {
    let totalAmount = 0;

    document.querySelectorAll('.product').forEach(function(row) {
        // Lấy giá trị của đơn giá và số lượng
        const unitPriceText = row.querySelector('.item-price').innerText.replace(' đ', '').replace(/\./g, '').replace(/,/g, '');
        const discountText = row.querySelector('.discount').innerText.replace('%', '') || '0';
        const quantity = parseInt(row.querySelector('input[name="quantity"]').value);

        const unitPrice = parseFloat(unitPriceText);
        const discount = parseFloat(discountText);

        // Kiểm tra nếu giá trị đơn giá và số lượng hợp lệ
        if (!isNaN(unitPrice) && !isNaN(quantity)) {
            const itemTotalPrice = quantity * unitPrice * (1 - discount / 100);
            console.log(itemTotalPrice);
            row.querySelector('.item-total-price').innerText = formatCurrencyVND(itemTotalPrice);
            totalAmount += itemTotalPrice;
            
        }
    });
   

    // Cập nhật total-amount
    document.querySelector('.total-amount').innerText = formatCurrencyVND(totalAmount);

    // Lấy phí vận chuyển từ shipping-amount
    const shippingFeeText = document.querySelector('.shipping-amount').innerText.replace(' đ', '').replace(/\./g, '').replace(/,/g, '');
    const shippingFee = parseFloat(shippingFeeText) || 0;

    const finalTotal = totalAmount + shippingFee;

    // Cập nhật final-total
    document.querySelector('.final-total').innerText = formatCurrencyVND(finalTotal);
}

document.addEventListener('DOMContentLoaded', function () {
    calculatePrices();
});
//done