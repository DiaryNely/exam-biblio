package com.spring.biblio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biblio.entities.Exemplaire;
import com.spring.biblio.repositories.ExemplaireRepository;

@Service
public class LivreService {

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    public List<Exemplaire> listerExemplairesDisponibles() {
        return exemplaireRepository.findExemplairesDisponibles();
    }
}