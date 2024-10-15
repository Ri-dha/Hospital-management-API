package com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dto;

import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.entity.OtherConsumables;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OtherConsumablesDtoMapper implements Function<OtherConsumables, OtherConsumablesDto> {
    @Override
    public OtherConsumablesDto apply(OtherConsumables otherConsumables) {
        return new OtherConsumablesDto(
                otherConsumables.getId(),
                otherConsumables.getName(),
                otherConsumables.getCount(),
                otherConsumables.getNote(),
                otherConsumables.getPrice()
        );
    }
}
