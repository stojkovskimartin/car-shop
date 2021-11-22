package com.example.emtseminarska.service.impl;

import com.example.emtseminarska.models.Engine;
import com.example.emtseminarska.models.exception.EngineNotFoundException;
import com.example.emtseminarska.repository.EngineRepository;
import com.example.emtseminarska.service.EngineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineServiceImpl implements EngineService {

    private final EngineRepository engineRepository;

    public EngineServiceImpl(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }


    @Override
    public List<Engine> findAll() {
        return this.engineRepository.findAll();
    }

    @Override
    public Engine findById(Long id) {
        return this.engineRepository.findById(id).orElseThrow(() ->new EngineNotFoundException(id));

    }

    @Override
    public Engine save(Engine engine) {
        Engine engine1 = new Engine();

        engine1.setEngineName(engine.getEngineName());

        return this.engineRepository.save(engine);
    }

    @Override
    public Engine update(Long id, Engine engine) {
        Engine updateEngine = this.findById(id);
        updateEngine.setEngineName(engine.getEngineName());
        updateEngine.setId(engine.getId());
        return this.engineRepository.save(updateEngine);
    }

    @Override
    public void deleteById(Long id) {
        this.engineRepository.deleteById(id);
    }
}
