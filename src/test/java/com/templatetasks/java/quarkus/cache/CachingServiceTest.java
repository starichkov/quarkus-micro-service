package com.templatetasks.java.quarkus.cache;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.KeyCommands;
import io.quarkus.redis.datasource.value.ValueCommands;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
@DisplayName("CachingService methods tests")
class CachingServiceTest {

    CachingService cachingService;

    @InjectMock
    private RedisDataSource ds;

    private KeyCommands<String> keyCommands;

    private ValueCommands<String, String> valueCommands;

    @BeforeEach
    void setUp() {
        keyCommands = mock(KeyCommands.class);
        valueCommands = mock(ValueCommands.class);

        when(ds.key()).thenReturn(keyCommands);
        when(ds.value(String.class)).thenReturn(valueCommands);

        cachingService = new CachingService(ds);
    }

    @Test
    @DisplayName("get() - verify proper result if no such key found")
    void getNoSuchKey() {
        when(valueCommands.get(eq("some_random_key")))
                .thenReturn(null);

        assertNull(cachingService.get("some_random_key"));

        verify(valueCommands).get(eq("some_random_key"));
    }

    @Test
    void get() {
        when(valueCommands.get(eq("a")))
                .thenReturn(String.valueOf(2));

        assertEquals("2", cachingService.get("a"));
    }

    @Test
    void increment() {
        when(valueCommands.incr(eq("b")))
                .thenReturn(3L);

        assertEquals("3", cachingService.increment("b"));
    }

    @Test
    void set() {
        cachingService.set("c", 4);

        verify(valueCommands).set(eq("c"), eq("4"));
    }

    @Test
    void delete() {
        cachingService.delete("d");

        verify(keyCommands).del(eq("d"));
    }
}