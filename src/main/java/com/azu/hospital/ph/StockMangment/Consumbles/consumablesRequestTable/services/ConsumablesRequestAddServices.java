package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.services;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.bulding.unit.dao.UnitDao;
import com.azu.hospital.bulding.unit.entity.Unit;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.dao.ConsumableExpanseTableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.ConsumablesRequestTableList;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.dao.ConsumablesRequestTableListDao;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.dao.ConsumablesRequestTableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.entity.ConsumablesRequestTable;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.request.ConsumablesRequestTableRequest;
import com.azu.hospital.ph.StockMangment.Consumbles.dao.ConsumableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.OtherItemRequest.OtherItemRequestPlaces;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class ConsumablesRequestAddServices {
    private final ConsumablesRequestTableDao dao;
    private final BaseUserDao baseUserDao;
    private final WardDao wardDao;
    private final UnitDao unitDao;
    private final ConsumableExpanseTableDao expanseTableDao;
    private final ConsumableDao consumableDao;

    private final ConsumablesRequestTableListDao consumablesRequestTableListDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public ConsumablesRequestAddServices(
            @Qualifier("ConsumablesRequestTableJpa") ConsumablesRequestTableDao dao,
            @Qualifier("BaseUserJpa") BaseUserDao baseUserDao,
            @Qualifier("wardRepo") WardDao wardDao,
            @Qualifier("unitRepo") UnitDao unitDao,
            ConsumableExpanseTableDao expanseTableDao, ConsumableDao consumableDao, ConsumablesRequestTableListDao consumablesRequestTableListDao,

            ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.baseUserDao = baseUserDao;
        this.wardDao = wardDao;
        this.unitDao = unitDao;
        this.expanseTableDao = expanseTableDao;
        this.consumableDao = consumableDao;
        this.consumablesRequestTableListDao = consumablesRequestTableListDao;
        this.messageReturn = messageReturn;
    }


    @Transactional
    public void addNewRequests(List<ConsumablesRequestTableRequest> requests,String note) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        List<ConsumablesRequestTable> requestTables = new ArrayList<>();
        ConsumablesRequestTableList requestTableList = new ConsumablesRequestTableList();
        requestTableList.setRequestListNote(
                note
        );
        Integer totalQuantity = 0;
        for (ConsumablesRequestTableRequest request : requests) {
            totalQuantity += request.quantity();
            Consumables consumable = consumableDao.findConsumableById(request.consumableId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    messages.getString("resourceNotFound") + request.consumableId()
                            )
                    );

            ConsumablesRequestTable requestTable = new ConsumablesRequestTable();
            requestTable.setExpanseTables(expanseTableDao.findByMainsConsumables(request.consumableId()));
            requestTable.setExistTable(consumableDao.getConsumablesByBillId(request.consumableId()));
            if (consumable.getQuantity() < request.quantity()) {
                throw new ResourceNotFoundException(
                        messages.getString("quantityNotAvailable") + consumable.getQuantity()
                );
            } else {
                consumable.setQuantity(consumable.getQuantity() - request.quantity());
            }
            requestTable.setQuantity(request.quantity());
            requestTable.setRequestStatus(UnitInventoryRequestEnum.Waitting);

            if (request.itemPlace().equals(OtherItemRequestPlaces.Ward)) {
                Ward ward = wardDao.findWardById(request.placeId()).orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("wardNotFound") + request.placeId()
                        )
                );
                requestTableList.setWard(ward);
                requestTable.setWard(ward);
            } else {
                Unit unit = unitDao.findUnitById(request.placeId()).orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("unitNotFound") + request.placeId()
                        )
                );
                requestTableList.setUnit(unit);
                requestTable.setUnit(unit);
            }

            requestTables.add(requestTable);
        }
        requestTableList.setConsumablesRequestTables(requestTables);
        requestTableList.setRequestListQuantity(totalQuantity);

        consumablesRequestTableListDao.createConsumablesRequestTableList(requestTableList);
        dao.addNewRequests(requestTables);
    }


}
