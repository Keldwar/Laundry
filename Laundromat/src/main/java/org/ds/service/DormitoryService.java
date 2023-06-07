package org.ds.service;

import org.ds.model.entities.Dormitory;
import org.ds.model.entities.WashingMachine;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс сервиса для работы с объектами Общежитие
 */
public interface DormitoryService {
    /**
     * Добавление нового объекта Общежитие в базу
     *
     * @param dormitory Общежитие
     * @return добавленный объект Общежитие
     */
    Optional<Dormitory> addDormitory(Dormitory dormitory);

    /**
     * Удаление объекта Общежитие
     *
     * @param id идентификатор
     * @return удалось удалить объект или нет
     */
    boolean delete(Long id);

    /**
     * Возвращает объект по идентификатору
     *
     * @param id идентификатор
     * @return объект Общежитие
     */
    Optional<Dormitory> getById(Long id);

    /**
     * Обновляет данные в базе
     *
     * @param dormitory объект Общежитие
     */
    void update(Dormitory dormitory);

    /**
     * @return возвращает все общежития
     */
    List<Dormitory> getAll();

    /**
     * Добавляет объект стиральной машины в базу данных
     *
     * @param dormitoryId    идентификатор общежития
     * @param washingMachine объект стиральной машины
     * @return true - при успешном добавление, false - иначе
     */
    boolean addWashingMachine(Long dormitoryId, WashingMachine washingMachine);
}
