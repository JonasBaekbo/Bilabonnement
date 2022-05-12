package com.example.bilabonnement.controllers;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.repository.EconomyRepository;
import com.example.bilabonnement.repository.IRepository;
import com.example.bilabonnement.servises.Economy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EconomyController {
    private final IRepository<Car> EconomyRepository = new EconomyRepository();
    private final Economy economy = new Economy();


    @GetMapping("/test")
    public String leasingaftaleFront(Model model) {
        List cars = EconomyRepository.getAllEntities();
        int totalMontlyIncome = economy.totalMonthlyIncomeThisMonthFromRentedCars();
        model.addAttribute("cars", cars);
        model.addAttribute("totalMontlyIncome",totalMontlyIncome);
        return "admin/includes/Ecotest";
    }
}
