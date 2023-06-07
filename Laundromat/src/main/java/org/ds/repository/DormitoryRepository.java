package org.ds.repository;


import org.ds.model.entities.Dormitory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий с методами для работы с базой данных объектов Общежития
 */
@Repository
public interface DormitoryRepository extends CrudRepository<Dormitory, Long> {
}
