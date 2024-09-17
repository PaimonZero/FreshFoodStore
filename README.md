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

**4. Bảng Users (Người dùng hệ thống admin):**
   * **Mục đích:** Lưu trữ thông tin về người dùng.
   * **Các trường:**
     * **userId:** Mã người dùng
     * **fullName:** Tên người dùng
     * **address:** Địa chỉ.
     * **phone:** Số điện thoại.
     * **email:** Email.
     * **password:** Mật khẩu.
     * **roleId:** Quyền của User.
     * **otp:** Mã otp của người dùng.

**4. Bảng Customer (Khách hàng):**
   * **Mục đích:** Lưu trữ thông tin về người dùng.
   * **Các trường:**
     * **customerId:** Mã khách hàng.
     * **fullName:** Tên khách hàng.
     * **address:** Địa chỉ.
     * **phone:** Số điện thoại.
     * **email:** Email.
     * **password:** Mật khẩu.
     * **otp:** Mã otp của người dùng.
       
**4. Bảng Shipper (Người giao hàng):**
   * **Mục đích:** Lưu trữ thông tin về người dùng.
   * **Các trường:**
     * **shipperId:** Mã khách hàng.
     * **fullName:** Tên khách hàng.
     * **address:** Địa chỉ.
     * **phone:** Số điện thoại.
     * **email:** Email.
     * **password:** Mật khẩu.
     * **otp:** Mã otp của người dùng.
       
**5. Bảng Inputs (Phiếu nhập):**
   * **Mục đích:** Lưu trữ thông tin về các phiếu nhập hàng.
   * **Các trường:**
     * **inputId:** Mã phiếu nhập.
     * **dateInput:** Ngày nhập hàng.

**6. Bảng InputDetails (Chi tiết phiếu nhập):**
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
     * **Nhiều đối một** với bảng Products (qua productId): 1 chi tiết đơn nhập chỉ cho 1 product, 1 product thì có thể có nhiều chi tiết đơn nhập
       
**7. Bảng Orders (Đơn hàng):**
   * **Mục đích:** Lưu trữ thông tin về các phiếu xuất hàng.
   * **Các trường:**
     * **orderId:** Mã đơn hàng.
     * **dateOutput:** Ngày xuất hàng.
     * **shippingFee:** Phí vận chuyển.
     * **status:** Tình trạng vận chuyển
     * **customerId:** Người đặt hàng       
     * **shipperId:** Người chuyển đơn      
   * **Mối quan hệ:**
     * **Nhiều đối một** với bảng Customers (qua customerId ): 1 khách hàng có thể có nhiều order
     * **Nhiều đối một** với bảng Shippers (qua shipperId ): 1 shipper có thể vận chuyển nhiều order
     * **Lưu ý:** Vì khi tạo bảng với shipperId là khóa phụ thì ko đc để trống => khi order đc tạo thì shipperId mặt định sẽ là manager hoặc ai đó
     * 
**8. Bảng OrderDetails (Chi tiết phiếu xuất):**
   * **Mục đích:** Lưu trữ chi tiết các sản phẩm trong một đơn hàng.
   * **Các trường:**
     * **outputDetailId:** Mã chi tiết phiếu xuất.
     * **orderId:** Liên kết đến phiếu xuất.
     * **batchId:** Mã lô hàng của sản phẩm
     * **unitPriceOut:** Giá tiền của sản phẩm khi bán ra
     * **quantity:** Số lượng xuất.
     * **isReturn:** Trường check số lượng mặt hàng bị trả trong 1 order
   * **Mối quan hệ:**
     * **Nhiều đối một** với bảng Orders (qua orderId): 1 đơn hàng có nhiều mặt hàng khác nhau
     * **Nhiều đối một** với bảng BatchesProduct (qua batchId): 1 chi tiết đơn hàng chỉ cho 1 product, 1 product thì có thể có nhiều chi tiết đơn hàng
   * **Lưu ý:**
     * **Việc cập nhật batchId** trường này sẽ được cập nhật khi customer ấn để vào giỏ hàng và ấn thanh toán, bằng cách sử dụng order by để tìm lô hàng cũ nhất của sản phẩm đó.
       Điều này giúp ta có thể quản lý được số lượng và hạn sử đụng của sản phẩm tốt hơn theo từng lô nhập về 
```
SELECT *  
    SELECT *  
    FROM table_name  
    ORDER BY ngay_thang_nam DESC;     //desc là giảm dần (từ mới nhất đến cũ nhất)
```


**9. Bảng Inventories (Tồn kho):** (Sử dụng view hoặc truy vấn)
   * **Mục đích:** Theo dõi số lượng tồn kho thực tế của từng sản phẩm tại từng thời điểm.
   * **Các trường:**
     * **inventoryId:** Mã kho.
     * **productId:** Liên kết đến sản phẩm.
     * **quantity:** Số lượng hiện có.
     * **lastUpdated:** Thời điểm cập nhật cuối cùng.

**10. Bảng Promos (Khuyến mãi):**
   * **Mục đích:** Quản lý các chương trình khuyến mãi.
   * **Các trường:**
     * **promotionId:** Id chương trình khuyến mãi.
     * **productId:** Sản phẩm giảm giá (khóa ngoại)
     * **quantitySale:** số lượng sản phẩm khuyến mãi
     * **discount:** Mức giảm giá.
     * **startDate**, **endDate:** Thời gian bắt đầu và kết thúc khuyến mãi.
   * **Mối quan hệ:**
     * **Nhiều đối một** với bảng Products (qua productId): 1 mặt hàng có thể có nhiều lần khuyến mãi
     
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
     * **requestId:** Mã yêu cầu đổi trả
     * **orderDetailId:** Liên kết chi tiết đơn bán.
     * **returnReason:** Lý đo đổi trả.
     * **requestDate:** Ngày yêu cầu.
     * **status:** Trạng thái xử lý.
   * **Mối quan hệ:**
     * **Một đối một** với bảng OrderDetails (qua orderDetailId): 1 yêu cầu hoàn tiền chỉ liên kết với sản phẩm cụ thể trong order.
     * **Nhiều đối một** với bảng Users (qua customerId): 1 customer có thể gửi request return từ nhiều đơn khác nhau.

**13. Bảng Roles:
   * **Mục đích:** Thông tin về role của user, bao gồm: staff, manager
   * **Các trường:**
     * **roleId:** Mã role.
     * **name:** Tên của role
  
**13. Bảng Category:
   * **Mục đích:** Thông tin về danh mục sản phẩm
   * **Các trường:**
     * **categoryId:** Mã role.
     * **name:** Tên của category
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



***
***



