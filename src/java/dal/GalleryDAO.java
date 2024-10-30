/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Gallery;

/**
 *
 * @author PC
 */
public class GalleryDAO extends DBContext {

    public List<Gallery> getAllGalleryByPId(int productId) {
        List<Gallery> galleryList = new ArrayList<>();
        String sql = "SELECT [galleryId]\n"
                + "      ,[productId]\n"
                + "      ,[src]\n"
                + "  FROM [dbo].[Gallery]\n"
                + "  where productId = ?"; // Thay đổi bảng theo tên đúng của bạn
        try {
            connection = getConnection();
            preStatement = connection.prepareStatement(sql);
            preStatement.setObject(1, productId);
            resultSet = preStatement.executeQuery();
            while (resultSet.next()) {
                Gallery gallery = new Gallery();
                gallery.setGalleryId(resultSet.getInt("galleryId"));
                gallery.setProductId(resultSet.getInt("productId"));
                gallery.setSrc(resultSet.getString("src"));
                
                galleryList.add(gallery);
            }
        } catch (SQLException e) {
            System.err.println("Error getAllGalleryByPId: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return galleryList;
    }
    
    public static void main(String[] args) {
        GalleryDAO galleryDAO = new GalleryDAO();
        int productId = 2; // Change to the product ID you want to test with

        List<Gallery> galleries = galleryDAO.getAllGalleryByPId(productId);
        // Print details of each gallery item
        for (Gallery gallery : galleries) {
            System.out.println("Gallery ID: " + gallery.getGalleryId());
            System.out.println("Product ID: " + gallery.getProductId());
            System.out.println("Source: " + gallery.getSrc());
            System.out.println("------------------------");
        }
    }
}
