package com.azu.hospital.accounting.all_item_expanse.ultrasound.controller;


import com.azu.hospital.accounting.all_item_expanse.ultrasound.service.PatientUltrasoundExpanseResultTableAddServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/patient-ultrasound-expanse-result-table")
@CrossOrigin
public class PatientUltrasoundExpanseResultTableController {

    private final PatientUltrasoundExpanseResultTableAddServices addServices;

    public PatientUltrasoundExpanseResultTableController(
            @Qualifier("PatientUltrasoundExpanseResultTableAddServicesImp") PatientUltrasoundExpanseResultTableAddServices addServices) {
        this.addServices = addServices;
    }


    @PostMapping("/{patientId}")
    public ResponseEntity<Void> addPatientUltrasoundExpanseResultTable(
            @PathVariable("patientId") Long patientId) {
        addServices.addPatientUltrasoundExpanseResultTable(patientId);
        return ResponseEntity.ok().build();
    }



}
