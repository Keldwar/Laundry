package org.ds.model.stat;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;

/**
 * Загруженность всех стиральных машин в конкретный день недели. Загруженность представлена в виде словаря
 * соответствия времени и количества записей в это время.
 */
public class DayWorkload implements Stat {
    private final DayOfWeek dayOfWeek;
    private final Map<LocalTime, Integer> workload;

    public DayWorkload(DayOfWeek dayOfWeek, Map<LocalTime, Integer> workload) {
        this.dayOfWeek = dayOfWeek;
        this.workload = workload;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Map<LocalTime, Integer> getWorkload() {
        return workload;
    }
}
