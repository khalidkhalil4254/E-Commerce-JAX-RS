package com.example.shoppingcart.DAOImpl;

import com.example.shoppingcart.DAO.CartItemsDao;
import com.example.shoppingcart.Models.CartItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartItemsDaoImpl implements CartItemsDao {
    @Override
    public List<CartItem> getUserShoppingCartItems(String email) {

        List<CartItem> cartData = new ArrayList<>();

        //create database connection and add new user in database table:
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercedb", "root", "admin");
             PreparedStatement preparedStatement = connection.prepareStatement("call getUserShoppingCartItems(?)")) {

            preparedStatement.setString(1, email);

            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                cartData.add(
                        //int string double double
                        new CartItem(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(4),resultSet.getDouble(5),resultSet.getString(3))
                );
            }


        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return cartData;
    }

    @Override
    public int addUserShoppingCartItem(String title, String email) {

        //create database connection and add new user in database table:
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercedb", "root", "admin");
             PreparedStatement preparedStatement = connection.prepareStatement("call addProductToShoppingCart(?,?)")) {

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, email);

            preparedStatement.executeQuery();

            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return 0;
    }

    @Override
    public int editUserShoppingCartItem(String productTitle, String email, int Qnt) {


        //create database connection and add new user in database table:
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercedb", "root", "admin");
             PreparedStatement preparedStatement = connection.prepareStatement("call editProductQntInShoppingCart(?,?,?)")) {

            preparedStatement.setString(1, productTitle);
            preparedStatement.setString(2, email);
            preparedStatement.setInt(3, Qnt);

            preparedStatement.executeQuery();

            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }


        return 0;
    }

    @Override
    public int removeShoppingCartItem(String productTitle, String email) {


        //create database connection and add new user in database table:
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercedb", "root", "admin");
             PreparedStatement preparedStatement = connection.prepareStatement("call removeUserShoppingCartItem(?,?)")) {

            preparedStatement.setString(1, productTitle);
            preparedStatement.setString(2, email);

            preparedStatement.executeQuery();

            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return 0;
    }
}
