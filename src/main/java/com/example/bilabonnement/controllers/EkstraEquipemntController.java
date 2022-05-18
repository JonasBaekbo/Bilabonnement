package com.example.bilabonnement.controllers;

import com.example.bilabonnement.models.ekstraEquipemnt;
import com.example.bilabonnement.repository.ExtraEquipmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EkstraEquipemntController {

    private final ExtraEquipmentRepository extraEquipmentRepository = new ExtraEquipmentRepository();

    @GetMapping("/test")
    public String ekstreEq(Model model){
        List<ekstraEquipemnt> ekstraEquipemnts = new ArrayList<>();
        ekstraEquipemnts = extraEquipmentRepository.getAllExtraEqupment();
        model.addAttribute("ekstraEquipemnts",ekstraEquipemnts);
        return "test";
    }
}
