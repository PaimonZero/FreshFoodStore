package dal;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Users;

public class UserDAO extends DBContext {

    public Users findByEmailOrPhoneAndPassword(String emailOrPhone, String password) {
        //- connect w/database
        connection = getConnection();
        //- Chuẩn bị câu lệnh sql
        String sql = """
                     SELECT 
                         u.userId, 
                         u.fullName, 
                         u.email, 
                         u.phone, 
                         CASE 
                             WHEN c.customerId IS NOT NULL THEN 'Customer'
                             WHEN s.staffId IS NOT NULL THEN 'Staff'
                             WHEN sh.shipperId IS NOT NULL THEN 'Shipper'
                             WHEN m.managerId IS NOT NULL THEN 'Manager'
                             ELSE 'Unknown'
                         END AS role
                     FROM Users u
                     LEFT JOIN Customers c ON u.userId = c.userId
                     LEFT JOIN Staff s ON u.userId = s.userId
                     LEFT JOIN Shippers sh ON u.userId = sh.userId
                     LEFT JOIN Managers m ON u.userId = m.userId
                     WHERE (u.email = ? OR u.phone = ?)
                       AND u.password = ?;""";
        try {
            //- tạo đối tượng PrepareStatement
            preStatement = connection.prepareStatement(sql);
            //- Set parameter (optional)
            preStatement.setObject(1, emailOrPhone);
            preStatement.setObject(2, emailOrPhone);
            preStatement.setObject(3, password);
            //- Thực thi câu lệnh
            resultSet = preStatement.executeQuery();
            //- Trả về kết quả
            if (resultSet.next()) {
                String fullname_found = resultSet.getString("fullname").trim();
                String email_found = resultSet.getString("email").trim();
                String phone_found = resultSet.getString("phone").trim();
                String role = resultSet.getString("role").trim();
                int userID = resultSet.getInt("userID");

                Users user1 = new Users(userID, fullname_found, email_found, phone_found, role);
                return user1;
            }
        } catch (SQLException e) {
            System.out.println("??findByEmailPassword: " + e.getMessage());
        }
        return null;
    }
    
    public static void main(String[] args) {
       System.out.println(new UserDAO().findByEmailOrPhoneAndPassword("0912345678", "password123").toString());
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
