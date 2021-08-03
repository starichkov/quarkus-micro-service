package org.starichkov.java.quarkus.mapper;

import org.mapstruct.Mapper;
import org.starichkov.java.quarkus.domain.JediEntity;
import org.starichkov.java.quarkus.dto.Jedi;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 03.08.2021 14:27
 */
@Mapper
public interface JediMapper {

    Jedi map(JediEntity jediEntity);
}
