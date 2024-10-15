package com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList;


import com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.dto.MedicinesRequestsListDto;
import com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.entity.MedicinesRequestsList;
import com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.service.MedicinesRequestsListService;
import com.azu.hospital.newPh.MedicinsRequests.requests.MedicinesRequestsCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/medicines-requests-list")
@CrossOrigin
public class MedicinesRequestsListController {
    private final MedicinesRequestsListService medicinesRequestsListService;


    @Autowired
    public MedicinesRequestsListController(MedicinesRequestsListService medicinesRequestsListService) {
        this.medicinesRequestsListService = medicinesRequestsListService;
    }


    @PostMapping("/add")
    public ResponseEntity<Void> addMedicinesRequestsList(
            @RequestParam Long patientId,
            @RequestBody List<MedicinesRequestsCreate> requestsCreates
    ) {
        medicinesRequestsListService.addMedicinesRequestsList(patientId, requestsCreates);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/get-all")
    public ResponseEntity<Page<MedicinesRequestsListDto>> getAllMedicinesRequestsList(
            @PageableDefault Pageable pageable
    ) {
        return ResponseEntity.ok(
                medicinesRequestsListService.getMedicinesRequestsListAccordingToUpdatedAt(pageable)
        );
    }

    @GetMapping("/get-all-by-patient")
    public ResponseEntity<?> getAllMedicinesRequestsListByPatient(
            @RequestParam Long patientId,
            @PageableDefault Pageable pageable
    ) {
        return ResponseEntity.ok(
                medicinesRequestsListService.getMedicinesRequestsListByPatientId(patientId, pageable)
        );
    }


    @GetMapping("/get-all-by-patient-name")
    public ResponseEntity<?> getAllMedicinesRequestsListByPatientName(
            @RequestParam String patientName,
            @PageableDefault Pageable pageable
    ) {
        return ResponseEntity.ok(
                medicinesRequestsListService.getMedicinesRequestsListAllWithFilter(patientName, pageable)
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MedicinesRequestsListDto> getMedicinesRequestsListById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(medicinesRequestsListService.getMedicinesRequestsListById(id));
    }

}
