package org.ds.repository;


import org.ds.model.machine.WashingMachine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WashingMachineRepository extends CrudRepository<WashingMachine, Long> {
}
