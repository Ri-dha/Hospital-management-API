package com.azu.hospital.Patients.charts.postAnestheticEvaluation.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.postAnestheticEvaluation.dto.PostAnestheticEvaluationDto;
import com.azu.hospital.Patients.charts.postAnestheticEvaluation.dto.PostAnestheticEvaluationDtoMapper;
import com.azu.hospital.Patients.charts.postAnestheticEvaluation.entity.PostAnestheticEvaluationChart;
import com.azu.hospital.Patients.charts.postAnestheticEvaluation.dao.PostAnestheticEvaluationChartDao;
import com.azu.hospital.Patients.charts.postAnestheticEvaluation.request.PostAnestheticEvaluationRequest;
import com.azu.hospital.Patients.charts.postAnestheticEvaluation.utils.PostAnestheticEvaluationRequestToChart;
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

@Service("PostAnestheticEvaluationService")
public class PostAnestheticEvaluationService {
    private final PostAnestheticEvaluationChartDao chartDao;
    private final AllPatientChartsDao allPatientChartsDao;
    private final PatientDao patientDao;
    private final PostAnestheticEvaluationDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public PostAnestheticEvaluationService(
            @Qualifier("PostAnesthChartJpa") PostAnestheticEvaluationChartDao chartDao,
            @Qualifier("AllPatientChartsJpaDataAccess") AllPatientChartsDao allPatientChartsDao,
            @Qualifier("patientRepo") PatientDao patientDao,
            @Qualifier("postAnestheticEvaluationDtoMapper") PostAnestheticEvaluationDtoMapper dtoMapper,
            @Qualifier("exceptionsMessageReturn") ExceptionsMessageReturn messageReturn
    ) {
        this.chartDao = chartDao;
        this.allPatientChartsDao = allPatientChartsDao;
        this.patientDao = patientDao;
        this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
    }

    public PostAnestheticEvaluationDto getChartById(Long chartId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        PostAnestheticEvaluationChart chart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("PostAnestheticEvaluationChartNotFound") + " " + chartId
                        )
                );
        return dtoMapper.anestheticEvaluationChartToDto(chart);
    }

    public IdResponse createNewChart(
            Long patientId,
            PostAnestheticEvaluationRequest request
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

        PostAnestheticEvaluationChart chart = PostAnestheticEvaluationRequestToChart.getAnestheticEvaluationChart(request);

        chart.setPatient(patient);

        PostAnestheticEvaluationChart saved = chartDao.createNewChart(chart);

        allCharts.setChartType(ChartTypes.POST_ANESTHETIC_EVALUATION_CHART);
        allPatientChartsDao.updateCharts(allCharts);

        return new DtoForReturnIdOnly(saved.getId());
    }

    public List<PostAnestheticEvaluationDto> getAllChartsByPatientId(Long patientId) {
        return chartDao
                .getAllPostAnestheticEvaluationChartByPatientId(patientId)
                .stream()
                .map(dtoMapper::anestheticEvaluationChartToDto)
                .toList();
    }
}
