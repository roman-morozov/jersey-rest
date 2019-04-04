package com.epam.grow.jerseyrest.rest;

import com.epam.grow.jerseyrest.service.api.business.PurchaseService;
import com.epam.grow.jerseyrest.service.api.dto.PurchaseDto;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

@Path("/purchases")
public class PurchaseController {

    private PurchaseService purchaseService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PurchaseDto> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemById(@PathParam("id") Long id) {
        return purchaseService.getPurchaseById(id)
                .map(Response::ok)
                .orElseThrow(() -> new NotFoundException("Item with id " + id + " not found"))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createItem(@Valid PurchaseDto itemDto) {
        PurchaseDto result = purchaseService.makePurchase(itemDto);
        URI uri = UriBuilder.fromResource(getClass()).path("/{id}").build(result.getId());
        return Response.created(uri).entity(result).build();
    }
}
