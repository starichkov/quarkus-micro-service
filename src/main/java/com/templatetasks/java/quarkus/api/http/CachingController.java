package com.templatetasks.java.quarkus.api.http;

import com.templatetasks.java.quarkus.Constants;
import com.templatetasks.java.quarkus.cache.CachingService;
import lombok.extern.slf4j.Slf4j;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 07.08.2021 15:34
 */
@Path(Constants.CACHE_ENDPOINT)
@Slf4j
public class CachingController {

    private final CachingService cachingService;

    @Inject
    public CachingController(CachingService cachingService) {
        this.cachingService = cachingService;
    }

    @GET
    @Path("/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response get(@PathParam("key") String key) {
        logger.info("Processing Valkey 'get' request for '{}'", key);
        String result = cachingService.get(key);
        logger.info("Valkey request processed: {}", result);
        return (result == null ? Response.noContent() : Response.ok(result)).build();
    }

    @PATCH
    @Path("/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response increment(@PathParam("key") String key) {
        logger.info("Processing Valkey 'incr' request for '{}'", key);
        String result = cachingService.increment(key);
        logger.info("Valkey request processed: {}", result);
        return (result == null ? Response.notModified() : Response.ok(result)).build();
    }

    @POST
    @Path("/{key}/{value}")
    @Produces(MediaType.TEXT_PLAIN)
    public void set(@PathParam("key") String key, @PathParam("value") int value) {
        logger.info("Processing Valkey 'set' request for key/value '{}/{}'", key, value);
        cachingService.set(key, value);
    }

    @DELETE
    @Path("/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    public void delete(@PathParam("key") String key) {
        logger.info("Processing Valkey 'del' request for key '{}'", key);
        cachingService.delete(key);
    }
}
