package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "prets")
public class Pret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pret")
    private Long idPret;

    @Column(name = "date_emprunt", nullable = false)
    private LocalDate dateEmprunt;

    @Column(name = "date_fin_prevue", nullable = false)
    private LocalDate dateFinPrevue;

    @Column(name = "date_remise", length = 50)
    private String dateRemise;

    @ManyToOne
    @JoinColumn(name = "id_type_pret", nullable = false)
    private TypePret typePret;

    @ManyToOne
    @JoinColumn(name = "id_adherent", nullable = false)
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @ManyToMany
    @JoinTable(name = "status_pret", joinColumns = @JoinColumn(name = "id_pret"), inverseJoinColumns = @JoinColumn(name = "id_type_status"))
    private Set<TypeStatus> typeStatuses;

    // Constructeurs
    public Pret() {
    }

    public Pret(LocalDate dateEmprunt, LocalDate dateFinPrevue, String dateRemise,
            TypePret typePret, Adherent adherent, Exemplaire exemplaire) {
        this.dateEmprunt = dateEmprunt;
        this.dateFinPrevue = dateFinPrevue;
        this.dateRemise = dateRemise;
        this.typePret = typePret;
        this.adherent = adherent;
        this.exemplaire = exemplaire;
    }

    // Getters et Setters
    public Long getIdPret() {
        return idPret;
    }

    public void setIdPret(Long idPret) {
        this.idPret = idPret;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateFinPrevue() {
        return dateFinPrevue;
    }

    public void setDateFinPrevue(LocalDate dateFinPrevue) {
        this.dateFinPrevue = dateFinPrevue;
    }

    public String getDateRemise() {
        return dateRemise;
    }

    public void setDateRemise(String dateRemise) {
        this.dateRemise = dateRemise;
    }

    public TypePret getTypePret() {
        return typePret;
    }

    public void setTypePret(TypePret typePret) {
        this.typePret = typePret;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Set<TypeStatus> getTypeStatuses() {
        return typeStatuses;
    }

    public void setTypeStatuses(Set<TypeStatus> typeStatuses) {
        this.typeStatuses = typeStatuses;
    }
}