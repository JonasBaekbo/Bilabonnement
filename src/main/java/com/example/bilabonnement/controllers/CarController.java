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
    private final CarRepository carRepository = new CarRepository();
    private final  CarStatusRepository carStatusRepository = new CarStatusRepository();
    private final  CarModelRepository carModelRepository = new CarModelRepository();
    private final FuelTypeRepository fuelTypeRepository = new FuelTypeRepository();
    private final GearTypeRepository gearTypeRepository = new GearTypeRepository();
    private final ColourRepository colourRepository = new ColourRepository();

    @GetMapping("/admin/opretbil")
    public String showCreateCar(Model model) {
        ArrayList<CarModel> carModels = (ArrayList<CarModel>) carModelRepository.getAll();
        ArrayList<FuelType> fuelTypes = (ArrayList<FuelType>) fuelTypeRepository.getAll();
        ArrayList<GearType> gearTypes = (ArrayList<GearType>) gearTypeRepository.getAll();
        ArrayList<Colour> colours = (ArrayList<Colour>) colourRepository.getAll();
        model.addAttribute("carModels", carModels);
        model.addAttribute("fuelTypes", fuelTypes);
        model.addAttribute("gearTypes", gearTypes);
        model.addAttribute("colours", colours);
        return "admin/leasing/createCar";
    }

    @PostMapping("/createCar")
    public String createCar(@RequestParam("carModel") int carModelID, @RequestParam("vinNumber") String vinNumber, @RequestParam("licencePlate") String licencePlate, @RequestParam("fuelType") int fuelTypeID,
                            @RequestParam("gearType") int gearTypeID, @RequestParam("colour") int colourID, @RequestParam("regFee") Double regFee) {
        CarStatus carStatus = carStatusRepository.getByName("hjemme");
        CarModel carModel = carModelRepository.getByID(carModelID);
        FuelType fuelType = fuelTypeRepository.getByID(fuelTypeID);
        GearType gearType = gearTypeRepository.getByID(gearTypeID);
        Colour colour = colourRepository.getByID(colourID);
        Car car = new Car(carStatus, carModel, fuelType, gearType, colour, licencePlate, vinNumber, regFee);
        carRepository.create(car);
        return "redirect:/admin/opretbil";
    }

    @GetMapping("/admin/tilfoejnummerplade")
    public String showMissingLicencePlate(Model model) {
        ArrayList<Car> carsMissing = carRepository.getCarsMissingLicence();
        model.addAttribute("carsMissing", carsMissing);
        return "admin/leasing/addLicencePlate";
    }

    @PostMapping("/adLicencePlate")
    public String adLicencePlateToCar(@RequestParam("carID") int carID, @RequestParam("licencePlateLP") String licencePlate, @RequestParam("regFeeLP") Double regFee) {
        Car car = carRepository.getSingleById(carID);
        car.setLicencePlate(licencePlate);
        car.setRegistrationFee(regFee);
        carRepository.update(car);
        return "redirect:/admin/tilfoejnummerplade";
    }
}
