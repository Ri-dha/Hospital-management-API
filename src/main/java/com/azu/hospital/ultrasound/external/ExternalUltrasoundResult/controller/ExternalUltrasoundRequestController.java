package com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.controller;

import com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.request.CreateExternalUltrasoundResultRequest;
import com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.services.ExternalUltrasoundResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/ultrasound/external/result")
@CrossOrigin

public class ExternalUltrasoundRequestController {

    private final ExternalUltrasoundResultService externalUltrasoundRequestService;

    @Autowired
    public ExternalUltrasoundRequestController(ExternalUltrasoundResultService externalUltrasoundRequestService) {
        this.externalUltrasoundRequestService = externalUltrasoundRequestService;
    }

    @PostMapping("new")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<Void> createResult(
            @Valid @ModelAttribute CreateExternalUltrasoundResultRequest request
    ) throws IOException {
     externalUltrasoundRequestService.createResult(request) ;
        return ResponseEntity.ok().build();
    }


    @GetMapping()
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<?> getByTestId(
            @RequestParam Long testId
    ){
        return ResponseEntity.ok(externalUltrasoundRequestService.getResultByTestId(testId));
    }
}
