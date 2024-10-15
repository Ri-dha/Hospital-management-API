package com.azu.hospital.ecg.internal.controller;

import com.azu.hospital.ecg.internal.request.InternalEcgTestRequest;
import com.azu.hospital.ecg.internal.request.InternalEcgTestRequestUpdate;
import com.azu.hospital.ecg.internal.services.EcgGetService;
import com.azu.hospital.ecg.internal.services.EcgService;
import com.azu.hospital.utils.enums.ecg.EcgStates;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("api/v1/ecg/internal")
@CrossOrigin
public class EcgController {
    private final EcgService ecgService;
    private final EcgGetService ecgGetService;

    @Autowired
    public EcgController(EcgService ecgService, EcgGetService ecgGetService) {
        this.ecgService = ecgService;
        this.ecgGetService = ecgGetService;
    }


    @GetMapping("/get_all_by_nurse")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER'," +
            " 'DOCTOR', 'PERMANENT', 'NURSES')")
    public ResponseEntity<?> getAllByNurseId(

            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false) List<EcgStates> state,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ecgGetService.getAllByFilter(search, state, pageable));
    }

    @GetMapping("/get_by_id")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER'," +
            " 'DOCTOR', 'PERMANENT', 'NURSES')")
    public ResponseEntity<?> getById(
            @RequestParam Long id
    ) {
        return ResponseEntity.ok(ecgGetService.getById(id));
    }

    @GetMapping("/get_all_by_patient_id")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER'," +
            " 'DOCTOR', 'PERMANENT', 'NURSES')")
    public ResponseEntity<?> getAllByPatientId(
            @RequestParam Long patientId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ecgGetService.getAllByPatientId(patientId, pageable));
    }

    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER', 'ADMINISTRATIVE_MANAGER'," +
            " 'DOCTOR', 'PERMANENT', 'NURSES')")
    @PostMapping("/new-ecg-test")
    public ResponseEntity<?> createNewEcgTest(
            @Valid @ModelAttribute InternalEcgTestRequest request
    ) throws IOException {
        return ResponseEntity.ok(ecgService.createNewEcgTest(request));
    }


    @PostMapping("/new-ecg-test-request")
    public ResponseEntity<?> createNewEcgTestRequest(
            @RequestParam String note,
            @RequestParam Long patientId
    ) {
        return ResponseEntity.ok(ecgService.createRequest(patientId, note));
    }


    @PutMapping("/update-ecg-test")
    public ResponseEntity<?> updateEcgTest(
            @RequestParam Long id,
            @Valid @ModelAttribute InternalEcgTestRequestUpdate request
    ) throws IOException {
        return ResponseEntity.ok(ecgService.updateEcgTest(id,request));
    }

}
