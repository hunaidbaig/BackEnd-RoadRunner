package com.road.runner.roadrunner.Modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity

@AllArgsConstructor
@NoArgsConstructor
public class CarModal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String name;
    
    private String shortdesc;
    
    @Column(length = 1000)
    private String longdesc;
    private String imagelink;
    
    private Long rentfee;
    
}
