//Jonas
package com.example.bilabonnement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Map;
import java.util.TreeMap;


@Controller
public class AdminController {


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Forside");
        return "index";
    }
    @GetMapping("/admin")
    public String admin_dashboard(Model model) {
        model.addAttribute("title", "Forside");
        return "admin/dashboard";
    }
}

