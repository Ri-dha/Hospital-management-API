package com.azu.hospital.humanResource.employeeDetails.salaryDetails.dao;

import com.azu.hospital.humanResource.employeeDetails.employee.dao.Employee;
import com.azu.hospital.humanResource.employeeDetails.employeeEnum.SalaryComponentsType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "employee_salary_details")
@Data
public class SalaryDetails {
  @Id
  @SequenceGenerator(
          name = "employee_salary_details_id_sequence",
          sequenceName = "employee_salary_details_id_sequence"
  )
  @GeneratedValue(
          generator = "employee_salary_details_id_sequence",
          strategy = GenerationType.SEQUENCE
  )
  private Long salaryId;


  @NotNull(message = "Type is required")
  private SalaryComponentsType type;

  @NotNull(message = "Amount is required")
  private BigDecimal amount;

  private String note;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;
  
  @Column(updatable = false)
  private Instant createdAt;
  
  private Instant updatedAt;

  public SalaryDetails() {
  }

  public SalaryDetails(SalaryComponentsType type, BigDecimal amount, String note) {
    this.type = type;
    this.amount = amount;
    this.note = note;
  }

  @PrePersist
  public void onCreate(){
    this.createdAt = Instant.now();
  }
  @PreUpdate
  public void onUpdate(){
    this.updatedAt = Instant.now();
  }
}
