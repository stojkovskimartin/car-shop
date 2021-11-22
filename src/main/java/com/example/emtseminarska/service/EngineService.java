package com.example.emtseminarska.service;

import com.example.emtseminarska.models.Engine;

import java.util.List;

public interface EngineService {

    List<Engine> findAll();

    Engine findById(Long id);

    Engine save(Engine engine);

    Engine update(Long id, Engine engine);

    void deleteById(Long id);
}
