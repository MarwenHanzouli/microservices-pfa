package com.pfa.zuulserver.security;

import com.pfa.zuulserver.beans.UserBean;
import com.pfa.zuulserver.beans.UserWithoutPassword;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private UserWithoutPassword user;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public JwtResponse(String jwttoken, UserWithoutPassword user) {
        this.jwttoken = jwttoken;
        this.user = user;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public UserWithoutPassword getUser() {
        return this.user;
    }

    public void setUser(UserWithoutPassword user) {
        this.user = user;
    }
}
