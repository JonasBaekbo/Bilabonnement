package com.example.bilabonnement.controllers;


import com.example.bilabonnement.models.*;
import com.example.bilabonnement.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class CarController {
    CarRepository cr = new CarRepository();
    CarStatusRepository csr = new CarStatusRepository();
    CarModelRepository cmr=new CarModelRepository();
    FuelTypeRepository ftr = new FuelTypeRepository();
    GearTypeRepository gtr = new GearTypeRepository();
    ColourRepository colourRepository = new ColourRepository();

    @GetMapping("/createCar")
    public String showCreateCar(Model model){
        ArrayList<CarModel>carModels= (ArrayList<CarModel>) cmr.getAll();
        ArrayList<FuelType>fuelTypes= (ArrayList<FuelType>) ftr.getAll();
        ArrayList<GearType>gearTypes= (ArrayList<GearType>) gtr.getAll();
        ArrayList<Colour>colours= (ArrayList<Colour>) colourRepository.getAll();
        model.addAttribute("carModels",carModels);
        model.addAttribute("fuelTypes", fuelTypes);
        model.addAttribute("gearTypes", gearTypes);
        model.addAttribute("colours", colours);
        return "createCar";
    }

    @PostMapping("/createCar")
    public String createCar(
        @RequestParam("carModel") int carModelID,
        @RequestParam("vinNumber") String vinNumber,
        @RequestParam("numberPlate") String numberPlate,
        @RequestParam("fuelType") int fuelTypeID,
        @RequestParam("gearType") int gearTypeID,
        @RequestParam("colour") int colourID
        //@RequestParam("regFee") Integer regFee
    ) {
        CarStatus carStatus = csr.getByName("hjemme");
        CarModel carModel = cmr.getByID(carModelID);
        FuelType fuelType = ftr.getByID(fuelTypeID);
        GearType gearType = gtr.getByID(gearTypeID);
        Colour colour = colourRepository.getByID(colourID);
        Car car = new Car(
            carStatus,
            carModel,
            fuelType,
            gearType,
            colour,
            numberPlate,
            vinNumber
        );
        cr.create(car);
        return "redirect:/createCar";
    }
}
