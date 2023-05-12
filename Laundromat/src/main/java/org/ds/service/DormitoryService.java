package org.ds.service;

import org.ds.model.Dormitory;
import org.ds.model.machine.WashingMachine;

import java.util.Optional;

/**
 * Интерфейс сервиса для работы с объектами Dormitory
 */
public interface DormitoryService {
    /**
     * добавление новой общаги в базу
     *
     * @param dormitory - общага
     * @return объект общаги
     */
    Optional<Dormitory> addDormitory(Dormitory dormitory);

    /**
     * Удаление общаги из базы
     *
     * @param id - идентификатор общаги
     */
    void delete(Long id);

    /**
     * Возвращает объект по идентификатору
     *
     * @param id - идентификатор общаги
     * @return объект общаги
     */
    Optional<Dormitory> getById(Long id);

    /**
     * Обновляет данные в базе
     *
     * @param dormitory - объект общаги
     * @return объект общаги
     */
    Optional<Dormitory> update(Dormitory dormitory);

    /**
     * @return возвращает все общаги
     */
    Optional<Iterable<Dormitory>> getAll();

    /**
     * Добавляет объект машины в базу данных
     *
     * @param dormitoryId    - идентификатор общаги
     * @param washingMachine - объект стиральной машины
     */
    void addWashingMachine(Long dormitoryId, WashingMachine washingMachine);
}
