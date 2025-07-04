package com.spring.biblio.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.biblio.services.DashboardService;

// 1. @Controller : Indique à Spring que cette classe est un contrôleur web.
@Controller
public class DashboardController {

    // @Autowired : Spring va automatiquement nous fournir une instance de
    // DashboardService.
    @Autowired
    private DashboardService dashboardService;

    // 2. @GetMapping : Lie l'URL "/dashboard" à cette méthode.
    @GetMapping("/dashboard")
    public String showDashboard(Model model) { // Spring nous fournit l'objet 'Model'.

        // 3. Délégation : Le contrôleur demande au service de faire le travail.
        Map<String, Object> dashboardData = dashboardService.getDashboardData();

        // 4. Préparation des données : On met toutes les données du service dans le
        // "colis" (Model)
        // pour que Thymeleaf puisse les utiliser.
        model.addAllAttributes(dashboardData);

        // 5. Choix de la vue : On dit à Spring d'afficher la page "dashboard.html".
        return "dashboard/dashboard";
    }
}