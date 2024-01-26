package com.example.shoppingcart.DAO;

import com.example.shoppingcart.Models.Product;
import com.example.shoppingcart.Models.addProductModel;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts();
    addProductModel addProductToStore(addProductModel item);
    int deleteProductFromStore(String title);
    int editProductsInStore(String title, String description, double price, int Qnt);
}
