package com.azu.hospital.radiology.internal.InternalRadiologyResult.controller;

import com.azu.hospital.radiology.internal.InternalRadiologyResult.dto.InternalRadiologyResultDto;
import com.azu.hospital.radiology.internal.InternalRadiologyResult.request.CreateInternalRadiologyResultRequest;
import com.azu.hospital.radiology.internal.InternalRadiologyResult.services.InternalRadiologyResultService;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/radiology/internal/result")
@CrossOrigin
//@PreAuthorize("hasAnyRole('RADIOLOGIST', 'HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER')")
public class InternalRadiologyRequestController {

    private final InternalRadiologyResultService internalRadiologyRequestService;

    @Autowired
    public InternalRadiologyRequestController(InternalRadiologyResultService internalRadiologyRequestService) {
        this.internalRadiologyRequestService = internalRadiologyRequestService;
    }

    @PostMapping("/new")
    public void createResult(
            @Valid @ModelAttribute CreateInternalRadiologyResultRequest request
    ) throws IOException {
        internalRadiologyRequestService.createResult(request);
    }


    @GetMapping
    public ResponseEntity<?> getByTestId(
            @RequestParam Long testId
    ) {
        return ResponseEntity.ok(internalRadiologyRequestService.getResultByTestId(testId));
    }

    @GetMapping(value = "/get-all-results-by-patient-id")
    public ResponseEntity<List<InternalRadiologyResultDto>> getResultsById(
            @RequestParam("patientId") Long id,
            @RequestParam(value = "type", required = false) RadiologyTypeEnum radiologyType){
        return ResponseEntity.ok(internalRadiologyRequestService.getAllResultsByPatientIdAndType(id, radiologyType));
    }
}
