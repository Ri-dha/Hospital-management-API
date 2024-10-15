package com.azu.hospital.lab_collection.external.ext_test_result.controller;

import com.azu.hospital.lab_collection.external.ext_test_result.request.CreateTestResultRequest;
import com.azu.hospital.lab_collection.external.ext_test_result.request.TestResultDataRequest;
import com.azu.hospital.lab_collection.external.ext_test_result.request.TestResultDataRequestList;
import com.azu.hospital.lab_collection.external.ext_test_result.services.ExtTestResultService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Lab External Result" , description = "Lab External Result Routes")
@RestController
@RequestMapping("api/v1/lab/external-lab/result")
@CrossOrigin

public class ExtTestResultController {

    private final ExtTestResultService extTestRequestService;

    @Autowired
    public ExtTestResultController(ExtTestResultService extTestRequestService) {
        this.extTestRequestService = extTestRequestService;
    }

    @GetMapping("get")
    public ResponseEntity<?> getTestResultByTestId(@RequestParam Long testId) {
        return ResponseEntity.ok(extTestRequestService.getTestResultByTestId(testId));
    }

    @PostMapping("add")
    public ResponseEntity<?> createResultForTest(
            @Valid @RequestBody List<TestResultDataRequestList> results
    ) {
        return ResponseEntity.ok(extTestRequestService.createResultForTest(results));
    }


}
