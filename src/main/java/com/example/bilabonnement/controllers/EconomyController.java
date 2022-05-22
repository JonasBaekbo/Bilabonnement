package com.example.bilabonnement.controllers;


import com.example.bilabonnement.models.CarEconomy;
import com.example.bilabonnement.models.StatisticsItem;
import com.example.bilabonnement.repository.EconomyRepository;
import com.example.bilabonnement.repository.StatisticsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class EconomyController {
    private final EconomyRepository economyRepository = new EconomyRepository();
    private final StatisticsRepository statisticsRepository = new StatisticsRepository();

    @GetMapping("/admin/lejedebiler")
    public String leasedCars(Model model) {
        ArrayList<CarEconomy> carEconomies = economyRepository.getAllEntities();
        double totalMontlyIncome = economyRepository.totalMonthlyIncomeThisMonthFromRentedCars();
        ArrayList<StatisticsItem> carsPerStatus = statisticsRepository.getCarsPerStatus();
        model.addAttribute("title", "Alle lejede biler");
        model.addAttribute("totalMontlyIncome",totalMontlyIncome);
        model.addAttribute("carEconomies", carEconomies);
        model.addAttribute("carsPerStatus", carsPerStatus);
        return "admin/economy/showAllLeasedCars";
    }

}
