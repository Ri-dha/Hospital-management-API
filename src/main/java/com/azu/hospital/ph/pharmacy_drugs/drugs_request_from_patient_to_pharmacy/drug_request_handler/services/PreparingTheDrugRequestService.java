package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.main_data_store.drugs_item.dao.DrugItemDao;
import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dao.DrugRequestHandlerDao;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import com.azu.hospital.ph.pharmacy_drugs.expance_drugs_in_store.dao.DrugExpanseInStoreDao;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class PreparingTheDrugRequestService {


    private final DrugRequestHandlerDao handlerDao;
    private final DrugItemDao existDao;
    private final DrugExpanseInStoreDao expanseDao;
    private final ExceptionsMessageReturn messageReturn;

    public PreparingTheDrugRequestService(
            @Qualifier("DrugRequestHandlerJpa")DrugRequestHandlerDao handlerDao,
            @Qualifier("DrugItemJpa") DrugItemDao existDao,
            @Qualifier("DrugExpanseInStoreJpa") DrugExpanseInStoreDao expanseDao, ExceptionsMessageReturn messageReturn
    ) {
        this.handlerDao = handlerDao;
        this.existDao = existDao;
        this.expanseDao = expanseDao;
        this.messageReturn = messageReturn;
    }



    @Transactional
    public ObjectNode preparingTheDrugRequest(Long requestId, String status) {

        ObjectNode json = JsonNodeFactory.instance.objectNode();
        DrugRequestHandler existAskingRequest = drugAskingRequestCheckById(requestId);
        DrugsItem existsTable = getDrugExistsTableId(existAskingRequest.getDrugId());
        existsTable.setQuantity(existAskingRequest.getQuantity() - existsTable.getQuantity());
//        changesOnExpanseTables(requestId);


        if (existAskingRequest.getRequestStatus() != UnitInventoryRequestEnum.Cancel) {
            if (existAskingRequest.getRequestStatus() != UnitInventoryRequestEnum.Rejected
            ) {
                if (status.equals("Preparing") || status.equals("preparing")) {
                    existAskingRequest.setRequestStatus(UnitInventoryRequestEnum.Preparing);
                    json.put("status"," Request Preparing" );
                } else {
                    json.put("status"," Wrong status" + status + "You send" );
                }
            } else {
                json.put("status"," Request Is already rejected" );
            }
        } else {
            json.put("status"," Request Is already Canceled" );
        }


        // TODO: 11/6/2023 Must Send Request To Add One Of Item belong to DeviceType on other items
        return json ;
    }


    private DrugRequestHandler drugAskingRequestCheckById(Long requestId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        return handlerDao.getRequestById(requestId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )

                );
    }


//    private void changesOnExpanseTables(Long requestId) {
//        DrugRequestHandler request = drugAskingRequestCheckById(requestId);
//        DrugsItem existsTable = getDrugExistsTableId(request.getDrugId());
//
//        DrugExpanseTable expanseTable = expanseDao.getExpanseInStoreTableByDrugIdOfMainTable(request.getDrugId())
//                .map(existingExpanseTable -> {
//                    Integer existingQuantity = existingExpanseTable.getQuantity();
//                    int newQuantity = request.getQuantity() + (existingQuantity != null ? existingQuantity.intValue() : 0);
//                    existingExpanseTable.setQuantity(newQuantity);
//                    expanseDao.updateDrugsInExpanseStore(existingExpanseTable);
//                    return existingExpanseTable;
//                })
//                .orElseGet(() -> {
//                    DrugExpanseTable newExpanseTable = new DrugExpanseTable();
//                    newExpanseTable.setQuantity(request.getQuantity());
//                    newExpanseTable.setDrugsItem(existsTable);
//                    expanseDao.addDrugsToExpanseInStore(newExpanseTable);
//                    return newExpanseTable;
//                });
//    }


    private DrugsItem getDrugExistsTableId(Long drugId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        return existDao.selectDrudById(drugId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );
    }


}
