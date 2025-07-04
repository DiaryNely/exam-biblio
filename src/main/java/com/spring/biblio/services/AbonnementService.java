package com.spring.biblio.services;

import com.spring.biblio.entities.Abonnement;
import com.spring.biblio.entities.Adherent;
import com.spring.biblio.entities.TypeAbonnement;
import com.spring.biblio.repositories.AbonnementRepository;
import com.spring.biblio.repositories.TypeAbonnementRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AbonnementService {

    private final TypeAbonnementRepository typeAbonnementRepository;
    private final AbonnementRepository abonnementRepository;
    private final AdherentService adherentService;

    public AbonnementService(TypeAbonnementRepository typeAbonnementRepository,
            AbonnementRepository abonnementRepository,
            AdherentService adherentService) {
        this.typeAbonnementRepository = typeAbonnementRepository;
        this.abonnementRepository = abonnementRepository;
        this.adherentService = adherentService;
    }

    public List<TypeAbonnement> getAllTypesAbonnement() {
        return typeAbonnementRepository.findAllByOrderByPrixAsc();
    }

    public Abonnement souscrireAbonnement(Long userId, Long typeAbonnementId, int duree, Long typeAdherentId) {

        Adherent adherent = adherentService.getAdherentByUserId(userId)
                .orElse(null);
        if (adherent == null) {
            // Si l'adhérent n'existe pas, on le crée
            adherent = adherentService.createAdherent(userId, typeAdherentId);
        }

        TypeAbonnement typeAbonnement = typeAbonnementRepository.findById(typeAbonnementId)
                .orElseThrow(() -> new RuntimeException("Type d'abonnement non trouvé"));

        // Vérifier si l'adhérent a déjà un abonnement en cours
        if (abonnementRepository.existsByAdherentAndDateFinAfter(adherent, LocalDate.now())) {
            throw new RuntimeException("Vous avez déjà un abonnement en cours");
        }

        LocalDate dateFin = LocalDate.now().plusMonths(duree);

        Abonnement abonnement = new Abonnement();
        abonnement.setAdherent(adherent);
        abonnement.setTypeAbonnement(typeAbonnement);
        abonnement.setDateDebut(LocalDate.now());
        abonnement.setDateFin(dateFin);

        return abonnementRepository.save(abonnement);
    }

    public List<Abonnement> getHistoriqueAbonnements(Long adherentId) {
        Adherent adherent = adherentService.getAdherentById(adherentId)
                .orElseThrow(() -> new RuntimeException("Adhérent non trouvé"));
        return abonnementRepository.findByAdherent(adherent);
    }

    public Abonnement getCurrentAbonnement(Long adherentId) {
        Adherent adherent = adherentService.getAdherentById(adherentId)
                .orElseThrow(() -> new RuntimeException("Adhérent non trouvé"));
        Abonnement abonnement = abonnementRepository.findFirstByAdherentAndDateFinAfter(adherent, LocalDate.now());
        if (abonnement == null) {
            throw new RuntimeException("Aucun abonnement actif trouvé pour cet adhérent");
        }
        return abonnement;
    }
}