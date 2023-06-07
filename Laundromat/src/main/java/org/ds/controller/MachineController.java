package org.ds.controller;

import org.ds.exceptions.Response;
import org.ds.model.entities.Dormitory;
import org.ds.model.entities.WashingMachine;
import org.ds.service.DormitoryService;
import org.ds.service.WashingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public MachineController(DormitoryService dormitoryService, WashingMachineService washingMachineService) {
        this.dormitoryService = dormitoryService;
        this.washingMachineService = washingMachineService;
    }

    /**
     * @return все общежития, которые есть в бд
     */
    @GetMapping("/dormitories")
    public ResponseEntity<List<Dormitory>> readAll() {
        List<Dormitory> dormitories = dormitoryService.getAll();

        return dormitories.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(dormitories, HttpStatus.OK);
    }

    /**
     * @param dormitoryId идентификатор общежития
     * @return общежитие
     */
    @GetMapping("/dormitory/{dormitoryId}")
    public ResponseEntity<Dormitory> readDormitory(@PathVariable Long dormitoryId) {
        Optional<Dormitory> optionalDormitory = dormitoryService.getById(dormitoryId);

        return optionalDormitory.map(dormitory -> new ResponseEntity<>(dormitory, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * @param machineId идентификатор стиральной машины
     * @return стиральная машина
     */
    @GetMapping("/machine/{machineId}")
    public ResponseEntity<WashingMachine> readMachine(@PathVariable Long machineId) {
        Optional<WashingMachine> optionalMachine = washingMachineService.getById(machineId);

        return optionalMachine.map(machine -> new ResponseEntity<>(machine, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Добавление стиральной машины в бд
     *
     * @param dormitoryId    идентификатор общежития
     * @param washingMachine стиральная машина
     * @return сообщение о операции
     */
    @PostMapping("/dormitory/{dormitoryId}/addMachine")
    public ResponseEntity<Response> create(@PathVariable Long dormitoryId, @RequestBody WashingMachine washingMachine) {
        return dormitoryService.addWashingMachine(dormitoryId, washingMachine)
                ? new ResponseEntity<>(new Response("Machine added successfully"), HttpStatus.CREATED)
                : new ResponseEntity<>(new Response("Machine not added"), HttpStatus.BAD_REQUEST);
    }

    /**
     * Добавление общежития в бд
     *
     * @param dormitory объект Общежитие
     * @return добавленный объект
     */
    @PostMapping("/dormitory")
    public ResponseEntity<Dormitory> create(@RequestBody Dormitory dormitory) {
        if (dormitory.getName() == null || dormitory.getName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Dormitory> optionalDormitory = dormitoryService.addDormitory(dormitory);
        return optionalDormitory.map(dormitory1 -> new ResponseEntity<>(dormitory1, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    /**
     * Обновление данных о стиральной машине
     *
     * @param washingMachine объект Стиральная Машина
     */
    @PutMapping("/machine")
    public ResponseEntity<Void> update(@RequestBody WashingMachine washingMachine) {
        washingMachineService.update(washingMachine);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Удаление общежития из бд
     *
     * @param dormitoryId идентификатор общежития
     * @return сообщение о операции
     */
    @DeleteMapping("/dormitory/{dormitoryId}")
    public ResponseEntity<Response> deleteDormitory(@PathVariable Long dormitoryId) {
        return dormitoryService.delete(dormitoryId)
                ? new ResponseEntity<>(new Response("Dormitory deleted successfully"), HttpStatus.OK)
                : new ResponseEntity<>(new Response("Dormitory not found"), HttpStatus.NOT_FOUND);
    }

    /**
     * Удаление стиральной машины
     *
     * @param machineId идентификатор стиральной машины
     * @return сообщение о операции
     */
    @DeleteMapping("/machine/{machineId}")
    public ResponseEntity<Response> deleteMachine(@PathVariable Long machineId) {
        return washingMachineService.delete(machineId)
                ? new ResponseEntity<>(new Response("Machine deleted successfully"), HttpStatus.OK)
                : new ResponseEntity<>(new Response("Machine not found"), HttpStatus.NOT_FOUND);
    }
}
