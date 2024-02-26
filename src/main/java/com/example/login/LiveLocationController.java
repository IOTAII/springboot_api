package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LiveLocationController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LiveLocationController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/live-locations/{user_id}")
    public ResponseEntity<Map<String, List<Map<String, Double>>>> getLiveLocations(@PathVariable String user_id) {
        try {
            // Get user id based on tenant_id
            List<Map<String, Object>> userResults = jdbcTemplate.queryForList("SELECT user_id FROM tbl_user_tenant WHERE user_id = ?", user_id);

            if (userResults.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            // Retrieve live locations associated with the user
            List<Map<String, Object>> liveLocationsResults = jdbcTemplate.queryForList(
                    "SELECT device_id, latitude, longitude FROM tbl_location_data WHERE user_id = ?",
                    user_id
            );

            // Group locations by device ID
            Map<String, List<Map<String, Double>>> deviceLocations = new HashMap<>();
            for (Map<String, Object> location : liveLocationsResults) {
                String device_id = (String) location.get("device_id");
                double latitude = Double.parseDouble((String) location.get("latitude"));
                double longitude = Double.parseDouble((String) location.get("longitude"));

                Map<String, Double> coordinates = new HashMap<>();
                coordinates.put("latitude", latitude);
                coordinates.put("longitude", longitude);

                deviceLocations.computeIfAbsent(device_id, k -> new ArrayList<>()).add(coordinates);
            }

            return ResponseEntity.ok(deviceLocations);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
