package com.azu.hospital.Patients.charts.postDischargeInstructions.services;

import com.azu.hospital.Patients.charts.postDischargeInstructions.entity.PostDischargeInstructionsChart;
import com.azu.hospital.Patients.charts.postDischargeInstructions.dao.PostDischargeInstructionsDao;
import com.azu.hospital.Patients.charts.postDischargeInstructions.request.PostDischargeInstructionRequest;
import com.azu.hospital.Patients.charts.postDischargeInstructions.utils.PostDischargeInstructionRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service("PostDischargeInstructionsUpdateService")
public class PostDischargeInstructionsUpdateService {
  private final PostDischargeInstructionsDao chartDao;
  private final ExceptionsMessageReturn messageReturn;

  @Autowired
  public PostDischargeInstructionsUpdateService(
          @Qualifier("PostDischargeInstructionsJpaDataAccess")
          PostDischargeInstructionsDao chartDao, ExceptionsMessageReturn messageReturn
  ) {
    this.chartDao = chartDao;
      this.messageReturn = messageReturn;
  }

  public IdResponse updateExistsChart(Long chartId, PostDischargeInstructionRequest request){
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    PostDischargeInstructionsChart existsChart = chartDao.findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("PostDischargeInstructionsChartNotFound")+ " " + chartId
                    )
            );

    PostDischargeInstructionsChart newChart = PostDischargeInstructionRequestToChart
            .requestToChart(request);

    compareMethodExistChartAndUpdateCart(existsChart, newChart);

    newChart.setPatient(existsChart.getPatient());

    PostDischargeInstructionsChart savedChart = chartDao.updateExistsChart(newChart);

    return new DtoForReturnIdOnly(savedChart.getId());
  }

  private void compareMethodExistChartAndUpdateCart(
          PostDischargeInstructionsChart existsChart,
          PostDischargeInstructionsChart newChart
  ){
    if(newChart.getAnticoagulantAdvisement() == null){
      newChart.setAnticoagulantAdvisement(existsChart.getAnticoagulantAdvisement());
    }
    if(newChart.getDoctorName() == null){
      newChart.setDoctorName(existsChart.getDoctorName());
    }
    if(newChart.getPhoneNumber() == null){
      newChart.setPhoneNumber(existsChart.getPhoneNumber());
    }
    if(newChart.getHospitalName() == null){
      newChart.setHospitalName(existsChart.getHospitalName());
    }
    if(newChart.getPatientSignature() == null){
      newChart.setPatientSignature(existsChart.getPatientSignature());
    }
    if(newChart.getPatientSignatureDate() == null){
      newChart.setPatientSignatureDate(existsChart.getPatientSignatureDate());
    }
    if(newChart.getPatientSignatureTime() == null){
      newChart.setPatientSignatureTime(existsChart.getPatientSignatureTime());
    }
    if(newChart.getRegisteredNurseName() == null){
      newChart.setRegisteredNurseName(existsChart.getRegisteredNurseName());
    }
    if(newChart.getRegisteredDate() == null){
      newChart.setRegisteredDate(existsChart.getRegisteredDate());
    }
    if(newChart.getRegisteredTime() == null){
      newChart.setRegisteredTime(existsChart.getRegisteredTime());
    }
  }
}
