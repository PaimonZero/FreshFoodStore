/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dto.ProductDTO;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.BatchProduct;
import model.Category;
import model.Products;
import model.Promos;
import model.Supplier;

/**
 *
 * @author nguye
 */
public class ProductDAO extends DBContext {

    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM Products";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM product WHERE productId = ?";

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

    //-----------------------Huy--------------------------------------
    // Lấy tất cả sản phẩm (đã chỉnh kiểu dữ liệu)
    public List<Products> getAllProducts() {
        List<Products> productList = new ArrayList<>();
        String sql = "SELECT p.productId, p.name, p.unitMeasure, p.status, p.unitPrice, b.quantity, pr.discount "
                + "FROM Products p "
                + "JOIN BatchesProduct b ON p.productId = b.productId "
                + "JOIN Promos pr ON p.productId = pr.productId";
        try {
            connection = getConnection();
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt("productId");
                String name = resultSet.getString("name");
                String unitMeasure = resultSet.getString("unitMeasure");
                String status = resultSet.getString("status");
                BigDecimal unitPrice = resultSet.getBigDecimal("unitPrice");
                int quantity = resultSet.getInt("quantity");
                double discountValue = resultSet.getDouble("discount");

                // Chuyển đổi discount từ int sang chuỗi
                String discountString = (discountValue != 0) ? "In progress" : "None";

                // Tạo đối tượng Product, truyền discountValue vào nhưng dùng discountString để hiển thị
                Products product = new Products(productId, name, unitMeasure, status, unitPrice, quantity, discountValue);

                // Hiển thị thông tin discount dưới dạng chuỗi
                System.out.println("Product Discount Status: " + discountString);

                productList.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error getAllProducts: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return productList;
    }

//----------------------------------------------------------------------------------------------------------------------------------------------//
    // Hàm thêm sản phẩm vào cơ sở dữ liệu (chỉnh double cho unitPrice)
    public void addProduct(String name, String unitMeasure, int supplierId, int categoryId, String description,
            BigDecimal unitPrice, String status) {
        String sqlProduct = "INSERT INTO Products (name, unitMeasure, supplierId, categoryId, description, unitPrice, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlBatch = "INSERT INTO BatchesProduct (productId, quantity) VALUES (?, ?)";
        String sqlPromo = "INSERT INTO Promos (productId, quantitySale, discount, startDate, endDate) VALUES (?, ?, ?, ?, ?)";

        try {
            // Thêm sản phẩm mới vào bảng Products
            connection = getConnection();
            preStatement = connection.prepareStatement(sqlProduct, PreparedStatement.RETURN_GENERATED_KEYS);
            preStatement.setString(1, name);
            preStatement.setString(2, unitMeasure);
            preStatement.setInt(3, supplierId);
            preStatement.setInt(4, categoryId);
            preStatement.setString(5, description);
            preStatement.setObject(6, unitPrice);
            preStatement.setString(7, status);
            preStatement.executeUpdate();

            // Lấy ID sản phẩm vừa thêm
            ResultSet generatedKeys = preStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int productId = generatedKeys.getInt(1);

                // Thêm bản ghi vào bảng BatchesProduct với quantity mặc định là 1
                preStatement = connection.prepareStatement(sqlBatch);
                preStatement.setInt(1, productId);
                preStatement.setInt(2, 1); // quantity mặc định là 1
                preStatement.executeUpdate();

                // Thêm bản ghi vào bảng Promos
                preStatement = connection.prepareStatement(sqlPromo);
                preStatement.setInt(1, productId);
                preStatement.setInt(2, 0); // quantitySale mặc định là 0
                preStatement.setInt(3, 0); // discount mặc định là 0
                preStatement.setDate(4, java.sql.Date.valueOf(LocalDate.now())); // startDate là ngày hiện tại
                preStatement.setDate(5, java.sql.Date.valueOf("9999-12-31")); // endDate mặc định
                preStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error adding product: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }
//----------------------------------------------------------------------------------------------------------------------------------------------//
    
    // Hàm xóa sản phẩm khỏi cơ sở dữ liệu
    public boolean deleteProduct(int productId) {
        String deleteBatchProductsQuery = "DELETE FROM BatchesProduct WHERE productId = ?";
        String deletePromosQuery = "DELETE FROM Promos WHERE productId = ?";
        String deleteProductQuery = "DELETE FROM Products WHERE productId = ?";

        try {
            // Bắt đầu transaction
            connection = getConnection();
            connection.setAutoCommit(false);

            // Xóa các lô hàng liên quan đến sản phẩm
            try (PreparedStatement batchStmt = connection.prepareStatement(deleteBatchProductsQuery)) {
                batchStmt.setInt(1, productId);
                batchStmt.executeUpdate();
            }

            // Xóa các chương trình khuyến mãi liên quan đến sản phẩm
            try (PreparedStatement promoStmt = connection.prepareStatement(deletePromosQuery)) {
                promoStmt.setInt(1, productId);
                promoStmt.executeUpdate();
            }

            // Xóa sản phẩm
            try (PreparedStatement productStmt = connection.prepareStatement(deleteProductQuery)) {
                productStmt.setInt(1, productId);
                productStmt.executeUpdate();
            }

            // Commit transaction
            connection.commit();
            return true; // Xóa thành công
        } catch (SQLException e) {
            // Nếu có lỗi, rollback transaction
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            System.err.println("Error deleting product: " + e.getMessage());
            return false; // Xóa không thành công
        } finally {
            try {
                connection.setAutoCommit(true); // Đặt lại chế độ auto commit
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        }
    }
//----------------------------------------------------------------------------------------------------------------------------------------------//

    public void updateProduct(int productId, String name, String unitMeasure, int supplierId,
            int categoryId, String description, BigDecimal unitPrice, String status,
            int quantity, Date expiryDate) {

        String sqlUpdateProduct = "UPDATE Products SET name = ?, unitMeasure = ?, supplierId = ?, categoryId = ?, description = ?, unitPrice = ?, status = ? WHERE productId = ?";
        String sqlUpdateBatchProduct = "UPDATE BatchesProduct SET quantity = ?, expiryDate = ? WHERE productId = ?";

        try {
            // Bắt đầu transaction
            connection = getConnection();
            connection.setAutoCommit(false);

            // Cập nhật bảng Products
            try (PreparedStatement psProduct = connection.prepareStatement(sqlUpdateProduct)) {
                psProduct.setString(1, name);
                psProduct.setString(2, unitMeasure);
                psProduct.setInt(3, supplierId);
                psProduct.setInt(4, categoryId);
                psProduct.setString(5, description);
                psProduct.setBigDecimal(6, unitPrice);
                psProduct.setString(7, status);
                psProduct.setInt(8, productId);

                psProduct.executeUpdate();
            }

            // Cập nhật bảng BatchesProduct
            try (PreparedStatement psBatch = connection.prepareStatement(sqlUpdateBatchProduct)) {
                psBatch.setInt(1, quantity);
                psBatch.setDate(2, new java.sql.Date(expiryDate.getTime()));
                psBatch.setInt(3, productId);
                psBatch.executeUpdate();
            }

            // Commit transaction
            connection.commit();

        } catch (SQLException e) {
            // Rollback nếu có lỗi
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            System.err.println("Error updating product: " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true); // Đặt lại auto commit
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        }
    }

//----------------------------------------------------------------------------------------------------------------------------------------------//
    // Lấy tất cả nhà cung cấp
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT supplierId, name FROM Suppliers"; // Thay đổi bảng theo tên đúng của bạn
        try {
            connection = getConnection();
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();
            while (resultSet.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplierId(resultSet.getInt("supplierId"));
                supplier.setName(resultSet.getString("name"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            System.err.println("Error getAllSuppliers: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return suppliers;
    }
//----------------------------------------------------------------------------------------------------------------------------------------------//
    // Lấy tất cả danh mục sản phẩm
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT categoryId, name FROM Category"; // Thay đổi bảng theo tên đúng của bạn
        try {
            connection = getConnection();
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("categoryId"));
                category.setName(resultSet.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            System.err.println("Error getAllCategories: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return categories;
    }
    
    // Method to get product info by productId
    public ProductDTO getProductInfoById(int productId) {
        ProductDTO productInfo = null;
        
        String sql = "SELECT p.productId, p.name, p.unitMeasure, p.unitPrice, p.description, "
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
                    productInfo = new ProductDTO();
                    productInfo.setProductId(productId);
                    productInfo.setProductName(resultSet.getString("name"));
                    productInfo.setUnitMeasure(resultSet.getString("unitMeasure"));
                    productInfo.setUnitPrice(resultSet.getBigDecimal("unitPrice"));
                    productInfo.setDescription(resultSet.getString("description"));
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
                promo.setDiscount(resultSet.getDouble("discount"));
                promosList.add(promo);
            }

            // Gán các danh sách BatchProduct và Promos vào ProductInfo
            if (productInfo != null) {
                productInfo.setBatchProducts(batchProducts);
                productInfo.setPromosList(promosList);
            }

        } catch (SQLException e) {
            System.err.println("Error getProductInfoById: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return productInfo;
    }

    //-----------------------Huy-(end)--------------------------------
    public static void main(String[] args) {
//        // Tạo đối tượng ProductDao
//        ProductDAO productDao = new ProductDAO();
//
//        // Các giá trị cập nhật
//        int productId = 11; // ID của sản phẩm mà bạn muốn cập nhật
//        String productName = "Updated Product Name";
//        String unitMeasure = "New Unit";
//        int supplierId = 6; // ID nhà cung cấp
//        int categoryId = 5; // ID danh mục
//        String description = "Updated product description";
//        BigDecimal unitPrice = BigDecimal.valueOf(2500);
//        String status = "Available";
//        int quantity = 30;
//        java.sql.Date expiryDate = java.sql.Date.valueOf("2025-01-01");
//
//        // Gọi hàm updateProduct để cập nhật thông tin
//        productDao.updateProduct(productId, productName, unitMeasure, supplierId,
//                categoryId, description, unitPrice, status, quantity, expiryDate);
//
//        System.out.println("Product updated successfully.");
        
        //-------------------
                ProductDAO productInfoDao = new ProductDAO();
        int productId = 1; // Thay bằng ID thực
        ProductDTO productInfo = productInfoDao.getProductInfoById(productId);

        if (productInfo != null) {
            // In ra thông tin sản phẩm
            System.out.println("Product ID: " + productInfo.getProductId());
            System.out.println("Product Name: " + productInfo.getProductName());
            System.out.println("Unit Measure: " + productInfo.getUnitMeasure());
            System.out.println("Unit Price: " + productInfo.getUnitPrice());
            System.out.println("Description "+productInfo.getDescription());
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
