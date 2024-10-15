package com.azu.hospital.Patients.charts.operativeNote.controller;


import com.azu.hospital.Patients.charts.operativeNote.dto.OperativeNoteDto;
import com.azu.hospital.Patients.charts.operativeNote.request.OperativeNoteRequest;
import com.azu.hospital.Patients.charts.operativeNote.service.OperativeNoteService;
import com.azu.hospital.Patients.charts.operativeNote.service.OperativeNoteUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operative-note")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class OperativeNoteController {
    private final OperativeNoteService operativeNoteService;

    private final OperativeNoteUpdateService operativeNoteUpdateService;

    public OperativeNoteController(OperativeNoteService operativeNoteService, OperativeNoteUpdateService operativeNoteUpdateService) {
        this.operativeNoteService = operativeNoteService;
        this.operativeNoteUpdateService = operativeNoteUpdateService;
    }

    @PostMapping
    public ResponseEntity<?> addOperativeNote(
            @RequestParam("patientId") Long patientId,
            @RequestBody OperativeNoteRequest request
    ){
        return new ResponseEntity<>(
                operativeNoteService.addOperativeNoteChart(patientId,request),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{chart_id}")
    public OperativeNoteDto getOperativeNoteById(
            @PathVariable("chart_id") Long chartId
    ){
        return operativeNoteService.getChartById(chartId);

    }

    @GetMapping("/all")
    public List<OperativeNoteDto> getAllCharts(
            @RequestParam("patientId") Long patientId
    ){
        return operativeNoteService.getAllChart(patientId);
    }


    @PutMapping("update")
    public ResponseEntity<?> updateOperativeNote(
            @RequestParam("chartId") Long chatId,
            @RequestBody OperativeNoteRequest request
    ){
        return new ResponseEntity<>(
                operativeNoteUpdateService.updateOperativeNoteChart(chatId,request),
                HttpStatus.OK
        );
    }

}
