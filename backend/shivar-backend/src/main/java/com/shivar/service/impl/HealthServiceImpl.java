package com.shivar.service.impl;

import com.shivar.dto.response.HealthResponse;
import com.shivar.service.HealthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HealthServiceImpl implements HealthService {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${application.version}")
    private String applicationVersion;

    @Override
    public HealthResponse getApplicationHealth() {

        return HealthResponse.builder()
                .application(applicationName)
                .version(applicationVersion)
                .status("UP")
                .build();
    }
}