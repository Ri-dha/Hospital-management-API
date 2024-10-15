package com.azu.hospital.Patients.surgicalList.controller;

import com.azu.hospital.Patients.surgicalList.request.AddDoctorToSurgicalRequest;
import com.azu.hospital.Patients.surgicalList.request.SurgicalListCreateRequest;
import com.azu.hospital.Patients.surgicalList.services.SurgicalListService;
import com.azu.hospital.Validator.DateValidator.DatePattern;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Patient Surgical" , description = "Patient Surgical Routes")
@RestController
@RequestMapping("/api/v1/patient/surgical-list")
@CrossOrigin
public class SurgicalListController {
    private final SurgicalListService surgicalListService;

    @Autowired
    public SurgicalListController(
            SurgicalListService surgicalListService
    ) {
        this.surgicalListService = surgicalListService;
    }



    @PostMapping("add")
    public void createSurgicalList(@Valid @ModelAttribute SurgicalListCreateRequest request) {
        surgicalListService.createSurgicalList(request);
    }



    @GetMapping("get")
    public ResponseEntity<?> getAllSurgicalList(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size,
            @RequestParam String surgicalDate
    ) {
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(surgicalListService.getAllSurgicalList(surgicalDate , pageable));
    }

    @GetMapping("get-doctors")
    public ResponseEntity<?> getDoctorSurgicalList(
            @RequestParam Long surgicalListId
    ) {
        return ResponseEntity.ok(surgicalListService.getDoctorSurgicalList(surgicalListId));

    }

    @PostMapping("/add-doctor")

    public void addDoctorToSurgical(@Valid @ModelAttribute AddDoctorToSurgicalRequest request) {
        surgicalListService.addDoctorToSurgical(request);
    }



    @PutMapping("in-surgical")
    public ResponseEntity<?> surgicalInSurgical(
            @RequestParam Long surgicalId,
            @RequestParam String state
    ) {
        return ResponseEntity.ok(surgicalListService.surgicalInSurgical(surgicalId, state));
    }

    @PutMapping("done-surgical")
    public ResponseEntity<?> surgicalDone(
            @RequestParam Long surgicalId,
            @RequestParam String state
    ) {
        return ResponseEntity.ok(surgicalListService.surgicalDone(surgicalId, state));
    }


}
