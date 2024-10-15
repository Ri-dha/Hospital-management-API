package com.azu.hospital.ph.StockMangment.otherConsumablesList.dto;

import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dto.OtherConsumablesDto;

import java.util.List;

public record OtherConsumablesListDto (


        Long listId,
        List<OtherConsumablesDto> otherConsumablesDtoList

){
}
