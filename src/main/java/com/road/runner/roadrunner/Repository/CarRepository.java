package com.road.runner.roadrunner.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.road.runner.roadrunner.Modal.CarModal;

@EnableJpaRepositories
public interface CarRepository extends JpaRepository<CarModal,Long> {
    
}
