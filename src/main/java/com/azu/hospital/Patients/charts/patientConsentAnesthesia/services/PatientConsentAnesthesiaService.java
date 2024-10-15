package com.azu.hospital.Patients.charts.patientConsentAnesthesia.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.patientConsentAnesthesia.dto.PatientConsentAnesthesiaDto;
import com.azu.hospital.Patients.charts.patientConsentAnesthesia.dto.PatientConsentAnesthesiaDtoMapper;
import com.azu.hospital.Patients.charts.patientConsentAnesthesia.request.PatientConsentAnesthesiaRequest;
import com.azu.hospital.Patients.charts.patientConsentAnesthesia.utils.PatientConsentAnesthesiaRequestToChart;
import com.azu.hospital.Patients.charts.patientConsentAnesthesia.entity.PatientConsentAnesthesiaChart;
import com.azu.hospital.Patients.charts.patientConsentAnesthesia.dao.PatientConsentAnesthesiaDao;
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

@Service("PatientConsentAnesthesiaService")
public class PatientConsentAnesthesiaService {
  private final PatientConsentAnesthesiaDao chartDao;
  private final AllPatientChartsDao allPatientChartsDao;
  private final PatientDao patientDao;
  private final PatientConsentAnesthesiaDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

  public PatientConsentAnesthesiaService(
          @Qualifier("PatientConsentAnesthesiaJpaDataAccess")
          PatientConsentAnesthesiaDao chartDao,
          @Qualifier("AllPatientChartsJpaDataAccess")
          AllPatientChartsDao allPatientChartsDao,
          @Qualifier("patientRepo")
          PatientDao patientDao,
          PatientConsentAnesthesiaDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
  ) {
    this.chartDao = chartDao;
    this.allPatientChartsDao = allPatientChartsDao;
    this.patientDao = patientDao;
    this.dtoMapper = dtoMapper;
      this.messageReturn = messageReturn;
  }

  public List<PatientConsentAnesthesiaDto> getAllPatientChart(Long patientId){
    return chartDao.getAllCharts(patientId)
            .stream()
            .map(dtoMapper::chartToDto)
            .toList();
  }

  public PatientConsentAnesthesiaDto getChartById(Long chartId){
      Locale locale = messageReturn.getMessageLocally();
      ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    PatientConsentAnesthesiaChart chart = chartDao.findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("PatientConsentAnesthesiaChartNotFound") + " " + chartId
                    )
            );
    return dtoMapper.chartToDto(chart);
  }

  public IdResponse createNewChart(
          Long patientId,
          PatientConsentAnesthesiaRequest request
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
                    messages.getString("patientHasNotMedicalHistory") + " " + patientId
          );

    AllPatientChart allCharts = allPatientChartsDao.getAllPatientChartsByPatientId(patientId)
            .orElseGet(
                    () -> {
                      AllPatientChart c = new AllPatientChart();
                      c.setPatient(patient);
                      return allPatientChartsDao.addNewChart(c);
                    }
            );

    PatientConsentAnesthesiaChart chart = PatientConsentAnesthesiaRequestToChart.requestToChart(request);

    chart.setPatient(patient);

    PatientConsentAnesthesiaChart savedChart = chartDao.createNewChart(chart);

    allCharts.setChartType(ChartTypes.PATIENT_CONSENT_ANESTHESIA_CHART);
    allPatientChartsDao.updateCharts(allCharts);

    return new DtoForReturnIdOnly(savedChart.getId());
  }

}
