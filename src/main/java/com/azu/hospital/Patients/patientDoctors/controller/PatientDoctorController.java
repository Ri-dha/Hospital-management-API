package com.azu.hospital.Patients.patientDoctors.controller;

import com.azu.hospital.Patients.patientDoctors.services.PatientDoctorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Patient Doctor" , description = "Patient Doctor Routes")
@RestController
@RequestMapping("api/v1/patient/doctors")
@CrossOrigin
public class PatientDoctorController {
    private final PatientDoctorService patientDoctorService;

    @Autowired
    public PatientDoctorController(PatientDoctorService patientDoctorService) {
        this.patientDoctorService = patientDoctorService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDoctorToPatient(@RequestParam Long patientId, @RequestParam Long doctorId) {
        return ResponseEntity.ok(patientDoctorService.addPatientToDoctor(patientId, doctorId));
    }

    @PutMapping("/transferring")
    public void transferringDoctorToPatient(@RequestParam Long patientId, @RequestParam Long doctorId) {
        patientDoctorService.transferringDoctorToPatient(patientId, doctorId);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getPatientDoctors(@RequestParam Long patientId) {
        return ResponseEntity.ok(patientDoctorService.getPatientDoctors(patientId));
    }



}
