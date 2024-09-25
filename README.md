# Phân tích database, lệnh truy vấn sql

<https://doidev.com/markdown-co-ban/>

### Cách xác định vai trò của người dùng
Để biết vai trò của một người dùng cụ thể, bạn có thể sử dụng các truy vấn SQL để kiểm tra sự hiện diện của `userId` trong các bảng con (`Customers`, `Shippers`, `Staff`, `Managers`).

#### Truy vấn để xác định vai trò của người dùng:

```sql
SELECT u.userId, u.fullName, 
       CASE 
           WHEN c.userId IS NOT NULL THEN 'Customer'
           WHEN s.userId IS NOT NULL THEN 'Shipper'
           WHEN st.userId IS NOT NULL THEN 'Staff'
           WHEN m.userId IS NOT NULL THEN 'Manager'
           ELSE 'Unknown'
       END AS role
FROM Users u
LEFT JOIN Customers c ON u.userId = c.userId
LEFT JOIN Shippers s ON u.userId = s.userId
LEFT JOIN Staff st ON u.userId = st.userId
LEFT JOIN Managers m ON u.userId = m.userId;
```

#### Giải thích:
- **LEFT JOIN**: Kết nối giữa bảng `Users` với các bảng `Customers`, `Shippers`, `Staff`, và `Managers`.
- **CASE WHEN**: Sử dụng biểu thức điều kiện để xác định người dùng thuộc vai trò nào dựa trên sự tồn tại của `userId` trong từng bảng con.

### Kết quả của truy vấn:
- Truy vấn trên sẽ trả về danh sách tất cả người dùng và vai trò của họ (dựa vào bảng con mà họ có dữ liệu).

#### Ví dụ về kết quả:

| userId | fullName      | role     |
|--------|---------------|----------|
| 1      | John Doe      | Customer |
| 2      | Jane Smith    | Shipper  |
| 3      | Bob Johnson   | Manager  |
| 4      | Alice Cooper  | Staff    |

### Khi thêm người dùng mới:
Khi thêm một người dùng vào hệ thống, bạn sẽ thêm bản ghi vào bảng `Users`, sau đó thêm bản ghi tương ứng vào bảng con tương ứng với vai trò của họ (`Customers`, `Shippers`, `Staff`, hoặc `Managers`).

### Cách thêm người dùng mới:
Ví dụ: Thêm một khách hàng mới:

```sql
-- Thêm vào bảng Users trước
INSERT INTO Users (fullName, address, phone, email, password, otp) 
VALUES ('John Doe', '123 Main St', '0123456789', 'john@example.com', 'password123', '123456');

-- Lấy userId vừa được tạo ra
DECLARE @userId INT = SCOPE_IDENTITY();

-- Thêm vào bảng Customers với userId vừa được tạo
INSERT INTO Customers (userId, loyaltyPoints, preferredPaymentMethod) 
VALUES (@userId, 100, 'Credit Card');
```

Tương tự, bạn có thể thêm người dùng vào các bảng `Shippers`, `Staff`, hoặc `Managers` tùy theo vai trò của họ.

### Kết luận:
Bằng cách sử dụng các bảng con liên kết với bảng `Users` và không dùng bảng `Roles`, bạn vẫn có thể dễ dàng quản lý vai trò của người dùng thông qua các truy vấn SQL. Cách tiếp cận này cung cấp sự linh hoạt khi không cần lưu trữ dữ liệu đặc thù theo vai trò, đồng thời vẫn có thể phân biệt vai trò người dùng một cách hiệu quả.
