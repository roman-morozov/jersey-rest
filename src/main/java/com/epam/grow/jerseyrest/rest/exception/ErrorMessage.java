package com.epam.grow.jerseyrest.rest.exception;

import lombok.Data;

@Data
public class ErrorMessage {
    private String status;
    private String message;
}
