package org.ds.service;

import org.ds.model.machine.WashingMachine;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс сервиса для работы с объектами WashingMachine
 */
public interface WashingMachineService {
    /**
     * Удаляет стиральную машину из базы данных
     *
     * @param id - идентификатор машины
     * @return - удалось удалить объект или нет
     */
    boolean delete(Long id);

    /**
     * Возвращает объект по идентификатору
     *
     * @param id - идентификатору
     * @return объект Стиральная машина
     */
    Optional<WashingMachine> getById(Long id);

    /**
     * Обновляет данные стиральной машины
     *
     * @param washingMachine - объект стиральной машины
     * @return объект стиральной машины
     */
    Optional<WashingMachine> update(WashingMachine washingMachine);

    /**
     * @return объекты стиральных машинок
     */
    Optional<List<WashingMachine>> getAll();
}
