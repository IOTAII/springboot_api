package com.example.login;

public class RegistrationRequest {
    private String user_id;
    private String user_name;
    private String tenant_id;
    private String password;
    private String role;

    // Constructors
    public RegistrationRequest() {}

    public RegistrationRequest(String user_id, String user_name, String tenant_id, String password, String role) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.tenant_id = tenant_id;
        this.password = password;
        this.role = role;
    }

    // Getters and setters
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", tenant_id='" + tenant_id + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
