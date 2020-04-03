package com.pfa.microserviceoffers.models;

import com.pfa.microserviceoffers.models.audit.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Competence extends AbstractEntity {

    @Column(unique = true)
    private String titre;

    @ManyToMany(mappedBy = "competences")
    private Set<Offre> offres=new HashSet<>();;

    public Competence() {
    }

    public Competence(String titre) {
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

}
