package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "type_pret")
public class TypePret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_pret")
    private Long idTypePret;

    @Column(name = "nom_emprunt", nullable = false, length = 50)
    private String nomEmprunt;

    @Column(name = "descriprtion", length = 100)
    private String descriprtion;

    @OneToMany(mappedBy = "typePret")
    private Set<Pret> prets;

    // Constructeurs
    public TypePret() {
    }

    public TypePret(String nomEmprunt, String descriprtion) {
        this.nomEmprunt = nomEmprunt;
        this.descriprtion = descriprtion;
    }

    // Getters et Setters
    public Long getIdTypePret() {
        return idTypePret;
    }

    public void setIdTypePret(Long idTypePret) {
        this.idTypePret = idTypePret;
    }

    public String getNomEmprunt() {
        return nomEmprunt;
    }

    public void setNomEmprunt(String nomEmprunt) {
        this.nomEmprunt = nomEmprunt;
    }

    public String getDescriprtion() {
        return descriprtion;
    }

    public void setDescriprtion(String descriprtion) {
        this.descriprtion = descriprtion;
    }

    public Set<Pret> getPrets() {
        return prets;
    }

    public void setPrets(Set<Pret> prets) {
        this.prets = prets;
    }
}