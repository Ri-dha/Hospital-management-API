package com.azu.hospital.humanResource.employeeDetails.employee.dto;

import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.humanResource.employeeDetails.employeeEnum.EmployeeStatusEnum;
import com.azu.hospital.utils.enums.EnumRole;
import com.azu.hospital.utils.enums.Gender;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class EmployeeDto {
  private String name;

  private String specialty;

  private Gender gender;

  private String phoneNo;

  private Integer age;

  private String employeeDate;

  private Long id;

  private Set<Role> roles;

  private EmployeeStatusEnum status;



  public EmployeeDto() {
  }

  public EmployeeDto(
          String name,
          String specialty,
          Gender gender,
          String phoneNo,
          Integer age,
          String employeeDate,
          Long id,
          Set<Role> roles,
          EmployeeStatusEnum status

  ) {
    this.name = name;
    this.specialty = specialty;
    this.gender = gender;
    this.phoneNo = phoneNo;
    this.age = age;
    this.employeeDate = employeeDate;
    this.id = id;
    this.roles = roles;
    this.status = status;

  }
}
