package com.azu.hospital.radiology.radiology_bill_handler.controller;


import com.azu.hospital.radiology.radiology_bill_handler.requests.RadiologyCreateRequest;
import com.azu.hospital.radiology.radiology_bill_handler.requests.RadiologyUpdateRequest;
import com.azu.hospital.radiology.radiology_bill_handler.services.InjectionRadiologyBill;
import com.azu.hospital.radiology.radiology_bill_handler.services.RadiologyBillService;
import jakarta.validation.Valid;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/radiology/bill")
@CrossOrigin
//@PreAuthorize("hasAnyRole('ACCOUNTANT', 'HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER')")

public class RadiologyBillController {

    private final RadiologyBillService radiologyBillService;
    private final InjectionRadiologyBill injectionRadiologyBill;

    @Autowired
    public RadiologyBillController(
            @Qualifier("radiologyBillService") RadiologyBillService radiologyBillService, InjectionRadiologyBill injectionRadiologyBill) {
        this.radiologyBillService = radiologyBillService;
        this.injectionRadiologyBill = injectionRadiologyBill;
    }


    @GetMapping("")
    public ResponseEntity<?> getAllBill() {
        return ResponseEntity.ok(radiologyBillService.getAllRadiologyBill());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBillById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(radiologyBillService.getRadiologyBillById(id));
    }


    @GetMapping("/get-by-type/{type}")
    public ResponseEntity<?> getBillByType(
            @PathVariable String type
    ) {
        return ResponseEntity.ok(radiologyBillService.getRadiologyBillByType(type));
    }

    @PostMapping("")
    public ResponseEntity<?> createBill(
            @Valid @RequestBody RadiologyCreateRequest radiologyCreateRequest
    ) {
        return ResponseEntity.ok(radiologyBillService.createRadiologyBill(radiologyCreateRequest));
    }

    @PutMapping("")
    public ResponseEntity<?> updateBill(
            @RequestParam(name = "id") Long id,
            @RequestBody RadiologyUpdateRequest radiologyCreateRequest
    ) {
        return ResponseEntity.ok(radiologyBillService.updateRadiologyBill(id, radiologyCreateRequest));
    }


    @PostMapping("/load")
    public ResponseEntity<?> loadBill() {
        try {
            injectionRadiologyBill.LoadRadiologyBill();
            return ResponseEntity.ok("Data loaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while loading data: " + e.getMessage());
        }


    }

    @GetMapping("/get-all-with-price")
    public ResponseEntity<?> getAllWithPrice(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size
    ) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(radiologyBillService.getAllWithPrice(pageable));
    }

}
