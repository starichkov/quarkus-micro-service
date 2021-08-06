package com.templatetasks.java.quarkus.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 03.08.2021 14:01
 */
@Entity
@Table(name = "jedi_order")
@Data
public class JediEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;
}
