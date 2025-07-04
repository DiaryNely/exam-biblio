package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "localisations")
public class Localisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_localisation")
    private Long idLocalisation;

    @Column(name = "nom_zone", length = 50)
    private String nomZone;

    @Column(name = "niveau", nullable = false)
    private Integer niveau;

    @ManyToOne
    @JoinColumn(name = "id_etagere", nullable = false)
    private Etagere etagere;

    @OneToMany(mappedBy = "localisation")
    private Set<Exemplaire> exemplaires;

    // Constructeurs
    public Localisation() {
    }

    public Localisation(String nomZone, Integer niveau, Etagere etagere) {
        this.nomZone = nomZone;
        this.niveau = niveau;
        this.etagere = etagere;
    }

    // Getters et Setters
    public Long getIdLocalisation() {
        return idLocalisation;
    }

    public void setIdLocalisation(Long idLocalisation) {
        this.idLocalisation = idLocalisation;
    }

    public String getNomZone() {
        return nomZone;
    }

    public void setNomZone(String nomZone) {
        this.nomZone = nomZone;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public Etagere getEtagere() {
        return etagere;
    }

    public void setEtagere(Etagere etagere) {
        this.etagere = etagere;
    }

    public Set<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(Set<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }
}