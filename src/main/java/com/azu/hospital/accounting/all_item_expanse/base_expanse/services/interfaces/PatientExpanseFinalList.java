package com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces;

import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDto;

public interface PatientExpanseFinalList <T> {
    ExpanseDto getExpanseDtoList(T table);
}
