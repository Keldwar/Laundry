package org.ds.service;

import org.ds.model.machine.WashingMachine;

import java.util.List;

public interface WashingMachineService {
    WashingMachine addWashingMachine(WashingMachine washingMachine);
    void delete(Long id);
    WashingMachine getById(Long id);
    WashingMachine update(WashingMachine washingMachine);
    List<WashingMachine> getAll();
}
