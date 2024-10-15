package com.azu.hospital.lab_collection.external.controller;

import com.azu.hospital.lab_collection.external.dto.ExternalLabDto;
import com.azu.hospital.lab_collection.external.request.CreateExternalLabRequest;
import com.azu.hospital.lab_collection.external.services.ExternalLabGetService;
import com.azu.hospital.lab_collection.external.services.ExternalLabService;
import com.azu.hospital.lab_collection.external.services.ExternalLabStateService;
import com.azu.hospital.utils.enums.lab.LabRequestStatusEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Lab External" , description = "Lab External Routes")
@RestController
@RequestMapping("api/v1/lab/external-lab")
@CrossOrigin

public class ExternalLabController {

    private final ExternalLabService externalLabService;
    private final ExternalLabStateService externalLabStateService;
    private final ExternalLabGetService externalLabGetService;

    @Autowired
    public ExternalLabController(ExternalLabService externalLabService, ExternalLabStateService externalLabStateService, ExternalLabGetService externalLabGetService) {
        this.externalLabService = externalLabService;
        this.externalLabStateService = externalLabStateService;
        this.externalLabGetService = externalLabGetService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createExternalLab(@Valid @RequestBody CreateExternalLabRequest request) {
        return ResponseEntity.ok(externalLabService.createExternalLab(request));
    }

    @GetMapping("/patientInfo")
    public ResponseEntity<ExternalLabDto> getPatientInfo(
            @RequestParam("patientId") Long patientId
    ){
        return ResponseEntity.ok(externalLabGetService.getPatientInfo(patientId));
    }

    @GetMapping
    public ResponseEntity<?> getAllExternalLab(
            @PageableDefault Pageable pageable ,
            @RequestParam("states") List<LabRequestStatusEnum> states
    ) {

        return ResponseEntity.ok(externalLabGetService.getAllExternalLab(states , pageable));
    }

    @GetMapping("/get-not-archived")
    public ResponseEntity<?> getExternalLabNotArchived(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(externalLabGetService.getExternalLabNotArchived(pageable));
    }

    @GetMapping("/get-archived")
    public ResponseEntity<?> getExternalLabArchived(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(externalLabGetService.getExternalLabArchived(pageable));
    }


    @PutMapping("/lab-received-test")
    public ResponseEntity<?> labReceivedSample(
            @RequestParam Long extTestId,
            @RequestParam String state
    ) {
        return ResponseEntity.ok(externalLabStateService.labReceivedSample(extTestId,state));
    }

    @PutMapping("/lab-complete-test")

    public ResponseEntity<?> labCompleteSample(
            @RequestParam Long extTestId,
            @RequestParam String state
    ) {
        return ResponseEntity.ok(externalLabStateService.labCompleteSample(extTestId,state));
    }
    @PutMapping("/lab-cancel-test")
    public ResponseEntity<?> labCancelSample(
            @RequestParam Long extTestId,
            @RequestParam("state") String state

    ) {
        return ResponseEntity.ok(externalLabStateService.labCancelSample(extTestId,state));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllWithRequestByRequestId(
            @RequestParam(name = "labId") Long labId
    ) {
        return ResponseEntity.ok(externalLabGetService.getExternalLabByRequestId(labId));
    }
}
