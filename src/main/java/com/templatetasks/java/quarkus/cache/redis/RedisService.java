package com.templatetasks.java.quarkus.cache.redis;

import io.quarkus.redis.client.RedisClient;
import io.vertx.redis.client.Response;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 06.08.2021 14:49
 */
@ApplicationScoped
public class RedisService {

    private final RedisClient redisClient;

    @Inject
    public RedisService(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public String get(String key) {
        Response response = redisClient.get(key);
        return response == null ? null : response.toString();
    }

    public String increment(String key) {
        return redisClient.incr(key)
                          .toString();
    }

    public void delete(String key) {
        redisClient.del(List.of(key));
    }

    public void set(String key, int value) {
        redisClient.set(List.of(key, String.valueOf(value)));
    }
}
