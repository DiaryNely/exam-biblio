package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "type_status")
public class TypeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_status")
    private Long idTypeStatus;

    @Column(name = "libelle", length = 50)
    private String libelle;

    @ManyToMany(mappedBy = "typeStatuses")
    private Set<Pret> prets;

    @ManyToMany(mappedBy = "typeStatuses")
    private Set<Abonnement> abonnements;

    @ManyToMany(mappedBy = "typeStatuses")
    private Set<Paiement> paiements;

    @ManyToMany(mappedBy = "typeStatuses")
    private Set<Exemplaire> exemplaires;

    // Constructeurs
    public TypeStatus() {
    }

    public TypeStatus(String libelle) {
        this.libelle = libelle;
    }

    // Getters et Setters
    public Long getIdTypeStatus() {
        return idTypeStatus;
    }

    public void setIdTypeStatus(Long idTypeStatus) {
        this.idTypeStatus = idTypeStatus;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Pret> getPrets() {
        return prets;
    }

    public void setPrets(Set<Pret> prets) {
        this.prets = prets;
    }

    public Set<Abonnement> getAbonnements() {
        return abonnements;
    }

    public void setAbonnements(Set<Abonnement> abonnements) {
        this.abonnements = abonnements;
    }

    public Set<Paiement> getPaiements() {
        return paiements;
    }

    public void setPaiements(Set<Paiement> paiements) {
        this.paiements = paiements;
    }

    public Set<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(Set<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }
}
