package com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.dao.MedsPerMdDao;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.entities.MedsPerMDEntity;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.entities.NonSedatedPatientAssessment;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.dao.NonSedatedPatientAssessmentDao;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.dto.NonSedatedPatientAssessmentDto;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.dto.NonSedatedPatientAssessmentDtoMapper;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.request.NonSedatedPatientAssessmentRequest;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.utils.NonSedatedPatientAssessmentRequestToChart;
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
import java.util.Set;

@Service("NonSedatedPatientAssessmentService")
public class NonSedatedPatientAssessmentService {
    private final NonSedatedPatientAssessmentDtoMapper dtoMapper;
    private final NonSedatedPatientAssessmentDao chartDao;
    private final AllPatientChartsDao allPatientChartsDao;
    private final PatientDao patientDao;
    private final MedsPerMdDao medsDao;
    private final ExceptionsMessageReturn messageReturn;

    public NonSedatedPatientAssessmentService(
            NonSedatedPatientAssessmentDtoMapper dtoMapper,
            @Qualifier("NonSedatedPatientAssessmentJpaDataAccess")
            NonSedatedPatientAssessmentDao chartDao,
            @Qualifier("AllPatientChartsJpaDataAccess")
            AllPatientChartsDao allPatientChartsDao,
            @Qualifier("patientRepo")
            PatientDao patientDao,
            @Qualifier("MedsPerMdJpaDataAccess")
            MedsPerMdDao medsDao, ExceptionsMessageReturn messageReturn) {
        this.dtoMapper = dtoMapper;
        this.chartDao = chartDao;
        this.allPatientChartsDao = allPatientChartsDao;
        this.patientDao = patientDao;
        this.medsDao = medsDao;
        this.messageReturn = messageReturn;
    }

    public List<NonSedatedPatientAssessmentDto> getAllCharts(Long patientId) {
        return chartDao.getAllCharts(patientId)
                .stream()
                .map(dtoMapper::chartToDto)
                .toList();
    }

    public NonSedatedPatientAssessmentDto getChartById(Long chartId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        NonSedatedPatientAssessment chart = chartDao.findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("NonSedatedPatientAssessmentChartNotFound") + " " + chartId
                        )
                );

        return dtoMapper.chartToDto(chart);
    }

    public IdResponse createNewChart(
            Long patientId,
            NonSedatedPatientAssessmentRequest request
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

        NonSedatedPatientAssessment chart = NonSedatedPatientAssessmentRequestToChart.requestToChart(request);

        Set<MedsPerMDEntity> meds = medsDao.createAll(request.medsPerMDList());

        chart.setMedsPerMDList(meds);
        chart.setPatient(patient);

        NonSedatedPatientAssessment newChart = chartDao.createNewChart(chart);

        allCharts.setChartType(ChartTypes.NON_SEDATED_PATIENT_ASSESSMENT);
        allPatientChartsDao.updateCharts(allCharts);

        return new DtoForReturnIdOnly(newChart.getId());
    }
}
