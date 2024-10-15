package com.azu.hospital.ph.lab_inventory.lab_main_table.controller;

import com.azu.hospital.ph.lab_inventory.lab_main_table.request.MainLabTubeStoreRegistrationRequest;
import com.azu.hospital.ph.lab_inventory.lab_main_table.services.MainLabTubeStoreAddService;
import com.azu.hospital.ph.lab_inventory.lab_main_table.services.MainLabTubeStoreService;
import com.azu.hospital.ph.lab_inventory.lab_main_table.services.MainLabTubeStoreUpdateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

// TODO: 1/25/2024 more care
@Tag(name = "Inventory Lab Tube Store" , description = "Inventory Lab Tube Store Routes")
@RestController
@RequestMapping("api/v1/main-lab-tube")
@CrossOrigin
public class MainLabTubeStoreController {

    private final MainLabTubeStoreService mainLabTubeStoreService;
    private final MainLabTubeStoreUpdateService updateService;
    private final MainLabTubeStoreAddService addService;

    @Autowired
    public MainLabTubeStoreController(MainLabTubeStoreService mainLabTubeStoreService, MainLabTubeStoreUpdateService updateService, MainLabTubeStoreAddService addService) {
        this.mainLabTubeStoreService = mainLabTubeStoreService;
        this.updateService = updateService;
        this.addService = addService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'INVENTORY_CLERK')")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTube(@Valid @RequestParam(name = "id") Long billId,
                        @Valid @ModelAttribute MainLabTubeStoreRegistrationRequest request) throws IOException {
        addService.addNewLabTube(billId,request);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'INVENTORY_CLERK')")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateTube(@RequestParam(name = "id") Long id, @ModelAttribute MainLabTubeStoreRegistrationRequest update) throws IOException {
        updateService.updateMainLabTubeStore(id, update);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'INVENTORY_CLERK')")
    public ResponseEntity<?>getTubeById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(mainLabTubeStoreService.getMainTubeById(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'INVENTORY_CLERK')")
    public ResponseEntity<?>getAll(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(mainLabTubeStoreService.getAllTube(pageable));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'INVENTORY_CLERK')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(name = "id") Long id){
        mainLabTubeStoreService.deleteById(id);
    }
    // TODO: 11/4/2023 missing add by barcode

}
