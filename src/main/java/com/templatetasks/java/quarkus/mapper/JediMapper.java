package com.templatetasks.java.quarkus.mapper;

import com.templatetasks.java.quarkus.domain.JediEntity;
import com.templatetasks.java.quarkus.dto.Jedi;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 03.08.2021 14:27
 */
@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface JediMapper {

    Jedi map(JediEntity jediEntity);
}
