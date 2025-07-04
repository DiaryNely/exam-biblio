package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "quota")
public class Quota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quota")
    private Long idQuota;

    @Column(name = "nbr_max", nullable = false)
    private Integer nbrMax;

    @Column(name = "duree_max")
    private LocalTime dureeMax;

    @ManyToOne
    @JoinColumn(name = "id_type_adh", nullable = false)
    private TypeAdherent typeAdherent;

    // Constructeurs
    public Quota() {
    }

    public Quota(Integer nbrMax, LocalTime dureeMax, TypeAdherent typeAdherent) {
        this.nbrMax = nbrMax;
        this.dureeMax = dureeMax;
        this.typeAdherent = typeAdherent;
    }

    // Getters et Setters
    public Long getIdQuota() {
        return idQuota;
    }

    public void setIdQuota(Long idQuota) {
        this.idQuota = idQuota;
    }

    public Integer getNbrMax() {
        return nbrMax;
    }

    public void setNbrMax(Integer nbrMax) {
        this.nbrMax = nbrMax;
    }

    public LocalTime getDureeMax() {
        return dureeMax;
    }

    public void setDureeMax(LocalTime dureeMax) {
        this.dureeMax = dureeMax;
    }

    public TypeAdherent getTypeAdherent() {
        return typeAdherent;
    }

    public void setTypeAdherent(TypeAdherent typeAdherent) {
        this.typeAdherent = typeAdherent;
    }
}