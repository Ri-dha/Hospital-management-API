package com.azu.hospital.Patients.charts.sedatedPatientAssessment.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.entities.SedatedPatientAssessment;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dao.SedatedPatientAssessmentDao;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDto;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.dto.SedatedPatientAssessmentDtoMapper;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.request.SedatedPatientAssessmentRequest;
import com.azu.hospital.Patients.charts.sedatedPatientAssessment.utils.SedatedPatientAssessmentRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.patient.ChartTypes;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service("SedatedPatientAssessmentService")
public class SedatedPatientAssessmentService {
    private final SedatedPatientAssessmentDao chartDao;
    private final AllPatientChartsDao allPatientChartsDao;
    private final PatientDao patientDao;
    private final SedatedPatientAssessmentDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    public SedatedPatientAssessmentService(
            @Qualifier("SedatedPatientAssessmentJpaDataAccess")
            SedatedPatientAssessmentDao chartDao,
            @Qualifier("AllPatientChartsJpaDataAccess")
            AllPatientChartsDao allPatientChartsDao,
            @Qualifier("patientRepo")
            PatientDao patientDao,
            SedatedPatientAssessmentDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
    ) {
        this.chartDao = chartDao;
        this.allPatientChartsDao = allPatientChartsDao;
        this.patientDao = patientDao;
        this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
    }

    public List<SedatedPatientAssessmentDto> getAllPatientCharts(Long patientId) {
        return chartDao.getAllCharts(patientId)
                .stream()
                .map(dtoMapper::chartToDto)
                .toList();
    }

    public SedatedPatientAssessmentDto getChartById(Long chartId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        SedatedPatientAssessment chart = chartDao
                .findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("SedatedPatientAssessmentChartNotFound")
                                        + " " + chartId
                        )
                );
        return dtoMapper.chartToDto(chart);
    }

    public IdResponse createNewChart(
            Long patientId,
            SedatedPatientAssessmentRequest request
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

        SedatedPatientAssessment chart = SedatedPatientAssessmentRequestToChart
                .requestToChart(request);


        chart.setPatient(patient);

        SedatedPatientAssessment savedChart = chartDao.createNewChart(chart);

        allCharts.setChartType(ChartTypes.SEDATED_PATIENT_ASSESSMENT);
        allPatientChartsDao.updateCharts(allCharts);

        return new DtoForReturnIdOnly(savedChart.getId());
    }
}
