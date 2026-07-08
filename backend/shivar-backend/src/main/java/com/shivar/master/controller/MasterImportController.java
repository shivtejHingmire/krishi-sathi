package com.shivar.master.controller;

import com.shivar.common.response.ResponseBuilder;
import com.shivar.master.service.MasterImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/master")
@RequiredArgsConstructor
public class MasterImportController {

    private final MasterImportService masterImportService;

    @PostMapping("/import")
    public Object importMasterData() {

        masterImportService.importMasterData();

        return ResponseBuilder.success(
                "Master data imported successfully.",
                null
        );
    }
}