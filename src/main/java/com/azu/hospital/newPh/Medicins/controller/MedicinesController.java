package com.azu.hospital.newPh.Medicins.controller;


import com.azu.hospital.newPh.Medicins.Dto.MedicinesDto;
import com.azu.hospital.newPh.Medicins.requests.MedicinesCreateRecord;
import com.azu.hospital.newPh.Medicins.service.MedicinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/medicines")
@CrossOrigin
public class MedicinesController {
    private final MedicinesService medicinesService;

    @Autowired
    public MedicinesController(MedicinesService medicinesService) {
        this.medicinesService = medicinesService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createMedicine(
            @ModelAttribute MedicinesCreateRecord request
    ) {
        medicinesService.createMedicine(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateMedicine(
            @ModelAttribute MedicinesCreateRecord request,
            @PathVariable Long id
    ) {
        medicinesService.updateMedicine(request, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMedicine(
            @PathVariable Long id
    ) {
        medicinesService.deleteMedicine(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MedicinesDto> getMedicineById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(medicinesService.getMedicineById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<MedicinesDto>> getAllMedicines(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(medicinesService.getAllMedicines(pageable));
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<List<MedicinesDto>> getByName(
            @RequestParam("name") String name,
            Pageable pageable
    ) {
        return ResponseEntity.ok(medicinesService.getByName(name, pageable));
    }

    @GetMapping("/get-by-filter")
    public ResponseEntity<Page<MedicinesDto>> getAllByFilter(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "barCode", required = false) String barCode,
            @RequestParam(value = "price", required = false) Long price,
            @RequestParam(value = "quantity", required = false) Long quantity,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(medicinesService.getAllByFilter(name, barCode, price, quantity, pageable));
    }




}
