package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/users")
    public ResponseEntity<List<Map<String, Object>>> getUsers() {
        try {
            // Execute SQL query to fetch all users
            String sql = "SELECT * FROM tbl_user_tenent";
            List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);

            // Return users as JSON
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            // Log error and return internal server error
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
