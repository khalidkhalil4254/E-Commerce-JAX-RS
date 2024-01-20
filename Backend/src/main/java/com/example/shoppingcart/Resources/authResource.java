package com.example.shoppingcart.Resources;


import com.example.shoppingcart.DAOImpl.AuthDaoImpl;
import com.example.shoppingcart.Models.SignIn;
import com.example.shoppingcart.Models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/auth")
public class authResource {


    @POST
    @Path("/signUp")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String signUp(User user){
        AuthDaoImpl dao = new AuthDaoImpl();
        return "{Status: "+dao.signUp(user)+"}";
    }


    @POST
    @Path("/signIn")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String signIn(SignIn user){
        AuthDaoImpl dao = new AuthDaoImpl();
        return "{Status: "+dao.signIn(user.getEmail(), user.getPassword())+"}";
    }



}
