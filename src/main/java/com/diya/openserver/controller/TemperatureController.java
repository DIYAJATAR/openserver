package com.diya.openserver.controller;

import com.diya.openserver.service.MonitoringService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/system/temperature")
public class TemperatureController {

    private double temperature = 45.0;

    private final MonitoringService monitoringService;

    public TemperatureController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @GetMapping
    public Map<String, Object> getTemperature() {

        return Map.of(
                "temperatureCelsius", temperature,
                "status", getStatus()
        );
    }

    @PostMapping("/simulate")
    public Map<String, Object> simulateTemperature(
            @RequestParam double value) {

        temperature = value;

        String status = getStatus();

        if (status.equals("CRITICAL")) {

            monitoringService.addEvent(
                    "CRITICAL",
                    "TEMPERATURE_CRITICAL",
                    "Server temperature reached critical level"
            );

        } else if (status.equals("WARNING")) {

            monitoringService.addEvent(
                    "WARNING",
                    "TEMPERATURE_WARNING",
                    "Server temperature exceeded warning threshold"
            );

        } else {

            monitoringService.addEvent(
                    "INFO",
                    "TEMPERATURE_NORMAL",
                    "Server temperature returned to normal"
            );
        }

        return Map.of(
                "temperatureCelsius", temperature,
                "status", status
        );
    }

    private String getStatus() {

        if (temperature >= 90) {
            return "CRITICAL";
        }

        if (temperature >= 75) {
            return "WARNING";
        }

        return "NORMAL";
    }
}