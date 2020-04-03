package com.pfa.microserviceoffers.models.enumuration;

public enum Niveau {
    Bac("BAC"),
    Licence("Licence"),
    Mastere("Mastère"),
    Ingenieur("Ingénieur"),
    Autre("Autre niveau");

    private String niveau="";
    Niveau(String niveau) {
        this.niveau=niveau;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
}
