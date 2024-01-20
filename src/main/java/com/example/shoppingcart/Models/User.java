package com.example.shoppingcart.Models;

import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @JsonbProperty("firstname")
    private String firstname;
    @JsonbProperty("lastname")
    private String lastname;
    @JsonbProperty("email")
    private String email;
    @JsonbProperty("password")
    private String password;
    @JsonbProperty("addr")
    private String addr;
    @JsonbProperty("phone")
    private String phone;

}
