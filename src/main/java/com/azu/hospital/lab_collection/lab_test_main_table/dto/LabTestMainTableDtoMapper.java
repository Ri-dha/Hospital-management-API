package com.azu.hospital.lab_collection.lab_test_main_table.dto;

import com.azu.hospital.lab_collection.lab_test_main_table.entity.LabTestMainTableForMainTestName;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class LabTestMainTableDtoMapper implements Function<LabTestMainTableForMainTestName, LabTestMainTableDto> {
    @Override
    public LabTestMainTableDto apply(LabTestMainTableForMainTestName labTestMainTableForMainTestName) {
        return new LabTestMainTableDto(
                labTestMainTableForMainTestName.getTestId(),
                labTestMainTableForMainTestName.getTestName(),
                labTestMainTableForMainTestName.getNote(),
                labTestMainTableForMainTestName.getPrice(),
                labTestMainTableForMainTestName.getNormalValue()
        );
    }
}
