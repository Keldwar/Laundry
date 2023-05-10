package org.ds;

import org.ds.model.Dormitory;
import org.ds.model.machine.State;
import org.ds.model.machine.WashingMachine;
import org.ds.repository.DormitoryRepository;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Generator {
    private final DormitoryRepository dormitoryRepository;

    public Generator(DormitoryRepository dormitoryRepository) {
        this.dormitoryRepository = dormitoryRepository;
        dormitoryRepository.saveAndFlush(new Dormitory(1L, "First dormitory", new ArrayList<>()));
    }

    private void generateState(WashingMachine washingMachine) {
        washingMachine.setTime(new Random().nextLong());
        washingMachine.setState(State.getState(new Random().nextInt(3)));
        washingMachine.setDuration(new Random().nextLong(60));
    }

    public void run() {
        Timer timer = new Timer("State generation");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                boolean flag = false;
                for (Dormitory dormitory : Generator.this.dormitoryRepository.findAll()) {
                    for (WashingMachine washingMachine : dormitory.getMachines()) {
                        if (washingMachine.isFinish()) {
                            System.out.println(dormitory.getId() + " " + washingMachine.getId() + " " + washingMachine.getTime() + " " + washingMachine.getDuration());
                            generateState(washingMachine);
                            flag = true;
                        }
                    }
                }
                if (flag) {
                    System.out.println();
                }
            }
        }, 10, 50);
    }
}
