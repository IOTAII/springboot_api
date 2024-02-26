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
public class DeviceController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DeviceController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/devices/{user_id}")
    public ResponseEntity<Object> getDevices(@PathVariable("user_id") String userId) {
        try {
            // Check if the user exists
            String userQuery = "SELECT user_id FROM tbl_user_tenent WHERE user_id = ?";
            if (jdbcTemplate.queryForObject(userQuery, String.class, userId) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            // Retrieve devices associated with the user
            String devicesQuery = "SELECT * FROM tbl_user_info WHERE user_id = ?";
            List<Map<String, Object>> devices = jdbcTemplate.queryForList(devicesQuery, userId);

            return ResponseEntity.ok(devices);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
