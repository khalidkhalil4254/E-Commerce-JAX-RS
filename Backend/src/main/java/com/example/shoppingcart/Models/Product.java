package com.example.shoppingcart.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String title;
    private String description;
    private String imageAddr;
    private String price;
    private String Qnt;
}