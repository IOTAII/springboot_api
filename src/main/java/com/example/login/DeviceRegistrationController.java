package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceRegistrationController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DeviceRegistrationController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/register-device")
    public ResponseEntity<String> registerDevice(@RequestBody DeviceRegistrationRequest deviceRegistrationRequest) {
        String userId = deviceRegistrationRequest.getUser_id();
        String name = deviceRegistrationRequest.getName();
        String emailId = deviceRegistrationRequest.getEmail_id();
        String mob = deviceRegistrationRequest.getMob();
        String address = deviceRegistrationRequest.getAddress();
        String dateOfPurchase = deviceRegistrationRequest.getDate_of_purchase();
        String invoiceNumber = deviceRegistrationRequest.getInvoice_number();
        String deviceId = deviceRegistrationRequest.getDevice_id();
        String servicesOffered = deviceRegistrationRequest.getServices_offered();

        // Validate input data
        if (userId == null || name == null || emailId == null || mob == null || address == null || dateOfPurchase == null || invoiceNumber == null || deviceId == null || servicesOffered == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required parameters");
        }

        try {
            // Check if user exists
            String userQuery = "SELECT user_id FROM tbl_user_tenent WHERE user_id = ?";
            if (jdbcTemplate.queryForObject(userQuery, String.class, userId) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }

            // Insert device information into the database
            String insertQuery = "INSERT INTO tbl_user_info (user_id, name, email_id, mob, address, date_of_purchase, invoice_number, device_id, services_offered) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(insertQuery, userId, name, emailId, mob, address, dateOfPurchase, invoiceNumber, deviceId, servicesOffered);

            return ResponseEntity.ok("Device registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
