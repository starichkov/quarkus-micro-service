package com.templatetasks.java.quarkus;

import com.templatetasks.java.quarkus.dto.Jedi;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 02.08.2021 20:29
 */
public interface JediService {

    Jedi getJedi(String name);
}
