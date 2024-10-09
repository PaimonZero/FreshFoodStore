//nút tăng giảm quantity
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
//tính toán tổng tiền
// Hàm để cập nhật tổng tiền của từng sản phẩm  
function updateTotal() {
    // Lặp qua tất cả các sản phẩm trong bảng  
    document.querySelectorAll('.product').forEach(function (productRow) {
        // Lấy giá của sản phẩm từ phần tử chứa giá  
        const priceElement = productRow.querySelector('.item-price');
        const price = parseFloat(priceElement.innerText.replace('$', ''));

        // Lấy số lượng sản phẩm từ trường nhập  
        const qtyInput = productRow.querySelector('.input-qty');
        const quantity = parseInt(qtyInput.value);

        // Tính tổng tiền cho sản phẩm đó  
        const totalElement = productRow.querySelector('.item-total-price');
        const total = price * quantity;

        // Cập nhật tổng tiền trong phần tử chứa tổng tiền  
        totalElement.innerText = `$${total.toFixed(2)}`;
    });

    // Sau khi tính tổng cho từng sản phẩm, bạn có thể gọi thêm hàm để cập nhật tổng cộng nếu cần  
    updateGrandTotal();
}

// Hàm để cập nhật tổng tiền phải thanh toán cho tất cả các sản phẩm  
function updateGrandTotal() {
    let grandTotal = 0;

    // Lặp qua tất cả các sản phẩm và tính tổng  
    document.querySelectorAll('.product').forEach(function (productRow) {
        const totalElement = productRow.querySelector('.item-total-price');
        const total = parseFloat(totalElement.innerText.replace('$', '')) || 0;
        grandTotal += total;
    });

    // Cập nhật tổng cộng trong phần "Tổng cộng"  
    const grandTotalElement = document.querySelector('.total-amount');
    grandTotalElement.innerText = `$${grandTotal.toFixed(2)}`;

    // Cập nhật tổng tiền cuối cùng (sau khi cộng các khoản giảm giá, phí vận chuyển, thuế, v.v.)  
    updateFinalTotal(grandTotal);
}

// Hàm để tính tổng tiền phải thanh toán cuối cùng  
function updateFinalTotal(grandTotal) {
    const totalAmount = parseFloat(document.querySelector('.total-amount').innerText.replace('$', ''));
    const discountAmount = parseFloat(document.querySelector('.discount-amount').innerText.replace('-$', ''));
    const shippingAmount = parseFloat(document.querySelector('.shipping-amount').innerText.replace('$', ''));

    // Tính thuế 10% dựa trên tổng cộng trước thuế
    const taxRate = 0.10;
    const taxAmount = totalAmount * taxRate;

    // Cập nhật giá trị thuế trên trang
    document.querySelector('.tax-amount').innerText = `$${taxAmount.toFixed(2)}`;
    var finalTotalElement = document.querySelector('.final-total');
    // Tính tổng tiền phải thanh toán
    const finalTotal = totalAmount - discountAmount + shippingAmount + taxAmount;
    finalTotalElement.innerText = `$${finalTotal.toFixed(2)}`;
}
window.onload = updateTotal;