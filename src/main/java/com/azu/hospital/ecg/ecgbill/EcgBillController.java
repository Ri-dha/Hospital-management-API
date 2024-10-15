package com.azu.hospital.ecg.ecgbill;


import com.azu.hospital.ecg.ecgbill.request.EcgBillCreateRequest;
import com.azu.hospital.ecg.ecgbill.request.EcgUpdateRequest;
import com.azu.hospital.ecg.ecgbill.service.EcgBillService;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ecg-bill")
@CrossOrigin
public class EcgBillController {

    private final EcgBillService ecgBillService;

    @Autowired
    public EcgBillController(@Qualifier("ecgBillService") EcgBillService ecgBillService) {
        this.ecgBillService = ecgBillService;
    }

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ACCOUNTANT', 'HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<?> getAllBill() {
        return ResponseEntity.ok(ecgBillService.getAllEcgBill());
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyRole('ACCOUNTANT', 'HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<?> getBillById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(ecgBillService.getEcgBillById(id));
    }



    @PostMapping("")
    @PreAuthorize("hasAnyRole('ACCOUNTANT', 'HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<Void> createBill(
            @RequestBody EcgBillCreateRequest ecgBillCreateRequest
    ){
        ecgBillService.createEcgBill(ecgBillCreateRequest);
        return ResponseEntity.ok().build();
    }


    @PutMapping
    @PreAuthorize("hasAnyRole('ACCOUNTANT', 'HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<Void> updateBill(
            @RequestParam(name = "id") Long id,
            @RequestBody EcgUpdateRequest request
    ){
        ecgBillService.updateEcgBill(id,request);
        return ResponseEntity.ok().build();
    }

}
