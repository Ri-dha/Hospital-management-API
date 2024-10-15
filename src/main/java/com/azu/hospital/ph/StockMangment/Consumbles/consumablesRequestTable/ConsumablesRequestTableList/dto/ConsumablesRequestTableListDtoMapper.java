package com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.dto;

import com.azu.hospital.all_user_sevices.user_utils.GetUserNameForAuditing;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.ConsumablesRequestTableList.ConsumablesRequestTableList;
import com.azu.hospital.ph.StockMangment.Consumbles.consumablesRequestTable.dto.ConsumablesRequestTableDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ConsumablesRequestTableListDtoMapper implements Function<ConsumablesRequestTableList, ConsumablesRequestTableListDto>{

    private final ConsumablesRequestTableDtoMapper consumablesRequestTableDtoMapper;

    private final GetUserNameForAuditing getBaseUserName;



    @Autowired
    public ConsumablesRequestTableListDtoMapper(ConsumablesRequestTableDtoMapper consumablesRequestTableDtoMapper, GetUserNameForAuditing getBaseUserName) {
        this.consumablesRequestTableDtoMapper = consumablesRequestTableDtoMapper;
        this.getBaseUserName = getBaseUserName;
    }


    @Override
    public ConsumablesRequestTableListDto apply(ConsumablesRequestTableList consumablesRequestTableList) {
        String userCreatedAtName = null;
        String userLastModifiedByName = null;
        if (consumablesRequestTableList.getCreatedBy() != null){
             userCreatedAtName = getBaseUserName.getProviderName(consumablesRequestTableList.getCreatedBy());

        }
        if (consumablesRequestTableList.getLastModifiedBy() != null){
            userLastModifiedByName = getBaseUserName.getProviderName(consumablesRequestTableList.getLastModifiedBy());

        }
        return new ConsumablesRequestTableListDto(
                consumablesRequestTableList.getRequestListId(),
                consumablesRequestTableList.getRequestListNote(),
                consumablesRequestTableList.getRequestListQuantity(),
                consumablesRequestTableList.getConsumablesRequestTables().stream()
                        .map(consumablesRequestTableDtoMapper).toList(),
                consumablesRequestTableList.getCreateAt(),
                consumablesRequestTableList.getUpdateAt(),
                consumablesRequestTableList.getCreatedBy(),
                userCreatedAtName,
                consumablesRequestTableList.getLastModifiedBy(),
                userLastModifiedByName,
                consumablesRequestTableList.getRequestListState()
        );
    }





}
