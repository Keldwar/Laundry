package org.ds.model.machine;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Класс Стиральная машинка - хранит в себе информацию о конкретной машинке.
 * Хранится в базе данных таблицей machines.
 * Сущность зависит от общаги.
 */
@Entity
@Table(name = "machines")
public class WashingMachine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "state")
    private State state;
    @Column(name = "last_time")
    private Long time;
    @Column(name = "duration")
    private Long duration;

    public WashingMachine() {

    }

    public WashingMachine(Long id, State state, Long time, Long duration) {
        this.id = id;
        this.state = state;
        this.time = time;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isFinish() {
        return new Date().getTime() -
                time >= duration * 1000 || state == State.FREE;
    }
}
