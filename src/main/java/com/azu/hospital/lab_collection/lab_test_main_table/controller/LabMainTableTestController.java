package com.azu.hospital.lab_collection.lab_test_main_table.controller;

import com.azu.hospital.lab_collection.lab_test_main_table.dto.LabTestMainTableDto;
import com.azu.hospital.lab_collection.lab_test_main_table.inject_services.DataInitializationService;
import com.azu.hospital.lab_collection.lab_test_main_table.services.LabTestMainTableAddServices;
import com.azu.hospital.lab_collection.lab_test_main_table.services.LabTestMainTableGetServices;
import com.azu.hospital.lab_collection.lab_test_main_table.services.LabTestMainTableUpdateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/lab-test-main-table")
@CrossOrigin

public class LabMainTableTestController {

    private final LabTestMainTableAddServices addServices;

    private final LabTestMainTableGetServices getServices;

    private final LabTestMainTableUpdateServices updateServices;
    private final DataInitializationService dataInitializationService;

    @Autowired
    public LabMainTableTestController(LabTestMainTableAddServices addServices,
                                      LabTestMainTableGetServices getServices,
                                      LabTestMainTableUpdateServices updateServices, DataInitializationService dataInitializationService) {
        this.addServices = addServices;
        this.getServices = getServices;
        this.updateServices = updateServices;
        this.dataInitializationService = dataInitializationService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addTestToManiTable(@RequestParam("testName") String testName){
        addServices.addLabTestMainTable(testName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{testId}")
    public ResponseEntity<LabTestMainTableDto> getLabTestMainTableById(@PathVariable Long testId){
        return ResponseEntity.ok(getServices.getMainLabTestById(testId));
    }

    @GetMapping("/get-all-test-names")
    public ResponseEntity<Page<LabTestMainTableDto>>getAllTest(
            @RequestParam(value = "testName", required = false) String testName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size

    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getServices.getAllMainLabTest(testName, pageable));
    }


    @PutMapping("/update-test-name")
    public ResponseEntity<Void> updateExistTestName(
            @RequestParam("testId") Long testId,
            @RequestParam("testName") String testName
    ){
        updateServices.updateExistMainLabTestName(testId, testName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/load-data")
    public ResponseEntity<String> loadData()  {
        try {
            dataInitializationService.loadLabData();
            return ResponseEntity.ok("Data loaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Error while loading data"+e.getMessage());
        }

    }

    @GetMapping("/get-all-with-price")
    public ResponseEntity<Page<LabTestMainTableDto>> getAllWithPrice(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getServices.getAllMainLabTest(pageable));
    }


}
