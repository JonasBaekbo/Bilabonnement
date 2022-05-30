//Mikkel

package com.example.bilabonnement.controllers;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.ExtraEquipment;
import com.example.bilabonnement.repository.CarRepository;
import com.example.bilabonnement.repository.ExtraEquipmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ExtraEquipmentController {

    private final ExtraEquipmentRepository extraEquipmentRepository = new ExtraEquipmentRepository();
    private final CarRepository carRepository =new CarRepository();

    @GetMapping("/admin/visekstraudstyr")
    public String extraEq(Model model) {
        List<ExtraEquipment> extraEquipment = extraEquipmentRepository.getExtraEquipmentCarList();
        model.addAttribute("extraEquipment", extraEquipment);
        return "admin/leasing/extraEquipment";
    }

    @GetMapping("/admin/tilfoejekstraudstyr")
    public String showaddExtraEquipment(Model model){
        List<ExtraEquipment> extraEquipmentList = extraEquipmentRepository.getAllEntities();
        List<Car>allCars=carRepository.getAllEntities();
        model.addAttribute("extraEquipmentList", extraEquipmentList);
        model.addAttribute("allCars", allCars);
        return "admin/leasing/addExtraEquipment";
    }

    @PostMapping("/adExtraEquipment")
    public String adExtraEquipment(@RequestParam("exID") List<Integer> exIDs, @RequestParam("carID") int carID) {
        Car car= carRepository.getSingleById(carID);
        for (Integer exID : exIDs) {
            ExtraEquipment extraEquipment = new ExtraEquipment(car, exID);
            extraEquipmentRepository.create(extraEquipment);
        }
        return "redirect:/admin/tilfoejekstraudstyr";
    }
}
