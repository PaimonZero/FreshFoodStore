package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Supplier;

public class SupplierDAO extends DBContext {

    public List<Supplier> getAllSupplier(int page, int pageSize) {
        List<Supplier> supplierList = new ArrayList<>();
        String query = "SELECT * FROM Suppliers ORDER BY supplierId OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";  // Sửa lại từ customerId thành supplierId

        try {
            connection = getConnection();
            if (connection != null) {
                preStatement = connection.prepareStatement(query);
                preStatement.setInt(1, (page - 1) * pageSize); // Offset
                preStatement.setInt(2, pageSize); // Page Size (Limit)
                resultSet = preStatement.executeQuery();

                while (resultSet.next()) {
                    Supplier supplier = new Supplier();
                    supplier.setSupplierId(resultSet.getInt("supplierId"));
                    supplier.setName(resultSet.getString("name"));
                    supplier.setPhone(resultSet.getString("phone"));
                    supplier.setEmail(resultSet.getString("email"));
                    supplier.setAddress(resultSet.getString("address"));
                    supplier.setMoreInfo(resultSet.getString("moreInfo"));  // Giữ nguyên hoặc đổi tên nếu cần
                    supplier.setCreatedAt(resultSet.getDate("createdAt"));
                    supplierList.add(supplier);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching suppliers: " + e.getMessage());
            // Có thể ném lại ngoại lệ hoặc ghi log chi tiết hơn nếu cần
        } finally {
            closeConnection(); // Đảm bảo tài nguyên được giải phóng
        }
        return supplierList;
    }

    public List<Supplier> searchSuppliers(String searchQuery) {
        List<Supplier> supplierList = new ArrayList<>();
        String query = "SELECT \n"
                + "    supplierId,\n"
                + "    name,\n"
                + "    address,\n"
                + "    phone,\n"
                + "    email,\n"
                + "    moreInfo,\n"
                + "    createdAt\n"
                + "FROM \n"
                + "    Suppliers\n"
                + "WHERE \n"
                + "    name LIKE ? OR email LIKE ? OR phone LIKE ?\n"
                + "ORDER BY createdAt DESC;"; // Có thể thay đổi theo yêu cầu

        try {
            connection = getConnection();
            if (connection != null) {
                preStatement = connection.prepareStatement(query);
                // Sử dụng '%' để tìm kiếm với LIKE
                String searchPattern = "%" + searchQuery + "%";
                preStatement.setString(1, searchPattern); // Tên nhà cung cấp
                preStatement.setString(2, searchPattern); // Email
                preStatement.setString(3, searchPattern); // Số điện thoại

                resultSet = preStatement.executeQuery();

                while (resultSet.next()) {
                    Supplier supplier = new Supplier();
                    supplier.setSupplierId(resultSet.getInt("supplierId"));
                    supplier.setName(resultSet.getString("name"));
                    supplier.setAddress(resultSet.getString("address"));
                    supplier.setPhone(resultSet.getString("phone"));
                    supplier.setEmail(resultSet.getString("email"));
                    supplier.setMoreInfo(resultSet.getString("moreInfo"));
                    supplier.setCreatedAt(resultSet.getDate("createdAt")); // Chắc chắn bạn đã có một phương thức setCreatedAt

                    supplierList.add(supplier);
                }
            }
        } catch (SQLException e) {
            System.err.println("??(SupplierDAO)searchSuppliers: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return supplierList;
    }

    public int getTotalSupplier() {
        int total = 0;
        String query = "SELECT COUNT(*) AS total FROM Suppliers";

        try {
            connection = getConnection();
            if (connection != null) {
                preStatement = connection.prepareStatement(query);
                resultSet = preStatement.executeQuery();

                if (resultSet.next()) {
                    total = resultSet.getInt("total");
                }
            }
        } catch (Exception e) {
            System.err.println("Error while counting supplier: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return total;
    }

    public boolean editSupplier(Supplier supplier) {
        String query = "UPDATE [dbo].[Suppliers]\n"
                + "   SET [name] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[moreInfo] = ?\n"
                + " WHERE supplierId = ?";

        try {
            connection = getConnection();
            if (connection != null) {
                preStatement = connection.prepareStatement(query);
                preStatement.setString(1, supplier.getName());
                preStatement.setString(2, supplier.getAddress());
                preStatement.setString(3, supplier.getPhone());
                preStatement.setString(4, supplier.getEmail());
                preStatement.setString(5, supplier.getMoreInfo()); // status is a String
                preStatement.setInt(6, supplier.getSupplierId());

                int rowsAffected = preStatement.executeUpdate();
                return rowsAffected > 0; // Return true if the update was successful
            }
        } catch (Exception e) {
            System.err.println("Error while editing supplier: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean addSupplier(Supplier supplier) {
        String query = "INSERT INTO [dbo].[Suppliers]\n"
                + "           ([name]\n"
                + "           ,[address]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[moreInfo])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            connection = getConnection();
            if (connection != null) {
                preStatement = connection.prepareStatement(query);
                preStatement.setString(1, supplier.getName());
                preStatement.setString(2, supplier.getAddress());
                preStatement.setString(3, supplier.getPhone());
                preStatement.setString(4, supplier.getEmail());
                preStatement.setString(5, supplier.getMoreInfo());

                int rowsAffected = preStatement.executeUpdate();
                return rowsAffected > 0; // Return true if the update was successful
            }
        } catch (Exception e) {
            System.err.println("(SupplierDAO)addSupplier: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public static void main(String[] args) {
        SupplierDAO supplierDAO = new SupplierDAO();

        // Lấy tổng số khách hàng
        int totalSupplier = supplierDAO.getTotalSupplier();
        System.out.println("Tổng số khách hàng: " + totalSupplier);

        // Lấy danh sách khách hàng ở trang 1 với kích thước trang là 5
        List<Supplier> suppliers = supplierDAO.getAllSupplier(1, 10);
        System.out.println("Danh sách khách hàng:");

        for (Supplier supplier : suppliers) {
            System.out.println("ID: " + supplier.getSupplierId()
                    + ", Tên: " + supplier.getName()
                    + ", Số điện thoại: " + supplier.getPhone()
                    + ", Email: " + supplier.getEmail()
                    + ", Địa chỉ: " + supplier.getAddress()
                    + ", Ngày tạo: " + supplier.getCreatedAt()
                    + ", MoreInfo: " + supplier.getMoreInfo());
        }
    }
}
