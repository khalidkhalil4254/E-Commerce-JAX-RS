package com.example.shoppingcart;


import com.example.shoppingcart.Resources.authResource;
import com.example.shoppingcart.Resources.cartResource;
import com.example.shoppingcart.Resources.productsResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/apiV1")
public class shoppingCartApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(authResource.class);
        resources.add(cartResource.class);
        resources.add(productsResource.class);
        resources.add(CorsFilter.class); // Register your CorsFilter
        return resources;
    }

}