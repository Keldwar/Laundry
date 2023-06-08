package org.ds.service;

import org.ds.model.entities.WashRecord;
import org.ds.model.stat.DayWorkload;
import org.ds.model.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;

/**
 * Реализация {@link StatisticsService}.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final WashRecordService washRecordService;

    @Autowired
    public StatisticsServiceImpl(WashRecordService washRecordService) {
        this.washRecordService = washRecordService;
    }

    @Override
    public Statistics getDailyWorkload(Long dormitoryId, Long fromTime, Long toTime) {
        List<WashRecord> washRecordList = washRecordService.getAllWithConditions(dormitoryId, fromTime, toTime);
        System.out.println(washRecordList);
        Map<DayOfWeek, Map<LocalTime, Integer>> hourWorkloadByDayOfWeek = new HashMap<>();

        for (WashRecord washRecord : washRecordList) {
            DayOfWeek dayOfWeek = LocalDate.ofInstant(washRecord.getStartTime(), ZoneOffset.UTC).getDayOfWeek();
            LocalTime localTime = LocalTime.ofInstant(washRecord.getStartTime(), ZoneOffset.UTC);

            Map<LocalTime, Integer> hourWorkloadInDayOfWeek = hourWorkloadByDayOfWeek.getOrDefault(dayOfWeek, new HashMap<>());

            Integer count = hourWorkloadInDayOfWeek.getOrDefault(localTime, 0);

            hourWorkloadInDayOfWeek.put(localTime, count + 1);
            hourWorkloadByDayOfWeek.put(dayOfWeek, hourWorkloadInDayOfWeek);
        }

        List<DayWorkload> dailyWorkload = new ArrayList<>();
        for (Map.Entry<DayOfWeek, Map<LocalTime, Integer>> entry : hourWorkloadByDayOfWeek.entrySet()) {
            DayWorkload dayWorkload = new DayWorkload(entry.getKey(), entry.getValue());
            dailyWorkload.add(dayWorkload);
        }

        return new Statistics(LocalDateTime.ofEpochSecond(fromTime, 0, ZoneOffset.UTC),
                LocalDateTime.ofEpochSecond(toTime, 0, ZoneOffset.UTC), dailyWorkload);
    }
}
