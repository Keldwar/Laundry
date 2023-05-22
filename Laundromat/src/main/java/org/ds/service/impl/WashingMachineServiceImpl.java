package org.ds.service.impl;

import org.ds.Generator;
import org.ds.model.machine.WashingMachine;
import org.ds.repository.WashingMachineRepository;
import org.ds.service.WashingMachineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с объектами WashingMachine
 */
@Service
public class WashingMachineServiceImpl implements WashingMachineService {
    /**
     * Хранилище объектов
     */
    private final WashingMachineRepository washingMachineRepository;

    public WashingMachineServiceImpl(WashingMachineRepository washingMachineRepository) {
        Generator generator = new Generator(washingMachineRepository);
        generator.run();
        this.washingMachineRepository = washingMachineRepository;
    }

    @Override
    public boolean delete(Long id) {
        Optional<WashingMachine> washingMachine = washingMachineRepository.findById(id);
        if (washingMachine.isPresent()) {
            washingMachineRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<WashingMachine> getById(Long id) {
        return washingMachineRepository.findById(id);
    }

    @Override
    public Optional<WashingMachine> update(WashingMachine washingMachine) {
        Optional<WashingMachine> machine = washingMachineRepository.findById(washingMachine.getId());
        if (machine.isPresent()) {
            washingMachineRepository.deleteById(washingMachine.getId());
            return Optional.of(washingMachineRepository.save(washingMachine));
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<WashingMachine>> getAll() {
        return Optional.of((List<WashingMachine>) washingMachineRepository.findAll());
    }
}
