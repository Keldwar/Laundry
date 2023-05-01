package org.ds;

import org.ds.model.Dormitory;
import org.ds.model.machine.State;
import org.ds.model.machine.WashingMachine;

import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Generator {
    private final MachinesRepository machinesRepository;

    public Generator() {
        this.machinesRepository = new MachinesRepository();
        Timer timer = new Timer("State generation");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                boolean flag = false;
                for (Dormitory dormitory : machinesRepository.getRepository().values()) {
                    for (WashingMachine washingMachine : dormitory.getMachines()) {
                        if (washingMachine.isFinish()) {
                            System.out.println(dormitory.getId() + " " + washingMachine.getId() + " "
                            + washingMachine.getCalendar() + " " + washingMachine.getDuration());
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

    public MachinesRepository getMachinesRepository() {
        return machinesRepository;
    }

    private void generateState(WashingMachine washingMachine) {
        washingMachine.setCalendar(new GregorianCalendar());
        washingMachine.setState(State.getState(new Random().nextInt(3)));
        washingMachine.setDuration(new Random().nextLong(60));
    }
}
