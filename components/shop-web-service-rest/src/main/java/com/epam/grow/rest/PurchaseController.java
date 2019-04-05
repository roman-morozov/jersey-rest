package com.epam.grow.rest;

import com.epam.grow.core.service.api.business.PurchaseService;
import com.epam.grow.core.service.api.dto.PurchaseDto;
import com.epam.grow.core.service.api.dto.PurchaseItemDto;
import com.epam.grow.core.service.api.dto.PurchaseItemRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

@Path("/purchases")
@Component
public class PurchaseController {

    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PurchaseDto> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPurchaseById(@PathParam("id") Long id) {
        return purchaseService.getPurchaseById(id)
                .map(Response::ok)
                .orElseThrow(() -> new NotFoundException("Item with id " + id + " not found"))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response makePurchase(List<PurchaseItemRequestDto> purchaseDtos) {
        PurchaseDto result = purchaseService.makePurchase(purchaseDtos);
        URI uri = UriBuilder.fromResource(getClass()).path("/{id}").build(result.getId());
        return Response.created(uri).entity(result).build();
    }
}
