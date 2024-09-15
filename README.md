# BanThucPhamOnline
Đồ án môn SWP391

### Phân tích chi tiết các bảng và mối quan hệ

**1. Bảng Products (Sản phẩm):**
   * **Mục đích:** Lưu trữ thông tin chi tiết về từng sản phẩm.
   * **Các trường:**
     * **productId:** Mã sản phẩm duy nhất.
     * **name:** Tên sản phẩm.
     * **unitId:** Liên kết đến đơn vị tính của sản phẩm.
     * **supplierId:** Liên kết đến nhà cung cấp của sản phẩm.
     * **description:** Mô tả chi tiết về sản phẩm.
     * **image:** Đường dẫn đến hình ảnh sản phẩm.
     * **unitPrice:** Giá bán lẻ của sản phẩm.
     * **status:** Tình trạng sản phẩm (còn hàng, hết hàng, ngừng kinh doanh)
   * **Mối quan hệ:**
     * **Nhiều đối một** với bảng Units (qua UnitId): Mỗi sản phẩm chỉ có một đơn vị tính, 1 đơn vị tính có thể sài được cho nhiều sản phẩm
     * **Nhiều đối một** với bảng Suppliers (qua SupplierId): Mỗi sản phẩm chỉ có một nhà cung cấp, 1 nhà cung cấp có thể cung cấp nhiều sản phẩm

**2. Bảng Units (Đơn vị tính):**
   * **Mục đích:** Lưu trữ danh sách các đơn vị tính sử dụng.
   * **Các trường:**
     * **unitId:** Mã đơn vị tính.
     * **name:** Tên đơn vị tính (ví dụ: kg, lít, cái).

**3. Bảng Suppliers (Nhà cung cấp):**
   * **Mục đích:** Lưu trữ thông tin về các nhà cung cấp.
   * **Các trường:**
     * **supplierId:** Mã nhà cung cấp.
     * **name:** Tên nhà cung cấp.
     * **address:** Địa chỉ.
     * **phone:** Số điện thoại.
     * **email:** Email.
     * **moreInfo:** Thông tin thêm.

**4. Bảng User (Người dùng hệ thống):**
   * **Mục đích:** Lưu trữ thông tin về người dùng.
   * **Các trường:**
     * **userId:** Mã khách hàng.
     * **fullName:** Tên khách hàng.
     * **address:** Địa chỉ.
     * **phone:** Số điện thoại.
     * **email:** Email.
     * **password:** Mật khẩu.
     * **roleId:** Quyền của User.
     * **otp:** Mã otp của người dùng.

**5. Bảng Inputs (Phiếu nhập):**
   * **Mục đích:** Lưu trữ thông tin về các phiếu nhập hàng.
   * **Các trường:**
     * **inputId:** Mã phiếu nhập.
     * **totalPrice:** Tổng giá trị phiếu nhập.
     * **dateInput:** Ngày nhập hàng.

**6. Bảng InputDetail (Chi tiết phiếu nhập):**
   * **Mục đích:** Lưu trữ chi tiết các sản phẩm trong một phiếu nhập.
   * **Các trường:**
     * **inputDetailId:** Mã chi tiết phiếu nhập.
     * **inputId:** Liên kết đến phiếu nhập.
     * **productId:** Liên kết đến sản phẩm.
     * **quantity:** Số lượng nhập.
     * **inputPrice:** Giá nhập.
     * **expiryDate:** Ngày hết hạn của lô hàng này.
   * **Mối quan hệ:**
     * **Nhiều đối một** với bảng Input (qua inputId): 1 đơn nhập có nhiều mặt hàng khác nhau
     * **Nhiều đối một** với bảng Products (qua productId): 1 đơn nhập có nhiều mặt hàng khác nhau
       
**7. Bảng Orders (Đơn hàng):**
   * **Mục đích:** Lưu trữ thông tin về các phiếu xuất hàng.
   * **Các trường:**
     * **orderId:** Mã đơn hàng.
     * **totalPrice:** Tổng giá trị đơn hàng.
     * **dateOutput:** Ngày xuất hàng.
     * **status:** Tình trạng vận chuyển
     * **customerId:** Người ngận đơn       (là khóa ngoại từ userId của bảng Users)
     * **shipperId:** Người chuyển đơn      (là khóa ngoại từ userId của bảng Users)

**8. Bảng OrderDetail (Chi tiết phiếu xuất):**
   * **Mục đích:** Lưu trữ chi tiết các sản phẩm trong một đơn hàng.
   * **Các trường:**
     * **outputDetailId:** Mã chi tiết phiếu xuất.
     * **orderId:** Liên kết đến phiếu xuất.
     * **productId:** Liên kết đến sản phẩm.
     * **quantity:** Số lượng xuất.
     * **isReturn:** Trường check số lượng mặt hàng bị trả trong 1 order
   * **Mối quan hệ:**
     * **Nhiều đối một** với bảng Orders (qua orderId): 1 đơn hàng có nhiều mặt hàng khác nhau
     * **Một đối một** với bảng Products (qua productId): 1 chi tiết đơn hàng chỉ có 1 sản phẩm

**9. Bảng Inventories (Tồn kho):** (Sử dụng view hoặc truy vấn)
   * **Mục đích:** Theo dõi số lượng tồn kho thực tế của từng sản phẩm tại từng thời điểm.
   * **Các trường:**
     * **inventoryId:** Mã kho.
     * **productId:** Liên kết đến sản phẩm.
     * **Quantity:** Số lượng hiện có.
     * **LastUpdated:** Thời điểm cập nhật cuối cùng.

**10. Bảng Promotions (Khuyến mãi):**
   * **Mục đích:** Quản lý các chương trình khuyến mãi.
   * **Các trường:**
     * **promotionId:** Mã khuyến mãi.
     * **batchId:** Kiểm tra
     * **inventoryId:** Liên kết đến sản phẩm.
     * **discount:** Mức giảm giá.
     * **startDate**, **EndDate:** Thời gian bắt đầu và kết thúc khuyến mãi.
     * **batchId**: Lưu trữ thông tin lô hàng của sản phẩm
   * **Mối quan hệ:**
     * **một đối một** với bảng Batches (qua batchId): mỗi lô hàng có thể set 1 mã khuyến mãi khác nhau

**11. Bảng BatchesProduct (Các lô hàng của sản phẩm):**
   * **Mục đích:** Quản lý số lượng, ngày hết hạn của từng lô hàng nhập vào của mỗi sản phẩm
   * **Các trường:**
     * **batchId:** Mã lô hàng
     * **inputDetailId:** Liên kết chi tiết đơn nhập.
     * **productId:** Liên kết đến sản phẩm.
     * **quantity:** Số lượng sản phẩm
     * **expiryDate:** Ngày hết hạn của lô hàng.
   * **Mối quan hệ:**
     * **Một đối một** với bảng InputDetail (qua inputDetailId): Mỗi chi tiết đơn nhập thì chỉ tạo ra 1 batchProduct để quản lý sản phẩm đó
     * **Một đối nhiều** với bảng Products (qua productId): 1 product có nhiều batchsProduct để quản lý số lượng từng lô, 1 batchsProduct thì chỉ hướng đến 1 product

**12. Bảng ReturnRequests (Các lô hàng của các khiếu nại trả hàng, hoàn tiền):**
   * **Mục đích:** Cải thiện và rút gọn thời gian xử lý trả hàng, Nâng cao trải nghiệm của khách hàng
   * **Các trường:**
     * **requestId:** Mã lô hàng.
     * **orderId:** Liên kết chi tiết đơn nhập.
     * **customerId:** Liên kết đến sản phẩm.
     * **returnReason:** Lý đo đổi trả.
     * **requestDate:** Ngày yêu cầu .
     * **status:** Trạng thái xử lý.
   * **Mối quan hệ:**
     * **Một đối một** với bảng Orders (qua orderId): Mỗi lần yêu cầu hoàn tiền chỉ liên kết với một đơn hàng cụ thể.
     * **Một đối nhiều** với bảng Users (qua customerId): 1 customer có thể gửi request return từ nhiều đơn khác nhau.

**13. Bảng Roles:
   * **Mục đích:** Thông tin về role của user, bao gồm:customer, shipper, staff, manager
   * **Các trường:**
     * **roleId:** Mã role.
     * **name:** Tên của role
  
///
/
/
/
/
/
/
/
/
/
/
/
/
///

### Logic hoạt động và mối quan hệ
* **Khi nhập hàng:** Tạo một bản ghi mới trong bảng Input và các chi tiết tương ứng trong bảng InputDetail. Cập nhật số lượng tồn kho trong bảng Inventories.
* **Khi xuất hàng:** Tạo một bản ghi mới trong bảng Output và các chi tiết tương ứng trong bảng OutputDetail. Cập nhật số lượng tồn kho trong bảng Inventories.
* **Quản lý tồn kho:** Bảng Inventories luôn được cập nhật khi có phiếu nhập hoặc xuất.
* **Quản lý khuyến mãi:** Bảng Promotions lưu trữ thông tin về các chương trình khuyến mãi. Khi tạo đơn hàng, hệ thống sẽ kiểm tra xem sản phẩm có đang được khuyến mãi hay không và áp dụng mức giảm giá tương ứng.
* **Báo cáo:** Các view được tạo dựa trên các bảng này để cung cấp các báo cáo về doanh thu, tồn kho, sản phẩm sắp hết hạn, v.v.

**Mối quan hệ giữa các bảng:**
* **Một sản phẩm** có thể có **nhiều phiếu nhập** và **nhiều phiếu xuất**.
* **Một phiếu nhập/xuất** có thể có **nhiều sản phẩm**.
* **Tồn kho** của một sản phẩm được cập nhật dựa trên các phiếu nhập/xuất.

**Với cấu trúc này, có thể dễ dàng xây dựng các chức năng:**
* Quản lý sản phẩm, nhà cung cấp, khách hàng.
* Nhập xuất kho, quản lý tồn kho.
* Tạo báo cáo doanh thu, tồn kho.
* Quản lý khuyến mãi.
* Cảnh báo hàng sắp hết hạn.

**Các phần cần nghiên cứu thêm** Ví dụ:
* Cách thực hiện các tính toán liên quan đến tồn kho và doanh thu.
* Cách thiết kế các view để tạo báo cáo.
* Cách triển khai chức năng cảnh báo.

***
***

**Câu hỏi**

    Khi tôi thêm unitPrice vào InputDetail thì làm sao để tôi truy ra giá của sản phầm đó được:
Ví dụ cùng 1 loại thịt từ 1 nhà cung cấp, trong kho của tôi đang có thịt từ 2 đợt nhập hàng khác nhau, giá thịt đợt sau thì cao hơn đợt nhập hàng trước. Vậy làm như thế nào để tôi có thể hiện thị giá thịt ấy trong list sản phẩm

**Trả lời**

Bạn hoàn toàn đúng khi đặt câu hỏi về việc truy xuất giá sản phẩm khi có nhiều đơn giá khác nhau cho cùng một sản phẩm. Đây là một vấn đề khá phổ biến trong quản lý kho hàng, đặc biệt khi giá cả thị trường biến động.

**Để giải quyết vấn đề này, chúng ta cần xem xét một số phương pháp:**

### 1. **Xác định giá hiển thị mặc định:**
* **Giá nhập mới nhất:** Hiển thị giá nhập của lô hàng gần nhất.
* **Giá bán lẻ hiện tại:** Nếu có bảng giá bán lẻ riêng, sử dụng giá bán lẻ hiện tại.
* **Giá trung bình:** Tính giá trung bình cộng của tất cả các lô hàng.

### 2. **Cung cấp tùy chọn chọn giá:**
* **Cho phép người dùng chọn:** Khi xem thông tin sản phẩm, người dùng có thể chọn xem giá nhập của lô hàng nào, hoặc giá bán lẻ hiện tại.
* **Sử dụng bộ lọc:** Cho phép người dùng lọc sản phẩm theo khoảng giá hoặc theo lô hàng.

### 3. **Sử dụng view hoặc stored procedure:**
* **Tạo view:** Tạo một view để tính toán giá hiển thị dựa trên các tiêu chí nhất định (ví dụ: giá nhập mới nhất, giá bán lẻ hiện tại).
* **Sử dụng stored procedure:** Tạo một stored procedure để tính toán giá động dựa trên các tham số đầu vào (ví dụ: mã sản phẩm, ngày).

### **Ví dụ về stored procedure:**

```sql
CREATE PROCEDURE GetProductPrice
    @ProductId INT
AS
BEGIN
    DECLARE @LatestUnitPrice DECIMAL(18,2)

    SELECT TOP 1
        @LatestUnitPrice = UnitPrice
    FROM
        InputDetails
    WHERE
        ObjectId = @ProductId
    ORDER BY
        InputId DESC

    SELECT @LatestUnitPrice AS UnitPrice
END
```

Stored procedure này sẽ trả về giá nhập mới nhất của sản phẩm có mã `@ProductId`.

### **Hiển thị giá trong list sản phẩm:**

Khi hiển thị danh sách sản phẩm, bạn có thể gọi stored procedure này để lấy giá và hiển thị.

**Ví dụ bằng SQL Server và C#:**

```csharp
using System.Data.SqlClient;

// ...

// Gọi stored procedure
using (SqlConnection connection = new SqlConnection("YourConnectionString"))
using (SqlCommand command = new SqlCommand("GetProductPrice", connection))
{
    command.CommandType = CommandType.StoredProcedure;
    command.Parameters.AddWithValue("@ProductId", productId);

    connection.Open();
    object result = command.ExecuteScalar();
    decimal price = Convert.ToDecimal(result);

    // Hiển thị giá trong giao diện
    labelPrice.Text = price.ToString();
}
```

**Những yếu tố khác cần xem xét:**

* **Hiệu suất:** Nếu có nhiều sản phẩm và cần hiển thị giá thực tế nhanh chóng, bạn có thể sử dụng các kỹ thuật tối ưu hóa như indexing, caching.
* **Tính linh hoạt:** Cung cấp cho người dùng nhiều tùy chọn để tùy chỉnh cách hiển thị giá.
* **Độ chính xác:** Đảm bảo rằng giá hiển thị luôn chính xác và cập nhật.

**Kết luận:**

Việc chọn phương pháp nào phụ thuộc vào yêu cầu cụ thể của hệ thống của bạn. Bạn nên cân nhắc các yếu tố như:

* **Độ phức tạp của tính toán giá:** Nếu công thức tính giá phức tạp, bạn nên sử dụng stored procedure.
* **Tần suất cập nhật giá:** Nếu giá thường xuyên thay đổi, bạn cần có cơ chế cập nhật giá tự động.
* **Yêu cầu của người dùng:** Hiểu rõ nhu cầu của người dùng để cung cấp các tùy chọn hiển thị giá phù hợp.

Bằng cách kết hợp các phương pháp trên, bạn có thể xây dựng một hệ thống quản lý kho hàng linh hoạt và chính xác, giúp bạn quản lý giá cả sản phẩm một cách hiệu quả.

***
***

## Sử dụng View để quản lý tồn kho

**Thay vì sử dụng Trigger, chúng ta có thể sử dụng View để tính toán số lượng tồn kho dựa trên các giao dịch nhập xuất.**

### Tại sao nên sử dụng View?
* **Tính linh hoạt:** View cung cấp một cách linh hoạt để truy vấn dữ liệu từ nhiều bảng mà không cần thay đổi cấu trúc cơ sở dữ liệu. 
* **Hiệu suất cao:** Nếu được thiết kế đúng cách, view có thể cung cấp hiệu suất truy vấn tốt.
* **Dễ bảo trì:** View dễ hiểu và bảo trì hơn trigger.

### Cách tạo View để tính toán tồn kho:

Giả sử chúng ta có các bảng sau:
* **Products:** Lưu trữ thông tin sản phẩm
* **InputDetail:** Chi tiết phiếu nhập
* **OutputDetail:** Chi tiết phiếu xuất

**Tạo View:**

```sql
CREATE VIEW vw_Inventory AS
SELECT
    p.ProductId,
    p.ProductName,
    SUM(id.Quantity) - SUM(od.Quantity) AS Quantity
FROM
    Products p
LEFT JOIN InputDetail id ON p.ProductId = id.ProductId
LEFT JOIN OutputDetail od ON p.ProductId = od.ProductId
GROUP BY
    p.ProductId, p.ProductName;
```

**Giải thích:**

* **View vw_Inventory:** Tạo một view mới để tính toán số lượng tồn kho.
* **LEFT JOIN:** Sử dụng LEFT JOIN để đảm bảo rằng tất cả các sản phẩm đều được liệt kê, kể cả những sản phẩm chưa có giao dịch nhập xuất.
* **SUM(id.Quantity) - SUM(od.Quantity):** Tính toán số lượng tồn kho bằng cách trừ tổng số lượng xuất đi khỏi tổng số lượng nhập vào.

### Sử dụng View:
Để xem số lượng tồn kho của một sản phẩm, bạn chỉ cần truy vấn view này:

```sql
SELECT * FROM vw_Inventory WHERE ProductId = 'your_product_id';
```

### Ưu điểm của cách làm này:
* **Dễ hiểu:** Câu lệnh SQL để tạo view rất dễ hiểu và bảo trì.
* **Hiệu suất:** Nếu bạn tạo index phù hợp cho các cột được sử dụng trong WHERE và GROUP BY, hiệu suất truy vấn sẽ được cải thiện đáng kể.
* **Linh hoạt:** Bạn có thể tùy chỉnh view để tính toán các thông tin khác như giá trị tồn kho, ngày nhập cuối cùng, v.v.

### Nhược điểm:
* **Không tự động cập nhật:** View chỉ là một "lát cắt" của dữ liệu tại thời điểm tạo hoặc cập nhật. Nếu dữ liệu gốc thay đổi, bạn cần làm mới view để có kết quả chính xác.

### Giải pháp cho nhược điểm:
* **Materialized View:** Một số hệ quản trị cơ sở dữ liệu hỗ trợ materialized view, đây là một loại view đặc biệt lưu trữ kết quả của truy vấn. Khi dữ liệu gốc thay đổi, materialized view có thể được cập nhật tự động hoặc theo lịch trình.
* **Scheduled Job:** Bạn có thể tạo một job chạy định kỳ để làm mới view, đảm bảo dữ liệu luôn được cập nhật.

**Kết luận:**

Sử dụng View là một cách tiếp cận linh hoạt và hiệu quả để quản lý tồn kho. Tuy nhiên, bạn cần cân nhắc kỹ lưỡng để chọn phương pháp phù hợp nhất với hệ thống của mình.

**Bạn có muốn tìm hiểu thêm về materialized view hoặc cách tạo job để cập nhật view không?**

**Ngoài ra, bạn có thể kết hợp cả View và Trigger:**
* **Sử dụng View:** Để hiển thị thông tin tồn kho một cách trực quan và dễ dàng truy vấn.
* **Sử dụng Trigger:** Để thực hiện các tác vụ khác như gửi thông báo khi số lượng tồn kho thấp, tạo báo cáo tự động, v.v..

***
***

BatchId, InputDetailId, ProductId, Quantity, ExpiryDate, UnitPriceIn 
Đây là các trường của bảng Batch, Mục đích của bản này là lưu trữ thông tin về số lượng và ngày hết hạn của hàng hóa của từng lô hàng đang còn trong kho. Vậy tôi sẽ tạo view cho Kho hàng như thế nào

## Tạo View để Hiển thị Thông tin Tồn Kho

**Hiểu rõ vấn đề:**

Bạn đã có một bảng `Batches` để lưu trữ thông tin chi tiết về từng lô hàng, bao gồm số lượng hiện tại, ngày hết hạn và các thông tin liên quan. Để có một cái nhìn tổng quan về tình trạng tồn kho, chúng ta sẽ tạo một view.

**Mục tiêu của view:**

* Hiển thị tổng số lượng tồn kho của từng sản phẩm.
* Hiển thị số lượng tồn kho của từng lô hàng.
* Có thể lọc dữ liệu theo các tiêu chí như sản phẩm, ngày hết hạn, v.v.

**Cấu trúc của view:**

```sql
CREATE VIEW vw_Inventory AS
SELECT 
    b.ProductId, 
    p.ProductName,
    b.BatchId,
    SUM(b.Quantity) AS QuantityInStock,
    MAX(b.ExpiryDate) AS LatestExpiryDate
FROM 
    Batches b
INNER JOIN Products p ON b.ProductId = p.ProductId
GROUP BY 
    b.ProductId, p.ProductName, b.BatchId;
```

**Giải thích:**

* **INNER JOIN:** Kết hợp bảng `Batches` và `Products` để lấy thêm thông tin tên sản phẩm.
* **SUM(b.Quantity):** Tính tổng số lượng của từng lô hàng.
* **MAX(b.ExpiryDate):** Lấy ngày hết hạn gần nhất của từng lô hàng.
* **GROUP BY:** Nhóm kết quả theo `ProductId`, `ProductName` và `BatchId` để hiển thị thông tin cho từng lô hàng.

**Sử dụng view:**

Để xem thông tin tồn kho của tất cả các sản phẩm:

```sql
SELECT * FROM vw_Inventory;
```

Để xem thông tin tồn kho của sản phẩm có mã là 'PRD001':

```sql
SELECT * FROM vw_Inventory WHERE ProductId = 'PRD001';
```

**Mở rộng chức năng:**

* **Thêm cột:** Bạn có thể thêm các cột tính toán khác như giá trị tồn kho (QuantityInStock * UnitPriceIn), ngày sản xuất, v.v.
* **Lọc dữ liệu:** Sử dụng mệnh đề WHERE để lọc dữ liệu theo các tiêu chí như ngày hết hạn, nhà cung cấp, v.v.
* **Tính toán tổng:** Sử dụng hàm SUM, AVG để tính tổng, trung bình của các cột số.

**Lưu ý:**

* **Hiệu suất:** Nếu bảng `Batches` rất lớn, việc tạo view và thực hiện các truy vấn có thể ảnh hưởng đến hiệu suất của cơ sở dữ liệu. Bạn có thể tạo index cho các cột thường được sử dụng để tăng tốc độ truy vấn.
* **Cập nhật view:** View chỉ là một "lát cắt" của dữ liệu tại thời điểm tạo. Nếu dữ liệu trong bảng `Batches` thay đổi, bạn cần làm mới view để có kết quả chính xác.
* **Materialized view:** Một số hệ quản trị cơ sở dữ liệu hỗ trợ materialized view, đây là một loại view đặc biệt lưu trữ kết quả của truy vấn. Việc cập nhật materialized view có thể được tự động hóa, giúp tăng tốc độ truy vấn.

**Ví dụ về materialized view (MySQL):**

```sql
CREATE MATERIALIZED VIEW vw_Inventory AS
SELECT ... -- Câu lệnh SELECT giống như ở trên
WITH REFRESH MATERIALIZED EVERY 1 HOUR;
```

**Lựa chọn phương pháp:**

Việc chọn sử dụng view thông thường hay materialized view phụ thuộc vào nhu cầu cụ thể của hệ thống, bao gồm tần suất truy vấn, kích thước dữ liệu và yêu cầu về thời gian thực.

**Với view, bạn có thể dễ dàng quản lý và theo dõi tình trạng tồn kho của từng lô hàng, từ đó đưa ra các quyết định kinh doanh hiệu quả hơn.**

**Bạn có muốn tìm hiểu thêm về các tính năng khác của view hoặc các cách tối ưu hóa hiệu suất truy vấn không?**


***
***



