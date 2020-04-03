package com.pfa.microserviceoffers.models.enumuration;

public enum TypeOffre {

    TempsPlein("Temps Plein"),
    TempsPartiel("Temps Partiel"),
    CDD("CDD ou mission ponctuelle"),
    TravailTemporaire("Travail Temporaire"),
    Stagiaire("Stagiaire");

    private String type="";
    TypeOffre(String type) {
        this.type=type;
    }

    @Override
    public String toString() {
        return "TypeOffre{" +
                "type='" + type + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
