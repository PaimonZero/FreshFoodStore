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
public class CategoryDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
// đếm số lượng 
    public int countProduct(String type) {
        String sql = "select COUNT(*) from Products p join Category c on p.categoryId = c.categoryId where c.name =?";
        try {
            // connect
            con = new DBContext().getConnection();
            // write sql
            ps = con.prepareStatement(sql);
            ps.setString(1, type);

            // excute
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);

            }

        } catch (Exception e) {
        }
        return 0;

    }
// tìm sản phẩm theo tên và kèm paging

    public List<ProductDTO> findByName(String type, int offset, int row) {
        List<ProductDTO> p = new ArrayList<>();
        String sql = "select p.*,pr.discount from Products p join Category c on p.categoryId = c.categoryId left join Promos pr on pr.productId = p.productId where c.name =?"
                + "ORDER BY p.productId DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            // connect
            con = new DBContext().getConnection();
            // sql
            ps = con.prepareStatement(sql);
            ps.setString(1, type);
            ps.setInt(2, offset);
            ps.setInt(3, row);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.add(new ProductDTO(rs.getInt(1),
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
                        rs.getDouble(12))
                );

            }
        } catch (Exception e) {
        }
        return p;
    }
    
// sắp xếp sản phẩm theo dữ liệu truyền vào như asc desc

    public List<ProductDTO> sortProductByUporDown(String type, String attribute, String fluctuation, int offset, int row) {
        List<ProductDTO> p = new ArrayList<>();
        String sql = "select p.*,pr.discount from Products p join Category c on p.categoryId = c.categoryId left join Promos pr on pr.productId = p.productId "
                + " where c.name = ? order by " + attribute + " " + fluctuation + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            // connect
            con = new DBContext().getConnection();
            // sql
            ps = con.prepareStatement(sql);
            ps.setString(1, type);
            ps.setInt(2, offset);
            ps.setInt(3, row);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.add(new ProductDTO(rs.getInt(1),
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
                        rs.getDouble(12))
                );

            }
        } catch (Exception e) {
        }
        return p;
    }
// danh sách sản phẩm sale

    public List<ProductDTO> listProductSale() {
        List<ProductDTO> p = new ArrayList<>();
        String sql = "	select top 3 p.*, pr.* from Products p join Promos pr on p.productId = pr.productId order by pr.discount desc";

        try {
            // connect
            con = new DBContext().getConnection();
            // sql
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                p.add(new ProductDTO(rs.getInt(1),
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
                        rs.getInt(14),
                        rs.getDouble(15),
                        rs.getDate(16),
                        rs.getDate(17)
                ));

            }
        } catch (Exception e) {
        }
        return p;
    }
}
