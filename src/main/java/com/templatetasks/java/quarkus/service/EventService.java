package com.templatetasks.java.quarkus.service;

import com.templatetasks.java.quarkus.dto.Jedi;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 20.08.2021 13:39
 */
public interface EventService {

    void send(Jedi jedi);
}
