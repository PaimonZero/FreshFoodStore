package dao;

import java.util.ArrayList;
import java.util.List;
import model.ProductInfo;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PC
 */
public class ProductInfoDao extends DBContext {
    
    // Method to get all product info
public ProductInfo getProductInfoById(int productId) {
    ProductInfo productInfo = null;
    
    String sql = "SELECT p.productId, p.name, p.unitMeasure, p.unitPrice, "
               + "c.name AS categoryName, s.name AS supplierName, s.phone AS supplierPhone "
               + "FROM Products p "
               + "JOIN Category c ON p.categoryId = c.categoryId "
               + "JOIN Suppliers s ON p.supplierId = s.supplierId "
               + "WHERE p.productId = ?"; // Thêm điều kiện WHERE để lấy sản phẩm theo productId

    try {
        connection = getConnection();
        preStatement = connection.prepareStatement(sql);
        preStatement.setInt(1, productId); // Gán giá trị productId vào câu truy vấn
        resultSet = preStatement.executeQuery();
        
        if (resultSet.next()) {
            // Lấy thông tin từ resultSet
            String productName = resultSet.getString("name");
            String unitMeasure = resultSet.getString("unitMeasure");
            int unitPrice = resultSet.getInt("unitPrice");
            String categoryName = resultSet.getString("categoryName");
            String supplierName = resultSet.getString("supplierName");
            String supplierPhone = resultSet.getString("supplierPhone");

            // Tạo đối tượng ProductInfo
            productInfo = new ProductInfo();
            productInfo.setProductId(productId);
            productInfo.setProductName(productName);
            productInfo.setUnitMeasure(unitMeasure);
            productInfo.setUnitPrice(unitPrice);
            productInfo.setCategoryName(categoryName);
            productInfo.setSupplierName(supplierName);
            productInfo.setSupplierPhone(supplierPhone);
        }
    } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage());
    } finally {
        closeConnection();
    }
    return productInfo; // Trả về đối tượng ProductInfo
}


        public static void main(String[] args) {
        ProductInfoDao productInfoDao = new ProductInfoDao();
        int testProductId = 1; // Thay đổi giá trị này để kiểm tra các sản phẩm khác

        // Lấy thông tin sản phẩm theo productId
        ProductInfo productInfo = productInfoDao.getProductInfoById(testProductId);

        // Kiểm tra và in thông tin sản phẩm
        if (productInfo != null) {
            System.out.println("Thông tin sản phẩm:");
            System.out.println("Mã sản phẩm: " + productInfo.getProductId());
            System.out.println("Tên sản phẩm: " + productInfo.getProductName());
            System.out.println("Đơn vị tính: " + productInfo.getUnitMeasure());
            System.out.println("Giá bán ra: " + productInfo.getUnitPrice() + " vnd");
            System.out.println("Danh mục: " + productInfo.getCategoryName());
            System.out.println("Nhà cung cấp: " + productInfo.getSupplierName());
            System.out.println("Số điện thoại nhà cung cấp: " + productInfo.getSupplierPhone());
        } else {
            System.out.println("Không tìm thấy sản phẩm với mã: " + testProductId);
        }
    }
}
