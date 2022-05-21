package com.example.bilabonnement.controllers;

import com.example.bilabonnement.models.ExtraEquipment;
import com.example.bilabonnement.repository.ExtraEquipmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EkstraEquipemntController {

    private final ExtraEquipmentRepository extraEquipmentRepository = new ExtraEquipmentRepository();

    @GetMapping("/admin/visekstraudstyr")
    public String ekstreEq(Model model){
        List<ExtraEquipment> extraEquipment = new ArrayList<>();
        extraEquipment = extraEquipmentRepository.getAllExtraEqupment();
        model.addAttribute("extraEquipment", extraEquipment);
        return "admin/leasing/extraEquipment";
    }
}
