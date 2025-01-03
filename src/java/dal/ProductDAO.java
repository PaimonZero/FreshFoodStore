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
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
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

        //String sql = "select p.*,c.name,pr.discount, bp.quantity FROM BatchesProduct bp join Products p on bp.productId = p.productId join Category c on p.categoryId = c.categoryId  left join Promos pr on p.productId = pr.productId   WHERE p.productId=? order by bp.expiryDate desc";
        //Nam Chỉnh
//        String sql = "SELECT p.*, c.name, pr.discount, bp.quantity \n"
//                + "FROM BatchesProduct bp \n"
//                + "JOIN Products p ON bp.productId = p.productId \n"
//                + "JOIN Category c ON p.categoryId = c.categoryId  \n"
//                + "LEFT JOIN Promos pr ON p.productId = pr.productId   \n"
//                + "WHERE p.productId = ? \n"
//                + "AND bp.quantity > 0 \n"
//                + "ORDER BY bp.expiryDate DESC;";
//  Xử lý trường hợp ko tìm thấy kết quả ở điều kiện bp.quantity > 0
        String sql = "SELECT p.*, c.name, \n"
                + "       CASE \n"
                + "           WHEN bp.quantity = 0 THEN 0 \n"
                + "           ELSE pr.discount \n"
                + "       END AS discount,  -- Sử dụng CASE để xử lý điều kiện discount\n"
                + "       bp.quantity, \n"
                + "       bp.expiryDate\n"
                + "FROM BatchesProduct bp \n"
                + "JOIN Products p ON bp.productId = p.productId \n"
                + "JOIN Category c ON p.categoryId = c.categoryId  \n"
                + "LEFT JOIN Promos pr ON p.productId = pr.productId   \n"
                + "WHERE p.productId = ?\n"
                + "AND bp.quantity > 0 \n"
                + "\n"
                + "UNION ALL\n"
                + "\n"
                + "SELECT p.*, c.name, \n"
                + "       0 AS discount,  -- Đặt discount là 0 nếu không có lô hàng nào\n"
                + "       bp.quantity, \n"
                + "       NULL AS expiryDate\n" //Thêm expiryDate, sử dụng NULL nếu không có giá trị
                + "FROM BatchesProduct bp \n"
                + "JOIN Products p ON bp.productId = p.productId \n"
                + "JOIN Category c ON p.categoryId = c.categoryId  \n"
                + "LEFT JOIN Promos pr ON p.productId = pr.productId   \n"
                + "WHERE p.productId = ?\n"
                + "AND NOT EXISTS (\n"
                + "    SELECT 1 \n"
                + "    FROM BatchesProduct \n"
                + "    WHERE productId = ?\n"
                + "    AND quantity > 0\n"
                + ")\n"
                + "\n"
                + "ORDER BY expiryDate DESC;  -- Sắp xếp kết quả chung";

        try {
            // connect
            con = new DBContext().getConnection();
            // sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            //Nam thêm
            ps.setInt(2, id);
            ps.setInt(3, id);

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
                        rs.getDouble(13),
                        rs.getInt(14)
                );

            }
        } catch (SQLException e) {
            System.out.println("???(ProductDAO)findProductById: " + e);
        }
        return null;
    }

    //tìm ảnh trong gallery
    public ArrayList<ProductDTO> findProductGalleryById(int id) {
        ArrayList<ProductDTO> p = new ArrayList<>();
        String sql = "select g.productId, g.src FROM products p join Gallery g on g.productId = p.productId WHERE p.productId= ?";

        try {
            // connect
            con = new DBContext().getConnection();
            // sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.add(new ProductDTO(rs.getInt(1),
                        rs.getString(2)
                ));

            }
        } catch (Exception e) {
        }
        return p;
    }

    // 4 sản phẩm nổi bật có đụng
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
                                    ORDER BY bp.expiryDate ASC
                                )
                                                     left join Promos pr ON p.productId = pr.productId 
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

    //Mercode here----------------------------------------------------------------------------------------------------------------------------------------------//
    public List<ProductDTO> findByName(String txtSearch, int offset, int row) {
        List<ProductDTO> proDTO = new ArrayList<>();
        String sql = "select p.*,pr.discount from Products p join Category c on p.categoryId = c.categoryId left join Promos pr on pr.productId = p.productId where p.name like ? order by p.name OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            // connect
            con = new DBContext().getConnection();
            // sql
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setInt(2, offset);
            ps.setInt(3, row);

            rs = ps.executeQuery();
            while (rs.next()) {
                proDTO.add(new ProductDTO(rs.getInt(1),
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
        return proDTO;
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------//

    public int countProductSearch(String textSearch) {
        String sql = "select COUNT(*) from Products p join Category c on p.categoryId = c.categoryId left join Promos pr on pr.productId = p.productId  where p.name like ?";
        try {
            // connect
            con = new DBContext().getConnection();
            // write sql
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + textSearch + "%");

            // excute
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);

            }

        } catch (Exception e) {
        }
        return 0;

    }

    //-----------------------Huy--------------------------------------//
    public int calculateTotalProducts() {
        int totalProducts = 0;
        String sql = "SELECT COUNT(*) AS totalProducts FROM Products";

        try {
            connection = getConnection(); // Use your existing method for database connection
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Retrieve the count of products
            if (resultSet.next()) {
                totalProducts = resultSet.getInt("totalProducts");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total products: " + e.getMessage());
        } finally {
            closeConnection(); // Close the connection
        }

        return totalProducts;
    }

    public int calculateTotalPromos() {
        int totalPromos = 0;
        String sql = "SELECT COUNT(DISTINCT p.productId) AS promotionProductCount\n"
                + "FROM Products p\n"
                + "JOIN Promos promo ON p.productId = promo.productId\n"
                + "JOIN BatchesProduct bp ON p.productId = bp.productId\n"
                + "WHERE bp.quantity > 0\n"
                + "  AND promo.startDate <= GETDATE()\n"
                + "  AND promo.endDate >= GETDATE();";

        try {
            connection = getConnection(); // Use your existing method for database connection
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Retrieve the count of products
            if (resultSet.next()) {
                totalPromos = resultSet.getInt("promotionProductCount");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total products: " + e.getMessage());
        } finally {
            closeConnection(); // Close the connection
        }

        return totalPromos;
    }

    public int calculateLowStock() {
        int totalLowStock = 0;
        String sql = "SELECT COUNT(DISTINCT bp.productId) AS lowStockProductCount\n"
                + "FROM BatchesProduct bp\n"
                + "WHERE bp.quantity > 0\n"
                + "  AND bp.quantity < 21\n"
                + "  AND bp.expiryDate > GETDATE();";

        try {
            connection = getConnection(); // Use your existing method for database connection
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Retrieve the count of products
            if (resultSet.next()) {
                totalLowStock = resultSet.getInt("lowStockProductCount");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total products: " + e.getMessage());
        } finally {
            closeConnection(); // Close the connection
        }

        return totalLowStock;
    }

    public int calculateOutOfStock() {
        int totalOutStock = 0;
        String sql = "  SELECT COUNT(DISTINCT p.productId) AS totalOutOfStockProducts\n"
                + "FROM Products p\n"
                + "LEFT JOIN BatchesProduct bp ON p.productId = bp.productId\n"
                + "WHERE p.status = 'Out of stock'\n"
                + "   OR (bp.quantity = 0 AND bp.expiryDate > GETDATE());";

        try {
            connection = getConnection(); // Use your existing method for database connection
            preStatement = connection.prepareStatement(sql);
            resultSet = preStatement.executeQuery();

            // Retrieve the count of products
            if (resultSet.next()) {
                totalOutStock = resultSet.getInt("totalOutOfStockProducts");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total products: " + e.getMessage());
        } finally {
            closeConnection(); // Close the connection
        }

        return totalOutStock;
    }

//    public List<Products> getAllProducts() {
//        List<Products> productList = new ArrayList<>();
//        String sql = "SELECT p.productId, \n"
//                + "       p.name, \n"
//                + "       p.unitMeasure, \n"
//                + "       p.status, \n"
//                + "       p.unitPrice, \n"
//                + "       COALESCE(SUM(b.quantity), 0) AS totalQuantity, \n"
//                + "       COUNT(b.batchId) AS activeBatches, \n"
//                + "       MIN(pr.startDate) AS minStartDate, \n"
//                + "       MAX(pr.endDate) AS maxEndDate, \n"
//                + "       COUNT(pr.promotionId) AS promoCount\n"
//                + "FROM Products p\n"
//                + "LEFT JOIN BatchesProduct b ON p.productId = b.productId AND b.expiryDate >= GETDATE()  -- Chỉ lấy lô hàng còn hiệu lực\n"
//                + "LEFT JOIN Promos pr ON p.productId = pr.productId\n"
//                + "GROUP BY p.productId, \n"
//                + "         p.name, \n"
//                + "         p.unitMeasure, \n"
//                + "         p.status, \n"
//                + "         p.unitPrice\n"
//                + "ORDER BY p.productId;";  // Sắp xếp theo productId
//
//        try {
//            connection = getConnection();
//            preStatement = connection.prepareStatement(sql);
//            resultSet = preStatement.executeQuery();
//            while (resultSet.next()) {
//                int productId = resultSet.getInt("productId");
//                String name = resultSet.getString("name");
//                String unitMeasure = resultSet.getString("unitMeasure");
//                String status = resultSet.getString("status");
//                BigDecimal unitPrice = resultSet.getBigDecimal("unitPrice");
//                int quantity = resultSet.getInt("totalQuantity");
//                int promoCount = resultSet.getInt("promoCount");
//                Date minStartDate = resultSet.getDate("minStartDate");
//                Date maxEndDate = resultSet.getDate("maxEndDate");
//                int activeBatches = resultSet.getInt("activeBatches"); // Số lượng lô hàng còn hiệu lực
//
//                // Xác định trạng thái khuyến mãi
//                String promotionStatus = "None"; // Mặc định là None
//                Date currentDate = new Date();  // Lấy thời gian hiện tại
//
//                // Kiểm tra điều kiện trạng thái khuyến mãi
//                if (promoCount > 0) { // Nếu có khuyến mãi
//                    if (minStartDate != null && maxEndDate != null) {
//                        if (minStartDate.before(currentDate) && maxEndDate.after(currentDate) && activeBatches > 0) {
//                            promotionStatus = "In progress";  // Khuyến mãi đang diễn ra
//                        }
//                    }
//                }
//
//                // Nếu không có lô hàng nào còn hiệu lực, trạng thái khuyến mãi sẽ là None
//                if (activeBatches == 0) {
//                    promotionStatus = "None";
//                }
//
//                Products product = new Products(productId, name, unitMeasure, status, unitPrice, quantity);
//                product.setPromotionStatus(promotionStatus);
//                productList.add(product);
//            }
//        } catch (SQLException e) {
//            System.err.println("Error getAllProducts: " + e.getMessage());
//        } finally {
//            closeConnection();
//        }
//        return productList;
//    }
    
    public List<Products> getAllProducts() {
        List<Products> productList = new ArrayList<>();

        // SQL để cập nhật trạng thái "In Stock" hoặc "Out of Stock" dựa trên totalQuantity
        String updateStatusSql = "WITH ProductQuantities AS ( "
                + "    SELECT p.productId, "
                + "           COALESCE(SUM(b.quantity), 0) AS totalQuantity "
                + "    FROM Products p "
                + "    LEFT JOIN BatchesProduct b ON p.productId = b.productId AND b.expiryDate >= GETDATE() "
                + "    GROUP BY p.productId "
                + ") "
                + "UPDATE Products "
                + "SET status = CASE "
                + "                 WHEN pq.totalQuantity = 0 THEN 'Out of Stock' "
                + "                 ELSE 'In Stock' "
                + "             END "
                + "FROM Products p "
                + "JOIN ProductQuantities pq ON p.productId = pq.productId;";

        // SQL để lấy dữ liệu sản phẩm
        String selectSql = "SELECT p.productId, "
                + "       p.name, "
                + "       p.unitMeasure, "
                + "       p.status, "
                + "       p.unitPrice, "
                + "       COALESCE(SUM(b.quantity), 0) AS totalQuantity, "
                + "       COUNT(b.batchId) AS activeBatches, "
                + "       MIN(pr.startDate) AS minStartDate, "
                + "       MAX(pr.endDate) AS maxEndDate, "
                + "       COUNT(pr.promotionId) AS promoCount "
                + "FROM Products p "
                + "LEFT JOIN BatchesProduct b ON p.productId = b.productId AND b.expiryDate >= GETDATE() "
                + "LEFT JOIN Promos pr ON p.productId = pr.productId "
                + "GROUP BY p.productId, p.name, p.unitMeasure, p.status, p.unitPrice "
                + "ORDER BY p.productId;";

        try {
            // Kết nối tới cơ sở dữ liệu
            connection = getConnection();

            // Thực thi lệnh UPDATE để cập nhật trạng thái sản phẩm
            preStatement = connection.prepareStatement(updateStatusSql);
            preStatement.executeUpdate();

            // Thực thi lệnh SELECT để lấy dữ liệu sản phẩm sau khi đã cập nhật
            preStatement = connection.prepareStatement(selectSql);
            resultSet = preStatement.executeQuery();

            // Duyệt qua từng hàng dữ liệu trả về và tạo đối tượng sản phẩm
            while (resultSet.next()) {
                int productId = resultSet.getInt("productId");
                String name = resultSet.getString("name");
                String unitMeasure = resultSet.getString("unitMeasure");
                String status = resultSet.getString("status");
                BigDecimal unitPrice = resultSet.getBigDecimal("unitPrice");
                int quantity = resultSet.getInt("totalQuantity");
                int promoCount = resultSet.getInt("promoCount");
                Date minStartDate = resultSet.getDate("minStartDate");
                Date maxEndDate = resultSet.getDate("maxEndDate");
                int activeBatches = resultSet.getInt("activeBatches");

                // Xác định trạng thái khuyến mãi
                String promotionStatus = "None";
                Date currentDate = new Date();

                if (promoCount > 0) {
                    if (minStartDate != null && maxEndDate != null) {
                        if (minStartDate.before(currentDate) && maxEndDate.after(currentDate) && activeBatches > 0) {
                            promotionStatus = "In progress";
                        }
                    }
                }

                if (activeBatches == 0) {
                    promotionStatus = "None";
                }

                Products product = new Products(productId, name, unitMeasure, status, unitPrice, quantity);
                product.setPromotionStatus(promotionStatus);
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
        String sqlBatch = "INSERT INTO BatchesProduct (productId, quantity, expiryDate) VALUES (?, ?, DATEADD(DAY, 3, GETDATE()))";
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
                preStatement.setDate(4, java.sql.Date.valueOf(LocalDate.now().minusDays(5))); // startDate là ngày trước ngày hiện tại
                preStatement.setDate(5, java.sql.Date.valueOf(LocalDate.now().minusDays(2))); // endDate mặc định là một năm trước
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

    public void updateProduct(int productId, String name, String unitMeasure, int categoryId, String description, BigDecimal unitPrice, String status, String image) {

        String sqlUpdateProduct = "UPDATE Products SET name = ?, unitMeasure = ?, categoryId = ?, description = ?, unitPrice = ?, status = ?, image = ? WHERE productId = ?";

        try {
            // Bắt đầu transaction
            connection = getConnection();
            connection.setAutoCommit(false);

            // Cập nhật bảng Products
            try (PreparedStatement psProduct = connection.prepareStatement(sqlUpdateProduct)) {
                psProduct.setString(1, name);
                psProduct.setString(2, unitMeasure);
                psProduct.setInt(3, categoryId);
                psProduct.setString(4, description);
                psProduct.setBigDecimal(5, unitPrice);
                psProduct.setString(6, status);
                psProduct.setString(7, image);
                psProduct.setInt(8, productId);

                psProduct.executeUpdate();
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
//----------------------------------------------------------------------------------------------------------------------------------------------//
    // Method to get product info by productId

    public ProductDTO getProductInfoById(int productId) {
        ProductDTO productInfo = null;

        String sql = "SELECT p.productId, p.name, p.unitMeasure, p.unitPrice, p.description, p.image, \n"
                + "       c.name AS categoryName, s.name AS supplierName, s.phone AS supplierPhone, \n"
                + "       bp.batchId, bp.quantity, bp.expiryDate, \n"
                + "       r.dateInput, -- Added dateInput from Receipts\n"
                + "       pr.promotionId, pr.startDate, pr.endDate, pr.discount \n"
                + "FROM Products p \n"
                + "JOIN Category c ON p.categoryId = c.categoryId \n"
                + "JOIN Suppliers s ON p.supplierId = s.supplierId \n"
                + "LEFT JOIN BatchesProduct bp ON p.productId = bp.productId \n"
                + "LEFT JOIN ReceiptDetails rd ON bp.receiptDetailId = rd.receiptDetailId -- Join with ReceiptDetails\n"
                + "LEFT JOIN Receipts r ON rd.receiptId = r.receiptId -- Join with Receipts to get dateInput\n"
                + "LEFT JOIN Promos pr ON p.productId = pr.productId \n"
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
                    productInfo.setImage(resultSet.getString("image"));

                }

                // Thêm thông tin BatchProduct nếu có
                BatchProduct batchProduct = new BatchProduct();
                batchProduct.setBatchId(resultSet.getInt("batchId"));
                batchProduct.setQuantity(resultSet.getInt("quantity"));
                batchProduct.setExpiryDate(resultSet.getDate("expiryDate"));
                batchProduct.setCreatedAt(resultSet.getDate("dateInput"));
                batchProducts.add(batchProduct);

                // Kiểm tra nếu Promos chưa được thêm vào
                int promotionId = resultSet.getInt("promotionId");
                boolean promoExists = promosList.stream()
                        .anyMatch(p -> p.getPromotionId() == promotionId);
                if (!promoExists && promotionId > 0) {  // Chỉ thêm nếu chưa tồn tại và promotionId hợp lệ
                    Promos promo = new Promos();
                    promo.setPromotionId(promotionId);
                    promo.setStartDate(resultSet.getDate("startDate"));
                    promo.setEndDate(resultSet.getDate("endDate"));
                    promo.setDiscount(resultSet.getDouble("discount"));
                    promosList.add(promo);
                }
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
//----------------------------------------------------------------------------------------------------------------------------------------------//

    public void updateBatch(int batchId, int quantity) {
        //- Chuan bi cau lenh sql
        String sql = "UPDATE [dbo].[BatchesProduct]\n"
                + "   SET [quantity] = ?\n"
                + " WHERE batchId = ?;";
        boolean isUpdated = false;
        try {
            connection = getConnection();
            //- Tao doi tuong prepareStatement (thêm generated key vao tham so thu 2)
            preStatement = connection.prepareStatement(sql);            //- set parameter
            preStatement.setInt(1, quantity);
            preStatement.setInt(2, batchId);

            //- thuc thi cau lenh
            int rowsAffected = preStatement.executeUpdate();
            if (rowsAffected > 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            System.err.println("Error updateBatchs: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }
//----------------------------------------------------------------------------------------------------------------------------------------------//

    public boolean deleteBatchById(int batchId) {
        String deleteBatchQuery = "DELETE FROM BatchesProduct WHERE batchId = ?";

        try {
            // Bắt đầu transaction
            connection = getConnection();
            connection.setAutoCommit(false);

            // Xóa lô hàng theo batchId
            try (PreparedStatement batchStmt = connection.prepareStatement(deleteBatchQuery)) {
                batchStmt.setInt(1, batchId);
                int rowsAffected = batchStmt.executeUpdate();

                if (rowsAffected > 0) {
                    connection.commit(); // Commit transaction nếu xóa thành công
                    return true; // Xóa thành công
                } else {
                    connection.rollback(); // Rollback nếu không có bản ghi nào bị ảnh hưởng
                    return false; // Không tìm thấy batchId để xóa
                }
            }
        } catch (SQLException e) {
            // Nếu có lỗi, rollback transaction
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            System.err.println("Error deleting batch by ID: " + e.getMessage());
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
    public void updatePromos(int promotionId, Date startDate, Date endDate, double discount) {
        //- Chuan bi cau lenh sql
        String sql = "UPDATE Promos SET startDate = ?, endDate = ?, discount = ? WHERE promotionId = ?";
        boolean isUpdated = false;
        try {
            connection = getConnection();
            //- Tao doi tuong prepareStatement (thêm generated key vao tham so thu 2)
            preStatement = connection.prepareStatement(sql);            //- set parameter
            preStatement.setDate(1, new java.sql.Date(startDate.getTime()));
            preStatement.setDate(2, new java.sql.Date(endDate.getTime()));
            preStatement.setDouble(3, discount);
            preStatement.setInt(4, promotionId);

            //- thuc thi cau lenh
            int rowsAffected = preStatement.executeUpdate();
            if (rowsAffected > 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            System.err.println("Error updatePromos: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    //-----------------------Huy-(end)--------------------------------//
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        List<Products> products = productDAO.getAllProducts();

        // Hiển thị thông tin sản phẩm
        for (Products product : products) {
            System.out.println("Name: " + product.getName()
                    + ", Sold Quantity: " + product.getQuantity()
                    + ", Price: " + product.getUnitPrice()
                    + ", Promotion Status: " + product.getPromotionStatus());
        }
    }

    // 
    public void uppr() {
        //- Chuan bi cau lenh sql
        String sql = "UPDATE pr\n"
                + "SET pr.discount = 0\n"
                + "FROM Promos pr\n"
                + "JOIN Products p ON p.productId = pr.productId\n"
                + "WHERE pr.endDate < GETDATE();";
        boolean isUpdated = false;
        try {
            connection = getConnection();
            //- Tao doi tuong prepareStatement (thêm generated key vao tham so thu 2)
            preStatement = connection.prepareStatement(sql);            //- set parameter

            //- thuc thi cau lenh
            int rowsAffected = preStatement.executeUpdate();
            if (rowsAffected > 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            System.err.println("Error updateBatchs: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }
}
