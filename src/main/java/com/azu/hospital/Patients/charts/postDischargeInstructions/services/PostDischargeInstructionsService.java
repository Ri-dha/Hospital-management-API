package com.azu.hospital.Patients.charts.postDischargeInstructions.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.postDischargeInstructions.dto.PostDischargeInstructionsDto;
import com.azu.hospital.Patients.charts.postDischargeInstructions.dto.PostDischargeInstructionsDtoMapper;
import com.azu.hospital.Patients.charts.postDischargeInstructions.entity.PostDischargeInstructionsChart;
import com.azu.hospital.Patients.charts.postDischargeInstructions.dao.PostDischargeInstructionsDao;
import com.azu.hospital.Patients.charts.postDischargeInstructions.request.PostDischargeInstructionRequest;
import com.azu.hospital.Patients.charts.postDischargeInstructions.utils.PostDischargeInstructionRequestToChart;
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

@Service("PostDischargeInstructionsService")
public class PostDischargeInstructionsService {
    private final PostDischargeInstructionsDao chartDao;
    private final AllPatientChartsDao allPatientChartsDao;
    private final PatientDao patientDao;
    private final PostDischargeInstructionsDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    public PostDischargeInstructionsService(
            @Qualifier("PostDischargeInstructionsJpaDataAccess")
            PostDischargeInstructionsDao chartDao,
            @Qualifier("AllPatientChartsJpaDataAccess")
            AllPatientChartsDao allPatientChartsDao,
            @Qualifier("patientRepo")
            PatientDao patientDao,
            PostDischargeInstructionsDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
    ) {
        this.chartDao = chartDao;
        this.allPatientChartsDao = allPatientChartsDao;
        this.patientDao = patientDao;
        this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
    }

    public PostDischargeInstructionsDto getChartById(Long chartId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        PostDischargeInstructionsChart chart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("PostDischargeInstructionsChartNotFound") + " " + chartId
                        )
                );

        return dtoMapper.chartToDto(chart);
    }

    public List<PostDischargeInstructionsDto> getAllPatientCharts(
            Long patientId
    ) {
        return chartDao.getAllCharts(patientId)
                .stream()
                .map(dtoMapper::chartToDto)
                .toList();
    }

    public IdResponse createNewChart(
            Long patientId,
            PostDischargeInstructionRequest request
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

        PostDischargeInstructionsChart chart = PostDischargeInstructionRequestToChart.requestToChart(request);

        chart.setPatient(patient);

        PostDischargeInstructionsChart savedChart = chartDao.createNewChart(chart);

        allCharts.setChartType(ChartTypes.POST_DISCHARGE_INSTRUCTIONS_CHART);
        allPatientChartsDao.updateCharts(allCharts);

        return new DtoForReturnIdOnly(savedChart.getId());
    }


}
