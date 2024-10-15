package com.azu.hospital.Patients.charts.preMedicalAssessment.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.preMedicalAssessment.request.PreMedicalAssessmentRequest;
import com.azu.hospital.Patients.charts.preMedicalAssessment.utils.PreMedicalAssessmentRequestToChart;
import com.azu.hospital.Patients.charts.preMedicalAssessment.entity.PreMedicalAssessmentChart;
import com.azu.hospital.Patients.charts.preMedicalAssessment.dao.PreMedicalAssessmentDao;
import com.azu.hospital.Patients.charts.preMedicalAssessment.dto.PreMedicalAssessmentDto;
import com.azu.hospital.Patients.charts.preMedicalAssessment.dto.PreMedicalAssessmentDtoMapper;
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

@Service("PreMedicalAssessmentService")
public class PreMedicalAssessmentService {
  private final PreMedicalAssessmentDao chartDao;
  private final AllPatientChartsDao allPatientChartsDao;
  private final PatientDao patientDao;
  private final PreMedicalAssessmentDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

  public PreMedicalAssessmentService(
          @Qualifier("PreMedicalAssessmentJpaDataAccess")
          PreMedicalAssessmentDao chartDao,
          @Qualifier("AllPatientChartsJpaDataAccess")
          AllPatientChartsDao allPatientChartsDao,
          @Qualifier("patientRepo")
          PatientDao patientDao,
          PreMedicalAssessmentDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
  ) {
    this.chartDao = chartDao;
    this.allPatientChartsDao = allPatientChartsDao;
    this.patientDao = patientDao;
    this.dtoMapper = dtoMapper;
      this.messageReturn = messageReturn;
  }

  public List<PreMedicalAssessmentDto> getAllPatientCharts(Long patientId){
    return chartDao.getAllCharts(patientId)
            .stream()
            .map(dtoMapper::chartToDto)
            .toList();
  }

  public PreMedicalAssessmentDto getChartById(Long chartId){
      Locale locale = messageReturn.getMessageLocally();
      ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    PreMedicalAssessmentChart chart = chartDao.findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("PreMedicalAssessmentChartNotFound") + " " + chartId
                    )
            );
    return dtoMapper.chartToDto(chart);
  }

  public IdResponse createNewChart(
          Long patientId,
          PreMedicalAssessmentRequest request
  ){
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

    PreMedicalAssessmentChart chart = PreMedicalAssessmentRequestToChart
            .requestToChart(request);

    chart.setPatient(patient);

    PreMedicalAssessmentChart newChart = chartDao.createNewChart(chart);

    allCharts.setChartType(ChartTypes.PRE_MEDICAL_ASSESSMENT_CHART);
    allPatientChartsDao.updateCharts(allCharts);

    return new DtoForReturnIdOnly(newChart.getId());
  }
}
