package com.templatetasks.java.quarkus.mapper;

import org.mapstruct.Mapper;
import com.templatetasks.java.quarkus.domain.JediEntity;
import com.templatetasks.java.quarkus.dto.Jedi;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 03.08.2021 14:27
 */
@Mapper
public interface JediMapper {

    Jedi map(JediEntity jediEntity);
}
