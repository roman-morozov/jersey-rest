package com.epam.grow.configuraton;

import com.epam.grow.rest.ItemController;
import com.epam.grow.rest.PurchaseController;
import com.epam.grow.rest.exception.ApplicationExceptionMapper;
import com.epam.grow.rest.exception.GenericExceptionMapper;
import com.epam.grow.rest.exception.WebApplicationExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        registerEndpoints();
        registerProviders();
    }

    private void registerEndpoints() {
        register(ItemController.class);
        register(PurchaseController.class);
    }

    private void registerProviders() {
        register(GenericExceptionMapper.class);
        register(WebApplicationExceptionMapper.class);
        register(ApplicationExceptionMapper.class);
    }
}
