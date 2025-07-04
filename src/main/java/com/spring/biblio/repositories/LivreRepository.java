package com.spring.biblio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.biblio.entities.Livre;

public interface LivreRepository extends JpaRepository<Livre, Long> {
    // Le count est fourni par JpaRepository
}