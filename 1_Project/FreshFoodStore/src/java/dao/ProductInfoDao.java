package dao;

import model.ProductInfo;
import model.BatchProduct;
import model.Promos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductInfoDao extends DBContext {
    
    // Method to get product info by productId
    public ProductInfo getProductInfoById(int productId) {
        ProductInfo productInfo = null;
        
        String sql = "SELECT p.productId, p.name, p.unitMeasure, p.unitPrice, "
                   + "c.name AS categoryName, s.name AS supplierName, s.phone AS supplierPhone, "
                   + "bp.batchId, bp.quantity, bp.expiryDate, bp.createdAt, "
                   + "pr.promotionId, pr.startDate, pr.endDate, pr.discount "
                   + "FROM Products p "
                   + "JOIN Category c ON p.categoryId = c.categoryId "
                   + "JOIN Suppliers s ON p.supplierId = s.supplierId "
                   + "LEFT JOIN BatchesProduct bp ON p.productId = bp.productId "
                   + "LEFT JOIN Promos pr ON p.productId = pr.productId "
                   + "WHERE p.productId = ?";
        
        try {
            connection = getConnection();
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, productId);
            resultSet = preStatement.executeQuery();
            
            List<BatchProduct> batchProducts = new ArrayList<>();
            List<Promos> promosList = new ArrayList<>();
            
            while (resultSet.next()) {
                if (productInfo == null) {
                    // Chỉ tạo đối tượng ProductInfo một lần
                    productInfo = new ProductInfo();
                    productInfo.setProductId(productId);
                    productInfo.setProductName(resultSet.getString("name"));
                    productInfo.setUnitMeasure(resultSet.getString("unitMeasure"));
                    productInfo.setUnitPrice(resultSet.getInt("unitPrice"));
                    productInfo.setCategoryName(resultSet.getString("categoryName"));
                    productInfo.setSupplierName(resultSet.getString("supplierName"));
                    productInfo.setSupplierPhone(resultSet.getString("supplierPhone"));
                }

                // Thêm thông tin BatchProduct nếu có
                BatchProduct batchProduct = new BatchProduct();
                batchProduct.setBatchId(resultSet.getInt("batchId"));
                batchProduct.setQuantity(resultSet.getInt("quantity"));
                batchProduct.setExpiryDate(resultSet.getDate("expiryDate"));
                batchProduct.setCreatedAt(resultSet.getDate("createdAt"));
                batchProducts.add(batchProduct);
                
                // Thêm thông tin Promos nếu có
                Promos promo = new Promos();
                promo.setPromotionId(resultSet.getInt("promotionId"));
                promo.setStartDate(resultSet.getDate("startDate"));
                promo.setEndDate(resultSet.getDate("endDate"));
                promo.setDiscount(resultSet.getInt("discount"));
                promosList.add(promo);
            }

            // Gán các danh sách BatchProduct và Promos vào ProductInfo
            if (productInfo != null) {
                productInfo.setBatchProducts(batchProducts);
                productInfo.setPromosList(promosList);
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return productInfo;
    }

    public static void main(String[] args) {
        ProductInfoDao productInfoDao = new ProductInfoDao();
        int productId = 1; // Thay bằng ID thực
        ProductInfo productInfo = productInfoDao.getProductInfoById(productId);

        if (productInfo != null) {
            // In ra thông tin sản phẩm
            System.out.println("Product ID: " + productInfo.getProductId());
            System.out.println("Product Name: " + productInfo.getProductName());
            System.out.println("Unit Measure: " + productInfo.getUnitMeasure());
            System.out.println("Unit Price: " + productInfo.getUnitPrice());

            // In ra danh sách BatchProduct
            for (BatchProduct batch : productInfo.getBatchProducts()) {
                System.out.println("Batch ID: " + batch.getBatchId());
                System.out.println("Quantity: " + batch.getQuantity());
                System.out.println("Expiry Date: " + batch.getExpiryDate());
                System.out.println("Created At: " + batch.getCreatedAt());
            }

            // In ra danh sách Promos
            for (Promos promo : productInfo.getPromosList()) {
                System.out.println("Promotion ID: " + promo.getPromotionId());
                System.out.println("Start Date: " + promo.getStartDate());
                System.out.println("End Date: " + promo.getEndDate());
                System.out.println("Discount: " + promo.getDiscount());
            }
        } else {
            System.out.println("No product found with ID: " + productId);
        }
    }
}
