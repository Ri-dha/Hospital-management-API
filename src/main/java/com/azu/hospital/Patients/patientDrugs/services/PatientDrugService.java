package com.azu.hospital.Patients.patientDrugs.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.patientDrugs.dao.PatientDrug;
import com.azu.hospital.Patients.patientDrugs.dao.PatientDrugDao;
import com.azu.hospital.Patients.patientDrugs.dto.PatientDrugDto;
import com.azu.hospital.Patients.patientDrugs.dto.PatientDrugDtoMapper;
import com.azu.hospital.Patients.patientDrugs.request.PatientDrugRequest;
import com.azu.hospital.Patients.patientDrugs.request.PatientDrugRequestUpdate;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import com.azu.hospital.utils.return_id.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("PatientDrugService")
public class PatientDrugService {

  private final PatientDrugDao drugDao;
  private final PatientDao patientDao;
  private final PatientDrugDtoMapper mapper;
  private final ExceptionsMessageReturn messageReturn;


  @Autowired
  public PatientDrugService(
          @Qualifier("PatientDrugJpaDataAccess")
          PatientDrugDao drugDao,
          @Qualifier("patientRepo")
          PatientDao patientDao,
          PatientDrugDtoMapper mapper, ExceptionsMessageReturn messageReturn
  ) {
    this.drugDao = drugDao;
    this.patientDao = patientDao;
    this.mapper = mapper;
      this.messageReturn = messageReturn;
  }

  public List<PatientDrugDto> getDrugs(Long patientId) {
    return drugDao.getAllDrugsByPatientId(patientId)
            .stream()
            .map(mapper)
            .collect(Collectors.toList());
  }

  public void addNewDrug(Long patientId, List<PatientDrugRequest> request) {
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    Patient patient = patientDao.getPatientById(patientId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("patientNotFound")+" "+patientId
                    )
            );

    List<PatientDrug> drugs = request
            .stream()
            .map(p -> new PatientDrug(
                      p.drug(),
                      p.dose(),
                      p.time(),
                      p.roa(),
                      p.note(),
                      p.count()
              ))
            .collect(Collectors.toList());

    drugs.forEach(d -> {
      d.setPatient(patient);
    });

    drugDao.create(drugs);
  }

  public IdResponse updateExistsDrug(Long drugId, List<PatientDrugRequestUpdate> request){
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());

    for (PatientDrugRequestUpdate p : request) {
      if (p.getDrugId() == null) {
        PatientDrug drug = new PatientDrug(
                p.getDrug(),
                p.getDose(),
                p.getTime(),
                p.getRoa(),
                p.getNote(),
                p.getCount()
        );
        drugDao.createPatientDrug(drug);
      }else {
        PatientDrug drug = drugDao.getDrugById(p.getDrugId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("drugNotFound")+" "+p.getDrugId()
                        )
                );

        if (p.getDrug() != null) {
          drug.setDrug(p.getDrug());
        }
        if (p.getDose() != null) {
          drug.setDose(p.getDose());
        }
        if (p.getTime() != null) {
          drug.setTime(p.getTime());
        }
        if (p.getRoa() != null) {
          drug.setRoa(p.getRoa());
        }
        if (p.getCount() != null) {
          drug.setCount(p.getCount());
        }
        if (p.getNote() != null) {
          drug.setNote(p.getNote());
        }
        drugDao.update(drug);
      }
    }

    return new DtoForReturnIdOnly(drugId);


  }

}
