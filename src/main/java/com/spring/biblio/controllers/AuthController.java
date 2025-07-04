package com.spring.biblio.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.biblio.entities.User;
import com.spring.biblio.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController extends BaseController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session, Model model) {
        Optional<User> existingUser = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (existingUser.isPresent()) {
            System.out.println("User found: " + existingUser.get().getEmail());
            session.setAttribute("user", existingUser.get());
            return "redirect:/abonnements";
            // return chargerTemplate(model, "Home | Biblio", "home");
        } else {
            model.addAttribute("error", "Email ou mot de passe incorrect.");
            return "auth/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
