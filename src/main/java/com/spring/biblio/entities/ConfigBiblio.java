package com.spring.biblio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "config_biblio")
public class ConfigBiblio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_config")
    private Long idConfig;

    @Column(name = "cle", nullable = false, length = 50)
    private String cle;

    @Column(name = "valeur", nullable = false)
    private Integer valeur;

    // Constructeurs
    public ConfigBiblio() {
    }

    public ConfigBiblio(String cle, Integer valeur) {
        this.cle = cle;
        this.valeur = valeur;
    }

    // Getters et Setters
    public Long getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(Long idConfig) {
        this.idConfig = idConfig;
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }
}