package org.ds.model;


import jakarta.validation.constraints.*;

import java.util.List;

/**
 * Класс общежития, хранит название общежития и стиральные машины, находящиеся в этом общежитии.
 */
public class Dormitory {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private List<WashingMachine> machines;

    public Dormitory() {

    }

    public Dormitory(String name, List<WashingMachine> machines) {
        this.name = name;
        this.machines = machines;
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
}
