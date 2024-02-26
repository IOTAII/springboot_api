package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;

@RestController
public class UserSearchController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/search-users")
    public ResponseEntity<?> searchUsers(@RequestParam(required = false) String user_name,
                                         @RequestParam(required = false) String tenant_id,
                                         @RequestParam(required = false) String user_id) {
        String query = "SELECT * FROM tbl_user_tenent WHERE 1=1 ";
        StringBuilder condition = new StringBuilder();
        if (user_name != null) {
            condition.append("AND user_name LIKE '%" + user_name + "%' ");
        }
        if (tenant_id != null) {
            condition.append("AND tenant_id LIKE '%" + tenant_id + "%' ");
        }
        if (user_id != null) {
            condition.append("AND user_id = '" + user_id + "' ");
        }
        query += condition.toString().trim();

        try {
            List<Map<String, Object>> searchResults = jdbcTemplate.queryForList(query);
            if (searchResults.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found");
            }
            return ResponseEntity.ok(searchResults);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
