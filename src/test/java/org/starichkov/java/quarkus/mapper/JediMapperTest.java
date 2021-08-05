package org.starichkov.java.quarkus.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.starichkov.java.quarkus.domain.JediEntity;
import org.starichkov.java.quarkus.dto.Jedi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 05.08.2021 18:35
 */
class JediMapperTest {

    private final JediMapper mapper = Mappers.getMapper(JediMapper.class);

    @DisplayName("Test map of null should return null")
    @Test
    void mapNull() {
        assertNull(mapper.map(null));
    }

    @DisplayName("Test map of empty entity should return empty dto")
    @Test
    void mapEmpty() {
        Jedi jedi = mapper.map(new JediEntity());
        assertNotNull(jedi);
        assertNull(jedi.getName());
        assertNull(jedi.getTitle());
    }

    @DisplayName("Test map of valid entity should return valid dto")
    @Test
    void map() {
        JediEntity jediEntity = new JediEntity();
        jediEntity.setName("A");
        jediEntity.setTitle("B");

        Jedi jedi = mapper.map(jediEntity);
        assertNotNull(jedi);

        assertEquals(jediEntity.getName(), jedi.getName());
        assertEquals(jediEntity.getTitle(), jedi.getTitle());
    }
}