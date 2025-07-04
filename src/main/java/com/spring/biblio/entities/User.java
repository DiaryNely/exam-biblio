package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "prenom", length = 50)
    private String prenom;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "adresse", length = 50)
    private String adresse;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "type_user", nullable = false)
    private int typeUser;

    @OneToMany(mappedBy = "user")
    private Set<Adherent> adherents;

    @OneToMany(mappedBy = "user")
    private Set<PaiementDetail> paiementDetails;

    // Constructeurs
    public User() {
    }

    public User(String nom, String prenom, String email, String password, String adresse, LocalDate dateNaissance,
            int typeUser) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.typeUser = typeUser;
    }

    // Getters et Setters
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Set<Adherent> getAdherents() {
        return adherents;
    }

    public void setAdherents(Set<Adherent> adherents) {
        this.adherents = adherents;
    }

    public Set<PaiementDetail> getPaiementDetails() {
        return paiementDetails;
    }

    public void setPaiementDetails(Set<PaiementDetail> paiementDetails) {
        this.paiementDetails = paiementDetails;
    }

    public int getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(int typeUser) {
        this.typeUser = typeUser;
    }
}
