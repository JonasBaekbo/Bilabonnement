package com.example.bilabonnement.controllers;

import com.example.bilabonnement.repository.StatisticsRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class StatisticsController {
    StatisticsRepository sr=new StatisticsRepository();

    @GetMapping("/statistik")
    public String leasedCars(Model model) {
        int carsLeased= sr.getNumberOfCarsLeased();
        int carsInTotal= sr.getNumbersOfCarsInTotal();
        return "/statistik";
    }
}
