package dal;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import dto.OrderDTO;
import dto.ShipperDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Date;
import model.OrderDetail;

public class OrdersDAO extends DBContext {
//=================================================================================================================================================================
    // Hiển thị thông tin cho phần Tổng quan đơn hàng
    // Lấy thông tin tổng quan của đơn hàng

    public Map<String, Object> getOrderOverview() {
        Map<String, Object> overview = new HashMap<>();
        connection = getConnection();

        String sql = """
            SELECT
                COUNT(DISTINCT o.orderId) AS totalOrders,
                SUM(CASE WHEN o.deliveryStatus = 'Shipping' THEN 1 ELSE 0 END) AS totalShippingOrders,
                SUM(CASE WHEN o.deliveryStatus = 'Waiting' THEN 1 ELSE 0 END) AS totalWaitingOrders,
                SUM(CASE WHEN o.paymentStatus = 'Paid' THEN od.unitPriceOut * od.quantity ELSE 0 END) AS totalRevenue
            FROM Orders o
            JOIN OrderDetails od ON o.orderId = od.orderId;
        """;

        try {
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();
            if (resultSet.next()) {
                overview.put("totalOrders", resultSet.getInt("totalOrders"));
                overview.put("totalRevenue", resultSet.getDouble("totalRevenue"));
                overview.put("totalShipping", resultSet.getInt("totalShippingOrders"));
                overview.put("totalWaiting", resultSet.getInt("totalWaitingOrders"));
            }
        } catch (SQLException ex) {
            System.out.println("Error in getOrderOverview: " + ex.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preStatement != null) {
                    preStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error in closing resources: " + e.getMessage());
            }
        }
        return overview;
    }

    // Hiện thị tất cả các thông tin Orders cho trang Orders.jsp
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> orderDisplayList = new ArrayList<>();
        connection = getConnection();
        // Check db connection trong terminal
        if (connection != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Connection failed.");
        }

         String sql = "SELECT \n"
            + "    o.orderId, \n"
            + "    o.receiverName AS customerName, \n"
            + "    o.paymentStatus, \n"
            + "    SUM(od.unitPriceOut * od.quantity * (1 - COALESCE(p.discount, 0) / 100)) + 20000 AS totalPrice, \n" // Tính tổng giá trị đã áp dụng giảm giá và thêm phí vận chuyển
            + "    o.orderCreatedAt AS orderDate, \n"
            + "    u_shipper.fullName AS shipperName, \n"
            + "    o.deliveryStatus\n"
            + "FROM Orders o\n"
            + "JOIN Users u ON o.userId = u.userId                        -- Người đặt hàng\n"
            + "LEFT JOIN Shippers s ON o.shipperId = s.shipperId           -- Người giao hàng\n"
            + "LEFT JOIN Users u_shipper ON s.userId = u_shipper.userId    -- Lấy tên của shipper từ bảng Users thông qua userId\n"
            + "JOIN OrderDetails od ON o.orderId = od.orderId\n"
            + "JOIN BatchesProduct bp ON od.batchId = bp.batchId           -- Nối bảng BatchesProduct để lấy productId\n"
            + "LEFT JOIN Promos p ON bp.productId = p.productId            -- Nối bảng Promos để lấy discount\n"
            + "GROUP BY \n"
            + "    o.orderId, \n"
            + "    o.receiverName, \n"
            + "    o.paymentStatus, \n"
            + "    o.orderCreatedAt, \n"
            + "    u_shipper.fullName, \n"
            + "    o.deliveryStatus\n"
            + "ORDER BY \n"
            + "    CASE \n"
            + "        WHEN u_shipper.fullName IS NULL THEN 0  -- Đưa các đơn hàng chưa có shipper lên đầu\n"
            + "        WHEN o.deliveryStatus = 'Waiting' THEN 1\n"
            + "        WHEN o.deliveryStatus = 'Shipping' THEN 2\n"
            + "        ELSE 3\n"
            + "    END,\n"
            + "    o.orderCreatedAt ASC;";

        try {
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();
            while (resultSet.next()) {
                // Lấy dữ liệu từ resultSet
                int orderId = resultSet.getInt("orderId");
                String customerName = resultSet.getString("customerName");
                String paymentStatus = resultSet.getString("paymentStatus");
                double totalPrice = resultSet.getDouble("totalPrice");
                String orderDate = resultSet.getString("orderDate");
                String shipperName = resultSet.getString("shipperName");
                String deliveryStatus = resultSet.getString("deliveryStatus");

                // Tạo đối tượng Orders
                OrderDTO orderDisplay = new OrderDTO(orderId, customerName, paymentStatus, totalPrice, orderDate, shipperName, deliveryStatus);

                // Thêm đối tượng vào danh sách
                orderDisplayList.add(orderDisplay);
            }
        } catch (SQLException ex) {
            System.out.println("Error in getAllOrders: " + ex.getMessage());
        } finally {
            // Đóng tất cả các tài nguyên sau khi truy vấn xong
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preStatement != null) {
                    preStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error in closing resources: " + e.getMessage());
            }
        }
        return orderDisplayList;
    }

    public List<OrderDetail> getOrderDetailById(int orderId) {
        List<OrderDetail> orderDetail = new ArrayList<>();
        //- connect w/Database
        connection = getConnection();
        //- Chuan bi cau lenhj sql
        String sql = "SELECT \n"
                + "    p.image, \n"
                + "    p.name, \n"
                + "    od.orderDetailId, \n"
                + "    od.orderId,\n"
                + "    od.batchId, \n"
                + "    od.unitPriceOut, \n"
                + "    od.quantity, \n"
                + "    u.fullName, \n"
                + "    u.address, \n"
                + "    u.email, \n"
                + "    u.phone, \n"
                + "    o.receiverName, \n"
                + "    o.deliveryLocation, \n"
                + "    o.receiverPhone, \n"
                + "    o.shippingFee, \n"
                + "    o.deliveryStatus,\n"
                + "    o.paymentType, \n"
                + "    o.paymentStatus, \n"
                + "    o.orderCreatedAt,\n"
                + "	o.shipperId,\n"
                + "    pro.discount,\n"
                + "    s.fullName AS shipperName,          -- Shipper's name\n"
                + "    s.phone AS shipperPhone              -- Shipper's phone number\n"
                + "FROM \n"
                + "    OrderDetails od \n"
                + "INNER JOIN \n"
                + "    BatchesProduct bp ON od.batchId = bp.batchId\n"
                + "INNER JOIN \n"
                + "    Products p ON bp.productId = p.productId\n"
                + "INNER JOIN \n"
                + "    Orders o ON od.orderId = o.orderId\n"
                + "INNER JOIN \n"
                + "    Users u ON o.userId = u.userId\n"
                + "LEFT JOIN \n"
                + "    Shippers sh ON o.shipperId = sh.shipperId   -- Join to get shipper info\n"
                + "LEFT JOIN \n"
                + "    Users s ON sh.userId = s.userId              -- Join to get shipper's name and phone from Users\n"
                + "LEFT JOIN \n"
                + "    Promos pro ON p.productId = pro.productId     -- Use LEFT JOIN to include products without promotions\n"
                + "WHERE \n"
                + "    od.orderId = ?;                              -- Replace 1 with the desired order ID";

        try {
            //- Tao doi tuong prepareStatement
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, orderId);
            //- set parameter (optional)
            //- thuc thi cau lenh
            resultSet = preStatement.executeQuery();
            //- tra ve ket qua
            while (resultSet.next()) {
                int orderDetailId = resultSet.getInt("orderDetailId");
                orderId = resultSet.getInt("orderId");
                int batchId = resultSet.getInt("batchId");
                double unitPriceOut = resultSet.getDouble("unitPriceOut");
                int quantity = resultSet.getInt("quantity");
                String productImage = resultSet.getString("image");
                String productName = resultSet.getString("name");
                //thuộc tính không có trong bảng orderDetail
                String fullName = resultSet.getString("fullName");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String receiverName = resultSet.getString("receiverName");
                String deliveryLocation = resultSet.getString("deliveryLocation");
                String receiverPhone = resultSet.getString("receiverPhone");
                double shippingFee = resultSet.getDouble("shippingFee");
                String deliveryStatus = resultSet.getString("deliveryStatus");
                String paymentType = resultSet.getString("paymentType");
                String paymentStatus = resultSet.getString("paymentStatus");
                Date orderCreatedAt = resultSet.getDate("orderCreatedAt");
                double discount = resultSet.getDouble("discount");
                //Nam thêm vào
                int shipperId = resultSet.getInt("shipperId");
                String shipperName = resultSet.getString("shipperName");
                String shipperPhone = resultSet.getString("shipperPhone");

                OrderDetail orderDetails = new OrderDetail(orderDetailId, orderId, batchId, unitPriceOut, quantity, productImage, productName, fullName,
                        address, email, phone, receiverName, deliveryLocation, receiverPhone, shippingFee, deliveryStatus, paymentType, orderCreatedAt, discount);
                orderDetails.setPaymentStatus(paymentStatus);
                orderDetails.setShipperId(shipperId);
                orderDetails.setShipperName(shipperName);
                orderDetails.setShipperPhone(shipperPhone);

                orderDetail.add(orderDetails);
            }
            return orderDetail;
        } catch (SQLException e) {
            System.out.println("??(OrderDAO) getOrderDetailById" + e.getMessage());
        }
        return null;
    }

    public void updateDelivery(int orderId, int shipperId) {
        //- connect w/Database
        connection = getConnection();
        //- Chuan bi cau lenh sql
        String sql = """
                     UPDATE Orders
                        SET shipperId = ?
                      WHERE orderId = ?;""";
        try {
            //- Tao doi tuong prepareStatement (thêm generated key vao tham so thu 2)
            preStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //- set parameter
            preStatement.setInt(1, shipperId);
            preStatement.setInt(2, orderId);

            //- thuc thi cau lenh
            preStatement.executeUpdate();
            //- tra ve ket qua mới thêm
            resultSet = preStatement.getGeneratedKeys();
        } catch (SQLException e) {
            System.out.println("??updatePasswordUserDB: " + e.getMessage());
        }
    }

    //Mục đích sử dụng: Lấy danh sách shipper để chọn người giao hàng
    public List<ShipperDTO> getAllShipperInfo() {
        List<ShipperDTO> listShipper = new ArrayList<>();
        //- connect w/database
        connection = getConnection();
        //- Chuẩn bị câu lệnh sql
        String sql = "SELECT \n"
                + "    sh.shipperId,\n"
                + "    sh.userId,\n"
                + "    us.fullName,\n"
                + "    us.phone,\n"
                + "    us.status\n"
                + "FROM [Shippers] sh\n"
                + "INNER JOIN Users us ON sh.userId = us.userId\n"
                + "WHERE us.status != 'block';";
        try {
            //- tạo đối tượng PrepareStatement
            preStatement = connection.prepareStatement(sql);
            //- Set parameter (optional)

            //- Thực thi câu lệnh
            resultSet = preStatement.executeQuery();
            //- Trả về kết quả
            while (resultSet.next()) {
                int shipperId = resultSet.getInt("shipperId");
                int userId = resultSet.getInt("userId");
                String fullName = resultSet.getString("fullName");
                String phone = resultSet.getString("phone");
                String status = resultSet.getString("status");

                ShipperDTO sh = new ShipperDTO(shipperId, userId, fullName, phone, status);
                listShipper.add(sh);
            }
        } catch (SQLException e) {
            System.out.println("??(OrderDAO)getAllShipperInfo: " + e.getMessage());
        }
        return listShipper;
    }

//=================================================================================================================================================================
    public static void main(String[] args) {
        // Tạo một đối tượng của OrdersDAO để gọi getAllOrders()
        OrdersDAO ordersDAO = new OrdersDAO();

        // Gọi phương thức getAllShipperInfo và lưu kết quả
        List<ShipperDTO> shippers = ordersDAO.getAllShipperInfo();

        // Kiểm tra và in ra danh sách shipper
        if (shippers != null && !shippers.isEmpty()) {
            System.out.println("Danh sách shipper:");
            for (ShipperDTO shipper : shippers) {
                System.out.println("ShipperId: " + shipper.getShipperId()
                        + ", UserId: " + shipper.getUserId()
                        + ", FullName: " + shipper.getFullName()
                        + ", Phone: " + shipper.getPhone()
                        + ", Status: " + shipper.getStatus());
            }
        } else {
            System.out.println("Không có shipper nào.");
        }

//        ordersDAO.updateDelivery(12, 3);
//        Map<String, Object> orderOverview = ordersDAO.getOrderOverview();
//
//        if (orderOverview != null) {
//            System.out.println("Order Overview:");
//            System.out.println("Total Orders: " + orderOverview.get("totalOrders"));
//            System.out.println("Total Shipping Orders: " + orderOverview.get("totalShipping"));
//            System.out.println("Total Waiting Orders: " + orderOverview.get("totalWaiting"));
//            System.out.println("Total Revenue: " + orderOverview.get("totalRevenue"));
//        } else {
//            System.out.println("No data available for order overview.");
//        }
//        // Lấy danh sách các đơn hàng từ CSDL
//        List<OrderDTO> orderDisplayList = ordersDAO.getAllOrders();
//
//        // In ra danh sách các đơn hàng
//        if (orderDisplayList != null && !orderDisplayList.isEmpty()) {
//            for (OrderDTO orderDisplay : orderDisplayList) {
//                // Sử dụng phương thức toString() của lớp OrderDisplay để hiển thị thông tin
//                System.out.println(orderDisplay);
//            }
//        } else {
//            System.out.println("No orders found.");
//        }
//        // Replace with a valid orderId for testing
//        int testOrderId = 10;
//
//        // Call the method to get order details
//        List<OrderDetail> orderDetails = ordersDAO.getOrderDetailById(testOrderId);
        // Check if the orderDetails list is not null and print the details
//        if (orderDetails != null && !orderDetails.isEmpty()) {
//            for (OrderDetail detail : orderDetails) {
//                System.out.println("Order Detail ID: " + detail.getOrderDetailId());
//                System.out.println("Order ID: " + detail.getOrderId());
//                System.out.println("Batch ID: " + detail.getBatchId());
//                System.out.println("Product Name: " + detail.getProductName());
//                System.out.println("Unit Price: " + detail.getUnitPriceOut());
//                System.out.println("Quantity: " + detail.getQuantity());
//                System.out.println("Customer Full Name: " + detail.getFullName());
//                System.out.println("Shipper Name: " + detail.getShipperName());
//                System.out.println("Shipper Phone: " + detail.getShipperPhone());
//                System.out.println("Order Created At: " + detail.getOrderCreatedAt());
//                System.out.println("--------------------------");
//            }
//        } else {
//            System.out.println("No order details found for order ID: " + testOrderId);
//        }
    }
}
