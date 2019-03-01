package com.epam.grow.jerseyrest.controller;

import com.epam.grow.jerseyrest.domain.Purchase;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("/purchases")
public class PurchaseController {

    @GET
    public List<Purchase> getAllPurchases() {
        return new ArrayList<>();
    }
}
