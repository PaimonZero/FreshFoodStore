document.addEventListener('DOMContentLoaded', function() {
     // Lấy tất cả các mục menu
     const menuItems = document.querySelectorAll('.menu-item');

     // Lưu trạng thái menu được click
     function setActiveMenuItem(clickedItem) {
         // Loại bỏ class active khỏi tất cả các menu-item
         menuItems.forEach(item => {
             item.classList.remove('active');
         });
 
         // Thêm class active vào mục vừa click
         clickedItem.classList.add('active');
 
         // Lưu ID của mục active vào localStorage để khôi phục trạng thái sau khi chuyển trang
         localStorage.setItem('activeMenuItem', clickedItem.id);
     }
 
     // Thêm sự kiện click cho mỗi mục menu
     menuItems.forEach(item => {
         item.addEventListener('click', function () {
             setActiveMenuItem(item);
             // Điều hướng tới trang tương ứng dựa trên ID của mục
             const targetPage = item.id + '.html';
             window.location.href = './' + targetPage;
         });
     });
 
     // Khôi phục trạng thái active khi tải lại trang
     const savedActiveMenuItem = localStorage.getItem('activeMenuItem');
     if (savedActiveMenuItem) {
         const activeItem = document.getElementById(savedActiveMenuItem);
         if (activeItem) {
             activeItem.classList.add('active');
         }
     }

    document.getElementById('Dashboard').addEventListener('click', function() {
        window.location.href = './Dashboard.jsp';
    });
    
    document.getElementById('Inventory').addEventListener('click', function() {
        window.location.href = './Inventory.jsp';
    });

    document.getElementById('Product').addEventListener('click', function() {
        window.location.href = './Product.jsp';
    });

    document.getElementById('Report').addEventListener('click', function() {
        window.location.href = './Report.jsp';
    });
    
    document.getElementById('Supplier').addEventListener('click', function() {
        window.location.href = './Supplier.jsp';
    });

    document.getElementById('Orders').addEventListener('click', function() {
        window.location.href = './Orders.jsp';
    });
});