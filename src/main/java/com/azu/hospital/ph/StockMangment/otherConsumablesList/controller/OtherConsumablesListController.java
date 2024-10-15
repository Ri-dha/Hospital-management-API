package com.azu.hospital.ph.StockMangment.otherConsumablesList.controller;


import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.request.OtherConsumablesCreateRequest;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.request.OtherConsumablesListUpdateRequest;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.service.OtherConsumablesListGetService;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.service.OtherConsumablesListService;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.service.OtherConsumablesListUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/otherConsumablesList")
@RestController
@CrossOrigin
public class OtherConsumablesListController {
    private final OtherConsumablesListService service;
    private final OtherConsumablesListUpdateService updateService;
    private final OtherConsumablesListGetService getService;


    public OtherConsumablesListController(OtherConsumablesListService service, OtherConsumablesListUpdateService updateService, OtherConsumablesListGetService getService) {
        this.service = service;
        this.updateService = updateService;
        this.getService = getService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addOtherConsumablesList(
            @RequestParam("patientId") Long patientId,
            @RequestBody List<OtherConsumablesCreateRequest> request

    ){
        service.createOtherConsumablesList(patientId,request);
        return  ResponseEntity.ok().build();
    }

    @GetMapping("/{listId}")
    public ResponseEntity<?> getOtherConsumablesList(
            @PathVariable("listId") Long listId
    ){
        return ResponseEntity.ok(getService.getOtherConsumablesList(listId));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOtherConsumablesListByPatientId(
            @RequestParam("patientId") Long patientId
    ){
        return ResponseEntity.ok(getService.getAllOtherConsumablesListByPatientId(patientId));
    }

    @PutMapping("/{listId}")
    public ResponseEntity<Void> updateOtherConsumablesList(
            @PathVariable("listId") Long listId,
            @RequestBody OtherConsumablesListUpdateRequest request
    ){
        updateService.updateOtherConsumablesListUpdate(listId,request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-to-list")
public ResponseEntity<Void> addOtherConsumablesToList(
            @RequestParam("listId") Long listId,
            @RequestBody List<OtherConsumablesCreateRequest> request
    ){
        service.addOtherConsumablesToList(listId,request);
        return ResponseEntity.ok().build();
    }


}
