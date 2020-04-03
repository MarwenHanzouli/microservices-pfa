package com.pfa.microserviceusers.models.embedded;

import javax.persistence.*;

@Embeddable
public class Address {

    private String address;
    private String city;
    private String country;
    private String postcode;

    public Address() {
    }

    public Address(String address, String city, String country, String postcode) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
