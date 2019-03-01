package com.epam.grow.jerseyrest.controller;

import com.epam.grow.jerseyrest.controller.dto.ErrorInfo;
import com.epam.grow.jerseyrest.controller.dto.ItemDto;
import com.epam.grow.jerseyrest.controller.mapper.ItemMapper;
import com.epam.grow.jerseyrest.domain.Item;
import com.epam.grow.jerseyrest.service.api.ItemService;
import com.epam.grow.jerseyrest.validation.IdMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

@Component
@Path("/items")
public class ItemController {

    private ItemService itemService;
    private ItemMapper itemMapper;

    @Autowired
    public ItemController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemById(@PathParam("id") Long id) {
        return itemService.getItemById(id)
                .map(itemMapper::convertToDto)
                .map(Response::ok)
                .orElseGet(() -> {
                    ErrorInfo errorInfo = buildError(Response.Status.NOT_FOUND, "Item with id " + id + " not found");
                    return Response.status(Response.Status.NOT_FOUND).entity(errorInfo);
                })
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createItem(@Valid ItemDto itemDto) {
        itemDto.setId(null);
        Item item = itemService.save(itemMapper.convertToEntity(itemDto));
        ItemDto result = itemMapper.convertToDto(item);
        URI uri = UriBuilder.fromResource(getClass()).path("/{id}").build(result.getId());
        return Response.created(uri).entity(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @IdMatch
    public Response updateItem(@PathParam("id") Long id, ItemDto itemDto) {
        if (itemService.exists(id)) {
            Item item = itemMapper.convertToEntity(itemDto);
            item.setId(id);
            ItemDto result = itemMapper.convertToDto(itemService.save(item));
            return Response.ok(result).build();
        } else {
            ErrorInfo errorInfo = buildError(Response.Status.NOT_FOUND, "Item with id " + id + " not found");
            return Response.status(Response.Status.NOT_FOUND).entity(errorInfo).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteItem(@PathParam("id") Long id) {
        if (itemService.exists(id)) {
            itemService.delete(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    private ErrorInfo buildError(Response.Status status, String message) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setStatus(status.toString());
        errorInfo.setMessage(message);
        return errorInfo;
    }
}
