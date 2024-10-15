package com.azu.hospital.patient_expances.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.patient_expances.dao.PatientExpanseDao;
import com.azu.hospital.patient_expances.dao.PatientExpanseListDao;
import com.azu.hospital.patient_expances.dto.PatientExpanseDtoMapper;
import com.azu.hospital.patient_expances.entity.PatientExpanse;
import com.azu.hospital.patient_expances.entity.PatientExpanseList;
import com.azu.hospital.patient_expances.request.PatientExpanseListUpdate;
import com.azu.hospital.patient_expances.request.PatientExpanseRequest;
import com.azu.hospital.patient_expances.request.PatientExpanseUpdate;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class PatientExpanseUpdateServices {
    private final PatientExpanseDao dao;
    private final PatientExpanseDtoMapper expanseDtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    private final PatientExpanseListDao listDao;


    @Autowired
    public PatientExpanseUpdateServices(
            PatientExpanseDao dao, PatientExpanseDtoMapper expanseDtoMapper, ExceptionsMessageReturn messageReturn, PatientExpanseListDao listDao) {
        this.dao = dao;
        this.expanseDtoMapper = expanseDtoMapper;
        this.messageReturn = messageReturn;
        this.listDao = listDao;
    }

    public void updatePatientExpense(Long id, PatientExpanseListUpdate request) {

        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        PatientExpanseList list = listDao.getPatientExpanseById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound") + id
                        )
                );

        for (PatientExpanseUpdate update : request.requestList()) {
            PatientExpanse expanse = dao.getPatientExpanseById(update.getId())
                    .orElseThrow(
                            ()-> new ResourceNotFoundException(
                                    messages.getString("resourceNotFound") + update.getId()
                            )
                    );
            boolean changes = false;
            if (update.getItemName() != null) {
                expanse.setItemName(update.getItemName());
                changes = true;
            }
            if (update.getItemCount() != null) {
                expanse.setItemCount(update.getItemCount());
                changes = true;
            }
            if (update.getNote() != null) {
                expanse.setNote(update.getNote());
                changes = true;
            }
            if (!changes) {
                throw new ResourceNotFoundException(
                        messages.getString("noChanges")
                );
            }
        }
        listDao.updateList(list);
    }
}
