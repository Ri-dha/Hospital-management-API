package com.azu.hospital.accounting.patient_wallet.controller;

import com.azu.hospital.accounting.patient_wallet.dto.PatientWalletDto;
import com.azu.hospital.accounting.patient_wallet.request.PatientWalletRequest;
import com.azu.hospital.accounting.patient_wallet.services.IPatientWalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/patient-wallet")
public class PatientWalletController {


    private final IPatientWalletService patientWalletService;

    public PatientWalletController(IPatientWalletService patientWalletService) {
        this.patientWalletService = patientWalletService;
    }

    @PostMapping("/addBalance")
    public void addBalance(
            @Valid @RequestBody PatientWalletRequest request
    ) {
        patientWalletService.addBalance(request);
    }


    @GetMapping("/balance")
    public ResponseEntity<?> getPatientWalletById(
            @RequestParam("patientId") Long patientId
    ) {
        PatientWalletDto patientWalletDto = patientWalletService.getPatientWallet(patientId);
        return ResponseEntity.ok(patientWalletDto);
    }
}
