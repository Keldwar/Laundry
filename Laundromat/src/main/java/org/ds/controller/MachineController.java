package org.ds.controller;

import org.ds.model.machine.WashingMachine;
import org.ds.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MachineController {
    private final MachineService machineService;

    @Autowired
    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @GetMapping("/machines")
    public ResponseEntity<List<WashingMachine>> read() {
        List<WashingMachine> machines = machineService.readAll();

        return machines != null && !machines.isEmpty()
                ? new ResponseEntity<>(machines, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
