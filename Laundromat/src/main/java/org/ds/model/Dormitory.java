package org.ds.model;

import jakarta.persistence.*;
import org.ds.model.machine.WashingMachine;

import java.util.*;

@Entity
@Table(name = "dormitories")
public class Dormitory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "dormitory_id")
    private List<WashingMachine> machines;

    public Dormitory(Long id, String name, List<WashingMachine> machines) {
        this.id = id;
        this.name = name;
        this.machines = machines;
    }

    public Dormitory() {
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

    public WashingMachine getWashingMachineById(Long machineId) {
        return machines.stream()
                .filter(washingMachine -> washingMachine.getId().equals(machineId))
                .findFirst()
                .get();
    }

    public void addWashingMachine(WashingMachine washingMachine) {
        machines.add(washingMachine);
    }

    public boolean removeWashingMachine(WashingMachine washingMachine) {
        return machines.remove(washingMachine);
    }

    public boolean removeWashingMachine(Long dormitoryId, Long machineId) {
        return machines.removeIf(washingMachine -> washingMachine.getId().equals(machineId));
    }
}
