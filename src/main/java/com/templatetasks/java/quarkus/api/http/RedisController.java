package com.templatetasks.java.quarkus.api.http;

import com.templatetasks.java.quarkus.Constants;
import com.templatetasks.java.quarkus.cache.redis.RedisService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 07.08.2021 15:34
 */
@Path(Constants.REDIS_ENDPOINT)
@Slf4j
public class RedisController {

    private final RedisService redisService;

    @Inject
    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GET
    @Path("/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response get(@PathParam("key") String key) {
        logger.info("Processing Redis 'get' request for '{}'", key);
        String result = redisService.get(key);
        logger.info("Redis request processed: {}", result);
        return (result == null ? Response.noContent() : Response.ok(result)).build();
    }

    @PATCH
    @Path("/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response increment(@PathParam("key") String key) {
        logger.info("Processing Redis 'incr' request for '{}'", key);
        String result = redisService.increment(key);
        logger.info("Redis request processed: {}", result);
        return (result == null ? Response.notModified() : Response.ok(result)).build();
    }

    @POST
    @Path("/{key}/{value}")
    @Produces(MediaType.TEXT_PLAIN)
    public void set(@PathParam("key") String key, @PathParam("value") int value) {
        logger.info("Processing Redis 'set' request for key/value '{}/{}'", key, value);
        redisService.set(key, value);
    }

    @DELETE
    @Path("/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    public void delete(@PathParam("key") String key) {
        logger.info("Processing Redis 'del' request for key '{}'", key);
        redisService.delete(key);
    }
}
