package com.azu.hospital.accounting.all_item_expanse.radiology.dto;

import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDto;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.PatientExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.radiology.entity.PatientRadiologyExpanseResultTable;
import org.springframework.stereotype.Service;

@Service
public class PatientExpanseFinalListRadiologyDtoMapper implements PatientExpanseFinalList<PatientRadiologyExpanseResultTable> {


    @Override
    public ExpanseDto getExpanseDtoList(PatientRadiologyExpanseResultTable table) {
        return new ExpanseDto(
                table.getId(),
                table.getRadiologyId(),
                table.getRadiologyName().getName(),
                table.getRadiologyCount(),
                table.getRadiologyPrice(),
                table.getTotalRadiologyPrice()
        );
    }
}
