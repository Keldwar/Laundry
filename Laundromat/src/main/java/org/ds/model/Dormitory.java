package org.ds.model;

import org.ds.model.machine.WashingMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dormitory {
    private final Long id;
    private final Map<Long, WashingMachine> machines;

    public Dormitory(Long id, Map<Long, WashingMachine> machines) {
        this.id = id;
        this.machines = machines;
    }

    public Dormitory(Long id) {
        this.id = id;
        this.machines = new HashMap<>();
    }
    public Long getId() {
        return id;
    }

    public List<WashingMachine> getMachines() {
        return new ArrayList<WashingMachine>(this.machines.values());
    }
    public WashingMachine getMachine(Long id) {
        return this.machines.get(id);
    }

    public void setMachine(WashingMachine washingMachine) {
        this.machines.put(washingMachine.getId(), washingMachine);
    }

    public void addMachine(WashingMachine machine) {
        this.machines.put(machine.getId(), machine);
    }
    public WashingMachine removeMachine(Long id) {
        return this.machines.remove(id);
    }
}
