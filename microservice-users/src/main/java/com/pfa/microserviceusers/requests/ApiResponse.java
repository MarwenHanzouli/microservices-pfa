package com.pfa.microserviceusers.requests;

import java.io.Serializable;

public class ApiResponse implements Serializable {

    private String message;
    private boolean succes;


    public ApiResponse() {
    }

    public ApiResponse(String message, boolean succes) {
        this.message = message;
        this.succes = succes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }
}
