package com.azu.hospital.patient_expances.controller;

import com.azu.hospital.patient_expances.dto.PatientExpanseDto;
import com.azu.hospital.patient_expances.dto.PatientExpanseListDto;
import com.azu.hospital.patient_expances.request.PatientExpanseListUpdate;
import com.azu.hospital.patient_expances.request.PatientExpanseRequest;
import com.azu.hospital.patient_expances.request.PatientExpanseUpdate;
import com.azu.hospital.patient_expances.services.PatientExpanseAddServices;
import com.azu.hospital.patient_expances.services.PatientExpanseGetServices;
import com.azu.hospital.patient_expances.services.PatientExpanseUpdateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/patient-expanse")
@CrossOrigin

public class PatientExpanseController {

    private final PatientExpanseAddServices addServices;
    private final PatientExpanseGetServices getServices;
    private final PatientExpanseUpdateServices updateServices;

    @Autowired
    public PatientExpanseController(PatientExpanseAddServices addServices, PatientExpanseGetServices getServices,
                                    PatientExpanseUpdateServices updateServices) {
        this.addServices = addServices;
        this.getServices = getServices;
        this.updateServices = updateServices;
    }


    @PostMapping("/add")
    public ResponseEntity<?> createNewList(
            @RequestParam("patientId") Long patientId,
            @RequestBody List<PatientExpanseRequest> requests){
        addServices.addPatientList(patientId, requests);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list/{expanseId}")
    public ResponseEntity<PatientExpanseListDto> getExpanseListById(
            @PathVariable("expanseId") Long expanseId) {
        return ResponseEntity.ok(getServices.getPatientExpanseListById(expanseId));
    }
    @GetMapping("/expanse/{expanseId}")
    public ResponseEntity<PatientExpanseDto> getExpanseById(@PathVariable("expanseId") Long expanseId) {
        return ResponseEntity.ok(getServices.getPatientExpanseById(expanseId));
    }


    @GetMapping("/list/patient")
    public ResponseEntity<List<PatientExpanseListDto>> getAllPatientExpanseListByPatientId(
            @RequestParam("patientId") Long patientId
    ){
        return ResponseEntity.ok(getServices.getAllPatientExpanseListByPatientId(patientId));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<PatientExpanseListDto>> getAllPatientExpanseList(
            Pageable pageable,
            @RequestParam String patientName
    ){
        Page<PatientExpanseListDto> expenseListDtoPage = getServices.getAllPatientExpanseList(patientName,pageable);
        return ResponseEntity.ok(expenseListDtoPage);
    }

    @PutMapping("/list/update/{listId}")
    public ResponseEntity<?> updatePatientExpense(
            @PathVariable("listId") Long listId,
            @RequestBody PatientExpanseListUpdate request
    ){
        updateServices.updatePatientExpense(listId,request);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/list/add")
    public ResponseEntity<?> addPatientExpense(
            @RequestParam(name = "listId") Long listId,
            @RequestBody List<PatientExpanseRequest> request
    ){
        addServices.addPatientExpanseToList(listId,request);
        return ResponseEntity.ok().build();
    }



}
