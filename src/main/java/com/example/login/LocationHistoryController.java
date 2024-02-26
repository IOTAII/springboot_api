package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

@RestController
public class LocationHistoryController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationHistoryController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/fetch-location-history/{user_id}/{device_id}")
    public ResponseEntity<List<Map<String, Object>>> fetchLocationHistory(
            @PathVariable String device_id,
            @PathVariable String user_id,
            @RequestParam String filter
    ) {
        try {
            LocalDateTime startTime;
            switch (filter) {
                case "24hours":
                    startTime = LocalDateTime.now().minusHours(24);
                    break;
                case "48hours":
                    startTime = LocalDateTime.now().minusHours(48);
                    break;
                case "6months":
                    startTime = LocalDateTime.now().minusMonths(6);
                    break;
                default:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            // Retrieve live locations associated with the device and within the specified time range
            List<Map<String, Object>> historyResults = jdbcTemplate.queryForList(
                    "SELECT CAST(SUBSTRING_INDEX(latitude, 'N', 1) AS DECIMAL(10,6)) AS latitude, " +
                            "CAST(SUBSTRING_INDEX(longitude, 'E', 1) AS DECIMAL(10,6)) AS longitude " +
                            "FROM tbl_location_data WHERE device_id = ? AND time_stamp >= ?",
                    device_id, startTime.toEpochSecond(ZoneOffset.UTC)
            );

            return ResponseEntity.ok(historyResults);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
