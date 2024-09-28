/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.ConnectDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Staff;
import model.Users;

/**
 *
 * @author nguye
 */
public class ManagerDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // LIST ACTOR 
    public List<Users> allUser() {
        List<Users> allUser = new ArrayList<>();

        String sql = "SELECT *\n"
                + "FROM users s";

        try {
            // connect
            con = new ConnectDB().condb();
            // sql
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                Users u = new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getDate(8),
                        rs.getString(9)
                );
                allUser.add(u);
            }
        } catch (Exception e) {
        }
        return allUser;
    }

    public List<Users> listCustomer() {
        List<Users> customers = new ArrayList<>();

        String sql = "SELECT u.*, s.*\n"
                + "FROM customers s\n"
                + "JOIN users u ON s.userId = u.userId;";

        try {
            // connect
            con = new ConnectDB().condb();
            // sql
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                Users u = new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getDate(8),
                        rs.getString(9)
                );
                customers.add(u);
            }
        } catch (Exception e) {
        }
        return customers;
    }

    public List<Users> listShipper() {
        List<Users> shippers = new ArrayList<>();

        String sql = "SELECT u.*, s.*\n"
                + "FROM shippers s\n"
                + "JOIN users u ON s.userId = u.userId;";

        try {
            // connect
            con = new ConnectDB().condb();
            // sql
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                Users u = new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getDate(8),
                        rs.getString(9)
                );
                shippers.add(u);
            }
        } catch (Exception e) {
        }
        return shippers;
    }

    public List<Users> listManager() {
        List<Users> managers = new ArrayList<>();

        String sql = "SELECT u.*, m.*\n"
                + "FROM managers m\n"
                + "JOIN users u ON m.userId = u.userId;";

        try {
            // connect
            con = new ConnectDB().condb();
            // sql
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                Users u = new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getDate(8),
                        rs.getString(9)
                );
                managers.add(u);
            }
        } catch (Exception e) {
        }
        return managers;
    }

    public List<Users> listStaff() {
        List<Users> staffs = new ArrayList<>();

        String sql = "SELECT u.*, s.*\n"
                + "FROM staff s\n"
                + "JOIN users u ON s.userId = u.userId;";

        try {
            // connect
            con = new ConnectDB().condb();
            // sql
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                Users u = new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getDate(8),
                        rs.getString(9)
                );
                staffs.add(u);
            }
        } catch (Exception e) {
        }
        return staffs;
    }

    // ADD CUSTOMER 
    public void addCustomer(Users user) {
        String sqlUser = "insert into users(fullName,address,phone,email,password,otp,createdAt,image) values(?,?,?,?,?,?,?,?)";
        String sqlCustomer = "INSERT INTO customers (userId) VALUES (?);";
        String sqlGetUserId = "SELECT u.userId FROM users u WHERE email = ?";

        try {
            // connect
            con = new ConnectDB().condb();
            // write sql
            ps = con.prepareStatement(sqlUser);

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getAddress());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getOtp());
            ps.setDate(7, Date.valueOf(LocalDate.now()));
            ps.setString(8, user.getImage());
            ps.executeUpdate();

            ps = con.prepareStatement(sqlGetUserId);
            ps.setString(1, user.getEmail());

            ResultSet rsn = ps.executeQuery();
            if (rsn.next()) {
                int userId = rsn.getInt(1);
                System.out.println(userId);
                ps = con.prepareStatement(sqlCustomer);
                ps.setInt(1, userId);
                ps.executeUpdate();
            }

        } catch (Exception e) {
        }

    }

    public void addStaff(Users user) {
        String sqlUser = "insert into users(fullName,address,phone,email,password,otp,createdAt,image) values(?,?,?,?,?,?,?,?)";
        String sqlStaff = "INSERT INTO staff (userId) VALUES (?);";
        String sqlGetUserId = "SELECT u.userId FROM users u WHERE email = ?";

        try {
            // connect
            con = new ConnectDB().condb();
            // write sql
            ps = con.prepareStatement(sqlUser);

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getAddress());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getOtp());
            ps.setDate(7, Date.valueOf(LocalDate.now()));
            ps.setString(8, user.getImage());
            ps.executeUpdate();

            ps = con.prepareStatement(sqlGetUserId);
            ps.setString(1, user.getEmail());

            ResultSet rsn = ps.executeQuery();
            if (rsn.next()) {
                int userId = rsn.getInt(1);
                System.out.println(userId);
                ps = con.prepareStatement(sqlStaff);
                ps.setInt(1, userId);
                ps.executeUpdate();
            }

        } catch (Exception e) {
        }

    }

    public void addShippers(Users user) {
        String sqlUser = "insert into users(fullName,address,phone,email,password,otp,createdAt,image) values(?,?,?,?,?,?,?,?)";
        String sqlShipper = "INSERT INTO shippers (userId) VALUES (?);";
        String sqlGetUserId = "SELECT u.userId FROM users u WHERE email = ?";

        try {
            // connect
            con = new ConnectDB().condb();
            // write sql
            ps = con.prepareStatement(sqlUser);

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getAddress());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getOtp());
            ps.setDate(7, Date.valueOf(LocalDate.now()));
            ps.setString(8, user.getImage());
            ps.executeUpdate();

            ps = con.prepareStatement(sqlGetUserId);
            ps.setString(1, user.getEmail());

            ResultSet rsn = ps.executeQuery();
            if (rsn.next()) {
                int userId = rsn.getInt(1);
                System.out.println(userId);
                ps = con.prepareStatement(sqlShipper);
                ps.setInt(1, userId);
                ps.executeUpdate();
            }

        } catch (Exception e) {
        }

    }

    public void addManager(Users user) {
        String sqlUser = "insert into users(fullName,address,phone,email,password,otp,createdAt,image) values(?,?,?,?,?,?,?,?)";
        String sqlManager = "INSERT INTO managers (userId) VALUES (?);";
        String sqlGetUserId = "SELECT u.userId FROM users u WHERE email = ?";

        try {
            // connect
            con = new ConnectDB().condb();
            // write sql
            ps = con.prepareStatement(sqlUser);

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getAddress());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getOtp());
            ps.setDate(7, Date.valueOf(LocalDate.now()));
            ps.setString(8, user.getImage());
            ps.executeUpdate();

            ps = con.prepareStatement(sqlGetUserId);
            ps.setString(1, user.getEmail());

            ResultSet rsn = ps.executeQuery();
            if (rsn.next()) {
                int userId = rsn.getInt(1);
                System.out.println(userId);
                ps = con.prepareStatement(sqlManager);
                ps.setInt(1, userId);
                ps.executeUpdate();
            }

        } catch (Exception e) {
        }

    }

    // Delete Actor   
    public void deleteCustomer(int id) {
        String sqlUser = "DELETE FROM users WHERE userId=?";
        String sqlOrder = "DELETE FROM orders WHERE userId=?";
        try {
            // connect
            con = new ConnectDB().condb();
            // sql
            ps = con.prepareStatement(sqlOrder);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps = con.prepareStatement(sqlUser);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
        }

    }

    public void deleteShipper(int id) {
        String sqlUser = "DELETE FROM users WHERE userId=?";
        try {
            // connect
            con = new ConnectDB().condb();
            // sql

            ps = con.prepareStatement(sqlUser);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
        }

    }

    public void deleteStaff(int id) {
        String sqlUser = "DELETE FROM users WHERE userId=?";
        try {
            // connect
            con = new ConnectDB().condb();
            // sql

            ps = con.prepareStatement(sqlUser);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
        }

    }

    // Update Actor 
    public Users updateUser(Users u) {
        String sql = "UPDATE users SET fullName = ?,address = ?,phone = ?,password = ? ,image = ? WHERE userId = ?;";
        try {
            // connect
            con = new ConnectDB().condb();
            // sql
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getFullName());
            ps.setString(2, u.getAddress());
            ps.setString(3, u.getPhone());
            ps.setString(4, u.getPassword());
            ps.setString(5, u.getImage());
            ps.setInt(6, u.getUserId());

            ps.executeUpdate();
        } catch (Exception e) {
        }

        return u;
    }

    // Find Actor (use code)
    public Users findUserById(int id) {

        String sql = "select * FROM users WHERE userId=?";

        try {
            // connect
            con = new ConnectDB().condb();
            // sql
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Users(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getDate(8),
                        rs.getDate(9),
                        rs.getString(10)
                );

            }
        } catch (Exception e) {
        }
        return null;
    }

}
