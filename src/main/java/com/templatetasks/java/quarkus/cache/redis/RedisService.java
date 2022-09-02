package com.templatetasks.java.quarkus.cache.redis;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.KeyCommands;
import io.quarkus.redis.datasource.string.StringCommands;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 06.08.2021 14:49
 */
@ApplicationScoped
public class RedisService {

    private final KeyCommands<String> keyCommands;
    private final StringCommands<String, String> stringCommands;

    @Inject
    public RedisService(RedisDataSource ds) {
        this.keyCommands = ds.key();
        this.stringCommands = ds.string(String.class);
    }

    public String get(String key) {
        return stringCommands.get(key);
    }

    public String increment(String key) {
        return String.valueOf(stringCommands.incr(key));
    }

    public void delete(String key) {
        keyCommands.del(key);
    }

    public void set(String key, int value) {
        stringCommands.set(key, String.valueOf(value));
    }
}
