package org.ds.service;

import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.collections4.IterableUtils;
import org.ds.model.entities.WashRecord;
import org.ds.repository.WashRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Реализация {@link WashRecordService}, работает с базой данных.
 */
@Service
public class WashRecordServiceImpl implements WashRecordService {
    private final WashRecordRepository washRecordRepository;

    public WashRecordServiceImpl(WashRecordRepository washRecordRepository) {
        this.washRecordRepository = washRecordRepository;
    }

    /**
     * Получение всех записей на стирку.
     *
     * @return список всех записей
     */
    @Override
    public List<WashRecord> getAll() {
        return IterableUtils.toList(washRecordRepository.findAll());
    }

    /**
     * Получение конкретной записи на стирку из БД по её идентификатору.
     *
     * @param washRecordsId идентификатор стирки
     * @return запись на стирку
     */
    @Override
    public WashRecord getById(Long washRecordsId) {
        return washRecordRepository.findById(washRecordsId).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Добавление записи на стирку в БД.
     *
     * @param washRecord запись на стирку
     */
    @Override
    public void add(WashRecord washRecord) {
        washRecordRepository.save(washRecord);
    }

    /**
     * Обновление записи на стирку в БД.
     *
     * @param washRecordId идентификатор обновляемой записи
     * @param washRecord   новое состояние записи
     */
    @Override
    public void update(Long washRecordId, WashRecord washRecord) {
        Optional<WashRecord> washRecordOptional = washRecordRepository.findById(washRecordId);
        if (washRecordOptional.isPresent()) {
            WashRecord wash = washRecordOptional.get();
            wash.setDuration(washRecord.getDuration());
            wash.setEndTime(washRecord.getEndTime());
            wash.setStartTime(washRecord.getStartTime());
            wash.setMachineNumber(washRecord.getMachineNumber());
            wash.setUser(washRecord.getUser());
            washRecordRepository.save(wash);
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * Удаление записи на стирку из БД.
     *
     * @param dormitoryId идентификатор записи, которую нужно удалить
     */
    @Override
    public void delete(Long dormitoryId) {
        washRecordRepository.deleteById(dormitoryId);
    }
}
