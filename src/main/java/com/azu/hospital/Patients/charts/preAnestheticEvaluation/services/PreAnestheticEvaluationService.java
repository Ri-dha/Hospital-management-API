package com.azu.hospital.Patients.charts.preAnestheticEvaluation.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.preAnestheticEvaluation.entity.PreAnestheticEvaluationChart;
import com.azu.hospital.Patients.charts.preAnestheticEvaluation.dao.PreAnestheticEvaluationChartDao;
import com.azu.hospital.Patients.charts.preAnestheticEvaluation.dto.PreAnestheticEvaluationChartDto;
import com.azu.hospital.Patients.charts.preAnestheticEvaluation.dto.PreAnestheticEvaluationChartDtoMapper;
import com.azu.hospital.Patients.charts.preAnestheticEvaluation.request.PreAnestheticEvaluationChartRequest;
import com.azu.hospital.Patients.charts.preAnestheticEvaluation.utils.PreAnestheticEvaluationRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.patient.ChartTypes;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


@Service("AnestheticEvaluationAddService")
public class PreAnestheticEvaluationService {
    private final PreAnestheticEvaluationChartDao chartDao;
    private final AllPatientChartsDao allPatientChartsDao;
    private final PatientDao patientDao;
    private final PreAnestheticEvaluationChartDtoMapper preAnestheticEvaluationChartDtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public PreAnestheticEvaluationService(
            @Qualifier("AnestheticEvaluationChartDAO")
            PreAnestheticEvaluationChartDao chartDao,
            @Qualifier("AllPatientChartsJpaDataAccess")
            AllPatientChartsDao allPatientChartsDao,
            @Qualifier("patientRepo")
            PatientDao patientDao,
            PreAnestheticEvaluationChartDtoMapper preAnestheticEvaluationChartDtoMapper, ExceptionsMessageReturn messageReturn
    ) {
        this.chartDao = chartDao;
        this.allPatientChartsDao = allPatientChartsDao;
        this.patientDao = patientDao;
        this.preAnestheticEvaluationChartDtoMapper = preAnestheticEvaluationChartDtoMapper;
        this.messageReturn = messageReturn;
    }

    public List<PreAnestheticEvaluationChartDto> getAllChartsByPatientId(Long patientId){
        return chartDao.getAllCharts(patientId)
                .stream()
                .map(preAnestheticEvaluationChartDtoMapper::anestheticEvaluationChartToDTO)
                .toList();
    }

    public PreAnestheticEvaluationChartDto getChartById(Long chartId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        PreAnestheticEvaluationChart chart = chartDao
                .findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("PreAnestheticEvaluationChartNotFound") + " " + chartId

                        )
                );

      return preAnestheticEvaluationChartDtoMapper
              .anestheticEvaluationChartToDTO(chart);
    }

    public IdResponse createNewChart(
            Long patientId,
            PreAnestheticEvaluationChartRequest request
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
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

        PreAnestheticEvaluationChart preAnestheticEvaluationChart = PreAnestheticEvaluationRequestToChart.getAnestheticEvaluationChart(request);

        preAnestheticEvaluationChart.setPatient(patient);

        PreAnestheticEvaluationChart newPreAnestheticEvaluationChart = chartDao
                .createNewChart(preAnestheticEvaluationChart);

        allCharts.setChartType(ChartTypes.PRE_ANESTHETIC_EVALUATION_CHART);
        allPatientChartsDao.updateCharts(allCharts);

        return new DtoForReturnIdOnly(newPreAnestheticEvaluationChart.getId());
    }
}

