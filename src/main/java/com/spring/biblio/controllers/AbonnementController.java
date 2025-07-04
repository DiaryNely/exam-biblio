package com.spring.biblio.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.biblio.entities.Abonnement;
import com.spring.biblio.entities.Adherent;
import com.spring.biblio.entities.TypeAbonnement;
import com.spring.biblio.entities.User;
import com.spring.biblio.services.AbonnementService;
import com.spring.biblio.services.AdherentService;
import com.spring.biblio.services.TypeAbonnementService;
import com.spring.biblio.services.TypeAdherentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/abonnements")
public class AbonnementController {

    private final AbonnementService abonnementService;
    private final TypeAbonnementService typeAbonnementService;
    private final AdherentService adherentService;
    private final TypeAdherentService typeAdherentService;

    public AbonnementController(AbonnementService abonnementService,
            TypeAbonnementService typeAbonnementService,
            AdherentService adherentService,
            TypeAdherentService typeAdherentService) {
        this.abonnementService = abonnementService;
        this.typeAbonnementService = typeAbonnementService;
        this.adherentService = adherentService;
        this.typeAdherentService = typeAdherentService;
    }

    @GetMapping
    public String showAbonnementsPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        // Changement ici: utiliser le même nom d'attribut que dans le JSP
        Adherent adherent = adherentService.getAdherentByUsername(user.getEmail())
                .orElse(null);
        model.addAttribute("adherent", adherent); // <-- Cette ligne est cruciale
        model.addAttribute("typesAbonnement", typeAbonnementService.getAllTypesAbonnement());

        Abonnement currentAbonnement = null;
        if (adherent != null) {
            currentAbonnement = abonnementService.getCurrentAbonnement(adherent.getIdAdherent());
            if (currentAbonnement == null) {
                currentAbonnement = new Abonnement();
                currentAbonnement.setAdherent(adherent);
            }
        }
        model.addAttribute("currentAbonnement", currentAbonnement);
        return "abonnement/listeAbonnements";
    }

    @PostMapping("/souscrire")
    public String souscrireAbonnement(@RequestParam Long typeAbonnementId,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        return "redirect:/abonnements/paiement/" + typeAbonnementId;
    }

    @GetMapping("/paiement/{typeAbonnementId}")
    public String showPaiementPage(@PathVariable Long typeAbonnementId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        TypeAbonnement typeAbonnement = typeAbonnementService.getTypeAbonnementById(typeAbonnementId)
                .orElseThrow(() -> new RuntimeException("Type d'abonnement non trouvé"));
        model.addAttribute("typesAdherent", typeAdherentService.getAllTypesAdherent());

        model.addAttribute("typeAbonnement", typeAbonnement);
        return "abonnement/paiementAbonnement";
    }

    @PostMapping("/confirmer-paiement")
    public String confirmerPaiement(
            @RequestParam Long typeAbonnementId,
            @RequestParam int durationMonths,
            @RequestParam Long typeAdherentId,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        // Vérification simple de la durée (optionnel)
        if (durationMonths < 1 || durationMonths > 12) {
            redirectAttributes.addFlashAttribute("error", "Durée d'abonnement invalide.");
            return "redirect:/abonnements";
        }

        Abonnement abonnement = abonnementService.souscrireAbonnement(user.getIdUser(), typeAbonnementId,
                durationMonths, typeAdherentId);
        if (abonnement == null) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la souscription à l'abonnement");
        } else {
            redirectAttributes.addFlashAttribute("success", "Abonnement souscrit avec succès !");
            redirectAttributes.addFlashAttribute("abonnement", abonnement);
            redirectAttributes.addFlashAttribute("durationMonths", durationMonths);
        }
        return "redirect:/abonnements";
    }

    @GetMapping("/confirmation")
    public String showConfirmationPage() {
        return "confirmationAbonnement";
    }

    @GetMapping("/historique")
    public String showHistoriquePage(Principal principal, Model model) {
        String username = principal.getName();
        Adherent adherent = adherentService.getAdherentByUsername(username)
                .orElseThrow(() -> new RuntimeException("Adhérent non trouvé"));

        model.addAttribute("abonnements", abonnementService.getHistoriqueAbonnements(adherent.getIdAdherent()));
        return "historiqueAbonnements";
    }
}