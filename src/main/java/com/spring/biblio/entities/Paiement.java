package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "paiement")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paiement")
    private Long idPaiement;

    @Column(name = "validated_at")
    private LocalDate validatedAt;

    @ManyToOne
    @JoinColumn(name = "id_abonnement", nullable = false)
    private Abonnement abonnement;

    @OneToMany(mappedBy = "paiement")
    private Set<PaiementDetail> paiementDetails;

    @ManyToMany
    @JoinTable(name = "paiement_status", joinColumns = @JoinColumn(name = "id_paiement"), inverseJoinColumns = @JoinColumn(name = "id_type_status"))
    private Set<TypeStatus> typeStatuses;

    // Constructeurs
    public Paiement() {
    }

    public Paiement(LocalDate validatedAt, Abonnement abonnement) {
        this.validatedAt = validatedAt;
        this.abonnement = abonnement;
    }

    // Getters et Setters
    public Long getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(Long idPaiement) {
        this.idPaiement = idPaiement;
    }

    public LocalDate getValidatedAt() {
        return validatedAt;
    }

    public void setValidatedAt(LocalDate validatedAt) {
        this.validatedAt = validatedAt;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }

    public Set<PaiementDetail> getPaiementDetails() {
        return paiementDetails;
    }

    public void setPaiementDetails(Set<PaiementDetail> paiementDetails) {
        this.paiementDetails = paiementDetails;
    }

    public Set<TypeStatus> getTypeStatuses() {
        return typeStatuses;
    }

    public void setTypeStatuses(Set<TypeStatus> typeStatuses) {
        this.typeStatuses = typeStatuses;
    }
}