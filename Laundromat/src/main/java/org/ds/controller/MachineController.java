package org.ds.controller;

import org.ds.model.Dormitory;
import org.ds.model.machine.WashingMachine;
import org.ds.service.impl.DormitoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MachineController {
    private final DormitoryServiceImpl dormitoryService;

    public MachineController(DormitoryServiceImpl dormitoryService) {
        this.dormitoryService = dormitoryService;
    }

    @GetMapping("/dormitories")
    public ResponseEntity<List<Dormitory>> readAll() {
        List<Dormitory> dormitories = dormitoryService.getAll();

        return dormitories != null && !dormitories.isEmpty()
                ? new ResponseEntity<>(dormitories, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/dormitory/{dormitoryId}")
    public ResponseEntity<?> read(@PathVariable Long dormitoryId) {
        Dormitory dormitory = dormitoryService.getById(dormitoryId);

        return dormitory != null
                ? new ResponseEntity<>(dormitory, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/dormitory/{dormitoryId}/machine/{machineId}")
    public ResponseEntity<?> read(@PathVariable Long dormitoryId, @PathVariable Long machineId) {
        WashingMachine machine = dormitoryService.getById(dormitoryId).getWashingMachineById(machineId);

        return machine != null
                ? new ResponseEntity<>(machine, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/dormitory/addMachine/{dormitoryId}")
    public ResponseEntity<?> create(@PathVariable Long dormitoryId, @RequestBody WashingMachine washingMachine) {
        dormitoryService.addWashingMachine(dormitoryId, washingMachine);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*@PutMapping("/machines/{dormitoryId}")
    public ResponseEntity<?>
    update(@PathVariable Long dormitoryId,
           @RequestBody WashingMachine washingMachine) {

        //boolean updated = machineService.update(dormitoryId, washingMachine);

        *//*return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);*//*
    }*/

    @DeleteMapping("/dormitory/{dormitoryId}")
    public ResponseEntity<?> delete(@PathVariable Long dormitoryId) {
        dormitoryService.delete(dormitoryId);

        return new ResponseEntity<>(HttpStatus.OK);
               // : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/dormitory/{dormitoryId}/machine/{machineId}")
    public ResponseEntity<?> delete(@PathVariable Long dormitoryId, @PathVariable Long machineId) {
        boolean deleted = dormitoryService.deleteWashingMachine(dormitoryId, machineId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
