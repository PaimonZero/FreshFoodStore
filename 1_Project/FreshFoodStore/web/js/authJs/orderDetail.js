// Function to calculate item total price
//function calculateTotal() {
//    // Get all the rows in the table
//    const rows = document.querySelectorAll('tr');
//
//    rows.forEach(row => {
//        // Get the price, quantity, and total price elements
//        const priceElement = row.querySelector('.item-price');
//        const qtyElement = row.querySelector('.input-qty');
//        const totalElement = row.querySelector('.item-total-price');
//
//        // If all required elements exist
//        if (priceElement && qtyElement && totalElement) {
//            // Get price (remove 'đ', commas, and periods, and convert to number)
//            let price = priceElement.textContent.trim().replace('đ', '').replace(/\./g, '').replace(/,/g, '');
//            let quantity = qtyElement.textContent.trim();
//
//            // Convert price and quantity to numbers
//            price = parseFloat(price);
//            quantity = parseInt(quantity);
//
//            // Calculate total price
//            const totalPrice = price * quantity;
//
//            // Format total price with commas and 'đ' and update the total element
//            totalElement.textContent = new Intl.NumberFormat('vi-VN').format(totalPrice) + 'đ';
//        }
//    });
//}

// Run the function when the page loads
document.addEventListener('DOMContentLoaded', calculateTotal);


//tính tổng sau khi discount, shipping fee
// Function to calculate subtotal, shipping fee, and total
//function calculateCartSummary() {
//    // Calculate subtotal by summing all item total prices
//    let subtotal = 0;
//    document.querySelectorAll('.item-total-price').forEach(item => {
//        // Remove 'đ' and dots, then convert to number
//        let itemTotal = item.textContent.trim().replace('đ', '').replace(/\./g, '');
//        itemTotal = parseFloat(itemTotal);
//        subtotal += itemTotal;
//    });
//
//    // Update subtotal in the table (format with dots and 'đ')
//    document.querySelector('.subtotal').textContent = new Intl.NumberFormat('vi-VN').format(subtotal) + 'đ';
//
//    // Get shipping fee (remove 'đ' and dots)
//    let shippingFee = document.querySelector('.shippingFee').textContent.trim().replace('đ', '').replace(/\./g, '');
//    shippingFee = parseFloat(shippingFee);
//
//    // Calculate total (subtotal plus shipping)
//    const total = subtotal + shippingFee;
//
//    // Update total in the table (format with dots and 'đ')
//    document.querySelector('.total').textContent = new Intl.NumberFormat('vi-VN').format(total) + 'đ';
//}
//
//// Run the function when the page loads
//document.addEventListener('DOMContentLoaded', calculateCartSummary);

