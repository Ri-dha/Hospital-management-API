package com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.controller;

import com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.request.CreateInternalUltrasoundResultRequest;
import com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.services.InternalUltrasoundResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/ultrasound/internal/result")
@CrossOrigin

public class InternalUltrasoundRequestController {

    private final InternalUltrasoundResultService internalUltrasoundRequestService;

    @Autowired
    public InternalUltrasoundRequestController(InternalUltrasoundResultService internalUltrasoundRequestService) {
        this.internalUltrasoundRequestService = internalUltrasoundRequestService;
    }

    @PostMapping("new")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<?> createResult(
            @Valid @ModelAttribute CreateInternalUltrasoundResultRequest request
    ) throws IOException {
        internalUltrasoundRequestService.createResult(request);
     return ResponseEntity.ok().build() ;
    }


    @GetMapping()
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<?> getByTestId(
            @RequestParam Long testId
    ){
        return ResponseEntity.ok(internalUltrasoundRequestService.getResultByTestId(testId));
    }
}
