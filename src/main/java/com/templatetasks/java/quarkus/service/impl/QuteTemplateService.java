package com.templatetasks.java.quarkus.service.impl;

import com.templatetasks.java.quarkus.Constants;
import com.templatetasks.java.quarkus.event.NewJediMemberEvent;
import com.templatetasks.java.quarkus.service.TemplateService;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.vertx.ConsumeEvent;
import lombok.extern.slf4j.Slf4j;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 20.08.2021 16:26
 */
@ApplicationScoped
@Slf4j
public class QuteTemplateService implements TemplateService {

    @Location("report.html")
    Template reportTemplate;

    @ConsumeEvent(Constants.EVENT_ADDRESS_NEW_JEDI)
    @Override
    public void report(NewJediMemberEvent event) {
        String report = reportTemplate.data("jedi", event.jedi())
                                      .render();
        logger.info(report);
    }
}
