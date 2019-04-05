package com.epam.grow.core.service.api.exception;

public class EntityNotFoundException extends ApplicationException {

    public EntityNotFoundException(String message) {
        super(404, message);
    }
}
