package org.ds.service.impl;

import org.ds.model.machine.WashingMachine;
import org.ds.repository.WashingMachineRepository;
import org.ds.service.WashingMachineService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WashingMachineServiceImpl implements WashingMachineService {
    private final WashingMachineRepository washingMachineRepository;

    public WashingMachineServiceImpl(WashingMachineRepository washingMachineRepository) {
        this.washingMachineRepository = washingMachineRepository;
    }

    @Override
    public WashingMachine addWashingMachine(WashingMachine washingMachine) {
        return washingMachineRepository.saveAndFlush(washingMachine);
    }

    @Override
    public void delete(Long id) {
        washingMachineRepository.deleteById(id);
    }

    @Override
    public WashingMachine getById(Long id) {
        return washingMachineRepository.getReferenceById(id);
    }

    @Override
    public WashingMachine update(WashingMachine washingMachine) {
        return washingMachineRepository.saveAndFlush(washingMachine);
    }

    @Override
    public List<WashingMachine> getAll() {
        return washingMachineRepository.findAll();
    }
}
