package org.ds.model.entities;

import jakarta.persistence.*;

/**
 * Настройки для сущностей Общежитие и Стиральная машина
 */
@MappedSuperclass
abstract class AbstractEntity {
    /**
     * Идентификатор сущности
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Long getId() {
        return id;
    }
}
