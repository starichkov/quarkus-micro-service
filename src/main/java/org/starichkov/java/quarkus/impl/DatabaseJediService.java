package org.starichkov.java.quarkus.impl;

import org.starichkov.java.quarkus.JediService;
import org.starichkov.java.quarkus.domain.JediEntity;
import org.starichkov.java.quarkus.dto.Jedi;
import org.starichkov.java.quarkus.mapper.JediMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.stream.*;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 03.08.2021 12:03
 */
@ApplicationScoped
@Default
public class DatabaseJediService implements JediService {

    @Inject
    EntityManager em;

    @Inject
    JediMapper mapper;

    @Override
    public Jedi getJedi(String name) {
        TypedQuery<JediEntity> query = em.createQuery("select j from JediEntity j where j.name like :name", JediEntity.class);
        query.setParameter("name", name + '%');

        Stream<JediEntity> results = query.getResultStream();
        JediEntity result = results.findFirst()
                                   .orElse(null);

        return mapper.map(result);
    }
}
