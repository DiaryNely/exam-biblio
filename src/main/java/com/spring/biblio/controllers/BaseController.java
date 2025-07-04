package com.spring.biblio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class BaseController {
    protected String chargerTemplate(Model model, String titre, String vue) {
        String vue_2 = vue + ".jsp";
        model.addAttribute("pageTitle", titre);
        model.addAttribute("contentPage", vue_2);
        return "template";
    }
}
