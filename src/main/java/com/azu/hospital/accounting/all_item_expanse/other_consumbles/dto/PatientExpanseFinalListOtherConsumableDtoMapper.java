package com.azu.hospital.accounting.all_item_expanse.other_consumbles.dto;

import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDto;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.PatientExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.entity.PatientOtherConsumablesResultTable;
import org.springframework.stereotype.Service;

@Service
public class PatientExpanseFinalListOtherConsumableDtoMapper implements PatientExpanseFinalList<PatientOtherConsumablesResultTable> {

    @Override
    public ExpanseDto getExpanseDtoList(PatientOtherConsumablesResultTable table) {
        return new ExpanseDto(
                table.getId(),
                table.getItemId(),
                table.getItemName(),
                table.getItemCount(),
                table.getItemPrice(),
                table.getTotalPrice()
        );
    }
}
