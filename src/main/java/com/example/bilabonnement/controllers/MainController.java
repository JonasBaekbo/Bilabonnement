package com.example.bilabonnement.controllers;

import com.example.bilabonnement.db.DB;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


@Controller
public class MainController {


    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Forside");
        return "index";
    }
    @GetMapping("/admin")
    public String admin_dashboard(Model model) {
        Map<String, Integer> graphData = new TreeMap<>();
        graphData.put("2016", 147);
        graphData.put("2017", 1256);
        graphData.put("2018", 3856);
        graphData.put("2019", 19807);
        model.addAttribute("chartData", graphData);
        model.addAttribute("title", "Forside");
        return "admin/dashboard";
    }
}

