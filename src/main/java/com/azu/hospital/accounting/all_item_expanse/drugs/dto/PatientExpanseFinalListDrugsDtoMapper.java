package com.azu.hospital.accounting.all_item_expanse.drugs.dto;

import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDto;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.PatientExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.drugs.entity.PatientDrugsExpanseResultTable;
import org.springframework.stereotype.Service;

@Service
public class PatientExpanseFinalListDrugsDtoMapper implements PatientExpanseFinalList<PatientDrugsExpanseResultTable> {

    @Override
    public ExpanseDto getExpanseDtoList(PatientDrugsExpanseResultTable table) {
        return new ExpanseDto(
                table.getId(),
                table.getDrugName(),
                table.getDrugCount()
        );
    }
}
