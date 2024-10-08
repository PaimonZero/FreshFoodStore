package dao;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Category;
import model.Product;
import model.Supplier;

public class ProductDao extends DBContext {

    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM Products";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM product WHERE productId = ?";

    // Lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
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
                int unitPrice = resultSet.getInt("unitPrice");
                int quantity = resultSet.getInt("quantity");
                int discountValue = resultSet.getInt("discount");

                // Chuyển đổi discount từ int sang chuỗi
                String discountString = (discountValue != 0) ? "In progress" : "None";

                // Tạo đối tượng Product, truyền discountValue vào nhưng dùng discountString để hiển thị
                Product product = new Product(productId, name, unitMeasure, status, unitPrice, quantity, discountValue);

                // Hiển thị thông tin discount dưới dạng chuỗi
                System.out.println("Product Discount Status: " + discountString);

                productList.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return productList;
    }

    
    // Hàm thêm sản phẩm vào cơ sở dữ liệu
public void addProduct(String name, String unitMeasure, int supplierId, int categoryId, String description,
                           int unitPrice, String status) {
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
            preStatement.setInt(6, unitPrice);
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
            System.err.println("Error: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return suppliers;
    }

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
            System.err.println("Error: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return categories;
    }


    public static void main(String[] args) {
        // Tạo đối tượng ProductDao
        ProductDao productDao = new ProductDao();

                productDao.addProduct("Product Name", "Unit", 1, 1, "Description", 1000, "Available");


        // Gọi phương thức getAllProducts để lấy danh sách sản phẩm
        List<Product> productList = productDao.getAllProducts();

        // In ra danh sách sản phẩm và trạng thái discount
        for (Product product : productList) {
            // Kiểm tra và hiển thị trạng thái discount
            String discountStatus = (product.getDiscount() != 0) ? "In progress" : "None";

            // In thông tin chi tiết sản phẩm
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Name: " + product.getName());
            System.out.println("Unit Measure: " + product.getUnitMeasure());
            System.out.println("Status: " + product.getStatus());
            System.out.println("Unit Price: " + product.getUnitPrice());
            System.out.println("Quantity: " + product.getQuantity());
            System.out.println("Discount: " + discountStatus);  // Hiển thị trạng thái discount
            System.out.println("------------------------------");
        }
    }
}
