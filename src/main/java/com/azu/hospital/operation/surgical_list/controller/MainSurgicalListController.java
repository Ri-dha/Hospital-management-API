package com.azu.hospital.operation.surgical_list.controller;

import com.azu.hospital.operation.surgical_list.dto.MainSurgicalListDto;
import com.azu.hospital.operation.surgical_list.injection_services_syrgical_list.SurgicalListDataService;
import com.azu.hospital.operation.surgical_list.services.MainSurgicalListAddServices;
import com.azu.hospital.operation.surgical_list.services.MainSurgicalListGetServices;
import com.azu.hospital.operation.surgical_list.services.MainSurgicalListUpdateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/surgical-list")
@CrossOrigin

public class MainSurgicalListController {

    private final MainSurgicalListGetServices getServices;
    private final MainSurgicalListAddServices addServices;
    private final MainSurgicalListUpdateServices updateServices;
    private final SurgicalListDataService surgicalListDataService;

    @Autowired
    public MainSurgicalListController(
            MainSurgicalListGetServices getServices,
            MainSurgicalListAddServices addServices,
            MainSurgicalListUpdateServices updateServices,
            SurgicalListDataService surgicalListDataService) {
        this.getServices = getServices;
        this.addServices = addServices;
        this.updateServices = updateServices;
        this.surgicalListDataService = surgicalListDataService;
    }

    @PostMapping
    public ResponseEntity<Void> createSurgicalList(@RequestParam("name") String name){
        addServices.addSurgicalName(name);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/id")
    public ResponseEntity<Void> createSurgicalList(@RequestParam("id") Long id, @RequestParam("name") String name){
        updateServices.updateSurgicalNameList(id, name);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<MainSurgicalListDto> getListById(@PathVariable("id") Long id){
        return ResponseEntity.ok(getServices.getSurgicalNameById(id));
    }

    @GetMapping("/price")
    public ResponseEntity<Page<MainSurgicalListDto>> getListById(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getServices.getAllWithPrice(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<MainSurgicalListDto>> getListById(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getServices.getAllWithFilter(name, pageable));
    }


    @PostMapping("/loadData")
    public ResponseEntity<String> loadData() {
        try {
            surgicalListDataService.loadSurgicalData();
            return ResponseEntity.ok("Data loaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while loading data: " + e.getMessage());
        }
    }

}
