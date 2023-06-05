package org.ds.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

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
    private Instant startTime;
    @NotNull
    private Instant endTime;
    @NotNull
    private Long duration;
    @ManyToOne
    private User user;
    private Long dormitoryId;

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

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
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

    public Long getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Long dormitoryId) {
        this.dormitoryId = dormitoryId;
    }
}