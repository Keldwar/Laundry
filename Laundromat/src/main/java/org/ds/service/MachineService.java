package org.ds.service;

import org.ds.model.machine.WashingMachine;

import java.util.List;

public interface MachineService {
    void create(Long dormitoryId, WashingMachine washingMachine);

    List<WashingMachine> readAll();

    WashingMachine read(Long dormitoryId, Long machineId);

    List<WashingMachine> readByDormitoryId(Long dormitoryId);

    boolean update(WashingMachine washingMachine, Long dormitoryId);

    boolean delete(Long dormitoryId, Long machineId);

    boolean deleteDormitory(Long dormitoryId);
}
