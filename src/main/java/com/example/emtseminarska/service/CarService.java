package com.example.emtseminarska.service;

import com.example.emtseminarska.models.Car;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {

    List<Car> findByCategoryId(Long id);

    List<Car> findAll();

    Car findById(Long id);

    Car save(Car car, MultipartFile image) throws IOException;

    Car update(Long id, Car car, MultipartFile image) throws IOException;

    void deleteById(Long id);

}
