package com.templatetasks.java.quarkus.api.http;

import com.templatetasks.java.quarkus.Constants;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 20.08.2021 16:47
 */
@QuarkusTest
@DisplayName("JediController endpoints tests")
class TemplateControllerTest {

    @Test
    @DisplayName("Endpoint 'GET /{text}' test with no item found")
    void getNoSuchKey() {
        String html = given()
                .queryParam("text", "May the Force be with you, always...")
                .get(Constants.TEMPLATE_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .asString();
        assertNotNull(html);
        assertTrue(html.contains("<p style=\"font-style: italic\">May the Force be with you, always...</p>\n"));
    }
}