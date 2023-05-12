package org.ds.service;

import org.ds.model.machine.WashingMachine;

import java.util.Optional;

/**
 * Интерфейс сервиса для работы с объектами WashingMachine
 */
public interface WashingMachineService {
    /**
     * Удаляет стиральную машину из базы данных
     *
     * @param id - идентификатор машины
     */
    void delete(Long id);

    /**
     * Возвращает объект по идентификатору
     *
     * @param id - идентификатору
     * @return объект стиральную машину
     */
    Optional<WashingMachine> getById(Long id);

    /**
     * Обновляет данные машинки
     *
     * @param washingMachine - объект стиральной машины
     * @return объект стиральной машины
     */
    Optional<WashingMachine> update(WashingMachine washingMachine);

    /**
     * @return объекты стиральных машинок
     */
    Optional<Iterable<WashingMachine>> getAll();
}
