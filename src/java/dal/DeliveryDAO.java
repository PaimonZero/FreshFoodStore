/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Delivery;
import model.OrderDetail;

/**
 *
 * @author PC
 */
public class DeliveryDAO extends DBContext {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Delivery> getAllDelivery() {
        List<Delivery> deliveryList = new ArrayList<>();
        String sql = "SELECT \n"
                + "    o.orderId,\n"
                + "    o.receiverName AS userName,\n"  // Changed from u.fullName to o.receiverName
                + "    o.receiverPhone,\n"
                + "    o.paymentStatus,\n"
                + "    SUM(od.unitPriceOut * od.quantity * (1 - COALESCE(p.discount, 0) / 100)) + 20000 AS totalValue,\n" // Tính tổng giá trị có giảm giá và thêm phí ship
                + "    o.orderCreatedAt AS orderDate,\n"
                + "    o.deliveryStatus\n"
                + "FROM Orders o \n"
                + "JOIN Users u ON o.userId = u.userId \n"
                + "JOIN OrderDetails od ON o.orderId = od.orderId \n"
                + "JOIN BatchesProduct bp ON od.batchId = bp.batchId  -- Thêm bảng BatchesProduct để lấy productId\n"
                + "LEFT JOIN Promos p ON bp.productId = p.productId       -- Thêm bảng Promos để lấy discount \n"
                + "WHERE o.isConfirmed = 1 \n"
                + "GROUP BY \n"
                + "    o.orderId, o.receiverName, o.receiverPhone, o.paymentStatus, o.orderCreatedAt, o.deliveryStatus\n" // Updated GROUP BY to include o.receiverName
                + "ORDER BY \n"
                + "    CASE \n"
                + "        WHEN o.deliveryStatus = 'Waiting' THEN 0  -- Đặt trạng thái \"Waiting\" lên đầu\n"
                + "        ELSE 1 \n"
                + "    END,\n"
                + "    o.orderCreatedAt DESC;  -- Sau đó sắp xếp theo ngày tạo đơn hàng mới nhất";

        try {
            connection = getConnection();
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            while (resultSet.next()) {
                // Lấy dữ liệu từ resultSet
                String customerName = resultSet.getString("userName");
                String customerPhone = resultSet.getString("receiverPhone");
                int orderId = resultSet.getInt("orderId");
                String paymentStatus = resultSet.getString("paymentStatus");
                String deliveryStatus = resultSet.getString("deliveryStatus");
                Date orderCreatedAt = resultSet.getDate("orderDate");
                BigDecimal total = resultSet.getBigDecimal("totalValue");

                // Tạo đối tượng Delivery từ các giá trị vừa lấy
                Delivery delivery = new Delivery(customerName, customerPhone, orderId, paymentStatus, deliveryStatus, orderCreatedAt, total);

                // Thêm đối tượng Delivery vào danh sách
                deliveryList.add(delivery);
            }
        } catch (SQLException e) {
            System.err.println("Error in getAllDelivery: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return deliveryList;
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
                + "    o.deliveryStatus, \n"
                + "    o.paymentType, \n"
                + "   o.paymentStatus, \n"
                + "    o.orderCreatedAt, \n"
                + "    pro.discount\n"
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
                + "    Promos pro ON p.productId = pro.productId  -- Sử dụng LEFT JOIN để không bỏ lỡ sản phẩm không có khuyến mãi\n"
                + "WHERE \n"
                + "    od.orderId = ?;  -- Thay thế ? bằng ID đơn hàng thực tế khi thực hiện truy vấn";

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
                OrderDetail orderDetails = new OrderDetail(orderDetailId, orderId, batchId, unitPriceOut, quantity, productImage, productName, fullName,
                        address, email, phone, receiverName, deliveryLocation, receiverPhone, shippingFee, deliveryStatus, paymentType, orderCreatedAt, discount);
                orderDetails.setPaymentStatus(paymentStatus);
                orderDetail.add(orderDetails);

            }
            return orderDetail;
        } catch (SQLException e) {
            System.out.println("??(DeliveryDAO)listOrderDetail" + e.getMessage());
        }
        return null;
    }

    public String getCurrentStatus(int orderId) {
        String sql = "SELECT deliveryStatus FROM Orders WHERE orderId = ?";
        String currentStatus = null;

        try (Connection connection = getConnection(); PreparedStatement preStatement = connection.prepareStatement(sql)) {

            preStatement.setInt(1, orderId);

            try (ResultSet resultSet = preStatement.executeQuery()) {
                if (resultSet.next()) {
                    currentStatus = resultSet.getString("deliveryStatus"); // Lấy giá trị trạng thái
                }
            }

        } catch (SQLException e) {
            System.err.println("Error in getCurrentStatus: " + e.getMessage());
        }

        return currentStatus; // Trả về trạng thái hiện tại hoặc null nếu không tìm thấy
    }

    //Hàm lấy dữ shipperID để lấy list đơn hàng của mỗi shipper riêng biệt
    public int getShipperIdByUserId(int userId) {
        int result = 0;
        String query = "SELECT [shipperId]\n"
                + "FROM [dbo].[Shippers]\n"
                + "WHERE [userId] = ?";

        try {
            connection = getConnection();
            if (connection != null) {
                preStatement = connection.prepareStatement(query);
                preStatement.setObject(1, userId);
                resultSet = preStatement.executeQuery();

                if (resultSet.next()) {
                    result = resultSet.getInt("shipperId");
                }
            }
        } catch (SQLException e) {
            System.err.println("??(DeliveryDAO)getShipperIdByUserId: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<Delivery> getAllDeliveryForShipper(int shipperId) {
        List<Delivery> deliveryList = new ArrayList<>();
        String sql = "SELECT \n"
                + "    o.orderId,\n"
                + "    o.receiverName AS userName,\n"  // Changed from u.fullName to o.receiverName
                + "    o.receiverPhone,\n"
                + "    o.paymentStatus,\n"
                + "    SUM(od.unitPriceOut * od.quantity * (1 - COALESCE(p.discount, 0) / 100)) + 20000 AS totalValue,\n"
                + "    o.orderCreatedAt AS orderDate,\n"
                + "    o.deliveryStatus\n"
                + "FROM \n"
                + "    Orders o \n"
                + "JOIN \n"
                + "    Users u ON o.userId = u.userId \n"
                + "JOIN \n"
                + "    OrderDetails od ON o.orderId = od.orderId \n"
                + "JOIN \n"
                + "    BatchesProduct bp ON od.batchId = bp.batchId\n"
                + "LEFT JOIN \n"
                + "    Promos p ON bp.productId = p.productId\n"
                + "WHERE \n"
                + "    o.isConfirmed = 1 \n"
                + "    AND o.shipperId = ?  -- Filter for orders with shipperId equal to ?\n"
                + "GROUP BY \n"
                + "    o.orderId, o.receiverName, o.receiverPhone, o.paymentStatus, o.orderCreatedAt, o.deliveryStatus\n" // Updated GROUP BY to include o.receiverName
                + "ORDER BY \n"
                + "    CASE \n"
                + "        WHEN o.deliveryStatus = 'Waiting' THEN 0\n"
                + "        ELSE 1 \n"
                + "    END,\n"
                + "    o.orderCreatedAt DESC;";

        try {
            connection = getConnection();
            preStatement = connection.prepareStatement(sql);
            preStatement.setObject(1, shipperId);
            resultSet = preStatement.executeQuery();

            while (resultSet.next()) {
                // Lấy dữ liệu từ resultSet
                String customerName = resultSet.getString("userName");
                String customerPhone = resultSet.getString("receiverPhone");
                int orderId = resultSet.getInt("orderId");
                String paymentStatus = resultSet.getString("paymentStatus");
                String deliveryStatus = resultSet.getString("deliveryStatus");
                Date orderCreatedAt = resultSet.getDate("orderDate");
                BigDecimal total = resultSet.getBigDecimal("totalValue");

                // Tạo đối tượng Delivery từ các giá trị vừa lấy
                Delivery delivery = new Delivery(customerName, customerPhone, orderId, paymentStatus, deliveryStatus, orderCreatedAt, total);

                // Thêm đối tượng Delivery vào danh sách
                deliveryList.add(delivery);
            }
        } catch (SQLException e) {
            System.err.println("Error in getAllDeliveryForShipper: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return deliveryList;
    }

    public List<Delivery> searchDeliveries(int shipperId, String query) {
        List<Delivery> deliveryList = new ArrayList<>();
        String sql = """
                 SELECT 
                     o.orderId,
                     o.receiverName AS userName,  -- Change from u.fullName to o.receiverName
                     o.receiverPhone,
                     o.paymentStatus,
                     SUM(od.unitPriceOut * od.quantity * (1 - COALESCE(p.discount, 0) / 100)) + 20000 AS totalValue,
                     o.orderCreatedAt AS orderDate,
                     o.deliveryStatus
                 FROM 
                     Orders o
                 JOIN 
                     Users u ON o.userId = u.userId
                 JOIN 
                     OrderDetails od ON o.orderId = od.orderId
                 JOIN 
                     BatchesProduct bp ON od.batchId = bp.batchId
                 LEFT JOIN 
                     Promos p ON bp.productId = p.productId
                 WHERE 
                     o.isConfirmed = 1
                     AND o.shipperId = ?  -- Filter for orders with shipperId equal to ?
                     AND (o.orderId LIKE ? OR o.receiverName LIKE ? OR o.receiverPhone LIKE ?)  -- Search conditions
                 GROUP BY 
                     o.orderId, o.receiverName, o.receiverPhone, o.paymentStatus, o.orderCreatedAt, o.deliveryStatus
                 ORDER BY 
                     CASE 
                         WHEN o.deliveryStatus = 'Waiting' THEN 0
                         ELSE 1 
                     END,
                     o.orderCreatedAt DESC;""";

        try {
            connection = getConnection();
            preStatement = connection.prepareStatement(sql);
            preStatement.setObject(1, shipperId);
            String searchPattern = "%" + query + "%";  // Using wildcards for LIKE
            preStatement.setString(2, searchPattern); // For orderId
            preStatement.setString(3, searchPattern); // For receiverName
            preStatement.setString(4, searchPattern); // For receiverPhone

            resultSet = preStatement.executeQuery();

            while (resultSet.next()) {
                // Retrieve data from resultSet
                String customerName = resultSet.getString("userName");  // Still using userName for convenience
                String customerPhone = resultSet.getString("receiverPhone");
                int orderId = resultSet.getInt("orderId");
                String paymentStatus = resultSet.getString("paymentStatus");
                String deliveryStatus = resultSet.getString("deliveryStatus");
                Date orderCreatedAt = resultSet.getDate("orderDate");
                BigDecimal total = resultSet.getBigDecimal("totalValue");

                // Create a Delivery object from the retrieved values
                Delivery delivery = new Delivery(customerName, customerPhone, orderId, paymentStatus, deliveryStatus, orderCreatedAt, total);

                // Add Delivery object to the list
                deliveryList.add(delivery);
            }
        } catch (SQLException e) {
            System.err.println("Error in searchDeliveries: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return deliveryList;
    }

    public boolean updateDeliveryStatus(int orderId, String newStatus, String paymentStatus) {
        String sql = "UPDATE Orders SET deliveryStatus = ? ,paymentStatus = ? WHERE orderId = ?";
        boolean isUpdated = false;

        try (Connection connection = getConnection(); PreparedStatement preStatement = connection.prepareStatement(sql)) {

            preStatement.setString(1, newStatus);
            preStatement.setString(2, paymentStatus);
            preStatement.setInt(3, orderId);

            int rowsUpdated = preStatement.executeUpdate();
            isUpdated = rowsUpdated > 0; // Returns true if at least one row is updated

        } catch (SQLException e) {
            System.err.println("Error in updateDeliveryStatus: " + e.getMessage());
        }

        return isUpdated;
    }

    public static void main(String[] args) {
//        DeliveryDAO deliveryDAO = new DeliveryDAO();
//
//        // Call getAllDelivery() to retrieve the list of deliveries
//        List<Delivery> deliveries = deliveryDAO.getAllDelivery();
//
//        // Iterate through the list and display each delivery's details
//        if (deliveries != null && !deliveries.isEmpty()) {
//            for (Delivery delivery : deliveries) {
//                System.out.println("Order ID: " + delivery.getOrderId());
//                System.out.println("Customer Name: " + delivery.getUserName());
//                System.out.println("Customer Phone: " + delivery.getReceiverPhone());
//                System.out.println("Payment Status: " + delivery.getPaymentStatus());
//                System.out.println("Delivery Status: " + delivery.getDeliveryStatus());
//                System.out.println("Order Created At: " + delivery.getOrderDate());
//                System.out.println("Total Value: " + delivery.getTotalValue());
//                System.out.println("---------------");
//            }
//        } else {
//            System.out.println("No deliveries found.");
//        }

        DeliveryDAO deliveryDAO = new DeliveryDAO();
        int testOrderId = 2; // Thay đổi orderId theo đơn hàng mà bạn muốn kiểm tra

        // Gọi phương thức getCurrentStatus và in ra kết quả
        String currentStatus = deliveryDAO.getCurrentStatus(testOrderId);

        if (currentStatus != null) {
            System.out.println("Current status for order ID " + testOrderId + ": " + currentStatus);
        } else {
            System.out.println("No status found for order ID " + testOrderId);
        }

        System.out.println("Test hàm lấy shipperID với userId = 12\n ==>Kết quả: " + deliveryDAO.getShipperIdByUserId(12));
    }

}
