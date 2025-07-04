package com.spring.biblio.services;

import com.spring.biblio.entities.*;
import com.spring.biblio.repositories.*;
import com.spring.biblio.services.exception.PretException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class PretService {

    @Autowired
    private AdherentRepository adherentRepository;
    @Autowired
    private ExemplaireRepository exemplaireRepository;
    @Autowired
    private PretRepository pretRepository;
    @Autowired
    private AbonnementRepository abonnementRepository;

    @Transactional
    public Pret creerNouveauPret(Long idAdherent, Long idExemplaire) throws PretException {

        // Étape 1: Récupération des entités
        Adherent adherent = adherentRepository.findById(idAdherent)
                .orElseThrow(() -> new PretException("Adhérent introuvable (ID: " + idAdherent + ")."));
        Exemplaire exemplaire = exemplaireRepository.findById(idExemplaire)
                .orElseThrow(() -> new PretException("Exemplaire introuvable (ID: " + idExemplaire + ")."));

        // Étape 2: Vérification des règles métier
        verifierDisponibiliteExemplaire(exemplaire);
        verifierAbonnementAdherent(adherent);
        verifierQuotaAdherent(adherent);

        // Étape 3: Création et sauvegarde du prêt
        return creerEtSauvegarderPret(adherent, exemplaire);
    }

    private void verifierDisponibiliteExemplaire(Exemplaire exemplaire) throws PretException {
        if (pretRepository.existsByExemplaireAndDateRemiseIsNull(exemplaire)) {
            throw new PretException("Cet exemplaire (ID: " + exemplaire.getIdExemplaire() + ") est déjà emprunté.");
        }
    }

    private void verifierAbonnementAdherent(Adherent adherent) throws PretException {
        if (!abonnementRepository.hasActiveSubscription(adherent, LocalDate.now())) {
            throw new PretException("L'adhérent n'a pas d'abonnement valide. Prêt refusé.");
        }
    }

    private void verifierQuotaAdherent(Adherent adherent) throws PretException {
        Quota reglesAdherent = adherent.getTypeAdherent().getQuota();
        if (reglesAdherent == null) {
            throw new PretException("Aucune règle de prêt (quota) n'est définie pour le type: "
                    + adherent.getTypeAdherent().getNomType());
        }

        long empruntsEnCours = pretRepository.countByAdherentAndDateRemiseIsNull(adherent);
        if (empruntsEnCours >= reglesAdherent.getNombreMaxEmprunt()) {
            throw new PretException("Quota atteint. L'adhérent a déjà " + empruntsEnCours + " livre(s) en prêt.");
        }
    }

    private Pret creerEtSauvegarderPret(Adherent adherent, Exemplaire exemplaire) {
        Pret nouveauPret = new Pret();
        nouveauPret.setAdherent(adherent);
        nouveauPret.setExemplaire(exemplaire);
        nouveauPret.setDateEmprunt(LocalDate.now());

        Quota reglesAdherent = adherent.getTypeAdherent().getQuota();
        int dureePretEnJours = reglesAdherent.getDureePretJours();
        nouveauPret.setDateFinPrevue(LocalDate.now().plusDays(dureePretEnJours));

        return pretRepository.save(nouveauPret);
    }
}
