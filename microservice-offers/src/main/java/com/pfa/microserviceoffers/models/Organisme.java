package com.pfa.microserviceoffers.models;

import com.pfa.microserviceoffers.models.audit.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
public class Organisme extends AbstractEntity {

    @Column(unique = true)
    @NotNull
    private String nomOrganisme;

    @Column
    @NotNull
    private String secteur;

    public Organisme() {
    }

    public Organisme(String nomOrganisme, String secteur) {
        this.nomOrganisme = nomOrganisme;
        this.secteur = secteur;
    }

    public String getNomOrganisme() {
        return nomOrganisme;
    }

    public void setNomOrganisme(String nomOrganisme) {
        this.nomOrganisme = nomOrganisme;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    @Override
    public String toString() {
        return "Organisme{" +
                "nomOrganisme='" + nomOrganisme + '\'' +
                ", secteur='" + secteur + '\'' +
                '}';
    }
}
