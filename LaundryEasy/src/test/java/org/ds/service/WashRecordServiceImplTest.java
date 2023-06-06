package org.ds.service;

import jakarta.persistence.EntityNotFoundException;
import org.ds.model.entities.WashRecord;
import org.ds.repository.WashRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Тестирование класса {@link org.ds.service.WashRecordServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
class WashRecordServiceImplTest {
    private static final Long ID = 1L;
    @Mock
    private WashRecordRepository washRecordRepository;

    @InjectMocks
    private WashRecordServiceImpl washRecordService;

    /**
     * Тестирование получение всех записей.
     */
    @Test
    void getAll_ReturnList() {
        WashRecord washRecord1 = mock(WashRecord.class);
        WashRecord washRecord2 = mock(WashRecord.class);
        List<WashRecord> washRecords = List.of(washRecord1, washRecord2);
        when(washRecordRepository.findAll()).thenReturn(washRecords);

        List<WashRecord> response = washRecordService.getAll();

        assertNotNull(response);
        assertEquals(washRecords, response);
        verify(washRecordRepository).findAll();
    }

    /**
     * Тестирование получение записи на стирку.
     */
    @Test
    void getById_ifWashRecordExists_ReturnWashRecord() {
        WashRecord washRecord = mock(WashRecord.class);
        when(washRecordRepository.findById(ID)).thenReturn(Optional.ofNullable(washRecord));

        WashRecord actual = washRecordService.getById(ID);

        assertNotNull(actual);
        assertEquals(washRecord, actual);
        verify(washRecordRepository).findById(ID);
    }

    /**
     * Тестирование возникновения исключения при получении записи, когда записи с таким идентификатором не существует.
     */
    @Test
    void getById_ifWashRecordNotExist_ThrowException() {
        when(washRecordRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> washRecordService.getById(ID));
    }

    /**
     * Тестирование добавления записи.
     */
    @Test
    void addTest() {
        WashRecord washRecord = mock(WashRecord.class);

        washRecordService.add(washRecord);

        verify(washRecordRepository, times(1))
                .save(washRecord);
        verify(washRecordRepository, never())
                .delete(any(WashRecord.class));
    }

    /**
     * Тестирование обновления записи, если запись существует.
     */
    @Test
    void update_IfWashRecordExists() {
        WashRecord oldWashRecord = new WashRecord(1);
        WashRecord newWashRecord = new WashRecord(2);
        when(washRecordRepository.findById(ID)).thenReturn(Optional.of(oldWashRecord));

        washRecordService.update(ID, newWashRecord);

        assertEquals(newWashRecord, oldWashRecord);
        verify(washRecordRepository, times(1))
                .save(oldWashRecord);
    }

    /**
     * Тестирование обновления записи, если запись отсутствует.
     */
    @Test
    void update_IfWashRecordNotExists() {
        WashRecord washRecord = mock(WashRecord.class);
        when(washRecordRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> washRecordService.update(ID, washRecord));
        verify(washRecordRepository).findById(ID);
    }

    /**
     * Тестирование удаления записи, если запись существует.
     */
    @Test
    void deleteTest_IfWashRecordExists() {
        when(washRecordRepository.existsById(ID)).thenReturn(true);

        washRecordService.delete(ID);

        verify(washRecordRepository, times(1))
                .deleteById(ID);
        verify(washRecordRepository, times(1))
                .existsById(ID);
        verify(washRecordRepository, never())
                .save(any(WashRecord.class));
    }

    /**
     * Тестирование удаления записи, если запись не существует.
     */
    @Test
    void deleteTest_IfWashRecordNotExists() {
        when(washRecordRepository.existsById(ID)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> washRecordService.delete(ID));
        verify(washRecordRepository, never())
                .deleteById(any(Long.class));
    }
}