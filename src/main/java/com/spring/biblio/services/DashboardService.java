package com.spring.biblio.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biblio.repositories.LivreRepository;
import com.spring.biblio.repositories.PretRepository;

@Service
public class DashboardService {

    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private PretRepository pretRepository;

    public Map<String, Object> getDashboardData() {
        Map<String, Object> data = new HashMap<>();

        // 1. KPIs
        data.put("nombreTotalLivres", livreRepository.count());
        data.put("nombreEmpruntsEnCours", pretRepository.countByDateRemiseIsNull());
        data.put("nombreLivresEnRetard", pretRepository.countByDateFinPrevueBeforeAndDateRemiseIsNull(LocalDate.now()));

        // 2. Données pour le graphique
        data.put("livresPopulaires", pretRepository.findTop5PopularBooks());

        // 3. Liste des derniers emprunts
        data.put("derniersEmprunts", pretRepository.findTop5ByOrderByDateEmpruntDesc());

        return data;
    }
}