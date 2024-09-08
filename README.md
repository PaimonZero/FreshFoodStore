# QuanLyKhoHang
Đồ án môn SWP391

### Phân tích chi tiết các bảng và mối quan hệ

**1. Bảng Objects (Sản phẩm):**
   * **Mục đích:** Lưu trữ thông tin chi tiết về từng sản phẩm.
   * **Các trường:**
     * **ObjectId:** Mã sản phẩm duy nhất.
     * **Name:** Tên sản phẩm.
     * **UnitId:** Liên kết đến đơn vị tính của sản phẩm.
     * **SupplierId:** Liên kết đến nhà cung cấp của sản phẩm.
     * **ExpiryDate:** Ngày hết hạn.
     * **Description:** Mô tả chi tiết về sản phẩm.
     * **Image:** Đường dẫn đến hình ảnh sản phẩm.
   * **Mối quan hệ:**
     * **Một đối một** với bảng Units (qua UnitId): Mỗi sản phẩm chỉ có một đơn vị tính.
     * **Một đối một** với bảng Suppliers (qua SupplierId): Mỗi sản phẩm chỉ có một nhà cung cấp.

**2. Bảng Units (Đơn vị tính):**
   * **Mục đích:** Lưu trữ danh sách các đơn vị tính sử dụng.
   * **Các trường:**
     * **UnitId:** Mã đơn vị tính.
     * **Name:** Tên đơn vị tính (ví dụ: kg, lít, cái).

**3. Bảng Suppliers (Nhà cung cấp):**
   * **Mục đích:** Lưu trữ thông tin về các nhà cung cấp.
   * **Các trường:**
     * **SupplierId:** Mã nhà cung cấp.
     * **Name:** Tên nhà cung cấp.
     * **Address:** Địa chỉ.
     * **Phone:** Số điện thoại.
     * **Email:** Email.
     * **MoreInfo:** Thông tin thêm.

**4. Bảng Customers (Khách hàng):**
   * **Mục đích:** Lưu trữ thông tin về khách hàng.
   * **Các trường:**
     * **CustomerId:** Mã khách hàng.
     * **Name:** Tên khách hàng.
     * **Address:** Địa chỉ.
     * **Phone:** Số điện thoại.
     * **Email:** Email.
     * **MoreInfo:** Thông tin thêm.

**5. Bảng Input (Phiếu nhập):**
   * **Mục đích:** Lưu trữ thông tin về các phiếu nhập hàng.
   * **Các trường:**
     * **InputId:** Mã phiếu nhập.
     * **TotalPrice:** Tổng giá trị phiếu nhập.
     * **DateInput:** Ngày nhập hàng.

**6. Bảng InputDetail (Chi tiết phiếu nhập):**
   * **Mục đích:** Lưu trữ chi tiết các sản phẩm trong một phiếu nhập.
   * **Các trường:**
     * **InputDetailId:** Mã chi tiết phiếu nhập.
     * **InputId:** Liên kết đến phiếu nhập.
     * **ObjectId:** Liên kết đến sản phẩm.
     * **Quantity:** Số lượng nhập.
     * **InputPrice:** Giá nhập.
     * **ExpiryDate:** Ngày hết hạn của lô hàng này.
     * **BatchNumber:** Số lô hàng.
     * **UnitPriceIn:** Giá bán dự kiến.

**7. Bảng Output (Phiếu xuất):**
   * **Mục đích:** Lưu trữ thông tin về các phiếu xuất hàng.
   * **Các trường:**
     * **OutputId:** Mã phiếu xuất.
     * **TotalPrice:** Tổng giá trị phiếu xuất.
     * **DateOutput:** Ngày xuất hàng.

**8. Bảng OutputDetail (Chi tiết phiếu xuất):**
   * **Mục đích:** Lưu trữ chi tiết các sản phẩm trong một phiếu xuất.
   * **Các trường:**
     * **OutputDetailId:** Mã chi tiết phiếu xuất.
     * **OutputId:** Liên kết đến phiếu xuất.
     * **ObjectId:** Liên kết đến sản phẩm.
     * **Quantity:** Số lượng xuất.
     * **UnitPriceOut:** Giá bán ra.

**9. Bảng Inventories (Tồn kho):**
   * **Mục đích:** Theo dõi số lượng tồn kho thực tế của từng sản phẩm tại từng thời điểm.
   * **Các trường:**
     * **InventoryId:** Mã kho.
     * **ObjectId:** Liên kết đến sản phẩm.
     * **Quantity:** Số lượng hiện có.
     * **LastUpdated:** Thời điểm cập nhật cuối cùng.

**10. Bảng Promotions (Khuyến mãi):**
   * **Mục đích:** Quản lý các chương trình khuyến mãi.
   * **Các trường:**
     * **PromotionId:** Mã khuyến mãi.
     * **ObjectId:** Liên kết đến sản phẩm.
     * **Discount:** Mức giảm giá.
     * **StartDate**, **EndDate:** Thời gian bắt đầu và kết thúc khuyến mãi.

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


Tôi muốn khi hiển thị danh sách sản phẩm thì không hiện ngày hết hạn, khi chọn drop down thì hiển thị list các sản phầm theo các lô hàng và hiển thị cả ngày hết hạn. 
Vậy thì chỉnh database như thế nào

***
***

## Đánh giá và Điều chỉnh Cấu trúc Cơ sở Dữ liệu

**Cảm ơn bạn đã chia sẻ cấu trúc cơ sở dữ liệu chi tiết.** Tôi nhận thấy rằng bạn đã thiết kế một hệ thống khá toàn diện, bao gồm các bảng chính để quản lý sản phẩm, nhà cung cấp, khách hàng, phiếu nhập xuất và tồn kho.

**Tuy nhiên, tôi xin đưa ra một số gợi ý điều chỉnh để tối ưu hóa hơn nữa cấu trúc này:**

### 1. **Bảng Objects:**
* **Loại bỏ trường ExpiryDate:** Trường này nên được đưa vào bảng InputDetail, vì ngày hết hạn của một sản phẩm có thể khác nhau tùy thuộc vào lô hàng.
* **Thêm trường Status:** Để quản lý trạng thái của sản phẩm (ví dụ: đang bán, ngừng kinh doanh).

### 2. **Bảng InputDetail:**
* **Giữ nguyên:** Cấu trúc này đã khá hợp lý, lưu trữ chi tiết về từng lô hàng nhập.

### 3. **Bảng OutputDetail:**
* **Giữ nguyên:** Tương tự như bảng InputDetail, lưu trữ chi tiết về từng lô hàng xuất.

### 4. **Bảng Inventories:**
* **Loại bỏ:** Thay vào đó, chúng ta có thể tính toán số lượng tồn kho trực tiếp từ bảng InputDetail và OutputDetail khi cần thiết. Việc duy trì một bảng Inventories riêng có thể gây ra sự không nhất quán giữa dữ liệu.

### 5. **Bảng Promotions:**
* **Thêm trường ObjectId:** Để liên kết với bảng Batches thay vì bảng Objects. Điều này sẽ cho phép áp dụng khuyến mãi cho từng lô hàng cụ thể.

### Cấu trúc mới đề xuất:

| Bảng | Các trường chính | Mô tả |
|---|---|---|
| **Products** | ProductId, Name, UnitId, SupplierId, Description, Image, Status | Thông tin chung về sản phẩm |
| **Units** | UnitId, Name | Đơn vị tính |
| **Suppliers** | SupplierId, Name, Address, Phone, Email, MoreInfo | Thông tin nhà cung cấp |
| **Batches** | BatchId, ProductId, SupplierId, PurchaseDate, Quantity, UnitPrice, ExpiryDate, BatchNumber, UnitPriceIn | Thông tin chi tiết về từng lô hàng |
| **Input** | InputId, TotalPrice, DateInput | Thông tin về phiếu nhập |
| **Output** | OutputId, TotalPrice, DateOutput | Thông tin về phiếu xuất |
| **Promotions** | PromotionId, BatchId, Discount, StartDate, EndDate | Thông tin về chương trình khuyến mãi áp dụng cho từng lô hàng |

### Lý do thay đổi:
* **Loại bỏ trùng lặp:** Việc loại bỏ trường ExpiryDate khỏi bảng Objects giúp tránh trùng lặp dữ liệu và đảm bảo tính chính xác.
* **Tính linh hoạt:** Bằng cách liên kết khuyến mãi với từng lô hàng, chúng ta có thể áp dụng các chương trình khuyến mãi linh hoạt hơn.
* **Hiệu suất:** Việc loại bỏ bảng Inventories sẽ giúp giảm số lượng truy vấn và tăng hiệu suất hệ thống.

**Lợi ích của cấu trúc mới:**
* **Rõ ràng:** Cấu trúc rõ ràng, dễ hiểu và dễ quản lý.
* **Linh hoạt:** Dễ dàng mở rộng và tùy chỉnh để đáp ứng các yêu cầu mới.
* **Hiệu quả:** Giảm thiểu sự trùng lặp dữ liệu và tăng hiệu suất truy vấn.

**Các câu hỏi để thảo luận thêm:**
* **Chi tiết về trường Status:** Bạn muốn theo dõi những trạng thái nào của sản phẩm (ví dụ: đang bán, ngừng kinh doanh, tạm ngưng)?
* **Tính năng báo cáo:** Bạn muốn tạo các báo cáo nào (ví dụ: báo cáo tồn kho, báo cáo doanh thu, báo cáo sản phẩm bán chạy)?
* **Tích hợp với hệ thống khác:** Bạn có kế hoạch tích hợp hệ thống này với các hệ thống khác (ví dụ: hệ thống bán hàng, hệ thống kế toán)?

**Với cấu trúc này, bạn có thể dễ dàng xây dựng các tính năng như:**
* **Quản lý tồn kho:** Tính toán số lượng tồn kho thực tế dựa trên các giao dịch nhập xuất.
* **Quản lý khuyến mãi:** Áp dụng các chương trình khuyến mãi linh hoạt cho từng lô hàng.
* **Báo cáo doanh thu:** Tính toán doanh thu theo sản phẩm, nhà cung cấp, thời gian.

**Tôi sẵn sàng hỗ trợ bạn thêm nếu bạn có bất kỳ câu hỏi nào khác.**

**Bạn có muốn thảo luận sâu hơn về bất kỳ khía cạnh nào của cấu trúc này không?**

***
***



