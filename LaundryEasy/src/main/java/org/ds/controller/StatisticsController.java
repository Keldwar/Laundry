package org.ds.controller;

import org.ds.model.stat.Statistics;
import org.ds.service.StatisticsService;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер, предоставляющий доступ к статистике.
 */
@RestController
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     * Получение загруженности каждого дня недели по времени за определённый период и для конкретного общежития.
     */
    @GetMapping("/statistics/dailyWorkload")
    public Statistics getDailyWorkload(@RequestParam(value = "dormitoryId", required = false) Long dormitoryId,
                                       @RequestParam(value = "fromTime", required = false) Long fromTime,
                                       @RequestParam(value = "toTime", required = false) Long toTime) {

        return statisticsService.getDailyWorkload(dormitoryId, fromTime, toTime);
    }
}
