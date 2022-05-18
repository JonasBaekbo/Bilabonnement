package com.example.bilabonnement.controllers;

import com.example.bilabonnement.models.Damage;
import com.example.bilabonnement.models.DamagedCar;
import com.example.bilabonnement.repository.DamageRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;

public class DamageController {


   DamageRepository dr=new DamageRepository();

    @GetMapping("/damage")
    public String listCars(Model model) {
        model.addAttribute("title", "Opret skade");
        return "createDamage";
    }

    @PostMapping("/createDamage")
    public String createDamage(@RequestParam("carID") int carID, @RequestParam("damageDescription") String damageDescription, @RequestParam("damagesCost") double damagesCost, @RequestParam("claimant") String claimant,
                              @RequestParam("damageDate") Date damageDate){
        Damage damage = new Damage(carID,damageDescription,damagesCost,claimant,damageDate);
        dr.create(damage);
        return "redirect:/admin";
    }

    @PostMapping("/closeDamage")
    public  String closeDamage(@RequestParam("carID")int carID, @RequestParam("damageFixedDate")Date damageFixedDate){
        dr.closeDamage(carID,damageFixedDate);
        return "redirect:/admin";
    }

    @GetMapping("/closeDamage")
    public String showListOfDamagedCars(Model model) {
        ArrayList<DamagedCar> damageList = (ArrayList<DamagedCar>) dr.getAllDamgesCars();
        model.addAttribute("damageList", damageList);
        return "closeDamage";
    }
}
