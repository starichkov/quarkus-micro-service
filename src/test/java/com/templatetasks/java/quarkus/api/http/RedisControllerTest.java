package com.templatetasks.java.quarkus.api.http;

import com.templatetasks.java.quarkus.Constants;
import com.templatetasks.java.quarkus.cache.redis.RedisService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 07.08.2021 15:41
 */
@QuarkusTest
@DisplayName("RedisController endpoints tests")
class RedisControllerTest {

    @InjectMock
    RedisService redisService;

    @Test
    @DisplayName("Endpoint 'GET /redis/{key}' test with no item found")
    void getNoSuchKey() {
        when()
                .get(Constants.REDIS_ENDPOINT + "/{key}", "some_random_key")
                .then()
                .statusCode(204);

        verify(redisService).get(eq("some_random_key"));
    }

    @Test
    @DisplayName("Endpoint 'GET /redis/{key}' test")
    void get() {
        Mockito.when(redisService.get(eq("a")))
               .thenReturn("2");

        when()
                .get(Constants.REDIS_ENDPOINT + "/{key}", "a")
                .then()
                .statusCode(200)
                .assertThat()
                .body(equalTo("2"));

        verify(redisService).get(eq("a"));
    }

    @Test
    @DisplayName("Endpoint 'PATCH /redis/{key}' test")
    void increment() {
        Mockito.when(redisService.increment(eq("b")))
               .thenReturn("3");

        when()
                .patch(Constants.REDIS_ENDPOINT + "/{key}", "b")
                .then()
                .statusCode(200)
                .assertThat()
                .body(equalTo("3"));

        verify(redisService).increment(eq("b"));
    }

    @Test
    @DisplayName("Endpoint 'POST /redis/{key}/{value}' test")
    void set() {
        when()
                .post(Constants.REDIS_ENDPOINT + "/{key}/{value}", "c", "4")
                .then()
                .statusCode(204);

        verify(redisService).set(eq("c"), eq(4));
    }

    @Test
    @DisplayName("Endpoint 'DELETE /redis/{key}' test")
    void delete() {
        when()
                .delete(Constants.REDIS_ENDPOINT + "/{key}", "d")
                .then()
                .statusCode(204);

        verify(redisService).delete(eq("d"));

    }
}