package com.example.bilabonnement.controllers;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.Damage;
import com.example.bilabonnement.models.DamagedCar;
import com.example.bilabonnement.repository.CarRepository;
import com.example.bilabonnement.repository.DamageRepository;
import com.example.bilabonnement.repository.DamagedCarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DamageController {

DamagedCarRepository dcr=new DamagedCarRepository();
   DamageRepository dr=new DamageRepository();
   CarRepository cr =new CarRepository();


    @GetMapping ("/admin/opretskade")
    public String showListOfCars(Model model) {
        model.addAttribute("title", "Opret");
        ArrayList<Car> carList = (ArrayList<Car>) cr.getAllEntities();
        model.addAttribute("carList", carList);
        return "admin/damage/createDamage";
    }


    @PostMapping("/createDamage")
    public String createDamage(@RequestParam("carID") int carID, @RequestParam("damageDescription") String damageDescription, @RequestParam("damagesCost") double damagesCost, @RequestParam("claimant") String claimant,
                              @RequestParam("damageDate") Date damageDate){
        Damage damage = new Damage(carID,damageDescription,damagesCost,claimant,damageDate);
        dr.create(damage);
        return "redirect:/admin/opretskade";
    }


    @GetMapping("/admin/lukskade")
    public String showListOfDamagedCars(Model model) {
        model.addAttribute("title", "Luk skade");
        ArrayList<DamagedCar> damageList = (ArrayList<DamagedCar>) dcr.getAllDamgesCars();
        model.addAttribute("damageList", damageList);
        return "admin/damage/closeDamage";
    }


    @PostMapping("/closeDamage")
    public String closeDamage(@RequestParam("damage") List<Integer> selectedDamageIDs, @RequestParam("fixedDate") Date fixedDate){
        for (Integer damageID : selectedDamageIDs) {
            dr.closeDamage(damageID, fixedDate);
        }
        return "redirect:/admin/lukskade";
    }



}
