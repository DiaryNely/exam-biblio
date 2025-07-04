package com.spring.biblio.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.biblio.entities.Abonnement;
import com.spring.biblio.entities.Adherent;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    List<Abonnement> findByAdherent(Adherent adherent);

    boolean existsByAdherentAndDateFinAfter(Adherent adherent, LocalDate date);

    Abonnement findFirstByAdherentAndDateFinAfter(Adherent adherent, LocalDate date);

    Abonnement findFirstByAdherentAndDateFinAfterOrderByDateFinDesc(Adherent adherent, LocalDate date);

}