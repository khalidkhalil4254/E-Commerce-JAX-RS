package com.example.shoppingcart.Resources;

import com.example.shoppingcart.DAOImpl.ProductDaoImpl;
import com.example.shoppingcart.Models.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/products")
public class productsResource {

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> productRes(){
        return new ProductDaoImpl().getAllProducts();
    }

}
