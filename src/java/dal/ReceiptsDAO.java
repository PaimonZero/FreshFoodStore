package dal;

import java.sql.Statement;
import dto.ReceiptDTO;
import dto.ReceiptDetailDTO;
import dto.ShipperDTO;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import model.BatchesProduct;
import model.Products;
import model.ReceiptDetails;

public class ReceiptsDAO extends DBContext {

    public ReceiptsDAO() {
    }

//Mục đích sử dụng: Lấy danh sách các đơn nhập hàng
    public List<ReceiptDTO> getAllReceipts() {
        List<ReceiptDTO> listReceipt = new ArrayList<>();
        //- connect w/database
        connection = getConnection();
        //- Chuẩn bị câu lệnh sql
        String sql = """
                 SELECT 
                     r.receiptId,
                     s.name AS supplierName,
                     r.dateInput AS inputDate,
                     SUM(rd.quantity * rd.inputPrice) AS totalPrice, -- Tính tổng giá tiền của đơn nhập
                     COUNT(DISTINCT rd.productId) AS productTypes,   -- Đếm số loại sản phẩm (số sản phẩm khác nhau)
                     SUM(rd.quantity) AS totalQuantity               -- Tính tổng số lượng sản phẩm
                 FROM 
                     Receipts r
                 JOIN 
                     Suppliers s ON r.supplierId = s.supplierId
                 LEFT JOIN 
                     ReceiptDetails rd ON r.receiptId = rd.receiptId
                 GROUP BY 
                     r.receiptId, s.name, r.dateInput
                 ORDER BY 
                     r.dateInput DESC;""";  // Thêm ORDER BY để sắp xếp theo ngày giảm dần
        try {
            //- tạo đối tượng PrepareStatement
            preStatement = connection.prepareStatement(sql);
            //- Thực thi câu lệnh
            resultSet = preStatement.executeQuery();
            //- Trả về kết quả
            while (resultSet.next()) {
                int receiptId = resultSet.getInt("receiptId");
                String supplierName = resultSet.getString("supplierName");
                Date inputDate = resultSet.getDate("inputDate");
                double totalPrice = resultSet.getDouble("totalPrice");
                int productTypes = resultSet.getInt("productTypes");
                int totalQuantity = resultSet.getInt("totalQuantity");

                ReceiptDTO re = new ReceiptDTO(receiptId, supplierName, inputDate, totalPrice, productTypes, totalQuantity);
                listReceipt.add(re);
            }
        } catch (SQLException e) {
            System.out.println("??(Receipts)getAllReceipts: " + e.getMessage());
        }
        return listReceipt;
    }

    public List<ReceiptDTO> searchReceipts(String query) {
        List<ReceiptDTO> listReceipt = new ArrayList<>();
        // Connect to the database
        connection = getConnection();
        // Prepare SQL query
        String sql = """
                 SELECT 
                     r.receiptId,
                     s.name AS supplierName,
                     r.dateInput AS inputDate,
                     SUM(rd.quantity * rd.inputPrice) AS totalPrice,
                     COUNT(DISTINCT rd.productId) AS productTypes,
                     SUM(rd.quantity) AS totalQuantity
                 FROM 
                     Receipts r
                 JOIN 
                     Suppliers s ON r.supplierId = s.supplierId
                 LEFT JOIN 
                     ReceiptDetails rd ON r.receiptId = rd.receiptId
                 WHERE 
                     r.receiptId LIKE ? OR s.name LIKE ? -- Search condition
                 GROUP BY 
                     r.receiptId, s.name, r.dateInput
                 ORDER BY 
                     r.dateInput DESC;""";

        try {
            preStatement = connection.prepareStatement(sql);
            // Use "%" for wildcard search
            String searchPattern = "%" + query + "%";
            preStatement.setString(1, searchPattern);
            preStatement.setString(2, searchPattern);

            resultSet = preStatement.executeQuery();

            while (resultSet.next()) {
                // Extract data as before
                int receiptId = resultSet.getInt("receiptId");
                String supplierName = resultSet.getString("supplierName");
                Date inputDate = resultSet.getDate("inputDate");
                double totalPrice = resultSet.getDouble("totalPrice");
                int productTypes = resultSet.getInt("productTypes");
                int totalQuantity = resultSet.getInt("totalQuantity");

                ReceiptDTO re = new ReceiptDTO(receiptId, supplierName, inputDate, totalPrice, productTypes, totalQuantity);
                listReceipt.add(re);
            }
        } catch (SQLException e) {
            System.out.println("Error in searchReceipts: " + e.getMessage());
        }
        return listReceipt;
    }

//Mục đích sử dụng: Lấy thông tin đơn nhập nhập hàng theo id
    public ReceiptDTO getReceiptById(int receiptId) {
        //- connect w/database
        connection = getConnection();
        //- Chuẩn bị câu lệnh sql
        String sql = """
                     SELECT 
                             r.receiptId,
                             s.name AS supplierName,
                             s.supplierId,
                             r.dateInput AS inputDate,
                             SUM(rd.quantity * rd.inputPrice) AS totalPrice, -- Tính tổng giá tiền của đơn nhập
                             COUNT(DISTINCT rd.productId) AS productTypes,   -- Đếm số loại sản phẩm (số sản phẩm khác nhau)
                             SUM(rd.quantity) AS totalQuantity               -- Tính tổng số lượng sản phẩm
                         FROM 
                             Receipts r
                         JOIN 
                             Suppliers s ON r.supplierId = s.supplierId
                         LEFT JOIN 
                             ReceiptDetails rd ON r.receiptId = rd.receiptId
                         WHERE 
                             r.receiptId = ?
                         GROUP BY 
                             r.receiptId, s.supplierId, s.name, r.dateInput;""";
        try {
            //- tạo đối tượng PrepareStatement
            preStatement = connection.prepareStatement(sql);
            //- Set parameter (optional)
            preStatement.setObject(1, receiptId);
            //- Thực thi câu lệnh
            resultSet = preStatement.executeQuery();
            //- Trả về kết quả
            if (resultSet.next()) {
                int receiptId_found = resultSet.getInt("receiptId");
                String supplierName = resultSet.getString("supplierName");
                int supplierId = resultSet.getInt("supplierId");
                Date inputDate = resultSet.getDate("inputDate");
                double totalPrice = resultSet.getDouble("totalPrice");
                int productTypes = resultSet.getInt("productTypes");
                int totalQuantity = resultSet.getInt("totalQuantity");

                ReceiptDTO re = new ReceiptDTO(receiptId, supplierName, inputDate, totalPrice, productTypes, totalQuantity, supplierId);
                return re;
            }
        } catch (SQLException e) {
            System.out.println("??(Receipts)getReceiptById: " + e.getMessage());
        }
        return null;
    }

//Mục đích sử dụng: Lấy danh sách thông tin đơn nhập
    public List<ReceiptDetailDTO> getReceiptDetailById(int receiptId) {
        List<ReceiptDetailDTO> listReceiptDetail = new ArrayList<>();
        //- connect w/database
        connection = getConnection();
        //- Chuẩn bị câu lệnh sql
        String sql = "SELECT \n"
                + "    rd.receiptDetailId,\n"
                + "    rd.productId,\n"
                + "    p.name AS productName,\n"
                + "    rd.quantity,\n"
                + "    rd.inputPrice,\n"
                + "    rd.expiryDate,\n"
                + "    bp.batchId\n"
                + "FROM \n"
                + "    ReceiptDetails rd\n"
                + "JOIN \n"
                + "    Products p ON rd.productId = p.productId\n"
                + "LEFT JOIN \n"
                + "    BatchesProduct bp ON rd.receiptDetailId = bp.receiptDetailId\n"
                + "WHERE \n"
                + "    rd.receiptId = ?;";
        try {
            //- tạo đối tượng PrepareStatement
            preStatement = connection.prepareStatement(sql);
            //- Set parameter (optional)
            preStatement.setObject(1, receiptId);
            //- Thực thi câu lệnh
            resultSet = preStatement.executeQuery();
            //- Trả về kết quả
            while (resultSet.next()) {
                int receiptDetailId = resultSet.getInt("receiptDetailId");
                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                int quantity = resultSet.getInt("quantity");
                double inputPrice = resultSet.getDouble("inputPrice");
                Date expiryDate = resultSet.getDate("expiryDate");
                int batchId = resultSet.getInt("batchId");

                ReceiptDetailDTO rd = new ReceiptDetailDTO(receiptDetailId, productId, productName, quantity, inputPrice, expiryDate, batchId);
                listReceiptDetail.add(rd);
            }
        } catch (SQLException e) {
            System.out.println("??(Receipts)getReceiptDetailById: " + e.getMessage());
        }
        return listReceiptDetail;
    }

    public List<Products> getAllProducts() {
        List<Products> listProducts = new ArrayList<>();
        //- connect w/database
        connection = getConnection();
        //- Chuẩn bị câu lệnh sql
        String sql = "SELECT [productId]\n"
                + "      ,[name]\n"
                + "  FROM [dbo].[Products]";
        try {
            //- tạo đối tượng PrepareStatement
            preStatement = connection.prepareStatement(sql);
            //- Set parameter (optional)
            //- Thực thi câu lệnh
            resultSet = preStatement.executeQuery();
            //- Trả về kết quả
            while (resultSet.next()) {
                int productId = resultSet.getInt("productId");
                String name = resultSet.getString("name");

                Products pd = new Products();
                pd.setProductId(productId);
                pd.setName(name);
                listProducts.add(pd);
            }
        } catch (SQLException e) {
            System.out.println("??(Receipts)getAllProducts: " + e.getMessage());
        }
        return listProducts;
    }

    //Mục đích sử dụng: thêm thông tin vào ReceiptDetails
    public int insertReDetail(ReceiptDetails rd) {
        int receiptDetailId = -1;
        //- connect w/Database
        connection = getConnection();
        //- Chuan bi cau lenh sql
        String sql = "INSERT INTO [dbo].[ReceiptDetails]\n"
                + "           ([receiptId]\n"
                + "           ,[productId]\n"
                + "           ,[quantity]\n"
                + "           ,[inputPrice]\n"
                + "           ,[expiryDate])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            //- Tao doi tuong prepareStatement (thêm generated key vao tham so thu 2)
            preStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //- set parameter
            preStatement.setObject(1, rd.getReceiptId());
            preStatement.setObject(2, rd.getProductId());
            preStatement.setObject(3, rd.getQuantity());
            preStatement.setObject(4, rd.getInputPrice());
            preStatement.setObject(5, rd.getExpiryDate());

            //- thuc thi cau lenh
            preStatement.executeUpdate();
            //- tra ve ket qua mới thêm
            resultSet = preStatement.getGeneratedKeys();
            if (resultSet.next()) {
                receiptDetailId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("??(ReceiptsDAO)insertReDetail: " + e.getMessage());
        }
        return receiptDetailId;
    }

    // Hàm thêm sản phẩm vào cơ sở dữ liệu (chỉnh double cho unitPrice)
    public int addProductInReceipt(String name, String unitMeasure, int supplierId, int categoryId, String description,
            BigDecimal unitPrice, String status, String image) {
        int productId = -1;
        String sqlProduct = "INSERT INTO Products (name, unitMeasure, supplierId, categoryId, description, unitPrice, status, image) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
            preStatement.setString(8, image);
            preStatement.executeUpdate();

            // Lấy ID sản phẩm vừa thêm
            ResultSet generatedKeys = preStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                productId = generatedKeys.getInt(1);

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
            System.err.println("??(ReceiptDAO)Error adding product: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return productId;
    }

    //Mục đích sử dụng: thêm thông tin vào Gallery
    public boolean insertGallery(int productId, String src) {
        boolean result = false;
        //- connect w/Database
        connection = getConnection();
        //- Chuan bi cau lenh sql
        String sql = "INSERT INTO [dbo].[Gallery]\n"
                + "           ([productId]\n"
                + "           ,[src])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";

        try {
            //- Tao doi tuong prepareStatement (thêm generated key vao tham so thu 2)
            preStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //- set parameter
            preStatement.setObject(1, productId);
            preStatement.setObject(2, src);

            //- thuc thi cau lenh
            preStatement.executeUpdate();
            //- tra ve ket qua mới thêm
            resultSet = preStatement.getGeneratedKeys();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("??(ReceiptsDAO)insertGallery: " + e.getMessage());
        }
        return result;
    }

    //Hàm để xóa dữ liệu gallery cũ khi update product
    public boolean deleteGalleryByPId(int productId) {
        boolean result = false;
        // Kết nối cơ sở dữ liệu
        connection = getConnection();
        String sql = "DELETE FROM [dbo].[Gallery] WHERE productId = ?";

        try {
            // Tạo đối tượng PreparedStatement
            preStatement = connection.prepareStatement(sql);
            // Thiết lập tham số
            preStatement.setInt(1, productId);
            // Thực thi câu lệnh và kiểm tra số hàng bị ảnh hưởng
            int affectedRows = preStatement.executeUpdate();
            if (affectedRows > 0) {
                result = true; // Nếu có hàng bị xóa, thì thành công
            }
        } catch (SQLException e) {
            System.out.println("??(ReceiptsDAO)deleteGalleryByPId: " + e.getMessage());
        } finally {
            // Đóng tài nguyên để tránh rò rỉ
            try {
                if (preStatement != null) {
                    preStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("?? Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
        return result;
    }

    //Mục đích sử dụng: thêm thông tin vào BatchesProduct
    public boolean insertBatchesProduct(int receiptDetailId, int productId, int quantity, Date expiryDate) {
        boolean result = false;
        //- connect w/Database
        connection = getConnection();
        //- Chuan bi cau lenh sql
        String sql = "INSERT INTO [dbo].[BatchesProduct]\n"
                + "           ([receiptDetailId]\n"
                + "           ,[productId]\n"
                + "           ,[quantity]\n"
                + "           ,[expiryDate])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            //- Tao doi tuong prepareStatement (thêm generated key vao tham so thu 2)
            preStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //- set parameter
            preStatement.setObject(1, receiptDetailId);
            preStatement.setObject(2, productId);
            preStatement.setObject(3, quantity);
            preStatement.setObject(4, expiryDate);

            //- thuc thi cau lenh
            preStatement.executeUpdate();
            //- tra ve ket qua mới thêm
            resultSet = preStatement.getGeneratedKeys();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("??(ReceiptsDAO)insertBatchesProduct: " + e.getMessage());
        }
        return result;
    }

    public List<BatchesProduct> getBatchesByReceiptDetailIds(List<Integer> receiptDetailIds) {
        List<BatchesProduct> batchesList = new ArrayList<>();
        connection = getConnection();
        String sql = "SELECT bp.batchId, bp.receiptDetailId, bp.productId, bp.quantity, bp.expiryDate, "
                + "p.name AS productName, p.unitPrice "
                + "FROM BatchesProduct bp "
                + "JOIN Products p ON bp.productId = p.productId "
                + "WHERE bp.receiptDetailId IN ("
                + receiptDetailIds.stream().map(id -> "?").collect(Collectors.joining(", ")) + ")";
        try {
            preStatement = connection.prepareStatement(sql);
            for (int i = 0; i < receiptDetailIds.size(); i++) {
                preStatement.setInt(i + 1, receiptDetailIds.get(i));
            }
            resultSet = preStatement.executeQuery();
            while (resultSet.next()) {
                int batchId = resultSet.getInt("batchId");
                int receiptDetailId = resultSet.getInt("receiptDetailId");
                int productId = resultSet.getInt("productId");
                int quantity = resultSet.getInt("quantity");
                Date expiryDate = resultSet.getDate("expiryDate");
                String productName = resultSet.getString("productName");
                double salePrice = resultSet.getDouble("unitPrice");
                BatchesProduct batch = new BatchesProduct(batchId, receiptDetailId, productId, quantity, expiryDate, productName, salePrice);
                batchesList.add(batch);
            }
        } catch (SQLException e) {
            System.out.println("??(ReceiptsDAO)getBatchesByReceiptDetailIds: " + e.getMessage());
        }
        return batchesList;
    }

    public static void main(String[] args) {
        // Tạo một đối tượng của lớp chứa phương thức getAllReceipts()
        ReceiptsDAO receiptDAO = new ReceiptsDAO();

        // Tạo danh sách các receiptDetailId cần truy vấn
        List<Integer> receiptDetailIds = Arrays.asList(1, 2, 3); // Ví dụ các ID là 1, 2, 3

        // Gọi phương thức getBatchesByReceiptDetailIds và lấy kết quả
        List<BatchesProduct> batches = receiptDAO.getBatchesByReceiptDetailIds(receiptDetailIds);

        // In ra kết quả để kiểm tra
        System.out.println("Kết quả truy vấn BatchesProduct:");
        for (BatchesProduct batch : batches) {
            System.out.println("Batch ID: " + batch.getBatchId());
            System.out.println("Receipt Detail ID: " + batch.getReceiptDetailId());
            System.out.println("Product ID: " + batch.getProductId());
            System.out.println("Quantity: " + batch.getQuantity());
            System.out.println("Expiry Date: " + batch.getExpiryDate());
            System.out.println("-----------------------------");
        }

//        // Gọi phương thức getAllReceipts() để lấy danh sách tất cả các Receipt
//        List<ReceiptDTO> receipts = receiptDAO.getAllReceipts();
//
//        // Kiểm tra danh sách trả về
//        if (receipts.isEmpty()) {
//            System.out.println("Không có phiếu nhập nào trong cơ sở dữ liệu.");
//        } else {
//            // In ra thông tin từng phiếu nhập
//            for (ReceiptDTO receipt : receipts) {
//                System.out.println(receipt);
//            }
//        }
    }
}
