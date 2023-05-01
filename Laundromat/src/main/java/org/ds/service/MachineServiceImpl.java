package org.ds.service;

import org.ds.Generator;
import org.ds.MachinesRepository;
import org.ds.model.Dormitory;
import org.ds.model.machine.WashingMachine;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {
    private static final Generator generator = new Generator();
    private static final MachinesRepository machineRepository = generator.getMachinesRepository();
    @Override
    public void create(Long dormitoryId, WashingMachine washingMachine) {
        machineRepository.getDormitoryById(dormitoryId).addMachine(washingMachine);
    }

    @Override
    public List<WashingMachine> readAll() {
        List<WashingMachine> result = new ArrayList<>();
        for (Dormitory dormitory : machineRepository.getRepository().values()) {
            result.addAll(dormitory.getMachines());
        }
        return result;
    }

    @Override
    public WashingMachine read(Long dormitoryId, Long machineId) {
        return machineRepository.getMachineByIds(dormitoryId, machineId);
    }

    @Override
    public List<WashingMachine> readByDormitoryId(Long dormitoryId) {
        return machineRepository.getDormitoryById(dormitoryId).getMachines();
    }

    @Override
    public boolean update(WashingMachine washingMachine, Long dormitoryId) {
        if (machineRepository.getDormitoryById(dormitoryId).getMachine(washingMachine.getId()) != null)
        {
            machineRepository.getDormitoryById(dormitoryId).setMachine(washingMachine);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long dormitoryId, Long machineId) {
        return machineRepository.getDormitoryById(dormitoryId).removeMachine(machineId) != null;
    }

    @Override
    public boolean deleteDormitory(Long dormitoryId) {
        return false;
    }
}
