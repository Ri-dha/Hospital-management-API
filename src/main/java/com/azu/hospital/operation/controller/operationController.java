package com.azu.hospital.operation.controller;

import com.azu.hospital.Validator.DateValidator.DatePattern;
import com.azu.hospital.operation.request.CreateOperationRequest;
import com.azu.hospital.operation.service.OperationAddService;
import com.azu.hospital.operation.service.OperationGetService;
import com.azu.hospital.operation.service.OperationStateService;
import com.azu.hospital.utils.Dtos.StatusDto;
import com.azu.hospital.utils.enums.operation.OperationStates;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/v1/operations")
@Validated
@CrossOrigin
public class operationController {


    private final OperationAddService operationAddService;
    private final OperationGetService operationGetService;
    private final OperationStateService operationStateService;

    public operationController(
            OperationAddService operationAddService,
            OperationGetService operationGetService, OperationStateService operationStateService) {
        this.operationAddService = operationAddService;
        this.operationGetService = operationGetService;
        this.operationStateService = operationStateService;
    }


    @PostMapping("/new")
    public void createNewOperation(
            @RequestParam("patientId") Long patientId ,
            @Valid @RequestBody CreateOperationRequest request

            ){
        operationAddService.createNewOperation(patientId , request );
    }
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER','DOCTOR', 'PERMANENT')")
    @PutMapping("waiting-reject-operation")
    public StatusDto<OperationStates> acceptRejectOrOperation(
            @RequestParam() Long operationId,
            @RequestParam String state
    ) {
        return operationStateService.acceptOrRejectOrder(operationId, state);
    }


    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER','DOCTOR', 'PERMANENT')")
    @PutMapping("doctor-cancel-operation")
    public StatusDto<OperationStates> cancelDoctorOperation(
            @RequestParam() Long operationId,
            @RequestParam String state
    ) {
        return operationStateService.cancelOrder(operationId, state);
    }
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER','DOCTOR', 'PERMANENT')")
    @PutMapping("in-operation")
    public StatusDto<OperationStates> inOperationOrder(
            @RequestParam() Long operationId,
            @RequestParam String state
    ) {
        return operationStateService.inOperationDoctorOrder(operationId, state);
    }

    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER','DOCTOR', 'PERMANENT')")
    @PutMapping("before-recover")
    public StatusDto<OperationStates> beforeRecover(
            @RequestParam() Long operationId,
            @RequestParam String state
    ) {
        return operationStateService.beforeRecovery(operationId, state);
    }

    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'PERMANENT')")
    @PutMapping("recovery")
    public StatusDto<OperationStates> recoveryOperation(
            @RequestParam() Long operationId,
            @RequestParam String state
    ) {
        return operationStateService.recoveryOrder(operationId, state);
    }
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER','DOCTOR', 'PERMANENT')")
    @PutMapping("sent-ward")
    public StatusDto<OperationStates> sentToWardOperation(
            @RequestParam() Long operationId,
            @RequestParam String state,
            @RequestParam String note
    ) {
        return operationStateService.sentToWardOrder(operationId, state, note);
    }
//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER','DOCTOR', 'PERMANENT')")
//    @PutMapping("waiting-ward")
//    public StatusDto<OperationStates> waitingForWardOperation(
//            @RequestParam() Long operationId,
//            @RequestParam String state
//    ) {
//        return operationStateService.waitingForWardOrder(operationId, state);
//    }
//
//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER','DOCTOR', 'PERMANENT')")
//    @PutMapping("in-ward")
//    public StatusDto<OperationStates> inWardOperation(
//            @RequestParam() Long operationId,
//            @RequestParam String state
//    ) {
//        return operationStateService.inWardOrder(operationId, state);
//    }
//
//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER','DOCTOR', 'PERMANENT')")
//    @PutMapping("patient-received")
//    public StatusDto<OperationStates> PatientReceivedOperation(
//            @RequestParam() Long operationId,
//            @RequestParam String state
//    ) {
//        return operationStateService.PatientReceivedOrder(operationId, state);
//    }

    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER','DOCTOR', 'PERMANENT', 'ANESTHETIC_HEAD_CHIEF', 'ANESTHESIOLOGIST', " +
            "'NURSES', 'NURSE_MANAGER', 'NURSE_PRACTITIONER', 'NURSE_AIDE', 'NURSE_CLINICAL_EDUCATOR', " +
            "'NURSE_CONSULTANT')")
    @GetMapping("/get-by-patient-id")
    public ResponseEntity<?> getByPatientId(
            @RequestParam("patientId") Long patientId,
            @PageableDefault Pageable pageable

            ){
       return ResponseEntity.ok(operationGetService.getOperationsByPatientId(patientId , pageable ));
    }

    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER','DOCTOR', 'PERMANENT', 'ANESTHETIC_HEAD_CHIEF', 'ANESTHESIOLOGIST', " +
            "'NURSES', 'NURSE_MANAGER', 'NURSE_PRACTITIONER', 'NURSE_AIDE', 'NURSE_CLINICAL_EDUCATOR', " +
            "'NURSE_CONSULTANT')")
    @GetMapping("/all")
    public ResponseEntity<?> getAll(
            @RequestParam("date") @DatePattern String date,
            @RequestParam(required = false) List<OperationStates> states,
            @PageableDefault Pageable pageable

            ){
       return ResponseEntity.ok(operationGetService.getOperationsByDate(date , states , pageable ));
    }

    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER','DOCTOR', 'PERMANENT', 'ANESTHETIC_HEAD_CHIEF', 'ANESTHESIOLOGIST', " +
            "'NURSES', 'NURSE_MANAGER', 'NURSE_PRACTITIONER', 'NURSE_AIDE', 'NURSE_CLINICAL_EDUCATOR', " +
            "'NURSE_CONSULTANT')")
    @GetMapping("/all-by-state")
    public ResponseEntity<?> getAllByState(
            @RequestParam(required = false) List<OperationStates> states,
            @PageableDefault(size = 10, page = 0) Pageable pageable

            ){
       return ResponseEntity.ok(operationGetService.getAllOperationsByState(states , pageable ));
    }

    @GetMapping("/all-patient-who-has-operation-and-state-is-before-in-operation")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER','DOCTOR', 'PERMANENT', 'ANESTHETIC_HEAD_CHIEF', 'ANESTHESIOLOGIST', " +
            "'NURSES', 'NURSE_MANAGER', 'NURSE_PRACTITIONER', 'NURSE_AIDE', 'NURSE_CLINICAL_EDUCATOR', " +
            "'NURSE_CONSULTANT')")
    public ResponseEntity<?> getAllPatientWhoHasOperationAndStateIsBeforeInOperation(
            @RequestParam(required = false) List<OperationStates> states,
            @PageableDefault(size = 10, page = 0) Pageable pageable

            ){
       return ResponseEntity.ok(operationGetService.getAllPatientWhoHasOperationAndStateIsBeforeInOperation(states , pageable ));
    }


}
