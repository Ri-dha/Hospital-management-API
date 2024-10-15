package com.azu.hospital.ph.pharmacy_drugs.expance_drugs_in_store.dto;

import com.azu.hospital.ph.pharmacy_drugs.expance_drugs_in_store.entity.DrugExpanseTable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DrugExpanseInStoreDtoMapper implements Function<DrugExpanseTable, DrugExpanseInStoreDto> {
    @Override
    public DrugExpanseInStoreDto apply(DrugExpanseTable drugExpanseTable) {
        return new DrugExpanseInStoreDto(
                drugExpanseTable.getId(),
                drugExpanseTable.getQuantity(),
                drugExpanseTable.getDrugsItem().getId(),
                drugExpanseTable.getDrugsItem().getItemName(),
                drugExpanseTable.getDrugsItem().getDrugScientificName(),
                drugExpanseTable.getDrugsItem().getType(),
                drugExpanseTable.getDrugsItem().getPrice(),
                drugExpanseTable.getDrugsItem().getTypeOfCurrency().getDisplayName(),
                drugExpanseTable.getDrugsItem().getBarcode()
        );
    }
}
