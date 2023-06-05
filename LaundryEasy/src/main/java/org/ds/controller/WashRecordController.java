package org.ds.controller;

import jakarta.validation.Valid;
import org.ds.model.entities.WashRecord;
import org.ds.service.WashRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Контроллер, предоставляющий доступ к записям на стирку. Позволяет получать, добавлять, изменять записи.
 */
@RestController
public class WashRecordController {
    private final WashRecordService washRecordService;

    public WashRecordController(WashRecordService washRecordService) {
        this.washRecordService = washRecordService;
    }

    /**
     * Получение всех записей на стирку, подходящих под условия.
     * Условиями могут быть конкретное общежития, период, записи которого нужно получить.
     *
     * @return список всех записей
     */
    @GetMapping("/washRecords")
    public List<WashRecord> getAllinDormitory(@RequestParam(value = "dormitoryId", required = false) Long dormitoryId,
                                              @RequestParam(value = "fromTime", required = false) Long fromTime,
                                              @RequestParam(value = "toTime", required = false) Long toTime) {
        return washRecordService.getAllWithConditions(dormitoryId, fromTime, toTime);
    }

    /**
     * Получение конкретной записи на стирку по её идентификатору.
     *
     * @param washRecordId идентификатор записи
     * @return запись на стирку с данным идентификатором
     */
    @GetMapping("/washRecords/{washRecordId}")
    public WashRecord read(@PathVariable Long washRecordId) {
        return washRecordService.getById(washRecordId);
    }

    /**
     * Добавление новой записи на стирку.
     *
     * @param washRecord запись на стирку
     */
    @PostMapping("/washRecords")
    public void add(@RequestBody @Valid WashRecord washRecord) {
        washRecordService.add(washRecord);
    }

    /**
     * Обновление записи на стирку.
     *
     * @param washRecordId идентификатор записи, которую нужно обновить
     * @param washRecord   новое состояние записи
     */
    @PutMapping("/washRecords/{washRecordId}")
    public void update(@PathVariable Long washRecordId, @RequestBody @Valid WashRecord washRecord) {
        washRecordService.update(washRecordId, washRecord);
    }

    /**
     * Удаление записи на стирку.
     *
     * @param washRecordId идентификатор записи, которую нужно удалить
     */
    @DeleteMapping("/washRecords/{washRecordId}")
    public void delete(@PathVariable Long washRecordId) {
        washRecordService.delete(washRecordId);
    }
}
