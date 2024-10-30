/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dto.OrderDTO;
import dto.OrderDetailDTO;
import dto.ProductDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Orders;

/**
 *
 * @author nguye
 */
public class OrderDetailDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Viet cho Product
    public ProductDTO findProductById(int id) {

        String sql = "select top 1 p.*, bp.*, pr.discount FROM products p join BatchesProduct bp on p.productId = bp.productId join Promos pr on pr.productId = p.productId WHERE p.productId= ? and bp.expiryDate > getDate()";
        try {
            // connect
            con = new DBContext().getConnection();
            // sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new ProductDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBigDecimal(8),
                        rs.getString(9),
                        rs.getDate(10),
                        rs.getDate(11),
                        rs.getInt(12),
                        rs.getInt(15),
                        rs.getDate(16),
                        rs.getDouble(17)
                );

            }
        } catch (Exception e) {
            System.out.println("loi:" + e.getMessage());
        }
        return null;
    }

    // Viết cho Order
    public OrderDTO findOrderById(int userId) {
        String sql = "select o.* from Users u join Orders o on u.userId = o.userId where u.userId=? and o.isConfirmed=0";

        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            rs = ps.executeQuery();
            while (rs.next()) {
                return new OrderDTO(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getDate(12),
                        rs.getDate(13)
                );
            }

        } catch (Exception e) {

        }
        return null;

    }

    public void createOrder(OrderDTO o) {

        String sql = "insert into Orders(userId,shippingFee,isConfirmed,orderCreatedAt) values(?,?,?,?)";

        try {
            // connect
            con = new DBContext().getConnection();
            // write sql
            ps = con.prepareStatement(sql);

            ps.setInt(1, o.getUserId());
            ps.setDouble(2, 20000);
            ps.setInt(3, 0);
            ps.setDate(4, Date.valueOf(LocalDate.now()));

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("loi:" + e.getMessage());
        }

    }

    // Viết cho OrderDetail
    public OrderDetailDTO findOrderDetail(int orderId, int productId) {
        String sql = """
                     select od.*,p.*, pr.*,bp.quantity from  OrderDetails od  join BatchesProduct bp on od.batchId = bp.batchId
                     join Products p on bp.productId = p.productId 
                     join Promos pr on pr.productId = p.productId where od.orderId= ? and bp.productId = ?""";

        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, productId);

            rs = ps.executeQuery();
            while (rs.next()) {
                return new OrderDetailDTO(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getBigDecimal(4),
                        rs.getInt(5),
                        rs.getInt(6), //productId
                        rs.getString(7), //name
                        rs.getString(8), //name
                        rs.getInt(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getBigDecimal(13),
                        rs.getString(14),
                        rs.getDate(15),
                        rs.getDate(16),
                        rs.getInt(17),
                        rs.getInt(19),
                        rs.getDouble(20),
                        rs.getDate(21),
                        rs.getDate(22),
                        rs.getInt(23)
                );
            }

        } catch (Exception e) {

        }
        return null;

    }

    public OrderDetailDTO findOrderDetailByOrderId(int orderId) {
        String sql = "select od.*,p.*, pr.*,bp.quantity from  OrderDetails od  join BatchesProduct bp on od.batchId = bp.batchId\n"
                + "join Products p on bp.productId = p.productId \n"
                + "join Promos pr on pr.productId = p.productId where od.orderId= ?";

        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);

            rs = ps.executeQuery();
            while (rs.next()) {
                return new OrderDetailDTO(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getBigDecimal(4),
                        rs.getInt(5),
                        rs.getInt(6), //productId
                        rs.getString(7), //name
                        rs.getString(8), //name
                        rs.getInt(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getBigDecimal(13),
                        rs.getString(14),
                        rs.getDate(15),
                        rs.getDate(16),
                        rs.getInt(17),
                        rs.getInt(19),
                        rs.getDouble(20),
                        rs.getDate(21),
                        rs.getDate(22),
                        rs.getInt(23)
                );
            }

        } catch (Exception e) {

        }
        return null;

    }

    public List<OrderDetailDTO> listOrderDetail(int orderId) {
        String sql = """
                     SELECT od.*, p.*, pr.*,  bp.quantity,o.shippingFee
                     FROM OrderDetails od
                     JOIN BatchesProduct bp ON od.batchId = bp.batchId
                     JOIN Products p ON bp.productId = p.productId
                     JOIN Promos pr ON pr.productId = p.productId
                     JOIN Orders o on od.orderId = o.orderId
                     WHERE od.orderId = ? order by bp.expiryDate desc """;
        List<OrderDetailDTO> listOrderDetail = new ArrayList<>();

        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);

            rs = ps.executeQuery();
            while (rs.next()) {
                listOrderDetail.add(new OrderDetailDTO(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getBigDecimal(4),
                        rs.getInt(5),
                        rs.getInt(6), //productId
                        rs.getString(7), //name
                        rs.getString(8), //name
                        rs.getInt(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getBigDecimal(13),
                        rs.getString(14),
                        rs.getDate(15),
                        rs.getDate(16),
                        rs.getInt(17),
                        rs.getInt(19),
                        rs.getDouble(20),
                        rs.getDate(21),
                        rs.getDate(22),
                        rs.getInt(23),
                        rs.getDouble(24))
                );
            }

        } catch (Exception e) {
            System.out.println("loi:" + e.getMessage());
        }
        return listOrderDetail;

    }

    public void createOrderDetail(OrderDetailDTO odDTO) {
        String sql = "INSERT INTO OrderDetails (orderId,batchId, unitPriceOut, quantity) VALUES (?, ?, ?, ?)";
        try {
            // connect
            con = new DBContext().getConnection();
            // write sql
            ps = con.prepareStatement(sql);

            ps.setInt(1, odDTO.getOrderId());
            ps.setInt(2, odDTO.getBatchId());
            ps.setBigDecimal(3, odDTO.getUnitPriceOut());
            ps.setInt(4, odDTO.getQuantity());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("loi:" + e.getMessage());
        }

    }

    public OrderDetailDTO updateQuantity(OrderDetailDTO odDTO) {
        String sql = "UPDATE OrderDetails SET quantity = ? WHERE orderDetailId = ?";
        try {
            // connect
            con = new DBContext().getConnection();
            // sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, odDTO.getQuantity());
            ps.setInt(2, odDTO.getOrderDetailId());

            ps.executeUpdate();
        } catch (Exception e) {
        }
        return odDTO;

    }

    public Boolean updateQuantity2(int quantity, int orderDetailId) {
        String sql = "UPDATE OrderDetails SET quantity = ? WHERE orderDetailId = ?";
        boolean isUpdated = false;
        try {
            // connect
            con = new DBContext().getConnection();
            // sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, orderDetailId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                isUpdated = true; // Cập nhật thành công
            }

            ps.executeUpdate();
        } catch (Exception e) {
        }
        return isUpdated;

    }

    public void deleteOrderDetailById(int deleteId) {
        String sql = "DELETE FROM orderDetails  WHERE orderDetailId=?";

        try {
            // connect
            con = new DBContext().getConnection();
            // sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, deleteId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }

    // viet cho gio hang 
    public List<OrderDetailDTO> listGioHang(int userId) {
        String sql = """
                     					 SELECT od.*, p.*, pr.*, bp.quantity, o.shippingFee
                                          FROM OrderDetails od
                                          JOIN (
                                              SELECT TOP 1 ord.*
                                              FROM Orders ord 
                                              JOIN Users u ON u.userId = ord.userId
                                              WHERE ord.isConfirmed = 0 AND u.userId = ?  -- Place WHERE clause before ORDER BY
                                              ORDER BY ord.orderId ASC
                                          ) o ON od.orderId = o.orderId 
                                          JOIN BatchesProduct bp ON bp.batchId = od.batchId 
                                          JOIN Promos pr ON pr.productId = bp.productId 
                                          JOIN Products p ON p.productId = bp.productId
                                          ORDER BY o.orderId ASC;""";
        List<OrderDetailDTO> listOrderDetail = new ArrayList<>();

        try {
            con = new DBContext().getConnection();
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                listOrderDetail.add(new OrderDetailDTO(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getBigDecimal(4),
                        rs.getInt(5),
                        rs.getInt(6), //productId
                        rs.getString(7), //name
                        rs.getString(8), //name
                        rs.getInt(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getBigDecimal(13),
                        rs.getString(14),
                        rs.getDate(15),
                        rs.getDate(16),
                        rs.getInt(17),
                        rs.getInt(19),
                        rs.getDouble(20),
                        rs.getDate(21),
                        rs.getDate(22),
                        rs.getInt(23),
                        rs.getDouble(24))
                );
            }

        } catch (Exception e) {
            System.out.println("loi:" + e.getMessage());
        }
        return listOrderDetail;

    }

    public static void main(String[] args) {
        OrderDetailDAO dao = new OrderDetailDAO();
        List<OrderDetailDTO> test1 = dao.listGioHang(3);
        for (OrderDetailDTO t : test1) {
            System.out.println(t.toString());
        }
    }
}
//done