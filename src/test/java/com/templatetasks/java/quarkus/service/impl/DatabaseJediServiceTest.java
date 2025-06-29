package com.templatetasks.java.quarkus.service.impl;

import com.templatetasks.java.quarkus.dto.Jedi;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for {@link DatabaseJediService} using Testcontainers.
 * <p>
 * This test uses a real PostgreSQL database running in a Docker container
 * managed by Testcontainers. The database is automatically started before
 * the tests and stopped after the tests.
 */
@QuarkusTest
@Testcontainers
public class DatabaseJediServiceTest {

    @Inject
    DatabaseJediService jediService;

    @Test
    public void testAddJedi() {
        String name = "Obi-Wan";
        String title = "Master";

        Jedi jedi = jediService.addJedi(name, title);

        assertNotNull(jedi);
        assertEquals(name, jedi.name());
        assertEquals(title, jedi.title());
    }

    @Test
    public void testGetJedi() {
        String name = "Anakin";
        String title = "Knight";

        Jedi addedJedi = jediService.addJedi(name, title);
        assertNotNull(addedJedi);
        assertEquals(name, addedJedi.name());
        assertEquals(title, addedJedi.title());

        Jedi retrievedJedi = jediService.getJedi(name);

        assertNotNull(retrievedJedi, "Retrieved Jedi should not be null");
        assertEquals(name, retrievedJedi.name(), "Jedi name should match");
        assertEquals(title, retrievedJedi.title(), "Jedi title should match");
    }

    @Test
    public void testRemoveJedi() {
        String name = "Yoda";
        String title = "Grand Master";
        jediService.addJedi(name, title);

        int removed = jediService.removeJedi(name);

        assertEquals(1, removed);
    }

    @Test
    public void testAfterMigrateScript() {
        // Check if the Jedi records from afterMigrate.sql are present
        Jedi quiGon = jediService.getJedi("Qui-Gonn Jinn");
        assertNotNull(quiGon, "Qui-Gonn Jinn should be present from afterMigrate.sql");
        assertEquals("Jedi Master", quiGon.title(), "Qui-Gonn Jinn should have the title 'Jedi Master'");

        Jedi obiWan = jediService.getJedi("Obi-Wan Kenobi");
        assertNotNull(obiWan, "Obi-Wan Kenobi should be present from afterMigrate.sql");
        assertEquals("Jedi Master", obiWan.title(), "Obi-Wan Kenobi should have the title 'Jedi Master'");

        Jedi anakin = jediService.getJedi("Anakin Skywalker");
        assertNotNull(anakin, "Anakin Skywalker should be present from afterMigrate.sql");
        assertEquals("...", anakin.title(), "Anakin Skywalker should have the title '...'");
    }
}
