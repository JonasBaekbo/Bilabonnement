package com.example.bilabonnement.controllers;

import com.example.bilabonnement.models.Leasing;
import com.example.bilabonnement.repository.LeasingRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

public class LeasingController {

    LeasingRepository lr = new LeasingRepository();

    @GetMapping("/leasingaftale")
    public String leasingaftaleFront(Model model) {
        return "leasing/opretlease";
    }

    @PostMapping("/leasingaftale")
    public String createLease(@RequestParam("customerID") String customerID, @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate,
                              @RequestParam("includedKM") int includedKM, @RequestParam("carID") int carID){
        lr.create(new Leasing(customerID, startDate, endDate, includedKM, carID));
        return "redirect:leasing/opretlease";
            }
}
