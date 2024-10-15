package com.azu.hospital.Patients.charts.fluidBalance.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.fluidBalance.dao.FluidBalanceDao;
import com.azu.hospital.Patients.charts.fluidBalance.dto.FluidBalanceDto;
import com.azu.hospital.Patients.charts.fluidBalance.dto.FluidBalanceDtoMapper;
import com.azu.hospital.Patients.charts.fluidBalance.entities.FluidBalanceChart;
import com.azu.hospital.Patients.charts.fluidBalance.request.FluidBalanceRequest;
import com.azu.hospital.Patients.charts.fluidBalance.utils.FluidBalanceRequestToChart;
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

@Service("FluidBalanceService")
public class FluidBalanceService {
  private final FluidBalanceDao chartDao;
  private final AllPatientChartsDao allPatientChartsDao;
  private final PatientDao patientDao;
  private final FluidBalanceDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
  public FluidBalanceService(
            @Qualifier("FluidBalanceJpaDataAccess")
          FluidBalanceDao chartDao,
            @Qualifier("AllPatientChartsJpaDataAccess")
          AllPatientChartsDao allPatientChartsDao,
            @Qualifier("patientRepo")
          PatientDao patientDao,
            FluidBalanceDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
    ) {
    this.chartDao = chartDao;
    this.allPatientChartsDao = allPatientChartsDao;
    this.patientDao = patientDao;
    this.dtoMapper = dtoMapper;
        this.messageReturn = messageReturn;
    }

  public List<FluidBalanceDto> getAllCharts(Long patientId){
    return chartDao.getAllChartByPatientId(patientId)
            .stream()
            .map(dtoMapper::chartToDto)
            .toList();
  }

  public FluidBalanceDto getChartById(Long chartId) {
      Locale locale = messageReturn.getMessageLocally();
      ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    FluidBalanceChart chart = chartDao.findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("FluidBalanceChartNotFound")+" "+chartId
                    )
            );

    return dtoMapper.chartToDto(chart);
  }

  public IdResponse createNewChart(
          Long patientId,
          FluidBalanceRequest request
  ) {
      Locale locale = messageReturn.getMessageLocally();
      ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    Patient patient = patientDao.getPatientById(patientId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("PatientNotFound")+" "+patientId
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

    FluidBalanceChart chart = FluidBalanceRequestToChart.requestToChart(request);

    chart.setPatient(patient);

    FluidBalanceChart savedChart = chartDao.createNewChart(chart);

    allCharts.setChartType(ChartTypes.FLUID_BALANCE_CHART);
    allPatientChartsDao.updateCharts(allCharts);

    return new DtoForReturnIdOnly(savedChart.getId());
  }

}
