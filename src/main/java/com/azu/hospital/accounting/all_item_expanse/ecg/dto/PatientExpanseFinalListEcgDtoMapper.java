package com.azu.hospital.accounting.all_item_expanse.ecg.dto;

import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDto;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.PatientExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.ecg.entity.PatientEcgExpanseResultTable;
import org.springframework.stereotype.Service;

@Service
public class PatientExpanseFinalListEcgDtoMapper implements PatientExpanseFinalList<PatientEcgExpanseResultTable> {

    @Override
    public ExpanseDto getExpanseDtoList(PatientEcgExpanseResultTable table) {
        return new ExpanseDto(
                table.getId(),
                table.getEcgId(),
                table.getEcgName(),
                table.getEcgCount(),
                table.getEcgPrice(),
                table.getTotalEcgPrice()
        );
    }
}
