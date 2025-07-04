package com.spring.biblio.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.biblio.dto.LivreStatDto;
import com.spring.biblio.entities.Adherent;
import com.spring.biblio.entities.Exemplaire;
import com.spring.biblio.entities.Pret;

public interface PretRepository extends JpaRepository<Pret, Long> {

    long countByDateRemiseIsNull();

    long countByDateFinPrevueBeforeAndDateRemiseIsNull(LocalDate date);

    @Query("SELECT new com.spring.biblio.dto.LivreStatDto(l.titre, COUNT(p.id)) " +
            "FROM Pret p " +
            "JOIN p.exemplaire ex " +
            "JOIN ex.livre l " +
            "GROUP BY l.titre " +
            "ORDER BY COUNT(p.id) DESC")
    List<LivreStatDto> findTopPopularBooks(Pageable pageable); // <--- Changement de nom pour la clarté

    default List<LivreStatDto> findTop5PopularBooks() {
        return findTopPopularBooks(PageRequest.of(0, 5));
    }

    List<Pret> findTop5ByOrderByDateEmpruntDesc();

    long countByAdherentAndDateRemiseIsNull(Adherent adherent);

    boolean existsByExemplaireAndDateRemiseIsNull(Exemplaire exemplaire);
}