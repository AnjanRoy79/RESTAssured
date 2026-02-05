package com.TelecomProject_POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String _id;
    private String firstName;
    private String lastName;
    private String email;

    // Getters & Setters
    public String get_id()
    { return _id; }
    public void set_id(String _id)
    { this._id = _id; }

    public String getFirstName() 
    { return firstName; }
    public void setFirstName(String firstName) 
    { this.firstName = firstName; }

    public String getLastName() 
    { return lastName; }
    public void setLastName(String lastName)
    { this.lastName = lastName; }

    public String getEmail() 
    { return email; }
    public void setEmail(String email) { this.email = email; }
}
