package com.azu.hospital.consultant.consultantPatient.vitalSign.controller;

import com.azu.hospital.consultant.consultantPatient.vitalSign.request.ConsultantCreateVitalSignRequest;
import com.azu.hospital.consultant.consultantPatient.vitalSign.request.ConsultantUpdateVitalSignRequest;
import com.azu.hospital.consultant.consultantPatient.vitalSign.services.ConsultantVitalSignService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/consultant/patient/vital-sign")
@CrossOrigin
public class ConsultantVitalSignController {

    private final ConsultantVitalSignService consultantVitalSignService;

    @Autowired
    public ConsultantVitalSignController(ConsultantVitalSignService consultantVitalSignService) {
        this.consultantVitalSignService = consultantVitalSignService;
    }

    @GetMapping("get-by-id")
    public ResponseEntity<?> findById(@RequestParam Long vitalSignId){
        return ResponseEntity.ok(consultantVitalSignService.findById(vitalSignId));
    }

    @GetMapping("get-by-patient-id")
    public ResponseEntity<?> getAllByPatientId(@RequestParam Long patientId ){
        return ResponseEntity.ok(consultantVitalSignService.getAllVitalSignByPatientId(patientId));
    }


    @PostMapping("add")
    public ResponseEntity<?> createNewVitalSign(@Valid @ModelAttribute ConsultantCreateVitalSignRequest request){
        return ResponseEntity.ok(consultantVitalSignService.createNewVitalSign(request));
    }

    @PutMapping("update")
    public void updateVitalSign(@Valid @ModelAttribute ConsultantUpdateVitalSignRequest request){
         consultantVitalSignService.updateVitalSign(request);
    }

}
