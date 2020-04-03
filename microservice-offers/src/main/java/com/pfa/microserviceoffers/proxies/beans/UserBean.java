package com.pfa.microserviceoffers.proxies.beans;

import java.io.Serializable;

public class UserBean implements Serializable {
    private long id;

    public UserBean(long id) {
        this.id = id;
    }

    public UserBean() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
