package org.ds.service;

import org.ds.model.Dormitory;
import org.ds.model.machine.WashingMachine;

import java.util.List;

public interface DormitoryService {
    Dormitory addDormitory(Dormitory dormitory);
    void delete(Long id);
    Dormitory getById(Long id);
    Dormitory update(Dormitory dormitory);
    List<Dormitory> getAll();
    void addWashingMachine(Long dormitoryId, WashingMachine washingMachine);
    boolean deleteWashingMachine(Long dormitoryId, WashingMachine washingMachine);
    boolean deleteWashingMachine(Long dormitoryId, Long machineId);
    boolean updateWashingMachine(Long dormitoryId, WashingMachine washingMachine);
}
