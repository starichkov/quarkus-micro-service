package com.templatetasks.java.quarkus;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 20.08.2021 12:57
 */
public interface Constants {

    String BASE_ENDPOINT = "/examples/quarkus";

    String JEDI_ENDPOINT = BASE_ENDPOINT + "/jedi";
    String CACHE_ENDPOINT = BASE_ENDPOINT + "/cache";
    String TEMPLATE_ENDPOINT = BASE_ENDPOINT + "/template";

    String EVENT_ADDRESS_NEW_JEDI = "jedi_order";
}
