package com.templatetasks.java.quarkus.service.impl;

import com.templatetasks.java.quarkus.service.RandomStringService;

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
