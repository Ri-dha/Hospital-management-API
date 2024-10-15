package com.azu.hospital.accounting.all_item_expanse.opreation.dto;

import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDto;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.PatientExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.opreation.entity.PatientOperationExpanseResultTable;
import org.springframework.stereotype.Service;

@Service
public class PatientExpanseFinalListOperationDtoMapper implements PatientExpanseFinalList<PatientOperationExpanseResultTable> {

    @Override
    public ExpanseDto getExpanseDtoList(PatientOperationExpanseResultTable table) {
        return new ExpanseDto(
                table.getId(),
                table.getOperationId(),
                table.getOperationName(),
                table.getOperationCount(),
                table.getOperationPrice(),
                table.getTotalOperationPrice()
        );
    }
}
