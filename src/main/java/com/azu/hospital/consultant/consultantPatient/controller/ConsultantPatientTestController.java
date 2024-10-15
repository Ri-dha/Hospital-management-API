package com.azu.hospital.consultant.consultantPatient.controller;

import com.azu.hospital.consultant.consultantPatient.request.ConsultantPatientEcg;
import com.azu.hospital.consultant.consultantPatient.request.CreateNewPatientRequest;
import com.azu.hospital.consultant.consultantPatient.services.*;
import com.azu.hospital.lab_collection.internal.request.CreateInternalLabRequest;
import com.azu.hospital.radiology.internal.request.CreateInternalRadiologyTestRequest;
import com.azu.hospital.ultrasound.internal.request.CreateInternalUltrasoundTestRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Consultant Patient Test" , description = "Consultant Patient Tests Routes")
@RestController
@RequestMapping("api/v1/consultant/patient/tests")
@CrossOrigin
public class ConsultantPatientTestController {

    private final ConsultantEcgService consultantEcgService;
    private final ConsultantRadiologyService consultantRadiologyService;
    private final ConsultantLabService consultantLabService;
    private final ConsultantUltrasoundService consultantUltrasoundService;

    public ConsultantPatientTestController(
            ConsultantEcgService consultantEcgService,
            ConsultantRadiologyService consultantRadiologyService,
            ConsultantLabService consultantLabService,
            ConsultantUltrasoundService consultantUltrasoundService
    ) {
        this.consultantEcgService = consultantEcgService;
        this.consultantRadiologyService = consultantRadiologyService;
        this.consultantLabService = consultantLabService;
        this.consultantUltrasoundService = consultantUltrasoundService;
    }


    @PostMapping("/new-ecg")
    public ResponseEntity<?> createNewEcgTest(
            @RequestParam Long patientId,
            @RequestBody ConsultantPatientEcg request

            ){
      return ResponseEntity.ok(consultantEcgService.createNewEcgTest(patientId,request));
    }

    @PostMapping("/new-radiology")
    public ResponseEntity<?> createNewRadiologyTest(@Valid @ModelAttribute CreateInternalRadiologyTestRequest request){
      return ResponseEntity.ok(consultantRadiologyService.createNewRadiologyTest(request));
    }

    @PostMapping("/new-lab")
    public ResponseEntity<?> createInternalLab(
            @RequestParam("patientId") Long patientId,
            @RequestBody CreateInternalLabRequest request
    ){
        return ResponseEntity.ok(consultantLabService.createInternalLab(patientId ,request));
    }

    @PostMapping("/new-ultrasound")
    public ResponseEntity<?> createNewUltrasoundTest(@Valid @ModelAttribute CreateInternalUltrasoundTestRequest request){
        return ResponseEntity.ok(consultantUltrasoundService.createNewUltrasoundTest(request));
    }


    @GetMapping("/get-ecg-by-patient-id")
    public ResponseEntity<?> getEcgByPatientIdTest(@RequestParam Long patientId){
        return ResponseEntity.ok(consultantEcgService.getEcgByPatientIdTest(patientId));
    }

    @GetMapping("/get-ecg")
    public ResponseEntity<?> getEcgTest(
            @RequestParam(defaultValue = "0") Integer page ,
            @RequestParam(defaultValue = "10") Integer size
    ){
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(consultantEcgService.getEcgTest(pageable));
    }


    @GetMapping("/get-lab-test-by-patient-id")
    public ResponseEntity<?> getLabTestByPatientIdTest(@RequestParam Long patientId){
        return ResponseEntity.ok(consultantLabService.getLabTestByPatientIdTest(patientId));
    }

    @GetMapping("/get-all-lab-test")
    public ResponseEntity<?> getAllLabTest(
            @RequestParam(defaultValue = "0") Integer page ,
            @RequestParam(defaultValue = "10") Integer size
    ){
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(consultantLabService.getLabTest(pageable));
    }



    @GetMapping("/get-radiology-by-patient-id")
    public ResponseEntity<?> getRadiologyByPatientIdTest(@RequestParam Long patientId){
        return ResponseEntity.ok(consultantRadiologyService.getRadiologyTestByPatientId(patientId));
    }

    @GetMapping("/get-all-radiology-test")
    public ResponseEntity<?> getAllRadiology(
            @RequestParam(defaultValue = "0") Integer page ,
            @RequestParam(defaultValue = "10") Integer size
    ){
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(consultantRadiologyService.getRadiologyTest(pageable));
    }


    @GetMapping("/get-ultrasound-by-patient-id")
    public ResponseEntity<?> getUltrasoundByPatientIdTest(@RequestParam Long patientId){
        return ResponseEntity.ok(consultantUltrasoundService.getUltrasoundTestByPatientId(patientId));
    }

    @GetMapping("/get-all-ultrasound-test")
    public ResponseEntity<?> getAllUltrasound(
            @RequestParam(defaultValue = "0") Integer page ,
            @RequestParam(defaultValue = "10") Integer size
    ){
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(consultantUltrasoundService.getUltrasoundTest(pageable));
    }
}
