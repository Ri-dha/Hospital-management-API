package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.request;

import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.request.CustomSalaryUpdate;

import java.time.Month;
import java.util.List;

public record MainSalaryTableUpdateRequest (
        List<CustomSalaryUpdate> bonusesAndDeductions
){
}
