package com.azu.hospital.Patients.entryTable;


import com.azu.hospital.Patients.entryTable.dto.EntryTableDateDto;
import com.azu.hospital.Patients.entryTable.service.EntryTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/entryTable")
@CrossOrigin
public class EntryTableController {

    private final EntryTableService entryTableService;

    @Autowired
    public EntryTableController(EntryTableService entryTableService) {
        this.entryTableService = entryTableService;
    }


    @GetMapping( "/all")
    public ResponseEntity<List<EntryTableDateDto>> getALlEntryTableByPatientId(@RequestParam Long patientId) {
        return ResponseEntity.ok(entryTableService.getALlEntryTableByPatientId(patientId));
    }






}
