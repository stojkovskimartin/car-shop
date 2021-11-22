package com.example.emtseminarska.web;

import com.example.emtseminarska.models.Car;
import com.example.emtseminarska.service.CarService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/api/cars")
public class CarRestController {

    private final CarService carService;

    public CarRestController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> findAll() {
        return this.carService.findAll();
    }

    @GetMapping("/{id}")
    public Car findById(@PathVariable Long id) {
        return this.carService.findById(id);
    }

    @GetMapping("/by-category/{id}")
    public List<Car> findAllByCategoryId(@PathVariable Long id) {
        return this.carService.findByCategoryId(id);
    }


    @PostMapping
    public Car save(@Valid Car car, @RequestParam(required = false) MultipartFile image) throws IOException, SQLException {
        return this.carService.save(car, image);
    }


    @PutMapping("/{id}")
    public Car update(@PathVariable Long id,
                       @Valid Car car,
                       @RequestParam(required = false) MultipartFile image) throws IOException, SQLException {
        return this.carService.update(id, car, image);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.carService.deleteById(id);
    }

}
