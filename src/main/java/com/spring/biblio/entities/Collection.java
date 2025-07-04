package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "collections")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_collection")
    private Long idCollection;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "description", length = 100)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_editeur", nullable = false)
    private Editeur editeur;

    @OneToMany(mappedBy = "collection")
    private Set<Livre> livres;

    // Constructeurs
    public Collection() {
    }

    public Collection(String nom, String description, Editeur editeur) {
        this.nom = nom;
        this.description = description;
        this.editeur = editeur;
    }

    // Getters et Setters
    public Long getIdCollection() {
        return idCollection;
    }

    public void setIdCollection(Long idCollection) {
        this.idCollection = idCollection;
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

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public Set<Livre> getLivres() {
        return livres;
    }

    public void setLivres(Set<Livre> livres) {
        this.livres = livres;
    }
}