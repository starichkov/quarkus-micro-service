package com.templatetasks.java.quarkus.service.impl;

import com.templatetasks.java.quarkus.Constants;
import com.templatetasks.java.quarkus.event.NewJediMemberEvent;
import com.templatetasks.java.quarkus.service.JournalService;
import io.micrometer.core.annotation.Counted;
import io.quarkus.vertx.ConsumeEvent;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.*;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 20.08.2021 12:52
 */
@ApplicationScoped
@Slf4j
public class EventsJournalService implements JournalService {

    private final AtomicInteger counter = new AtomicInteger();

    @Counted("jedi.new.counted")
    @ConsumeEvent(Constants.EVENT_ADDRESS_NEW_JEDI)
    @Override
    public void handle(NewJediMemberEvent event) {
        logger.info("JOURNAL RECORD #{}: Welcome our new Jedi Order member! {}", counter.incrementAndGet(), event.getJedi());
    }
}
