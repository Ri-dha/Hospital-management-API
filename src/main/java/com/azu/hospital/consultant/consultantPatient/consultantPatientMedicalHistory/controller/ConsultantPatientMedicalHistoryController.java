package com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.controller;

import com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.request.CreateConsultantPatientMedicalHistoryRequest;
import com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.request.UpdateConsultantPatientMedicalHistoryRequest;
import com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.service.ConsultantPatientMedicalHistoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/consultant/patient/medical-history")
@CrossOrigin
public class ConsultantPatientMedicalHistoryController {

    private final ConsultantPatientMedicalHistoryService medicalHistoryService;

    @Autowired
    public ConsultantPatientMedicalHistoryController(ConsultantPatientMedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    @GetMapping("get-last")
    public ResponseEntity<?> getLastMedicalHistory(@RequestParam Long patientId){
        return ResponseEntity.ok(medicalHistoryService.getLastMedicalHistory(patientId));
    }

    @GetMapping("get-history")
    public ResponseEntity<?> getHistoryOfMedicalHistory(@RequestParam Long patientId){
        return ResponseEntity.ok(medicalHistoryService.getHistoryOfMedicalHistory(patientId));
    }

    @PostMapping("new")
    public ResponseEntity<?> createNewMedicalHistory(
            @Valid @ModelAttribute CreateConsultantPatientMedicalHistoryRequest request
    ){
      return ResponseEntity.ok(medicalHistoryService.createNewMedicalHistory(request));
    }


    @PutMapping("update")
    public void updateMedicalHistory(
            @Valid @ModelAttribute UpdateConsultantPatientMedicalHistoryRequest request
    ){
        medicalHistoryService.updatePatientMedicalHistory(request);
    }
}
