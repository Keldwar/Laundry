package org.ds.controller;

import org.ds.exceptions.Response;
import org.ds.model.entities.Dormitory;
import org.ds.model.State;
import org.ds.model.entities.WashingMachine;
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
 * Написаны интеграционные тесты для каждого запроса
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MachineControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void readAll_ReturnsStatus200WithEntities() {
        ResponseEntity<List<Dormitory>> response = restTemplate.exchange("/dormitories",
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
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

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Test dormitory #1", response.getBody().getName());
    }

    @Test
    public void readDormitory_ReturnStatus404WithoutEntity() {
        ResponseEntity<Dormitory> response = restTemplate.getForEntity("/dormitory/10", Dormitory.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void readMachine_ReturnStatus200WithEntity() {
        ResponseEntity<WashingMachine> response = restTemplate.getForEntity("/machine/1", WashingMachine.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void readMachine_ReturnStatus404WithoutEntity() {
        ResponseEntity<WashingMachine> response = restTemplate.getForEntity("/machine/10", WashingMachine.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void createMachine_ReturnStatus201WithResponse() {
        Dormitory dormitory = new Dormitory("Test dormitory #11");
        ResponseEntity<Dormitory> responseDormitoryAdded = restTemplate
                .postForEntity("/dormitory", dormitory, Dormitory.class);

        WashingMachine washingMachine = new WashingMachine(State.BROKEN, 2684669877732L, 32L);
        Long dormitoryId = responseDormitoryAdded.getBody().getId();

        ResponseEntity<Response> response = restTemplate
                .postForEntity("/dormitory/{dormitoryId}/addMachine", washingMachine, Response.class, dormitoryId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Machine added successfully", response.getBody().getMessage());

        responseDormitoryAdded = restTemplate
                .getForEntity("/dormitory/{dormitoryId}", Dormitory.class, dormitoryId);
        assertEquals(responseDormitoryAdded.getStatusCode(), HttpStatus.OK);
        assertEquals("Test dormitory #11", responseDormitoryAdded.getBody().getName());

        WashingMachine addedMachine = responseDormitoryAdded.getBody().getMachines().get(0);
        assertEquals(washingMachine, addedMachine);
    }

    @Test
    public void createMachineIntoNonExistentDormitory_ReturnStatus400WithResponse() {
        WashingMachine washingMachine = new WashingMachine(State.FREE, 2684726377732L, 55L);
        Long dormitoryId = 10L;
        ResponseEntity<Response> response = restTemplate
                .postForEntity("/dormitory/{id}/addMachine", washingMachine, Response.class, dormitoryId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Machine not added", response.getBody().getMessage());
    }

    @Test
    public void createDormitory_ReturnStatus201WithEntity() {
        Dormitory dormitory = new Dormitory("Test dormitory #4");
        ResponseEntity<Dormitory> response = restTemplate
                .postForEntity("/dormitory", dormitory, Dormitory.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(dormitory, response.getBody());
    }

    @Test
    public void updateMachine_ReturnStatus200() {
        ResponseEntity<WashingMachine> readMachineResponse = restTemplate
                .getForEntity("/machine/1", WashingMachine.class);

        WashingMachine washingMachine = readMachineResponse.getBody();
        washingMachine.setState(State.BROKEN);
        washingMachine.setDuration(59L);
        washingMachine.setTime(2684735488362L);

        HttpEntity<WashingMachine> entity = new HttpEntity<>(washingMachine);
        restTemplate.exchange("/machine", HttpMethod.PUT, entity, Void.class);

        readMachineResponse = restTemplate.getForEntity("/machine/1", WashingMachine.class);
        assertEquals(HttpStatus.OK, readMachineResponse.getStatusCode());

        WashingMachine updatedMachine = readMachineResponse.getBody();
        assertEquals(updatedMachine, washingMachine);
    }

    @Test
    public void deleteDormitory_ReturnStatus200WithResponse() {
        Dormitory dormitory = new Dormitory("Test dormitory #5");
        ResponseEntity<Dormitory> responseDormitoryAdded = restTemplate
                .postForEntity("/dormitory", dormitory, Dormitory.class);

        Long dormitoryId = responseDormitoryAdded.getBody().getId();
        System.out.println(dormitoryId);
        ResponseEntity<Response> response = restTemplate.exchange("/dormitory/{dormitoryId}",
                HttpMethod.DELETE, HttpEntity.EMPTY, new ParameterizedTypeReference<>() {
                }, dormitoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Dormitory deleted successfully", response.getBody().getMessage());
    }

    @Test
    public void deleteNonExistDormitory_ReturnStatus404WithResponse() {
        Long id = 11L;
        ResponseEntity<Response> response = restTemplate
                .exchange("/dormitory/{id}", HttpMethod.DELETE, null, Response.class, id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Dormitory not found", response.getBody().getMessage());
    }

    @Test
    public void deleteMachine_ReturnStatus200WithResponse() {
        Long id = 2L;
        ResponseEntity<Response> response = restTemplate
                .exchange("/machine/{id}", HttpMethod.DELETE, null, Response.class, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Machine deleted successfully", response.getBody().getMessage());
    }

    @Test
    public void deleteNonExistMachine_ReturnStatus404WithResponse() {
        Long id = 11L;
        ResponseEntity<Response> response = restTemplate
                .exchange("/machine/{id}", HttpMethod.DELETE, null, Response.class, id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Machine not found", response.getBody().getMessage());
    }
}