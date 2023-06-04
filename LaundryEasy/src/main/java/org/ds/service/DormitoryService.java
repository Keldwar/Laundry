package org.ds.service;

import org.ds.model.Dormitory;

import java.util.List;

public interface DormitoryService {
    List<Dormitory> getAll();

    Dormitory getById(Long dormitoryId);

    void add(Dormitory dormitory);

    void update(Long dormitoryId, Dormitory dormitory);

    void delete(Long dormitoryId);
}
