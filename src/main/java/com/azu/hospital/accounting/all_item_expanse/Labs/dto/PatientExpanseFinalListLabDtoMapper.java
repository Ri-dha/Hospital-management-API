package com.azu.hospital.accounting.all_item_expanse.Labs.dto;

import com.azu.hospital.accounting.all_item_expanse.Labs.entity.PatientLabExpanseResultTable;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDto;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.PatientExpanseFinalList;
import org.springframework.stereotype.Service;

@Service
public class PatientExpanseFinalListLabDtoMapper implements PatientExpanseFinalList<PatientLabExpanseResultTable> {

    @Override
    public ExpanseDto getExpanseDtoList(PatientLabExpanseResultTable table) {
        return new ExpanseDto(
                table.getId(),
                table.getLabTestId(),
                table.getLabTestName(),
                table.getLabTestCount(),
                table.getLabTestPrice(),
                table.getTotalLabTestPrice()
        );
    }
}
