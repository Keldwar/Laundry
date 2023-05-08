package org.ds.service.impl;

import org.ds.Generator;
import org.ds.model.Dormitory;
import org.ds.model.machine.WashingMachine;
import org.ds.repository.DormitoryRepository;
import org.ds.service.DormitoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormitoryServiceImpl implements DormitoryService {
    private final Generator generator;
    private final DormitoryRepository dormitoryRepository;

    public DormitoryServiceImpl(DormitoryRepository dormitoryRepository) {
        this.generator = new Generator(dormitoryRepository);
        this.dormitoryRepository = dormitoryRepository;
    }

    @Override
    public Dormitory addDormitory(Dormitory dormitory) {
        return dormitoryRepository.saveAndFlush(dormitory);
    }

    @Override
    public void delete(Long id) {
        dormitoryRepository.deleteById(id);
    }

    @Override
    public Dormitory getById(Long id) {
        return dormitoryRepository.getReferenceById(id);
    }

    @Override
    public Dormitory update(Dormitory dormitory) {
        return dormitoryRepository.saveAndFlush(dormitory);
    }

    @Override
    public List<Dormitory> getAll() {
        return dormitoryRepository.findAll();
    }

    @Override
    public void addWashingMachine(Long dormitoryId, WashingMachine washingMachine) {
        System.out.println(dormitoryRepository.getReferenceById(dormitoryId));
        //dormitoryRepository.getReferenceById(dormitoryId).addWashingMachine(washingMachine);

        Dormitory dormitory = dormitoryRepository.getReferenceById(dormitoryId);
        dormitory.addWashingMachine(washingMachine);
        dormitoryRepository.saveAndFlush(dormitory);
    }

    @Override
    public boolean deleteWashingMachine(Long dormitoryId, WashingMachine washingMachine) {
        return dormitoryRepository.getReferenceById(dormitoryId).removeWashingMachine(washingMachine);
    }

    @Override
    public boolean deleteWashingMachine(Long dormitoryId, Long machineId) {
        return dormitoryRepository.getReferenceById(dormitoryId).removeWashingMachine(dormitoryId, machineId);
    }

    @Override
    public boolean updateWashingMachine(Long dormitoryId, WashingMachine washingMachine) {
        return false;
    }
}
