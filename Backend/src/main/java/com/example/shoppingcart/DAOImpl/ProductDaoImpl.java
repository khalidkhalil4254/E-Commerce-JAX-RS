package com.example.shoppingcart.DAOImpl;

import com.example.shoppingcart.DAO.ProductDao;
import com.example.shoppingcart.Models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> getAllProducts() {
        List<Product> productsData = new ArrayList<>();
        //create database connection and add new user in database table:
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercedb", "root", "admin");
             PreparedStatement preparedStatement = connection.prepareStatement("call getAllProducts()")) {

             ResultSet resultSet=preparedStatement.executeQuery();

             while(resultSet.next()){
                 productsData.add(
                         new Product(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6))
                 );
             }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return productsData;
    }
}
