package org.ds.repository;


import org.ds.model.machine.WashingMachine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий с методами для работы с базой данных объетов WashingMachine
 */
@Repository
public interface WashingMachineRepository extends CrudRepository<WashingMachine, Long> {
}
