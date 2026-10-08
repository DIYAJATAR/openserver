package com.diya.openserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SystemController {

    @GetMapping("/api/system/status")
    public Map<String, Object> getSystemStatus() {
        return Map.of(
                "server", "OpenServer",
                "status", "ONLINE",
                "power", "ON",
                "health", "HEALTHY"
        );
    }
}