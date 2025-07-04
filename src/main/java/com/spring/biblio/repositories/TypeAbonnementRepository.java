package com.spring.biblio.repositories;

import com.spring.biblio.entities.TypeAbonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeAbonnementRepository extends JpaRepository<TypeAbonnement, Long> {
    List<TypeAbonnement> findAllByOrderByPrixAsc();
}
