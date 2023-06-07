package org.ds;

import org.ds.model.State;
import org.ds.model.entities.WashingMachine;
import org.ds.repository.WashingMachineRepository;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Генератор состояний стиральных машинок
 */
@Component
public class Generator {
    /**
     * Количество состояний стиральной машины
     */
    private static final int amountOfStates = 3;

    /**
     * Максимальное время стирки
     */
    private static final long maxDuration = 60L;

    /**
     * Через какое время планируется выполнение задания
     */
    private static final long delay = 10;

    /**
     * Как часто будет повторяться задание
     */
    private static final long period = 50;

    /**
     * Хранилище стиральных машин
     */
    private final WashingMachineRepository washingMachineRepository;

    /**
     * Объект класса Random
     * Нуден для случайного выбора состояний и длительности стирки
     */
    private final Random random;

    /**
     * Объект класса Date
     * Нужен для получения unix-времени
     */
    private final Date date;

    public Generator(WashingMachineRepository washingMachineRepository) {
        this.washingMachineRepository = washingMachineRepository;
        this.random = new Random();
        this.date = new Date();
    }

    private void generateState(WashingMachine washingMachine) {
        washingMachine.setTime(date.getTime());
        washingMachine.setState(State.values()[random.nextInt(amountOfStates)]);
        washingMachine.setDuration(random.nextLong(maxDuration));
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
        }, delay, period);
    }
}
