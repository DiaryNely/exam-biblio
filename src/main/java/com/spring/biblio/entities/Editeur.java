package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "editeurs")
public class Editeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_editeur")
    private Long idEditeur;

    @Column(name = "nom_editeur", nullable = false, length = 100)
    private String nomEditeur;

    @OneToMany(mappedBy = "editeur")
    private Set<Collection> collections;

    // Constructeurs
    public Editeur() {
    }

    public Editeur(String nomEditeur) {
        this.nomEditeur = nomEditeur;
    }

    // Getters et Setters
    public Long getIdEditeur() {
        return idEditeur;
    }

    public void setIdEditeur(Long idEditeur) {
        this.idEditeur = idEditeur;
    }

    public String getNomEditeur() {
        return nomEditeur;
    }

    public void setNomEditeur(String nomEditeur) {
        this.nomEditeur = nomEditeur;
    }

    public Set<Collection> getCollections() {
        return collections;
    }

    public void setCollections(Set<Collection> collections) {
        this.collections = collections;
    }
}