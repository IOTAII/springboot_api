package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/device-info")
public class DeviceInfoController {

    @Autowired
    private DeviceInfoService deviceInfoService;

    @GetMapping("/{user_id}/{device_id}")
    public ResponseEntity<?> getDeviceInfo(@PathVariable("user_id") String userId,
                                           @PathVariable("device_id") String deviceId) {
        try {
            Map<String, Object> deviceInfo = deviceInfoService.getDeviceInfo(userId, deviceId);
            if (deviceInfo == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Device information not found");
            }
            return ResponseEntity.ok(deviceInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
