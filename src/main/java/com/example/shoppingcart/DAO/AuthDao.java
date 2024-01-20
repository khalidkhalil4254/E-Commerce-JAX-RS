package com.example.shoppingcart.DAO;

import com.example.shoppingcart.Models.User;

public interface AuthDao {
    int signUp(User user);
    String signIn(String email, String password);
}
