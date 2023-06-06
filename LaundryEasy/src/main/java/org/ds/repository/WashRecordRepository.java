package org.ds.repository;

import org.ds.model.entities.WashRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с базой данных записей на стирку.
 */
@Repository
public interface WashRecordRepository extends CrudRepository<WashRecord, Long> {

}