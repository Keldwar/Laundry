package org.ds.controller;

import org.ds.model.Dormitory;
import org.ds.model.machine.WashingMachine;
import org.ds.service.DormitoryService;
import org.ds.service.WashingMachineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер, в котором реализована логика работы запросов
 */
@RestController
public class MachineController {
    private final DormitoryService dormitoryService;
    private final WashingMachineService washingMachineService;

    public MachineController(DormitoryService dormitoryService, WashingMachineService washingMachineService) {
        this.dormitoryService = dormitoryService;
        this.washingMachineService = washingMachineService;
    }

    @GetMapping("/dormitories")
    public ResponseEntity<List<Dormitory>> readAll() {
        Optional<List<Dormitory>> dormitories = dormitoryService.getAll();

        return dormitories.map(dormitoryList -> new ResponseEntity<>(dormitoryList, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/dormitory/{dormitoryId}")
    public ResponseEntity<Dormitory> readDormitory(@PathVariable Long dormitoryId) {
        Optional<Dormitory> optionalDormitory = dormitoryService.getById(dormitoryId);

        return optionalDormitory.map(dormitory -> new ResponseEntity<>(dormitory, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/machine/{machineId}")
    public ResponseEntity<WashingMachine> readMachine(@PathVariable Long machineId) {
        Optional<WashingMachine> optionalMachine = washingMachineService.getById(machineId);

        return optionalMachine.map(machine -> new ResponseEntity<>(machine, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/dormitory/{dormitoryId}/addMachine")
    public ResponseEntity<WashingMachine> create(@PathVariable Long dormitoryId, @RequestBody WashingMachine washingMachine) {
        Optional<WashingMachine> optionalMachine = dormitoryService.addWashingMachine(dormitoryId, washingMachine);

        return optionalMachine.map(machine -> new ResponseEntity<>(machine, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/dormitory")
    public ResponseEntity<Dormitory> create(@RequestBody Dormitory dormitory) {
        if (dormitory.getName() == null || dormitory.getName().isEmpty()) {
            System.out.println("Error");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Dormitory> optionalDormitory = dormitoryService.addDormitory(dormitory);

        return optionalDormitory.map(dormitory1 -> new ResponseEntity<>(dormitory1, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/dormitory/{dormitoryId}/machine")
    public ResponseEntity<WashingMachine> update(@PathVariable Long dormitoryId, @RequestBody WashingMachine washingMachine) {
        Optional<WashingMachine> optionalMachine = dormitoryService.update(dormitoryId, washingMachine);

        return optionalMachine.map(machine -> new ResponseEntity<>(machine, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
    }

    @DeleteMapping("/dormitory/{dormitoryId}")
    public ResponseEntity<?> deleteDormitory(@PathVariable Long dormitoryId) {
        return dormitoryService.delete(dormitoryId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/machine/{machineId}")
    public ResponseEntity<?> deleteMachine(@PathVariable Long machineId) {
        return washingMachineService.delete(machineId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
