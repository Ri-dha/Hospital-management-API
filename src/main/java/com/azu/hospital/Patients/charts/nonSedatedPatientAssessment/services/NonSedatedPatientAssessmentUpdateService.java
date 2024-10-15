package com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.services;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.charts.physical_assessment_enm.MDOrders;
import com.azu.hospital.Patients.charts.physical_assment.MedsPerMD;
import com.azu.hospital.Patients.charts.physical_assment.PatientLimitation;
import com.azu.hospital.Patients.charts.physical_assment.PatientPositions;
import com.azu.hospital.Patients.charts.physical_assment.ProcedureArea;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.entities.NonSedatedPatientAssessment;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.dao.NonSedatedPatientAssessmentDao;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.request.NonSedatedPatientAssessmentRequest;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.utils.NonSedatedPatientAssessmentRequestToChart;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Service("NonSedatedPatientAssessmentUpdateService")
public class NonSedatedPatientAssessmentUpdateService {

  private final NonSedatedPatientAssessmentDao chartDao;
  private final PatientDao patientDao;
  private final ExceptionsMessageReturn messageReturn;

  @Autowired
  public NonSedatedPatientAssessmentUpdateService(
          @Qualifier("NonSedatedPatientAssessmentJpaDataAccess")
          NonSedatedPatientAssessmentDao chartDao,
          @Qualifier("patientRepo")
          PatientDao patientDao, ExceptionsMessageReturn messageReturn
  ) {
    this.chartDao = chartDao;
    this.patientDao = patientDao;
      this.messageReturn = messageReturn;
  }

  public IdResponse updateExistsChart(
          Long patientId,
          Long chartId,
          NonSedatedPatientAssessmentRequest request
  ){
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    Patient patient = patientDao.getPatientById(patientId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("patientNotFound")+" "+patientId
                    )
            );

    NonSedatedPatientAssessment chart = chartDao.findChartById(chartId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("NonSedatedPatientAssessmentChartNotFound")+" "+chartId
                    )
            );

    NonSedatedPatientAssessment newChart = NonSedatedPatientAssessmentRequestToChart.requestToChart(request);

    compareMethodExistChartAndUpdateCart(chart, newChart);

    newChart.setPatient(patient);

    NonSedatedPatientAssessment update = chartDao.updateExistsChart(newChart);

    return new DtoForReturnIdOnly(update.getId());
  }
  private static void compareMethodExistChartAndUpdateCart(
          NonSedatedPatientAssessment existsChart,
          NonSedatedPatientAssessment newChart
  ){

    patientLimitationMethod(existsChart, newChart);

    procedureAreaMethod(existsChart, newChart);

    medsPerMdMethod(existsChart, newChart);

    if(newChart.getMedsPerMDList() == null){
      newChart.setMedsPerMDList(existsChart.getMedsPerMDList());
    }
    if(newChart.getMedsPerMDListOther() == null){
      newChart.setMedsPerMDListOther(existsChart.getMedsPerMDListOther());
    }
    if(newChart.getNursesNotes() == null){
      newChart.setNursesNotes(existsChart.getNursesNotes());
    }
    if(newChart.getPostOperativeDiagnosis() == null){
      newChart.setPostOperativeDiagnosis(existsChart.getPostOperativeDiagnosis());
    }
    if(newChart.getProcedurePerformed() == null){
      newChart.setProcedurePerformed(existsChart.getProcedurePerformed());
    }
    if(newChart.getTimeStart() == null){
      newChart.setTimeStart(existsChart.getTimeStart());
    }
    if(newChart.getTimeFinish() == null){
      newChart.setTimeFinish(existsChart.getTimeFinish());
    }

  }

  private static void medsPerMdMethod(NonSedatedPatientAssessment existsChart, NonSedatedPatientAssessment newChart) {
    if(newChart.getMedsPerMD() == null){
      newChart.setMedsPerMD(existsChart.getMedsPerMD());
    } else {
      MedsPerMD newMedsPerMD = newChart.getMedsPerMD();
      MedsPerMD existsMedsPerMD = existsChart.getMedsPerMD();
      if(newMedsPerMD.getMarcaine_25() == null){
        Map<MDOrders, Boolean> update = new HashMap<>(existsMedsPerMD.getMarcaine_25());
        newMedsPerMD.setMarcaine_25(update);
      }
      if(newMedsPerMD.getMarcaine_5() == null){
        Map<MDOrders, Boolean> update = new HashMap<>(existsMedsPerMD.getMarcaine_5());
        newMedsPerMD.setMarcaine_5(update);
      }
      if(newMedsPerMD.getMarcaine_75() == null){
        Map<MDOrders, Boolean> update = new HashMap<>(existsMedsPerMD.getMarcaine_75());
        newMedsPerMD.setMarcaine_75(update);
      }
      if(newMedsPerMD.getLidocaine_1() == null){
        Map<MDOrders, Boolean> update = new HashMap<>(existsMedsPerMD.getLidocaine_1());
        newMedsPerMD.setLidocaine_1(update);
      }
      if(newMedsPerMD.getLidocaine_2() == null){
//        Map<MDOrders, Boolean> update = new HashMap<>(existsMedsPerMD.getLidocaine_2());
        newMedsPerMD.setLidocaine_2(existsMedsPerMD.getLidocaine_2());
      }
    }
  }

  private static void procedureAreaMethod(NonSedatedPatientAssessment existsChart, NonSedatedPatientAssessment newChart) {
    if(newChart.getProcedureArea() == null){
      newChart.setProcedureArea(existsChart.getProcedureArea());
    } else {
      ProcedureArea newProcedureArea = newChart.getProcedureArea();
      ProcedureArea existsProcedureArea = existsChart.getProcedureArea();
      if(newProcedureArea.getArea() == null){
        newProcedureArea.setArea(existsProcedureArea.getArea());
      }
      if(newProcedureArea.getTubes() == null){
        newProcedureArea.setTubes(existsProcedureArea.getTubes());
      }
      if(newProcedureArea.getDrains() == null){
        newProcedureArea.setDrains(existsProcedureArea.getDrains());
      }
      if(newProcedureArea.getProcedureAreaOther() == null){
        newProcedureArea.setProcedureAreaOther(existsProcedureArea.getProcedureAreaOther());
      }
    }
  }

  private static void patientLimitationMethod(NonSedatedPatientAssessment existsChart, NonSedatedPatientAssessment newChart) {
    if(newChart.getPatientLimitation() == null){
      newChart.setPatientLimitation(existsChart.getPatientLimitation());
    } else {
      PatientLimitation newPatientLimitation = newChart.getPatientLimitation();
      PatientLimitation existsPatientLimitation = existsChart.getPatientLimitation();
      if(newPatientLimitation.getLimitation() == null){
        newPatientLimitation.setLimitation(existsPatientLimitation.getLimitation());
      }
      if(newPatientLimitation.getList() == null){
        newPatientLimitation.setList(existsPatientLimitation.getList());
      }
    }
  }

  private static void patientPositionMethod(NonSedatedPatientAssessment existsChart, NonSedatedPatientAssessment newChart) {
    if(newChart.getPatientPositions() == null){
      newChart.setPatientPositions(existsChart.getPatientPositions());
    } else {
      PatientPositions newPatientPositions = newChart.getPatientPositions();
      PatientPositions existsChartPatientPositions = existsChart.getPatientPositions();
      if(newPatientPositions.getPositions() == null){
        newPatientPositions.setPositions(existsChartPatientPositions.getPositions());
      }
      if(newPatientPositions.getBodyContact() == null){
        newPatientPositions.setBodyContact(existsChartPatientPositions.getBodyContact());
      }
      if(newPatientPositions.getIntact() == null){
        newPatientPositions.setIntact(existsChartPatientPositions.getIntact());
      }
      if(newPatientPositions.getPatientPositionsOther() == null){
        newPatientPositions.setPatientPositionsOther(existsChartPatientPositions.getPatientPositionsOther());
      }
      if(newPatientPositions.getPatientPositionsOtherNote() == null){
        newPatientPositions.setPatientPositionsOtherNote(existsChartPatientPositions.getPatientPositionsOtherNote());
      }
      if(newPatientPositions.getIsNPO() == null){
        newPatientPositions.setIsNPO(existsChartPatientPositions.getIsNPO());
      }
      if(newPatientPositions.getIsConsentSigned() == null){
        newPatientPositions.setIsConsentSigned(existsChartPatientPositions.getIsConsentSigned());
      }
      if(newPatientPositions.getIsVerification() == null){
        newPatientPositions.setIsVerification(existsChartPatientPositions.getIsVerification());
      }
    }
  }
}
