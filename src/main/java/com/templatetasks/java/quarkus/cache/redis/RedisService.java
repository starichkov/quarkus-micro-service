package com.templatetasks.java.quarkus.cache.redis;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.KeyCommands;
import io.quarkus.redis.datasource.value.ValueCommands;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 06.08.2021 14:49
 */
@ApplicationScoped
public class RedisService {

    private final KeyCommands<String> keyCommands;
    private final ValueCommands<String, String> valueCommands;

    @Inject
    public RedisService(RedisDataSource ds) {
        this.keyCommands = ds.key();
        this.valueCommands = ds.value(String.class);
    }

    public String get(String key) {
        return valueCommands.get(key);
    }

    public String increment(String key) {
        return String.valueOf(valueCommands.incr(key));
    }

    public void delete(String key) {
        keyCommands.del(key);
    }

    public void set(String key, int value) {
        valueCommands.set(key, String.valueOf(value));
    }
}
