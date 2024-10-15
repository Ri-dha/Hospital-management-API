package com.azu.hospital.lab_collection.external.ext_tests_request.controller;


import com.azu.hospital.lab_collection.external.ext_tests_request.request.TestRequestDataDataRequest;
import com.azu.hospital.lab_collection.external.ext_tests_request.services.ExtTestRequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Lab External Request" , description = "Lab External Request Routes")
@RestController
@RequestMapping("api/v1/lab/external-lab/tests")
@CrossOrigin

public class ExtTestRequestController {

    private final ExtTestRequestService extTestRequestService;

    @Autowired
    public ExtTestRequestController(ExtTestRequestService extTestRequestService) {
        this.extTestRequestService = extTestRequestService;
    }

    @GetMapping("get")
    public ResponseEntity<?> getTestsByExtTestId(@RequestParam Long extTestId) {
        return ResponseEntity.ok(extTestRequestService.getTestsByExtTestId(extTestId));
    }


    @PostMapping("add")
    public ResponseEntity<?> createTestForExtLabTest(
            @RequestParam Long extTestId,
            @Valid @RequestBody List<TestRequestDataDataRequest> test
    ) {
        return ResponseEntity.ok(extTestRequestService.createTestForExtLabTest(extTestId , test));
    }

}
