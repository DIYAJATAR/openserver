package com.diya.openserver.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MonitoringService {

    private final List<Map<String, Object>> events = new ArrayList<>();

    private boolean highCpuAlertActive = false;

    public MonitoringService() {
        events.add(createEvent(
                "INFO",
                "SERVER_STARTED",
                "OpenServer monitoring initialized"
        ));
    }

    public List<Map<String, Object>> getEvents() {
        return events;
    }

    public void addEvent(
            String level,
            String type,
            String message) {

        events.add(createEvent(
                level,
                type,
                message
        ));
    }

    public Map<String, Object> checkSystemHealth() {

        OperatingSystemMXBean os =
                ManagementFactory.getOperatingSystemMXBean();

        double cpuLoad = -1;

        if (os instanceof com.sun.management.OperatingSystemMXBean sunOs) {
            cpuLoad = sunOs.getCpuLoad() * 100;
        }

        if (cpuLoad >= 80) {

            if (!highCpuAlertActive) {

                highCpuAlertActive = true;

                Map<String, Object> event = createEvent(
                        "WARNING",
                        "HIGH_CPU",
                        "CPU usage exceeded 80%"
                );

                events.add(event);

                return event;
            }

            return createEvent(
                    "WARNING",
                    "HIGH_CPU_ACTIVE",
                    "CPU remains above 80%"
            );
        }

        if (highCpuAlertActive) {

            highCpuAlertActive = false;

            Map<String, Object> event = createEvent(
                    "INFO",
                    "CPU_RECOVERED",
                    "CPU usage returned to normal"
            );

            events.add(event);

            return event;
        }

        Map<String, Object> event = createEvent(
                "INFO",
                "SYSTEM_HEALTHY",
                "System operating normally"
        );

        events.add(event);

        return event;
    }

    @Scheduled(fixedRate = 10000)
    public void automaticHealthCheck() {
        checkSystemHealth();
    }

    private Map<String, Object> createEvent(
            String level,
            String type,
            String message) {

        return Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "level", level,
                "type", type,
                "message", message
        );
    }
}