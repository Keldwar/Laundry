package org.ds.model;

/**
 * Класс стиральной машины, хранит {@link WashingMachine#time время начала последней стирки} в UNIX формате,
 * {@link WashingMachine#duration длительность последней стирки} в минутах и {@link WashingMachine#state состояние машины}.
 */
public class WashingMachine {
    private Long id;
    private Long time;
    private Long duration;
    private State state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
