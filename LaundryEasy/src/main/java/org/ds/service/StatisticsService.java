package org.ds.service;

import org.ds.model.stat.Statistics;

/**
 * Сервис для сбора статистики.
 */
public interface StatisticsService {
    /**
     * Собирает информацию о загруженности прачечной конкретного общежития по дням недели и часам за определённый период.
     *
     * @param dormitoryId идентификатор общежития
     * @param fromTime    начало периода
     * @param toTime      конец периода
     * @return объект Statistics с информацией о загруженности
     */
    Statistics getDailyWorkload(Long dormitoryId, Long fromTime, Long toTime);
}
