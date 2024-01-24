package com.example.shoppingcart.DAOImpl;

import com.example.shoppingcart.DAO.ProductDao;
import com.example.shoppingcart.Models.Product;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {


    private static final BasicDataSource dataSource = new BasicDataSource();


    static {
        final String databaseName = "ecommercedb";
        dataSource.setUrl("jdbc:mysql://localhost:3306/"+databaseName);
        dataSource.setUsername("root");
        dataSource.setPassword("admin");

        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(25);
    }


    @Override
    public List<Product> getAllProducts() {
        List<Product> productsData = new ArrayList<>();
        //create database connection and add new user in database table:
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("call getAllProducts()")) {

             ResultSet resultSet=preparedStatement.executeQuery();

             while(resultSet.next()){
                 productsData.add(
                         new Product(resultSet.getInt(1),
                                 resultSet.getString(2),
                                 resultSet.getString(3),
                                 resultSet.getString(4),
                                 resultSet.getString(5),
                                 resultSet.getString(6))
                 );
             }

        } catch (SQLException e) {
            System.out.println("Error: "+ e);
            // Handle the exception appropriately
        }
        return productsData;
    }
}
