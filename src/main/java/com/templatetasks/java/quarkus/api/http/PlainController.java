package com.templatetasks.java.quarkus.api.http;

import com.templatetasks.java.quarkus.Constants;
import com.templatetasks.java.quarkus.service.RandomStringService;
import lombok.extern.slf4j.Slf4j;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 20.08.2021 13:24
 */
@Path(Constants.BASE_ENDPOINT)
@Slf4j
public class PlainController {

    @Inject
    RandomStringService sampleService;

    @GET
    @Path("/plain")
    @Produces(MediaType.TEXT_PLAIN)
    public String testPlain() {
        logger.info("New request received, processing");
        String result = sampleService.randomString();
        logger.info("Request processed: {}", result);
        return result;
    }
}
