package com.example.login;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginControlle {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LoginControlle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        String user_id = loginRequest.getUser_id();
        String password = loginRequest.getPassword();

        try {
            // Execute SQL query to fetch user based on user_id
            String sql = "SELECT * FROM tbl_user_tenent WHERE user_id = ?";
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, user_id);

            if (result.isEmpty()) {
                return "Invalid credentials"; // User not found
            }

            Map<String, Object> user = result.get(0);

            // Compare the passwords
            String storedPassword = (String) user.get("password");
            if (!password.equals(storedPassword)) {
                return "Invalid credentials"; // Passwords don't match
            }

            return "Login successful"; // Successful login
        } catch (Exception e) {
            e.printStackTrace();
            return "Internal Server Error";
        }
    }
}
