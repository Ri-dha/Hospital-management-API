package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.dto;

import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.entity.CustomSalary;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service("customSalaryMapper")
public class CustomSalaryMapper implements Function<CustomSalary, CustomSalaryDetailDto> {

    @Override
    public CustomSalaryDetailDto apply(CustomSalary customSalary) {
        return new CustomSalaryDetailDto(
                customSalary.getId(),
                customSalary.getTitle(),
                customSalary.getAmount(),
                customSalary.getIsDown(),
                customSalary.getNote(),
                customSalary.getIsDeleted(),
                customSalary.getCreatedAt(),
                customSalary.getUpdatedAt()
        );
    }
}

