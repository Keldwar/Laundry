package org.ds.repository;


import org.ds.model.entities.WashingMachine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий с методами для работы с базой данных объетов Стиральной машины
 */
@Repository
public interface WashingMachineRepository extends CrudRepository<WashingMachine, Long> {
}
