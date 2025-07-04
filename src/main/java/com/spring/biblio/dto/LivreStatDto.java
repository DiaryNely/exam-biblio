package com.spring.biblio.dto;

public class LivreStatDto {

    private String titre;
    private long nombreEmprunts;

    // Constructeur public requis par JPA pour mapper les résultats de la requête
    public LivreStatDto(String titre, long nombreEmprunts) {
        this.titre = titre;
        this.nombreEmprunts = nombreEmprunts;
    }

    // Getters (et Setters si besoin, mais les getters sont suffisants ici)
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public long getNombreEmprunts() {
        return nombreEmprunts;
    }

    public void setNombreEmprunts(long nombreEmprunts) {
        this.nombreEmprunts = nombreEmprunts;
    }
}