package com.azu.hospital.ph.StockMangment.Consumbles.Dto;

import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SecondConsumableMapper implements Function<Consumables, ConsumableForBillDto> {


    @Override
    public ConsumableForBillDto apply(Consumables consumables) {
        return new ConsumableForBillDto(
                consumables.getConsumableId(),
                consumables.getConsumableName(),
                consumables.getQuantity(),
                consumables.getPrice(),
                consumables.getBounce()
        );
    }
}
