package com.azu.hospital.lab_collection.internal.int_tests_request.controller;

import com.azu.hospital.lab_collection.internal.int_tests_request.request.InternalTestRequestDataRequest;
import com.azu.hospital.lab_collection.internal.int_tests_request.services.IntTestRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/lab/internal-lab/tests")
@CrossOrigin
public class IntTestRequestController {

    private final IntTestRequestService intTestRequestService;

    @Autowired
    public IntTestRequestController(
            @Qualifier("intTestRequestService") IntTestRequestService intTestRequestService) {
        this.intTestRequestService = intTestRequestService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getTestsByIntTestId(@RequestParam Long intTestId){
       return ResponseEntity.ok(intTestRequestService.getTestsByIntTestId(intTestId));
    }



    @GetMapping("/get-all-by-internal-lab-test-id")
    public ResponseEntity<?> getAllByInternalLabTestId(@RequestParam Long internalLabTestId){
        return ResponseEntity.ok(intTestRequestService.getIntTestRequestsByInternalLabTestId(internalLabTestId));
    }

}
