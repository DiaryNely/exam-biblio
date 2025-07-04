package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "exemplaires")
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exemplaire")
    private Long idExemplaire;

    @Column(name = "date_acquisition", nullable = false)
    private LocalDate dateAcquisition;

    @ManyToOne
    @JoinColumn(name = "id_localisation")
    private Localisation localisation;

    @ManyToOne
    @JoinColumn(name = "id_livre", nullable = false)
    private Livre livre;

    @OneToMany(mappedBy = "exemplaire")
    private Set<Reservation> reservations;

    @OneToMany(mappedBy = "exemplaire")
    private Set<Pret> prets;

    @ManyToMany
    @JoinTable(name = "exemplaire_status", joinColumns = @JoinColumn(name = "id_exemplaire"), inverseJoinColumns = @JoinColumn(name = "id_type_status"))
    private Set<TypeStatus> typeStatuses;

    // Constructeurs
    public Exemplaire() {
    }

    public Exemplaire(LocalDate dateAcquisition, Localisation localisation, Livre livre) {
        this.dateAcquisition = dateAcquisition;
        this.localisation = localisation;
        this.livre = livre;
    }

    // Getters et Setters
    public Long getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(Long idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public LocalDate getDateAcquisition() {
        return dateAcquisition;
    }

    public void setDateAcquisition(LocalDate dateAcquisition) {
        this.dateAcquisition = dateAcquisition;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
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

    public Set<TypeStatus> getTypeStatuses() {
        return typeStatuses;
    }

    public void setTypeStatuses(Set<TypeStatus> typeStatuses) {
        this.typeStatuses = typeStatuses;
    }
}
