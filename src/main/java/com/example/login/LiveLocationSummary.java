package com.example.login;

public class LiveLocationSummary {
    private Double latitude;
    private Double longitude;
    private Double speed;
    private Integer batteryPercentage;

    public LiveLocationSummary(Double latitude, Double longitude, Double speed, Integer batteryPercentage) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.batteryPercentage = batteryPercentage;
    }

    // Getters and setters
    // You can generate them using your IDE or define them manually
}
