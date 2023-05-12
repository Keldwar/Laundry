package org.ds;

import org.ds.model.machine.State;
import org.ds.model.machine.WashingMachine;
import org.ds.repository.WashingMachineRepository;

import java.util.*;

/**
 * Генератор состояний стиральных машинок
 */
public class Generator {
    private final WashingMachineRepository washingMachineRepository;

    public Generator(WashingMachineRepository washingMachineRepository) {
        this.washingMachineRepository = washingMachineRepository;
    }

    private void generateState(WashingMachine washingMachine) {
        washingMachine.setTime(new Date().getTime() + new Random().nextLong(60L));
        washingMachine.setState(State.values()[new Random().nextInt(3)]);
        washingMachine.setDuration(new Random().nextLong(60));
    }

    public void run() {
        Timer timer = new Timer("State generation");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Iterable<WashingMachine> iterable = washingMachineRepository.findAll();
                for (WashingMachine washingMachine : iterable) {
                    if (washingMachine.isFinish()) {
                        generateState(washingMachine);
                        washingMachineRepository.save(washingMachine);
                    }
                }
            }
        }, 10, 50);
    }
}
