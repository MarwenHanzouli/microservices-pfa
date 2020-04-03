package com.pfa.microserviceoffers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pfa.microserviceoffers.models.audit.AbstractEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Candidature extends AbstractEntity {

    @Column
    private String commentaire;

    @Column
    private boolean etat=true;

    @Column
    private Long idCandidat;

    @Column
    private Long idCv;

    @Column
    private Instant dateCandidature=Instant.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "offre_id",nullable = false)
    private Offre offre;

    public Candidature() {
    }

    public Candidature(String commentaire, boolean etat, Long idCandidat, Long idCv, Offre offre) {
        this.commentaire = commentaire;
        this.etat = etat;
        this.idCandidat = idCandidat;
        this.idCv = idCv;
        this.offre = offre;
    }
    public Instant getDateCandidature() {
        return dateCandidature;
    }

    public void setDateCandidature(Instant dateCandidature) {
        this.dateCandidature = dateCandidature;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Long getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(Long idCandidat) {
        this.idCandidat = idCandidat;
    }

    public Long getIdCv() {
        return idCv;
    }

    public void setIdCv(Long idCv) {
        this.idCv = idCv;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    @Override
    public String toString() {
        return "Candidature{" +
                "commentaire='" + commentaire + '\'' +
                ", etat=" + etat +
                ", idCandidat=" + idCandidat +
                ", idCv=" + idCv +
                ", offre=" + offre +
                '}';
    }
}
