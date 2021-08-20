package com.templatetasks.java.quarkus.service.impl;

import com.templatetasks.java.quarkus.Constants;
import com.templatetasks.java.quarkus.dto.Jedi;
import com.templatetasks.java.quarkus.event.NewJediMemberEvent;
import com.templatetasks.java.quarkus.service.EventService;
import io.vertx.core.eventbus.EventBus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 20.08.2021 13:40
 */
@ApplicationScoped
public class EventBusService implements EventService {

    @Inject
    EventBus eventBus;

    @Override
    public void send(Jedi jedi) {
        eventBus.publish(Constants.EVENT_ADDRESS_NEW_JEDI, new NewJediMemberEvent(jedi));
    }
}
