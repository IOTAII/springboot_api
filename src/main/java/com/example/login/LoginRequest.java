package com.example.login;

public class LoginRequest {
    private String user_id;
    private String password;

    // Constructor
    public LoginRequest(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }

    // Getters and setters
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
