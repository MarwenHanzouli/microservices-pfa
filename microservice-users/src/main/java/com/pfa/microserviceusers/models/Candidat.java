package com.pfa.microserviceusers.models;

import com.pfa.microserviceusers.models.embedded.Address;
import com.pfa.microserviceusers.models.enumuration.RoleName;

import javax.persistence.*;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "candidat_id")
public class Candidat extends User {

    @Column
    private String niveau;

    @Column
    private String diplome;

    @Column
    private String institut;

    @Column
    private Date date_naissance;

    @Column
    @Embedded
    private Address address;


    public Candidat(String niveau, String diplome, String institut, Date date_naissance, Address address) {
        this.niveau = niveau;
        this.diplome = diplome;
        this.institut = institut;
        this.date_naissance = date_naissance;
        this.address = address;
    }

    public Candidat() {
        super();
    }

    public Candidat(String username, String password, String email, RoleName role) {
        super(username, password, email, role);
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getInstitut() {
        return institut;
    }

    public void setInstitut(String institut) {
        this.institut = institut;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
