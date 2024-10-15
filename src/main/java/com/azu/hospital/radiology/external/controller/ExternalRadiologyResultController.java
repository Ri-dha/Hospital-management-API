package com.azu.hospital.radiology.external.controller;

import com.azu.hospital.radiology.external.request.CreateExternalRadiologyTestRequest;
import com.azu.hospital.radiology.external.services.ExternalRadiologyTestGetService;
import com.azu.hospital.radiology.external.services.ExternalRadiologyTestService;
import com.azu.hospital.radiology.external.services.ExternalRadiologyTestStateService;
import com.azu.hospital.utils.Dtos.StatusDto;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/radiology/external")
@CrossOrigin
public class ExternalRadiologyResultController {

    private final ExternalRadiologyTestService externalRadiologyTestService;
    private final ExternalRadiologyTestStateService externalRadiologyTestStateService;
    private final ExternalRadiologyTestGetService externalRadiologyTestGetService;


    public ExternalRadiologyResultController(
            ExternalRadiologyTestService externalRadiologyTestService,
            ExternalRadiologyTestStateService externalRadiologyTestStateService,
            ExternalRadiologyTestGetService externalRadiologyTestGetService) {
        this.externalRadiologyTestService = externalRadiologyTestService;
        this.externalRadiologyTestStateService = externalRadiologyTestStateService;
        this.externalRadiologyTestGetService = externalRadiologyTestGetService;
    }


    @GetMapping("/get-all-tests")
    @PreAuthorize("hasAnyRole('RADIOLOGIST', 'HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<?> getAllTests(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size,
            @RequestParam List<RadiologyOrderState> states,
            @RequestParam List<RadiologyTypeEnum> types
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(externalRadiologyTestGetService.getAllTests(types, states, search, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('RADIOLOGIST', 'HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<?> getTestById(
            @PathVariable("id") Long id
    ) {

        return ResponseEntity.ok(externalRadiologyTestGetService.getById(id));
    }


    @PostMapping("/new-test")
    @PreAuthorize("hasAnyRole('RADIOLOGIST', 'HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<DtoForReturnIdOnly> createNewRadiologyTest(
            @Valid @RequestBody CreateExternalRadiologyTestRequest request
    ) {
        return ResponseEntity.ok(externalRadiologyTestService.createNewRadiologyTest(request));
    }


    @PutMapping("/accept-reject-test")
    @PreAuthorize("hasAnyRole('RADIOLOGIST', 'HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public StatusDto<RadiologyOrderState> acceptRejectOrder(
            @RequestParam() Long testId,
            @RequestParam("state") String state
    ) {
        return externalRadiologyTestStateService.acceptRejectOrder(testId,state);
    }


    @PutMapping("/complete-test")
    @PreAuthorize("hasAnyRole('RADIOLOGIST', 'HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public StatusDto<RadiologyOrderState> completeOrder(
            @RequestParam() Long testId,
            @RequestParam("state") String state
            ) {
       return externalRadiologyTestStateService.completeOrder(testId,state);
    }

}
