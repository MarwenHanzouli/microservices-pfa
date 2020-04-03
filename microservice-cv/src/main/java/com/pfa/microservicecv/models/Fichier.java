package com.pfa.microservicecv.models;

public class Fichier {

    private String nom;
    private String type;
    private String encoded;

    public Fichier() {
    }

    public Fichier(String nom, String type, String encoded) {
        this.nom = nom;
        this.type = type;
        this.encoded = encoded;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEncoded() {
        return encoded;
    }

    public void setEncoded(String encoded) {
        this.encoded = encoded;
    }
}
