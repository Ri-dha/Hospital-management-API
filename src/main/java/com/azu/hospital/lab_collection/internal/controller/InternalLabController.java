package com.azu.hospital.lab_collection.internal.controller;


import com.azu.hospital.lab_collection.internal.request.CreateInternalLabRequest;
import com.azu.hospital.lab_collection.internal.services.InternalLabGetService;
import com.azu.hospital.lab_collection.internal.services.InternalLabService;
import com.azu.hospital.lab_collection.internal.services.InternalLabStateService;
import com.azu.hospital.utils.enums.lab.InternalLabRequestStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lab/internal-lab")
@CrossOrigin
public class InternalLabController {

    private final InternalLabService internalLabService;
    private final InternalLabStateService internalLabStateService;
    private final InternalLabGetService internalLabGetService;

    @Autowired
    public InternalLabController(InternalLabService internalLabService, InternalLabStateService internalLabStateService, InternalLabGetService internalLabGetService) {
        this.internalLabService = internalLabService;
        this.internalLabStateService = internalLabStateService;
        this.internalLabGetService = internalLabGetService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createInternalLab(
            @RequestParam("patientId") Long patientId,
            @RequestBody CreateInternalLabRequest request
    ) {
        return ResponseEntity.ok(internalLabService.createInternalLab(patientId, request));
    }



    @GetMapping("/get-by-state")
    public ResponseEntity<?> getInternalLabByState(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam List<InternalLabRequestStatusEnum> states
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(internalLabGetService.getInternalLabByState(pageable, states));
    }


    @GetMapping("/get-by-patient")
    public ResponseEntity<?> getInternalLabByPatient(
            @RequestParam Long patientId,
            @PageableDefault Pageable pageable
    ) {
        return ResponseEntity.ok(internalLabGetService.getByPatientId(patientId, pageable));
    }


    @PutMapping("/accept-nurse-test")
    @GetMapping("/get-by-state")
    public ResponseEntity<?> acceptNurseTest(
            @RequestParam Long intTestId,
            @RequestParam String state) {
        return ResponseEntity.ok(internalLabStateService.acceptLAbTestFromDoctor(intTestId, state));
    }

    @PutMapping("/lab-received-test")
    public ResponseEntity<?> labReceivedSample(
            @RequestParam Long intTestId,
            @RequestParam String state) {
        return ResponseEntity.ok(internalLabStateService.labReceivedSample(intTestId, state));
    }

    @PutMapping("/lab-complete-test")
    public ResponseEntity<?> labCompleteSample(
            @RequestParam Long intTestId,
            @RequestParam String state) {
        return ResponseEntity.ok(internalLabStateService.labCompleteSample(intTestId, state));
    }


    @PutMapping("/lab-getting-test")
    public ResponseEntity<?> labGettingSample(
            @RequestParam Long intTestId,
            @RequestParam String state) {
        return ResponseEntity.ok(internalLabStateService.gettingSample(intTestId, state));
    }

    @PutMapping("/lab-cancel-test")
    public ResponseEntity<?> labCancelTest(
            @RequestParam Long intTestId,
            @RequestParam String state) {
        return ResponseEntity.ok(internalLabStateService.CancelAcceptDoctorOrder(intTestId, state));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllWithRequestByRequestId(
            @RequestParam(name = "labId") Long labId
    ) {
        return ResponseEntity.ok(internalLabGetService.getInternalLabByRequestId(labId));
    }
}

