package com.templatetasks.java.quarkus.service.impl;

import com.templatetasks.java.quarkus.domain.JediEntity;
import com.templatetasks.java.quarkus.dto.Jedi;
import com.templatetasks.java.quarkus.mapper.JediMapper;
import com.templatetasks.java.quarkus.service.JediService;
import io.micrometer.core.annotation.Timed;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
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

    @Timed("jedi.new.timed")
    @Transactional
    @Override
    public Jedi addJedi(String name, String title) {
        JediEntity jediEntity = new JediEntity(name, title);
        jediEntity = em.merge(jediEntity);
        return mapper.map(jediEntity);
    }

    @Timed("jedi.get.timed")
    @Override
    public Jedi getJedi(String name) {
        TypedQuery<JediEntity> query = em.createQuery("select j from JediEntity j where j.name like :name", JediEntity.class);
        query.setParameter("name", name + '%');

        Stream<JediEntity> results = query.getResultStream();
        JediEntity result = results.findFirst()
                                   .orElse(null);

        return mapper.map(result);
    }

    @Timed("jedi.delete.timed")
    @Transactional
    @Override
    public int removeJedi(String name) {
        return em.createQuery("delete from JediEntity j where j.name = :name")
                 .setParameter("name", name)
                 .executeUpdate();
    }
}
