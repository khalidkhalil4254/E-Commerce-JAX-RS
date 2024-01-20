package com.example.shoppingcart.DAOImpl;

import com.example.shoppingcart.DAO.AuthDao;
import com.example.shoppingcart.Models.User;
import java.sql.*;

public class AuthDaoImpl implements AuthDao {


    @Override
    public int signUp(User user) {
        //create database connection and add new user in database table:
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercedb", "root", "admin");
             PreparedStatement preparedStatement = connection.prepareStatement("call registerNewUser(?,?,?,?,?,?)")) {

            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getAddr());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.executeQuery();

            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return 0;
    }

    @Override
    public String signIn(String email, String password) {
        //create database connection and add new user in database table:
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercedb", "root", "admin");
             PreparedStatement preparedStatement = connection.prepareStatement("call LoginUser(?,?)")) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet=preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                return "Account not found!";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return "null!";
    }
}
