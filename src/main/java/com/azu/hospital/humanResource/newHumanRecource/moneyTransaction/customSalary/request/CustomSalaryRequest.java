package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.request;

import jakarta.validation.constraints.NotNull;

public record CustomSalaryRequest(
        @NotNull(message = "title should not be null")
        String title,
        @NotNull(message = "amount should not be null")
        Double amount,
        @NotNull(message = "is_down should not be null")
        Boolean isDown,
        @NotNull(message = "title should not be null")
        String note,

        Boolean isDeleted
){
}
