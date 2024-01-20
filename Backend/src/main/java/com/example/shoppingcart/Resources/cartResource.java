package com.example.shoppingcart.Resources;

import com.example.shoppingcart.DAOImpl.CartItemsDaoImpl;
import com.example.shoppingcart.Models.CartItem;
import com.example.shoppingcart.Models.Email;
import com.example.shoppingcart.Models.addItemModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/shopping-cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class cartResource {



    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public int addItemToCart(addItemModel userItem){
        return new CartItemsDaoImpl().addUserShoppingCartItem(userItem.getTitle(),userItem.getEmail());
    }


    @GET
    @Path("/items")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<CartItem> getUserShoppingCartItems(@QueryParam("email") String userEmail) {
        return new CartItemsDaoImpl().getUserShoppingCartItems(userEmail);
    }


    @PUT
    @Path("/edit/{email}/{productTitle}/{Qnt}")
    @Produces(MediaType.APPLICATION_JSON)
    public String editUserShoppingCartItem(@PathParam("productTitle") String title,@PathParam("email") String email,@PathParam("Qnt") int qnt) {
        return (new CartItemsDaoImpl().editUserShoppingCartItem(title,email,qnt))==1?
                "edit success":"edit failure";
    }


    @DELETE
    @Path("/remove/{email}/{productTitle}")
    @Produces(MediaType.APPLICATION_JSON)
    public String removeUserShoppingCartItem(@PathParam("productTitle") String productTitle,@PathParam("email") String email) {
        return (new CartItemsDaoImpl().removeShoppingCartItem(productTitle,email))==1?
                "remove success":"remove failure";
    }


}