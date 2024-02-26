package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserControllerUpdate {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserControllerUpdate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PutMapping("/update-user/{user_id}")
    public ResponseEntity<String> updateUser(@RequestParam String user_id, @RequestBody UpdateUserRequest updateUserRequest) {
        try {
            // Construct the dynamic SQL update query
            StringBuilder updateQuery = new StringBuilder("UPDATE tbl_user_info SET ");
            List<Object> queryParams = new ArrayList<>();
            int paramIndex = 0;

            if (updateUserRequest.getName() != null) {
                updateQuery.append("name=?, ");
                queryParams.add(updateUserRequest.getName());
            }
            if (updateUserRequest.getEmail_id() != null) {
                updateQuery.append("email_id=?, ");
                queryParams.add(updateUserRequest.getEmail_id());
            }
            if (updateUserRequest.getMob() != null) {
                updateQuery.append("mob=?, ");
                queryParams.add(updateUserRequest.getMob());
            }
            if (updateUserRequest.getAddress() != null) {
                updateQuery.append("address=?, ");
                queryParams.add(updateUserRequest.getAddress());
            }
            if (updateUserRequest.getDate_of_purchase() != null) {
                updateQuery.append("date_of_purchase=?, ");
                queryParams.add(updateUserRequest.getDate_of_purchase());
            }
            if (updateUserRequest.getInvoice_number() != null) {
                updateQuery.append("invoice_number=?, ");
                queryParams.add(updateUserRequest.getInvoice_number());
            }
            if (updateUserRequest.getDevice_id() != null) {
                updateQuery.append("device_id=?, ");
                queryParams.add(updateUserRequest.getDevice_id());
            }
            if (updateUserRequest.getServices_offered() != null) {
                updateQuery.append("services_offered=?, ");
                queryParams.add(updateUserRequest.getServices_offered());
            }

            // Remove the last comma and space
            updateQuery.delete(updateQuery.length() - 2, updateQuery.length());

            // Add WHERE clause
            updateQuery.append(" WHERE user_id=?");
            queryParams.add(user_id);

            // Execute the update query
            jdbcTemplate.update(updateQuery.toString(), queryParams.toArray());

            return ResponseEntity.ok("User information updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
