package org.ds.service;

import org.ds.model.entities.WashRecord;

import java.util.List;

/**
 * Интерфейс сервиса для работы с {@link WashRecord записями на стирку}. Позволяет добавлять, получать, изменять
 * записи на стирку.
 */
public interface WashRecordService {
    /**
     * Получение всех записей на стирку.
     *
     * @return список записей на стирку
     */
    List<WashRecord> getAll();

    /**
     * Получение конкретной записи на стирку по её идентификатору.
     *
     * @param washRecordsId идентификатор стирки
     * @return запись на стирку
     */
    WashRecord getById(Long washRecordsId);

    /**
     * Получение записей на стирку, подходящих под условия.
     *
     * @param dormitoryId идентификатор общежития, записи которого нужно получить
     * @return список записей
     */
    List<WashRecord> getAllWithConditions(Long dormitoryId, Long from, Long to);

    /**
     * Добавление новой записи на стирку.
     *
     * @param washRecord запись на стирку
     */
    void add(WashRecord washRecord);

    /**
     * Обновление записи на стирку.
     *
     * @param washRecordId идентификатор обновляемой записи
     * @param washRecord   новое состояние записи
     */
    void update(Long washRecordId, WashRecord washRecord);

    /**
     * Удаление записи на стирку.
     *
     * @param dormitoryId идентификатор записи, которую нужно удалить
     */
    void delete(Long dormitoryId);
}

