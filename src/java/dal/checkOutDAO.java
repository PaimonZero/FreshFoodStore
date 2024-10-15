/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.OrderDetail;

/**
 *
 * @author DELL
 */
public class checkOutDAO extends DBContext {

    //list thông tin về order
    public ArrayList<OrderDetail> listAllCart(int userId, int orderId) {
        ArrayList<OrderDetail> allCart = new ArrayList<>();
        //- connect w/Database
        connection = getConnection();
        //- Chuan bi cau lenhj sql
        String sql = "select od.orderDetailId, o.orderId, o.userId ,p.image, p.name, od.quantity, od.unitPriceOut, pro.discount, o.shippingFee \n"
                + "from Orders o \n"
                + "join OrderDetails od on o.orderId = od.orderId\n"
                + "join BatchesProduct bp on od.batchId = bp.batchId\n"
                + "join Products p on bp.productId = p.productId\n"
                + "join Promos pro on p.productId = pro.productId\n"
                + "where o.userId = ? AND o.orderId = ?\n";
        try {
            //- Tao doi tuong prepareStatement
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, userId);
            preStatement.setInt(2, orderId);
            //- set parameter (optional)
            //- thuc thi cau lenh
            resultSet = preStatement.executeQuery();
            //- tra ve ket qua
            while (resultSet.next()) {
                int orderDetailId = resultSet.getInt("orderDetailId");
                orderId = resultSet.getInt("orderId");
                userId = resultSet.getInt("userId");
                String productImage = resultSet.getString("image");
                String productName = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                double unitPriceOut = resultSet.getDouble("unitPriceOut");
                double discount = resultSet.getDouble("discount");
                double shippingFee = resultSet.getDouble("shippingFee");
                OrderDetail orderDetail = new OrderDetail(orderDetailId, orderId, userId, unitPriceOut, quantity, productImage, productName, shippingFee, discount);
                allCart.add(orderDetail);
            }
        } catch (SQLException e) {
            System.out.println("??(DashboardDAO)listAllCart" + e.getMessage());
        }
        return allCart;
    }

    //tim
    public void updateCartInfo(String paymentStatus, String paymentType, String deliveryLocation, String receiverName, String receiverPhone, int isConfirmed, int userId, int orderId) {
        //- connect w/Database
        connection = getConnection();
        //- Chuan bi cau lenh sql
        String sql = """
                     UPDATE Orders
                                 SET 
                                 [paymentStatus] = ?,
                                 [paymentType] = ?,
                                 [deliveryLocation] = ?,
                                 [receiverName] = ?,
                                 [receiverPhone] = ?,
                                 [isConfirmed] = ?
                                 where [userId] = ? and [orderId] = ?""";
        try {
            //- Tao doi tuong prepareStatement (thêm generated key vao tham so thu 2) chưa dùng
            preStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //- set parameter
            preStatement.setObject(1, paymentStatus);      // paymentStatus
            preStatement.setObject(2, paymentType);        // paymentType
            preStatement.setObject(3, receiverName);       // receiverName
            preStatement.setObject(4, deliveryLocation);   // deliveryLocation
            preStatement.setObject(5, receiverPhone);      // receiverPhone
            preStatement.setObject(6, isConfirmed);
            preStatement.setObject(7, userId);             // userId
            preStatement.setObject(8, orderId);            // orderId
            //- thuc thi cau lenh
            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("??updateCartInfo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        checkOutDAO dao = new checkOutDAO();
//        ArrayList<OrderDetail> list1 = dao.listAllCart(1, 11);
//        for (OrderDetail item : list1) {
//            System.out.println(item.toString());
//        }
//        dao.updateCartInfo("Pending", "Paypal", "Nguyen Van C", "13 Thai Ha", "0666666666", 1, 11);
        
    }
}
