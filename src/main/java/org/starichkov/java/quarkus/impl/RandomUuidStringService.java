package org.starichkov.java.quarkus.impl;

import org.starichkov.java.quarkus.RandomStringService;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 02.08.2021 18:09
 */
@ApplicationScoped
public class RandomUuidStringService implements RandomStringService {

    @Override
    public String randomString() {
        return UUID.randomUUID()
                   .toString();
    }
}
