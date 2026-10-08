package com.diya.openserver.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/system/power")
public class PowerController {

    private String powerState = "ON";

    @PostMapping("/on")
    public Map<String, String> powerOn() {
        powerState = "ON";

        return Map.of(
                "server", "OpenServer",
                "action", "POWER_ON",
                "power", powerState,
                "status", "SUCCESS"
        );
    }

    @PostMapping("/off")
    public Map<String, String> powerOff() {
        powerState = "OFF";

        return Map.of(
                "server", "OpenServer",
                "action", "POWER_OFF",
                "power", powerState,
                "status", "SUCCESS"
        );
    }

    @PostMapping("/restart")
    public Map<String, String> restart() {
        powerState = "ON";

        return Map.of(
                "server", "OpenServer",
                "action", "RESTART",
                "power", powerState,
                "status", "SUCCESS"
        );
    }

    @GetMapping
    public Map<String, String> getPowerStatus() {
        return Map.of(
                "server", "OpenServer",
                "power", powerState
        );
    }
}