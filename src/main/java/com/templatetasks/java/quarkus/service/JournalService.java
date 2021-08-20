package com.templatetasks.java.quarkus.service;

import com.templatetasks.java.quarkus.event.NewJediMemberEvent;

/**
 * Service for receiving events from {@link io.vertx.core.eventbus.EventBus}
 *
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 20.08.2021 12:50
 */
public interface JournalService {

    void handle(NewJediMemberEvent event);
}
