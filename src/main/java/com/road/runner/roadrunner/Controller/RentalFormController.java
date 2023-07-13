package com.road.runner.roadrunner.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.road.runner.roadrunner.Modal.RentalForm;
import com.road.runner.roadrunner.Repository.RentalFormRepository;

@CrossOrigin("*")
@RequestMapping("/form")
@RestController
public class RentalFormController {

    @Autowired
    private RentalFormRepository rentalRepo;
    
    @PostMapping(value = "/add")
    public ResponseEntity<RentalForm> addCar(@RequestBody RentalForm form) {

        RentalForm submit = rentalRepo.save(form);
        return ResponseEntity.ok().body(submit);

    }

}
