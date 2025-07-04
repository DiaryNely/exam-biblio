package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "type_abonnement")
public class TypeAbonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_abonnement")
    private Long idTypeAbonnement;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "prix", nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    @Column(name = "surplus_quota")
    private Integer surplusQuota;

    @Column(name = "priorite")
    private Integer priorite;

    @OneToMany(mappedBy = "typeAbonnement")
    private Set<Abonnement> abonnements;

    // Constructeurs
    public TypeAbonnement() {
    }

    public TypeAbonnement(String name, String description, BigDecimal prix, Integer surplusQuota, Integer priorite) {
        this.name = name;
        this.description = description;
        this.prix = prix;
        this.surplusQuota = surplusQuota;
        this.priorite = priorite;
    }

    // Getters et Setters
    public Long getIdTypeAbonnement() {
        return idTypeAbonnement;
    }

    public void setIdTypeAbonnement(Long idTypeAbonnement) {
        this.idTypeAbonnement = idTypeAbonnement;
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

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Integer getSurplusQuota() {
        return surplusQuota;
    }

    public void setSurplusQuota(Integer surplusQuota) {
        this.surplusQuota = surplusQuota;
    }

    public Integer getPriorite() {
        return priorite;
    }

    public void setPriorite(Integer priorite) {
        this.priorite = priorite;
    }

    public Set<Abonnement> getAbonnements() {
        return abonnements;
    }

    public void setAbonnements(Set<Abonnement> abonnements) {
        this.abonnements = abonnements;
    }
}