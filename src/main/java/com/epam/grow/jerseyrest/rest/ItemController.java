package com.epam.grow.jerseyrest.rest;

import com.epam.grow.jerseyrest.service.api.dto.ItemDto;
import com.epam.grow.jerseyrest.service.api.mapper.ItemMapper;
import com.epam.grow.jerseyrest.service.api.business.ItemService;
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

    @Autowired
    public ItemController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemById(@PathParam("id") Long id) {
        return itemService.getItemById(id)
                .map(Response::ok)
                .orElseThrow(() -> new NotFoundException("Item with id " + id + " not found"))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createItem(@Valid ItemDto itemDto) {
        ItemDto result = itemService.createItem(itemDto);
        URI uri = UriBuilder.fromResource(getClass()).path("/{id}").build(result.getId());
        return Response.created(uri).entity(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateItem(@PathParam("id") Long id, ItemDto itemDto) {
        ItemDto result = itemService.updateItem(id, itemDto);
        return Response.ok(result).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("id") Long id) {
        itemService.delete(id);
        return Response.noContent().build();
    }
}
