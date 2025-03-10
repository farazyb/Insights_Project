package com.sea.reporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main application class for the Reporter service.
 * This Spring Boot application handles report generation and scheduling.
 * 
 * Features:
 * - Scheduled task execution
 * - Report generation
 * - Organization API integration
 * - Database operations
 */
@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class ReporterApplication  {

    public static void main(String[] args) {
        SpringApplication.run(ReporterApplication.class, args);
    }
}
