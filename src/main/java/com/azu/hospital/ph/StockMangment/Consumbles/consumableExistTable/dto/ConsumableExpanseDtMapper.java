package com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.dto;

import com.azu.hospital.ph.StockMangment.Consumbles.Dto.ConsumableDto;
import com.azu.hospital.ph.StockMangment.Consumbles.consumableExistTable.entity.ConsumableExpanseTable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ConsumableExpanseDtMapper implements Function<ConsumableExpanseTable, ConsumableExpanseDto> {
    @Override
    public ConsumableExpanseDto apply(ConsumableExpanseTable expanseTable) {
        return new ConsumableExpanseDto(
                expanseTable.getId(),
                expanseTable.getMainsConsumables().getConsumableId(),
                expanseTable.getMainsConsumables().getConsumableName(),
                expanseTable.getMainsConsumables().getConsumableCompany(),
                expanseTable.getQuantity(),
                expanseTable.getMainsConsumables().getPrice(),
                expanseTable.getMainsConsumables().getConsumableSellingPrice(),
                expanseTable.getMainsConsumables().getBarcode(),
                expanseTable.getMainsConsumables().getExpDate(),
                expanseTable.getMainsConsumables().getProDate(),
                expanseTable.getMainsConsumables().getTypeOfCurrency(),
                expanseTable.getMainsConsumables().getExchange(),
                expanseTable.getMainsConsumables().getDescription(),
                expanseTable.getMainsConsumables().getConsumableImage() == null ? null : expanseTable.getMainsConsumables().getConsumableImage(),
                expanseTable.getConsumablePlace(),
                expanseTable.getMainsConsumables().getBill().getBillId(),
                expanseTable.getMainsConsumables().getBill().getSupplierDetails(),
                expanseTable.getCreateAt(),
                expanseTable.getUpdateAt()
        );
    }
}
