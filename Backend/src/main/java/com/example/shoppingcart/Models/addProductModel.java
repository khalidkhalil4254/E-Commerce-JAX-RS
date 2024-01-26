package com.example.shoppingcart.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.json.bind.annotation.JsonbProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class addProductModel {
    @JsonbProperty("title")
    private String title;
    @JsonbProperty("description")
    private String description;
    @JsonbProperty("imageAddr")
    private String imageAddr;
    @JsonbProperty("price")
    private double price;
    @JsonbProperty("quantity")
    private int quantity;
}