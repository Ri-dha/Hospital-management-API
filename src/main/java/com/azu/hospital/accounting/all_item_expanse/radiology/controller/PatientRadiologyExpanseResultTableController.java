package com.azu.hospital.accounting.all_item_expanse.radiology.controller;

import com.azu.hospital.accounting.all_item_expanse.radiology.service.PatientRadiologyExpanseResultTableAddServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/patient-radiology-expanse-result-table")
@CrossOrigin
public class PatientRadiologyExpanseResultTableController {

    private final PatientRadiologyExpanseResultTableAddServices addServices;

    @Autowired
    public PatientRadiologyExpanseResultTableController(
            @Qualifier("PatientRadiologyExpanseResultTableAddServicesImp") PatientRadiologyExpanseResultTableAddServices addServices) {
        this.addServices = addServices;
    }

    @PostMapping("/{patientId}")
    public ResponseEntity<Void> addPatientRadiologyExpanseResultTable(
            @PathVariable("patientId") Long patientId) {
        addServices.addPatientRadiologyExpanseResultTable(patientId);
        return ResponseEntity.ok().build();
    }

}
