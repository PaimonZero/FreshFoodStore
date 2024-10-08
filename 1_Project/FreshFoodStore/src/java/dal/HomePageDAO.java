/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Products;
import model.Users;

/**
 *
 * @author nguye
 */
public class HomePageDAO {
     Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
// Sản phẩm khuyến mãi
    public List<Products> getProductKhuyenMai() {
        String sql = "SELECT top 3 * FROM Products p order by unitPrice asc;";
        List<Products> p = new ArrayList<>();
        try {
            // connect
            con = new DBContext().getConnection();
            // write sql
            ps = con.prepareStatement(sql);
            // excute
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
    // Sản phẩm bán chạy
    public List<Products> getProductBanChay() {
        String sql = "SELECT top 3 * FROM Products p join BatchesProduct b on p.productId = b.productId order by b.quantity desc;";
        List<Products> p = new ArrayList<>();
        try {
            // connect
            con = new DBContext().getConnection();
            // write sql
            ps = con.prepareStatement(sql);
            // excute
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
    // Sản phẩm đánh giá tốt 
    public List<Products> getProductDanhGiaTot() {
        String sql = "SELECT top 3 * FROM Products p order by unitPrice desc;";
        List<Products> p = new ArrayList<>();
        try {
            // connect
            con = new DBContext().getConnection();
            // write sql
            ps = con.prepareStatement(sql);
            // excute
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
