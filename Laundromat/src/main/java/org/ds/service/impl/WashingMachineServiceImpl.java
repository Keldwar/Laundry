package org.ds.service.impl;

import org.ds.Generator;
import org.ds.model.entities.WashingMachine;
import org.ds.repository.WashingMachineRepository;
import org.ds.service.WashingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final Generator generator;

    @Autowired
    public WashingMachineServiceImpl(WashingMachineRepository washingMachineRepository, Generator generator) {
        this.generator = generator;
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
    public void update(WashingMachine washingMachine) {
        Optional<WashingMachine> desiredMachine = washingMachineRepository.findById(washingMachine.getId());
        desiredMachine.ifPresent(machine -> washingMachineRepository.save(washingMachine));
    }

    @Override
    public List<WashingMachine> getAll() {
        List<WashingMachine> list = (List<WashingMachine>) washingMachineRepository.findAll();
        if (list.isEmpty()) {
            return List.of();
        }
        return list;
    }
}
