package com.example.shoppingcart.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.json.bind.annotation.JsonbProperty;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class addItemModel {
    @JsonbProperty("title")
    private String title;
    @JsonbProperty("email")
    private String email;

}
