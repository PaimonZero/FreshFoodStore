/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Users;

/**
 *
 * @author DELL
 */
public class DashboardDAO extends DBContext{
    //list thông tin về user
    public ArrayList<Users> findAllInfo(int userId) {
        ArrayList<Users> allInfo = new ArrayList<>();
        //- connect w/Database
        connection = getConnection();
        //- Chuan bi cau lenhj sql
        String sql = "select * from Users where userId = ? ";

        try {
            //- Tao doi tuong prepareStatement
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, userId);
            //- set parameter (optional)
            //- thuc thi cau lenh
            resultSet = preStatement.executeQuery();
            //- tra ve ket qua
            while (resultSet.next()) {
                userId = resultSet.getInt("userId"); 
                String fullName = resultSet.getString("fullName");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String avatar = resultSet.getString("avatar");
                Users user = new Users(userId, fullName, address, phone, email, password, avatar);
                allInfo.add(user);
            }
        } catch (SQLException e) {
            System.out.println("??(DashboardDAO)findAllInfo" + e.getMessage());
        }
        return allInfo;
    }
    public static void main(String[] args) {
        DashboardDAO test1 = new DashboardDAO();
        ArrayList<Users> list1 = test1.findAllInfo(11);
        for (Users item : list1) {
            System.out.print(item.toString());
        }
    }
}
