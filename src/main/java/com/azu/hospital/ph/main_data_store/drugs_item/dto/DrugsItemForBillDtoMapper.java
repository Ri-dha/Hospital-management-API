package com.azu.hospital.ph.main_data_store.drugs_item.dto;

import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class DrugsItemForBillDtoMapper implements Function<DrugsItem, DrugsItemForBillDto> {
    @Override
    public DrugsItemForBillDto apply(DrugsItem drugsItem) {
        return new DrugsItemForBillDto(
                drugsItem.getId(),
                drugsItem.getItemName(),
                drugsItem.getQuantity(),
                drugsItem.getDrugBonus(),
                drugsItem.getPrice()

        );
    }
}
