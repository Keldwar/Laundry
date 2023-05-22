package org.ds.controller;

import org.ds.model.Dormitory;
import org.ds.model.machine.State;
import org.ds.model.machine.WashingMachine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс тестирования контроллера
 * <p>
 * Описаны интеграционные тесты для каждого запроса
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MachineControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void readAll_ReturnsStatus200WithEntities()  {
        ResponseEntity<List<Dormitory>> response = restTemplate.exchange("/dormitories",
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Dormitory testDormitory = null;
        for (Dormitory dormitory : response.getBody()) {
            if (dormitory.getName().equals("Test dormitory #1")) {
                testDormitory = dormitory;
                break;
            }
        }
        assertNotNull(testDormitory);
    }
    @Test
    public void readDormitory_ReturnStatus200WithEntity() {
        ResponseEntity<Dormitory> response = restTemplate.getForEntity("/dormitory/1", Dormitory.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getId(), 1L);
        assertEquals(response.getBody().getName(), "Test dormitory #1");
    }

    @Test
    public void readDormitory_ReturnStatus404WithoutEntity() {
        ResponseEntity<Dormitory> response = restTemplate.getForEntity("/dormitory/-1", Dormitory.class);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(response.getBody());
    }

    @Test
    public void readMachine_ReturnStatus200WithEntity() {
        ResponseEntity<WashingMachine> response = restTemplate.getForEntity("/machine/1", WashingMachine.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getId(), 1L);
    }

    @Test
    public void readMachine_ReturnStatus404WithoutEntity() {
        ResponseEntity<WashingMachine> response = restTemplate.getForEntity("/machine/-1", WashingMachine.class);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(response.getBody());
    }

    @Test
    public void createMachine_ReturnStatus201WithEntity() {
        WashingMachine washingMachine = new WashingMachine(-1L, State.FREE, 1684669877732L, 32L);
        Long id = 1L;
        ResponseEntity<WashingMachine> response = restTemplate
                .postForEntity("/dormitory/{id}/addMachine", washingMachine, WashingMachine.class, id);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getBody().getId(), -1L);
    }

    @Test
    public void createMachineIntoNonExistentDormitory_ReturnStatus400WithoutEntity() {
        WashingMachine washingMachine = new WashingMachine(5L, State.FREE, 1684726377732L, 55L);
        Long id = -1L;
        ResponseEntity<WashingMachine> response = restTemplate
                .postForEntity("/dormitory/{id}/addMachine", washingMachine, WashingMachine.class, id);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNull(response.getBody());
    }

    @Test
    public void createDormitory_ReturnStatus201WithEntity() {
        Dormitory dormitory = new Dormitory("Test dormitory #-1");
        ResponseEntity<Dormitory> response = restTemplate.postForEntity("/dormitory",
                dormitory, Dormitory.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertNotNull(response.getBody().getId());
        assertEquals(response.getBody().getName(), "Test dormitory #-1");
    }

    @Test
    public void createDormitoryWithoutName_ReturnStatus400WithoutEntity() {
        Dormitory dormitory = new Dormitory();
        ResponseEntity<Dormitory> response = restTemplate.postForEntity("/dormitory",
                dormitory, Dormitory.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNull(response.getBody());
    }

    @Test
    public void updateMachine_ReturnStatus200WithEntity() {
        ResponseEntity<WashingMachine> readMachineResponse = restTemplate.getForEntity("/machine/1", WashingMachine.class);
        WashingMachine washingMachine = readMachineResponse.getBody();
        washingMachine.setState(State.BROKEN);
        washingMachine.setDuration(59L);
        washingMachine.setTime(2684735488362L);

        HttpEntity<WashingMachine> entity = new HttpEntity<>(washingMachine);
        Long id = 1L;
        ResponseEntity<WashingMachine> response = restTemplate.exchange("/dormitory/{id}/machine",
                HttpMethod.PUT, entity, WashingMachine.class, id);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getId(), 1L);
    }

    @Test
    public void deleteDormitory_ReturnStatus200() {
        Long id = 3L;
        ResponseEntity<HttpStatus> response = restTemplate.exchange("/dormitory/{id}",
                HttpMethod.DELETE, null, HttpStatus.class, id);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void deleteNonExistDormitory_ReturnStatus404() {
        Long id = -1L;
        ResponseEntity<HttpStatus> response = restTemplate.exchange("/dormitory/{id}",
                HttpMethod.DELETE, null, HttpStatus.class, id);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void deleteMachine_ReturnStatus200() {
        Long id = 2L;
        ResponseEntity<HttpStatus> response = restTemplate.exchange("/machine/{id}",
                HttpMethod.DELETE, null, HttpStatus.class, id);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void deleteNonExistMachine_ReturnStatus404() {
        Long id = -1L;
        ResponseEntity<HttpStatus> response = restTemplate.exchange("/machine/{id}",
                HttpMethod.DELETE, null, HttpStatus.class, id);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}