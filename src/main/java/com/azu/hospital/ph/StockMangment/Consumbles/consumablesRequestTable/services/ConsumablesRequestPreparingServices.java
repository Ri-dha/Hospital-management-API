package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.services;

import com.azu.hospital.bulding.unit.entity.Unit;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.dao.ConsumableExpanseTableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.entity.ConsumableExpanseTable;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.dao.ConsumablesRequestTableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.entity.ConsumablesRequestTable;
import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class ConsumablesRequestPreparingServices {

    private final ConsumablesRequestTableDao dao;
    private final ConsumablesRequestStatusServices statusServices;
    private final ConsumableExpanseTableDao expanseTableDao;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public ConsumablesRequestPreparingServices(
            @Qualifier("ConsumablesRequestTableJpa") ConsumablesRequestTableDao dao,
            ConsumablesRequestStatusServices statusServices,
            @Qualifier("ConsumableExpanseTableJpa") ConsumableExpanseTableDao expanseTableDao, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.statusServices = statusServices;
        this.expanseTableDao = expanseTableDao;
        this.messageReturn = messageReturn;
    }

    @Transactional
    public ObjectNode preparingTheItemRequest(Long requestId, String status) {

        ObjectNode json = JsonNodeFactory.instance.objectNode();
        ConsumablesRequestTable requestTable = statusServices.consumablesRequestTableCheckById(requestId);

        Long conId = statusServices.getConsumableIdFromConsumableRequestTable(requestTable);

        Consumables existsTable = statusServices.getConsumablesExistTableId(conId);
        existsTable.setQuantity(requestTable.getQuantity() - existsTable.getQuantity());
        changesOnExpanseTables(requestId);
        if (requestTable.getRequestStatus() != UnitInventoryRequestEnum.Waitting) {
            if (requestTable.getRequestStatus() != UnitInventoryRequestEnum.Waitting
            ) {
                if (status.equals("Preparing") || status.equals("preparing")) {
                    requestTable.setRequestStatus(UnitInventoryRequestEnum.Preparing);
                    dao.updateExistingRequest(requestTable);
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
        return json ;
    }


    private void changesOnExpanseTables(Long requestId) {
        ConsumablesRequestTable requestTable = statusServices.consumablesRequestTableCheckById(requestId);
        Long conId = statusServices.getConsumableIdFromConsumableRequestTable(requestTable);
        Consumables existsTable = statusServices.getConsumablesExistTableId(conId);
        Long expanseId = getConsumableInExpanseTableIdFromConsumableRequestTable(requestTable);

        ConsumableExpanseTable expanseTable = expanseTableDao.getConsumablesById(expanseId)
                .map(existingExpanseTable -> {
                    existingExpanseTable.setQuantity(requestTable.getQuantity() + existingExpanseTable.getQuantity());
                    if (requestTable.getWard() != null){
                        Ward ward = requestTable.getWard();
                        requestTable.setWard(ward);
                    }else{
                        Unit unit = requestTable.getUnit();
                        requestTable.setUnit(unit);
                    }

                    expanseTableDao.updateConsumables(existingExpanseTable);
                    return existingExpanseTable;
                })
                .orElseGet(() -> {
                    ConsumableExpanseTable newExpanseTable = new ConsumableExpanseTable();
                    newExpanseTable.setQuantity(requestTable.getQuantity());
                    if (requestTable.getWard() != null){
                        Ward ward = requestTable.getWard();
                        requestTable.setWard(ward);
                    }else{
                        Unit unit = requestTable.getUnit();
                        requestTable.setUnit(unit);
                    }
                    newExpanseTable.setMainsConsumables(existsTable);
                    existsTable.setExistsTable(newExpanseTable);
                    expanseTableDao.addConsumables(newExpanseTable);
                    return newExpanseTable;
                });
    }


    Long getConsumableInExpanseTableIdFromConsumableRequestTable(ConsumablesRequestTable requestTable) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return requestTable.getExpanseTables()
                .stream()
                .map(ConsumableExpanseTable::getId)
                .findFirst()
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );
    }

    @Transactional
    public ObjectNode doneTheItemRequest(Long requestId, String status) {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        ConsumablesRequestTable requestTable = statusServices.consumablesRequestTableCheckById(requestId);
        if (requestTable.getRequestStatus() != UnitInventoryRequestEnum.Waitting) {

                if (status.equals("Done") || status.equals("done")) {
                    requestTable.setRequestStatus(UnitInventoryRequestEnum.Done);
                    dao.updateExistingRequest(requestTable);
                    json.put("status"," Request Done" );
                } else {
                    json.put("status"," Wrong status" + status + "You send" );
                }
            } else {
                json.put("status"," Request Is already rejected" );
            }

        return json ;
    }




}
