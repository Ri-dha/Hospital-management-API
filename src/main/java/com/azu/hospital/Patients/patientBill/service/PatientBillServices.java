package com.azu.hospital.Patients.patientBill.service;

import com.azu.hospital.Patients.patientBill.request.DrugItemUpdateRequest;
import com.azu.hospital.Patients.patientBill.request.LabTestUpdateRequest;
import com.azu.hospital.Patients.patientBill.request.OperationUpdateRequest;
import com.azu.hospital.exceptions.RequestValidationException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.lab_test_main_table.dao.LabTestMainTableDao;
import com.azu.hospital.lab_collection.lab_test_main_table.entity.LabTestMainTableForMainTestName;
import com.azu.hospital.operation.surgical_list.dao.MainSurgicalListDao;
import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
import com.azu.hospital.ph.main_data_store.drugs_item.dao.DrugItemDao;
import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class PatientBillServices {
  private final MainSurgicalListDao surgicalListDao;
  private final LabTestMainTableDao labTestMainTableDao;
  private final DrugItemDao drugItemDao;
  private final ExceptionsMessageReturn messageReturn;


  @Autowired
  public PatientBillServices(MainSurgicalListDao surgicalListDao, LabTestMainTableDao labTestMainTableDao, DrugItemDao drugItemDao, ExceptionsMessageReturn messageReturn) {
    this.surgicalListDao = surgicalListDao;
    this.labTestMainTableDao = labTestMainTableDao;
    this.drugItemDao = drugItemDao;
      this.messageReturn = messageReturn;
  }

  @Transactional
  public void updateOperationBill(
          Long operationId, OperationUpdateRequest request
  ) {
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    MainSurgicalList list = surgicalListDao.getSurgicalById(operationId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    )
            );

    boolean changes = false;
    if(request.name() != null){
      list.setSurgicalName(request.name());
      changes = true;
    }
    if (request.price() != null) {
      list.setPrice(request.price());
      changes = true;
    }
    if (request.note() != null) {
      list.setNote(request.note());
      changes = true;
    }

    if (!changes) {
      throw new ResourceNotFoundException("There is No changes");
    }
    surgicalListDao.updateSurgicalName(list);
  }

  @Transactional
  public void updateDrugItem(
          Long drugId,
         DrugItemUpdateRequest request
  ) {
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    DrugsItem drugs = drugItemDao.selectDrudById(drugId)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    )
                    );
    boolean changes = false;
    if (request.price() != null) {
      drugs.setPrice(request.price());
      changes = true;
    }
    if (!changes) {
      throw new RequestValidationException(
                messages.getString("noChanges")
      );
    }
    drugItemDao.updateDrug(drugs);
  }


  @Transactional
  public void updateTest(Long id, LabTestUpdateRequest request) {
    Locale locale = messageReturn.getMessageLocally();
    ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
    LabTestMainTableForMainTestName table = labTestMainTableDao.getTestFromMainTableById(id)
            .orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    )
            );
    if (request.price() != null) {
      table.setPrice(request.price());
      labTestMainTableDao.updateExistTestFromMainTable(table);
    }
  }


}


