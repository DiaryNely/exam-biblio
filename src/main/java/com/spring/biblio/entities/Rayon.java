package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rayons")
public class Rayon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rayon")
    private Long idRayon;

    @Column(name = "nom_rayon", nullable = false, length = 50)
    private String nomRayon;

    @OneToMany(mappedBy = "rayon")
    private Set<Etagere> etageres;

    // Constructeurs
    public Rayon() {
    }

    public Rayon(String nomRayon) {
        this.nomRayon = nomRayon;
    }

    // Getters et Setters
    public Long getIdRayon() {
        return idRayon;
    }

    public void setIdRayon(Long idRayon) {
        this.idRayon = idRayon;
    }

    public String getNomRayon() {
        return nomRayon;
    }

    public void setNomRayon(String nomRayon) {
        this.nomRayon = nomRayon;
    }

    public Set<Etagere> getEtageres() {
        return etageres;
    }

    public void setEtageres(Set<Etagere> etageres) {
        this.etageres = etageres;
    }
}
