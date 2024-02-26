package com.example.login;

public class DeviceRegistrationRequest {
    private String user_id;
    private String name;
    private String email_id;
    private String mob;
    private String address;
    private String date_of_purchase;
    private String invoice_number;
    private String device_id;
    private String services_offered;

    // Constructors
    public DeviceRegistrationRequest() {}

    public DeviceRegistrationRequest(String user_id, String name, String email_id, String mob, String address, String date_of_purchase, String invoice_number, String device_id, String services_offered) {
        this.user_id = user_id;
        this.name = name;
        this.email_id = email_id;
        this.mob = mob;
        this.address = address;
        this.date_of_purchase = date_of_purchase;
        this.invoice_number = invoice_number;
        this.device_id = device_id;
        this.services_offered = services_offered;
    }

    // Getters and setters
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate_of_purchase() {
        return date_of_purchase;
    }

    public void setDate_of_purchase(String date_of_purchase) {
        this.date_of_purchase = date_of_purchase;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getServices_offered() {
        return services_offered;
    }

    public void setServices_offered(String services_offered) {
        this.services_offered = services_offered;
    }
}
