package com.example.emtseminarska.service.impl;

import com.example.emtseminarska.models.Brand;
import com.example.emtseminarska.models.Car;
import com.example.emtseminarska.models.Engine;
import com.example.emtseminarska.models.exception.CarNotFoundException;
import com.example.emtseminarska.repository.CarRepository;
import com.example.emtseminarska.service.BrandService;
import com.example.emtseminarska.service.CarService;
import com.example.emtseminarska.service.EngineService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Blob;
import java.util.Base64;
import java.util.List;

@Transactional
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final BrandService brandService;

    private final EngineService engineService;

    public CarServiceImpl(CarRepository carRepository, BrandService brandService, EngineService engineService) {
        this.carRepository = carRepository;
        this.brandService = brandService;
        this.engineService = engineService;
    }

    @Override
    public List<Car> findByCategoryId(Long id) { return null;   }

    @Override
    public List<Car> findAll() {
        return this.carRepository.findAll();
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
    }

    @Override
    public Car save(Car car, MultipartFile image) throws IOException {

        Brand brand = this.brandService.findById(car.getId_brand().getId());
        car.setId_brand(brand);
        Engine engine = this.engineService.findById(car.getId_engine().getId());
        car.setId_engine(engine);
        if (image != null) {
            byte[] imageBytes = image.getBytes();
            String test = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(imageBytes));
            car.setImage(test);
        }
        return this.carRepository.save(car);
    }

    @Override
    public Car update(Long id, Car car, MultipartFile image) throws IOException {
        Car updatedProduct = this.findById(id);
        Brand brand = this.brandService.findById(car.getId_brand().getId());
        Engine engine = this.engineService.findById(car.getId_engine().getId());

        updatedProduct.setId_brand(brand);
        updatedProduct.setId_engine(engine);

        updatedProduct.setCarName(car.getCarName());
        updatedProduct.setPrice(car.getPrice());
        updatedProduct.setImage(car.getImage());

        if (image != null) {
            byte[] imageBytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(imageBytes));
            updatedProduct.setImage(base64Image);
        }
        return this.carRepository.save(updatedProduct);
    }

    @Override
    public void deleteById(Long id) {
        this.carRepository.deleteById(id);
    }
}
