package com.pfa.zuulserver.beans;

import java.io.Serializable;
import java.util.Date;

public class UserBean implements Serializable {
    private long id;
    private String username;
    private String password;
    private String role;
    private String email;
    private String name;
    private String lastName;
    private String telephone;
    private Photo photo;
    private Address address;
    private String niveau;
    private String diplome;
    private String institut;
    private Date date_naissance;
    private String nameEntreprise;
    private Integer active;
    private boolean isLoacked;
    private boolean isExpired;
    private boolean isEnabled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public String getNameEntreprise() {
        return nameEntreprise;
    }

    public void setNameEntreprise(String nameEntreprise) {
        this.nameEntreprise = nameEntreprise;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "UserBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
    public UserWithoutPassword mapToUserWithoutPassword(){
        UserWithoutPassword u =new UserWithoutPassword();
        u.setId(this.getId());
        u.setAddress(this.getAddress());
        u.setDate_naissance(this.getDate_naissance());
        u.setDiplome(this.getDiplome());
        u.setInstitut(this.getInstitut());
        u.setNiveau(this.getNiveau());
        u.setUsername(this.getUsername());
        u.setEmail(this.getEmail());
        u.setName(this.getName());
        u.setPhoto(this.getPhoto());
        u.setLastName(this.getLastName());
        u.setTelephone(this.getTelephone());
        u.setRole(this.getRole());
        u.setNameEntreprise(this.getNameEntreprise());
        u.setActive(active);
        u.setEnabled(isEnabled);
        u.setExpired(isExpired);
        u.setLoacked(this.isLoacked());
        return u;
    }
}
