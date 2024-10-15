package com.azu.hospital.ph.StockMangment.otherConsumablesList.dto;


import com.azu.hospital.ph.StockMangment.otherConsumablesList.entity.OtherConsumablesList;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dto.OtherConsumablesDtoMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OtherConsumablesListDtoMapper implements Function<OtherConsumablesList, OtherConsumablesListDto> {
    private final OtherConsumablesDtoMapper otherConsumablesDtoMapper;

    public OtherConsumablesListDtoMapper(OtherConsumablesDtoMapper otherConsumablesDtoMapper) {
        this.otherConsumablesDtoMapper = otherConsumablesDtoMapper;
    }


    @Override
    public OtherConsumablesListDto apply(OtherConsumablesList otherConsumablesList) {
        return new OtherConsumablesListDto(
                otherConsumablesList.getListId(),
                otherConsumablesList.getOtherConsumables()
                        .stream()
                        .map(otherConsumablesDtoMapper)
                        .toList()

        );
    }
}
