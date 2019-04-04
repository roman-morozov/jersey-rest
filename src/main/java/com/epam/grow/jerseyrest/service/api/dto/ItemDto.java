package com.epam.grow.jerseyrest.service.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ItemDto implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String price;
}
