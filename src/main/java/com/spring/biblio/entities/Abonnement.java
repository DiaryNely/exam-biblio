package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "abonnement")
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_abonnement")
    private Long idAbonnement;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "id_adherent", nullable = false)
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "id_type_abonnement", nullable = false)
    private TypeAbonnement typeAbonnement;

    @OneToMany(mappedBy = "abonnement")
    private Set<Paiement> paiements;

    @ManyToMany
    @JoinTable(name = "abonnement_status", joinColumns = @JoinColumn(name = "id_abonnement"), inverseJoinColumns = @JoinColumn(name = "id_type_status"))
    private Set<TypeStatus> typeStatuses;

    // Constructeurs
    public Abonnement() {
    }

    public Abonnement(LocalDate dateDebut, LocalDate dateFin, Adherent adherent, TypeAbonnement typeAbonnement) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.adherent = adherent;
        this.typeAbonnement = typeAbonnement;
    }

    // Getters et Setters
    public Long getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(Long idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public TypeAbonnement getTypeAbonnement() {
        return typeAbonnement;
    }

    public void setTypeAbonnement(TypeAbonnement typeAbonnement) {
        this.typeAbonnement = typeAbonnement;
    }

    public Set<Paiement> getPaiements() {
        return paiements;
    }

    public void setPaiements(Set<Paiement> paiements) {
        this.paiements = paiements;
    }

    public Set<TypeStatus> getTypeStatuses() {
        return typeStatuses;
    }

    public void setTypeStatuses(Set<TypeStatus> typeStatuses) {
        this.typeStatuses = typeStatuses;
    }
}
