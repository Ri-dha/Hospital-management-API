package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.controller;

import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.request.DrugRequestHandlerStatusRegistry;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.services.DrugRequestHandlerStatusServices;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.services.PreparingTheDrugRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/drugs-request-status")
@CrossOrigin
public class DrugRequestHandlerStatusController {


    private final DrugRequestHandlerStatusServices statusServices;
    private final PreparingTheDrugRequestService preparingTheDrugRequestService;


    @Autowired
    public DrugRequestHandlerStatusController(
            DrugRequestHandlerStatusServices statusServices,
            PreparingTheDrugRequestService preparingTheDrugRequestService) {

        this.statusServices = statusServices;
        this.preparingTheDrugRequestService = preparingTheDrugRequestService;
    }


    @PutMapping("/accept-reject-request")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT')")
    public ResponseEntity<?> acceptOrRejectRequest(
            @RequestParam("listId")Long listId,
            @RequestBody List<DrugRequestHandlerStatusRegistry> status
    ){
        return ResponseEntity.ok(statusServices.acceptOrRejectRequest(listId, status));
    }

    @PutMapping("/cancel-request")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'PERMANENT', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF')")
    public ResponseEntity<?> cancelRequest(@RequestParam("requestId")Long requestId,
                                                   @RequestParam("status") String status,
                                           @RequestParam(value = "quantity", required = false) Integer quantity){
        return ResponseEntity.ok(statusServices.cancelRequest(requestId, status, quantity));
    }

    @PutMapping("/received-or-not-received-request")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT')")
    public ResponseEntity<?> receivedOrNotReceivedTheItemRequest(@RequestParam("requestId")Long requestId,
                                                   @RequestParam("status") String status){
        return ResponseEntity.ok(statusServices.receivedOrNotReceivedTheItemRequest(requestId, status));
    }

    @PutMapping("/preparing-request")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT')")
    public ResponseEntity<?> preparingTheDrugRequest(@RequestParam("requestId")Long requestId,
                                                   @RequestParam("status") String status){
        return ResponseEntity.ok(preparingTheDrugRequestService.preparingTheDrugRequest(requestId, status));
    }
}
