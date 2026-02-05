package com.RestAPI_POJO;

public class AuthenticationPOJO
{
	/*
	 * POJO: Plain Old Java Object
	 * Encapsulation: private data +public methods
	 * 
	 * Simple Json
	 * {
    "username" : "admin",
    "password" : "password123"
		}
	 * 
	 */
	// using getter and setter method 
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
