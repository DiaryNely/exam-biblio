package com.spring.biblio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.biblio.entities.TypeAbonnement;
import com.spring.biblio.repositories.TypeAbonnementRepository;

@Service
public class TypeAbonnementService {

    private final TypeAbonnementRepository typeAbonnementRepository;

    public TypeAbonnementService(TypeAbonnementRepository typeAbonnementRepository) {
        this.typeAbonnementRepository = typeAbonnementRepository;
    }

    public List<TypeAbonnement> getAllTypesAbonnement() {
        return typeAbonnementRepository.findAllByOrderByPrixAsc();
    }

    public Optional<TypeAbonnement> getTypeAbonnementById(Long id) {
        return typeAbonnementRepository.findById(id);
    }
}
