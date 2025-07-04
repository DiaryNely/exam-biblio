package com.spring.biblio.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "type_adherent")
public class TypeAdherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_adh")
    private Long idTypeAdh;

    @Column(name = "nom_type", nullable = false, length = 50)
    private String nomType;

    @OneToMany(mappedBy = "typeAdherent")
    private Set<Adherent> adherents;

    @OneToMany(mappedBy = "typeAdherent")
    private Set<Quota> quotas;

    @ManyToMany(mappedBy = "typesAdherents")
    private Set<Livre> livres;

    @OneToOne(mappedBy = "typeAdherent", fetch = FetchType.LAZY)
    private Quota quota;

    // Constructeurs
    public TypeAdherent() {
    }

    public TypeAdherent(String nomType) {
        this.nomType = nomType;
    }

    // Getters et Setters
    public Long getIdTypeAdh() {
        return idTypeAdh;
    }

    public void setIdTypeAdh(Long idTypeAdh) {
        this.idTypeAdh = idTypeAdh;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public Set<Adherent> getAdherents() {
        return adherents;
    }

    public void setAdherents(Set<Adherent> adherents) {
        this.adherents = adherents;
    }

    public Set<Quota> getQuotas() {
        return quotas;
    }

    public void setQuotas(Set<Quota> quotas) {
        this.quotas = quotas;
    }

    public Set<Livre> getLivres() {
        return livres;
    }

    public void setLivres(Set<Livre> livres) {
        this.livres = livres;
    }

    public Quota getQuota() {
        return quota;
    }

    public void setQuota(Quota quota) {
        this.quota = quota;
    }
}
