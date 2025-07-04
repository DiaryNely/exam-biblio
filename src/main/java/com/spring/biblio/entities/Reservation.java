package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long idReservation;

    @Column(name = "date_reservation", nullable = false)
    private LocalDate dateReservation;

    @Column(name = "motif", length = 100)
    private String motif;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "id_adherent", nullable = false)
    private Adherent adherent;

    @ManyToMany
    @JoinTable(name = "reservation_status", joinColumns = @JoinColumn(name = "id_reservation"), inverseJoinColumns = @JoinColumn(name = "id_type_status"))
    private Set<TypeStatus> typeStatuses;

    // Constructeurs
    public Reservation() {
    }

    public Reservation(LocalDate dateReservation, String motif, Exemplaire exemplaire, Adherent adherent) {
        this.dateReservation = dateReservation;
        this.motif = motif;
        this.exemplaire = exemplaire;
        this.adherent = adherent;
    }

    // Getters et Setters
    public Set<TypeStatus> getTypeStatuses() {
        return typeStatuses;
    }

    public void setTypeStatuses(Set<TypeStatus> typeStatuses) {
        this.typeStatuses = typeStatuses;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }
}
