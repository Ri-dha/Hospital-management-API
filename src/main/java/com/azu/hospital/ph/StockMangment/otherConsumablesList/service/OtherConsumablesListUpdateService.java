package com.azu.hospital.ph.StockMangment.otherConsumablesList.service;


import com.azu.hospital.accounting.all_item_expanse.other_consumbles.dao.PatientOtherConsumablesResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.service.IPatientOtherConsumablesResultTableService;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.dao.OtherConsumablesListDao;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.entity.OtherConsumablesList;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dao.OtherConsumablesDao;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.entity.OtherConsumables;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.request.OtherConsumablesRequest;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.request.OtherConsumablesListUpdateRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class OtherConsumablesListUpdateService {
    private final OtherConsumablesListDao dao;
    private final OtherConsumablesDao otherConsumablesDao;

    private final PatientOtherConsumablesResultTableDao patientOtherConsumablesResultTableDao;
    private final IPatientOtherConsumablesResultTableService iPatientOtherConsumablesResultTableService;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public OtherConsumablesListUpdateService(OtherConsumablesListDao dao, OtherConsumablesDao otherConsumablesDao, PatientOtherConsumablesResultTableDao patientOtherConsumablesResultTableDao, IPatientOtherConsumablesResultTableService iPatientOtherConsumablesResultTableService, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.otherConsumablesDao = otherConsumablesDao;
        this.patientOtherConsumablesResultTableDao = patientOtherConsumablesResultTableDao;
        this.iPatientOtherConsumablesResultTableService = iPatientOtherConsumablesResultTableService;
        this.messageReturn = messageReturn;
    }

    public void updateOtherConsumablesListUpdate(Long listId, OtherConsumablesListUpdateRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());

        OtherConsumablesList list = dao.getOtherConsumablesList(listId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound") + " " + listId
                        )
                );

        for(OtherConsumablesRequest update : request.requestList()){
            OtherConsumables otherConsumables = otherConsumablesDao.getOtherConsumablesById(update.id())
                    .orElseThrow(
                            ()-> new ResourceNotFoundException(
                                    messages.getString("resourceNotFound") + update.id()
                            )
                    );
            boolean changes = false;
            if (update.count() != null){
                otherConsumables.setCount(update.count());
                changes = true;
            }if (update.name() != null){
                otherConsumables.setName(update.name());
                changes = true;
            }if (update.note() != null){
                otherConsumables.setNote(update.note());
                changes = true;
            }if (update.price() != null){
                otherConsumables.setPrice(update.price());
                changes = true;

            }

            if(!changes){
                throw new BadRequestException(
                        messages.getString("noChanges")
                );
            }
            iPatientOtherConsumablesResultTableService.update(otherConsumables.getId(),otherConsumables.getPatient().getId(), otherConsumables.getPrice(), otherConsumables.getCount(), otherConsumables.getName());

        }
        dao.updateOtherConsumablesList(list);

    }

}
