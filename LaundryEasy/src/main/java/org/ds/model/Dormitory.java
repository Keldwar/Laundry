package org.ds.model;


import jakarta.validation.constraints.*;

import java.util.List;

public class Dormitory {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private List<WashingMachine> machines;

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
