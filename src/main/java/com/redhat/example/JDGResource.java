package com.redhat.example;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/jdg")
public class JDGResource {

    @Autowired
    private JDGBean jdgBean;

    @GET
    @Path("{cache-key}")
    @Produces("text/plain")
    public Response getEntry(@PathParam("cache-key") String cacheKey) {
        String entry = jdgBean.get(cacheKey);
        return Response.ok(entry).build();
    }

    @PUT
    @Path("{cache-key}")
    @Consumes("text/plain")
    public void putEntry(@PathParam("cache-key") String cacheKey, String cacheValue) {
        jdgBean.put(cacheKey, cacheValue);
    }
}
