package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "adherents")
public class Adherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adherent")
    private Long idAdherent;

    @Column(name = "numero", nullable = false, length = 100)
    private String numero;

    @Column(name = "date_inscription", nullable = false)
    private LocalDate dateInscription;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_type_adh", nullable = false)
    private TypeAdherent typeAdherent;

    @OneToMany(mappedBy = "adherent")
    private Set<Abonnement> abonnements;

    @OneToMany(mappedBy = "adherent")
    private Set<Penalite> penalites;

    @OneToMany(mappedBy = "adherent")
    private Set<Reservation> reservations;

    @OneToMany(mappedBy = "adherent")
    private Set<Pret> prets;

    // Constructeurs
    public Adherent() {
    }

    public Adherent(String numero, LocalDate dateInscription, User user, TypeAdherent typeAdherent) {
        this.numero = numero;
        this.dateInscription = dateInscription;
        this.user = user;
        this.typeAdherent = typeAdherent;
    }

    // Getters et Setters
    public Long getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(Long idAdherent) {
        this.idAdherent = idAdherent;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TypeAdherent getTypeAdherent() {
        return typeAdherent;
    }

    public void setTypeAdherent(TypeAdherent typeAdherent) {
        this.typeAdherent = typeAdherent;
    }

    public Set<Abonnement> getAbonnements() {
        return abonnements;
    }

    public void setAbonnements(Set<Abonnement> abonnements) {
        this.abonnements = abonnements;
    }

    public Set<Penalite> getPenalites() {
        return penalites;
    }

    public void setPenalites(Set<Penalite> penalites) {
        this.penalites = penalites;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Pret> getPrets() {
        return prets;
    }

    public void setPrets(Set<Pret> prets) {
        this.prets = prets;
    }
}