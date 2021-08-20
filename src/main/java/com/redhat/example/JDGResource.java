package com.redhat.example;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/jdg")
public class JDGResource {

    @Autowired
    private JDGBean jdgBean;

    @GET
    @Path("{cache-key}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEntry(@PathParam("cache-key") String cacheKey) {
        String entry = jdgBean.get(cacheKey);
        if(entry == null){
            return Response.status(Status.NO_CONTENT.getStatusCode(), "No entry for provided key: " + cacheKey).build();
        } else {
            return Response.ok(entry).build();
        }
        
    }

    @PUT
    @Path("{cache-key}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response putEntry(@PathParam("cache-key") String cacheKey, String cacheValue) {
        jdgBean.put(cacheKey, cacheValue);
        return Response.status(Status.CREATED.getStatusCode(), "Entry successfully added to the cache with key: " + cacheKey).build();
    }
}
