package com.example.shoppingcart.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteProduct {
    @JsonbProperty("title")
    private String title;
}
