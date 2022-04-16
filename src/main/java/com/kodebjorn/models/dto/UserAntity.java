package com.kodebjorn.models.dto;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class UserAntity {

    private String username;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserAntity{" +
            "username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
