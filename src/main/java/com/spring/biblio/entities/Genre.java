package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre")
    private Long idGenre;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "description", length = 100)
    private String description;

    @OneToMany(mappedBy = "genre")
    private Set<Livre> livres;

    // Constructeurs
    public Genre() {
    }

    public Genre(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    // Getters et Setters
    public Long getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Long idGenre) {
        this.idGenre = idGenre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Livre> getLivres() {
        return livres;
    }

    public void setLivres(Set<Livre> livres) {
        this.livres = livres;
    }
}