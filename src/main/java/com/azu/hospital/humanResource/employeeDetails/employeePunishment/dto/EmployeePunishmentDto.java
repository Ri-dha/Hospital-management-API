package com.azu.hospital.humanResource.employeeDetails.employeePunishment.dto;

import com.azu.hospital.humanResource.employeeDetails.employeeEnum.PunishmentStatusEnum;
import com.azu.hospital.humanResource.employeeDetails.employeeEnum.PunishmentTypesEnum;
import lombok.Data;

@Data
public class EmployeePunishmentDto {
  private Long id;

  private PunishmentTypesEnum punishmentType;

  private String punishmentNote;

  private PunishmentStatusEnum status;

  private Long employeeId;

  public EmployeePunishmentDto() {
  }

  public EmployeePunishmentDto(
          Long id,
          PunishmentTypesEnum punishmentType,
          String punishmentNote,
          Long employeeId
  ) {
    this.id = id;
    this.punishmentType = punishmentType;
    this.punishmentNote = punishmentNote;
    this.employeeId = employeeId;
  }
}
