document.addEventListener('DOMContentLoaded', function () {
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
            const targetPage = item.id + '.jsp';
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

    document.getElementById('Dashboard').addEventListener('click', function () {
        window.location.href = './Dashboard';
    });

    document.getElementById('Inventory').addEventListener('click', function () {
        window.location.href = './receipts';
    });

    document.getElementById('Product').addEventListener('click', function () {
        window.location.href = './Product.jsp';
    });

    document.getElementById('Report').addEventListener('click', function () {
        window.location.href = './users';
    });

    document.getElementById('Supplier').addEventListener('click', function () {
        window.location.href = './suppliers';
    });

    document.getElementById('Orders').addEventListener('click', function () {
        window.location.href = './OrdersController';
    });

    document.getElementById('Delivery').addEventListener('click', function () {
        window.location.href = './DeliveryList';
    });
    document.getElementById('Settings').addEventListener('click', function () {
        const contextPath = '/' + window.location.pathname.split('/')[1];
        console.log(contextPath); // Kết quả: "/FreshFoodStore"
        window.location.href = window.location.origin + contextPath + '/customer/Homepage';
    });
    
    document.getElementById('Logout').addEventListener('click', function () {
        // Tạo một form động
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = '/FreshFoodStore/auth?action=logout';
        // Thêm form vào body và submit
        document.body.appendChild(form);
        form.submit();
    });


});