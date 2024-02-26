package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class LocationController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/fetch-locations/{user_id}/{device_id}")
    public ResponseEntity<List<Map<String, Object>>> fetchLocations(@PathVariable String user_id, @PathVariable String device_id) {
        try {
            // Retrieve live locations associated with the device
            String query = "SELECT SUBSTRING_INDEX(latitude, 'N', 1) AS latitude_numeric, SUBSTRING_INDEX(longitude, 'E', 1) AS longitude_numeric FROM tbl_location_data WHERE device_id = ?";
            List<Map<String, Object>> locationsResults = jdbcTemplate.queryForList(query, device_id);

            // Convert latitude and longitude to numeric format
            List<Map<String, Object>> locations = LocationUtil.convertLocations(locationsResults);

            return ResponseEntity.ok(locations);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
