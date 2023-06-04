package org.ds.controller;

import jakarta.validation.Valid;
import org.ds.model.Dormitory;
import org.ds.model.WashingMachine;
import org.ds.service.DormitoryServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DormitoryController {
    private final DormitoryServiceImpl dormitoryService;

    public DormitoryController(DormitoryServiceImpl dormitoryService) {
        this.dormitoryService = dormitoryService;
    }

    @GetMapping("/dormitories")
    public List<Dormitory> readAll() {
        return dormitoryService.getAll();
    }

    @GetMapping("/dormitories/{dormitoryId}")
    public Dormitory read(@PathVariable Long dormitoryId) {
        return dormitoryService.getById(dormitoryId);
    }

    @PostMapping("/dormitories")
    public void create(@RequestBody @Valid Dormitory dormitory) {
        dormitoryService.add(dormitory);
    }

    @PutMapping("/dormitories/{dormitoryId}")
    public void update(@PathVariable Long dormitoryId, @RequestBody Dormitory dormitory) {
        dormitoryService.update(dormitoryId, dormitory);
    }

    @DeleteMapping("/dormitories/{dormitoryId}")
    public void delete(@PathVariable Long dormitoryId) {
        dormitoryService.delete(dormitoryId);
    }

    @GetMapping("/dormitories/{dormitoryId}/machines")
    public List<WashingMachine> getMachines(@PathVariable Long dormitoryId) {
        return dormitoryService.getById(dormitoryId).getMachines();
    }
}
