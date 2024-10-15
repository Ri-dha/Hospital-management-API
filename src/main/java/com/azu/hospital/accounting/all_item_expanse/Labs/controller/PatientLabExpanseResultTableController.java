package com.azu.hospital.accounting.all_item_expanse.Labs.controller;

import com.azu.hospital.accounting.all_item_expanse.Labs.service.IPatientLabExpanseResultTableService;
import com.azu.hospital.accounting.all_item_expanse.Labs.service.PatientLabExpanseResultTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patient_lab_expanse_result_table")
@CrossOrigin
public class PatientLabExpanseResultTableController {

    private final IPatientLabExpanseResultTableService service;

    @Autowired
    public PatientLabExpanseResultTableController(
            @Qualifier("PatientLabExpanseResultTableServiceImp") IPatientLabExpanseResultTableService service) {
        this.service = service;
    }



    @PostMapping("/{patientId}")
    public ResponseEntity<Void> addPatientLabExpanseResultTable(
            @PathVariable("patientId") Long patientId
    ){
        service.addPatientLabExpanseResultTable(patientId);
        return ResponseEntity.ok().build();
    }




}
