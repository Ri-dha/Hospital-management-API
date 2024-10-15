package com.azu.hospital.accounting.all_item_expanse.ultrasound.dto;

import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDto;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.PatientExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.ultrasound.entity.PatientUltrasoundExpanseResultTable;
import org.springframework.stereotype.Service;

@Service
public class PatientExpanseFinalListUltraSoundDtoMapper implements PatientExpanseFinalList<PatientUltrasoundExpanseResultTable> {


    @Override
    public ExpanseDto getExpanseDtoList(PatientUltrasoundExpanseResultTable table) {
        return new ExpanseDto(
                table.getId(),
                table.getUltrasoundId(),
                table.getUltrasoundName().getName(),
                table.getUltrasoundCount(),
                table.getUltrasoundPrice(),
                table.getTotalUltrasoundPrice()
        );
    }
}
