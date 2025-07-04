package com.spring.biblio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
// Supprimer l'import java.time.LocalTime;

@Entity
@Table(name = "quota")
public class Quota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quota")
    private Long idQuota;

    // Renommé pour la clarté, correspond à la colonne nbr_max
    @Column(name = "nbr_max", nullable = false)
    private Integer nombreMaxEmprunt;

    // --- CORRECTION MAJEURE ICI ---
    // 'duree_max' doit représenter un nombre de jours, donc un Integer.
    // L'utilisation de LocalTime est incorrecte pour une durée.
    @Column(name = "duree_max")
    private Integer dureePretJours;

    @ManyToOne(fetch = FetchType.LAZY) // LAZY est une meilleure pratique
    @JoinColumn(name = "id_type_adh", nullable = false)
    private TypeAdherent typeAdherent;

    // Constructeurs, Getters et Setters mis à jour...

    public Quota() {
    }

    // Getter pour le nombre max
    public Integer getNombreMaxEmprunt() {
        return nombreMaxEmprunt;
    }

    public void setNombreMaxEmprunt(Integer nombreMaxEmprunt) {
        this.nombreMaxEmprunt = nombreMaxEmprunt;
    }

    // Getter pour la durée
    public Integer getDureePretJours() {
        return dureePretJours;
    }

    public void setDureePretJours(Integer dureePretJours) {
        this.dureePretJours = dureePretJours;
    }

    // Autres getters et setters
    public Long getIdQuota() {
        return idQuota;
    }

    public void setIdQuota(Long idQuota) {
        this.idQuota = idQuota;
    }

    public TypeAdherent getTypeAdherent() {
        return typeAdherent;
    }

    public void setTypeAdherent(TypeAdherent typeAdherent) {
        this.typeAdherent = typeAdherent;
    }
}