package com.road.runner.roadrunner.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.road.runner.roadrunner.Modal.CarModal;
import com.road.runner.roadrunner.Repository.CarRepository;

@CrossOrigin("*")
@RequestMapping("/cars")
@RestController
public class CarController {
    
    @Autowired
    private CarRepository carRepo;

    @GetMapping(value = "")
    public ResponseEntity<List<CarModal>> getAllCars() {

        List<CarModal> cars = carRepo.findAll();
        return ResponseEntity.ok().body(cars);

    }

    @PostMapping(value = "/add")
    public ResponseEntity<CarModal> addCar(@RequestBody CarModal car) {

        CarModal cars = carRepo.save(car);
        return ResponseEntity.ok().body(cars);

    }
}
