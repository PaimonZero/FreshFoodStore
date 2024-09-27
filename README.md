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

---
---

# Giải thích ON DELETE SET NULL ON UPDATE CASCADE và  ON DELETE CASCADE ON UPDATE CASCADE

Trong SQL Server, các ràng buộc khóa ngoại (foreign key constraints) có thể định nghĩa cách hành động khi một bản ghi từ bảng cha (bảng được tham chiếu) bị **xóa** hoặc **cập nhật**. Các tùy chọn như `ON DELETE` và `ON UPDATE` chỉ định hành vi khi có sự thay đổi trong bảng cha đối với bản ghi được tham chiếu bởi khóa ngoại.

Dưới đây là giải thích chi tiết cho các lựa chọn bạn đã hỏi:

### 1. `ON DELETE SET NULL ON UPDATE CASCADE`

- **`ON DELETE SET NULL`**: Khi một bản ghi trong bảng cha (bảng được tham chiếu) bị **xóa**, giá trị khóa ngoại trong bảng con sẽ được **đặt thành NULL**. Điều này có nghĩa là thay vì xóa bản ghi liên quan trong bảng con, giá trị khóa ngoại được đặt thành `NULL` để biểu thị rằng không còn liên kết nào với bản ghi đã bị xóa.

  - **Ví dụ**: Trong bảng `Orders`, nếu `userId` là khóa ngoại liên kết với bảng `Users`, và một người dùng bị xóa khỏi bảng `Users`, thì cột `userId` trong bảng `Orders` sẽ được đặt thành `NULL` thay vì xóa bản ghi đơn hàng.

  ```sql
  userId INT FOREIGN KEY REFERENCES Users(userId) ON DELETE SET NULL
  ```

- **`ON UPDATE CASCADE`**: Khi một giá trị khóa chính (Primary Key) trong bảng cha bị **cập nhật**, tất cả các giá trị khóa ngoại tương ứng trong bảng con sẽ được **tự động cập nhật theo**. Điều này giúp giữ cho các bản ghi ở cả hai bảng luôn đồng bộ.

  - **Ví dụ**: Nếu `userId` của một người dùng trong bảng `Users` được thay đổi, giá trị `userId` trong bảng `Orders` sẽ được tự động cập nhật để giữ cho liên kết không bị mất.

  ```sql
  userId INT FOREIGN KEY REFERENCES Users(userId) ON UPDATE CASCADE
  ```

### 2. `ON DELETE CASCADE ON UPDATE CASCADE`

- **`ON DELETE CASCADE`**: Khi một bản ghi trong bảng cha bị **xóa**, tất cả các bản ghi liên quan trong bảng con cũng sẽ bị **xóa tự động**. Điều này hữu ích khi bạn muốn đảm bảo rằng nếu bản ghi chính bị xóa, thì không có bản ghi liên quan nào còn tồn tại trong bảng con, vì chúng không còn hợp lệ.

  - **Ví dụ**: Nếu một người dùng bị xóa khỏi bảng `Users`, tất cả các đơn hàng mà người dùng đó đã thực hiện trong bảng `Orders` cũng sẽ bị xóa.

  ```sql
  userId INT FOREIGN KEY REFERENCES Users(userId) ON DELETE CASCADE
  ```

- **`ON UPDATE CASCADE`**: Giống như phần trên, khi giá trị khóa chính trong bảng cha bị cập nhật, tất cả các giá trị khóa ngoại tương ứng trong bảng con sẽ được tự động cập nhật.

---

### Sự khác biệt chính:
- **`SET NULL`** (trong `ON DELETE`): Thay vì xóa bản ghi trong bảng con khi bản ghi liên quan trong bảng cha bị xóa, khóa ngoại được đặt thành `NULL`, giữ lại bản ghi trong bảng con nhưng không còn liên kết.
- **`CASCADE`** (trong `ON DELETE`): Xóa tất cả các bản ghi liên quan trong bảng con khi bản ghi trong bảng cha bị xóa.

---

### Ví dụ cụ thể:

#### `ON DELETE SET NULL ON UPDATE CASCADE`
- Khi **xóa** một người dùng (`Users`), cột `userId` trong các đơn hàng của người dùng đó (`Orders`) sẽ được đặt thành `NULL`.
- Khi **cập nhật** `userId` của người dùng trong bảng `Users`, tất cả các giá trị `userId` tương ứng trong bảng `Orders` cũng sẽ tự động cập nhật.

#### `ON DELETE CASCADE ON UPDATE CASCADE`
- Khi **xóa** một người dùng trong bảng `Users`, tất cả các đơn hàng của người dùng đó trong bảng `Orders` cũng bị xóa.
- Khi **cập nhật** `userId` của người dùng, tất cả các giá trị `userId` trong bảng `Orders` sẽ được cập nhật theo.

### Ứng dụng thực tế:
- **`ON DELETE CASCADE`** phù hợp khi dữ liệu liên quan không có ý nghĩa nếu bản ghi cha bị xóa, ví dụ như khi xóa người dùng và muốn xóa hết các đơn hàng của họ.
- **`ON DELETE SET NULL`** hữu ích khi bạn muốn giữ lại các bản ghi trong bảng con nhưng biểu thị rằng chúng không còn liên kết với bản ghi cha bị xóa.

Hy vọng giải thích này rõ ràng và giúp bạn hiểu rõ hơn về cách sử dụng hai tùy chọn này trong SQL Server.
