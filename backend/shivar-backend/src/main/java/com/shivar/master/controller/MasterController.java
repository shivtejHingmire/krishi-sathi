package com.shivar.master.controller;

import com.shivar.common.response.ApiResponse;
import com.shivar.common.response.ResponseBuilder;
import com.shivar.master.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/master")
@RequiredArgsConstructor
public class MasterController {

    private final MasterService masterService;

    @GetMapping("/states")
    public ResponseEntity<ApiResponse> getAllStates() {

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        "States fetched successfully",
                        masterService.getAllStates()
                )
        );
    }

    @GetMapping("/districts/state/{stateId}")
    public ResponseEntity<ApiResponse> getDistrictsByState(@PathVariable Long stateId) {

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        "Districts fetched successfully",
                        masterService.getDistrictsByState(stateId)
                )
        );
    }

    @GetMapping("/talukas/district/{districtId}")
    public ResponseEntity<ApiResponse> getTalukasByDistrict(@PathVariable Long districtId) {

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        "Talukas fetched successfully",
                        masterService.getTalukasByDistrict(districtId)
                )
        );
    }

    @GetMapping("/villages/taluka/{talukaId}")
    public ResponseEntity<ApiResponse> getVillagesByTaluka(@PathVariable Long talukaId) {

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        "Villages fetched successfully",
                        masterService.getVillagesByTaluka(talukaId)
                )
        );
    }
}