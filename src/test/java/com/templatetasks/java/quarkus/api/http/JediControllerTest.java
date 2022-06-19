package com.templatetasks.java.quarkus.api.http;

import com.templatetasks.java.quarkus.Constants;
import com.templatetasks.java.quarkus.dto.Jedi;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.mapper.ObjectMapperType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 02.08.2021 18:25
 */
@QuarkusTest
@DisplayName("JediController endpoints tests")
class JediControllerTest {

    @Test
    @DisplayName("Endpoint '/jedi' test, Jedi exists")
    void testGetJediEndpoint() {
        Jedi response = when()
                .get(Constants.JEDI_ENDPOINT + "/{name}", "Obi-Wan")
                .then()
                .statusCode(200)
                .extract()
                .as(Jedi.class);

        assertNotNull(response);
        assertEquals("Obi-Wan Kenobi", response.name());
        assertEquals("Jedi Master", response.title());
    }

    @Test
    @DisplayName("Endpoint '/jedi' test, no Jedi found")
    void testGetJediEndpointNothingFound() {
        when()
                .get(Constants.JEDI_ENDPOINT + "/{name}", "Kirk")
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("POST '/jedi' test, creating new Jedi")
    void testAddJediEndpoint() {
        Jedi response = given()
                .queryParam("name", "Joda")
                .queryParam("title", "Uber Jedi")
                .post(Constants.JEDI_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .as(Jedi.class);

        assertNotNull(response);
        assertEquals("Joda", response.name());
        assertEquals("Uber Jedi", response.title());
    }

    @Test
    @DisplayName("DELETE '/jedi' test")
    void testDeleteJediEndpoint() {
        given()
                .queryParam("name", "Sparrow")
                .queryParam("title", "Captain Jack Sparrow")
                .post(Constants.JEDI_ENDPOINT)
                .then()
                .statusCode(200);

        Integer response = when()
                .delete(Constants.JEDI_ENDPOINT + "/{name}", "Sparrow")
                .then()
                .statusCode(200)
                .extract()
                .as(Integer.class, ObjectMapperType.JACKSON_2);

        assertNotNull(response);
        assertEquals(1, response);
    }
}
