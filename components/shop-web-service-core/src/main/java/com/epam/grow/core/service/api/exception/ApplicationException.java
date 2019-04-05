package com.epam.grow.core.service.api.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final int code;

    public ApplicationException(int code, String message) {
        super(message);
        this.code = code;
    }
}
