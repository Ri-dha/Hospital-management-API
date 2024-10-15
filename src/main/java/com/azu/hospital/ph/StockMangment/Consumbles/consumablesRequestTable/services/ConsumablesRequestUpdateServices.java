package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.services;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.bulding.unit.dao.UnitDao;
import com.azu.hospital.bulding.unit.entity.Unit;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.dao.ConsumablesRequestTableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.entity.ConsumablesRequestTable;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.request.ConsumablesRequestTableUpdateRequest;
import com.azu.hospital.ph.StockMangment.Consumbles.dao.ConsumableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.OtherItemRequest.OtherItemRequestPlaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class ConsumablesRequestUpdateServices {
    private final ConsumablesRequestTableDao dao;
    private final ConsumableDao consumableDao;
    private final WardDao wardDao;
    private final UnitDao unitDao;
    private final BaseUserDao baseUserDao;

    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public ConsumablesRequestUpdateServices(
            @Qualifier("ConsumablesRequestTableJpa") ConsumablesRequestTableDao dao,
            ConsumableDao consumableDao, WardDao wardDao, UnitDao unitDao, BaseUserDao baseUserDao, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.consumableDao = consumableDao;
        this.wardDao = wardDao;
        this.unitDao = unitDao;
        this.baseUserDao = baseUserDao;
        this.messageReturn = messageReturn;
    }

    public void updateRequestConsumable(Long id, ConsumablesRequestTableUpdateRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ConsumablesRequestTable requestTable = dao.getRequestById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );

        boolean changes = false;

        if (request.itemPlace() != null) {
            if (request.itemPlace() == OtherItemRequestPlaces.Ward) {
                Ward ward = wardDao.findWardById(request.placeId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        messages.getString("wardNotFound")
                                )
                        );
                requestTable.setWard(ward);
            } else if (request.itemPlace() == OtherItemRequestPlaces.Unit) {
                Unit unit = unitDao.findUnitById(request.placeId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        messages.getString("unitNotFound")
                                )
                        );
                requestTable.setUnit(unit);
            }
            changes = true;
        }
        if (request.consumableId() != null) {
            Consumables consumables = consumableDao.findConsumableById(request.consumableId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    messages.getString("resourceNotFound")
                            )
                    );
            requestTable.setExistTable(List.of(consumables));//todo Fix this
            changes = true;
        }
        if (request.quantity() != null) {
            requestTable.setQuantity(request.quantity());
            changes = true;

        }
        if (!changes) {
            throw new ResourceNotFoundException(
                    messages.getString("noChanges")
            );

        }
        dao.updateExistingRequest(requestTable);
    }
}
