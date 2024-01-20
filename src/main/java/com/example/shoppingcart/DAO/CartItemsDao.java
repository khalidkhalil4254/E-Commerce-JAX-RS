package com.example.shoppingcart.DAO;

import com.example.shoppingcart.Models.CartItem;

import java.util.ArrayList;
import java.util.List;

public interface CartItemsDao {
    List<CartItem> getUserShoppingCartItems(String email);
    int addUserShoppingCartItem(String title, String email);
    int editUserShoppingCartItem(String productTitle, String email, int Qnt);
    int removeShoppingCartItem(String productTitle, String email);
}
