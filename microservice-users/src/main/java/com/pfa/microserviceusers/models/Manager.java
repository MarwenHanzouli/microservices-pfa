package com.pfa.microserviceusers.models;


import com.pfa.microserviceusers.models.enumuration.RoleName;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Manager extends User {

    @Column
    private String nameEntreprise;

    public Manager(String username, String password, String email, RoleName role, String nameEntreprise) {
        super(username, password, email, role);
        this.nameEntreprise = nameEntreprise;
    }

    public Manager(String nameEntreprise) {
        this.nameEntreprise = nameEntreprise;
    }

    public Manager() { }

    public String getNameEntreprise() {
        return nameEntreprise;
    }

    public void setNameEntreprise(String nameEntreprise) {
        this.nameEntreprise = nameEntreprise;
    }
}
