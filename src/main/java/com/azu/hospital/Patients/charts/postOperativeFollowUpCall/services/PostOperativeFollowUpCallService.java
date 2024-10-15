package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dao.PatientExperiencingDao;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PatientExperiencing;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PostOperativeFollowUpCall;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dao.PostOperativeFollowUpCallDao;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dto.PostOperativeFollowUpCallDto;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dto.PostOperativeFollowUpCallDtoMapper;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.request.PostOperativeFollowUpCallChartRequest;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.utils.PostOperativeFollowUpCallRequestToChart;
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

@Service("PostOperativeFollowUpCallService")
public class PostOperativeFollowUpCallService {

    private final PostOperativeFollowUpCallDao chartDao;
    private final AllPatientChartsDao allPatientChartsDao;
    private final PatientDao patientDao;
    private final PatientExperiencingDao patientExperiencingDao;
    private final ExceptionsMessageReturn messageReturn;

    private final PostOperativeFollowUpCallDtoMapper dtoMapper;

    public PostOperativeFollowUpCallService(
            @Qualifier("PostOperativeFollowUpCallJpaDataAccess")
            PostOperativeFollowUpCallDao chartDao,
            @Qualifier("AllPatientChartsJpaDataAccess")
            AllPatientChartsDao allPatientChartsDao,
            @Qualifier("patientRepo")
            PatientDao patientDao,
            PatientExperiencingDao patientExperiencingDao, ExceptionsMessageReturn messageReturn, PostOperativeFollowUpCallDtoMapper dtoMapper) {
        this.chartDao = chartDao;
        this.allPatientChartsDao = allPatientChartsDao;
        this.patientDao = patientDao;
        this.patientExperiencingDao = patientExperiencingDao;
        this.messageReturn = messageReturn;
        this.dtoMapper = dtoMapper;
    }

    public List<PostOperativeFollowUpCallDto> getAllPatientCharts(Long patientId) {
        return chartDao.getAllCharts(patientId)
                .stream()
                .map(dtoMapper::chartToDto)
                .toList();
    }

    public PostOperativeFollowUpCallDto getChartById(Long chartId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        PostOperativeFollowUpCall chart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("PostOperativeFollowUpCallChartNotFound") + " " + chartId
                        )
                );
        return dtoMapper.chartToDto(chart);
    }

    public IdResponse createNewChart(
            Long patientId,
            PostOperativeFollowUpCallChartRequest request
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("patientNotFound") + " " + patientId)
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

        PostOperativeFollowUpCall chart = PostOperativeFollowUpCallRequestToChart.requestToChart(request);

        PostOperativeFollowUpCall finalPost = chart;
        request.patientExperiencing()
                .stream()
                .map((d) -> {
                    PatientExperiencing patientExperiencing = new PatientExperiencing();
                    patientExperiencing.setExperience(d.getExperience());
                    patientExperiencing.setNote(d.getNote());
                    patientExperiencing.setIsChecked(d.getIsChecked());
                    patientExperiencingDao.createNewExperiencingChart(List.of(patientExperiencing));
                    patientExperiencing.setPostOperativeFollowUpCall(finalPost);
                    return patientExperiencing;


                }).toList();

        chart.setPatient(patient);

        PostOperativeFollowUpCall savedChart = chartDao.createNewChart(chart);

        allCharts.setChartType(ChartTypes.POST_OPERATIVE_FOLLOW_UP_CALL);

        allPatientChartsDao.updateCharts(allCharts);

        return new DtoForReturnIdOnly(savedChart.getId());
    }

}
