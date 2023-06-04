package org.ds.controller;

import org.ds.model.Dormitory;
import org.ds.service.DormitoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class DormitoryControllerTest {
    @Mock
    DormitoryService dormitoryService;
    @InjectMocks
    DormitoryController dormitoryController;

    @Test
    void getAllTest() {
        List<Dormitory> dormitories = List.of(new Dormitory("Первое общежитие", new ArrayList<>()),
                new Dormitory("Второе общежитие", new ArrayList<>()));
        doReturn(dormitories).when(this.dormitoryService).getAll();

        List<Dormitory> response = this.dormitoryController.readAll();

        assertNotNull(response);
        assertEquals(dormitories, response);
    }
}