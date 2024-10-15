package com.azu.hospital.Patients.charts.preOperativeHPChart.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.preOperativeHPChart.entity.PreOperativeHPChart;
import com.azu.hospital.Patients.charts.preOperativeHPChart.dao.PreOperativeHPChartDao;
import com.azu.hospital.Patients.charts.preOperativeHPChart.dto.PreOperativeHPDto;
import com.azu.hospital.Patients.charts.preOperativeHPChart.dto.PreOperativeHPDtoMapper;
import com.azu.hospital.Patients.charts.preOperativeHPChart.request.PreOperativeHPChartRequest;
import com.azu.hospital.Patients.charts.preOperativeHPChart.utils.PreOperativeHPRequestToChart;
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

@Service("PreOperativeHPService")
public class PreOperativeHPService {
  private final PreOperativeHPChartDao chartDao;
  private final AllPatientChartsDao allPatientChartsDao;
  private final PatientDao patientDao;
  private final PreOperativeHPDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

  @Autowired
  public PreOperativeHPService(
          @Qualifier("PreOperativeHpChartJpaDataAccess")
          PreOperativeHPChartDao chartDao,
          @Qualifier("AllPatientChartsJpaDataAccess")
          AllPatientChartsDao allPatientChartsDao,
          @Qualifier("patientRepo")
          PatientDao patientDao,
          PreOperativeHPDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
  ) {
    this.chartDao = chartDao;
    this.allPatientChartsDao = allPatientChartsDao;
    this.patientDao = patientDao;
    this.dtoMapper = dtoMapper;
      this.messageReturn = messageReturn;
  }

  public List<PreOperativeHPDto> getAllPatientChart(Long patientId){
    return chartDao.getAllCharts(patientId)
            .stream()
            .map(dtoMapper::chartToDto)
            .toList();
  }

  public PreOperativeHPDto getChartById(Long chartId) {
      Locale locale = messageReturn.getMessageLocally();
      ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    PreOperativeHPChart chart = chartDao
            .findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("PreOperativeHPChartNotFound") + " " + chartId

                    )
            );

    return dtoMapper.chartToDto(chart);
  }

  public IdResponse createNewChart(
          Long patientId,
          PreOperativeHPChartRequest request
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

    PreOperativeHPChart chart = PreOperativeHPRequestToChart.requestToChart(request);

    chart.setPatient(patient);

    PreOperativeHPChart saved = chartDao.createNewChart(chart);

    allCharts.setChartType(ChartTypes.PRE_OPERATIVE_HP_CHART);
    allPatientChartsDao.updateCharts(allCharts);

    return new DtoForReturnIdOnly(saved.getId());
  }
}
