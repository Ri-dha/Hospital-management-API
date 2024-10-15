package com.azu.hospital.accounting.patientBill.controller;

import com.azu.hospital.accounting.patientBill.requests.PatientBillCreateRequest;
import com.azu.hospital.accounting.patientBill.requests.PatientBillUpdateRequest;
import com.azu.hospital.accounting.patientBill.services.PatientBillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patient/patient-bill")
@CrossOrigin
public class PatientBillController {

    private final PatientBillService patientBillService;

    @Autowired
    public PatientBillController(PatientBillService patientBillService) {
        this.patientBillService = patientBillService;
    }


    @PostMapping("/add")
    public void createPatientBill(@Valid @ModelAttribute PatientBillCreateRequest request) {
        patientBillService.CreateNewPatientBill(request);
    }

    @PutMapping("/update")
    public void updatePatientBill(
            @RequestParam Long patientBillId,
            @Valid @ModelAttribute PatientBillUpdateRequest request
    ) {
        patientBillService.updatePatientBill(patientBillId, request);
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<?> getPatientBillById(
            @RequestParam Long patientBillId
    ) {
        return ResponseEntity.ok(patientBillService.getById(patientBillId));
    }

    @GetMapping("/get-by-patient-id")
    public ResponseEntity<?> getPatientBillsByPatientId(
            @RequestParam Long patientId,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(patientBillService.getByPatientId(patientId, pageable));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(patientBillService.getAll(pageable));
    }
}
