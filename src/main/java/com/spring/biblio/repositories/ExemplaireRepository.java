package com.spring.biblio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.biblio.entities.Exemplaire;

// @Repository est optionnel car JpaRepository est déjà détecté par Spring, mais c'est une bonne pratique.
@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {
    // Aucune méthode supplémentaire n'est nécessaire pour l'instant.
    // Les méthodes comme findById(), save(), etc., sont héritées de JpaRepository.

    @Query("SELECT ex FROM Exemplaire ex WHERE ex.id NOT IN " +
            "(SELECT p.exemplaire.id FROM Pret p WHERE p.dateRemise IS NULL)")
    List<Exemplaire> findExemplairesDisponibles();
}