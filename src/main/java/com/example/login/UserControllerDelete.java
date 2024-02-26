package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerDelete {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserControllerDelete(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @DeleteMapping("/delete-user/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable String user_id, @RequestParam(required = false) String device_id) {
        try {
            String deleteQuery;
            Object[] queryParams;

            if (device_id != null && !device_id.isEmpty()) {
                // If device_id is provided, delete rows with the provided device_id
                deleteQuery = "DELETE FROM tbl_user_info WHERE device_id=?";
                queryParams = new Object[]{device_id};
            } else {
                // If device_id is not provided, delete all rows associated with the provided user_id
                deleteQuery = "DELETE FROM tbl_user_info WHERE user_id=?";
                queryParams = new Object[]{user_id};
            }

            // Execute the delete query
            int rowsAffected = jdbcTemplate.update(deleteQuery, queryParams);

            if (rowsAffected > 0) {
                return ResponseEntity.ok("User information deleted successfully");
            } else {
                return ResponseEntity.notFound().build(); // User not found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
