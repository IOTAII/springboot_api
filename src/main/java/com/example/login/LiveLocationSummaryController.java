package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LiveLocationSummaryController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LiveLocationSummaryController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/live-location-summary/{user_id}/{device_id}")
    public ResponseEntity<Map<String, Object>> getLiveLocationSummary(@PathVariable String user_id, @PathVariable String device_id) {
        try {
            // Check if the user and device exist
            String userInfoQuery = "SELECT * FROM tbl_user_info WHERE user_id = ? AND device_id = ?";
            Map<String, Object> userInfo = jdbcTemplate.queryForMap(userInfoQuery, user_id, device_id);

            // If user info does not exist, return an error
            if (userInfo.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User or device not found"));
            }

            // Retrieve live location data based on device ID
            String locationQuery = "SELECT latitude, longitude, speed, battery_percentage FROM tbl_location_data WHERE device_id = ? ORDER BY time_stamp DESC LIMIT 1";
            Map<String, Object> locationData = jdbcTemplate.queryForMap(locationQuery, device_id);

            // If location data does not exist, return an error
            if (locationData.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Live location data not found"));
            }

            // Return the live location data in the response
            return ResponseEntity.ok(locationData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Internal Server Error"));
        }
    }
}
