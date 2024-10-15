package com.azu.hospital.humanResource.employeeDetails.salaryDetails.dto;

import com.azu.hospital.humanResource.employeeDetails.employeeEnum.SalaryComponentsType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalaryDetailsDto {

  private Long salaryId;

  private SalaryComponentsType type;

  private BigDecimal amount;

  private String note;

  private Long employeeId;

  public SalaryDetailsDto() {
  }

  public SalaryDetailsDto(
          Long salaryId,
          SalaryComponentsType type,
          BigDecimal amount,
          String note,
          Long employeeId
  ) {
    this.salaryId = salaryId;
    this.type = type;
    this.amount = amount;
    this.note = note;
    this.employeeId = employeeId;
  }
}
