package org.ds;

import org.ds.model.Dormitory;
import org.ds.model.machine.State;
import org.ds.model.machine.WashingMachine;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Repository
public class MachinesRepository {
    private Map<Long, Dormitory> repository;

    public MachinesRepository() {
        this.repository = new HashMap<>();
        for (int i = 1; i <= 1; i++) {
            Dormitory dormitory = new Dormitory((long) i);
            for (int j = 0; j < 5; j++) {
                dormitory.addMachine(new WashingMachine(
                        (long) j,
                        State.getState((i + j) % 3),
                        new GregorianCalendar(),
                        new Random().nextLong(60)));
            }
            this.repository.put((long) i, dormitory);
        }
    }

    public Map<Long, Dormitory> getRepository() {
        return this.repository;
    }
    public Dormitory getDormitoryById(Long id) {
        return this.repository.get(id);
    }

    public WashingMachine getMachineByIds(Long dormitoryId, Long machineId) {
        return (WashingMachine) this.repository.get(dormitoryId).
                getMachines().stream().filter(k -> k.getId().equals(machineId));
    }
}
