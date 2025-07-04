package com.spring.biblio.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.biblio.dto.LivreStatDto;
import com.spring.biblio.entities.Pret;

public interface PretRepository extends JpaRepository<Pret, Long> {

    // KPI: Nombre d'emprunts en cours
    long countByDateRemiseIsNull();

    // KPI: Nombre de livres en retard
    long countByDateFinPrevueBeforeAndDateRemiseIsNull(LocalDate date);

    // Graphique: Livres les plus populaires
    // J'ai renommé cette méthode pour plus de clarté, elle reste privée à
    // l'interface
    @Query("SELECT new com.spring.biblio.dto.LivreStatDto(l.titre, COUNT(p.id)) " +
            "FROM Pret p " +
            "JOIN p.exemplaire ex " +
            "JOIN ex.livre l " +
            "GROUP BY l.titre " +
            "ORDER BY COUNT(p.id) DESC")
    List<LivreStatDto> findTopPopularBooks(Pageable pageable); // <--- Changement de nom pour la clarté

    // --- NOUVELLE MÉTHODE DE COMMODITÉ ---
    /**
     * C'est la méthode que votre service doit appeler.
     * Elle ne prend aucun argument et appelle l'autre méthode en lui fournissant
     * un Pageable qui limite le résultat aux 5 premiers.
     */
    default List<LivreStatDto> findTop5PopularBooks() {
        return findTopPopularBooks(PageRequest.of(0, 5));
    }
    // ---------------------------------------

    // Liste: 5 derniers prêts effectués
    List<Pret> findTop5ByOrderByDateEmpruntDesc();
}