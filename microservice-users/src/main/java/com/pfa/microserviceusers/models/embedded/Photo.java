package com.pfa.microserviceusers.models.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Photo {

    @Column(nullable = true)
    private String photo_name;

    @Column(nullable = true)
    private String photo_type;

    @Column(length = 200000)
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
