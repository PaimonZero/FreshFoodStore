//////nút tăng giảm quantity
////document.querySelectorAll('.qty-btn-plus').forEach(function (button) {
////    button.addEventListener('click', function () {
////        const qtyInput = button.previousElementSibling;
////        let currentValue = parseInt(qtyInput.value);
////        qtyInput.value = currentValue + 1;
////        updateTotal(); // Update total after changing quantity
////    });
////});
////
////document.querySelectorAll('.qty-btn-minus').forEach(function (button) {
////    button.addEventListener('click', function () {
////        const qtyInput = button.nextElementSibling;
////        let currentValue = parseInt(qtyInput.value);
////        if (currentValue > 0) {
////            qtyInput.value = currentValue - 1;
////            updateTotal(); // Update total after changing quantity
////        }
////    });
////});
//function displayOk(event) {
//        const okButton = document.getElementById('okButton');
//        const quantityInput = event.target;
//
//        // Check if the input value is valid (greater than 0)
//        if (quantityInput.value > 0) {
//            okButton.style.display = 'block'; // Show the OK button
//        } else {
//            okButton.style.display = 'none'; // Hide the OK button if quantity is 0 or less
//        }
//    }
////tính toán tổng tiền
//// Hàm để cập nhật tổng tiền của từng sản phẩm  
//function updateTotal() {
//    // Lặp qua tất cả các sản phẩm trong bảng  
//    document.querySelectorAll('.product').forEach(function (productRow) {
//        // Lấy giá của sản phẩm từ phần tử chứa giá  
//        const priceElement = productRow.querySelector('.item-price');
//        const price = parseFloat(priceElement.innerText.replace('$', ''));
//
//        // Lấy số lượng sản phẩm từ trường nhập  
//        const qtyInput = productRow.querySelector('.input-qty');
//        const quantity = parseInt(qtyInput.value);
//
//        // Tính tổng tiền cho sản phẩm đó  
//        const totalElement = productRow.querySelector('.item-total-price');
//        const total = price * quantity;
//
//        // Cập nhật tổng tiền trong phần tử chứa tổng tiền  
//        totalElement.innerText = `${total.toFixed(2)}`;
//    });
//
//    // Sau khi tính tổng cho từng sản phẩm, bạn có thể gọi thêm hàm để cập nhật tổng cộng nếu cần  
//    updateGrandTotal();
//}
//
//// Hàm để cập nhật tổng tiền phải thanh toán cho tất cả các sản phẩm  
//function updateGrandTotal() {
//    let grandTotal = 0;
//
//    // Lặp qua tất cả các sản phẩm và tính tổng  
//    document.querySelectorAll('.product').forEach(function (productRow) {
//        const totalElement = productRow.querySelector('.item-total-price');
//        const total = parseFloat(totalElement.innerText.replace('$', '')) || 0;
//        grandTotal += total;
//    });
//
//    // Cập nhật tổng cộng trong phần "Tổng cộng"  
//    const grandTotalElement = document.querySelector('.total-amount');
//    grandTotalElement.innerText = `${grandTotal.toFixed(2)}`;
//
//    // Cập nhật tổng tiền cuối cùng (sau khi cộng các khoản giảm giá, phí vận chuyển, thuế, v.v.)  
//    updateFinalTotal(grandTotal);
//}
//
//// Hàm để tính tổng tiền phải thanh toán cuối cùng  
//function updateFinalTotal(grandTotal) {
//    const totalAmount = parseFloat(document.querySelector('.total-amount').innerText.replace('$', ''));
//    const discountAmount = parseFloat(document.querySelector('.discount-amount').innerText.replace('-$', ''));
//    const shippingAmount = parseFloat(document.querySelector('.shipping-amount').innerText.replace('$', ''));
//
//    // Tính thuế 10% dựa trên tổng cộng trước thuế
//    const taxRate = 0.10;
//    const taxAmount = totalAmount * taxRate;
//
//    // Cập nhật giá trị thuế trên trang
//    document.querySelector('.tax-amount').innerText = `${taxAmount.toFixed(2)}`;
//    var finalTotalElement = document.querySelector('.final-total');
//    // Tính tổng tiền phải thanh toán
//    const finalTotal = totalAmount - discountAmount + shippingAmount + taxAmount;
//    finalTotalElement.innerText = `${finalTotal.toFixed(2)}`;
//}
//window.onload = updateTotal;

//// Xử lý khi nhấn nút + để tăng số lượng
//document.querySelectorAll('.qty-btn-plus').forEach(function (button) {
//    button.addEventListener('click', function () {
//        const qtyInput = button.previousElementSibling;
//        let currentValue = parseInt(qtyInput.value);
//        qtyInput.value = currentValue + 1;
//        showConfirmButton(button); // Hiển thị nút Confirm khi thay đổi quantity
//        updateTotal(); // Cập nhật total
//    });
//});
//
//// Xử lý khi nhấn nút - để giảm số lượng
//document.querySelectorAll('.qty-btn-minus').forEach(function (button) {
//    button.addEventListener('click', function () {
//        const qtyInput = button.nextElementSibling;
//        let currentValue = parseInt(qtyInput.value);
//        if (currentValue > 0) {
//            qtyInput.value = currentValue - 1;
//            showConfirmButton(button); // Hiển thị nút Confirm khi thay đổi quantity
//            updateTotal(); // Cập nhật total
//        }
//    });
//});

//// Hiển thị nút Confirm khi có thay đổi quantity
//function showConfirmButton(button) {
//    const confirmButton = button.closest('tr').querySelector('.btn-confirm');
//    confirmButton.style.display = 'inline-block'; // Hiển thị nút Confirm
//
//    // Thêm sự kiện khi nhấn nút Confirm
//    confirmButton.addEventListener('click', function () {
//        confirmButton.style.display = 'none'; // Ẩn nút Confirm sau khi người dùng nhấn
//    });
//}

//function formatCurrency(amount) {
//    return amount.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
//}
//
//function cleanCurrency(amount) {
//    // Remove the currency symbol and any grouping separators to get a raw number
//    return parseFloat(amount.replace(/[₫,]/g, ""));
//}
//
//function calculateTotal() {
//    let totalAmount = 0;
//    let shippingAmount = 30000; // Example shipping fee, you can change this value if needed
//
//    document.querySelectorAll('.product').forEach(function (product) {
//        // Get the formatted price and clean it to a number
//        let unitPrice = cleanCurrency(product.querySelector('.item-price').innerText);
//        let quantity = parseInt(product.querySelector('.input-qty').value);
//        let discount = 0; // Assuming no discount or get discount from product if it's there
//        let discountPercentage = parseFloat(discount) || 0;
//
//        // Calculate item total
//        let itemTotal = quantity * unitPrice * (1 - discountPercentage / 100);
//        totalAmount += itemTotal;
//
//        // Update item total price
//        product.querySelector('.item-total-price').innerText = formatCurrency(itemTotal);
//    });
//
//    // Update total amount
//    document.querySelector('.total-amount').innerText = formatCurrency(totalAmount);
//
//    // Update shipping amount
//    document.querySelector('.shipping-amount').innerText = formatCurrency(shippingAmount);
//
//    // Final total including shipping
//    let finalTotal = totalAmount + shippingAmount;
//    document.querySelector('.final-total').innerText = formatCurrency(finalTotal);
//}
//
//// Call calculateTotal function when the page loads
//window.onload = function () {
//    calculateTotal();
//}
//
//// Call calculateTotal function whenever the quantity input changes
//document.querySelectorAll('.input-qty').forEach(function (input) {
//    input.addEventListener('change', calculateTotal);
//});
function formatCurrency(amount) {
    return amount.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
}

function cleanCurrency(amount) {
    // Remove the currency symbol 'đ' and any thousands separators (.)
    return parseFloat(amount.replace(/[đ.,]/g, "").trim());
}

function cleanPercentage(percentage) {
    // Remove the percentage symbol '%' and convert to a number
    return parseFloat(percentage.replace(/[%]/g, "").trim());
}

function calculateTotal() {
    let totalAmount = 0;
    let shippingAmount = 30000; // Example shipping fee, you can change this value if needed

    document.querySelectorAll('.product').forEach(function (product) {
        // Clean the formatted price and discount
        let unitPrice = cleanCurrency(product.querySelector('.item-price').innerText);
        let quantity = parseInt(product.querySelector('.input-qty').value);
        let discount = cleanPercentage(product.querySelector('td .d-none').innerText); // Extract the discount

        // Calculate the item total with discount
        let itemTotal = quantity * unitPrice * (1 - discount / 100);
        totalAmount += itemTotal;

        // Update the item total price display
        product.querySelector('.item-total-price').innerText = formatCurrency(itemTotal);
    });

    // Update total amount
    document.querySelector('.total-amount').innerText = formatCurrency(totalAmount);

    // Update shipping amount
    document.querySelector('.shipping-amount').innerText = formatCurrency(shippingAmount);

    // Final total including shipping
    let finalTotal = totalAmount + shippingAmount;
    document.querySelector('.final-total').innerText = formatCurrency(finalTotal);
}

// Call calculateTotal function when the page loads
window.onload = function () {
    calculateTotal();
}

// Call calculateTotal function whenever the quantity input changes
document.querySelectorAll('.input-qty').forEach(function (input) {
    input.addEventListener('change', calculateTotal);
});


// test
  function changeQuantity(orderDetailId, change, button) {
    // Lấy input chứa số lượng hiện tại
    var input = button.parentNode.querySelector('.input-qty');
    var currentQuantity = parseInt(input.value);
    var maxQuantity = parseInt(input.max); // Số lượng tối đa
    var minQuantity = parseInt(input.min); // Số lượng tối thiểu

    // Tính toán số lượng mới
    var newQuantity = currentQuantity + change;

    // Kiểm tra giới hạn số lượng (min và max)
    if (newQuantity >= minQuantity && newQuantity <= maxQuantity) {
        // Cập nhật số lượng mới trong input
        input.value = newQuantity;

        // Gửi yêu cầu AJAX để cập nhật số lượng trong database
        var xhr = new XMLHttpRequest();
        xhr.open("POST", contextPath+"/customer/updateQuantity", true); // URL của servlet
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // Xử lý kết quả từ server (nếu cần)
                console.log(xhr.responseText);
            }
        };

        // Gửi orderDetailId và newQuantity lên server
        xhr.send("orderDetailId=" + orderDetailId + "&newQuantity=" + newQuantity);
    }
  }
