package com.templatetasks.java.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
                .body("checks", hasSize(1))
                .body("checks[0].name", equalTo("Database connections health check"))
                .body("checks[0].status", equalTo("UP"))
        ;
    }
}
