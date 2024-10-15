package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.controller;

import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dto.DrugRequestHandlerDto;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.request.DrugRequestHandlerRegistrationsRequest;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.request.DrugRequestHandlerUpdateRequest;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.services.DrugRequestHandlerAddServices;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.services.DrugRequestHandlerServices;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.services.DrugRequestHandlerUpdateServices;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/drugs-list/drugs-request")
@CrossOrigin
public class DrugRequestHandlerController {

    private final DrugRequestHandlerAddServices addServices;
    private final DrugRequestHandlerUpdateServices updateServices;
    private final DrugRequestHandlerServices services;


    @Autowired
    public DrugRequestHandlerController(DrugRequestHandlerAddServices addServices, DrugRequestHandlerUpdateServices updateServices, DrugRequestHandlerServices services) {
        this.addServices = addServices;
        this.updateServices = updateServices;
        this.services = services;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'PERMANENT', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> addNewRequest(
            @RequestParam Long listId,
            @Valid @RequestBody List<DrugRequestHandlerRegistrationsRequest> registrationsRequest){
        addServices.addRequestHandler(listId,registrationsRequest);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'PERMANENT', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF')")
    public ResponseEntity<?> updateDrugRequest(
            @RequestParam("drugId") Long drugId,
            @RequestBody DrugRequestHandlerUpdateRequest request
            ){
        updateServices.updateDrugRequestHandler(drugId,request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{requestId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'PERMANENT', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF')")
    public ResponseEntity<?> getRequestById(@PathVariable("requestId")Long id){
        return ResponseEntity.ok(services.getDrugRequestById(id));
    }

    @GetMapping("/request-status")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'PERMANENT', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF')")
    public ResponseEntity<?> getByStatus(
            @RequestParam(value = "status", defaultValue = "") UnitInventoryRequestEnum status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortField", defaultValue = "createAt") String sortField,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
    ){
        Page<DrugRequestHandlerDto> pages = services.getRequestByStatus(status, page, size, sortField, sortDir);
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @GetMapping("/all-request")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'DOCTOR', 'PERMANENT', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', 'ANESTHETIC_HEAD_CHIEF')")
    public ResponseEntity<?> getAllRequestBy(
            @RequestParam(value = "status", defaultValue = "") UnitInventoryRequestEnum status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "userId", defaultValue = "") Long userId,
            @RequestParam(value = "drugId", defaultValue = "") Long drugId
    ){
        Pageable pageable = PageRequest.of( page, size);
        Page<DrugRequestHandlerDto> pages = services.getAllRequestBy(userId, drugId,status,pageable);
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }
}
