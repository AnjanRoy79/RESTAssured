package com.TelecomProject_POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterResponse {
    private User user;
    private String token;

    // Getters & Setters
    public User getUser() 
    { return user; }
    public void setUser(User user)
    { this.user = user; }

    public String getToken() 
    { return token; }
    public void setToken(String token)
    { this.token = token; }
}
