package com.example.bilabonnement.controllers;
import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.DamagedCar;
import org.springframework.stereotype.Controller;
import com.example.bilabonnement.models.Leasing;
import com.example.bilabonnement.repository.LeasingRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;

@Controller
public class LeasingController {

    LeasingRepository lr = new LeasingRepository();

        @GetMapping("/admin/opretlease")
    public String leasingaftaleFront(Model model) {
            ArrayList<Car> listOfFreeCars = (ArrayList<Car>) lr.getAllEntities();
            model.addAttribute("listOfFreeCars", listOfFreeCars);
        model.addAttribute("title", "Opret lease");
        return "admin/leasing/opretlease";
    }

    @PostMapping("/opretlease")
    public String createLease(@RequestParam("customerID") int customerID, @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate,
                              @RequestParam("includedKM") int includedKM, @RequestParam("carID") int carID, @RequestParam("leasingType") String leasingType){
        Leasing lease = new Leasing(customerID, startDate, endDate, includedKM, carID, leasingType);
        lr.create(lease);

        return "redirect:/admin";
            }
}
