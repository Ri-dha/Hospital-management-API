package com.azu.hospital.ultrasound.external.controller;

import com.azu.hospital.ultrasound.external.request.CreateExternalUltrasoundTestRequest;
import com.azu.hospital.ultrasound.external.services.ExternalUltrasoundTestGetService;
import com.azu.hospital.ultrasound.external.services.ExternalUltrasoundTestService;
import com.azu.hospital.ultrasound.external.services.ExternalUltrasoundTestStateService;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ultrasound/external")
@CrossOrigin

public class ExternalUltrasoundResultController {

    private final ExternalUltrasoundTestService externalUltrasoundTestService;
    private final ExternalUltrasoundTestStateService externalUltrasoundTestStateService;
    private final ExternalUltrasoundTestGetService externalUltrasoundTestGetService;


    public ExternalUltrasoundResultController(
            ExternalUltrasoundTestService externalUltrasoundTestService,
            ExternalUltrasoundTestStateService externalUltrasoundTestStateService,
            ExternalUltrasoundTestGetService externalUltrasoundTestGetService
    ) {
        this.externalUltrasoundTestService = externalUltrasoundTestService;
        this.externalUltrasoundTestStateService = externalUltrasoundTestStateService;
        this.externalUltrasoundTestGetService = externalUltrasoundTestGetService;
    }


    @GetMapping("get-all-tests")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<?> getAllTests(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size,
            @RequestParam List<UltrasoundOrderState> states,
            @RequestParam List<UltrasoundTypeEnum> types
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(externalUltrasoundTestGetService.getAllTests(types,states, search, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER', 'PERMANENT')")
    public ResponseEntity<?> getTestById(
            @PathVariable("id") Long id
    ) {

        return ResponseEntity.ok(externalUltrasoundTestGetService.getTestById(id));
    }

    @PostMapping("new-test")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER', 'PERMANENT')")
    public ResponseEntity<?> createNewUltrasoundTest(
            @Valid @ModelAttribute CreateExternalUltrasoundTestRequest request
    ) {
        return ResponseEntity.ok(externalUltrasoundTestService.createNewUltrasoundTest(request));
    }


    @PutMapping("accept-reject-test")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER', 'PERMANENT')")
    public void acceptRejectOrder(
            @RequestParam() Long testId,
            @RequestParam String status
    ) {
        externalUltrasoundTestStateService.acceptRejectOrder(testId, status);
    }


    @PutMapping("doctor-cancel-accept-test")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public void cancelAcceptDoctorOrder(
            @RequestParam() Long testId,
            @RequestParam String status
    ) {
        externalUltrasoundTestStateService.cancelAcceptDoctorOrder(testId, status);
    }

    @PutMapping("received-test")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public void receivedOrder(@RequestParam() Long testId,
                              @RequestParam String status
    ) {
        externalUltrasoundTestStateService.receivedOrder(testId, status);
    }

    @PutMapping("complete-test")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public void completeOrder(@RequestParam() Long testId,
                              @RequestParam String status
    ) {
        externalUltrasoundTestStateService.completeOrder(testId, status);
    }

}
