package dal;

import dto.UsersDTO;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Users;

public class UserDAO extends DBContext {

    //Mục đích sử dụng: login (bằng mật khẩu tự đặt)
    public Users findByEmailAndPassword(String email, String password) {
        //- connect w/database
        connection = getConnection();
        //- Chuẩn bị câu lệnh sql
        String sql = """
                     SELECT 
                         u.userId, 
                         u.fullName, 
                         u.email, 
                         u.address, 
                         u.phone, 
                         u.avatar,
                         CASE 
                             WHEN c.customerId IS NOT NULL THEN 'customer'
                             WHEN s.staffId IS NOT NULL THEN 'staff'
                             WHEN sh.shipperId IS NOT NULL THEN 'shipper'
                             WHEN m.managerId IS NOT NULL THEN 'manager'
                             ELSE 'unknown'
                         END AS role
                     FROM Users u
                     LEFT JOIN Customers c ON u.userId = c.userId
                     LEFT JOIN Staffs s ON u.userId = s.userId
                     LEFT JOIN Shippers sh ON u.userId = sh.userId
                     LEFT JOIN Managers m ON u.userId = m.userId
                     WHERE u.email = ? AND u.password = ?;""";
        try {
            //- tạo đối tượng PrepareStatement
            preStatement = connection.prepareStatement(sql);
            //- Set parameter (optional)
            preStatement.setObject(1, email);
            preStatement.setObject(2, password);
            //- Thực thi câu lệnh
            resultSet = preStatement.executeQuery();
            //- Trả về kết quả
            if (resultSet.next()) {
                String fullname_found = resultSet.getString("fullname").trim();
                String email_found = resultSet.getString("email").trim();
                String phone_found = resultSet.getString("phone").trim();
                String role = resultSet.getString("role").trim();
                String address_found = resultSet.getString("address").trim();
                String avatar = resultSet.getString("avatar");
                int userID = resultSet.getInt("userID");

                Users user1 = new Users(userID, fullname_found, email_found, phone_found, address_found, role);
                user1.setAvatar(avatar);
                return user1;
            }
        } catch (SQLException e) {
            System.out.println("??findByEmailPassword: " + e.getMessage());
        }
        return null;
    }

    //Mục đích sử dụng: Check sign up
    public Users findByEmail(String email) {
        //- connect w/database
        connection = getConnection();
        //- Chuẩn bị câu lệnh sql
        String sql = """
                     SELECT 
                         u.userId, 
                         u.fullName, 
                         u.email, 
                         u.address, 
                         u.phone, 
                         u.avatar,
                         CASE 
                             WHEN c.customerId IS NOT NULL THEN 'customer'
                             WHEN s.staffId IS NOT NULL THEN 'staff'
                             WHEN sh.shipperId IS NOT NULL THEN 'shipper'
                             WHEN m.managerId IS NOT NULL THEN 'manager'
                             ELSE 'unknown'
                         END AS role
                     FROM Users u
                     LEFT JOIN Customers c ON u.userId = c.userId
                     LEFT JOIN Staffs s ON u.userId = s.userId
                     LEFT JOIN Shippers sh ON u.userId = sh.userId
                     LEFT JOIN Managers m ON u.userId = m.userId
                     WHERE u.email = ?""";
        try {
            //- tạo đối tượng PrepareStatement
            preStatement = connection.prepareStatement(sql);
            //- Set parameter (optional)
            preStatement.setObject(1, email);
            //- Thực thi câu lệnh
            resultSet = preStatement.executeQuery();
            //- Trả về kết quả
            if (resultSet.next()) {
                String fullname_found = resultSet.getString("fullname").trim();
                String email_found = resultSet.getString("email").trim();
                String phone_found = resultSet.getString("phone").trim();
                String role = resultSet.getString("role").trim();
                String address_found = resultSet.getString("address").trim();
                String avatar = resultSet.getString("avatar");
                int userID = resultSet.getInt("userID");

                Users user1 = new Users(userID, fullname_found, email_found, phone_found, address_found, role);
                user1.setAvatar(avatar);
                return user1;
                
            }
        } catch (SQLException e) {
            System.out.println("??findByEmail: " + e.getMessage());
        }
        return null;
    }

        //Mục đích sử dụng: sign up , 
    public int insertUserDB(Users user) {
        int userID = 0;
        //- connect w/Database
        connection = getConnection();
        //- Chuan bi cau lenh sql
        String sql = """
                     INSERT INTO [dbo].[Users]
                                ([fullName]
                                ,[address]
                                ,[phone]
                                ,[email]
                                ,[password]
                                ,avatar
                                ,status)
                          VALUES
                                (?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?);""";
        
        String sql2 = """
                      INSERT INTO [dbo].[Customers]
                                 ([userId])
                           VALUES
                                 (?);""";
        try {
            //- Tao doi tuong prepareStatement (thêm generated key vao tham so thu 2)
            preStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //- set parameter
            preStatement.setObject(1, user.getFullName());
            preStatement.setObject(2, user.getAddress());
            preStatement.setObject(3, user.getPhone());
            preStatement.setObject(4, user.getEmail());
            preStatement.setObject(5, user.getPassword());
            preStatement.setObject(6, user.getAvatar());
            preStatement.setObject(7, "normal");
            //- thuc thi cau lenh
            preStatement.executeUpdate();
            //- tra ve ket qua mới thêm
            resultSet = preStatement.getGeneratedKeys();
            if (resultSet.next()) {
                userID = resultSet.getInt(1);
            }
            //Thêm user trên vào bảng Customers
            preStatement = connection.prepareStatement(sql2);
            //- set parameter
            preStatement.setObject(1, userID);
            //- thuc thi cau lenh
            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("??insertUserDB: " + e.getMessage());
        }
        return userID;
    }
    
    public void updatePasswordUserDB(int userID, String newPassword) {
        //- connect w/Database
        connection = getConnection();
        //- Chuan bi cau lenh sql
        String sql = """
                     UPDATE [dbo].[Users]
                                   SET [password] = ?
                                 WHERE [userId] = ?""";
        try {
            //- Tao doi tuong prepareStatement (thêm generated key vao tham so thu 2)
            preStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //- set parameter
            preStatement.setObject(1, newPassword);
            preStatement.setObject(2, userID);
            
            //- thuc thi cau lenh
            preStatement.executeUpdate();
            //- tra ve ket qua mới thêm
            resultSet = preStatement.getGeneratedKeys();
        } catch (SQLException e) {
            System.out.println("??updatePasswordUserDB: " + e.getMessage());
        }
    }
    
//================================================================================================================================================
    //Cho bên admin
    public List<UsersDTO> getAllUsers(int page, int pageSize) {
        List<UsersDTO> userList = new ArrayList<>();
        String query = "SELECT \n"
                + "    U.userId,\n"
                + "    U.fullName,\n"
                + "    U.address,\n"
                + "    U.phone,\n"
                + "    U.email,\n"
                + "    U.avatar,\n"
                + "    U.createdAt,\n"
                + "    U.[status],\n"
                + "    CASE \n"
                + "        WHEN M.managerId IS NOT NULL THEN 'Manager'\n"
                + "        WHEN ST.staffId IS NOT NULL THEN 'Staff'\n"
                + "        WHEN S.shipperId IS NOT NULL THEN 'Shipper'\n"
                + "        WHEN C.customerId IS NOT NULL THEN 'Customer'\n"
                + "        ELSE 'Unknown'\n"
                + "    END AS role,\n"
                + "    ISNULL(M.managerId, ISNULL(ST.staffId, ISNULL(S.shipperId, C.customerId))) AS roleId\n"
                + "FROM \n"
                + "    Users U\n"
                + "LEFT JOIN Managers M ON U.userId = M.userId\n"
                + "LEFT JOIN Staffs ST ON U.userId = ST.userId\n"
                + "LEFT JOIN Shippers S ON U.userId = S.userId\n"
                + "LEFT JOIN Customers C ON U.userId = C.userId\n"
                + "ORDER BY \n"
                + "    CASE \n"
                + "        WHEN M.managerId IS NOT NULL THEN 1\n"
                + "        WHEN ST.staffId IS NOT NULL THEN 2\n"
                + "        WHEN S.shipperId IS NOT NULL THEN 3\n"
                + "        WHEN C.customerId IS NOT NULL THEN 4\n"
                + "        ELSE 5\n"
                + "    END\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try {
            connection = getConnection();
            if (connection != null) {
                preStatement = connection.prepareStatement(query);
                preStatement.setInt(1, (page - 1) * pageSize); // Offset
                preStatement.setInt(2, pageSize); // Page Size (Limit)
                resultSet = preStatement.executeQuery();

                while (resultSet.next()) {
                    UsersDTO user = new UsersDTO();
                    user.setUserId(resultSet.getInt("userId"));
                    user.setFullName(resultSet.getString("fullName"));
                    user.setAddress(resultSet.getString("address"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setEmail(resultSet.getString("email"));
//                    user.setPassword(resultSet.getString("password"));
//                    user.setPassGoogle(resultSet.getString("passGoogle"));
                    user.setAvatar(resultSet.getString("avatar"));
                    user.setCreatedAt(resultSet.getDate("createdAt"));
                    user.setStatus(resultSet.getString("status"));
                    user.setRoleName(resultSet.getString("role"));      //là biến lưu trữ tên role (Customer, Staff, Shipper, Manager)
                    user.setRoleId(resultSet.getInt("roleId"));          //là customerId, staffId, shipperId, managerId trong db

                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            System.err.println("??(UserDAO)getAllUsers: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return userList;
    }

    public List<UsersDTO> searchUsers(String searchQuery) {
        List<UsersDTO> userList = new ArrayList<>();
        String query = "SELECT \n"
                + "    U.userId,\n"
                + "    U.fullName,\n"
                + "    U.address,\n"
                + "    U.phone,\n"
                + "    U.email,\n"
                + "    U.avatar,\n"
                + "    U.createdAt,\n"
                + "    U.[status],\n"
                + "    CASE \n"
                + "        WHEN M.managerId IS NOT NULL THEN 'Manager'\n"
                + "        WHEN ST.staffId IS NOT NULL THEN 'Staff'\n"
                + "        WHEN S.shipperId IS NOT NULL THEN 'Shipper'\n"
                + "        WHEN C.customerId IS NOT NULL THEN 'Customer'\n"
                + "        ELSE 'Unknown'\n"
                + "    END AS role,\n"
                + "    ISNULL(M.managerId, ISNULL(ST.staffId, ISNULL(S.shipperId, C.customerId))) AS roleId\n"
                + "FROM \n"
                + "    Users U\n"
                + "LEFT JOIN Managers M ON U.userId = M.userId\n"
                + "LEFT JOIN Staffs ST ON U.userId = ST.userId\n"
                + "LEFT JOIN Shippers S ON U.userId = S.userId\n"
                + "LEFT JOIN Customers C ON U.userId = C.userId\n"
                + "WHERE U.fullName LIKE ? OR U.email LIKE ? OR U.phone LIKE ?\n"
                + "ORDER BY \n"
                + "    CASE \n"
                + "        WHEN M.managerId IS NOT NULL THEN 1\n"
                + "        WHEN ST.staffId IS NOT NULL THEN 2\n"
                + "        WHEN S.shipperId IS NOT NULL THEN 3\n"
                + "        WHEN C.customerId IS NOT NULL THEN 4\n"
                + "        ELSE 5\n"
                + "    END";

        try {
            connection = getConnection();
            if (connection != null) {
                preStatement = connection.prepareStatement(query);
                // Sử dụng '%' để tìm kiếm với LIKE
                String searchPattern = "%" + searchQuery + "%";
                preStatement.setString(1, searchPattern); // FullName
                preStatement.setString(2, searchPattern); // Email
                preStatement.setString(3, searchPattern); // Phone
                
                resultSet = preStatement.executeQuery();

                while (resultSet.next()) {
                    UsersDTO user = new UsersDTO();
                    user.setUserId(resultSet.getInt("userId"));
                    user.setFullName(resultSet.getString("fullName"));
                    user.setAddress(resultSet.getString("address"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setEmail(resultSet.getString("email"));
                    user.setAvatar(resultSet.getString("avatar"));
                    user.setCreatedAt(resultSet.getDate("createdAt"));
                    user.setStatus(resultSet.getString("status"));
                    user.setRoleName(resultSet.getString("role"));      // Tên role
                    user.setRoleId(resultSet.getInt("roleId"));          // ID role

                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            System.err.println("??(UserDAO)searchUsers: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return userList;
    }

    public int getTotalUsers() {
        int total = 0;
        String query = "SELECT COUNT(*) AS total FROM Users";

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
            System.err.println("??(UserDAO)getTotalUsers: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return total;
    }

    //Chưa sài, hiện tại đang sử dụng updateUserWithRole
    public boolean editUser(UsersDTO user) {
        String query = "UPDATE Users\n"
                + "SET \n"
                + "    fullName = ?,\n"
                + "    address = ?,\n"
                + "    phone = ?,\n"
                + "    email = ?,\n"
                + "    [status] = ?\n"
                + "WHERE userId = ?;";

        try {
            connection = getConnection();
            if (connection != null) {
                preStatement = connection.prepareStatement(query);
                preStatement.setObject(1, user.getFullName());
                preStatement.setObject(2, user.getAddress());
                preStatement.setObject(3, user.getPhone());
                preStatement.setObject(4, user.getEmail());
                preStatement.setObject(5, user.getStatus());
                preStatement.setObject(6, user.getUserId());

                int rowsAffected = preStatement.executeUpdate();
                return rowsAffected > 0; // Return true if the update was successful
            }
        } catch (SQLException e) {
            System.err.println("??(UserDAO)editUser: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean updateUserWithRole(UsersDTO user) {
        String updateUserQuery = "UPDATE Users SET fullName = ?, address = ?, phone = ?, email = ?, [status] = ? WHERE userId = ?";
        String deleteRolesQuery = "DELETE FROM Managers WHERE userId = ?; DELETE FROM Staffs WHERE userId = ?; DELETE FROM Shippers WHERE userId = ?; DELETE FROM Customers WHERE userId = ?";
        String insertRoleQuery = null;

        switch (user.getRoleName()) {
            case "Manager":
                insertRoleQuery = "INSERT INTO Managers (userId) VALUES (?)";
                break;
            case "Staff":
                insertRoleQuery = "INSERT INTO Staffs (userId) VALUES (?)";
                break;
            case "Shipper":
                insertRoleQuery = "INSERT INTO Shippers (userId) VALUES (?)";
                break;
            case "Customer":
                insertRoleQuery = "INSERT INTO Customers (userId) VALUES (?)";
                break;
            default:
                System.out.println("Invalid role provided");
                return false;
        }

        boolean isUpdated = false;

        try {
            connection = getConnection();
            if (connection != null) {
                connection.setAutoCommit(false); // Start transaction

                // Bước 1: Cập nhật thông tin trong bảng Users
                preStatement = connection.prepareStatement(updateUserQuery);
                preStatement.setString(1, user.getFullName());
                preStatement.setString(2, user.getAddress());
                preStatement.setString(3, user.getPhone());
                preStatement.setString(4, user.getEmail());
                preStatement.setString(5, user.getStatus());
                preStatement.setInt(6, user.getUserId());
                preStatement.executeUpdate();

                // Bước 2: Xóa các vai trò cũ
                preStatement = connection.prepareStatement(deleteRolesQuery);
                preStatement.setInt(1, user.getUserId());
                preStatement.setInt(2, user.getUserId());
                preStatement.setInt(3, user.getUserId());
                preStatement.setInt(4, user.getUserId());
                preStatement.executeUpdate();

                // Bước 3: Chèn vai trò mới
                preStatement = connection.prepareStatement(insertRoleQuery);
                preStatement.setInt(1, user.getUserId());
                preStatement.executeUpdate();

                connection.commit();  // Commit transaction
                isUpdated = true;
            }
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();  // Rollback nếu có lỗi
                }
            } catch (SQLException rollbackEx) {
                System.err.println("??(UserDAO)updateUserWithRole - Rollback failed: " + rollbackEx.getMessage());
            }
            System.err.println("??(UserDAO)updateUserWithRole: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return isUpdated;
    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        System.out.println(new UserDAO().findByEmailAndPassword("customer@gmail.com", "12345").toString());
        System.out.println(new UserDAO().findByEmail("customer@gmail.com").toString());

        UsersDTO user = new UsersDTO();

        // Giả sử chúng ta đang cập nhật userId 1 với vai trò mới là 'Staff'
        user.setUserId(3);
        user.setFullName("John Doe Updated");
        user.setAddress("123 New Address");
        user.setPhone("0987654321");
        user.setEmail("john.updated@example.com");
        user.setStatus("normal");
        user.setRoleName("Staff");  // Vai trò mới

        boolean success = userDAO.updateUserWithRole(user);

        if (success) {
            System.out.println("User and role updated successfully.");
        } else {
            System.out.println("Failed to update user and role.");
        }

//        //test getAllUsers
//        // Ví dụ, bạn muốn lấy trang 1 với mỗi trang có 5 người dùng
//        int page = 1;
//        int pageSize = 5;
//
//        // Gọi phương thức getAllUsers với page và pageSize
//        List<UsersDTO> users = userDAO.getAllUsers(page, pageSize);
//
//        // Kiểm tra kết quả
//        if (users != null && !users.isEmpty()) {
//            System.out.println("List of Users:");
//            for (UsersDTO user : users) {
//                System.out.println("UserID: " + user.getUserId());
//                System.out.println("Full Name: " + user.getFullName());
//                System.out.println("Address: " + user.getAddress());
//                System.out.println("Phone: " + user.getPhone());
//                System.out.println("Email: " + user.getEmail());
//                System.out.println("Role: " + user.getRoleName());
//                System.out.println("Role ID: " + user.getRoleId());
//                System.out.println("Status: " + user.getStatus());
//                System.out.println("Created At: " + user.getCreatedAt());
//                System.out.println("Role name: " + user.getRoleName());
//                System.out.println("RoleID: " + user.getRoleId());
//                System.out.println("-------------------------------");
//            }
//        } else {
//            System.out.println("No users found or error occurred.");
//        }
//        Users utest = new Users();
//        utest.setFullName("test01");
//        utest.setAddress("test01");
//        utest.setPhone("1234567890");
//        utest.setEmail("test01");
//        utest.setPassword("test01");
//        System.out.println(new UserDAO().insertUserDB(utest));
//        UserDAO re = new UserDAO();
////        ArrayList<Product> list1 = re.listAllProductPhone();
////        for (Product item : list1) {
////            System.out.println(item.toString());
////        }
//        ArrayList<ProductDetail> list2 = re.listAllProductDetailTablet();
//        for (ProductDetail item : list2) {
//            System.out.println(item.toString());
//        }
    }

    ////////////////
    //////////////
    //////////////
//    public void updatePasswordUserDB(int userID, String newPassword) {
//        //- connect w/Database
//        connection = getConnection();
//        //- Chuan bi cau lenh sql
//        String sql = """
//                     UPDATE [dbo].[Users]
//                                   SET [password] = ?
//                                 WHERE [userID] = ?""";
//        try {
//            //- Tao doi tuong prepareStatement (thêm generated key vao tham so thu 2)
//            preStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            //- set parameter
//            preStatement.setObject(1, newPassword);
//            preStatement.setObject(2, userID);
//
//            //- thuc thi cau lenh
//            preStatement.executeUpdate();
//            //- tra ve ket qua mới thêm
//            resultSet = preStatement.getGeneratedKeys();
//        } catch (SQLException e) {
//            System.out.println("??updatePasswordUserDB: " + e.getMessage());
//        }
//    }
//
//    public int insertUserDB(Users user) {
//        int userID = 0;
//        //- connect w/Database
//        connection = getConnection();
//        //- Chuan bi cau lenh sql
//        String sql = """
//                     INSERT INTO [dbo].[Users]
//                                ([fullName]
//                                ,[email]
//                                ,[password]
//                                ,[roleID]
//                                ,[phone]
//                                ,[address])
//                          VALUES
//                                (?
//                                ,?
//                                ,?
//                                ,?
//                                ,?
//                                ,?)""";
//        try {
//            //- Tao doi tuong prepareStatement (thêm generated key vao tham so thu 2)
//            preStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            //- set parameter
//            preStatement.setObject(1, user.getFullName());
//            preStatement.setObject(2, user.getEmail());
//            preStatement.setObject(3, user.getPassword());
//            preStatement.setObject(4, user.getRoleID());
//            preStatement.setObject(5, user.getPhone());
//            preStatement.setObject(6, user.getAddress());
//            //- thuc thi cau lenh
//            preStatement.executeUpdate();
//            //- tra ve ket qua mới thêm
//            resultSet = preStatement.getGeneratedKeys();
//            if (resultSet.next()) {
//                userID = resultSet.getInt(1);
//            }
//        } catch (SQLException e) {
//            System.out.println("??insertUserDB: " + e.getMessage());
//        }
//        return userID;
//    }
////--------------------------------------------------------------------
////--------------------------------------------------------------------
//
//    public ArrayList<Users> listAll() {
//        ArrayList<Users> list = new ArrayList<>();
//        connection = getConnection();
//        String sql = """
//                     select userID, fullName, phone, email, address, roleID
//                     from [dbo].[Users] """;
//        try {
//            preStatement = connection.prepareStatement(sql);
//            resultSet = preStatement.executeQuery();
//            while (resultSet.next()) {
//                int userID = resultSet.getInt("userID");
//                String fullName = resultSet.getString("fullName");
//                String phone = resultSet.getString("phone");
//                String email = resultSet.getString("email");
//                String address = resultSet.getString("address");
//                int roleID = resultSet.getInt("roleID");
//                Users u = new Users(userID, fullName, email, phone, address, roleID);
//                //Orders or = new Orders(resultSet.getInt(1) , resultSet.getDate(2), resultSet.getNString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getNString(6));
//                list.add(u);
//            }
//            connection.close();
//            return list;
//        } catch (SQLException ex) {
//
//        }
//        return null;
//    }
//
//    public Users getUserById(int id) {
//        connection = getConnection();
//        String sql = """
//                     select *
//                     from [dbo].[Users] 
//                     WHERE [userID] = ? 
//                     """;
//        try {
//            preStatement = connection.prepareStatement(sql);
//            preStatement.setInt(1, id);
//            resultSet = preStatement.executeQuery();
//            while (resultSet.next()) {
//                int userID = resultSet.getInt("userID");
//                String fullName = resultSet.getString("fullName");
//                String phone = resultSet.getString("phone");
//                String email = resultSet.getString("email");
//                String password = resultSet.getString("password");
//                String address = resultSet.getString("address");
//                int roleID = resultSet.getInt("roleID");
//                Users u = new Users(userID, fullName, email, password, phone, address, roleID);
//                //Orders or = new Orders(resultSet.getInt(1) , resultSet.getDate(2), resultSet.getNString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getNString(6));
//                return u;
//            }
//            connection.close();
//        } catch (SQLException ex) {
//
//        }
//        return null;
//    }
//
//    public void insertUser(String fullname, String email, String password, String phone, String address, int roleID) {
//        //- connect w/Database
//        connection = getConnection();
//        //- Chuan bi cau lenh sql
//        String sql = """
//                     INSERT INTO [dbo].[Users]
//                                ([fullName]
//                                ,[email]
//                                ,[password]
//                                ,[phone]
//                                ,[address]
//                                ,[roleID])
//                          VALUES
//                                (?
//                                ,?
//                                ,?
//                                ,?
//                                ,?
//                                ,?)""";
//        try {
//            //- Tao doi tuong prepareStatement (thêm generated key vao tham so thu 2)
//            preStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            //- set parameter
//            preStatement.setObject(1, fullname);
//            preStatement.setObject(2, email);
//            preStatement.setObject(3, password);
//            preStatement.setObject(4, phone);
//            preStatement.setObject(5, address);
//            preStatement.setObject(6, roleID);
//            //- thuc thi cau lenh
//            preStatement.executeUpdate();
//            //- tra ve ket qua mới thêm
//            resultSet = preStatement.getGeneratedKeys();
//        } catch (SQLException e) {
//            System.out.println("??insertUserDB: " + e.getMessage());
//        }
//    }
//
//    public void updateUser(int userID, String fullname, String email, String password, String phone, String address, int roleID) {
//        connection = getConnection();
//        String sql = """
//                     UPDATE [dbo].[Users]
//                     SET [fullName] = ?
//                           ,[email] = ?
//                           ,[password] = ?
//                           ,[phone] = ?
//                           ,[address] = ?
//                           ,[roleID] =?
//                      WHERE userID = ?
//                     """;
//        try {
//            preStatement = connection.prepareStatement(sql);
//
//            preStatement.setObject(1, fullname);
//            preStatement.setObject(2, email);
//            preStatement.setObject(3, password);
//            preStatement.setObject(4, phone);
//            preStatement.setObject(5, address);
//            preStatement.setObject(6, roleID);
//            preStatement.setObject(7, userID);
//
//            preStatement.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println("??updateUser: " + ex.getMessage());
//        }
//
//    }
//
//    public void blockUser(int id) {
//        String sql = """
//                     UPDATE [dbo].[Users] 
//                     SET [roleID] = 3
//                     WHERE userID = ? """;
//
//        connection = getConnection();
//        try {
//            preStatement = connection.prepareStatement(sql);
//            preStatement.setObject(1, id);
//            preStatement.executeUpdate();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//--------------------------------------------------------------------
//--------------------------------------------------------------------
}
