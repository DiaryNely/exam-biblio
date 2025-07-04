package com.spring.biblio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.biblio.entities.Exemplaire;
import com.spring.biblio.services.LivreService;
import com.spring.biblio.services.PretService;
import com.spring.biblio.services.exception.PretException;

@Controller
public class PretController {

    @Autowired
    private PretService pretService;

    @Autowired
    private LivreService livreService;

    @GetMapping("/prets/nouveau")
    public String afficherFormulairePret(Model model) {
        // Récupérer la liste des exemplaires disponibles
        List<Exemplaire> exemplairesDisponibles = livreService.listerExemplairesDisponibles();

        // Ajouter cette liste au modèle pour que la page JSP puisse l'utiliser
        model.addAttribute("exemplairesDisponibles", exemplairesDisponibles);

        return "prets/formulaire-pret";
    }

    @PostMapping("/prets/enregistrer")
    public String enregistrerPret(@RequestParam Long idAdherent,
            @RequestParam Long idExemplaire,
            RedirectAttributes redirectAttributes) {
        try {
            pretService.creerNouveauPret(idAdherent, idExemplaire);
            redirectAttributes.addFlashAttribute("successMessage", "Le prêt a été enregistré avec succès !");
            return "redirect:/dashboard";
        } catch (PretException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/prets/nouveau";
        }
    }
}
