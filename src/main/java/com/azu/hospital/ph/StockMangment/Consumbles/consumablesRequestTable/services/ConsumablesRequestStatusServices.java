package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.dao.ConsumablesRequestTableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.entity.ConsumablesRequestTable;
import com.azu.hospital.ph.StockMangment.Consumbles.dao.ConsumableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.requests.ItemRejectStateRequest;
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
public class ConsumablesRequestStatusServices {

    private final ConsumablesRequestTableDao dao;
    private final ConsumableDao consumableDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public ConsumablesRequestStatusServices(
            @Qualifier("ConsumablesRequestTableJpa") ConsumablesRequestTableDao dao,
            ConsumableDao consumableDao, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.consumableDao = consumableDao;
        this.messageReturn = messageReturn;
    }

    @Transactional
    public ObjectNode acceptOrRejectRequest(Long requestId, ItemRejectStateRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        ConsumablesRequestTable requestTable = consumablesRequestTableCheckById(requestId);

        Long conId = getConsumableIdFromConsumableRequestTable(requestTable);

        Consumables existsTable = getConsumablesExistTableId(conId);
        if (existsTable.getQuantity() == 0) {
            throw new ResourceNotFoundException(
                    messages.getString("resourceNotFound")
            );
        }

        if (requestTable.getRequestStatus() == UnitInventoryRequestEnum.Waitting) {

            if (request.status().equals("Accepted") || request.status().equals("accepted")
            ) {
                requestTable.setRequestStatus(UnitInventoryRequestEnum.Accepted);
                dao.updateExistingRequest(requestTable);
                return json.put("status"," Request Accepted");

            } else {
                requestTable.setRequestStatus(UnitInventoryRequestEnum.Rejected);
                requestTable.setRejectCause(request.cause());
                json.put("status"," Request Rejected");
                json.put("because", request.cause());
                dao.updateExistingRequest(requestTable);
                return json;
            }

        } else {
            return json.put("status"," Request is Already Canceled");
        }


        // TODO: 10/11/2023 send notifications to the user request for notify him about the request status
        // TODO: 10/11/2023 you should return a cause of reject to now users why rejected  and save it in tables

    }

     Long getConsumableIdFromConsumableRequestTable(ConsumablesRequestTable requestTable) {
        return requestTable.getExistTable()
                .stream()
                .map(Consumables::getConsumableId)
                .findFirst()
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "No such Consumable in the Table fo this item"
                        )
                );
    }

    // TODO: 10/10/2023 get by place, get by device type



    public ObjectNode cancelRequest(Long requestId, String status) {
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        ConsumablesRequestTable request = consumablesRequestTableCheckById(requestId);
        if ((request.getRequestStatus() != UnitInventoryRequestEnum.Waitting
        )
                && status.equalsIgnoreCase("Cancel")) {
            request.setRequestStatus(UnitInventoryRequestEnum.Cancel);
            dao.updateExistingRequest(request);
            json.put("status", "Request Cancel");
        } else {
            json.put("status", "The status of Request Is already Change, You can't Cancel it");
        }
        return json;
    }

    @Transactional
    public ObjectNode receivedOrNotReceivedTheItemRequest(Long requestId, String status) {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        ConsumablesRequestTable request = consumablesRequestTableCheckById(requestId);

        if (
                request.getRequestStatus() != UnitInventoryRequestEnum.Waitting
                && status.equals("Received") || status.equals("received")
        ){
            request.setRequestStatus(UnitInventoryRequestEnum.Received);
            dao.updateExistingRequest(request);
            json.put("status", "Request Received");
        } else {
            request.setRequestStatus(UnitInventoryRequestEnum.Not_Received);
            dao.updateExistingRequest(request);
            json.put("status", "Request Not Received");
        }
        return json;
    }

     Consumables getConsumablesExistTableId(Long itemId) {
         Locale locale = messageReturn.getMessageLocally();
         ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        return consumableDao.findConsumableById(itemId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound") + itemId
                        )
                );
    }


    ConsumablesRequestTable consumablesRequestTableCheckById(Long requestId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return dao.getRequestById(requestId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound") + requestId
                        )

                );
    }

}
