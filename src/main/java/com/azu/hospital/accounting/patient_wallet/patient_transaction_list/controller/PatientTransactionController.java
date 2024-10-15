package com.azu.hospital.accounting.patient_wallet.patient_transaction_list.controller;

import com.azu.hospital.accounting.patient_wallet.patient_transaction_list.services.IPatientTransactionService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/patient/transaction")
public class PatientTransactionController {

    private final IPatientTransactionService patientTransactionService;

    public PatientTransactionController(IPatientTransactionService patientTransactionService) {
        this.patientTransactionService = patientTransactionService;
    }


    @GetMapping
    public ResponseEntity<?> getPatientTransactionList(
            @RequestParam("patientId") Long patientId
    ) {
       return  ResponseEntity.ok(patientTransactionService.getPatientTransactionList(patientId));
    }
}
