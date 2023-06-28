package com.templatetasks.java.quarkus.api.http;

import com.templatetasks.java.quarkus.Constants;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import lombok.extern.slf4j.Slf4j;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 20.08.2021 16:40
 */
@Path(Constants.TEMPLATE_ENDPOINT)
@Slf4j
public class TemplateController {

    @Location("paragraph.html")
    Template paragraphTemplate;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(@QueryParam("text") String text) {
        return paragraphTemplate.data("italic_text", text);
    }
}
