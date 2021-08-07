package com.templatetasks.java.quarkus.cache.redis;

import io.quarkus.redis.client.RedisClient;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.vertx.redis.client.impl.types.NumberType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 06.08.2021 15:23
 */
@QuarkusTest
@DisplayName("RedisService methods tests")
class RedisServiceTest {

    @InjectMock
    RedisClient redisClient;

    @Inject
    RedisService redisService;

    @Test
    @DisplayName("get() - verify proper result if no such key found")
    void getNoSuchKey() {
        assertNull(redisService.get("some_random_key"));

        verify(redisClient).get(eq("some_random_key"));
    }

    @Test
    void get() {
        Mockito.when(redisClient.get(eq("a")))
               .thenReturn(NumberType.create(2));

        assertEquals("2", redisService.get("a"));
    }

    @Test
    void increment() {
        Mockito.when(redisClient.incr(eq("b")))
               .thenReturn(NumberType.create(3));

        assertEquals("3", redisService.increment("b"));
    }

    @Test
    void set() {
        redisService.set("c", 4);

        verify(redisClient).set(eq(List.of("c", "4")));
    }

    @Test
    void delete() {
        redisService.delete("d");

        verify(redisClient).del(eq(List.of("d")));
    }
}