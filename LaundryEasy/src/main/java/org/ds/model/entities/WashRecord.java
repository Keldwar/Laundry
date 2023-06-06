package org.ds.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Objects;

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

    public WashRecord(Integer machineNumber) {
        this.machineNumber = machineNumber;
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

    public void updateWashRecord(WashRecord other) {
        this.machineNumber = other.machineNumber;
        this.startTime = other.startTime;
        this.endTime = other.endTime;
        this.duration = other.duration;
        this.user = other.user;
        this.dormitoryId = other.dormitoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WashRecord that = (WashRecord) o;
        return Objects.equals(machineNumber, that.machineNumber)
                && Objects.equals(startTime, that.startTime)
                && Objects.equals(endTime, that.endTime)
                && Objects.equals(duration, that.duration)
                && Objects.equals(user, that.user)
                && Objects.equals(dormitoryId, that.dormitoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(machineNumber, startTime, endTime, duration, user, dormitoryId);
    }
}