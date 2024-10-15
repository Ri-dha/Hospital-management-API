package com.azu.hospital.ph.StockMangment.AddPharmacy;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pharmacy")
@CrossOrigin

public class PharmacyController {
    private final PharmacyServices pharmacyServices;

    public PharmacyController(PharmacyServices pharmacyServices) {
        this.pharmacyServices = pharmacyServices;
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<List<Pharmacy>> getAllPharmacyList() {
        List<Pharmacy> pharmacyList = pharmacyServices.getPharmacy();
        return ResponseEntity.ok(pharmacyList);
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<?> addPharmacy(
            @RequestBody Pharmacy pharmacy) {
        pharmacyServices.addPharmacy(pharmacy);
        boolean status = true;
        return ResponseEntity.ok().body("{\"status\": " + status + "}");
    }

    @PutMapping("/{pharmacyId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<String> updatePharmacy(
            @PathVariable Integer pharmacyId,
            @RequestBody Pharmacy pharmacy) {
        pharmacyServices.updatePharmacy(pharmacyId, pharmacy);
        boolean status = true;
        return ResponseEntity.ok().body("{\"status\": " + status + "}");
    }
    @DeleteMapping("/{pharmacyId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<String> deletePharmacy(@PathVariable Integer pharmacyId){
        pharmacyServices.deletePharmacy(pharmacyId);
        boolean status = true;
        return ResponseEntity.ok().body("{\"status\": " + status + "}");
    }
}
