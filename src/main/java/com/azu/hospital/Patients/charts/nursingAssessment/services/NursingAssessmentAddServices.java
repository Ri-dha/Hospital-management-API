package com.azu.hospital.Patients.charts.nursingAssessment.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.nursingAssessment.entity.NursingAssessmentChart;
import com.azu.hospital.Patients.charts.nursingAssessment.dao.NursingAssessmentChartDao;
import com.azu.hospital.Patients.charts.nursingAssessment.dto.NursingAssessmentChartDto;
import com.azu.hospital.Patients.charts.nursingAssessment.dto.NursingAssessmentChartDtoMapper;
import com.azu.hospital.Patients.charts.nursingAssessment.request.NursingAssessmentChartRequest;
import com.azu.hospital.Patients.charts.nursingAssessment.utils.NursingAssessmentRequestToChart;
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

@Service("NursingChartAddService")
public class NursingAssessmentAddServices {

    private final NursingAssessmentChartDao assessmentChartDao;
    private final AllPatientChartsDao allPatientChartsDao;
    private final PatientDao patientDao;
    private final NursingAssessmentChartDtoMapper assessmentChartDtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public NursingAssessmentAddServices(
            @Qualifier("NursingAssessmentJpa")
            NursingAssessmentChartDao assessmentChartDao,
            @Qualifier("AllPatientChartsJpaDataAccess")
            AllPatientChartsDao allPatientChartsDao,
            @Qualifier("patientRepo")
            PatientDao patientDao,
            NursingAssessmentChartDtoMapper assessmentChartDtoMapper, ExceptionsMessageReturn messageReturn
    ){
        this.assessmentChartDao = assessmentChartDao;
        this.allPatientChartsDao = allPatientChartsDao;
        this.patientDao = patientDao;
        this.assessmentChartDtoMapper = assessmentChartDtoMapper;
        this.messageReturn = messageReturn;
    }

    public List<NursingAssessmentChartDto> getAllCharts(
            Long patientId
    ){
        return assessmentChartDao.getAllCharts(patientId)
                .stream()
                .map(assessmentChartDtoMapper::chartDto)
                .toList();
    }


    public IdResponse addNursingChart(
            Long patientId,
            NursingAssessmentChartRequest request
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("patientNotFound")+" "+patientId
                        )
                );

        if (!patient.getIsHasMedicalHistory())
            throw new ResourceNotFoundException(
                    messages.getString("patientHasNoMedicalHistory")+" "+patientId
            );

        AllPatientChart allCharts = allPatientChartsDao.getAllPatientChartsByPatientId(patientId)
                .orElseGet(
                        () -> {
                            AllPatientChart c = new AllPatientChart();
                            c.setPatient(patient);
                            return allPatientChartsDao.addNewChart(c);
                        }
                );

        NursingAssessmentChart assessmentChart = NursingAssessmentRequestToChart
                .getNursingAssessmentChartPrivate(request);

        assessmentChart.setPatient(patient);

        NursingAssessmentChart chart = assessmentChartDao.createNewChart(assessmentChart);

        allCharts.setChartType(ChartTypes.NURSING_ASSESSMENT_CHART);
        allPatientChartsDao.updateCharts(allCharts);

        return new DtoForReturnIdOnly(chart.getId());
    }



    public NursingAssessmentChartDto getNursingAssessmentChartById(Long chartId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        NursingAssessmentChart chart = assessmentChartDao
                .findChartById(chartId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("NursingAssessmentChartNotFound")+" "+chartId
                        )
                );
        return assessmentChartDtoMapper.chartDto(chart);
    }
}
