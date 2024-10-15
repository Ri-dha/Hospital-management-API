package com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.services;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.bulding.unit.dao.UnitDao;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.dao.DeviceExpanseDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.entity.DeviceExpanseTable;
import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.dao.OtherItemAskingRequestDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.entity.OtherItemAskingRequest;
import com.azu.hospital.ph.StockMangment.addOtherItems.itemRquestHandle.requests.ItemRejectStateRequest;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.dao.DeviceExistDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.entity.DeviceExistsTable;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.OtherItemRequest.OtherItemRequestPlaces;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class OtherItemAskingRequestService {

    private final OtherItemAskingRequestDao dao;
    private final DeviceExistDao existDao;
    private final WardDao wardDao;
    private final UnitDao unitDao;
    private final DeviceExpanseDao expanseDao;
    private final BaseUserDao baseUserDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public OtherItemAskingRequestService(
            @Qualifier("AskingRequest") OtherItemAskingRequestDao dao,
            @Qualifier("DeviceExistJpa") DeviceExistDao existDao,
            @Qualifier("wardRepo") WardDao wardDao,
            @Qualifier("unitRepo") UnitDao unitDao,
            @Qualifier("DeviceExpanseJpa") DeviceExpanseDao expanseDao,
            @Qualifier("BaseUserJpa") BaseUserDao baseUserDao,
            ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.existDao = existDao;
        this.wardDao = wardDao;
        this.unitDao = unitDao;
        this.expanseDao = expanseDao;
        this.baseUserDao = baseUserDao;
        this.messageReturn = messageReturn;
    }


    @Transactional
    public void addNewRequest(
            @RequestParam  Long itemId,
                              Long userId,
                              Integer quantity,
           @RequestParam(required = false) OtherItemRequestPlaces itemPlace,
            Long placeId ,
            String note
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        BaseUser user = baseUserDao.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("userNotFound") + userId
                        )
                );
        
        if (itemPlace.equals(OtherItemRequestPlaces.Ward)){
            wardDao.findWardById(placeId).orElseThrow(
                    ()-> new ResourceNotFoundException(
                            messages.getString("wardNotFound") + placeId
                    )
            );
        }else{
            unitDao.findUnitById(placeId).orElseThrow(
                    ()-> new ResourceNotFoundException(
                            messages.getString("unitNotFound") + placeId
                    )
            );
        }

        OtherItemAskingRequest askingRequest = new OtherItemAskingRequest();
        askingRequest.setItemId(itemId);
        askingRequest.setQuantity(quantity);
        askingRequest.setDevicePlace(itemPlace);
        askingRequest.setRequestStatus(UnitInventoryRequestEnum.Waitting);
        askingRequest.setSignature(user);
        askingRequest.setPlaceId(placeId);
        askingRequest.setNote(note);
        dao.addNewRequestItem(askingRequest);
    }


    @Transactional
    public ObjectNode acceptOrRejectRequest(Long requestId, ItemRejectStateRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        OtherItemAskingRequest existAskingRequest = otherItemAskingRequestCheckById(requestId);

        DeviceExistsTable existsTable = getDeviceExistsTableId(existAskingRequest.getItemId());
        if (!existsTable.getIsAvailable()) {
            throw new ResourceNotFoundException(
                    messages.getString("resourceNotFound")
            );
        }

        if (existAskingRequest.getRequestStatus() != UnitInventoryRequestEnum.Cancel) {

            if (request.status().equals("Accepted") || request.status().equals("accepted")
            ) {
                existAskingRequest.setRequestStatus(UnitInventoryRequestEnum.Accepted);
                return json.put("status"," Request Accepted");

            } else {
                existAskingRequest.setRequestStatus(UnitInventoryRequestEnum.Rejected);
                existAskingRequest.setRejectCause(request.cause());
                json.put("status"," Request Rejected");
                json.put("because", request.cause());
                return json;
            }

        } else {
            return json.put("status"," Request is Already Canceled");
        }


        // TODO: 10/11/2023 send notifications to the user request for notify him about the request status
        // TODO: 10/11/2023 you should return a cause of reject to now users why rejected  and save it in tables

    }

    // TODO: 10/10/2023 get by place, get by device type

    @Transactional
    public ObjectNode preparingTheItemRequest(Long requestId, String status) {

        ObjectNode json = JsonNodeFactory.instance.objectNode();
        OtherItemAskingRequest existAskingRequest = otherItemAskingRequestCheckById(requestId);
        DeviceExistsTable existsTable = getDeviceExistsTableId(existAskingRequest.getItemId());
        existsTable.setQuantity(existAskingRequest.getQuantity() - existsTable.getQuantity());
        changesOnExpanseTables(requestId);
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

    public ObjectNode cancelRequest(Long requestId, String status) {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        OtherItemAskingRequest request = otherItemAskingRequestCheckById(requestId);
        if (request.getRequestStatus() == UnitInventoryRequestEnum.Waitting
                || request.getRequestStatus() == UnitInventoryRequestEnum.Accepted
                && status.equals("Cancel") || status.equals("cancel")
        ) {
            request.setRequestStatus(UnitInventoryRequestEnum.Cancel);
            json.put("status", "Request Cancel");
        } else {
            json.put("status", "The status of Request Is already Change, You can't Cancel it");
        }
        return json;
    }

    @Transactional
    public ObjectNode receivedOrNotReceivedTheItemRequest(Long requestId, String status) {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        OtherItemAskingRequest request = otherItemAskingRequestCheckById(requestId);

        if (request.getRequestStatus() == UnitInventoryRequestEnum.Preparing
                && status.equals("Received") || status.equals("received")
        ) {
            request.setRequestStatus(UnitInventoryRequestEnum.Received);
            json.put("status", "Request Received");
        } else {
            request.setRequestStatus(UnitInventoryRequestEnum.Not_Received);
            json.put("status", "Request Not Received");
        }
        return json;
    }

    private DeviceExistsTable getDeviceExistsTableId(Long itemId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return existDao.getExistDeviceByDeviceId(itemId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound") + itemId
                        )
                );
    }


    private OtherItemAskingRequest otherItemAskingRequestCheckById(Long requestId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        return dao.getRequestById(requestId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound") + requestId
                        )

                );
    }

    private void changesOnExpanseTables(Long requestId) {
        OtherItemAskingRequest request = otherItemAskingRequestCheckById(requestId);
        DeviceExistsTable existsTable = getDeviceExistsTableId(request.getItemId());

        DeviceExpanseTable expanseTable = expanseDao.getExpanseItemByItemId(request.getItemId())
                .map(existingExpanseTable -> {
                    existingExpanseTable.setQuantity(request.getQuantity() + existingExpanseTable.getQuantity());
                    existingExpanseTable.setDevicePlace(request.getDevicePlace());

                    expanseDao.updateDeviceExpanseTable(existingExpanseTable);
                    return existingExpanseTable;
                })
                .orElseGet(() -> {
                    DeviceExpanseTable newExpanseTable = new DeviceExpanseTable();
                    newExpanseTable.setQuantity(request.getQuantity());
                    newExpanseTable.setDevicePlace(request.getDevicePlace());
                    newExpanseTable.setDeviceExistsTable(existsTable);
                    existsTable.setExpanseTable(newExpanseTable);
                    expanseDao.addNewDeviceExpanseTable(newExpanseTable);
                    return newExpanseTable;
                });
    }
}
