package com.templatetasks.java.quarkus.api.http;

import com.templatetasks.java.quarkus.Constants;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 20.08.2021 13:25
 */
@QuarkusTest
@DisplayName("PlainController endpoints tests")
class PlainControllerTest {

    @Test
    @DisplayName("Endpoint '/plain' test")
    void testPlainEndpoint() {
        String response = when()
                .get(Constants.BASE_ENDPOINT + "/plain")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        UUID responseUuid = UUID.fromString(response);
        assertNotNull(responseUuid);
    }
}