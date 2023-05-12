package org.ds.service;

import org.ds.model.machine.WashingMachine;

import java.util.Optional;

public interface WashingMachineService {
    void delete(Long id);

    Optional<WashingMachine> getById(Long id);

    Optional<WashingMachine> update(WashingMachine washingMachine);

    Optional<Iterable<WashingMachine>> getAll();
}
