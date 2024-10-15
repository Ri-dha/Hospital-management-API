package com.azu.hospital.Patients.charts.preAnestheticEvaluation;

import com.azu.hospital.Patients.charts.preAnestheticEvaluation.dto.PreAnestheticEvaluationChartDto;
import com.azu.hospital.Patients.charts.preAnestheticEvaluation.request.PreAnestheticEvaluationChartRequest;
import com.azu.hospital.Patients.charts.preAnestheticEvaluation.services.PreAnestheticEvaluationService;
import com.azu.hospital.Patients.charts.preAnestheticEvaluation.services.PreAnestheticEvaluationUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/anesthetic-evaluation-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class PreAnestheticEvaluationController {

    private final PreAnestheticEvaluationService anestheticEvaluationAddService;
    private final PreAnestheticEvaluationUpdateService preAnestheticEvaluationUpdateService;

    @Autowired
    public PreAnestheticEvaluationController(
            @Qualifier("AnestheticEvaluationAddService")
            PreAnestheticEvaluationService anestheticEvaluationAddService,
            @Qualifier("AnestheticEvaluationUpdateService")
            PreAnestheticEvaluationUpdateService preAnestheticEvaluationUpdateService
    ) {
        this.anestheticEvaluationAddService = anestheticEvaluationAddService;
        this.preAnestheticEvaluationUpdateService = preAnestheticEvaluationUpdateService;
    }

    @GetMapping
    public List<PreAnestheticEvaluationChartDto> getAllCharts(
            @RequestParam("patient_id") Long patientId
    ){
        return anestheticEvaluationAddService.getAllChartsByPatientId(patientId);
    }

    @GetMapping("/{chart_id}")
    public PreAnestheticEvaluationChartDto getChartById(
            @PathVariable("chart_id") Long chartId
    ){
        return anestheticEvaluationAddService.getChartById(chartId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createNewChart(
            @RequestParam("patient_id") Long patientId,
            @RequestBody PreAnestheticEvaluationChartRequest request
    ){
        return ResponseEntity.ok(anestheticEvaluationAddService.createNewChart(patientId, request));
    }

    @PutMapping("/{chart_id}")
    public ResponseEntity<?> updateExistsChart(
            @PathVariable("chart_id") Long chartId,
            @RequestBody PreAnestheticEvaluationChartRequest request
    ){
        return new ResponseEntity<>(
                preAnestheticEvaluationUpdateService.updateAnestheticChart(chartId, request),
                HttpStatus.OK
        );
    }
}
