package com.example.bilabonnement.controllers;


import com.example.bilabonnement.models.CarEconomy;
import com.example.bilabonnement.repository.EconomyRepository;
import com.example.bilabonnement.servises.Economy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EconomyController {
    private final EconomyRepository economyRepository = new EconomyRepository();
    private final Economy economy = new Economy();


    @GetMapping("/test")
    public String leasingaftaleFront(Model model) {
        List<CarEconomy> carEconomies = economyRepository.getAllRentedCars();
        int totalMontlyIncome = economy.totalMonthlyIncomeThisMonthFromRentedCars();
        model.addAttribute("carEconomies", carEconomies);
        model.addAttribute("totalMontlyIncome",totalMontlyIncome);
        return "admin/includes/Ecotest";
    }
}
