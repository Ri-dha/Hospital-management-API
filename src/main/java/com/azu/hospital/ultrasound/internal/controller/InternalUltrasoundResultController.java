package com.azu.hospital.ultrasound.internal.controller;

import com.azu.hospital.ultrasound.internal.request.CreateInternalUltrasoundTestRequest;
import com.azu.hospital.ultrasound.internal.services.InternalUltrasoundTestGetService;
import com.azu.hospital.ultrasound.internal.services.InternalUltrasoundTestService;
import com.azu.hospital.ultrasound.internal.services.InternalUltrasoundTestStateService;
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
@RequestMapping("api/v1/ultrasound/internal")
@CrossOrigin
public class InternalUltrasoundResultController {

    private final InternalUltrasoundTestService internalUltrasoundTestService;
    private final InternalUltrasoundTestGetService internalUltrasoundTestGetService;
    private final InternalUltrasoundTestStateService internalUltrasoundTestStateService;


    public InternalUltrasoundResultController(
            InternalUltrasoundTestService internalUltrasoundTestService,
            InternalUltrasoundTestGetService internalUltrasoundTestGetService,
            InternalUltrasoundTestStateService internalUltrasoundTestStateService) {
        this.internalUltrasoundTestService = internalUltrasoundTestService;
        this.internalUltrasoundTestGetService = internalUltrasoundTestGetService;
        this.internalUltrasoundTestStateService = internalUltrasoundTestStateService;
    }


    @GetMapping("get-all-tests")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<?> getAllTests(
            @RequestParam(required = false) List<UltrasoundTypeEnum> types,
            @RequestParam(required = false) List<UltrasoundOrderState> state,
            @RequestParam(defaultValue = "", required = false) String search,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(internalUltrasoundTestGetService.getAllTests(types,  state, search, pageable));
    }

    @GetMapping("get-by-patient")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<?> getByPatient(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) List<UltrasoundOrderState> state,
            @RequestParam(defaultValue = "15") Integer size,
            @RequestParam Long patientId
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(internalUltrasoundTestGetService.getAllByPatientId(patientId,state,pageable));
    }


    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public ResponseEntity<?> getTestById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(internalUltrasoundTestGetService.getById(id));
    }

    @PostMapping("new-test")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER', 'PERMANENT')")
    public ResponseEntity<?> createNewUltrasoundTest(
            @Valid @ModelAttribute CreateInternalUltrasoundTestRequest request
    ) {
        return ResponseEntity.ok(internalUltrasoundTestService.createNewUltrasoundTest(request));
    }

    @PutMapping("accept-reject-test")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public void acceptRejectOrder(
            @RequestParam() Long testId,
            @RequestParam String state
    ) {
        internalUltrasoundTestStateService.acceptRejectOrder(testId, state);
    }

    @PutMapping("doctor-cancel-accept-test")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public void cancelAcceptDoctorOrder(
            @RequestParam() Long testId,
            @RequestParam String state
    ) {
        internalUltrasoundTestStateService.cancelAcceptDoctorOrder(testId, state);
    }

    @PutMapping("received-test")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public void receivedOrder(@RequestParam() Long testId) {
        internalUltrasoundTestStateService.receivedOrder(testId);
    }

    @PutMapping("complete-test")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'HOSPITAL_ASSISTANCE_MANAGER')")
    public void completeOrder(@RequestParam() Long testId) {
        internalUltrasoundTestStateService.completeOrder(testId);
    }

}
