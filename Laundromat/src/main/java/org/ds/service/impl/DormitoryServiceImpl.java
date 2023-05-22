package org.ds.service.impl;

import org.ds.model.Dormitory;
import org.ds.model.machine.WashingMachine;
import org.ds.repository.DormitoryRepository;
import org.ds.service.DormitoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с объектами Dormitory
 */
@Service
public class DormitoryServiceImpl implements DormitoryService {
    /**
     * Хранилище объектов
     */
    private final DormitoryRepository dormitoryRepository;

    public DormitoryServiceImpl(DormitoryRepository dormitoryRepository) {
        this.dormitoryRepository = dormitoryRepository;
    }

    @Override
    public Optional<Dormitory> addDormitory(Dormitory dormitory) {
        return Optional.of(dormitoryRepository.save(dormitory));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Dormitory> dormitory = dormitoryRepository.findById(id);
        if (dormitory.isPresent()) {
            dormitoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Dormitory> getById(Long id) {
        return dormitoryRepository.findById(id);
    }

    @Override
    public Optional<Dormitory> update(Dormitory dormitory) {
        return Optional.of(dormitoryRepository.save(dormitory));
    }

    @Override
    public Optional<WashingMachine> update(Long dormitoryId, WashingMachine washingMachine) {
        Optional<Dormitory> dormitory = dormitoryRepository.findById(dormitoryId);
        if (dormitory.isPresent() && dormitory.get().removeMachine(washingMachine.getId())) {
            dormitory.get().addWashingMachine(washingMachine);
            dormitoryRepository.save(dormitory.get());
            return Optional.of(washingMachine);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Dormitory>> getAll() {
        return Optional.of((List<Dormitory>) dormitoryRepository.findAll());
    }

    @Override
    public Optional<WashingMachine> addWashingMachine(Long dormitoryId, WashingMachine washingMachine) {
        Optional<Dormitory> optionalDormitory = getById(dormitoryId);
        if (optionalDormitory.isPresent()) {
            Dormitory dormitory = optionalDormitory.get();
            dormitory.addWashingMachine(washingMachine);
            dormitoryRepository.save(dormitory);
            return Optional.of(washingMachine);
        }
        return Optional.empty();
    }
}
