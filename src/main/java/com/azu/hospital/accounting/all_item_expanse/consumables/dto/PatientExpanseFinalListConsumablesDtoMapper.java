package com.azu.hospital.accounting.all_item_expanse.consumables.dto;

import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDto;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.PatientExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.consumables.entity.PatientsExpansesResultTable;
import org.springframework.stereotype.Service;

@Service
public class PatientExpanseFinalListConsumablesDtoMapper implements PatientExpanseFinalList<PatientsExpansesResultTable> {

    @Override
    public ExpanseDto getExpanseDtoList(PatientsExpansesResultTable table) {
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
