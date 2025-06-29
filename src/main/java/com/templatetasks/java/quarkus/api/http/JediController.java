package com.templatetasks.java.quarkus.api.http;

import com.templatetasks.java.quarkus.Constants;
import com.templatetasks.java.quarkus.dto.Jedi;
import com.templatetasks.java.quarkus.service.EventService;
import com.templatetasks.java.quarkus.service.JediService;
import lombok.extern.slf4j.Slf4j;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 02.08.2021 18:07
 */
@Path(Constants.JEDI_ENDPOINT)
@Slf4j
public class JediController {

    @Inject
    JediService jediService;

    @Inject
    EventService eventService;

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJedi(@PathParam("name") String name) {
        logger.info("Get Jedi request received, processing");
        Jedi jedi = jediService.getJedi(name);
        if (jedi == null) {
            logger.info("Nothing found by name '{}'", name);
            return Response.status(Response.Status.NOT_FOUND)
                           .build();
        } else {
            logger.info("Jedi found: {}", jedi);
            return Response.ok(jedi)
                           .build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addJedi(@QueryParam("name") String name, @QueryParam("title") String title) {
        logger.info("New Jedi request received, processing");
        Jedi jedi = jediService.addJedi(name, title);
        eventService.send(jedi);
        return Response.ok(jedi)
                       .build();
    }

    @DELETE
    @Path("/{name}")
    public Response removeJedi(@PathParam("name") String name) {
        logger.info("Jedi Removal request received, processing");
        int removed = jediService.removeJedi(name);
        return Response.ok(removed)
                       .build();
    }
}
