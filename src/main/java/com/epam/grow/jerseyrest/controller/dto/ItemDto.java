package com.epam.grow.jerseyrest.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ItemDto implements Identifiable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String price;
}
