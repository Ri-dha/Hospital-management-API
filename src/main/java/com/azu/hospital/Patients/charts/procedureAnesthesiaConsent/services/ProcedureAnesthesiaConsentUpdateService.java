package com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.services;

import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.entity.ProcedureAnesthesiaConsentChart;
import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.dao.ProcedureAnesthesiaConsentDao;
import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.request.ProcedureAnesthesiaConsentRequest;
import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.utils.ProcedureAnesthesiaConsentRequestToChart;
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

@Service("ProcedureAnesthesiaConsentUpdateService")
public class ProcedureAnesthesiaConsentUpdateService {
  private final ProcedureAnesthesiaConsentDao chartDao;
  private final ExceptionsMessageReturn messageReturn;

  @Autowired
  public ProcedureAnesthesiaConsentUpdateService(
          @Qualifier("ProcedureAnesthesiaConsentJpaDataAccess")
          ProcedureAnesthesiaConsentDao chartDao, ExceptionsMessageReturn messageReturn
  ) {
    this.chartDao = chartDao;
      this.messageReturn = messageReturn;
  }

  public IdResponse updateExistsChart(Long chartId, ProcedureAnesthesiaConsentRequest request){
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    ProcedureAnesthesiaConsentChart existsChart = chartDao.findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("ProcedureAnesthesiaConsentChartNotFound")+ " " + chartId
                    )
            );

    ProcedureAnesthesiaConsentChart newChart = ProcedureAnesthesiaConsentRequestToChart.requestToChart(request);

    compareMethodExistChartAndUpdateCart(existsChart, newChart);

    newChart.setPatient(existsChart.getPatient());

    ProcedureAnesthesiaConsentChart savedChart = chartDao.updateExistsChart(newChart);

    return new DtoForReturnIdOnly(savedChart.getId());
  }

  private void compareMethodExistChartAndUpdateCart(
          ProcedureAnesthesiaConsentChart existsChart,
          ProcedureAnesthesiaConsentChart newChart
  ){
    if(newChart.getMrn() == null){
      newChart.setMrn(existsChart.getMrn());
    }
    if(newChart.getProposedProcedures() == null){
      newChart.setProposedProcedures(existsChart.getProposedProcedures());
    }
    if(newChart.getNotEatenOrDrankSinceTime() == null){
      newChart.setNotEatenOrDrankSinceTime(existsChart.getNotEatenOrDrankSinceTime());
    }
    if(newChart.getNotEatenOrDrankSinceDate() == null){
      newChart.setNotEatenOrDrankSinceDate(existsChart.getNotEatenOrDrankSinceDate());
    }
    if(newChart.getPatientSignature() == null){
      newChart.setPatientSignature(existsChart.getPatientSignature());
    }
    if(newChart.getPatientSignatureDate() == null){
      newChart.setPatientSignatureDate(existsChart.getPatientSignatureDate());
    }
    if(newChart.getWitnessSignature() == null){
      newChart.setWitnessSignature(existsChart.getWitnessSignature());
    }
    if(newChart.getWitnessSignatureDate() == null){
      newChart.setWitnessSignatureDate(existsChart.getWitnessSignatureDate());
    }
    if(newChart.getPhysicianSignature() == null){
      newChart.setPhysicianSignature(existsChart.getPhysicianSignature());
    }
    if(newChart.getPhysicianSignatureDate() == null){
      newChart.setPhysicianSignatureDate(existsChart.getPhysicianSignatureDate());
    }
  }
}
