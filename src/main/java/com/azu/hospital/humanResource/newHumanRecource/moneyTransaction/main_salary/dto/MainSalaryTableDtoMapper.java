package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.dto;

import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.dto.CustomSalaryMapper;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.entity.MainSalaryTable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MainSalaryTableDtoMapper implements Function<MainSalaryTable, MainSalaryTableDto> {
    private final CustomSalaryMapper mapper;

    public MainSalaryTableDtoMapper(CustomSalaryMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public MainSalaryTableDto apply(MainSalaryTable mainSalaryTable) {
        return new MainSalaryTableDto(
                mainSalaryTable.getMainSalaryId(),
                mainSalaryTable.getUsers().getId(),
                mainSalaryTable.getTotalSalary(),
                mainSalaryTable.getYearMonth().toString(),
                mainSalaryTable.getCreatedAt(),
                mainSalaryTable.getUpdatedAt(),
                mainSalaryTable.getCustomSalaries()
                        .stream()
                        .map(mapper)
                        .toList()
        );
    }
}
