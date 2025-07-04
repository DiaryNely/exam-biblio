package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "paiement_details")
public class PaiementDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paiement_detaiil")
    private Long idPaiementDetail;

    @Column(name = "date_paiement")
    private LocalDate datePaiement;

    @Column(name = "montant_donne", precision = 10, scale = 2)
    private BigDecimal montantDonne;

    @Column(name = "retour", precision = 10, scale = 2)
    private BigDecimal retour;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_paiement", nullable = false)
    private Paiement paiement;

    // Constructeurs
    public PaiementDetail() {
    }

    public PaiementDetail(LocalDate datePaiement, BigDecimal montantDonne, BigDecimal retour, User user,
            Paiement paiement) {
        this.datePaiement = datePaiement;
        this.montantDonne = montantDonne;
        this.retour = retour;
        this.user = user;
        this.paiement = paiement;
    }

    // Getters et Setters
    public Long getIdPaiementDetail() {
        return idPaiementDetail;
    }

    public void setIdPaiementDetail(Long idPaiementDetail) {
        this.idPaiementDetail = idPaiementDetail;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public BigDecimal getMontantDonne() {
        return montantDonne;
    }

    public void setMontantDonne(BigDecimal montantDonne) {
        this.montantDonne = montantDonne;
    }

    public BigDecimal getRetour() {
        return retour;
    }

    public void setRetour(BigDecimal retour) {
        this.retour = retour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }
}
