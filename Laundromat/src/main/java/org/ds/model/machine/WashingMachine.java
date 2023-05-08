package org.ds.model.machine;

import jakarta.persistence.*;
import org.ds.model.Dormitory;

import java.util.GregorianCalendar;

@Entity
@Table(name = "machines")
public class WashingMachine {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "state")
    private State state;
    @Column(name = "lastTime")
    private Long time;
    @Column(name = "duration")
    private Long duration;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dormitory_id")
    private Dormitory dormitory;

    public WashingMachine(Long id, State state, Long time, Long duration) {
        this.id = id;
        this.state = state;
        this.time = time;
        this.duration = duration;
    }

    public WashingMachine() {

    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
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
        return new GregorianCalendar().getTime().getTime() -
                time >= duration * 1000 || state == State.FREE;
    }
}
