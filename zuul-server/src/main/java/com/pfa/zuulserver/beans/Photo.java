package com.pfa.zuulserver.beans;


import java.io.Serializable;

public class Photo implements Serializable {

    private String photo_name;
    private String photo_type;
    private String encoded_string;

    public Photo(String photo_name, String photo_type, String encoded_string) {
        this.photo_name = photo_name;
        this.photo_type = photo_type;
        this.encoded_string = encoded_string;
    }

    public Photo() {
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public String getPhoto_type() {
        return photo_type;
    }

    public void setPhoto_type(String photo_type) {
        this.photo_type = photo_type;
    }

    public String getEncoded_string() {
        return encoded_string;
    }

    public void setEncoded_string(String encoded_string) {
        this.encoded_string = encoded_string;
    }
}
