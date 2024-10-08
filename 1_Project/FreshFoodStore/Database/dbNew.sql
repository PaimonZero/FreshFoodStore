USE FreshFoodStore;
GO

-- Bảng Users (chứa thông tin chung cho mọi loại người dùng)
CREATE TABLE Users (
    userId INT PRIMARY KEY IDENTITY(1,1),
    fullName NVARCHAR(100),
    address NVARCHAR(200),
    phone VARCHAR(15),
    email NVARCHAR(255) UNIQUE,
    password NVARCHAR(100) NOT NULL,
	passGoogle NVARCHAR(100) NULL,
	avatar NVARCHAR(255),
    createdAt DATETIME DEFAULT GETDATE(),
    -- updatedAt DATETIME
);

-- Bảng Customers (thông tin cụ thể của khách hàng)
CREATE TABLE Customers (
    customerId INT PRIMARY KEY IDENTITY(1,1),
    userId INT UNIQUE FOREIGN KEY REFERENCES Users(userId) ON DELETE CASCADE, -- Kế thừa từ Users
    --loyaltyPoints INT DEFAULT 0,
    --preferredPaymentMethod NVARCHAR(100),
    --createdAt DATETIME DEFAULT GETDATE(),
    --updatedAt DATETIME
);

-- Bảng Shippers (thông tin cụ thể của người giao hàng)
CREATE TABLE Shippers (
    shipperId INT PRIMARY KEY IDENTITY(1,1),
    userId INT UNIQUE FOREIGN KEY REFERENCES Users(userId) ON DELETE CASCADE, -- Kế thừa từ Users
    --vehicleType NVARCHAR(50),
    --licenseNumber NVARCHAR(50),
    --createdAt DATETIME DEFAULT GETDATE(),
    --updatedAt DATETIME
);

-- Bảng Staff (thông tin cụ thể của nhân viên)
CREATE TABLE Staffs (
    staffId INT PRIMARY KEY IDENTITY(1,1),
    userId INT UNIQUE FOREIGN KEY REFERENCES Users(userId) ON DELETE CASCADE, -- Kế thừa từ Users
    --department NVARCHAR(100), -- Bộ phận làm việc của nhân viên
    --salary DECIMAL(18,2), -- Lương của nhân viên
    --createdAt DATETIME DEFAULT GETDATE(),
    --updatedAt DATETIME
);

-- Bảng Managers (thông tin cụ thể của quản lý)
CREATE TABLE Managers (
    managerId INT PRIMARY KEY IDENTITY(1,1),
    userId INT UNIQUE FOREIGN KEY REFERENCES Users(userId) ON DELETE CASCADE, -- Kế thừa từ Users
    --department NVARCHAR(100), -- Bộ phận quản lý
    --bonus DECIMAL(18,2), -- Tiền thưởng của quản lý
    --createdAt DATETIME DEFAULT GETDATE(),
    --updatedAt DATETIME
);

-- Bảng Suppliers (Nhà cung cấp)
CREATE TABLE Suppliers (
    supplierId INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    address NVARCHAR(200),
    phone VARCHAR(15),
    email NVARCHAR(255),
    moreInfo NVARCHAR(255),
    createdAt DATETIME DEFAULT GETDATE(),
);

-- Bảng Category (Danh mục sản phẩm)
CREATE TABLE Category (
    categoryId INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
);

-- Bảng Products (Sản phẩm)
CREATE TABLE Products (
    productId INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    unitMeasure NVARCHAR(50) NOT NULL,
    supplierId INT FOREIGN KEY REFERENCES Suppliers(supplierId) ON DELETE SET NULL ON UPDATE CASCADE,
    categoryId INT FOREIGN KEY REFERENCES Category(categoryId) ON DELETE SET NULL ON UPDATE CASCADE,
    description NVARCHAR(255),
    image NVARCHAR(255),
    unitPrice DECIMAL(18,2) NOT NULL,
    status NVARCHAR(50),
    createdAt DATETIME DEFAULT GETDATE(),
    updatedAt DATETIME
);

-- Bảng Receipts (Phiếu nhập)
CREATE TABLE Receipts (
    receiptId INT PRIMARY KEY IDENTITY(1,1),
    dateInput DATETIME DEFAULT GETDATE(),
    supplierId INT FOREIGN KEY REFERENCES Suppliers(supplierId) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Bảng ReceiptDetails (Chi tiết phiếu nhập)
CREATE TABLE ReceiptDetails (
    receiptDetailId INT PRIMARY KEY IDENTITY(1,1),
    receiptId INT NULL FOREIGN KEY REFERENCES Receipts(receiptId) ON DELETE SET NULL , -- Cho phép NULL để sử dụng SET NULL, xóa ON UPDATE CASCADE
    productId INT NULL FOREIGN KEY REFERENCES Products(productId) ON DELETE SET NULL ON UPDATE CASCADE, -- Cho phép NULL để sử dụng SET NULL
    quantity INT NOT NULL,
    inputPrice DECIMAL(18,2) NOT NULL,
    expiryDate DATETIME,
);

-- Bảng BatchesProduct (Các lô hàng của sản phẩm)
CREATE TABLE BatchesProduct (
    batchId INT PRIMARY KEY IDENTITY(1,1),
    receiptDetailId INT FOREIGN KEY REFERENCES ReceiptDetails(receiptDetailId) ON DELETE SET NULL,
    productId INT FOREIGN KEY REFERENCES Products(productId) ON DELETE SET NULL ON UPDATE CASCADE,
    quantity INT NOT NULL,
    expiryDate DATETIME,
    createdAt DATETIME DEFAULT GETDATE()
);

-- Bảng Orders (Đơn hàng)
CREATE TABLE Orders (
    orderId INT PRIMARY KEY IDENTITY(1,1),
    userId INT FOREIGN KEY REFERENCES Users(userId) ON DELETE SET NULL , -- Người đặt hàng, xóa ON UPDATE CASCADE
    shippingFee DECIMAL(18,2),
    isConfirmed BIT DEFAULT 0, -- Trạng thái xác nhận đơn hàng (0: giỏ hàng, 1: đã xác nhận)
    paymentStatus NVARCHAR(50), -- Trạng thái thanh toán (vd: Pending, Paid, Failed)
    deliveryStatus NVARCHAR(50), -- Trạng thái giao hàng (vd: Pending, Shipped, Delivered)
    paymentType NVARCHAR(50), -- Loại thanh toán (vd: CreditCard, Cash, PayPal)
    deliveryLocation NVARCHAR(255), -- Địa chỉ giao hàng
    receiverName NVARCHAR(100), -- Tên người nhận hàng
    receiverPhone VARCHAR(15), -- Số điện thoại người nhận hàng
    shipperId INT FOREIGN KEY REFERENCES Shippers(shipperId), -- Shipper giao hàng, xóa ON UPDATE CASCADE ON DELETE SET NULL
    orderCreatedAt DATETIME DEFAULT GETDATE(), -- Thời điểm tạo đơn hàng
    orderCompletedAt DATETIME, -- Thời điểm hoàn thành đơn hàng (sẽ cập nhật sau khi giao)
);

-- Bảng OrderDetails (Chi tiết đơn hàng)
CREATE TABLE OrderDetails (
    orderDetailId INT PRIMARY KEY IDENTITY(1,1),
    orderId INT FOREIGN KEY REFERENCES Orders(orderId) ON DELETE CASCADE ON UPDATE CASCADE,
    batchId INT FOREIGN KEY REFERENCES BatchesProduct(batchId) ON DELETE SET NULL ON UPDATE CASCADE,
    unitPriceOut DECIMAL(18,2) NOT NULL,
    quantity INT NOT NULL,
    --isReturn BIT,
);

-- Bảng Promos (Khuyến mãi)
CREATE TABLE Promos (
    promotionId INT PRIMARY KEY IDENTITY(1,1),
    productId INT FOREIGN KEY REFERENCES Products(productId) ON DELETE SET NULL ON UPDATE CASCADE,
    quantitySale INT NOT NULL,
    discount DECIMAL(5,2),
    startDate DATETIME NOT NULL,
    endDate DATETIME NOT NULL,
);

-- Chỉ mục cho tìm kiếm nhanh
CREATE INDEX idx_email_users ON Users(email);
CREATE INDEX idx_supplierId_products ON Products(supplierId);
CREATE INDEX idx_categoryId_products ON Products(categoryId);
CREATE INDEX idx_email_suppliers ON Suppliers(email);
GO

-- 1. Users (Người dùng)
INSERT INTO Users (fullName, address, phone, email, password, createdAt)
VALUES 
('Nguyen Van A', '123 Le Loi, Q1, TP.HCM', '0912345678', 'a.nguyen@example.com', 'password123', GETDATE()),
('Le Thi B', '456 Nguyen Trai, Q5, TP.HCM', '0987654321', 'b.le@example.com', 'password456', GETDATE()),
('Tran Van C', '789 Hai Ba Trung, Q3, TP.HCM', '0909090909', 'c.tran@example.com', 'password789', GETDATE()),
('Hoang Van D', '101 Tran Hung Dao, Q1, TP.HCM', '0911111111', 'd.hoang@example.com', 'password101', GETDATE()),
('Phan Thi E', '202 Le Van Sy, Q3, TP.HCM', '0922222222', 'e.phan@example.com', 'password202', GETDATE()),
('Nguyen Thi F', '303 Nguyen Thi Minh Khai, Q1, TP.HCM', '0933333333', 'f.nguyen@example.com', 'password303', GETDATE()),
('Bui Van G', '404 Nguyen Van Cu, Q5, TP.HCM', '0944444444', 'g.bui@example.com', 'password404', GETDATE()),
('Trinh Thi H', '505 Le Duan, Q3, TP.HCM', '0955555555', 'h.trinh@example.com', 'password505', GETDATE()),
('Vu Van I', '606 Le Lai, Q1, TP.HCM', '0966666666', 'i.vu@example.com', 'password606', GETDATE()),
('Nguyen Van J', '707 Nguyen Thi Minh Khai, Q5, TP.HCM', '0977777777', 'j.nguyen@example.com', 'password707', GETDATE());

select * from Users

-- 2. Customers (Khách hàng)
INSERT INTO Customers (userId)
VALUES 
(1), 
(2), 
(3), 
(4);

-- 3. Shippers (Người giao hàng)
INSERT INTO Shippers (userId)
VALUES 
(5), (6), (7);

INSERT INTO Staffs(userId)
VALUES 
(8), (9);

INSERT INTO Managers(userId)
VALUES 
(10);

-- 4. Suppliers (Nhà cung cấp)
INSERT INTO Suppliers (name, address, phone, email, moreInfo, createdAt)
VALUES 
('Công ty Thực Phẩm Tươi Sống ABC', '123 Đinh Tiên Hoàng, Q.Bình Thạnh, TP.HCM', '0911222333', 'abc.supplier@example.com', 'Chuyên cung cấp rau củ quả', GETDATE()),
('Công ty Thực Phẩm Hải Sản XYZ', '456 Lý Thường Kiệt, Q.Tân Bình, TP.HCM', '0922233444', 'xyz.supplier@example.com', 'Chuyên cung cấp hải sản', GETDATE()),
('Công ty Thực Phẩm Sạch 123', '789 Nguyễn Thái Bình, Q.Tân Phú, TP.HCM', '0933344556', 'sach123.supplier@example.com', 'Cung cấp thực phẩm sạch', GETDATE()),
('Công ty Thực Phẩm Xanh', '101 Trần Quốc Thảo, Q.3, TP.HCM', '0944455667', 'xanh.supplier@example.com', 'Cung cấp rau củ organic', GETDATE()),
('Công ty Thực Phẩm Việt', '102 Nguyễn Văn Cừ, Q.5, TP.HCM', '0955566778', 'viet.supplier@example.com', 'Chuyên cung cấp thịt tươi', GETDATE()),
('Công ty Thực Phẩm Quốc Tế', '103 Lê Hồng Phong, Q.10, TP.HCM', '0966677889', 'quoc.te.supplier@example.com', 'Cung cấp hải sản nhập khẩu', GETDATE()),
('Công ty Thực Phẩm Tự Nhiên', '104 Trương Định, Q.1, TP.HCM', '0977788990', 'tu.nhien.supplier@example.com', 'Cung cấp thực phẩm tự nhiên', GETDATE()),
('Công ty Thực Phẩm Tiên Phong', '105 Huỳnh Văn Bánh, Q.Phú Nhuận, TP.HCM', '0988899001', 'tien.phong.supplier@example.com', 'Cung cấp thực phẩm tươi sống', GETDATE()),
('Công ty Thực Phẩm An Lành', '106 Nguyễn Huệ, Q.1, TP.HCM', '0999900112', 'an.lanh.supplier@example.com', 'Cung cấp thực phẩm an toàn', GETDATE()),
('Công ty Thực Phẩm Hữu Cơ', '107 Trần Bình Trọng, Q.5, TP.HCM', '1000111223', 'huu.co.supplier@example.com', 'Chuyên cung cấp thực phẩm hữu cơ', GETDATE());

-- 5. Category (Danh mục sản phẩm)
INSERT INTO Category (name)
VALUES 
('Rau Củ Quả'),
('Hải Sản'),
('Thịt Tươi Sống'),
('Sữa và Các sản phẩm từ sữa'),
('Bánh Kẹo'),
('Đồ Uống'),
('Gia Vị'),
('Đồ ăn nhẹ'),
('Trái Cây'),
('Thực phẩm chế biến sẵn');

-- 6. Products (Sản phẩm)
INSERT INTO Products (name, unitMeasure, supplierId, categoryId, description, image, unitPrice, status, createdAt)
VALUES 
('Cà Rốt', 'kg', 1, 1, 'Cà rốt tươi, an toàn', NULL, 15000, 'Available', GETDATE()),
('Cá Hồi', 'kg', 2, 2, 'Cá hồi tươi từ Na Uy', NULL, 250000, 'Available', GETDATE()),
('Thịt Bò', 'kg', 5, 3, 'Thịt bò tươi ngon', NULL, 300000, 'Available', GETDATE()),
('Sữa Tươi', 'lít', 3, 4, 'Sữa tươi nguyên chất', NULL, 45000, 'Available', GETDATE()),
('Bánh Mì', 'cái', 4, 5, 'Bánh mì mới ra lò', NULL, 2000, 'Available', GETDATE()),
('Nước Ngọt', 'lít', 5, 6, 'Nước ngọt các loại', NULL, 12000, 'Available', GETDATE()),
('Gia Vị Nêm Sẵn', 'g', 6, 7, 'Gia vị nêm sẵn tiện lợi', NULL, 5000, 'Available', GETDATE()),
('Snack Khoai Tây', 'g', 7, 8, 'Snack khoai tây giòn tan', NULL, 15000, 'Available', GETDATE()),
('Cam', 'kg', 8, 9, 'Cam ngọt mát', NULL, 30000, 'Available', GETDATE()),
('Thực Phẩm Chế Biến', 'kg', 9, 10, 'Thực phẩm chế biến sẵn', NULL, 50000, 'Available', GETDATE());

-- 7. Receipts (Phiếu nhập hàng)
INSERT INTO Receipts (supplierId, dateInput)
VALUES 
(1, GETDATE()), (2, GETDATE()), (3, GETDATE()), (4, GETDATE()), (5, GETDATE()), 
(6, GETDATE()), (7, GETDATE()), (8, GETDATE()), (9, GETDATE()), (10, GETDATE());

INSERT INTO ReceiptDetails (receiptId, productId, quantity, inputPrice, expiryDate)
VALUES 
(1, 1, 100, 13000, DATEADD(month, 1, GETDATE())),
(1, 2, 50, 240000, DATEADD(month, 1, GETDATE())),
(2, 3, 30, 290000, DATEADD(month, 1, GETDATE())),
(2, 4, 20, 42000, DATEADD(month, 1, GETDATE())),
(3, 5, 200, 1500, DATEADD(month, 1, GETDATE())),
(3, 6, 100, 10000, DATEADD(month, 1, GETDATE())),
(4, 7, 150, 4500, DATEADD(month, 1, GETDATE())),
(4, 8, 80, 12000, DATEADD(month, 1, GETDATE())),
(5, 9, 60, 35000, DATEADD(month, 1, GETDATE())),
(5, 10, 70, 48000, DATEADD(month, 1, GETDATE()));


-- 9. BatchesProduct (Lô hàng của sản phẩm)
INSERT INTO BatchesProduct (receiptDetailId, productId, quantity, expiryDate, createdAt)
VALUES 
(1, 1, 100, DATEADD(month, 1, GETDATE()), GETDATE()),
(2, 2, 50, DATEADD(month, 1, GETDATE()), GETDATE()),
(3, 3, 30, DATEADD(month, 1, GETDATE()), GETDATE()),
(4, 4, 20, DATEADD(month, 1, GETDATE()), GETDATE()),
(5, 5, 200, DATEADD(month, 1, GETDATE()), GETDATE()),
(6, 6, 100, DATEADD(month, 1, GETDATE()), GETDATE()),
(7, 7, 150, DATEADD(month, 1, GETDATE()), GETDATE()),
(8, 8, 80, DATEADD(month, 1, GETDATE()), GETDATE()),
(9, 9, 60, DATEADD(month, 1, GETDATE()), GETDATE()),
(10, 10, 70, DATEADD(month, 1, GETDATE()), GETDATE());

-- 10. Orders (Đơn hàng)
INSERT INTO Orders (userId, shippingFee, isConfirmed, paymentStatus, deliveryStatus, paymentType, deliveryLocation, receiverName, receiverPhone, shipperId, orderCreatedAt)
VALUES 
(1, 20000, 1, 'Paid', 'Pending', 'CreditCard', '123 Le Loi, Q1, TP.HCM', 'Nguyen Van A', '0912345678', 1, GETDATE()),
(2, 15000, 0, 'Pending', 'Pending', 'Cash', '456 Nguyen Trai, Q5, TP.HCM', 'Le Thi B', '0987654321', 2, GETDATE()),
(3, 10000, 1, 'Paid', 'Delivered', 'CreditCard', '789 Hai Ba Trung, Q3, TP.HCM', 'Tran Van C', '0909090909', 3, GETDATE()),
(4, 25000, 1, 'Paid', 'Pending', 'Cash', '101 Tran Hung Dao, Q1, TP.HCM', 'Hoang Van D', '0911111111', 2, GETDATE()),
(2, 5000, 0, 'Pending', 'Pending', 'CreditCard', '202 Le Van Sy, Q3, TP.HCM', 'Phan Thi E', '0922222222', 3, GETDATE()),
(3, 30000, 1, 'Paid', 'Delivered', 'Cash', '303 Nguyen Thi Minh Khai, Q1, TP.HCM', 'Nguyen Thi F', '0933333333', 1, GETDATE()),
(3, 20000, 1, 'Paid', 'Pending', 'CreditCard', '404 Nguyen Van Cu, Q5, TP.HCM', 'Bui Van G', '0944444444', 1, GETDATE()),
(4, 15000, 0, 'Pending', 'Pending', 'Cash', '505 Le Duan, Q3, TP.HCM', 'Trinh Thi H', '0955555555', 2, GETDATE()),
(1, 5000, 1, 'Paid', 'Delivered', 'CreditCard', '606 Le Lai, Q1, TP.HCM', 'Vu Van I', '0966666666', 2, GETDATE()),
(4, 25000, 0, 'Pending', 'Pending', 'Cash', '707 Nguyen Thi Minh Khai, Q5, TP.HCM', 'Nguyen Van J', '0977777777', 3, GETDATE());

select * from Orders

-- 11. OrderDetails (Chi tiết đơn hàng)
INSERT INTO OrderDetails (orderId, batchId, unitPriceOut, quantity)
VALUES 
(1, 1, 15000, 5), 
(2, 2, 250000, 2), 
(3, 3, 300000, 1), 
(4, 4, 45000, 3), 
(5, 5, 2000, 10), 
(6, 6, 12000, 5), 
(7, 7, 5000, 15), 
(8, 8, 15000, 8), 
(9, 9, 30000, 7), 
(10, 10, 50000, 4);

-- 12. Promos (Khuyến mãi)
INSERT INTO Promos (productId, quantitySale, discount, startDate, endDate)
VALUES 
(1, 20, 10.00, GETDATE(), DATEADD(day, 30, GETDATE())), 
(2, 10, 15.00, GETDATE(), DATEADD(day, 30, GETDATE())), 
(3, 5, 20.00, GETDATE(), DATEADD(day, 30, GETDATE())), 
(4, 30, 5.00, GETDATE(), DATEADD(day, 30, GETDATE())), 
(5, 50, 10.00, GETDATE(), DATEADD(day, 30, GETDATE())), 
(6, 20, 5.00, GETDATE(), DATEADD(day, 30, GETDATE())), 
(7, 15, 10.00, GETDATE(), DATEADD(day, 30, GETDATE())), 
(8, 25, 5.00, GETDATE(), DATEADD(day, 30, GETDATE())), 
(9, 10, 15.00, GETDATE(), DATEADD(day, 30, GETDATE())), 
(10, 8, 5.00, GETDATE(), DATEADD(day, 30, GETDATE()));

/*
-- Xóa dữ liệu các bảng có phụ thuộc vào bảng khác trước
DELETE FROM OrderDetails;
DELETE FROM Orders;
DELETE FROM BatchesProduct;
DELETE FROM ReceiptDetails;
DELETE FROM Receipts;
DELETE FROM Promos;

-- Xóa dữ liệu các bảng liên quan đến người dùng
DELETE FROM Customers;
DELETE FROM Shippers;
DELETE FROM Staffs;
DELETE FROM Managers;

-- Xóa dữ liệu các bảng chính
DELETE FROM Products;
DELETE FROM Category;
DELETE FROM Suppliers;

-- Cuối cùng xóa dữ liệu bảng Users
DELETE FROM Users;
*/
