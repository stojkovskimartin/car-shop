package com.example.emtseminarska.service;

import com.example.emtseminarska.models.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> findAll();

    Brand findById(Long id);

    Brand save(Brand brand);

    Brand update(Long id, Brand brand);

    void deleteById(Long id);
}
