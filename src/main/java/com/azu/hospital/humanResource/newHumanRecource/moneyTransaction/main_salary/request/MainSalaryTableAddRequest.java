package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.request;

import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.request.CustomSalaryRequest;
import jakarta.validation.constraints.NotNull;

import java.time.Month;
import java.util.List;

public record MainSalaryTableAddRequest (

        List<CustomSalaryRequest> bonusesAndDeductions


){
}
