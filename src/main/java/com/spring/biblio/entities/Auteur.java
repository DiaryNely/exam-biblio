package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "auteurs")
public class Auteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auteur")
    private Long idAuteur;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;

    @Column(name = "date_de_naissance")
    private LocalDate dateDeNaissance;

    @ManyToMany(mappedBy = "auteurs")
    private Set<Livre> livres;

    // Constructeurs
    public Auteur() {
    }

    public Auteur(String nom, String prenom, LocalDate dateDeNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
    }

    // Getters et Setters
    public Long getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(Long idAuteur) {
        this.idAuteur = idAuteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public Set<Livre> getLivres() {
        return livres;
    }

    public void setLivres(Set<Livre> livres) {
        this.livres = livres;
    }
}