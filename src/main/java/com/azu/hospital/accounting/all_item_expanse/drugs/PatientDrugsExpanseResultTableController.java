package com.azu.hospital.accounting.all_item_expanse.drugs;

import com.azu.hospital.accounting.all_item_expanse.drugs.services.IPatientDrugsExpanseResultTableAddServices;
import com.azu.hospital.accounting.all_item_expanse.drugs.services.PatientDrugsExpanseResultTableAddServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/patient-drugs-expanse-result-table")
@CrossOrigin
public class PatientDrugsExpanseResultTableController {

    private final IPatientDrugsExpanseResultTableAddServices addServices;

    @Autowired
    public PatientDrugsExpanseResultTableController(
            @Qualifier("PatientDrugsExpanseResultTableAddServicesImp") IPatientDrugsExpanseResultTableAddServices addServices) {
        this.addServices = addServices;
    }



    @PostMapping("/{patientId}")
    public ResponseEntity<Void> addPatientDrugsExpanseResultTable(
            @PathVariable("patientId") Long patientId) {
        addServices.addPatientDrugsExpanseResultTable(patientId);
        return ResponseEntity.ok().build();
    }
}
