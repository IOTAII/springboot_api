package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service

public class DeviceInfoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getDeviceInfo(String userId, String deviceId) {
        String sql = "SELECT ui.user_id, ui.name, ui.email_id, ui.mob, ui.address, ui.date_of_purchase, ui.invoice_number, ui.device_id, ui.services_offered, ld.battery_percentage " +
                     "FROM tbl_user_info ui " +
                     "JOIN tbl_location_data ld ON ui.device_id = ld.device_id " +
                     "WHERE ui.user_id = ? AND ui.device_id = ?";
        try {
            List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, userId, deviceId);
            if (results.isEmpty()) {
                return null;
            }
            return results.get(0); // Assuming there's only one device with the given user_id and device_id
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
