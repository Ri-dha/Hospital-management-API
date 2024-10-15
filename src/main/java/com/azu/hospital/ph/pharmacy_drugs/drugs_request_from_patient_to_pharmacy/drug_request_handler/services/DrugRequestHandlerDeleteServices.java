package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.dao.DrugRequestHandlerListDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dao.DrugRequestHandlerDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler_deleted_item.dao.DrugRequestHandlerDeletedItemDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler_deleted_item.entity.DrugRequestHandlerDeletedItem;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.entity.DrugRequestHandlerList;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class DrugRequestHandlerDeleteServices {
    private final DrugRequestHandlerDao handlerDao;
    private final DrugRequestHandlerListDao drugRequestHandlerListDao;
    private final DrugRequestHandlerDeletedItemDao dao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public DrugRequestHandlerDeleteServices(DrugRequestHandlerDao handlerDao, DrugRequestHandlerListDao drugRequestHandlerListDao, DrugRequestHandlerDeletedItemDao dao, ExceptionsMessageReturn messageReturn) {
        this.handlerDao = handlerDao;
        this.drugRequestHandlerListDao = drugRequestHandlerListDao;
        this.dao = dao;
        this.messageReturn = messageReturn;
    }
    @Transactional
    public void deleteDrugRequestHandlerById(Long id){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        DrugRequestHandler drugRequestHandler = handlerDao.getRequestById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );

        DrugRequestHandlerDeletedItem deletedItem = new DrugRequestHandlerDeletedItem();
        deletedItem.setDrugId(drugRequestHandler.getDrugId());
        deletedItem.setQuantity(drugRequestHandler.getQuantity());
        deletedItem.setRequestStatus(drugRequestHandler.getRequestStatus());
        deletedItem.setTimesDay(drugRequestHandler.getTimesDay());
        deletedItem.setTimesServing(drugRequestHandler.getTimesServing());
        deletedItem.setDoes(drugRequestHandler.getDoes());
        deletedItem.setRoa(drugRequestHandler.getRoa());
        deletedItem.setType(drugRequestHandler.getType());
        deletedItem.setIsDrugStopped(drugRequestHandler.getIsDrugStopped());
        deletedItem.setIsDrugDeleted(drugRequestHandler.getIsDrugDeleted());
//        deletedItem.setDrugRequestHandlerLists(drugRequestHandler.getDrugRequestHandlerListsALL());
//        deletedItem.setPatient(drugRequestHandler.getPatient());
        dao.addDrugRequestHandlerDeleteItem(deletedItem);

        handlerDao.deleteDrugRequestHandlerById(id);

    }
}
