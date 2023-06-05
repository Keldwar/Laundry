package org.ds.service;

import org.ds.model.Dormitory;

import java.util.List;

/**
 * Интерфейс сервиса для работы с {@link Dormitory общежитиями}. Данный интерфейс предполагает отправку запросов на
 * сервер ландромата.
 */
public interface DormitoryService {
    /**
     * Получение всех общежитий.
     * @return список всех общежитий
     */
    List<Dormitory> getAll();

    /**
     * Получение конкретного общежития по его идентификатору.
     * @param dormitoryId идентификатор общежития
     * @return общежитие
     */
    Dormitory getById(Long dormitoryId);

    /**
     * Добавление нового общежития.
     * @param dormitory общежитие, которое нужно добавить
     */
    void add(Dormitory dormitory);

    /**
     * Обновление общежития. Если общежитие с такими идентификатором отсутствует, то метод ничего не делает.
     * @param dormitoryId идентификатор общежития, которое нужно обновить
     * @param dormitory новое состояние общежития
     */
    void update(Long dormitoryId, Dormitory dormitory);

    /**
     * Удаление общежития. Также удаляет все стиральные машины, связанные с этим общежитием.
     * @param dormitoryId идентификатор общежития, которое нужно удалить
     */
    void delete(Long dormitoryId);
}
