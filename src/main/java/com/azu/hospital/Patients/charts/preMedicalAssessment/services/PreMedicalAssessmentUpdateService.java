package com.azu.hospital.Patients.charts.preMedicalAssessment.services;


import com.azu.hospital.Patients.charts.preMedicalAssessment.request.PreMedicalAssessmentRequest;
import com.azu.hospital.Patients.charts.preMedicalAssessment.utils.PreMedicalAssessmentRequestToChart;
import com.azu.hospital.Patients.charts.physical_assment.CardiacRiskAssessment;
import com.azu.hospital.Patients.charts.physical_assment.NormalExamCheck;
import com.azu.hospital.Patients.charts.preMedicalAssessment.entity.PreMedicalAssessmentChart;
import com.azu.hospital.Patients.charts.preMedicalAssessment.dao.PreMedicalAssessmentDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service("PreMedicalAssessmentUpdateService")
public class PreMedicalAssessmentUpdateService {
  private final PreMedicalAssessmentDao chartDao;
  private final ExceptionsMessageReturn messageReturn;

  public PreMedicalAssessmentUpdateService(
          @Qualifier("PreMedicalAssessmentJpaDataAccess")
          PreMedicalAssessmentDao chartDao, ExceptionsMessageReturn messageReturn
  ) {
    this.chartDao = chartDao;
      this.messageReturn = messageReturn;
  }

  public IdResponse updateExistsChart(Long chartId, PreMedicalAssessmentRequest request){
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    PreMedicalAssessmentChart existsChart = chartDao.findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("PreMedicalAssessmentChartNotFound")+ " " + chartId
                    )
            );

    PreMedicalAssessmentChart newChart = PreMedicalAssessmentRequestToChart.requestToChart(request);

    compareMethodExistChartAndUpdateCart(existsChart, newChart);

    newChart.setPatient(existsChart.getPatient());

    PreMedicalAssessmentChart savedChart = chartDao.updateExistsChart(newChart);

    return new DtoForReturnIdOnly(savedChart.getId());
  }

  private void compareMethodExistChartAndUpdateCart(
          PreMedicalAssessmentChart existsChart,
          PreMedicalAssessmentChart newChart
  ){

    if(newChart.getPlannedSurgicalProcedure() == null){
      newChart.setPlannedSurgicalProcedure(existsChart.getPlannedSurgicalProcedure());
    }
    if(newChart.getSurgeryDate() == null){
      newChart.setSurgeryDate(existsChart.getSurgeryDate());
    }
    if(newChart.getSurgeryLocation() == null){
      newChart.setSurgeryDate(existsChart.getSurgeryLocation());
    }
    if(newChart.getAttendingSurgeon() == null){
      newChart.setAttendingSurgeon(existsChart.getAttendingSurgeon());
    }
    if(newChart.getPresentIllnessHistory() == null){
      newChart.setPresentIllnessHistory(existsChart.getPresentIllnessHistory());
    }
    if(newChart.getPastMedicalHistoryNote() == null){
      newChart.setPastMedicalHistoryNote(existsChart.getPastMedicalHistoryNote());
    }
    if(newChart.getPastMedicalHistoryTable() == null){
      newChart.setPastMedicalHistoryTable(existsChart.getPastMedicalHistoryTable());
    }
    if(newChart.getPriorAnesthesiaComplication() == null){
      newChart.setPriorAnesthesiaComplication(existsChart.getPriorAnesthesiaComplication());
    }
    if(newChart.getSocialHistory() == null){
      newChart.setSocialHistory(existsChart.getSocialHistory());
    }
    if(newChart.getCardiacHistory() == null){
      newChart.setCardiacHistory(existsChart.getCardiacHistory());
    }
    if(newChart.getSymptomsSystem() == null){
      newChart.setSymptomsSystem(existsChart.getSymptomsSystem());
    }
    if(newChart.getBmi() == null){
      newChart.setBmi(existsChart.getBmi());
    }
    checkNormalExamCheck(existsChart, newChart);

    if(newChart.getSurgicalRisk() == null){
      newChart.setSurgicalRisk(existsChart.getSurgicalRisk());
    }
    checkCardiacRiskAssessment(existsChart, newChart);

  }

  private static void checkCardiacRiskAssessment(PreMedicalAssessmentChart existsChart, PreMedicalAssessmentChart newChart) {
    if(newChart.getCardiacRiskAssessment() == null){
      newChart.setCardiacRiskAssessment(existsChart.getCardiacRiskAssessment());
    } else {
      CardiacRiskAssessment newCardiacRiskAssessment = newChart.getCardiacRiskAssessment();
      CardiacRiskAssessment existsCardiacRiskAssessment = existsChart.getCardiacRiskAssessment();

      if(newCardiacRiskAssessment.getRcriRisk() == null){
        newCardiacRiskAssessment.setRcriRisk(existsCardiacRiskAssessment.getRcriRisk());
      }
      if(newCardiacRiskAssessment.getPatientHas() == null){
        newCardiacRiskAssessment.setPatientHas(existsCardiacRiskAssessment.getPatientHas());
      }
      if(newCardiacRiskAssessment.getNonCardiacRisk() == null){
        newCardiacRiskAssessment.setNonCardiacRisk(existsCardiacRiskAssessment.getNonCardiacRisk());
      }
      if(newCardiacRiskAssessment.getOverallMedicalRisk() == null){
        newCardiacRiskAssessment.setOverallMedicalRisk(existsCardiacRiskAssessment.getOverallMedicalRisk());
      }
      if(newCardiacRiskAssessment.getRecommendations() == null){
        newCardiacRiskAssessment.setRecommendations(existsCardiacRiskAssessment.getRecommendations());
      }
      if(newCardiacRiskAssessment.getFurtherConsults() == null){
        newCardiacRiskAssessment.setFurtherConsults(existsCardiacRiskAssessment.getFurtherConsults());
      }
      if(newCardiacRiskAssessment.getFurtherTesting() == null){
        newCardiacRiskAssessment.setFurtherTesting(existsCardiacRiskAssessment.getFurtherTesting());
      }
      if(newCardiacRiskAssessment.getOptimized() == null){
        newCardiacRiskAssessment.setOptimized(existsCardiacRiskAssessment.getOptimized());
      }
    }
  }

  private static void checkNormalExamCheck(PreMedicalAssessmentChart existsChart, PreMedicalAssessmentChart newChart) {
    if(newChart.getNormalExamCheck() == null){
      newChart.setNormalExamCheck(existsChart.getNormalExamCheck());
    } else {
      NormalExamCheck newNormalExamCheck = newChart.getNormalExamCheck();
      NormalExamCheck existsNormalExamCheck = existsChart.getNormalExamCheck();

      if(newNormalExamCheck.getGeneral() == null){
        newNormalExamCheck.setGeneral(existsNormalExamCheck.getGeneral());
      }
      if(newNormalExamCheck.getEnt() == null){
        newNormalExamCheck.setEnt(existsNormalExamCheck.getEnt());
      }
      if(newNormalExamCheck.getNeck() == null){
        newNormalExamCheck.setNeck(existsNormalExamCheck.getNeck());
      }
      if(newNormalExamCheck.getCv() == null){
        newNormalExamCheck.setCv(existsNormalExamCheck.getCv());
      }
      if(newNormalExamCheck.getLungs() == null){
        newNormalExamCheck.setLungs(existsNormalExamCheck.getLungs());
      }
      if(newNormalExamCheck.getAbd() == null){
        newNormalExamCheck.setAbd(existsNormalExamCheck.getAbd());
      }
      if(newNormalExamCheck.getExt() == null){
        newNormalExamCheck.setExt(existsNormalExamCheck.getExt());
      }
      if(newNormalExamCheck.getNeuro() == null){
        newNormalExamCheck.setNeuro(existsNormalExamCheck.getNeuro());
      }
    }
  }
}
