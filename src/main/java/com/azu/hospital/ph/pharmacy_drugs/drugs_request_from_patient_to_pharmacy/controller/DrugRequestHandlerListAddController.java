package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.controller;

import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.request.DrugRequestHandlerRegistrationsRequest;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.dto.DrugRequestHandlerListDto;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.request.DrugRequestHandlerListUpdateRequest;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.services.DrugRequestHandlerListAddService;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.services.DrugRequestHandlerListGetService;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.services.DrugRequestHandlerListUpdateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/drugs-list")
@CrossOrigin
public class DrugRequestHandlerListAddController {
    private final DrugRequestHandlerListAddService addServices;
    private final DrugRequestHandlerListGetService getService;

    private final DrugRequestHandlerListUpdateService updateService;

    @Autowired
    public DrugRequestHandlerListAddController(DrugRequestHandlerListAddService addServices, DrugRequestHandlerListGetService getService, DrugRequestHandlerListUpdateService updateService) {
        this.addServices = addServices;
        this.getService = getService;
        this.updateService = updateService;
    }

    @PostMapping("/add")
//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'PERMANENT', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF')")
//    @Secured({"ROLE_HOSPITAL_MANAGER", "ROLE_DOCTOR", "ROLE_PERMANENT", "ROLE_SURGICAL_HEAD_CHIEF", "ROLE_INTERNAL_HEAD_CHIEF", "ROLE_ANESTHETIC_HEAD_CHIEF"})
    public ResponseEntity<Void> addNewDrugList(
            @Valid @RequestBody List<DrugRequestHandlerRegistrationsRequest> request,
            @RequestParam("patientId")Long patientId)
    {
        addServices.addDrugRequestList(patientId,request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{listId}")
//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'PERMANENT', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF')")
    public ResponseEntity<?> getDrugListById(
            @PathVariable("listId") Long listId
    ){
        return ResponseEntity.ok(getService.getDrugListById(listId));
    }

    @GetMapping("/all")
//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'PERMANENT', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF')")
    public ResponseEntity<Page<DrugRequestHandlerListDto>> getDrugRequestHandlerListAllWithFilter(
            @RequestParam(value = "patientName", required = false) String patientName, Pageable pageable) {
        Page<DrugRequestHandlerListDto> drugRequestHandlerListDtoPage = getService.getDrugRequestHandlerListAllWithFilter(patientName, pageable);
        return ResponseEntity.ok(drugRequestHandlerListDtoPage);
    }

    @GetMapping("/patient/{patientId}")
//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'PERMANENT', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF', 'NURSES')")
    public ResponseEntity<List<DrugRequestHandlerListDto>> getDrugRequestHandlerListByPatientId(
            @PathVariable("patientId") Long patientId) {

        return ResponseEntity.ok(getService.getDrugRequestHandlerListByPatientId(patientId));
    }

    @GetMapping("/get-according")
//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'PERMANENT', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF')")
    public ResponseEntity<Page<DrugRequestHandlerListDto>> getDrugRequestHandlerListAccordingToUpdatedAt(
            Pageable pageable) {
        Page<DrugRequestHandlerListDto> drugRequestHandlerListDtoPage = getService.getDrugRequestHandlerListAccordingToUpdatedAt(pageable);
        return ResponseEntity.ok(drugRequestHandlerListDtoPage);
    }

    @PutMapping("/update/{listId}")
//    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'PERMANENT', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF')")
    public ResponseEntity<Void> updateDrugRequestHandlerList(
            @PathVariable Long listId,
            @RequestBody DrugRequestHandlerListUpdateRequest request)  {
        updateService.updateDrugRequestHandlerList(listId, request);
        return ResponseEntity.ok().build();
    }



}
