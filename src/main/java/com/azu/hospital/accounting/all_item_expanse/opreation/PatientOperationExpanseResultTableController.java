package com.azu.hospital.accounting.all_item_expanse.opreation;

import com.azu.hospital.accounting.all_item_expanse.opreation.service.PatientOperationExpanseResultTableAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patient_operation_expanse_result_table")
@CrossOrigin
public class PatientOperationExpanseResultTableController {


    private final PatientOperationExpanseResultTableAddService service;

    @Autowired
    public PatientOperationExpanseResultTableController(
            @Qualifier("PatientOperationExpanseResultTableAddServiceImp") PatientOperationExpanseResultTableAddService service) {
        this.service = service;
    }


    @PostMapping("/{patientId}")
    public ResponseEntity<?> addPatientOperationExpanseResultTable(
            @PathVariable("patientId") Long patientId
    ){
        service.addPatientOperationExpanseResultTable(patientId);
        return ResponseEntity.ok().build();
    }


}
