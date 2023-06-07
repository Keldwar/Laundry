package org.ds.model.entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

import java.util.*;

/**
 * Класс Общежитие - хранит инфомарцию о общежитии и стиральных машинах.
 * Хранится в базе данных в таблице dormitories.
 */
@Entity
@Table(name = "dormitories")
public class Dormitory extends AbstractEntity {
    /**
     * Поле имени общежития
     */
    @Column(name = "name")
    @NotNull
    private String name;

    /**
     * Список стиральных машин общежития
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dormitory_id")
    private List<WashingMachine> machines;

    protected Dormitory() {
    }

    public Dormitory(@NotNull String name) {
        this.name = name;
        this.machines = List.of();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WashingMachine> getMachines() {
        return Collections.unmodifiableList(machines);
    }

    public void setMachines(List<WashingMachine> machines) {
        this.machines = machines;
    }

    /**
     * Метод добавление стиральной машины в обещежитие
     *
     * @param washingMachine стиральная машина
     */
    public void addWashingMachine(WashingMachine washingMachine) {
        machines.add(washingMachine);
    }

    /**
     * Метод удаления стиральной машины из общежития
     *
     * @param id идентификатор машины
     * @return true - удалили, false - иначе
     */
    public boolean removeMachine(Long id) {
        return machines.removeIf(k -> k.getId().equals(id));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dormitory dormitory = (Dormitory) o;
        return Objects.equals(name, dormitory.name) && Objects.equals(machines, dormitory.machines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, machines);
    }
}
