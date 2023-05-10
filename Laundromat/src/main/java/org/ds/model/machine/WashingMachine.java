package org.ds.model.machine;

import jakarta.persistence.*;

import java.util.GregorianCalendar;

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
