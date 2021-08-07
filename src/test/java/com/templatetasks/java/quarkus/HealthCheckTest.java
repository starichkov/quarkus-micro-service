package com.templatetasks.java.quarkus;

import io.quarkus.redis.client.RedisClient;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.vertx.redis.client.impl.types.SimpleStringType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.*;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.*;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 05.08.2021 18:44
 */
@QuarkusTest
class HealthCheckTest {

    @InjectMock
    RedisClient redisClient;

    @BeforeEach
    void setUp() {
        Mockito.when(redisClient.ping(ArgumentMatchers.eq(List.of())))
               .thenReturn(SimpleStringType.OK);
    }

    @Test
    @DisplayName("Endpoint '/q/health' test")
    void testHealthEndpoint() {
        testFullResponse("/q/health");
    }

    @Test
    @DisplayName("Endpoint '/q/health/ready' test")
    void testHealthReadyEndpoint() {
        testFullResponse("/q/health/ready");
    }

    @Test
    @DisplayName("Endpoint '/q/health/live' test")
    void testHealthLiveEndpoint() {
        when()
                .get("/q/health/live")
                .then()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("UP"))
                .body("checks", empty())
        ;
    }

    private static void testFullResponse(String path) {
        when()
                .get(path)
                .then()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("UP"))
                .body("checks", not(empty()))
                .body("checks", not(emptyArray()))
                .body("checks", hasSize(2))
                .body("checks[0].name", equalTo("Database connections health check"))
                .body("checks[0].status", equalTo("UP"))
                .body("checks[1].name", equalTo("Redis connection health check"))
                .body("checks[1].status", equalTo("UP"))
        ;
    }
}
