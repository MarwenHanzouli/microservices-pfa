package com.pfa.microserviceoffers.proxies.beans;

import java.io.Serializable;

public class CvBean implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CvBean() {
    }

    public CvBean(Long id) {
        this.id = id;
    }
}
