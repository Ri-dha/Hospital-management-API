package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.service;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.ConsumablesRequestTableList;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.dao.ConsumablesRequestTableListDao;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.dto.ConsumablesRequestTableListDto;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.dto.ConsumablesRequestTableListDtoMapper;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.dao.ConsumablesRequestTableDao;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.entity.ConsumablesRequestTable;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumablesRequestTableListService {

    private final ConsumablesRequestTableListDao consumablesRequestTableListDao;

    private final ConsumablesRequestTableDao consumablesRequestTableDao;
    private final ConsumablesRequestTableListDtoMapper listDtoMapper;

    @Autowired
    public ConsumablesRequestTableListService(ConsumablesRequestTableListDao consumablesRequestTableListDao, ConsumablesRequestTableDao consumablesRequestTableDao, ConsumablesRequestTableListDtoMapper listDtoMapper) {
        this.consumablesRequestTableListDao = consumablesRequestTableListDao;
        this.consumablesRequestTableDao = consumablesRequestTableDao;
        this.listDtoMapper = listDtoMapper;
    }


    public ObjectNode acceptOrRejectRequest(Long listId, List<ConsumablesRequestTableListStatusRequest> requests) {
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        ConsumablesRequestTableList tableList = consumablesRequestTableListDao.getConsumablesRequestTableListById(listId)
                .orElseThrow(() -> new ResourceNotFoundException("Consumables Request Table List not found"));

        for (ConsumablesRequestTableListStatusRequest request : requests) {
            ConsumablesRequestTable consumablesRequestTable = consumablesRequestTableDao.getRequestById(request.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Consumables Request Table not found"));

            if (consumablesRequestTable != null) {
                if (consumablesRequestTable.getRequestStatus() == UnitInventoryRequestEnum.Cancel) {
                    json.put("message", "Request is already canceled");
                    return json;
                }

                if (consumablesRequestTable.getRequestId().equals(request.getId())
                ) {
                    if (consumablesRequestTable.getRequestStatus() == UnitInventoryRequestEnum.Waitting){
                        consumablesRequestTable.setRequestStatus(UnitInventoryRequestEnum.Accepted);
                        consumablesRequestTableDao.updateExistingRequest(consumablesRequestTable);
                        tableList.setRequestListState(UnitInventoryRequestEnum.Accepted);
                        consumablesRequestTableListDao.updateConsumablesRequestTableList(tableList);
                        json.put("message", "Request is accepted");
                    }else {
                        json.put("message", "Request is not in Waitting state");
                    }
                }
            }
        }
        return json;
    }


    public Page <ConsumablesRequestTableListDto> getAllByWardOrUnit(Long wardId, Long unitId, Pageable pageable){
        return consumablesRequestTableListDao.getAllByWardOrUnit(wardId, unitId, pageable).map(listDtoMapper);
    }

}
