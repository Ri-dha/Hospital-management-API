package com.azu.hospital.lab_collection.internal.int_test_result.controller;

import com.azu.hospital.lab_collection.internal.int_test_result.dto.IntTestResultDto;
import com.azu.hospital.lab_collection.internal.int_test_result.request.InternalTestDataRequestList;
import com.azu.hospital.lab_collection.internal.int_test_result.request.InternalTestResultDataRequest;
import com.azu.hospital.lab_collection.internal.int_test_result.services.IntTestResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/lab/internal-lab/result")
@CrossOrigin
public class IntTestResultController {

    private final IntTestResultService intTestRequestService;

    @Autowired
    public IntTestResultController(IntTestResultService intTestRequestService) {
        this.intTestRequestService = intTestRequestService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getTestResultByTestId(@RequestParam Long testId){
        return ResponseEntity.ok(intTestRequestService.getTestResultByTestId(testId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> createResultForTest(
            @Valid @RequestBody List<InternalTestDataRequestList> results
    ) throws IOException {
        return ResponseEntity.ok(intTestRequestService.createResultForTest(results));
    }

    @GetMapping("/get-all-test-results-by-patient-id")
    public ResponseEntity<List<IntTestResultDto>> getAllResultsByPatientId(@RequestParam("patientId") Long patientId){
        return ResponseEntity.ok(intTestRequestService.getTestResultByPatientId(patientId));
    }


    @PostMapping("/add-file")
    public ResponseEntity<?> addFileToTestResult(
            @ModelAttribute List<MultipartFile> files
    ) throws IOException {
        return ResponseEntity.ok(intTestRequestService.uploadMultiFile(files));
    }

}
