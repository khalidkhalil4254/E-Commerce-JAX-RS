package com.example.shoppingcart.Models;


import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignIn {
    @JsonbProperty("email")
    private String email;
    @JsonbProperty("password")
    private String password;
}
