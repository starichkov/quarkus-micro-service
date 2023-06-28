package com.templatetasks.java.quarkus.cache.redis;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.KeyCommands;
import io.quarkus.redis.datasource.string.StringCommands;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 06.08.2021 15:23
 */
@QuarkusTest
@DisplayName("RedisService methods tests")
class RedisServiceTest {

    @InjectMock
    static RedisDataSource ds;

    @Inject
    RedisService redisService;

    static KeyCommands<String> keyCommands;
    static StringCommands<String, String> stringCommands;

    @BeforeAll
    static void beforeAll() {
        keyCommands = mock(KeyCommands.class);
        stringCommands = mock(StringCommands.class);
    }

    @BeforeEach
    void setUp() {
        when(ds.key()).thenReturn(keyCommands);
        when(ds.string(String.class)).thenReturn(stringCommands);
    }

    @Test
    @DisplayName("get() - verify proper result if no such key found")
    void getNoSuchKey() {
        when(stringCommands.get(eq("some_random_key")))
                .thenReturn(null);

        assertNull(redisService.get("some_random_key"));

        verify(stringCommands).get(eq("some_random_key"));
    }

    @Test
    void get() {
        when(stringCommands.get(eq("a")))
                .thenReturn(String.valueOf(2));

        assertEquals("2", redisService.get("a"));
    }

    @Test
    void increment() {
        when(stringCommands.incr(eq("b")))
                .thenReturn(3L);

        assertEquals("3", redisService.increment("b"));
    }

    @Test
    void set() {
        redisService.set("c", 4);

        verify(stringCommands).set(eq("c"), eq("4"));
    }

    @Test
    void delete() {
        redisService.delete("d");

        verify(keyCommands).del(eq("d"));
    }
}