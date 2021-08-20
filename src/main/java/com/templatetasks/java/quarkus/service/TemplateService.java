package com.templatetasks.java.quarkus.service;

import com.templatetasks.java.quarkus.event.NewJediMemberEvent;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 20.08.2021 16:26
 */
public interface TemplateService {

    void report(NewJediMemberEvent event);
}
