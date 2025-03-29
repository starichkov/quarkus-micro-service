package com.templatetasks.java.quarkus.api.http;

import com.templatetasks.java.quarkus.Constants;
import com.templatetasks.java.quarkus.cache.CachingService;
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
@DisplayName("CachingController endpoints tests")
class CachingControllerTest {

    @InjectMock
    CachingService cachingService;

    @Test
    @DisplayName("Endpoint 'GET /cache/{key}' test with no item found")
    void getNoSuchKey() {
        when()
                .get(Constants.CACHE_ENDPOINT + "/{key}", "some_random_key")
                .then()
                .statusCode(204);

        verify(cachingService).get(eq("some_random_key"));
    }

    @Test
    @DisplayName("Endpoint 'GET /cache/{key}' test")
    void get() {
        Mockito.when(cachingService.get(eq("a")))
               .thenReturn("2");

        when()
                .get(Constants.CACHE_ENDPOINT + "/{key}", "a")
                .then()
                .statusCode(200)
                .assertThat()
                .body(equalTo("2"));

        verify(cachingService).get(eq("a"));
    }

    @Test
    @DisplayName("Endpoint 'PATCH /cache/{key}' test")
    void increment() {
        Mockito.when(cachingService.increment(eq("b")))
               .thenReturn("3");

        when()
                .patch(Constants.CACHE_ENDPOINT + "/{key}", "b")
                .then()
                .statusCode(200)
                .assertThat()
                .body(equalTo("3"));

        verify(cachingService).increment(eq("b"));
    }

    @Test
    @DisplayName("Endpoint 'POST /cache/{key}/{value}' test")
    void set() {
        when()
                .post(Constants.CACHE_ENDPOINT + "/{key}/{value}", "c", "4")
                .then()
                .statusCode(204);

        verify(cachingService).set(eq("c"), eq(4));
    }

    @Test
    @DisplayName("Endpoint 'DELETE /cache/{key}' test")
    void delete() {
        when()
                .delete(Constants.CACHE_ENDPOINT + "/{key}", "d")
                .then()
                .statusCode(204);

        verify(cachingService).delete(eq("d"));

    }
}