package com.azu.hospital.Patients.MedicalHistory.controller;


import com.azu.hospital.Patients.MedicalHistory.request.AddMedicalHistoryRequest;
import com.azu.hospital.Patients.MedicalHistory.request.UpdateMedicalHistoryRequest;
import com.azu.hospital.Patients.MedicalHistory.services.MedicalHistoryService;
import com.azu.hospital.Patients.MedicalHistory.services.MedicalHistoryAddService;
import com.azu.hospital.utils.enums.patient.PatientTriageEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Medical History", description = "Medical History Routes")
@RestController
@RequestMapping("api/v1/patient/medical-history")
@CrossOrigin
public class MedicalHistoryController {

    private final MedicalHistoryService medicalHistoryService;
    private final MedicalHistoryAddService medicalHistoryAddService;

    @Autowired
    public MedicalHistoryController(MedicalHistoryService medicalHistoryService, MedicalHistoryAddService medicalHistoryAddService) {
        this.medicalHistoryService = medicalHistoryService;
        this.medicalHistoryAddService = medicalHistoryAddService;
    }

    @GetMapping("/get-by-patient-id")
//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'HOSPITAL_ASSISTANCE_MANAGER', 'DOCTOR', 'PERMANENT', 'NURSES', 'NURSING_HEAD_CHIEF', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF', 'PHYSICAL_THERAPY_MANAGER', 'CHIEF_OPERATING_OFFICER')")
    public ResponseEntity<?> getMedicalHistoryByPatientId(@RequestParam Long patientId) {
        return ResponseEntity.ok(medicalHistoryService.getMedicalHistoryByPatientId(patientId));
    }

    @GetMapping("/get-all-by-patient-id")
//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'HOSPITAL_ASSISTANCE_MANAGER', 'DOCTOR', 'PERMANENT', 'NURSES', 'NURSING_HEAD_CHIEF', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF', 'PHYSICAL_THERAPY_MANAGER', 'CHIEF_OPERATING_OFFICER')")
    public ResponseEntity<?> getAllMedicalHistoryByPatientId(@RequestParam Long patientId) {
        return ResponseEntity.ok(medicalHistoryService.getAllMedicalHistoryByPatientId(patientId));
    }


    @PostMapping("/add")
//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'HOSPITAL_ASSISTANCE_MANAGER', 'DOCTOR', 'PERMANENT', 'NURSES', 'NURSING_HEAD_CHIEF', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF', 'PHYSICAL_THERAPY_MANAGER', 'CHIEF_OPERATING_OFFICER')")

    public ResponseEntity<?> addMedicalHistory(@Valid @ModelAttribute AddMedicalHistoryRequest request) {
        return ResponseEntity.ok(medicalHistoryAddService.addMedicalHistory(request));
    }

    @PutMapping("/update")
//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'HOSPITAL_ASSISTANCE_MANAGER', 'DOCTOR', 'PERMANENT', 'NURSES', 'NURSING_HEAD_CHIEF', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF', 'PHYSICAL_THERAPY_MANAGER', 'CHIEF_OPERATING_OFFICER')")
    public void updateMedicalHistory(
            @Valid @ModelAttribute UpdateMedicalHistoryRequest request,
            @RequestParam Long medicalHistoryId
    ) {
        medicalHistoryService.updateMedicalHistory(request, medicalHistoryId);
    }

    @PutMapping("/update-triage")
//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'HOSPITAL_ASSISTANCE_MANAGER', 'DOCTOR', 'PERMANENT', 'NURSES', 'NURSING_HEAD_CHIEF', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF', 'PHYSICAL_THERAPY_MANAGER', 'CHIEF_OPERATING_OFFICER')")
    public void updateTriage(
            @RequestParam Long patientId,
            @RequestParam PatientTriageEnum triage
    ) {
        medicalHistoryService.updateTriage(patientId, triage);
    }

}
