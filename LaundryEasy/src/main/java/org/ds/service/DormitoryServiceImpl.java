package org.ds.service;

import org.ds.model.Dormitory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Реализация DormitoryService
 * <p>
 * Все методы данного класса обращаются к API ландромата
 */
@Service
public class DormitoryServiceImpl implements DormitoryService {
    private final WebClient dormitoryClient;

    public DormitoryServiceImpl(WebClient dormitoryClient) {
        this.dormitoryClient = dormitoryClient;
    }

    @Override
    public List<Dormitory> getAll() {
        return List.of(dormitoryClient.get()
                .uri("/dormitories")
                .retrieve()
                .bodyToMono(Dormitory[].class)
                .block());
    }

    @Override
    public Dormitory getById(Long dormitoryId) {
        return dormitoryClient.get()
                .uri("/dormitory/{dormitoryId}", dormitoryId)
                .retrieve()
                .bodyToMono(Dormitory.class)
                .block();
    }

    @Override
    public void add(Dormitory dormitory) {
        dormitoryClient.post()
                .uri("/dormitory")
                .body(Mono.just(dormitory), Dormitory.class)
                .retrieve()
                .bodyToMono(Dormitory.class)
                .block();
    }

    @Override
    public void update(Long dormitoryId, Dormitory dormitory) {
        dormitoryClient.put()
                .uri("/dormitory/{dormitoryId}", dormitoryId)
                .body(Mono.just(dormitory), Dormitory.class)
                .retrieve()
                .bodyToMono(Dormitory.class)
                .block();
    }

    @Override
    public void delete(Long dormitoryId) {
        dormitoryClient.delete()
                .uri("/dormitory/{dormitoryId}", dormitoryId)
                .retrieve()
                .bodyToMono(Dormitory.class)
                .block();
    }
}
