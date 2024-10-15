package com.azu.hospital.ecg.external.controller;

import com.azu.hospital.ecg.external.entity.EcgPatientData;
import com.azu.hospital.ecg.external.request.ExternalAddResultToEcgTest;
import com.azu.hospital.ecg.external.services.ExternalEcgGetService;
import com.azu.hospital.ecg.external.services.ExternalEcgService;
import com.azu.hospital.ecg.external.services.ExternalEcgStateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "External Ecg" , description = "External Ecg Routes")
@RestController
@RequestMapping("api/v1/ecg/external")
@CrossOrigin

public class ExternalEcgController {
    private final ExternalEcgService externalEcgService;
    private final ExternalEcgGetService externalEcgGetService;
    private final ExternalEcgStateService externalEcgStateService;

    @Autowired
    public ExternalEcgController(
            ExternalEcgService externalEcgService,
            ExternalEcgGetService externalEcgGetService,
            ExternalEcgStateService externalEcgStateService
    ) {
        this.externalEcgService = externalEcgService;
        this.externalEcgGetService = externalEcgGetService;
        this.externalEcgStateService = externalEcgStateService;
    }

    @GetMapping("/get-ecg-by-nurse")
    public ResponseEntity<?> getAllByNurse() {
        return ResponseEntity.ok(externalEcgGetService.getAllByNurseEcgTest());
    };
    @GetMapping("/get-ecg")
    public ResponseEntity<?> getAllByPatient(){
       return ResponseEntity.ok(externalEcgGetService.getAllByPatientEcgTest());
    };

    @PostMapping("/new-ecg-test")
    public ResponseEntity<?> createNewEcgTest(@Valid @ModelAttribute EcgPatientData patientData){
        return ResponseEntity.ok(externalEcgService.createNewEcgTest(patientData));
    };

    @PostMapping("/add-ecg-result")
    public ResponseEntity<?> addEcgResult(@Valid @ModelAttribute ExternalAddResultToEcgTest request) throws IOException {
        return ResponseEntity.ok(externalEcgService.addResult(request));
    };

    @PutMapping("/accept-ecg")
    void acceptFromNurse(@RequestParam Long testId){
        externalEcgStateService.acceptFromNurse(testId);
    };
}
