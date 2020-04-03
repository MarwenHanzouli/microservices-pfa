package com.pfa.microservicecv.beans;

import java.io.Serializable;

public class CandidatBean implements Serializable {

    private Long id;

    public CandidatBean(Long id) {
        this.id = id;
    }

    public CandidatBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
