package com.example.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class LoginApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LoginApplication.class, args);

        // Check database connection
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        try {
            jdbcTemplate.execute("SELECT 1");
            System.out.println("Done"); // Connection successful
        } catch (Exception e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
        }
    }

}
