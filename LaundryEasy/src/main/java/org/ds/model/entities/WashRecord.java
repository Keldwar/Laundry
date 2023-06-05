package org.ds.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

/**
 * Этот класс представляет запись на стирку на конкретной стиральной машине. Хранит информацию о том, на какую
 * стиральную машину осуществлена запись, время начала записи, время конца записи, а также пользователя, который записан
 * стирку.
 */
@Entity
@Table(name = "wash_record")
public class WashRecord {
    @Id
    @SequenceGenerator(
            name = "washrecord_sequence",
            sequenceName = "washrecord_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "washrecord_sequence"
    )
    private Long id;
    @NotNull
    private Integer machineNumber;

    @NotNull
    private Date startTime;
    @NotNull
    private Date endTime;
    @NotNull
    private Long duration;
    @ManyToOne
    private User user;

    public WashRecord() {
    }

    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    public Integer getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(Integer machineNumber) {
        this.machineNumber = machineNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}