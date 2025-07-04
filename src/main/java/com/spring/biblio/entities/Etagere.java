package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "etageres")
public class Etagere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etagere")
    private Long idEtagere;

    @Column(name = "nom_etagere", nullable = false, length = 50)
    private String nomEtagere;

    @Column(name = "nbr_niveau", nullable = false)
    private Integer nbrNiveau;

    @ManyToOne
    @JoinColumn(name = "id_rayon", nullable = false)
    private Rayon rayon;

    @OneToMany(mappedBy = "etagere")
    private Set<Localisation> localisations;

    // Constructeurs
    public Etagere() {
    }

    public Etagere(String nomEtagere, Integer nbrNiveau, Rayon rayon) {
        this.nomEtagere = nomEtagere;
        this.nbrNiveau = nbrNiveau;
        this.rayon = rayon;
    }

    // Getters et Setters
    public Long getIdEtagere() {
        return idEtagere;
    }

    public void setIdEtagere(Long idEtagere) {
        this.idEtagere = idEtagere;
    }

    public String getNomEtagere() {
        return nomEtagere;
    }

    public void setNomEtagere(String nomEtagere) {
        this.nomEtagere = nomEtagere;
    }

    public Integer getNbrNiveau() {
        return nbrNiveau;
    }

    public void setNbrNiveau(Integer nbrNiveau) {
        this.nbrNiveau = nbrNiveau;
    }

    public Rayon getRayon() {
        return rayon;
    }

    public void setRayon(Rayon rayon) {
        this.rayon = rayon;
    }

    public Set<Localisation> getLocalisations() {
        return localisations;
    }

    public void setLocalisations(Set<Localisation> localisations) {
        this.localisations = localisations;
    }
}