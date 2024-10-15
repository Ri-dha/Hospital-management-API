package com.azu.hospital.consultant.consultantPatient.controller;

import com.azu.hospital.consultant.consultantPatient.request.CreateNewPatientRequest;
import com.azu.hospital.consultant.consultantPatient.services.ConsultantPatientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Consultant Patient" , description = "Consultant Patient Routes")
@RestController
@RequestMapping("api/v1/consultant/patient")
@CrossOrigin
public class ConsultantPatientController {

    private final ConsultantPatientService consultantPatientService;

    @Autowired
    public ConsultantPatientController(ConsultantPatientService consultantPatientService) {
        this.consultantPatientService = consultantPatientService;
    }


    @PostMapping("new")
    public ResponseEntity<?> createNewPatient(@Valid @ModelAttribute CreateNewPatientRequest request) throws IOException {
        return ResponseEntity.ok(consultantPatientService.createNewPatient(request));
    }

    @GetMapping("get-by-doctor-id")
    public ResponseEntity<?> getByDoctorId(@RequestParam Long doctorId)  {
        return ResponseEntity.ok(consultantPatientService.getAllByDoctorId(doctorId));
    }

    @GetMapping("get-by-patient-id")
    public ResponseEntity<?> getByPatientId(@RequestParam Long patientId)  {
        return ResponseEntity.ok(consultantPatientService.getByPatientId(patientId));
    }
}
