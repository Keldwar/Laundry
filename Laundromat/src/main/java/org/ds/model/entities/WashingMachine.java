package org.ds.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.ds.model.State;

import java.util.Objects;

/**
 * Класс Стиральная машинка - хранит в себе информацию о конкретной машинке.
 * Хранится в базе данных таблицей machines.
 * Сущность зависит от общаги.
 */
@Entity
@Table(name = "machines")
public class WashingMachine extends AbstractEntity {
    /**
     * Состояние стиральной машины
     */
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;
    /**
     * Время начала стирки
     */
    @Column(name = "last_time")
    private Long time;
    /**
     * Продолжительность стирки
     */
    @Column(name = "duration")
    private Long duration;

    protected WashingMachine() {
    }

    public WashingMachine(State state, Long time, Long duration) {
        this.state = state;
        this.time = time;
        this.duration = duration;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * Метод, который сообщает можем мы начать стирать на этой машине или нет
     *
     * @return true - на этой машине можно стирать, false - нельзя
     */
    public boolean isFinish() {
        long millisecondsInOneSecond = 1000L;
        return state == State.FREE
                || System.currentTimeMillis() - time >= duration * millisecondsInOneSecond;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WashingMachine machine = (WashingMachine) o;
        return state == machine.state && Objects.equals(time, machine.time) && Objects.equals(duration, machine.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, time, duration);
    }
}
