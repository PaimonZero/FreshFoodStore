package dal;

import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author ADMIN-PC
 */
public class CustomerDAO extends DBContext {

    //Nam chỉnh lại và thêm avatar
    public List<Customer> getAllCustomers(int page, int pageSize) {
        List<Customer> customerList = new ArrayList<>();
        String query = "SELECT \n"
                + "    c.customerId, \n"
                + "    u.userId, \n"
                + "    u.fullName, \n"
                + "    u.address, \n"
                + "    u.phone, \n"
                + "    u.email, \n"
                + "    u.avatar, \n"
                + "    u.status,\n"
                + "    u.createdAt\n"
                + "FROM Customers c\n"
                + "JOIN Users u ON c.userId = u.userId\n"
                + "ORDER BY c.customerId \n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try {
            connection = getConnection();
            if (connection != null) {
                preStatement = connection.prepareStatement(query);
                preStatement.setInt(1, (page - 1) * pageSize); // Offset
                preStatement.setInt(2, pageSize); // Page Size (Limit)
                resultSet = preStatement.executeQuery();

                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(resultSet.getInt("customerId"));
                    customer.setFullName(resultSet.getString("fullName"));
                    customer.setPhone(resultSet.getString("phone"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setAddress(resultSet.getString("address"));
                    customer.setAvatar(resultSet.getString("avatar"));
                    customer.setStatus(resultSet.getString("status")); // status is a String
                    customer.setUserId(resultSet.getInt("userId"));

                    customerList.add(customer);
                }
            }
        } catch (Exception e) {
            System.err.println("Error while fetching customers: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return customerList;
    }

    //đã check
    public int getTotalCustomers() {
        int total = 0;
        String query = "SELECT COUNT(*) AS total FROM Customers";

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
            System.err.println("Error while counting customers: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return total;
    }

    //Nam chỉnh lại (bỏ qua avatar)
    public boolean editCustomer(Customer customer) {
//        String query = "UPDATE Customers SET name = ?, number = ?, email = ?, address = ?, status = ? WHERE customerId = ?";
        String query = "UPDATE u\n"
                + "SET \n"
                + "    u.fullName = ?,\n"
                + "    u.address = ?,\n"
                + "    u.phone = ?,\n"
                + "    u.email = ?,\n"
                + "    u.[status] = ?\n"
                + "FROM Users u\n"
                + "JOIN Customers c ON c.userId = u.userId\n"
                + "WHERE c.customerId = ?;";

        try {
            connection = getConnection();
            if (connection != null) {
                preStatement = connection.prepareStatement(query);
                preStatement.setString(1, customer.getFullName());
                preStatement.setString(2, customer.getAddress());
                preStatement.setString(3, customer.getPhone());
                preStatement.setString(4, customer.getEmail());
                preStatement.setString(5, customer.getStatus());    // status is a String
                preStatement.setInt(6, customer.getCustomerId());

                int rowsAffected = preStatement.executeUpdate();
                return rowsAffected > 0; // Return true if the update was successful
            }
        } catch (Exception e) {
            System.err.println("Error while editing customer: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return false;
    }

    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();

        // Lấy tổng số khách hàng
        int totalCustomers = customerDAO.getTotalCustomers();
        System.out.println("Tổng số khách hàng: " + totalCustomers);

        // Lấy danh sách khách hàng ở trang 1 với kích thước trang là 5
        List<Customer> customers = customerDAO.getAllCustomers(1, 5);
        System.out.println("Danh sách khách hàng:");

        for (Customer customer : customers) {
            System.out.println("ID: " + customer.getCustomerId()
                    + ", Tên: " + customer.getFullName()
                    + ", Số điện thoại: " + customer.getPhone()
                    + ", Email: " + customer.getEmail()
                    + ", Địa chỉ: " + customer.getAddress()
                    + ", Trạng thái: " + customer.getStatus());
        }

    }
}
