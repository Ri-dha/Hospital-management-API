package com.azu.hospital.accounting.all_item_expanse.ecg.controller;

import com.azu.hospital.accounting.all_item_expanse.ecg.service.PatientEcgExpanseResultTableAddServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/patient-ecg-expanse-result-table")
@CrossOrigin
public class PatientEcgExpanseResultTableController {
    private final PatientEcgExpanseResultTableAddServices addServices;

    @Autowired
    public PatientEcgExpanseResultTableController(
            @Qualifier("PatientEcgExpanseResultTableAddServicesImp") PatientEcgExpanseResultTableAddServices addServices) {
        this.addServices = addServices;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> addPatientEcgExpanseResultTable(
            @PathVariable("id") Long patientId) {
//        addServices.addPatientEcgExpanseResultTable(patientId);
        return ResponseEntity.ok().build();
    }


}
