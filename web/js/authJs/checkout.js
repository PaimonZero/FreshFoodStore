function calculateTotal() {
    // Get all the rows in the table (cart items)
    const rows = document.querySelectorAll('.d-flex.align-items-center'); // Lấy các phần tử hàng chứa sản phẩm

    rows.forEach(row => {
        // Get the price, quantity, discount, and total price elements
        const priceElement = row.querySelector('.item-price'); // Giá sản phẩm
        const qtyElement = row.querySelector('.input-qty'); // Số lượng
        const discountElement = row.querySelector('.discount'); // Giảm giá
        const totalElement = row.querySelector('.item-total-price'); // Tổng giá sau khi tính

        // If all required elements exist
        if (priceElement && qtyElement && discountElement && totalElement) {
            // Get price (remove 'đ', commas, and periods, and convert to number)
            let price = priceElement.textContent.trim().replace('đ', '').replace(/\./g, '').replace(/,/g, '');
            let quantity = qtyElement.textContent.trim();
            let discount = discountElement.textContent.trim().replace('%', '');

            // Convert price, quantity, and discount to numbers
            price = parseFloat(price);
            quantity = parseInt(quantity);
            discount = parseFloat(discount) / 100; // Convert discount to decimal

            // Calculate total price with discount applied
            const totalPrice = price * quantity * (1 - discount);

            // Format total price with commas and 'đ' and update the total element
            totalElement.textContent = new Intl.NumberFormat('vi-VN').format(totalPrice) + 'đ';
        }
    });
}

// Function to calculate subtotal, shipping fee, and total
function calculateCartSummary() {
    // Calculate subtotal by summing all item total prices
    let subtotal = 0;
    document.querySelectorAll('.item-total-price').forEach(item => {
        // Remove 'đ' and dots, then convert to number
        let itemTotal = item.textContent.trim().replace('đ', '').replace(/\./g, '');
        itemTotal = parseFloat(itemTotal);
        subtotal += itemTotal;
    });

    // Update subtotal in the table (format with commas and 'đ')
    document.querySelector('.subtotal').textContent = new Intl.NumberFormat('vi-VN').format(subtotal) + 'đ';

    // Get shipping fee (remove 'đ' and dots)
    let shippingFee = document.querySelector('.shippingFee').textContent.trim().replace('đ', '').replace(/\./g, '');
    shippingFee = parseFloat(shippingFee);

    // Calculate total (subtotal plus shipping)
    const total = subtotal + shippingFee;

    // Update total in the table (format with commas and 'đ')
    document.querySelector('.total').textContent = new Intl.NumberFormat('vi-VN').format(total) + 'đ';
}

// Run the functions when the page loads
document.addEventListener('DOMContentLoaded', () => {
    calculateTotal();
    calculateCartSummary();
});



//done
