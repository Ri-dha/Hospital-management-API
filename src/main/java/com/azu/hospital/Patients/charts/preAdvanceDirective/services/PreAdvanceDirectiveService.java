package com.azu.hospital.Patients.charts.preAdvanceDirective.services;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.preAdvanceDirective.dto.PreAdvanceDirectiveDto;
import com.azu.hospital.Patients.charts.preAdvanceDirective.dto.PreAdvanceDirectiveDtoMapper;
import com.azu.hospital.Patients.charts.preAdvanceDirective.entity.PreAdvanceDirectiveChart;
import com.azu.hospital.Patients.charts.preAdvanceDirective.dao.PreAdvanceDirectiveDao;
import com.azu.hospital.Patients.charts.preAdvanceDirective.request.PreAdvanceDirectiveRequest;
import com.azu.hospital.Patients.charts.preAdvanceDirective.utils.PreAdvanceDirectiveRequestToChart;
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

@Service("PreAdvanceDirectiveService")
public class PreAdvanceDirectiveService {
    private final PreAdvanceDirectiveDao chartDao;
    private final AllPatientChartsDao allPatientChartsDao;
    private final PatientDao patientDao;
    private final PreAdvanceDirectiveDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    public PreAdvanceDirectiveService(
            @Qualifier("PreAdvanceDirectiveJpaDataAccess")
            PreAdvanceDirectiveDao chartDao,
            @Qualifier("AllPatientChartsJpaDataAccess")
            AllPatientChartsDao allPatientChartsDao,
            @Qualifier("patientRepo")
            PatientDao patientDao,
            PreAdvanceDirectiveDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
    ) {
        this.chartDao = chartDao;
        this.allPatientChartsDao = allPatientChartsDao;
        this.patientDao = patientDao;
        this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
    }

    public List<PreAdvanceDirectiveDto> getAllPatientCharts(Long patientId) {
        return chartDao.getAllCharts(patientId)
                .stream()
                .map(dtoMapper::chartToDto)
                .toList();
    }

    public PreAdvanceDirectiveDto getChartById(Long chartId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        PreAdvanceDirectiveChart chart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("PreAdvanceDirectiveChartNotFound") + " " + chartId
                        )
                );
        return dtoMapper.chartToDto(chart);
    }

    public IdResponse createNewChart(
            Long patientId,
            PreAdvanceDirectiveRequest request
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

        PreAdvanceDirectiveChart chart = PreAdvanceDirectiveRequestToChart.requestToChart(request);

        chart.setPatient(patient);

        PreAdvanceDirectiveChart savedChart = chartDao.createNewChart(chart);

        allCharts.setChartType(ChartTypes.PRE_ADVANCE_DIRECTIVE_CHART);
        allPatientChartsDao.updateCharts(allCharts);

        return new DtoForReturnIdOnly(savedChart.getId());
    }
}
