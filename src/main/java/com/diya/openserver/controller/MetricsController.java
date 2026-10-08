package com.diya.openserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Map;

@RestController
public class MetricsController {

    private final long startTime = System.currentTimeMillis();

    @GetMapping("/api/system/metrics")
    public Map<String, Object> getMetrics() {

        OperatingSystemMXBean os =
                ManagementFactory.getOperatingSystemMXBean();

        double cpuLoad = -1;

        if (os instanceof com.sun.management.OperatingSystemMXBean sunOs) {
            cpuLoad = sunOs.getCpuLoad() * 100;
        }

        Runtime runtime = Runtime.getRuntime();

        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        File disk = new File(".");

        long uptimeSeconds =
                (System.currentTimeMillis() - startTime) / 1000;

        return Map.of(
                "cpuUsagePercent", Math.round(cpuLoad * 100.0) / 100.0,
                "memoryUsedMB", usedMemory / (1024 * 1024),
                "memoryTotalMB", totalMemory / (1024 * 1024),
                "diskFreeGB", disk.getFreeSpace() / (1024 * 1024 * 1024),
                "diskTotalGB", disk.getTotalSpace() / (1024 * 1024 * 1024),
                "uptimeSeconds", uptimeSeconds
        );
    }
}