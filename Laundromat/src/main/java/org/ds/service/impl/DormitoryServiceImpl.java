package org.ds.service.impl;

import org.ds.model.Dormitory;
import org.ds.model.machine.WashingMachine;
import org.ds.repository.DormitoryRepository;
import org.ds.service.DormitoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DormitoryServiceImpl implements DormitoryService {
    private final DormitoryRepository dormitoryRepository;

    public DormitoryServiceImpl(DormitoryRepository dormitoryRepository) {
        this.dormitoryRepository = dormitoryRepository;
    }

    @Override
    public Optional<Dormitory> addDormitory(Dormitory dormitory) {
        return Optional.of(dormitoryRepository.save(dormitory));
    }

    @Override
    public void delete(Long id) {
        dormitoryRepository.deleteById(id);
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
    public Optional<Iterable<Dormitory>> getAll() {
        return Optional.of(dormitoryRepository.findAll());
    }

    @Override
    public void addWashingMachine(Long dormitoryId, WashingMachine washingMachine) {
        Optional<Dormitory> optionalDormitory = getById(dormitoryId);
        if (optionalDormitory.isPresent()) {
            Dormitory dormitory = optionalDormitory.get();
            dormitory.addWashingMachine(washingMachine);
            dormitoryRepository.save(dormitory);
        }
    }
}
