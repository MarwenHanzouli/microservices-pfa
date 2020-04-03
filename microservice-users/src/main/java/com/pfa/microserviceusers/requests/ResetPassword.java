package com.pfa.microserviceusers.requests;

import java.io.Serializable;

public class ResetPassword implements Serializable {
    private String email;

    public ResetPassword() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
