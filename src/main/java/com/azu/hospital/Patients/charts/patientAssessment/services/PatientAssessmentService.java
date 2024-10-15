package com.azu.hospital.Patients.charts.patientAssessment.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.patientAssessment.dto.PatientAssessmentDto;
import com.azu.hospital.Patients.charts.patientAssessment.dto.PatientAssessmentDtoMapper;
import com.azu.hospital.Patients.charts.patientAssessment.request.PatientAssessmentChartRequest;
import com.azu.hospital.Patients.charts.patientAssessment.utils.PatientAssessmentRequestToChart;
import com.azu.hospital.Patients.charts.patientAssessment.entity.PatientAssessmentChart;
import com.azu.hospital.Patients.charts.patientAssessment.dao.PatientAssessmentChartDao;
import com.azu.hospital.Patients.charts.physical_assessment_enm.Fluid;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.patient.ChartTypes;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("PatientAssessmentService")
public class PatientAssessmentService {
    private final PatientAssessmentChartDao chartDao;
    private final AllPatientChartsDao allPatientChartsDao;
    private final PatientDao patientDao;
    private final PatientAssessmentDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    public PatientAssessmentService(
            @Qualifier("PatientAssessmentJpaDataAccess")
            PatientAssessmentChartDao chartDao,
            @Qualifier("AllPatientChartsJpaDataAccess")
            AllPatientChartsDao allPatientChartsDao,
            @Qualifier("patientRepo")
            PatientDao patientDao,
            PatientAssessmentDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
    ) {
        this.chartDao = chartDao;
        this.allPatientChartsDao = allPatientChartsDao;
        this.patientDao = patientDao;
        this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
    }

    public List<PatientAssessmentDto> getAllCharts(Long patientId) {
        return chartDao.getAllCharts(patientId)
                .stream()
                .map(dtoMapper::chartDto)
                .toList();
    }

    public PatientAssessmentDto getChartById(Long chartId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        PatientAssessmentChart chart = chartDao
                .findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("PatientAssessmentChartNotFound") + " " + chartId

                        )
                );
        return dtoMapper.chartDto(chart);
    }

    public Set<Map<String, String>> getFluidList() {
        Set<Map<String, String>> response = new LinkedHashSet<>();
        for (Fluid fluid : Fluid.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("key", fluid.name());
            map.put("value", fluid.getSymbol());
            response.add(map);
        }
        return response;
    }

    public IdResponse createNewChart(
            Long patientId,
            PatientAssessmentChartRequest request
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("patientNotFound") + " " + patientId
                        )
                );

        if (!patient.getIsHasMedicalHistory())
            throw new ResourceNotFoundException(
                    messages.getString("patientHasNoMedicalHistory") + " " + patientId
            );

        AllPatientChart allCharts = allPatientChartsDao.getAllPatientChartsByPatientId(patientId)
                .orElseGet(
                        () -> {
                            AllPatientChart c = new AllPatientChart();
                            c.setPatient(patient);
                            return allPatientChartsDao.addNewChart(c);
                        }
                );

        PatientAssessmentChart chart = PatientAssessmentRequestToChart.requestToChart(request);

        chart.setPatient(patient);

        PatientAssessmentChart newChart = chartDao.createNewChart(chart);

        allCharts.setChartType(ChartTypes.PATIENT_ASSESSMENT_CHART);
        allPatientChartsDao.updateCharts(allCharts);

        return new DtoForReturnIdOnly(newChart.getId());
    }
}
