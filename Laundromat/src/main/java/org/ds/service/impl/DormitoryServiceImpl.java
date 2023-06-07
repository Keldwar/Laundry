package org.ds.service.impl;

import org.ds.model.entities.Dormitory;
import org.ds.model.entities.WashingMachine;
import org.ds.repository.DormitoryRepository;
import org.ds.service.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Сервис для работы с объектами Dormitory
 */
@Service
public class DormitoryServiceImpl implements DormitoryService {
    /**
     * Хранилище объектов
     */
    private final DormitoryRepository dormitoryRepository;

    @Autowired
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
    public void update(Dormitory dormitory) {
        dormitoryRepository.save(dormitory);
    }

    @Override
    public List<Dormitory> getAll() {
        List<Dormitory> list = (List<Dormitory>) dormitoryRepository.findAll();
        if (list.isEmpty()) {
            return List.of();
        }
        return list;
    }

    @Override
    public boolean addWashingMachine(Long dormitoryId, WashingMachine washingMachine) {
        Optional<Dormitory> optionalDormitory = getById(dormitoryId);
        if (optionalDormitory.isPresent()) {
            Dormitory dormitory = optionalDormitory.get();
            dormitory.addWashingMachine(washingMachine);
            dormitoryRepository.save(dormitory);
            return true;
        }
        return false;
    }
}
