package org.ds.repository;


import org.ds.model.Dormitory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DormitoryRepository extends CrudRepository<Dormitory, Long> {
}
