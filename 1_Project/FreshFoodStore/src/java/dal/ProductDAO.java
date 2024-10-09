/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dto.ProductDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Products;

/**
 *
 * @author nguye
 */
public class ProductDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // tìm sản phẩm theo id
    public ProductDTO findProductById(int id) {

        String sql = "select p.*,c.name,pr.discount FROM products p join Category c on p.categoryId = c.categoryId  left join Promos pr on p.productId = pr.productId   WHERE p.productId= ?";

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
                        rs.getString(12),
                        rs.getDouble(13)
                );

            }
        } catch (Exception e) {
        }
        return null;
    }

    // 4 sản phẩm nổi bật
    public List<ProductDTO> top4ProductNoiBat() {
        List<ProductDTO> p = new ArrayList<>();
        String sql = """
                     
                      SELECT TOP 4
                                                  p.productId,
                                                  p.name,
                                                  p.unitPrice,
                                                  p.image,
                         						  pr.discount,
                                                  SUM(od.quantity) AS totalQuantity
                                              FROM 
                                                  OrderDetails od
                                              JOIN 
                                                  Orders o ON od.orderId = o.orderId
                                              JOIN 
                                                  Products p ON od.batchId = (
                                                      SELECT TOP 1 bp.batchId
                                                      FROM BatchesProduct bp
                                                      WHERE bp.productId = p.productId
                                                      ORDER BY bp.createdAt DESC
                                                  )
                         						 left join Promos pr ON p.productId = pr.productId 
                                              WHERE 
                                                  o.orderCreatedAt >= DATEADD(DAY, -7, GETDATE()) 
                                              GROUP BY 
                                                  p.productId, 
                                                  p.name,
                                                  p.unitPrice,  
                                                  p.image,      
                             pr.discount       
                                              ORDER BY 
                                                  totalQuantity DESC; """;

        try {
            // connect
            con = new DBContext().getConnection();
            // sql
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                p.add(new ProductDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getBigDecimal(3),
                        rs.getString(4),
                        rs.getDouble(5)
                ));

            }
        } catch (Exception e) {
            System.out.println("??top4ProductNoiBat" + e.getMessage());
        }
        return p;
    }
// tìm sản phẩm theo loại

    public List<Products> findProductByCategory(int id) {
        List<Products> p = new ArrayList<>();
        String sql = "SELECT top 4 \n"
                + "    p.*\n"
                + "FROM \n"
                + "    Products p\n"
                + "JOIN \n"
                + "    Category c ON p.categoryId = c.categoryId\n"
                + "WHERE \n"
                + "    p.categoryId = (\n"
                + "        SELECT \n"
                + "            categoryId\n"
                + "        FROM \n"
                + "            Products\n"
                + "        WHERE \n"
                + "            productId =?\n"
                + "    );";

        try {
            // connect
            con = new DBContext().getConnection();
            // sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                p.add(new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBigDecimal(8),
                        rs.getString(9),
                        rs.getDate(10),
                        rs.getDate(11))
                );

            }
        } catch (Exception e) {
        }
        return p;
    }

}
