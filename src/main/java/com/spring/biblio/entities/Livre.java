package com.spring.biblio.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "livres")
public class Livre {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_livre")
        private Long idLivre;

        @Column(name = "titre", nullable = false, length = 50)
        private String titre;

        @Column(name = "sous_titre", length = 50)
        private String sousTitre;

        @Column(name = "date_publication", nullable = false)
        private LocalDate datePublication;

        @Column(name = "prix", precision = 10, scale = 2)
        private BigDecimal prix;

        @ManyToOne
        @JoinColumn(name = "id_collection", nullable = false)
        private Collection collection;

        @ManyToOne
        @JoinColumn(name = "id_genre", nullable = false)
        private Genre genre;

        @ManyToOne
        @JoinColumn(name = "id_langue", nullable = false)
        private Langue langue;

        @OneToMany(mappedBy = "livre")
        private Set<Exemplaire> exemplaires;

        @ManyToMany
        @JoinTable(name = "auteur_livre", joinColumns = @JoinColumn(name = "id_livre"), inverseJoinColumns = @JoinColumn(name = "id_auteur"))
        private Set<Auteur> auteurs;

        @ManyToMany
        @JoinTable(name = "adh_livre", joinColumns = @JoinColumn(name = "id_livre"), inverseJoinColumns = @JoinColumn(name = "id_type_adh"))
        private Set<TypeAdherent> typesAdherents;

        // Constructeurs
        public Livre() {
        }

        public Livre(String titre, String sousTitre, LocalDate datePublication, BigDecimal prix,
                        Collection collection, Genre genre, Langue langue) {
                this.titre = titre;
                this.sousTitre = sousTitre;
                this.datePublication = datePublication;
                this.prix = prix;
                this.collection = collection;
                this.genre = genre;
                this.langue = langue;
        }

        // Getters et Setters
        public Long getIdLivre() {
                return idLivre;
        }

        public void setIdLivre(Long idLivre) {
                this.idLivre = idLivre;
        }

        public String getTitre() {
                return titre;
        }

        public void setTitre(String titre) {
                this.titre = titre;
        }

        public String getSousTitre() {
                return sousTitre;
        }

        public void setSousTitre(String sousTitre) {
                this.sousTitre = sousTitre;
        }

        public LocalDate getDatePublication() {
                return datePublication;
        }

        public void setDatePublication(LocalDate datePublication) {
                this.datePublication = datePublication;
        }

        public BigDecimal getPrix() {
                return prix;
        }

        public void setPrix(BigDecimal prix) {
                this.prix = prix;
        }

        public Collection getCollection() {
                return collection;
        }

        public void setCollection(Collection collection) {
                this.collection = collection;
        }

        public Genre getGenre() {
                return genre;
        }

        public void setGenre(Genre genre) {
                this.genre = genre;
        }

        public Langue getLangue() {
                return langue;
        }

        public void setLangue(Langue langue) {
                this.langue = langue;
        }

        public Set<Exemplaire> getExemplaires() {
                return exemplaires;
        }

        public void setExemplaires(Set<Exemplaire> exemplaires) {
                this.exemplaires = exemplaires;
        }

        public Set<Auteur> getAuteurs() {
                return auteurs;
        }

        public void setAuteurs(Set<Auteur> auteurs) {
                this.auteurs = auteurs;
        }

        public Set<TypeAdherent> getTypesAdherents() {
                return typesAdherents;
        }

        public void setTypesAdherents(Set<TypeAdherent> typesAdherents) {
                this.typesAdherents = typesAdherents;
        }
}