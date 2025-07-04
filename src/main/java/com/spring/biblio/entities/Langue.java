package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "langues")
public class Langue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_langue")
    private Long idLangue;

    @Column(name = "nom_langue", nullable = false, length = 50)
    private String nomLangue;

    @Column(name = "description", length = 100)
    private String description;

    @OneToMany(mappedBy = "langue")
    private Set<Livre> livres;

    // Constructeurs
    public Langue() {
    }

    public Langue(String nomLangue, String description) {
        this.nomLangue = nomLangue;
        this.description = description;
    }

    // Getters et Setters
    public Long getIdLangue() {
        return idLangue;
    }

    public void setIdLangue(Long idLangue) {
        this.idLangue = idLangue;
    }

    public String getNomLangue() {
        return nomLangue;
    }

    public void setNomLangue(String nomLangue) {
        this.nomLangue = nomLangue;
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