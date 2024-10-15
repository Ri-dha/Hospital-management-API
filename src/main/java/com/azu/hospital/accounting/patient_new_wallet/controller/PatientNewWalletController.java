package com.azu.hospital.accounting.patient_new_wallet.controller;


import com.azu.hospital.accounting.patient_new_wallet.request.PatientNewWalletRegistrationsRequest;
import com.azu.hospital.accounting.patient_new_wallet.request.PatientNewWalletUpdateRequest;
import com.azu.hospital.accounting.patient_new_wallet.services.PatientNewWalletAddServices;
import com.azu.hospital.accounting.patient_new_wallet.services.PatientNewWalletGetServices;
import com.azu.hospital.accounting.patient_new_wallet.services.PatientNewWalletUpdateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/patient_new_wallet")
@RestController
@CrossOrigin
public class PatientNewWalletController {

    private final PatientNewWalletGetServices getServices;
    private final PatientNewWalletUpdateServices updateServices;
    private final PatientNewWalletAddServices addServices;

    @Autowired
    public PatientNewWalletController(PatientNewWalletGetServices getServices, PatientNewWalletUpdateServices updateServices, PatientNewWalletAddServices addServices) {
        this.getServices = getServices;
        this.updateServices = updateServices;
        this.addServices = addServices;
    }


    @PostMapping("/add")
    public ResponseEntity<?> addNewPatientNewWallet(
            @RequestBody PatientNewWalletRegistrationsRequest request){
        addServices.insertPatientNewWallet(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientNewWalletById(@PathVariable Long id){
        return ResponseEntity.ok(getServices.getPatientNewWalletById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPatientNewWallet(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getServices.getAllPatientNewWallet(pageable));
    }

    @GetMapping("/count")
    public ResponseEntity<?> totalPatientNewWalletCount(){
        return ResponseEntity.ok(getServices.totalPatientNewWalletCount());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePatientNewWallet(
            @RequestParam Long id,
            @RequestBody PatientNewWalletUpdateRequest request){
        updateServices.updatePatientNewWallet(id,request);
        return ResponseEntity.ok().build();
    }



}
