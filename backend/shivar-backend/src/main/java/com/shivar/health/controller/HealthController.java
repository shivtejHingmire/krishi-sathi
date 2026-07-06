package com.shivar.health.controller;

import com.shivar.common.constants.ApiConstants;
import com.shivar.common.constants.MessageConstants;
import com.shivar.common.response.ApiResponse;
import com.shivar.common.response.ResponseBuilder;
import com.shivar.health.dto.HealthResponse;
import com.shivar.health.service.HealthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.API_V1 + "/health")
public class HealthController {

    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<HealthResponse>> health() {

        HealthResponse response = healthService.getApplicationHealth();

        return ResponseEntity.ok(
                ResponseBuilder.success(MessageConstants.APPLICATION_RUNNING, response)
        );
    }
}