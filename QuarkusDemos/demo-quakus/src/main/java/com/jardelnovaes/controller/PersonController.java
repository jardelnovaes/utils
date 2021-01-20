package com.jardelnovaes.controller;

import com.jardelnovaes.service.PersonService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonController {
    @Inject
    private PersonService service;

    @GET
    @Path("all")
    public Response all() {
        return Response.ok(service.findAll()).build();
    }

    @PUT
    @Path("add")
    public Response add(final String name) {
        return Response.ok(service.add(name)).build();
    }

    @GET
    @Path("/by-name/{name}")
    public Response byName(@PathParam final String name) {
        return Response.ok(service.findByName(name)).build();
    }

    @GET
    @Path("load")
    public Response load() {
        service.add("João");
        service.add("Maria");
        service.add("José");
        service.add("Carlos");
        return Response.ok("carga ok").build();
    }
}