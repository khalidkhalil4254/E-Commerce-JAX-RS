package com.example.shoppingcart.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditProduct {
    @JsonbProperty("title")
    private String title;
    @JsonbProperty("des")
    private String des;
    @JsonbProperty("price")
    private double price;
    @JsonbProperty("qnt")
    private int qnt;
}
