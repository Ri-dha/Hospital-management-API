package com.azu.hospital.radiology.internal.controller;

import com.azu.hospital.radiology.internal.dto.InternalRadiologyTestDto;
import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.radiology.internal.request.CreateInternalRadiologyTestRequest;
import com.azu.hospital.radiology.internal.services.InternalRadiologyTestGetService;
import com.azu.hospital.radiology.internal.services.InternalRadiologyTestService;
import com.azu.hospital.radiology.internal.services.InternalRadiologyTestStateService;
import com.azu.hospital.utils.Dtos.StatusDto;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/radiology/internal")
@CrossOrigin
//@PreAuthorize("hasAnyRole('RADIOLOGIST', 'HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER')")

public class InternalRadiologyResultController {

    private final InternalRadiologyTestService internalRadiologyTestService;
    private final InternalRadiologyTestStateService internalRadiologyTestStateService;
    private final InternalRadiologyTestGetService internalRadiologyTestGetService;


    public InternalRadiologyResultController(
            InternalRadiologyTestService internalRadiologyTestService,
            InternalRadiologyTestStateService internalRadiologyTestStateService,
            InternalRadiologyTestGetService internalRadiologyTestGetService) {
        this.internalRadiologyTestService = internalRadiologyTestService;
        this.internalRadiologyTestStateService = internalRadiologyTestStateService;
        this.internalRadiologyTestGetService = internalRadiologyTestGetService;
    }


    @GetMapping("/get-all-tests")
    public ResponseEntity<?> getAllTests(
            @RequestParam(defaultValue = "") String search,
            @PageableDefault Pageable pageable,
            @RequestParam(required = false) List<RadiologyOrderState> states,
            @RequestParam(required = false) List<RadiologyTypeEnum> types
    ) {
        return ResponseEntity.ok(internalRadiologyTestGetService.getAllTests(types, states, search, pageable));
    }

    @GetMapping("/get-by-patient-id")
    public ResponseEntity <List<InternalRadiologyTestDto>> getAllByPatientId(
            @RequestParam("patientId") Long patientId,
            @RequestParam(required = false) List<RadiologyOrderState> state)
    {
        return ResponseEntity.ok(internalRadiologyTestGetService.getAllByPatientIdAndState(patientId,state));
    }

    @GetMapping("/get-by-patient")
    public ResponseEntity<?> getByPatient(
            @PageableDefault Pageable pageable,
            @RequestParam List<RadiologyOrderState> state,
            @RequestParam List<RadiologyTypeEnum> types,
            @RequestParam Long patientId
    ) {
        return ResponseEntity.ok(internalRadiologyTestGetService.getInternalRadiologyByPatient(patientId, types,state,pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTestById(
            @PathVariable("id") Long id
    ) {

        return ResponseEntity.ok(internalRadiologyTestGetService.getById(id));
    }

    @PostMapping("/new-test")
    public ResponseEntity<?> createNewRadiologyTest(
            @Valid @RequestBody CreateInternalRadiologyTestRequest request
    ) {
        return ResponseEntity.ok(internalRadiologyTestService.createNewRadiologyTest(request));
    }

    @PutMapping("accept-reject-test")
    public StatusDto<RadiologyOrderState> acceptRejectOrder(
            @RequestParam() Long testId,
            @RequestParam String state
    ) {
        return internalRadiologyTestStateService.acceptRejectOrder(testId, state);
    }



}
