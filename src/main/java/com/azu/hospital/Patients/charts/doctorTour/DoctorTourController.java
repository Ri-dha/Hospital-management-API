package com.azu.hospital.Patients.charts.doctorTour;


import com.azu.hospital.Patients.charts.doctorTour.dto.DoctorTourDto;
import com.azu.hospital.Patients.charts.doctorTour.request.DoctorTourRequest;
import com.azu.hospital.Patients.charts.doctorTour.services.DoctorTourService;
import com.azu.hospital.Patients.charts.doctorTour.services.DoctorTourUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/doctor-tour")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT'  " +
        " , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class DoctorTourController {
    private final DoctorTourService service;
    private final DoctorTourUpdateService updateService;

    @Autowired
    public DoctorTourController(DoctorTourService service, DoctorTourUpdateService updateService) {
        this.service = service;
        this.updateService = updateService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<DoctorTourDto> getAllCharts(
            @RequestParam("patient_id") Long patientId
    ) {
        return service.getAllChartByPatientId(patientId);
    }

    @GetMapping("/{chart_id}")
    @ResponseStatus(code = HttpStatus.OK)
    public DoctorTourDto getChartById(
            @PathVariable("chart_id") Long chartId
    ) {
        return service.getChartById(chartId);
    }

    @PostMapping
    public ResponseEntity<?> createNewChart(
            @RequestParam("patient_id") Long patientId,
            @RequestBody DoctorTourRequest request
    ) {
        return new ResponseEntity<>(service.createNewChart(patientId, request), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateExistsChart(
            @RequestParam("chart_id") Long chartId,
            @RequestParam("patient_id") Long patientId,
            @RequestBody DoctorTourRequest request
    ) {
        return ResponseEntity.ok(updateService.updateExistsChart(chartId, patientId, request));
    }
}