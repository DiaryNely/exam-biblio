package com.spring.biblio.repositories;

import com.spring.biblio.entities.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Long> {

    // Trouver un adhérent par l'ID de l'utilisateur associé
    Optional<Adherent> findByUserIdUser(Long userId);

    // Trouver un adhérent par le nom d'utilisateur (email)
    @Query("SELECT a FROM Adherent a JOIN a.user u WHERE u.email = :username")
    Optional<Adherent> findByUsername(@Param("username") String username);

    // Vérifier si un numéro d'adhérent existe déjà
    boolean existsByNumero(String numero);

    // Trouver un adhérent par son numéro
    Optional<Adherent> findByNumero(String numero);
}