package org.ds.model.machine;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class WashingMachine {
    private Long id;
    private State state;
    private Calendar calendar;
    private Long duration;

    public WashingMachine(Long id, State state, Calendar calendar, Long duration) {
        this.id = id;
        this.state = state;
        this.calendar = calendar;
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

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
    public boolean isFinish() {
        return new GregorianCalendar().getTime().getTime() -
                calendar.getTime().getTime() >= duration * 1000 || state == State.FREE;
    }
}
