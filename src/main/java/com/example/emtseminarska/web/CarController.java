package com.example.emtseminarska.web;

import com.example.emtseminarska.models.Brand;
import com.example.emtseminarska.models.Car;
import com.example.emtseminarska.models.Engine;
import com.example.emtseminarska.service.BrandService;
import com.example.emtseminarska.service.CarService;
import com.example.emtseminarska.service.EngineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final BrandService brandService;
    private final EngineService engineService;

    public CarController(CarService carService, BrandService brandService, EngineService engineService) {
        this.carService = carService;
        this.brandService = brandService;
        this.engineService = engineService;
    }

    @GetMapping
    public String listCars(Model model)
    {
        List<Car> car = this.carService.findAll();
        model.addAttribute("car", car);
        return "cars";
    }

    @PostMapping
    public String saveOrUpdateCar(Model model, @Valid Car car, BindingResult bindingResult, MultipartFile image) throws IOException {
        if (car.getId() == null) {
            Car newCar = this.carService.save(car, image);
        } else {
            this.carService.update(car.getId(), car, image);
        }
//      if (bindingResult.hasErrors()) {
//            List<Brand> brand = this.brandService.findAll();
//            model.addAttribute("brand", brand);
//            List<Engine> engine = this.engineService.findAll();
//            model.addAttribute("engine", engine);
//            return "add";
//        }
        return "redirect:/cars";
    }

    @GetMapping("/add")
    public String addNewCar(Model model)
    {
        List<Brand> brand = this.brandService.findAll();
        List<Engine> engine = this.engineService.findAll();
        model.addAttribute("car", new Car());
        model.addAttribute("brand", brand);
        model.addAttribute("engine", engine);
        return "add";
    }

    @GetMapping("/{id}/edit")
    public String editCar(@PathVariable Long id, Model model)
    {
        try {
            Car car = this.carService.findById(id);
            List<Brand> brand = this.brandService.findAll();
            List<Engine> engine = this.engineService.findAll();
            model.addAttribute("car", car);
            model.addAttribute("brand", brand);
            model.addAttribute("engine", engine);
            return "add";
        } catch (RuntimeException ex)
        {
            return "redirect:/cars?error=" + ex.getLocalizedMessage();
        }
    }

    @DeleteMapping("/{id}/delete")
    public String deleteCarWithDelete(@PathVariable Long id){
        this.carService.deleteById(id);
        return "redirect:/cars";
    }

    @PostMapping (value = "/{id}/delete")
    public String deleteCarWithPost(@PathVariable Long id){
        this.carService.deleteById(id);
        return "redirect:/cars";
    }

}
