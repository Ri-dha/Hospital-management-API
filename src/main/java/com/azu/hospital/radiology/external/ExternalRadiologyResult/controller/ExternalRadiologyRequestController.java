package com.azu.hospital.radiology.external.ExternalRadiologyResult.controller;

import com.azu.hospital.radiology.external.ExternalRadiologyResult.request.CreateExternalRadiologyResultRequest;
import com.azu.hospital.radiology.external.ExternalRadiologyResult.services.ExternalRadiologyResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/radiology/external/result")
@CrossOrigin
public class ExternalRadiologyRequestController {

    private final ExternalRadiologyResultService externalRadiologyRequestService;

    @Autowired
    public ExternalRadiologyRequestController(ExternalRadiologyResultService externalRadiologyRequestService) {
        this.externalRadiologyRequestService = externalRadiologyRequestService;
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyRole('RADIOLOGIST', 'HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<Void> createResult(
            @Valid @ModelAttribute CreateExternalRadiologyResultRequest request
    ) throws IOException {
        externalRadiologyRequestService.createResult(request);
      return ResponseEntity.ok().build();
    }


    @GetMapping()
    @PreAuthorize("hasAnyRole('RADIOLOGIST', 'HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<?> getByTestId(
            @RequestParam(name = "testId") Long testId
    ){
        return ResponseEntity.ok(externalRadiologyRequestService.getResultByTestId(testId));
    }
}
