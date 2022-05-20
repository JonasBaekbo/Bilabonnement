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


    @GetMapping("/admin/lejedebiler")
    public String leasedCars(Model model) {
        List<CarEconomy> carEconomies = economyRepository.getAllRentedCars();
        model.addAttribute("carEconomies", carEconomies);
        model.addAttribute("title", "Alle lejede biler");
        return "admin/economy/showAllLeasedCars";
    }
    @GetMapping("/admin/samletpris")
    public String monthlyIncome(Model model) {
        int totalMontlyIncome = economy.totalMonthlyIncomeThisMonthFromRentedCars();
        model.addAttribute("totalMontlyIncome",totalMontlyIncome);
        model.addAttribute("title", "Samlet pris");
        return "admin/economy/showMonthlyIncome";
    }

}
