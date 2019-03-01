package com.epam.grow.jerseyrest.controller;

import com.epam.grow.jerseyrest.controller.dto.ItemDto;
import com.epam.grow.jerseyrest.controller.mapper.ItemMapper;
import com.epam.grow.jerseyrest.domain.Item;
import com.epam.grow.jerseyrest.service.api.ItemService;
import com.epam.grow.jerseyrest.validation.IdMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createItem(ItemDto itemDto) {
        itemService.save(itemMapper.convertToEntity(itemDto));
        return Response.created(URI.create(String.valueOf(itemDto.getId()))).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @IdMatch
    public Response updateItem(@PathParam("id") Long id, ItemDto itemDto) {
        Item item = itemMapper.convertToEntity(itemDto);
        item.setId(id);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteItem(@PathParam("id") Long id) {
        return Response.noContent().build();
    }
}
