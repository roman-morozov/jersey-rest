package com.epam.grow.jerseyrest.controller.dto;

import lombok.Data;

@Data
public class ErrorInfo {
    private String status;
    private String message;
}
