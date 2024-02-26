package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HelloController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/hello")
    public String sayHello() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return "hi done";
        } catch (Exception e) {
            return "Failed to connect to database: " + e.getMessage();
        }
    }
}
