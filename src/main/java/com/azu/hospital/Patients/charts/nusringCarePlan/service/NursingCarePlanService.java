package com.azu.hospital.Patients.charts.nusringCarePlan.service;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChart;
import com.azu.hospital.Patients.charts.allPatientCharts.dao.AllPatientChartsDao;
import com.azu.hospital.Patients.charts.nusringCarePlan.dao.NursingCarePlanDao;
import com.azu.hospital.Patients.charts.nusringCarePlan.dto.NursingCarePlanDto;
import com.azu.hospital.Patients.charts.nusringCarePlan.dto.NursingCarePlanDtoMapper;
import com.azu.hospital.Patients.charts.nusringCarePlan.entity.NursingCarePlan;
import com.azu.hospital.Patients.charts.nusringCarePlan.request.NursingCarePlanRequest;
import com.azu.hospital.Patients.charts.nusringCarePlan.utils.NursingCarePlanRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.patient.ChartTypes;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class NursingCarePlanService {
  private final NursingCarePlanDao nursingCarePlanDao;

  private final AllPatientChartsDao allPatientChartsDao;
    private final ExceptionsMessageReturn messageReturn;

  private final PatientDao patientDao;

  private final NursingCarePlanDtoMapper nursingCarePlanDtoMapper;

  @Autowired
  public NursingCarePlanService(NursingCarePlanDao nursingCarePlanDao, AllPatientChartsDao allPatientChartsDao, ExceptionsMessageReturn messageReturn, PatientDao patientDao, NursingCarePlanDtoMapper nursingCarePlanDtoMapper) {
    this.nursingCarePlanDao = nursingCarePlanDao;

    this.allPatientChartsDao = allPatientChartsDao;
      this.messageReturn = messageReturn;
      this.patientDao = patientDao;
    this.nursingCarePlanDtoMapper = nursingCarePlanDtoMapper;
  }

  public List<NursingCarePlanDto> getAllChart(
          Long patientId
  ){
    return nursingCarePlanDao.getAllCharts(patientId)
            .stream()
            .map(nursingCarePlanDtoMapper::chartDto)
            .toList();
  }

  public IdResponse addNursingCareChart(
          Long patientId,
          NursingCarePlanRequest request
  ){
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
    NursingCarePlan nursingCarePlan = NursingCarePlanRequestToChart
            .getNursingCarePlan(request);

    nursingCarePlan.setPatient(patient);

    NursingCarePlan carePlan = nursingCarePlanDao.createNewChart(nursingCarePlan);
    allCharts.setChartType(ChartTypes.NURSING_CARE_PLAN_CHART);
    allPatientChartsDao.updateCharts(allCharts);

    return new DtoForReturnIdOnly(carePlan.getId());
  }


  public NursingCarePlanDto getNursingCarePlan(Long chartId){
      Locale locale = messageReturn.getMessageLocally();
      ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    NursingCarePlan chart = nursingCarePlanDao
            .findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("NursingCarePlanChartNotFound")+" "+chartId

                    )
            );
    return nursingCarePlanDtoMapper.chartDto(chart);
  }
}
