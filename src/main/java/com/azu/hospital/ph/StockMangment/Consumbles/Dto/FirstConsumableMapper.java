package com.azu.hospital.ph.StockMangment.Consumbles.Dto;

import com.azu.hospital.ph.StockMangment.Consumbles.entity.Consumables;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FirstConsumableMapper implements Function<Consumables, ConsumableDto> {
    @Override
    public ConsumableDto apply(Consumables consumables) {
        return new ConsumableDto(
                consumables.getConsumableId(),
                consumables.getConsumableName(),
                consumables.getConsumableCompany(),
                consumables.getQuantity(),
                consumables.getPrice(),
                consumables.getConsumableSellingPrice(),
                consumables.getBarcode(),
                consumables.getExpDate(),
                consumables.getProDate(),
                consumables.getTypeOfCurrency(),
                consumables.getExchange(),
                consumables.getDescription(),
                consumables.getBounce(),
                consumables.getConsumableImage() == null ? null : consumables.getConsumableImage(),
                consumables.getConsumablePharmacyPlace(),
                consumables.getBill().getBillId(),
                consumables.getBill().getSupplierDetails(),
                consumables.getUpdateAt()
        );
    }
}
