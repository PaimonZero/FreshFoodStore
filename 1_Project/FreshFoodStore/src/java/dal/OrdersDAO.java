/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import dto.OrderDTO;

/**
 *
 * @author plmin
 */
public class OrdersDAO extends DBContext {

    // Hiển thị thông tin cho phần Tổng quan đơn hàng
    // Lấy thông tin tổng quan của đơn hàng
    public Map<String, Object> getOrderOverview() {
        Map<String, Object> overview = new HashMap<>();
        connection = getConnection();

        String sql = """
            SELECT 
                COUNT(*) AS totalOrders,
                SUM(CASE WHEN deliveryStatus = 'Pending' THEN 1 ELSE 0 END) AS totalShipping,
                SUM(CASE WHEN deliveryStatus = 'Returned' OR deliveryStatus = 'Returned' THEN 1 ELSE 0 END) AS totalCanceled,
                SUM(od.unitPriceOut * od.quantity) AS totalRevenue
            FROM Orders o
            JOIN OrderDetails od ON o.orderId = od.orderId;
        """;

        try {
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();
            if (resultSet.next()) {
                overview.put("totalOrders", resultSet.getInt("totalOrders"));
                overview.put("totalRevenue", resultSet.getDouble("totalRevenue"));
                overview.put("totalShipping", resultSet.getInt("totalShipping"));
                overview.put("totalCanceled", resultSet.getInt("totalCanceled"));
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
                + "    SUM(od.unitPriceOut * od.quantity) AS totalPrice, \n"
                + "    o.orderCreatedAt AS orderDate, \n"
                + "    u_shipper.fullName AS shipperName, \n"
                + "    o.deliveryStatus\n"
                + "FROM Orders o\n"
                + "JOIN Users u ON o.userId = u.userId                        -- Người đặt hàng\n"
                + "LEFT JOIN Shippers s ON o.shipperId = s.shipperId           -- Người giao hàng\n"
                + "LEFT JOIN Users u_shipper ON s.userId = u_shipper.userId    -- Lấy tên của shipper từ bảng Users thông qua userId\n"
                + "JOIN OrderDetails od ON o.orderId = od.orderId\n"
                + "GROUP BY \n"
                + "    o.orderId, \n"
                + "    o.receiverName, \n"
                + "    o.paymentStatus, \n"
                + "    o.orderCreatedAt, \n"
                + "    u_shipper.fullName, \n"
                + "    o.deliveryStatus\n"
                + "ORDER BY o.orderId;";

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

    public static void main(String[] args) {
        // Tạo một đối tượng của OrdersDAO để gọi getAllOrders()
        OrdersDAO ordersDAO = new OrdersDAO();

        // Lấy danh sách các đơn hàng từ CSDL
        List<OrderDTO> orderDisplayList = ordersDAO.getAllOrders();

        // In ra danh sách các đơn hàng
        if (orderDisplayList != null && !orderDisplayList.isEmpty()) {
            for (OrderDTO orderDisplay : orderDisplayList) {
                // Sử dụng phương thức toString() của lớp OrderDisplay để hiển thị thông tin
                System.out.println(orderDisplay);
            }
        } else {
            System.out.println("No orders found.");
        }
    }
}
