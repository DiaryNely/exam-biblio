package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "penalite")
public class Penalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_penalite")
    private Long idPenalite;

    @Column(name = "commentaire", length = 100)
    private String commentaire;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "id_adherent", nullable = false)
    private Adherent adherent;

    @ManyToMany
    @JoinTable(name = "penalite_status", joinColumns = @JoinColumn(name = "id_penalite"), inverseJoinColumns = @JoinColumn(name = "id_type_status"))
    private Set<TypeStatus> typeStatuses;

    // Constructeurs
    public Penalite() {
    }

    public Penalite(String commentaire, LocalDate dateDebut, LocalDate dateFin, Adherent adherent) {
        this.commentaire = commentaire;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.adherent = adherent;
    }

    // Getters et Setters

    public Set<TypeStatus> getTypeStatuses() {
        return typeStatuses;
    }

    public void setTypeStatuses(Set<TypeStatus> typeStatuses) {
        this.typeStatuses = typeStatuses;
    }

    public Long getIdPenalite() {
        return idPenalite;
    }

    public void setIdPenalite(Long idPenalite) {
        this.idPenalite = idPenalite;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }
}