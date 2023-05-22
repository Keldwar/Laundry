package org.ds.service;

import org.ds.model.Dormitory;
import org.ds.model.machine.WashingMachine;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс сервиса для работы с объектами Dormitory
 */
public interface DormitoryService {
    /**
     * Добавление нового объекта Dormitory в базу
     *
     * @param dormitory - Общежитие
     * @return объект Общежитие
     */
    Optional<Dormitory> addDormitory(Dormitory dormitory);
    /**
     * Удаление объекта Общежитие
     *
     * @param id - идентификатор
     * @return удалось удалить объект или нет
     */
    boolean delete(Long id);

    /**
     * Возвращает объект по идентификатору
     *
     * @param id - идентификатор
     * @return объект Общежитие
     */
    Optional<Dormitory> getById(Long id);

    /**
     * Обновляет данные в базе
     *
     * @param dormitory - объект
     * @return объект Общежитие
     */
    Optional<Dormitory> update(Dormitory dormitory);

    /**
     * Обновление данных Стиральной машины
     * @param dormitoryId - идентификатор Общежития
     * @param washingMachine - объект Стиральной машины
     * @return Стиральную машину, которую обновили
     */
    Optional<WashingMachine> update(Long dormitoryId, WashingMachine washingMachine);

    /**
     * @return возвращает все общежития
     */
    Optional<List<Dormitory>> getAll();

    /**
     * Добавляет объект машины в базу данных
     *
     * @param dormitoryId    - идентификатор общаги
     * @param washingMachine - объект стиральной машины
     * @return машину, которую добавили
     */
    Optional<WashingMachine> addWashingMachine(Long dormitoryId, WashingMachine washingMachine);
}
