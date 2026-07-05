package com.shivar.controller;

import com.shivar.common.response.ApiResponse;
import com.shivar.common.response.ResponseBuilder;
import com.shivar.dto.response.HealthResponse;
import com.shivar.service.HealthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<HealthResponse>> health() {

        HealthResponse response = healthService.getApplicationHealth();

        return ResponseEntity.ok(
                ResponseBuilder.success("Application is running.", response)
        );
    }
}