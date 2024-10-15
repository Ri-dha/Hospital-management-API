package com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.controller;


import com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.dto.BabyDoctorTourDto;
import com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.request.BabyDoctorTourCreateRequest;
import com.azu.hospital.Patients.charts.pedaitric_charts.doctor_tour.service.BabyDoctorTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/charts/pediatric-charts/baby-doctor-tour")
@CrossOrigin
public class BabyDoctorTourController {

    private final BabyDoctorTourService service;


    @Autowired
    public BabyDoctorTourController(BabyDoctorTourService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<BabyDoctorTourDto> createNewChart(
            @RequestParam Long prematureBabyId,
            @ModelAttribute BabyDoctorTourCreateRequest request) {
        return ResponseEntity.ok(service.createNewChart(prematureBabyId,request));
    }


    @GetMapping("/get-all")
    public ResponseEntity<Page<BabyDoctorTourDto>> getAllCharts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getAllCharts(pageable));
    }

    @GetMapping("/get-all-by-patient-id")
    public ResponseEntity<Page<BabyDoctorTourDto>> getAllChartsByPatientId(
            @RequestParam(value = "prematureBabyId") Long prematureBabyId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getAllChartByPatientId(prematureBabyId, pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BabyDoctorTourDto> getChartById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getChartById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<BabyDoctorTourDto> updateChart(
            @RequestParam Long chartId,
            @RequestParam Long prematureBabyId,
            @ModelAttribute BabyDoctorTourCreateRequest chart
    ) {
        return ResponseEntity.ok(service.updateExistsChart(chartId,prematureBabyId,chart));
    }


}
