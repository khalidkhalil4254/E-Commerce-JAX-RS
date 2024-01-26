package com.example.shoppingcart.Resources;

import com.example.shoppingcart.DAOImpl.ProductDaoImpl;
import com.example.shoppingcart.Models.DeleteProduct;
import com.example.shoppingcart.Models.EditProduct;
import com.example.shoppingcart.Models.Product;
import com.example.shoppingcart.Models.addProductModel;
import javax.ws.rs.*;
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

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public addProductModel addProduct(addProductModel item){
        return new ProductDaoImpl().addProductToStore(
                        new addProductModel(
                                item.getTitle(),
                                item.getDescription(),
                                item.getImageAddr(),
                                item.getPrice(),
                                item.getQuantity()
                        )
                );

    }


    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public int deleteProduct(DeleteProduct item){
        return new ProductDaoImpl().deleteProductFromStore(item.getTitle());
    }


    @PUT
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public int editProductInStore(EditProduct item){
        System.out.println("Item: " + item.getTitle() +"\tDescription: " + item.getDes()+ "\tPrice: "+ item.getPrice() + "\tQnt: " + item.getQnt());
        return new ProductDaoImpl().editProductsInStore(item.getTitle(),item.getDes(),item.getPrice(), item.getQnt());
    }


}