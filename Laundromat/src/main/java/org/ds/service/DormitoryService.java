package org.ds.service;

import org.ds.model.Dormitory;
import org.ds.model.machine.WashingMachine;

import java.util.Optional;

public interface DormitoryService {
    Optional<Dormitory> addDormitory(Dormitory dormitory);

    void delete(Long id);

    Optional<Dormitory> getById(Long id);

    Optional<Dormitory> update(Dormitory dormitory);

    Optional<Iterable<Dormitory>> getAll();

    void addWashingMachine(Long dormitoryId, WashingMachine washingMachine);
}
