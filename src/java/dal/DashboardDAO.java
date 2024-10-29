/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import model.Orders;
import model.Users;
import model.OrderDetail;

/**
 *
 * @author DELL
 */
public class DashboardDAO extends DBContext {

    //list thông tin về user
    public Users findAllInfo(int userId) {
//        ArrayList<Users> allInfo = new ArrayList<>();
        //- connect w/Database
        connection = getConnection();
        //- Chuan bi cau lenhj sql
        String sql = "select * from Users where userId = ? ";

        try {
            //- Tao doi tuong prepareStatement
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, userId);
            //- set parameter (optional)
            //- thuc thi cau lenh
            resultSet = preStatement.executeQuery();
            //- tra ve ket qua
            if (resultSet.next()) {
                userId = resultSet.getInt("userId");
                String fullName = resultSet.getString("fullName");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String avatar = resultSet.getString("avatar");
                Users user = new Users(userId, fullName, address, phone, email, password, avatar);
//                allInfo.add(user);
                return user;
            }
        } catch (SQLException e) {
            System.out.println("??(DashboardDAO)findAllInfo" + e.getMessage());
        }
        return null;
    }

    //list ra các order gần đây dự kiến bên jsp cho hiện 3-5 orders
    public ArrayList<Orders> findAllRecentOrder(int userId) {
        ArrayList<Orders> allOrders = new ArrayList<>();
        //- connect w/Database
        connection = getConnection();
        //- Chuan bi cau lenhj sql
        String sql = "SELECT \n"
                + "    O.orderId, \n"
                + "    O.userId, \n"
                + "    u.fullName,\n"
                + "    O.shippingFee,\n"
                + "    O.isConfirmed,\n"
                + "    O.paymentStatus, \n"
                + "    O.deliveryStatus, \n"
                + "    O.paymentType,\n"
                + "    O.deliveryLocation,\n"
                + "    O.receiverName, \n"
                + "    O.receiverPhone, \n"
                + "    O.shipperId, \n"
                + "    O.orderCreatedAt,\n"
                + "    O.orderCompletedAt,\n"
                + "    OD.quantity,\n"
                + "    SUM(OD.quantity * OD.unitPriceOut *(1-(pro.discount/100))+ O.shippingFee) AS totalAmount\n"
                + "FROM \n"
                + "    Orders O\n"
                + "INNER JOIN \n"
                + "    Users u ON O.userId = u.userId\n"
                + "INNER JOIN \n"
                + "    OrderDetails OD ON O.orderId = OD.orderId\n"
                + "inner join BatchesProduct bp on od.batchId = bp.batchId\n"
                + "inner join Products p on bp.productId = p.productId\n"
                + "inner join Promos pro on p.productId = pro.productId\n"
                + "WHERE \n"
                + "    O.userId = ?\n"
                + "GROUP BY \n"
                + "    O.orderId, \n"
                + "    O.userId, \n"
                + "    u.fullName,\n"
                + "    O.shippingFee,\n"
                + "    O.isConfirmed,\n"
                + "    O.paymentStatus, \n"
                + "    O.deliveryStatus, \n"
                + "    O.paymentType,\n"
                + "    O.deliveryLocation,\n"
                + "    O.receiverName, \n"
                + "    O.receiverPhone, \n"
                + "    O.shipperId, \n"
                + "    O.orderCreatedAt,\n"
                + "    O.orderCompletedAt,\n"
                + "    OD.quantity\n"
                + "ORDER BY \n"
                + "    O.orderCreatedAt DESC;";//chỗ này chưa chắc (sắp xếp theo thứ tự giảm dần của orderCreatedAt

        try {
            //- Tao doi tuong prepareStatement
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, userId);
            //- set parameter (optional)
            //- thuc thi cau lenh
            resultSet = preStatement.executeQuery();
            //- tra ve ket qua
            while (resultSet.next()) {
                int orderId = resultSet.getInt("orderId");
                userId = resultSet.getInt("userId");
                double shippingFee = resultSet.getDouble("shippingFee");
                boolean isConfirmed = resultSet.getBoolean("isConfirmed");
                String paymentStatus = resultSet.getString("paymentStatus");
                String deliveryStatus = resultSet.getString("deliveryStatus");
                String paymentType = resultSet.getString("paymentType");
                String deliveryLocation = resultSet.getString("deliveryLocation");
                String receiverName = resultSet.getString("receiverName");
                String receiverPhone = resultSet.getString("receiverPhone");
                int shipperId = resultSet.getInt("shipperId");
                Date orderCreatedAt = resultSet.getDate("orderCreatedAt");
                Date orderCompletedAt = resultSet.getDate("orderCompletedAt");
                double total = resultSet.getDouble("totalAmount");
                int quantity = resultSet.getInt("quantity");
                Orders order = new Orders(orderId, userId, shippingFee, isConfirmed, paymentStatus, deliveryStatus,
                        paymentType, deliveryLocation, receiverName, receiverPhone, shipperId, orderCreatedAt, orderCompletedAt, total, quantity);
                allOrders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("??(DashboardDAO)findAllReccentOrder" + e.getMessage());
        }
        return allOrders;
    }

    //list hết order hiển thị theo thứ tự orderId cái này cho orderHistory
    public ArrayList<Orders> findAllOrder(int userId, int offset, int row) {
        ArrayList<Orders> allOrders = new ArrayList<>();
        //- connect w/Database
        connection = getConnection();
        //- Chuan bi cau lenhj sql
        String sql = "SELECT \n"
                + "    O.orderId, \n"
                + "    O.userId, \n"
                + "    u.fullName,\n"
                + "    O.shippingFee,\n"
                + "    O.isConfirmed,\n"
                + "    O.paymentStatus, \n"
                + "    O.deliveryStatus, \n"
                + "    O.paymentType,\n"
                + "    O.deliveryLocation,\n"
                + "    O.receiverName, \n"
                + "    O.receiverPhone, \n"
                + "    O.shipperId, \n"
                + "    O.orderCreatedAt,\n"
                + "    O.orderCompletedAt,\n"
                + "    OD.quantity,\n"
                + "    SUM(OD.quantity * OD.unitPriceOut *(1-(pro.discount/100))+ O.shippingFee) AS totalAmount\n"
                + "FROM \n"
                + "    Orders O\n"
                + "INNER JOIN \n"
                + "    Users u ON O.userId = u.userId\n"
                + "INNER JOIN \n"
                + "    OrderDetails OD ON O.orderId = OD.orderId\n"
                + "inner join BatchesProduct bp on od.batchId = bp.batchId\n"
                + "inner join Products p on bp.productId = p.productId\n"
                + "inner join Promos pro on p.productId = pro.productId\n"
                + "WHERE \n"
                + "    O.userId = ?\n"
                + "GROUP BY \n"
                + "    O.orderId, \n"
                + "    O.userId, \n"
                + "    u.fullName,\n"
                + "    O.shippingFee,\n"
                + "    O.isConfirmed,\n"
                + "    O.paymentStatus, \n"
                + "    O.deliveryStatus, \n"
                + "    O.paymentType,\n"
                + "    O.deliveryLocation,\n"
                + "    O.receiverName, \n"
                + "    O.receiverPhone, \n"
                + "    O.shipperId, \n"
                + "    O.orderCreatedAt,\n"
                + "    O.orderCompletedAt,\n"
                + "    OD.quantity\n"
                + "ORDER BY \n"
                + "    O.orderId \n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            //- Tao doi tuong prepareStatement
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, userId);
            preStatement.setInt(2, offset);
            preStatement.setInt(3, row);
            //- set parameter (optional)
            //- thuc thi cau lenh
            resultSet = preStatement.executeQuery();
            //- tra ve ket qua
            while (resultSet.next()) {
                int orderId = resultSet.getInt("orderId");
                userId = resultSet.getInt("userId");
                double shippingFee = resultSet.getDouble("shippingFee");
                boolean isConfirmed = resultSet.getBoolean("isConfirmed");
                String paymentStatus = resultSet.getString("paymentStatus");
                String deliveryStatus = resultSet.getString("deliveryStatus");
                String paymentType = resultSet.getString("paymentType");
                String deliveryLocation = resultSet.getString("deliveryLocation");
                String receiverName = resultSet.getString("receiverName");
                String receiverPhone = resultSet.getString("receiverPhone");
                int shipperId = resultSet.getInt("shipperId");
                Date orderCreatedAt = resultSet.getDate("orderCreatedAt");
                Date orderCompletedAt = resultSet.getDate("orderCompletedAt");
                double total = resultSet.getDouble("totalAmount");
                int quantity = resultSet.getInt("quantity");
                Orders order = new Orders(orderId, userId, shippingFee, isConfirmed, paymentStatus, deliveryStatus,
                        paymentType, deliveryLocation, receiverName, receiverPhone, shipperId, orderCreatedAt, orderCompletedAt, total, quantity);
                allOrders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("??(DashboardDAO)findAllOrder" + e.getMessage());
        }
        return allOrders;
    }

    public int CountOrder(int userId) {
        connection = getConnection();
        //- Chuan bi cau lenhj sql
        String sql = " SELECT \n"
                + "    count (*) \n"
                + "FROM \n"
                + "    Orders O\n"
                + "INNER JOIN \n"
                + "    Users u ON O.userId = u.userId\n"
                + "INNER JOIN \n"
                + "    OrderDetails OD ON O.orderId = OD.orderId\n"
                + "WHERE \n"
                + "    O.userId = ?";
        try {
            //- Tao doi tuong prepareStatement
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, userId);
            //- set parameter (optional)
            //- thuc thi cau lenh
            resultSet = preStatement.executeQuery();
            //- tra ve ket qua
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("??(DashboardDAO)findAllOrder" + e.getMessage());
        }
        return 0;
    }

    //list ra chi tiết về order
    public OrderDetail findOrderDetailById(int orderId) {
//        ArrayList<OrderDetail> allOrderDetail = new ArrayList<>();
        //- connect w/Database
        connection = getConnection();
        //- Chuan bi cau lenhj sql
        String sql = "select p.image, p.name, od.orderDetailId, od.orderId,od.batchId, od.unitPriceOut, "
                + "od.quan tity, u.fullName, u.address, u.email, u.phone,o.receiverName, o.deliveryLocation, "
                + "o.receiverPhone, o.shippingFee, o.deliveryStatus, o.paymentType, o.orderCreatedAt, pro.discount\n"
                + "	from OrderDetails od \n"
                + "	inner join BatchesProduct bp on od.batchId = bp.batchId\n"
                + "	inner join Products p on bp.productId = p.productId\n"
                + "	inner join Orders o on od.orderId = o.orderId\n"
                + "	inner join Users u on o.userId = u.userId\n"
                + "	inner join Promos pro on p.productId = pro.productId\n"
                + "	where od.orderId = ?";

        try {
            //- Tao doi tuong prepareStatement
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, orderId);
            //- set parameter (optional)
            //- thuc thi cau lenh
            resultSet = preStatement.executeQuery();
            //- tra ve ket qua
            if (resultSet.next()) {
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
                Date orderCreatedAt = resultSet.getDate("orderCreatedAt");
                double discount = resultSet.getDouble("discount");
                OrderDetail orderDetails = new OrderDetail(orderDetailId, orderId, batchId, unitPriceOut, quantity, productImage, productName, fullName,
                        address, email, phone, receiverName, deliveryLocation, receiverPhone, shippingFee, deliveryStatus, paymentType, orderCreatedAt, discount);
                return orderDetails;
//                allOrderDetail.add(orderDetails);
            }
        } catch (SQLException e) {
            System.out.println("??(DashboardDAO)listOrderDetail" + e.getMessage());
        }
        return null;
    }

    public Users updateUserInfo(int userId, String name, String email, String phone, String address, String avatar) {
        Users current = new Users(userId, name, email, phone, address);
        //- connect w/Database
        connection = getConnection();
        //- Chuan bi cau lenh sql
        String sql = """
                     UPDATE [dbo].[Users]
                        SET [fullName] = ?
                           ,[email] = ?
                           ,[phone] = ?
                           ,[address] = ?
                           ,[avatar] = ?
                      WHERE [userId] = ?""";
        try {
            //- Tao doi tuong prepareStatement (thêm generated key vao tham so thu 2)
            preStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //- set parameter
            preStatement.setObject(1, name);
            preStatement.setObject(2, email);
            preStatement.setObject(3, phone);
            preStatement.setObject(4, address);
            preStatement.setObject(5, avatar);
            preStatement.setObject(6, userId);

            //- thuc thi cau lenh
            preStatement.executeUpdate();
            //- tra ve ket qua mới thêm
            resultSet = preStatement.getGeneratedKeys();
            if(resultSet.next()){
                userId = resultSet.getInt(1);       //lấy id user mới cập nhật (chưa sử dụng)
                current.setAvatar(avatar);
            }
            return current;
        } catch (SQLException e) {
            System.out.println("??updateUserInfo: " + e.getMessage());
        }
        return null;
    }
    

    public static void main(String[] args) {
         // Create an instance of the class containing the findOrderDetailById() method
        DashboardDAO orderDetailDAO = new DashboardDAO();

        // Test the findOrderDetailById method with a specific orderId
        int orderIdToTest = 1;  // Change this to an actual orderId that exists in your database
        OrderDetail orderDetail = orderDetailDAO.findOrderDetailById(orderIdToTest);

        // Check if an order detail was returned
        if (orderDetail != null) {
            System.out.println("Order Detail ID: " + orderDetail.getOrderDetailId());
            System.out.println("Order ID: " + orderDetail.getOrderId());
            System.out.println("Batch ID: " + orderDetail.getBatchId());
            System.out.println("Unit Price Out: " + orderDetail.getUnitPriceOut());
            System.out.println("Quantity: " + orderDetail.getQuantity());
            System.out.println("Product Image: " + orderDetail.getProductImage());
            System.out.println("Product Name: " + orderDetail.getProductName());
            System.out.println("Customer Full Name: " + orderDetail.getFullName());
            System.out.println("Customer Address: " + orderDetail.getAddress());
            System.out.println("Customer Email: " + orderDetail.getEmail());
            System.out.println("Customer Phone: " + orderDetail.getPhone());
            System.out.println("Receiver Name: " + orderDetail.getReceiverName());
            System.out.println("Delivery Location: " + orderDetail.getDeliveryLocation());
            System.out.println("Receiver Phone: " + orderDetail.getReceiverPhone());
            System.out.println("Shipping Fee: " + orderDetail.getShippingFee());
            System.out.println("Delivery Status: " + orderDetail.getDeliveryStatus());
            System.out.println("Payment Type: " + orderDetail.getPaymentType());
            System.out.println("Order Created At: " + orderDetail.getOrderCreatedAt());
            System.out.println("Discount: " + orderDetail.getDiscount());
        } else {
            System.out.println("No order detail found for Order ID: " + orderIdToTest);
        }
    }
}
