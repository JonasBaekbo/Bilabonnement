package com.example.bilabonnement.controllers;


import com.example.bilabonnement.models.CarEconomy;
import com.example.bilabonnement.repository.EconomyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EconomyController {
    private final EconomyRepository economyRepository = new EconomyRepository();

    @GetMapping("/admin/lejedebiler")
    public String leasedCars(Model model) {
        List<CarEconomy> carEconomies = economyRepository.getAllEntities();
        int totalMontlyIncome = economyRepository.totalMonthlyIncomeThisMonthFromRentedCars();
        model.addAttribute("totalMontlyIncome",totalMontlyIncome);
        model.addAttribute("carEconomies", carEconomies);
        model.addAttribute("title", "Alle lejede biler");
        return "admin/economy/showAllLeasedCars";
    }

}
