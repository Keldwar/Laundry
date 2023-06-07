package org.ds.service;

import org.ds.model.entities.WashingMachine;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс сервиса для работы с объектами Стиральная машина
 */
public interface WashingMachineService {
    /**
     * Удаляет стиральную машину из базы данных
     *
     * @param id идентификатор машины
     * @return удалось удалить объект или нет
     */
    boolean delete(Long id);

    /**
     * Возвращает объект по идентификатору
     *
     * @param id идентификатору
     * @return объект Стиральная машина
     */
    Optional<WashingMachine> getById(Long id);

    /**
     * Обновляет данные стиральной машины
     *
     * @param washingMachine объект стиральной машины
     */
    void update(WashingMachine washingMachine);

    /**
     * Возвращает все стиральные машины, которые есть в бд
     *
     * @return объекты стиральных машинок
     */
    List<WashingMachine> getAll();
}
