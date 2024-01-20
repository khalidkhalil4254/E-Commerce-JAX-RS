package com.example.shoppingcart.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    //@TODO adding on new elements in database:
    private int quantity;
    private String title;
    private double price;
    private double amount;
    private String imageAddr;
}
