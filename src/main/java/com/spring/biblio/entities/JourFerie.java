package com.spring.biblio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jour_ferie")
public class JourFerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jour_ferie")
    private Long idJourFerie;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", length = 100)
    private String description;

    // Constructeurs
    public JourFerie() {
    }

    public JourFerie(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters et Setters
    public Long getIdJourFerie() {
        return idJourFerie;
    }

    public void setIdJourFerie(Long idJourFerie) {
        this.idJourFerie = idJourFerie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}