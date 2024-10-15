package com.azu.hospital.accounting.insurance.controller;


import com.azu.hospital.accounting.insurance.dto.InsuranceDto;
import com.azu.hospital.accounting.insurance.request.InsuranceRequest;
import com.azu.hospital.accounting.insurance.service.InsuranceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/insurance")
@CrossOrigin
public class InsuranceController {
    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createInsurance(
            @RequestParam Long patientId,
            @RequestBody InsuranceRequest request){
        insuranceService.createInsurance(patientId,request);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/get")

    public ResponseEntity<InsuranceDto> getInsuranceById(Long patientId){
        return ResponseEntity.ok( insuranceService.getInsuranceById(patientId));
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateInsurance(
            @RequestParam Long patientId,
            @RequestBody InsuranceRequest request){
        insuranceService.updateInsurance(patientId,request);
        return ResponseEntity.ok().build();
    }
}
