package com.example.bilabonnement.controllers;

import com.example.bilabonnement.db.DB;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
public class MainController {


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

