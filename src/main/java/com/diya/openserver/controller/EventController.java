package com.diya.openserver.controller;

import com.diya.openserver.service.MonitoringService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/system/events")
public class EventController {

    private final MonitoringService monitoringService;

    public EventController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @GetMapping
    public List<Map<String, Object>> getEvents() {
        return monitoringService.getEvents();
    }

    @PostMapping("/check")
    public Map<String, Object> checkSystemHealth() {
        return monitoringService.checkSystemHealth();
    }
}