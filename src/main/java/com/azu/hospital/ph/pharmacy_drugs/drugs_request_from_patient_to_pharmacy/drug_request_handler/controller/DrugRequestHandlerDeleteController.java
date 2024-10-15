package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.controller;

import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.services.DrugRequestHandlerDeleteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/drugs-list/drugs-request")
public class DrugRequestHandlerDeleteController {
    private final DrugRequestHandlerDeleteServices deleteServices;

    @Autowired
    public DrugRequestHandlerDeleteController(DrugRequestHandlerDeleteServices deleteServices) {
        this.deleteServices = deleteServices;
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'PERMANENT', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF')")
    public ResponseEntity<Void> deleteDrugById(
            @RequestParam("drugId") Long drugId

    ){
        deleteServices.deleteDrugRequestHandlerById(drugId);
        return ResponseEntity.ok().build();
    }


}
