package org.ds.controller;

import org.ds.model.Dormitory;
import org.ds.model.machine.WashingMachine;
import org.ds.service.WashingMachineService;
import org.ds.service.impl.DormitoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Контроллер, в котором реализована логика работы запросов
 */
@RestController
public class MachineController {
    private final DormitoryServiceImpl dormitoryService;
    private final WashingMachineService washingMachineService;

    public MachineController(DormitoryServiceImpl dormitoryService, WashingMachineService washingMachineService) {
        this.dormitoryService = dormitoryService;
        this.washingMachineService = washingMachineService;
    }

    @GetMapping("/dormitories")
    public ResponseEntity<Iterable<Dormitory>> readAll() {
        Optional<Iterable<Dormitory>> dormitories = dormitoryService.getAll();

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
    public ResponseEntity<?> create(@PathVariable Long dormitoryId, @RequestBody WashingMachine washingMachine) {
        dormitoryService.addWashingMachine(dormitoryId, washingMachine);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/dormitory")
    public ResponseEntity<?> create(@RequestBody Dormitory dormitory) {
        Optional<Dormitory> optionalDormitory = dormitoryService.addDormitory(dormitory);

        return optionalDormitory.map(dormitory1 -> new ResponseEntity<>(HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
    }

    @PutMapping("/machines")
    public ResponseEntity<?> update(@RequestBody WashingMachine washingMachine) {
        Optional<WashingMachine> optionalMachine = washingMachineService.update(washingMachine);

        return optionalMachine.map(machine -> new ResponseEntity<>(HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
    }

    @DeleteMapping("/dormitory/{dormitoryId}")
    public ResponseEntity<?> deleteDormitory(@PathVariable Long dormitoryId) {
        dormitoryService.delete(dormitoryId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("machine/{machineId}")
    public ResponseEntity<?> deleteMachine(@PathVariable Long machineId) {
        washingMachineService.delete(machineId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
