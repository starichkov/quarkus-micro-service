package com.templatetasks.java.quarkus.service;

import com.templatetasks.java.quarkus.dto.Jedi;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 02.08.2021 20:29
 */
public interface JediService {

    Jedi addJedi(String name, String title);

    Jedi getJedi(String name);

    int removeJedi(String name);
}
