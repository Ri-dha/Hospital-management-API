package com.azu.hospital.Patients.charts.allPatientCharts.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dto.AllPatientChartsDto;
import com.azu.hospital.Patients.charts.allPatientCharts.dto.AllPatientChartsDtoMapper;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service("AllPatientChartServices")
public class AllPatientChartServices {

  private final AllPatientChartsDao dao;
  private final PatientDao patientDao;
  private final AllPatientChartsDtoMapper dtoMapper;
    private final ExceptionsMessageReturn messageReturn;

  public AllPatientChartServices(
          AllPatientChartsDao dao,
          PatientDao patientDao, AllPatientChartsDtoMapper dtoMapper, ExceptionsMessageReturn messageReturn
  ) {
    this.dao = dao;
    this.patientDao = patientDao;
    this.dtoMapper = dtoMapper;
      this.messageReturn = messageReturn;
  }


  public AllPatientChartsDto getExistsCharts(Long patientId){
      Locale locale = messageReturn.getMessageLocally();
      ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    Patient patient = patientDao.getPatientById(patientId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                           messages.getString("resourceNotFound")
                    )
            );

    AllPatientChart charts = dao.getAllPatientChartsByPatientId(patientId)
            .orElseGet(
                    () -> {
                      AllPatientChart c = new AllPatientChart();
                      c.setPatient(patient);
                      return dao.addNewChart(c);
                    }
            );

    return dtoMapper.apply(charts);
  }
}
