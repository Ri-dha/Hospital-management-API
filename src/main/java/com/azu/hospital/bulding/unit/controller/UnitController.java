package com.azu.hospital.bulding.unit.controller;

import com.azu.hospital.bulding.unit.request.CreateUnitRequest;
import com.azu.hospital.bulding.unit.request.UpdateUnitRequest;
import com.azu.hospital.bulding.unit.services.UnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/department/units")
@CrossOrigin

public class UnitController {

    private final UnitService unitServices;

    @Autowired
    public UnitController(UnitService unitServices) {
        this.unitServices = unitServices;
    }

    @PostMapping("add")
    public ResponseEntity<Void> createNewUnit(@Valid @ModelAttribute CreateUnitRequest request) {
        unitServices.createNewUnit(request);
        return ResponseEntity.ok().build();
    }

    ;


    @PutMapping("update/{id}")
    public ResponseEntity<?> updateUnit(@Valid @ModelAttribute UpdateUnitRequest request, @PathVariable Long id) {
        unitServices.updateUnit(request, id);
        return ResponseEntity.ok("");
    }


    @GetMapping("get-by-dep-id")
    public ResponseEntity<?> getUnitByDepId(
            @RequestParam Long depId
                                  ) {
        return ResponseEntity.ok(unitServices.getAllUnitByDepId(depId));
    }


    @GetMapping("get-by-floor-id")
    public ResponseEntity<?> getUnitByFloorId(@RequestParam Long floorId) {
        return ResponseEntity.ok(unitServices.getAllUnitByFloorId(floorId));
    }

    @GetMapping("get-manager")
    public ResponseEntity<?> getManagerByUnitId(
            @RequestParam Long unitId) {
        return ResponseEntity.ok(unitServices.getManagerByUnitId(unitId));
    }

    @GetMapping("get-manager-assistance")
    public ResponseEntity<?> getManagerAssistanceByUnitId(
            @RequestParam Long unitId) {
        return ResponseEntity.ok(unitServices.getAssistanceByUnitId(unitId));
    }
}
