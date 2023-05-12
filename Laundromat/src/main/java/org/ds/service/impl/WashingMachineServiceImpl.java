package org.ds.service.impl;

import org.ds.Generator;
import org.ds.model.machine.WashingMachine;
import org.ds.repository.WashingMachineRepository;
import org.ds.service.WashingMachineService;
import org.springframework.stereotype.Service;

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
    public void delete(Long id) {
        washingMachineRepository.deleteById(id);
    }

    @Override
    public Optional<WashingMachine> getById(Long id) {
        return washingMachineRepository.findById(id);
    }

    @Override
    public Optional<WashingMachine> update(WashingMachine washingMachine) {
        return Optional.of(washingMachineRepository.save(washingMachine));
    }

    @Override
    public Optional<Iterable<WashingMachine>> getAll() {
        return Optional.of(washingMachineRepository.findAll());
    }
}
