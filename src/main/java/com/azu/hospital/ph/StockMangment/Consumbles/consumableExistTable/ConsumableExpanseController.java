package com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable;

import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.dto.ConsumableExpanseDto;
import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.services.ConsumableExpanseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consumables-expanse")
@CrossOrigin
public class ConsumableExpanseController {

    private final ConsumableExpanseServices consumableExpanseServices;

    @Autowired
    public ConsumableExpanseController(ConsumableExpanseServices consumableExpanseServices) {
        this.consumableExpanseServices = consumableExpanseServices;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<?> updateConsumableExpanse(@PathVariable Long id,
                                                     @RequestParam(required = false) Integer quantity,
                                                     @RequestParam(required = false) String place) {

            consumableExpanseServices.updateConsumableExpanse(id, quantity, place);
            return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<ConsumableExpanseDto> getExpanseById(@PathVariable Long id) {
        ConsumableExpanseDto expanseDto = consumableExpanseServices.getExpanseById(id);
        if (expanseDto != null) {
            return ResponseEntity.ok(expanseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public ResponseEntity<Page<ConsumableExpanseDto>> getAllConsumables(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String barcode,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "0") int page
                                                                        ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ConsumableExpanseDto> consumables = consumableExpanseServices.getAllConsumables(name, barcode, pageable);
        return ResponseEntity.ok(consumables);
    }
}

