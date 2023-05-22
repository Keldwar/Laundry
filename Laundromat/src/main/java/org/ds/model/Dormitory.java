package org.ds.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.ds.model.machine.WashingMachine;

import java.util.*;

/**
 * Класс Общага - хранит инфомарцию о общаге и стиральных машинах в общаге.
 * Хранится в базе данных таблице dormitories.
 */
@Entity
@Table(name = "dormitories")
public class Dormitory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "dormitory_id")
    private List<WashingMachine> machines;

    public Dormitory() {
    }

    public Dormitory(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WashingMachine> getMachines() {
        return machines;
    }

    public void setMachines(List<WashingMachine> machines) {
        this.machines = machines;
    }

    public void addWashingMachine(WashingMachine washingMachine) {
        machines.add(washingMachine);
    }

    public boolean removeMachine(Long id) {
        return machines.removeIf(k -> k.getId().equals(id));
    }
}
