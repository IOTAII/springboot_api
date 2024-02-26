package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RegistrationController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        String userId = registrationRequest.getUser_id();
        String userName = registrationRequest.getUser_name();
        String tenantId = registrationRequest.getTenant_id();
        String password = registrationRequest.getPassword();
        String role = registrationRequest.getRole();

        // Log the received registration data
        System.out.println("Received Registration Request: " + registrationRequest);

        try {
            // Insert user registration data into the database
            String sql = "INSERT INTO tbl_user_tenent (user_id, user_name, tenant_id, password, role) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, userId, userName, tenantId, password, role);

            // Log successful registration
            System.out.println("User registered successfully: " + userId);

            return ResponseEntity.ok("Tenant registered successfully");
        } catch (Exception e) {
            // Log registration error
            System.err.println("Error registering user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
