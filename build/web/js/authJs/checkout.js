// Lấy phần tử checkbox và div chứa trường nhập liệu  
const checkbox = document.getElementById('different-address');  
const newAddressDiv = document.getElementById('new-address');  

// Lắng nghe sự kiện khi checkbox thay đổi trạng thái  
checkbox.addEventListener('change', function () {  
    if (checkbox.checked) {  
        // Nếu checkbox được tích, hiển thị trường nhập liệu  
        newAddressDiv.style.display = 'block';  
    } else {  
        // Nếu checkbox bỏ tích, ẩn trường nhập liệu  
        newAddressDiv.style.display = 'none';  
    }  
});

//select chọn tỉnh
// Lấy phần tử select của thành phố và tỉnh  
const citySelect = document.getElementById('city');  
const districtSelect = document.getElementById('district');  

// Danh sách quận/huyện theo thành phố  
const districts = {  
    'da-nang': ['Hòa Minh', 'Ngũ Hành Sơn', 'Sơn Trà', 'Liên Chiểu'],  
    'ho-chi-minh': ['Gò Vấp', 'Quận 1', 'Quận 3', 'Tân Bình']  
};  

// Hàm cập nhật danh sách quận/huyện khi thay đổi thành phố  
function updateDistricts() {  
    const selectedCity = citySelect.value; // Lấy giá trị thành phố được chọn  
    const districtOptions = districts[selectedCity]; // Lấy danh sách quận/huyện dựa trên thành phố  

    // Xóa tất cả các tùy chọn cũ của select tỉnh (quận/huyện)  
    districtSelect.innerHTML = '<option value="">Select district</option>';  

    // Kiểm tra nếu có danh sách quận/huyện thì tạo các tùy chọn mới  
    if (districtOptions) {  
        districtOptions.forEach(function (district) {  
            const option = document.createElement('option');  
            option.value = district;  
            option.textContent = district;  
            districtSelect.appendChild(option);  
        });  
    }  
}  

// Lắng nghe sự kiện thay đổi thành phố và cập nhật danh sách tỉnh  
citySelect.addEventListener('change', updateDistricts);