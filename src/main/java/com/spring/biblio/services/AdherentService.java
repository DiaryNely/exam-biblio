package com.spring.biblio.services;

import com.spring.biblio.entities.Adherent;
import com.spring.biblio.entities.TypeAdherent;
import com.spring.biblio.entities.User;
import com.spring.biblio.repositories.AdherentRepository;
import com.spring.biblio.repositories.TypeAdherentRepository;
import com.spring.biblio.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdherentService {

    private final AdherentRepository adherentRepository;
    private final UserRepository userRepository;
    private final TypeAdherentRepository typeAdherentRepository;

    public AdherentService(AdherentRepository adherentRepository,
            UserRepository userRepository,
            TypeAdherentRepository typeAdherentRepository) {
        this.adherentRepository = adherentRepository;
        this.userRepository = userRepository;
        this.typeAdherentRepository = typeAdherentRepository;
    }

    // Créer un nouvel adhérent
    public Adherent createAdherent(Long userId, Long typeAdherentId) {
        Adherent adherent = new Adherent();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        TypeAdherent typeAdherent = typeAdherentRepository.findById(typeAdherentId)
                .orElseThrow(() -> new RuntimeException("Type d'adhérent non trouvé"));

        // Générer un numéro d'adhérent unique
        String numero = generateNumeroAdherent();

        adherent.setUser(user);
        adherent.setTypeAdherent(typeAdherent);
        adherent.setNumero(numero);
        adherent.setDateInscription(LocalDate.now());

        return adherentRepository.save(adherent);
    }

    // Trouver un adhérent par son ID
    public Optional<Adherent> getAdherentById(Long id) {
        return adherentRepository.findById(id);
    }

    // Trouver un adhérent par l'ID utilisateur
    public Optional<Adherent> getAdherentByUserId(Long userId) {
        return adherentRepository.findByUserIdUser(userId);
    }

    // Trouver un adhérent par son username (email)
    public Optional<Adherent> getAdherentByUsername(String username) {
        return adherentRepository.findByUsername(username);
    }

    // Mettre à jour les informations d'un adhérent
    public Adherent updateAdherent(Long id, Adherent adherentDetails) {
        Adherent adherent = adherentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adhérent non trouvé"));

        if (adherentDetails.getTypeAdherent() != null) {
            TypeAdherent typeAdherent = typeAdherentRepository
                    .findById(adherentDetails.getTypeAdherent().getIdTypeAdh())
                    .orElseThrow(() -> new RuntimeException("Type d'adhérent non trouvé"));
            adherent.setTypeAdherent(typeAdherent);
        }

        // Mettre à jour d'autres champs si nécessaire
        // adherent.setAutresChamps(adherentDetails.getAutresChamps());

        return adherentRepository.save(adherent);
    }

    // Supprimer un adhérent
    public void deleteAdherent(Long id) {
        Adherent adherent = adherentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adhérent non trouvé"));
        adherentRepository.delete(adherent);
    }

    // Lister tous les adhérents
    public List<Adherent> getAllAdherents() {
        return adherentRepository.findAll();
    }

    // Générer un numéro d'adhérent unique
    private String generateNumeroAdherent() {
        String prefix = "ADH-";
        String numero;
        do {
            long randomNum = (long) (Math.random() * 1000000);
            numero = prefix + String.format("%06d", randomNum);
        } while (adherentRepository.existsByNumero(numero));

        return numero;
    }

    // Vérifier si un utilisateur est adhérent
    public boolean isUserAdherent(Long userId) {
        return adherentRepository.findByUserIdUser(userId).isPresent();
    }
}