package com.pfa.microserviceusers.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfa.microserviceusers.models.audit.AbstractEntity;
import com.pfa.microserviceusers.models.embedded.Photo;
import com.pfa.microserviceusers.models.enumuration.RoleName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class User extends AbstractEntity {
    @Column(unique = true)
    private String username;

    //@JsonIgnore
    @Column
    private String password;

    @Column(unique = true)
    private String email;

    @Column
    private String name;

    @Column
    private String lastName;

    @Enumerated(EnumType.STRING)
    private RoleName role;

    @Column
    private String telephone;

    @Column
    @Embedded
    private Photo photo;

    @Column
    private Integer active=1;

    @Column
    private boolean isLoacked=false;

    @Column
    private boolean isExpired=false;

    @Column
    private boolean isEnabled=false;


    public User(String username, String password, String email, RoleName role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User() {
        this.role=RoleName.USER;

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public boolean isLoacked() {
        return isLoacked;
    }

    public void setLoacked(boolean loacked) {
        isLoacked = loacked;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}