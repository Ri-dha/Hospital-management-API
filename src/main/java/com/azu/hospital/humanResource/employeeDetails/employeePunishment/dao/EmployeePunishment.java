package com.azu.hospital.humanResource.employeeDetails.employeePunishment.dao;

import com.azu.hospital.humanResource.employeeDetails.employee.dao.Employee;
import com.azu.hospital.humanResource.employeeDetails.employeeEnum.PunishmentStatusEnum;
import com.azu.hospital.humanResource.employeeDetails.employeeEnum.PunishmentTypesEnum;
import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;

@Entity
@Table(name = "employee_punishment")
@Data
public class EmployeePunishment {
  @Id
  @SequenceGenerator(
          name = "employee_punishment_id_sequence",
          sequenceName = "employee_punishment_id_sequence"
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "employee_punishment_id_sequence"
  )
  private Long punishmentId;

  @Enumerated(EnumType.STRING)
  private PunishmentTypesEnum punishmentType;

  @Column(columnDefinition = "TEXT")
  private String punishmentNote;

  @Enumerated(EnumType.STRING)
  private PunishmentStatusEnum status = PunishmentStatusEnum.PUNISHED;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;

  @Column(updatable = false)
  private Instant createdAt;

  private Instant updatedAt;

  public EmployeePunishment() {
  }

  public EmployeePunishment(
          PunishmentTypesEnum punishmentType,
          String punishmentNote
  ) {
    this.punishmentType = punishmentType;
    this.punishmentNote = punishmentNote;
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
