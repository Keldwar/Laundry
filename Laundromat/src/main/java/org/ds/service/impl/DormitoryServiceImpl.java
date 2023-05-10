package org.ds.service.impl;

import org.ds.Generator;
import org.ds.model.Dormitory;
import org.ds.model.machine.WashingMachine;
import org.ds.repository.DormitoryRepository;
import org.ds.service.DormitoryService;
import org.ds.service.WashingMachineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormitoryServiceImpl implements DormitoryService {
    private final DormitoryRepository dormitoryRepository;
    private final WashingMachineService washingMachineService;

    public DormitoryServiceImpl(DormitoryRepository dormitoryRepository, WashingMachineService washingMachineService) {
        Generator generator = new Generator(dormitoryRepository);
        generator.run();
        this.dormitoryRepository = dormitoryRepository;
        this.washingMachineService = washingMachineService;
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

        Dormitory dormitory = getById(dormitoryId);
        dormitory.addWashingMachine(washingMachine);
        dormitoryRepository.save(dormitory);
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
