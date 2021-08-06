package com.templatetasks.java.quarkus;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 06.08.2021 13:16
 */
@QuarkusMain
public final class Application {

    public static void main(String... args) {
        Quarkus.run(args);
    }
}
