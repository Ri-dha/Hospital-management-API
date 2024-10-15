package com.azu.hospital.ecg.internal.ecgInternalResults.controllers;


import com.azu.hospital.ecg.internal.ecgInternalResults.Services.InternalEcgResultService;
import com.azu.hospital.ecg.internal.ecgInternalResults.request.CreateInternalEcgResultRequest;
import com.azu.hospital.ecg.internal.ecgInternalResults.request.UpdateInternalEcgResultRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/ecg/internal/result")
@CrossOrigin
public class InternalEcgResultController {

    private final InternalEcgResultService internalEcgResultService;

    @Autowired
    public InternalEcgResultController(@Qualifier("internalEcgResultService") InternalEcgResultService internalEcgResultService) {
        this.internalEcgResultService = internalEcgResultService;
    }

    @PostMapping("")
    public ResponseEntity<?> createResult(
            @Valid @ModelAttribute CreateInternalEcgResultRequest request
            )throws IOException {
        return ResponseEntity.ok(internalEcgResultService.createResult(request));
    }


    @GetMapping("")
    public ResponseEntity<?> getByTestId(
                @RequestParam Long testId
            ){
        return ResponseEntity.ok(internalEcgResultService.getResultByTestId(testId));
    }


    @PutMapping("")
    public ResponseEntity<?> updateResult(
            @RequestParam Long testId,
            @Valid @ModelAttribute UpdateInternalEcgResultRequest request
    )throws IOException {

        return ResponseEntity.ok(internalEcgResultService.updateResult(testId,request));
    }









}
