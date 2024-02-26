package com.example.login;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LocationUtil {

    public static List<Map<String, Object>> convertLocations(List<Map<String, Object>> locationsResults) {
        return locationsResults.stream()
                .map(location -> {
                    double latitude = Double.parseDouble((String) location.get("latitude_numeric"));
                    double longitude = Double.parseDouble((String) location.get("longitude_numeric"));
                    location.put("latitude", latitude);
                    location.put("longitude", longitude);
                    return location;
                })
                .collect(Collectors.toList());
    }
}
