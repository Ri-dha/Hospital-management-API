package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.request;

public record CustomSalaryUpdate(
        Long id,

        String title,

        Double amount,

        Boolean isDown,

        String note,

        Boolean isDeleted
){
}
