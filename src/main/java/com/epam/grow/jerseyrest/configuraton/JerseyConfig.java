package com.epam.grow.jerseyrest.configuraton;

import com.epam.grow.jerseyrest.rest.ItemController;
import com.epam.grow.jerseyrest.rest.exception.GenericExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    public void init() {
        register(ItemController.class);
        register(GenericExceptionMapper.class);
    }
}
