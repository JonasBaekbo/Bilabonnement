package com.example.bilabonnement.controllers;

import com.example.bilabonnement.BilabonnementApplication;
import com.example.bilabonnement.db.DB;
import com.example.bilabonnement.ulility.DatabaseConnectionManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.util.Date;

public class lejeController {

    Connection con = DatabaseConnectionManager.getConnection();

    @PostMapping("/opretlejeaftale")
    public String opretLejeaftale(@RequestParam("customerID") String customerID, @RequestParam("startDate") Date startDate,
                                  @RequestParam("endDate") Date endDate, @RequestParam("includedKM") int includedKM,
                                  @RequestParam("carID") int carID, Authentication auth) {


        return "redirect:/make-a-wish";
    }
}
