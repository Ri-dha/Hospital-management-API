package com.azu.hospital.ultrasound.ultrasoundBill.controller;


import com.azu.hospital.ultrasound.ultrasoundBill.requests.UltrasoundBillCreateRequest;
import com.azu.hospital.ultrasound.ultrasoundBill.requests.UltrasoundUpdateRequest;
import com.azu.hospital.ultrasound.ultrasoundBill.service.UltrasoundBillInjection;
import com.azu.hospital.ultrasound.ultrasoundBill.service.UltrasoundBillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ultrasound/bill")
@CrossOrigin
//@PreAuthorize("hasAnyRole('ACCOUNTANT', 'HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER')")
public class UltrasoundBillController {

    private final UltrasoundBillService ultrasoundBillService;
    private final UltrasoundBillInjection LoadUltrasoundBill;


    @Autowired
    public UltrasoundBillController(
            @Qualifier("ultrasoundBillService") UltrasoundBillService ultrasoundBillService,
            @Qualifier("ultrasoundBillInjection") UltrasoundBillInjection loadUltrasoundBill) {
        this.ultrasoundBillService = ultrasoundBillService;
        this.LoadUltrasoundBill = loadUltrasoundBill;
    }


    @GetMapping("")
    public ResponseEntity<?> getAllBill() {
        return ResponseEntity.ok(ultrasoundBillService.getAllUltrasoundBill());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBillById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(ultrasoundBillService.getUltrasoundBillById(id));
    }

    @GetMapping("/get-by-type/{type}")
    public ResponseEntity<?> getBillByType(
            @PathVariable String type
    ) {
        return ResponseEntity.ok(ultrasoundBillService.getBillByType(type));
    }


    @PostMapping("")
    public ResponseEntity<Void> createBill(

            @Valid @RequestBody UltrasoundBillCreateRequest ultrasoundCreateRequest
    ) {
        ultrasoundBillService.createUltrasoundBill(ultrasoundCreateRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBill(
            @PathVariable Long id,
            @Valid @ModelAttribute UltrasoundUpdateRequest ultrasoundUpdateRequest
    ) {
        ultrasoundBillService.updateUltraSoundBill(id, ultrasoundUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/load")
    public ResponseEntity<?> loadBill() {
        try {
            LoadUltrasoundBill.LoadUltrasoundBill();
            return ResponseEntity.ok("Data loaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while loading data: " + e.getMessage());
        }
    }

}
