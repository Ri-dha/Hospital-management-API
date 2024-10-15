package com.azu.hospital.accounting.all_item_expanse.consumables.controller;

import com.azu.hospital.accounting.all_item_expanse.consumables.service.IPatientsExpansesResultTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/patient-consumables-expanse-result-table")
@CrossOrigin
public class PatientsExpansesResultTableController {
    private final IPatientsExpansesResultTableService addServices;


    @Autowired
    public PatientsExpansesResultTableController(
            @Qualifier("PatientsExpansesResultTableServiceImp") IPatientsExpansesResultTableService addServices) {
        this.addServices = addServices;
    }

    @PostMapping("/{patientId}")
    public ResponseEntity<Void> addPatientConsumablesExpanseResultTable(
            @PathVariable("patientId") Long patientId) {
        addServices.addPatientExpansesExpanseResultTable(patientId);
        return ResponseEntity.ok().build();
    }




}
