package com.example.shoppingcart.DAOImpl;

import com.example.shoppingcart.DAO.ProductDao;
import com.example.shoppingcart.Models.Product;
import com.example.shoppingcart.Models.addProductModel;
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

    @Override
    public addProductModel addProductToStore(addProductModel item) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("call AddNewProduct(?,?,?,?,?)")) {
            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setString(3, item.getImageAddr());
            preparedStatement.setDouble(4, item.getPrice());
            preparedStatement.setInt(5, item.getQuantity());
            preparedStatement.executeQuery();

            return item;

        } catch (SQLException e) {
            System.out.println("Error: " + e);
            // Handle the exception appropriately
        }

        return null;
    }

    @Override
    public int deleteProductFromStore(String title) {
        try(Connection con = dataSource.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement("call deleteProduct(?)")){

            preparedStatement.setString(1, title);

            preparedStatement.executeQuery();

            return 1;
        }catch(Exception err){
            System.out.println("Error deleting product from store: " + err);
        }

        return -1;
    }

    @Override
    public int editProductsInStore(String title, String description, double price, int Qnt) {

        try(Connection con = dataSource.getConnection();
            PreparedStatement preparedStatement= con.prepareStatement("call EditProduct(?,?,?,?)");){

            preparedStatement.setString(1,title);
            preparedStatement.setString(2,description);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, Qnt);

            preparedStatement.executeQuery();

            return 1;
        }catch(Exception err){
            System.out.println("Error Editing Product: "+ err);
        }

        return -1;
    }
}