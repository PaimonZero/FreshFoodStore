/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author DELL
 */
public class BatchProductDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void updateBatch(int userId) {
//        String sql = """
//                     WITH LatestOrder AS (
//                     select top 1 o.* from Orders o join Users u on o.userId = u.userId join OrderDetails od on o.orderId = od.orderId
//                     where u.userId = ? order by o.orderId desc
//                     )
//                     UPDATE bp
//                     SET bp.quantity = bp.quantity - od.quantity
//                     FROM BatchesProduct bp
//                     JOIN OrderDetails od ON bp.batchId = od.batchId
//                     JOIN LatestOrder lo ON od.orderId = lo.orderId;""";

        String sql = """
                    					 WITH LatestOrder AS (
                         SELECT TOP 1 o.*
                         FROM Orders o
                         JOIN Users u ON o.userId = u.userId
                         JOIN OrderDetails od ON o.orderId = od.orderId
                         WHERE u.userId = ?
                         ORDER BY o.orderId DESC
                     ),
                     LatestBatch AS (
                         SELECT TOP 1 bp.*
                         FROM BatchesProduct bp
                         JOIN OrderDetails od ON bp.batchId = od.batchId
                         JOIN LatestOrder lo ON od.orderId = lo.orderId
                         ORDER BY bp.expiryDate DESC  -- Select the batch with the latest expiry date
                     )
                     UPDATE bp
                     SET bp.quantity = bp.quantity - od.quantity
                     FROM BatchesProduct bp
                     JOIN OrderDetails od ON bp.batchId = od.batchId
                     JOIN LatestOrder lo ON od.orderId = lo.orderId
                     JOIN LatestBatch lb ON bp.batchId = lb.batchId;""";

        try {
            // connect
            con = new DBContext().getConnection();
            // sql
            ps = con.prepareStatement(sql);

            ps.setInt(1, userId);

            ps.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public void deleteOrderDetail(int orderId) {
        String sql = "DELETE od FROM OrderDetails od join BatchesProduct bp on bp.batchId = od.batchId join Orders o on o.orderId = od.orderId  where bp.quantity= 0 and o.orderId = ?";
        try {
            // connect
            con = new DBContext().getConnection();
            // sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);

            ps.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void deleteOrderDetailByStatus(int orderId) {
        String sql = "DELETE od FROM OrderDetails od join BatchesProduct bp on bp.batchId = od.batchId join Orders o on o.orderId = od.orderId join Products p on p.productId = bp.batchId  where (bp.quantity<= 0 and o.orderId = ?) or p.status ='Out of Stock'";
        try {
            // connect
            con = new DBContext().getConnection();
            // sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);

            ps.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void updateBatchforProductDetail() {
        String sql = """
                    UPDATE p
                                           SET p.status = 'In Stock'
                                           FROM Products p
                                           WHERE p.productId IN (
                                               SELECT bp.productId
                                               FROM BatchesProduct bp
                                               WHERE bp.quantity > 0
                                               GROUP BY bp.productId
                                               HAVING MAX(bp.expiryDate) > GETDATE()  -- Ensure the longest expiry date is greater than the current date
                                           );
                                           
                                           -- Optionally, if you want to set 'Out of Stock' for products with no valid batch
                                           UPDATE Products
                                           SET status = 'Out of Stock'
                                           WHERE productId NOT IN (
                                               SELECT bp.productId
                                               FROM BatchesProduct bp
                                               WHERE bp.quantity > 0
                                               GROUP BY bp.productId
                                               HAVING MAX(bp.expiryDate) > GETDATE()
                                           );""";
        try {
            // connect
            con = new DBContext().getConnection();
            // sql
            ps = con.prepareStatement(sql);

            ps.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
        }

    }

}
